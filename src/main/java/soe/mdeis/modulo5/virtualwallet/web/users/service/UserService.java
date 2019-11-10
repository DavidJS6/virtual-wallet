package soe.mdeis.modulo5.virtualwallet.web.users.service;

import soe.mdeis.modulo5.virtualwallet.database.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(int id);

    //boolean store(User user);
}
