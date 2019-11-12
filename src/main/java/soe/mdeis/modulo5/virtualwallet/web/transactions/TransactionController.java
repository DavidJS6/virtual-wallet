package soe.mdeis.modulo5.virtualwallet.web.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soe.mdeis.modulo5.virtualwallet.database.models.Transaction;
import soe.mdeis.modulo5.virtualwallet.web.transactions.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping(value = "api/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    private final TransactionService service;

    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveTransaction(@RequestBody TransactionRequestDto requestDto) {
        return new ResponseEntity<>(service.store(requestDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public TransactionResponseDto getSingle(@PathVariable int id) throws TransactionException {
        return service.findById(id);
    }

    @GetMapping
    public List<TransactionResponseDto> getAll() {
        return service.findAll();
    }

}
