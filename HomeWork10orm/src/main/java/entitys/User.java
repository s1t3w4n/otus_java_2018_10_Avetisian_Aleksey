package entitys;

import entitys.annotations.ID;
import dbservise.DBServiceUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User {
    @ID(developer = "Anonymous")
    private final long ID;
    private final String name;
    private final int age;
    //private static Logger logger = LoggerFactory.getLogger(DBServiceUser.class);

    public User(long ID, String name, int age) {

        this.ID = ID;
        this.name = name;
        this.age = age;

      //  logger.info(toString());
    }
    public User(Long ID, String name, Integer age) {

        this.ID = ID;
        this.name = name;
        this.age = age;

        //  logger.info(toString());
    }

    public long getID() {
        return ID;
    }


    public String getName() {
        return name;
    }


    public int getAge() {
        return age;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(this.getClass().getSimpleName());
        stringBuilder.append(" ID :");
        stringBuilder.append(getID() + "; ");
        stringBuilder.append("Name :");
        stringBuilder.append(getName() + "; ");
        stringBuilder.append("Age :");
        stringBuilder.append(getAge() + "; ");
        return stringBuilder.toString();
    }
}
