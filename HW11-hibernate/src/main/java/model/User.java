package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User implements Serializable {
    @Id
    private long id;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Phone> phones;

    public User() {
    }

    public User(String name,String password, int age, long id) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
        phones = new ArrayList<>();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addPhone(Phone phone) {
        this.phones.add(phone);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(this.getClass().getSimpleName());
        stringBuilder.append(" id :");
        stringBuilder.append(id + "; ");
        stringBuilder.append("Password :");
        stringBuilder.append(password + "; ");
        stringBuilder.append("Name :");
        stringBuilder.append(name + "; ");
        stringBuilder.append("Age :");
        stringBuilder.append(age + "; ");
        stringBuilder.append(getAddress());
        phones.forEach(phone -> stringBuilder.append(phone.toString()));
        return stringBuilder.toString();
    }
}
