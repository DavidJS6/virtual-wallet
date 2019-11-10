package soe.mdeis.modulo5.virtualwallet.web.users;

public class UserResponseDto extends UserDto {
    private Integer id;

    public UserResponseDto(Integer id, String email, String name) {
        super(email, name);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
