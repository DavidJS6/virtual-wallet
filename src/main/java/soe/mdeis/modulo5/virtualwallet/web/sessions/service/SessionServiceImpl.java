package soe.mdeis.modulo5.virtualwallet.web.sessions.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soe.mdeis.modulo5.virtualwallet.database.models.LogEvent;
import soe.mdeis.modulo5.virtualwallet.database.models.LogEventType;
import soe.mdeis.modulo5.virtualwallet.database.models.User;
import soe.mdeis.modulo5.virtualwallet.database.repositories.LogEventRepository;
import soe.mdeis.modulo5.virtualwallet.database.repositories.UserRepository;
import soe.mdeis.modulo5.virtualwallet.web.parsing.EntityToResponseDtoParser;
import soe.mdeis.modulo5.virtualwallet.web.parsing.EntityToResponseDtoParserImpl;
import soe.mdeis.modulo5.virtualwallet.web.sessions.LoginResponseDto;
import soe.mdeis.modulo5.virtualwallet.web.sessions.LoginRquestDto;
import soe.mdeis.modulo5.virtualwallet.web.sessions.LogoutRquestDto;
import soe.mdeis.modulo5.virtualwallet.web.sessions.UserResponseDto;

import java.sql.Timestamp;

@Service
public class SessionServiceImpl implements SessionService {
    private final UserRepository userRepository;
    private final LogEventRepository logEventRepository;
    private EntityToResponseDtoParser<User, UserResponseDto> parser = new UserParser();

    @Autowired
    public SessionServiceImpl(UserRepository userRepository, LogEventRepository logEventRepository) {
        this.userRepository = userRepository;
        this.logEventRepository = logEventRepository;
    }

    @Override
    public LoginResponseDto login(LoginRquestDto loginRequest, String token) throws Exception {
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new Exception());
        UserResponseDto userResponseDto = parser.parseEntityToResponseDto(user);
        saveLoginEvent(user);
        return new LoginResponseDto(userResponseDto, token);
    }

    @Override
    public boolean logout(LogoutRquestDto logoutRequest) throws Exception {
        User user = userRepository.findById(logoutRequest.getUser_id()).orElseThrow(() -> new Exception());
        saveLogoutEvent(user);
        return true;
    }

    private void saveLoginEvent(User user) {
        saveLogEvent(user, LogEventType.LOG_IN.toString());
    }

    private void saveLogoutEvent(User user) {
        saveLogEvent(user, LogEventType.LOG_OUT.toString());
    }

    private void saveLogEvent(User user, String eventType) {
        logEventRepository.save(new LogEvent(
                new Timestamp(System.currentTimeMillis()), eventType,
                user
        ));
    }

    private static class UserParser extends EntityToResponseDtoParserImpl<User, UserResponseDto> {
        @Override
        public UserResponseDto parseEntityToResponseDto(User user) {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(user, UserResponseDto.class);
        }
    }
}
