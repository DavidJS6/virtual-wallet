package soe.mdeis.modulo5.virtualwallet.web.sessions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soe.mdeis.modulo5.virtualwallet.web.auth.UserDetailsServiceImpl;
import soe.mdeis.modulo5.virtualwallet.web.auth.jwt.JwtGenerator;
import soe.mdeis.modulo5.virtualwallet.web.sessions.service.SessionService;

@RestController
@RequestMapping(value = "api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class SessionController {
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator tokenGenerator;
    private final UserDetailsServiceImpl userDetailsService;
    private final SessionService sessionService;

    @Autowired
    public SessionController(AuthenticationManager authenticationManager, JwtGenerator tokenGenerator,
                             UserDetailsServiceImpl userDetailsService, SessionService sessionService) {
        this.authenticationManager = authenticationManager;
        this.tokenGenerator = tokenGenerator;
        this.userDetailsService = userDetailsService;
        this.sessionService = sessionService;
    }

    @PostMapping("login")
    public LoginResponseDto login(@RequestBody LoginRquestDto loginRequest) throws Exception {
        authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String token = tokenGenerator.generateToken(userDetails);
        return sessionService.login(loginRequest, token);
    }

    @PostMapping("logout")
    public boolean login(@RequestBody LogoutRquestDto logoutRquest) throws Exception {
        return sessionService.logout(logoutRquest);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
