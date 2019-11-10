package soe.mdeis.modulo5.virtualwallet.database.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soe.mdeis.modulo5.virtualwallet.database.models.User;
import soe.mdeis.modulo5.virtualwallet.database.repositories.UserRepository;
import soe.mdeis.modulo5.virtualwallet.database.services.UserService;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
