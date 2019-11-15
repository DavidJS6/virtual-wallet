package soe.mdeis.modulo5.virtualwallet.web.wallets.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soe.mdeis.modulo5.virtualwallet.database.models.Transaction;
import soe.mdeis.modulo5.virtualwallet.database.models.User;
import soe.mdeis.modulo5.virtualwallet.database.models.Wallet;
import soe.mdeis.modulo5.virtualwallet.database.repositories.TransactionRepository;
import soe.mdeis.modulo5.virtualwallet.database.repositories.WalletRepository;
import soe.mdeis.modulo5.virtualwallet.web.parsing.DtoEntityParser;
import soe.mdeis.modulo5.virtualwallet.web.parsing.DtoEntityParserAbstractImpl;
import soe.mdeis.modulo5.virtualwallet.web.parsing.EntityToResponseDtoParserImpl;
import soe.mdeis.modulo5.virtualwallet.web.wallets.TransactionResponseDto;
import soe.mdeis.modulo5.virtualwallet.web.wallets.WalletException;
import soe.mdeis.modulo5.virtualwallet.web.wallets.WalletRequestDto;
import soe.mdeis.modulo5.virtualwallet.web.wallets.WalletResponseDto;

import java.util.List;

@Service("WalletService")
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    private DtoEntityParser<WalletRequestDto, WalletResponseDto, Wallet> parser = new WalletParser();

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<WalletResponseDto> findAll() {
        List<Wallet> wallets = walletRepository.findAll();
        return parser.parseEntitiesToResponseDtos(wallets);
    }

    @Override
    public WalletResponseDto findById(int id) throws WalletException {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new WalletException("Wallet with specified id not found."));
        return parser.parseEntityToResponseDto(wallet);
    }

    @Override
    public WalletResponseDto update(int id, WalletRequestDto requestDto) throws WalletException {
        Wallet walletToUpdate = walletRepository.findById(id).orElseThrow(() -> new WalletException("Wallet with specified id not found."));
        walletToUpdate.setWalletNumber(requestDto.getWalletNumber());
        User user = new User();
        user.setId(requestDto.getUserId());
        walletToUpdate.setUser(user);
        Wallet updatedWallet = walletRepository.save(walletToUpdate);
        return parser.parseEntityToResponseDto(updatedWallet);
    }

    @Override
    public WalletResponseDto store(WalletRequestDto requestDto) {
        //Wallet walletToSave = parser.parseRequestDtoToEntity(requestDto);
        Wallet walletToSave = new Wallet();
        walletToSave.setWalletNumber(requestDto.getWalletNumber());
        User user = new User();
        user.setId(requestDto.getUserId());
        walletToSave.setUser(user);
        walletToSave.setBalance(0.0);
        return parser.parseEntityToResponseDto(walletRepository.save(walletToSave));
    }

    @Override
    public void delete(int id) {
        Wallet toDelete = new Wallet();
        toDelete.setId(id);
        walletRepository.delete(toDelete);
    }

    @Override
    public List<TransactionResponseDto> getWalletTransactions(int id) {
        Wallet source = new Wallet();
        source.setId(id);
        Wallet destiny = new Wallet();
        destiny.setId(id);
        List<Transaction> transactions = transactionRepository.findAllByDestinyWalletOrSourceWalletOrderByDate(source, destiny);
        return new TransactionParserResponse().parseEntitiesToResponseDtos(transactions);
    }

    private static class WalletParser extends DtoEntityParserAbstractImpl<WalletRequestDto, WalletResponseDto, Wallet> {

        @Override
        public WalletResponseDto parseEntityToResponseDto(Wallet wallet) {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(wallet, WalletResponseDto.class);
        }

        @Override
        public Wallet parseRequestDtoToEntity(WalletRequestDto requestDto) {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(requestDto, Wallet.class);
        }

    }

    private static class TransactionParserResponse extends EntityToResponseDtoParserImpl<Transaction, TransactionResponseDto> {

        @Override
        public TransactionResponseDto parseEntityToResponseDto(Transaction transaction) {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(transaction, TransactionResponseDto.class);
        }

    }

}
