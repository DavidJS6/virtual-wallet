package soe.mdeis.modulo5.virtualwallet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import soe.mdeis.modulo5.virtualwallet.database.models.User;
import soe.mdeis.modulo5.virtualwallet.database.services.UserService;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private UserService service;

    @GetMapping("/")
    /*
    @RequestMapping(
            value = "/",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    */
    public ResponseEntity<Object> home() {
        List<User> all = service.findAll();
        return new ResponseEntity<>("Api Rest is up! Users count: " + all.size(), HttpStatus.OK);

    }

}