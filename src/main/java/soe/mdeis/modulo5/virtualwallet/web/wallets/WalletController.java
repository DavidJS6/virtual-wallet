package soe.mdeis.modulo5.virtualwallet.web.wallets;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/wallets", produces = MediaType.APPLICATION_JSON_VALUE)
public class WalletController {
}
