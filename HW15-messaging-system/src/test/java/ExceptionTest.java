import app.DBService;
import app.FrontendService;
import app.MessageSystemContext;
import app.db.DBServiceImpl;
import app.frontend.FrontendServiceImpl;
import app.frontend.websockets.MyWebSocket;
import app.messages.MsgAdd;
import app.messages.MsgShow;
import com.google.gson.Gson;
import messageSystem.Address;
import messageSystem.Message;
import messageSystem.MessageSystem;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import resource.TemplateProcessor;
import service.UserService;
import service.UserServiceImpl;


public class ExceptionTest {
    private final UserService service;

    private final TemplateProcessor templateProcessor;
    private final MessageSystem messageSystem;
    private final MessageSystemContext context;
    private final Address frontAddress;
    private final Address dbAddress;
    private final FrontendService frontendService;
    private final DBService dbService;

    public ExceptionTest() {
        service = addDBService();
        templateProcessor = new TemplateProcessor();

        messageSystem = new MessageSystem();
        context = new MessageSystemContext(messageSystem);
        frontAddress = new Address("Frontend");
        dbAddress = new Address("DB");
        frontendService = new FrontendServiceImpl(context, frontAddress);
        dbService = new DBServiceImpl(context, dbAddress, service);

        startMessageSystem();
    }

    @Test
    void checkShowAfterAddDBService() {
        String normalString = new Gson().toJson(service.loadAll());
        receiveAddMessage();
        Assertions.assertEquals(normalString, receiveShowMessage());
    }
    @Test
    void checkShowDBService() {
        String normalString = new Gson().toJson(service.loadAll());
        receiveAddMessage();
        Assertions.assertEquals(normalString, receiveShowMessage());
    }

    private String receiveShowMessage(){
        final String[] showString = new String[1];
        MyWebSocket showSocket = message -> showString[0] = message;

        Message showToDBMessage = new MsgShow(frontendService.getAddress(),
                frontendService.getContext().getDbAddress(),
                frontendService.registerWebSocket(showSocket));
        frontendService.getMS().sendMessage(showToDBMessage);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return showString[0];
    }

    private String receiveAddMessage(){
        final String[] addString = new String[1];
        MyWebSocket addSocket = message -> addString[0] = message;

        Message addToDBMessage = new MsgAdd(
                frontendService.getAddress(),
                frontendService.getContext().getDbAddress(),
                frontendService.registerWebSocket(addSocket),
                null);
        frontendService.getMS().sendMessage(addToDBMessage);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return addString[0];
    }

    private UserService addDBService() {
        UserService service = new UserServiceImpl();
        User admin = new User("Admin", "admin", 33, 1);
        service.save(admin);
        return service;
    }

    private void startMessageSystem() {
        context.setFrontAddress(frontAddress);
        context.setDbAddress(dbAddress);
        frontendService.init();
        dbService.init();
        messageSystem.start();
    }
}
