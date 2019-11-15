package soe.mdeis.modulo5.virtualwallet.web.sessions;

public class LoginResponseDto {
    private UserResponseDto user;
    private String token;

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
}
