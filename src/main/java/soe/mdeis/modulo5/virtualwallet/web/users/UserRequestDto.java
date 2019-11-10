package soe.mdeis.modulo5.virtualwallet.web.users;

public class UserRequestDto extends UserDto {
    private String password;

    public UserRequestDto(String email, String name, String password) {
        super(email, name);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
