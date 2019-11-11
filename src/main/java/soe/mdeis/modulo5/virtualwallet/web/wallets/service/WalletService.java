package soe.mdeis.modulo5.virtualwallet.web.wallets.service;

import soe.mdeis.modulo5.virtualwallet.database.models.Wallet;
import soe.mdeis.modulo5.virtualwallet.database.repositories.WalletRepository;
import soe.mdeis.modulo5.virtualwallet.web.wallets.WalletRequestDto;
import soe.mdeis.modulo5.virtualwallet.web.wallets.WalletResponseDto;

import java.util.List;

public interface WalletService {

    Wallet save(WalletRequestDto request);

    Wallet update(int id, WalletRequestDto request);

    boolean delete(int id);

    Wallet findById(int id);

    List<Wallet> findAll();

}
