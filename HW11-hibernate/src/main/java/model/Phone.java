package model;

import javax.persistence.*;

@Entity
public class Phone {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "number")
    private String number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Phone() {
    }

    public Phone(String number, User user) {
        this.number = number;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(this.getClass().getSimpleName());
        stringBuilder.append(" id :");
        stringBuilder.append(id);
        stringBuilder.append("; Phone: ");
        stringBuilder.append(number);
        return stringBuilder.toString();
    }
}
