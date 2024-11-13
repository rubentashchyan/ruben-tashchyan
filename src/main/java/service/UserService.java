package service;

import dao.UserDao;
import entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public void saveUser(User user) {
        userDao.save(user);
    }
    public List <User> getAllUsers() {
        return userDao.findAll();
    }
}
