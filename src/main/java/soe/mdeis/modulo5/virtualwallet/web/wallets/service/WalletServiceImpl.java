package soe.mdeis.modulo5.virtualwallet.web.wallets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soe.mdeis.modulo5.virtualwallet.database.models.Wallet;
import soe.mdeis.modulo5.virtualwallet.database.repositories.UserRepository;
import soe.mdeis.modulo5.virtualwallet.database.repositories.WalletRepository;
import soe.mdeis.modulo5.virtualwallet.web.users.service.UserService;
import soe.mdeis.modulo5.virtualwallet.web.wallets.WalletRequestDto;

import java.util.List;

@Service("WalletService")
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Wallet save(WalletRequestDto request) {
        Wallet wallet = new Wallet();
        wallet.setWalletNumber(request.getWallet_number());
        //wallet.setBalance(request.getBalance());
        wallet.setUser(userRepository.findById(request.getUser_id()).get());
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet update(int id, WalletRequestDto request) {
        Wallet wallet = findById(id);
        wallet.setWalletNumber(request.getWallet_number());
        //wallet.setBalance(request.getBalance());
        wallet.setUser(userRepository.findById(request.getUser_id()).get());
        return walletRepository.save(wallet);
    }

    @Override
    public boolean delete(int id) {
        Wallet wallet = findById(id);
        walletRepository.delete(wallet);
        return true;
    }

    @Override
    public Wallet findById(int id) {
        return walletRepository.findById(id).orElse(null);
    }

    @Override
    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }

}
