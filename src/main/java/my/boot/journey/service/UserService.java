package my.boot.journey.service;

import my.boot.journey.dao.UserDao;
import my.boot.journey.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public User getUserDetail(int id) {
        return userDao.getUserDetail(id);
    }

    public int createUser(User user) {
        return userDao.createUser(user);
    }

    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    public int deleteUser(int id) {
        return userDao.deleteUser(id);
    }
}
