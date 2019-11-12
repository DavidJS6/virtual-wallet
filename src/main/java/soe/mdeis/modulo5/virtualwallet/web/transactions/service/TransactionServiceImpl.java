package soe.mdeis.modulo5.virtualwallet.web.transactions.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soe.mdeis.modulo5.virtualwallet.database.models.Transaction;
import soe.mdeis.modulo5.virtualwallet.database.models.Wallet;
import soe.mdeis.modulo5.virtualwallet.database.repositories.TransactionRepository;
import soe.mdeis.modulo5.virtualwallet.web.parsing.DtoEntityParser;
import soe.mdeis.modulo5.virtualwallet.web.parsing.DtoEntityParserAbstractImpl;
import soe.mdeis.modulo5.virtualwallet.web.transactions.TransactionException;
import soe.mdeis.modulo5.virtualwallet.web.transactions.TransactionRequestDto;
import soe.mdeis.modulo5.virtualwallet.web.transactions.TransactionResponseDto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service("TransactionService")
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private DtoEntityParser<TransactionRequestDto, TransactionResponseDto, Transaction> parser = new TransactionParser();

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
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
    public TransactionResponseDto store(TransactionRequestDto requestDto) {
        //Transaction transactionToSave = parser.parseRequestDtoToEntity(requestDto);
        Transaction transactionToSave = new Transaction();
        transactionToSave.setResponsiblePersonName(requestDto.getResponsiblePersonName());
        transactionToSave.setTransactionAmount(requestDto.getTransactionAmount());
        transactionToSave.setTransactionType(requestDto.getTransactionType());
        transactionToSave.setDate(new Timestamp(new Date().getTime()));
        Wallet destinyWallet = new Wallet();
        destinyWallet.setId(requestDto.getDestinyWalletId());
        transactionToSave.setDestinyWallet(destinyWallet);

        if(requestDto.getSourceWalletId() != null) {
            Wallet sourceWallet = new Wallet();
            sourceWallet.setId(requestDto.getSourceWalletId());
            transactionToSave.setSourceWallet(sourceWallet);
        }

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
