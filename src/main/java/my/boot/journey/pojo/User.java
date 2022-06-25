package my.boot.journey.pojo;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
