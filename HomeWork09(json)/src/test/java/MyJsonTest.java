import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MyJsonTest {
    private Gson gson = new Gson();
    private MyJson json = new MyJson();

    @Test
    public void shouldTransformNull() throws IllegalAccessException {
        Object o = null;
        Assertions.assertEquals(gson.toJson(o), "123");
    }
    /*private MyJson json = new MyJson();

    *//*@BeforeAll
    public void configure(){
        gson = new Gson();
        json = new MyJson();
    }*//*

    @Test
    public void checkNull() {
        Object o = null;
        String myJson = json.toJson(o);
        Assertions.assertEquals(gson.toJson(o), myJson);
    }
    @Test
    public void checkLong() {
        long l = Long.MAX_VALUE;

        Assertions.assertEquals(gson.toJson(l), json.toJson(l));
    }
    @Test
    public void checkInteger() {
        int i = 47;

        Assertions.assertEquals(gson.toJson(i), json.toJson(i));
    }
    @Test
    public void checkShort(){
        short s = 47;

        Assertions.assertEquals(gson.toJson(s), json.toJson(s));
    }
    @Test
    public void checkByte(){
        byte b = 47;

        Assertions.assertEquals(gson.toJson(b), json.toJson(b));
    }*/

}
