package my.boot.journey.dao;

import com.google.common.cache.LoadingCache;
import my.boot.journey.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {
    private static final String LOGIN_TABLE = "login";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getUsers() {
        //due to h2 user identifier issue, table user changed to LOGIN
        return jdbcTemplate.query("select * from " + LOGIN_TABLE, (resultSet, i) -> {
            return toUser(resultSet);
        });
    }

    public User getUserDetail(int id) {
        return jdbcTemplate.queryForObject("select * from " + LOGIN_TABLE + " where id=?",
                (resultSet, i) -> {
                    return toUser(resultSet);
                },id);
    }

    public int createUser(User user) {
        String sql = "insert into " + LOGIN_TABLE + " (username, password, firstname, lastname, email) values (?, ?, ?,?,?)";
        return jdbcTemplate.update(sql, user.getUsername(),null,user.getFirstname(), user.getLastname(),user.getEmail());
    }

    public int updateUser(User user) {
        String sql = "update " + LOGIN_TABLE + " set (username, password, firstname, lastname, email) values (?, ?, ?,?,?) where id=?";
        return jdbcTemplate.update(sql, user.getUsername(),null,user.getFirstname(), user.getLastname(),user.getEmail(),user.getId());
    }

    public int deleteUser(int id) {
        String sql = "delete from "+ LOGIN_TABLE +" WHERE id = " +id ;
        return jdbcTemplate.update(sql);
    }

    private User toUser(ResultSet resultSet) throws SQLException{
        User user = new User();
        user.setId(resultSet.getLong("ID"));
        user.setUsername(resultSet.getString("USERNAME"));
        user.setFirstname(resultSet.getString("FIRSTNAME"));
        user.setLastname(resultSet.getString("LASTNAME"));
        user.setEmail(resultSet.getString("EMAIL"));
        return user;
    }
}
