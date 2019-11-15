package soe.mdeis.modulo5.virtualwallet.web.users.service;

import soe.mdeis.modulo5.virtualwallet.web.ResourceService;
import soe.mdeis.modulo5.virtualwallet.web.users.UserException;
import soe.mdeis.modulo5.virtualwallet.web.users.UserRequestDto;
import soe.mdeis.modulo5.virtualwallet.web.users.UserResponseDto;
import soe.mdeis.modulo5.virtualwallet.web.users.WalletResponseDto;

import java.util.List;

public interface UserService extends ResourceService<UserRequestDto, UserResponseDto, UserException> {

    List<WalletResponseDto> getUserWallets(int id);

}
