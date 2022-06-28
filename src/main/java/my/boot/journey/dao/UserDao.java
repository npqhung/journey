package my.boot.journey.dao;

import my.boot.journey.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    private static final String LOGIN_TABLE = "login";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getUsers() {
        //due to h2 user identifier issue, table user changed to LOGIN
        return jdbcTemplate.query("select * from " + LOGIN_TABLE, (resultSet, i) -> {
            User user = new User();
            user.setId(resultSet.getLong("ID"));
            user.setUsername(resultSet.getString("USERNAME"));
            user.setFirstname(resultSet.getString("FIRSTNAME"));
            user.setLastname(resultSet.getString("LASTNAME"));
            user.setEmail(resultSet.getString("EMAIL"));
            return user;
        });
    }
}
