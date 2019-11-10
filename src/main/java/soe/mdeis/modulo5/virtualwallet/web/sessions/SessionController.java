package soe.mdeis.modulo5.virtualwallet.web.sessions;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/session", produces = MediaType.APPLICATION_JSON_VALUE)
public class SessionController {
}
