package soe.mdeis.modulo5.virtualwallet.web.sessions.service;

import soe.mdeis.modulo5.virtualwallet.web.sessions.LoginResponseDto;
import soe.mdeis.modulo5.virtualwallet.web.sessions.LoginRquestDto;
import soe.mdeis.modulo5.virtualwallet.web.sessions.LogoutRquestDto;

public interface SessionService {
    LoginResponseDto login(LoginRquestDto loginRequest, String token) throws Exception;

    boolean logout(LogoutRquestDto logoutRequest) throws Exception;
}
