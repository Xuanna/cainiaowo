package ui.entiity;

/**
 * Created by ; on 2017/10/6.
 */

public class User {
    public int age;
    public String email;
    public String passward;

    public User(int age, String email, String passward) {
        this.age = age;
        this.email = email;
        this.passward = passward;
    }

    public User() {
    }

    public User(String email, String passward) {
        this.email = email;
        this.passward = passward;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", email='" + email + '\'' +
                ", passward='" + passward + '\'' +
                '}';
    }
}
