package soe.mdeis.modulo5.virtualwallet.web.wallets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soe.mdeis.modulo5.virtualwallet.database.models.Wallet;
import soe.mdeis.modulo5.virtualwallet.database.repositories.WalletRepository;
import soe.mdeis.modulo5.virtualwallet.web.users.UserException;
import soe.mdeis.modulo5.virtualwallet.web.users.service.UserService;
import soe.mdeis.modulo5.virtualwallet.web.wallets.WalletRequestDto;
import soe.mdeis.modulo5.virtualwallet.web.wallets.WalletResponseDto;

import java.util.List;

@Service("WalletService")
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserService userService;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository, UserService userService) {
        this.walletRepository = walletRepository;
        this.userService = userService;
    }

    @Override
    public List<WalletResponseDto> findAll() {
        return null;
    }

    @Override
    public WalletResponseDto findById(int id) throws UserException {
        return null;
    }

    @Override
    public WalletResponseDto update(int id, WalletRequestDto requestDto) {
        return null;
    }

    @Override
    public WalletResponseDto store(WalletRequestDto requestDto) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
