package soe.mdeis.modulo5.virtualwallet.web.wallets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public List<WalletResponseDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public WalletResponseDto getSingle(@PathVariable int id) throws WalletException {
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> store(@RequestBody WalletRequestDto requestDto) throws WalletException {
        return new ResponseEntity<>(service.store(requestDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody WalletRequestDto requestDto) throws WalletException {
        return new ResponseEntity<>(service.update(id, requestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) throws WalletException {
        service.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/by-number/{walletNumber}")
    public WalletResponseDto getSingleByNumber(@PathVariable String walletNumber) throws WalletException {
        return service.findByNumber(walletNumber);
    }

    @GetMapping("/{id}/transactions")
    public List<TransactionResponseDto> getTransactions(@PathVariable int id) {
        return service.getWalletTransactions(id);
    }
}
