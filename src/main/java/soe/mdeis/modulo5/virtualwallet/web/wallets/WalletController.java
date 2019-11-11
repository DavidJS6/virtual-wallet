package soe.mdeis.modulo5.virtualwallet.web.wallets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soe.mdeis.modulo5.virtualwallet.database.models.Wallet;
import soe.mdeis.modulo5.virtualwallet.web.wallets.service.WalletService;

import java.util.List;

@RestController
@RequestMapping(value = "api/wallets", produces = MediaType.APPLICATION_JSON_VALUE)
public class WalletController {

    private final WalletService service;

    @Autowired
    public WalletController(WalletService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveWallet(@RequestBody WalletRequestDto requestDto) {
        return new ResponseEntity<>(service.save(requestDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateWallet(@PathVariable int id, @RequestBody WalletRequestDto requestDto) {
        return new ResponseEntity<>(service.update(id, requestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWallet(@PathVariable int id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Wallet getSingle(@PathVariable int id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Wallet> getAll() {
        return service.findAll();
    }


}
