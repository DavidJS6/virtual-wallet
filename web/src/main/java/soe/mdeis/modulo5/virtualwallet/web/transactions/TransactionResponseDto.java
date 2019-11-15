package soe.mdeis.modulo5.virtualwallet.web.transactions;

import soe.mdeis.modulo5.virtualwallet.database.models.TransactionType;
import soe.mdeis.modulo5.virtualwallet.database.models.Wallet;

import java.sql.Timestamp;

public class TransactionResponseDto {

    private Integer id;
    private Timestamp date;
    private String responsiblePersonName;
    private Double transactionAmount;
    private TransactionType transactionType;
    private Wallet sourceWallet;
    private Wallet destinyWallet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getResponsiblePersonName() {
        return responsiblePersonName;
    }

    public void setResponsiblePersonName(String responsiblePersonName) {
        this.responsiblePersonName = responsiblePersonName;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Wallet getSourceWallet() {
        return sourceWallet;
    }

    public void setSourceWallet(Wallet sourceWallet) {
        this.sourceWallet = sourceWallet;
    }

    public Wallet getDestinyWallet() {
        return destinyWallet;
    }

    public void setDestinyWallet(Wallet destinyWallet) {
        this.destinyWallet = destinyWallet;
    }
}
