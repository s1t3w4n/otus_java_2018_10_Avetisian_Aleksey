package entitys;

import entitys.annotations.ID;

public class User {
    @ID(developer = "Anonymous")
    private final long ID;
    private final String name;
    private final int age;

    public User(long ID, String name, int age) {

        this.ID = ID;
        this.name = name;
        this.age = age;

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
