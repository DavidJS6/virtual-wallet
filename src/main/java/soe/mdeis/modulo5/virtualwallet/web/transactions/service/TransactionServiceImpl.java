package soe.mdeis.modulo5.virtualwallet.web.transactions.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soe.mdeis.modulo5.virtualwallet.database.models.Transaction;
import soe.mdeis.modulo5.virtualwallet.database.models.Wallet;
import soe.mdeis.modulo5.virtualwallet.database.repositories.TransactionRepository;
import soe.mdeis.modulo5.virtualwallet.database.repositories.WalletRepository;
import soe.mdeis.modulo5.virtualwallet.web.parsing.DtoEntityParser;
import soe.mdeis.modulo5.virtualwallet.web.parsing.DtoEntityParserAbstractImpl;
import soe.mdeis.modulo5.virtualwallet.web.transactions.TransactionException;
import soe.mdeis.modulo5.virtualwallet.web.transactions.TransactionRequestDto;
import soe.mdeis.modulo5.virtualwallet.web.transactions.TransactionResponseDto;
import soe.mdeis.modulo5.virtualwallet.web.wallets.WalletException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service("TransactionService")
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    private DtoEntityParser<TransactionRequestDto, TransactionResponseDto, Transaction> parser = new TransactionParser();

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, WalletRepository walletRepository) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public List<TransactionResponseDto> findAll() {
        List<Transaction> transactions = transactionRepository.findAll();
        return parser.parseEntitiesToResponseDtos(transactions);
    }

    @Override
    public TransactionResponseDto findById(int id) throws TransactionException {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new TransactionException());
        return parser.parseEntityToResponseDto(transaction);
    }

    @Override
    public TransactionResponseDto store(TransactionRequestDto requestDto) throws WalletException {
        //Transaction transactionToSave = parser.parseRequestDtoToEntity(requestDto);
        Transaction transactionToSave = new Transaction();
        transactionToSave.setResponsiblePersonName(requestDto.getResponsiblePersonName());
        transactionToSave.setTransactionAmount(requestDto.getTransactionAmount());
        transactionToSave.setTransactionType(requestDto.getTransactionType());
        transactionToSave.setDate(new Timestamp(new Date().getTime()));

        Wallet destinyWallet = walletRepository.findById(requestDto.getDestinyWalletId()).orElseThrow(() -> new WalletException("Wallet with the specified id not found"));

        if (requestDto.getSourceWalletId() != null) {
            if (!requestDto.getSourceWalletId().equals(requestDto.getDestinyWalletId())) {

                Wallet sourceWallet = walletRepository.findById(requestDto.getSourceWalletId()).orElseThrow(() -> new WalletException("Wallet with the specified id not found"));
                if (sourceWallet.getBalance() > requestDto.getTransactionAmount()) {
                    sourceWallet.setBalance(sourceWallet.getBalance() - requestDto.getTransactionAmount());
                    transactionToSave.setSourceWallet(walletRepository.save(sourceWallet));
                } else {
                    throw new WalletException("Balance from source wallet not enough!");
                }
            } else {
                throw new WalletException("Source wallet id and destiny wallet id cannot be the same");
            }
        }

        destinyWallet.setBalance(destinyWallet.getBalance() + requestDto.getTransactionAmount());
        transactionToSave.setDestinyWallet(walletRepository.save(destinyWallet));

        Transaction savedTransaction = transactionRepository.save(transactionToSave);
        return parser.parseEntityToResponseDto(savedTransaction);
    }

    private static class TransactionParser extends DtoEntityParserAbstractImpl<TransactionRequestDto, TransactionResponseDto, Transaction> {

        @Override
        public TransactionResponseDto parseEntityToResponseDto(Transaction transaction) {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(transaction, TransactionResponseDto.class);
        }

        @Override
        public Transaction parseRequestDtoToEntity(TransactionRequestDto requestDto) {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(requestDto, Transaction.class);
        }

    }

}
