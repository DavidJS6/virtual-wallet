package soe.mdeis.modulo5.virtualwallet.web.sessions;

public class LoginResponseDto {
    private String token;
    private UserResponseDto user;

    public LoginResponseDto(UserResponseDto user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserResponseDto getUser() {
        return user;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }
}
