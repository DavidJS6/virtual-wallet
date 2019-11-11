package soe.mdeis.modulo5.virtualwallet.web.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import soe.mdeis.modulo5.virtualwallet.web.users.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public UserResponseDto getSingle(@PathVariable int id) throws UserException {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable int id, @RequestBody UserRequestDto userRequest) throws UserException {
        return service.update(id, userRequest);
    }

    @PostMapping
    public UserResponseDto store(@RequestBody UserRequestDto userRequest) throws UserException {
        return service.store(userRequest);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) throws UserException {
        service.delete(id);
        return true;
    }

    @GetMapping("/{id}/wallets")
    public List<WalletResponseDto> getWallets(@PathVariable int id) {
        return service.getUserWallets(id);
    }

}
