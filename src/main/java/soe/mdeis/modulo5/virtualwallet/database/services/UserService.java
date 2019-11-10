package soe.mdeis.modulo5.virtualwallet.database.services;

import soe.mdeis.modulo5.virtualwallet.database.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

}
