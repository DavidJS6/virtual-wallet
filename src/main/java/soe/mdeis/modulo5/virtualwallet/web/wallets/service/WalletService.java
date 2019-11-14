package soe.mdeis.modulo5.virtualwallet.web.wallets.service;

import soe.mdeis.modulo5.virtualwallet.web.ResourceService;
import soe.mdeis.modulo5.virtualwallet.web.transactions.TransactionResponseDto;
import soe.mdeis.modulo5.virtualwallet.web.wallets.WalletException;
import soe.mdeis.modulo5.virtualwallet.web.wallets.WalletRequestDto;
import soe.mdeis.modulo5.virtualwallet.web.wallets.WalletResponseDto;

import java.util.List;

public interface WalletService extends ResourceService<WalletRequestDto, WalletResponseDto, WalletException> {
    public List<TransactionResponseDto> getWalletTransactions(int id);
}
