package soe.mdeis.modulo5.virtualwallet.web.transactions.service;

import soe.mdeis.modulo5.virtualwallet.web.transactions.TransactionException;
import soe.mdeis.modulo5.virtualwallet.web.transactions.TransactionRequestDto;
import soe.mdeis.modulo5.virtualwallet.web.transactions.TransactionResponseDto;

import java.util.List;

public interface TransactionService {

    List<TransactionResponseDto> findAll();

    TransactionResponseDto findById(int id) throws TransactionException;

    TransactionResponseDto store(TransactionRequestDto requestDto);

}
