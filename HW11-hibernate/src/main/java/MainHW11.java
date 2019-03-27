import model.Address;
import model.Phone;
import model.User;
import service.UserService;
import service.UserServiceImpl;

import java.io.*;

public class MainHW11 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        UserService userService = new UserServiceImpl();
        //CREATE
        User created_user = new User("Ivan","qwerty", 33, 007L);
        userService.save(created_user);
        User created_user1 = new User("Bogdan","qwerty", 22, 123);
        userService.save(created_user);
        User created_user2 = new User("Fontan","qwerty", 44, 99999);
        userService.save(created_user);
        //UPDATE
        User updated_user = clone(created_user);
        Address address = new Address("Lenina", updated_user);
        updated_user.setAddress(address);
        Phone phone_u_1 = new Phone("1234", updated_user);
        Phone phone_u_2 = new Phone("4321", updated_user);
        updated_user.addPhone(phone_u_1);
        updated_user.addPhone(phone_u_2);
        updated_user.setAge(55);
        userService.save(updated_user);
        //LOAD
        User loaded_user = userService.load(007l);
        //wrong id
        userService.load(333);

        System.out.println("CREATED");
        System.out.println(created_user.toString());

        System.out.println("UPDATED");
        System.out.println(updated_user.toString());

        System.out.println("LOADED");
        System.out.println(loaded_user.toString());
    }

    private static User clone(User user) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream ous = new ObjectOutputStream(baos);
        ous.writeObject(user);
        ous.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return  (User) ois.readObject();
    }

}
