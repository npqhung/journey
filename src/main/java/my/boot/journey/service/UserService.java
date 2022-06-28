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
}
