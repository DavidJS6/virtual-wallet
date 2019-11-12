package soe.mdeis.modulo5.virtualwallet.web.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import soe.mdeis.modulo5.virtualwallet.database.models.TransactionType;

public class TransactionRequestDto {

    @JsonProperty("responsible_person_name")
    private String responsiblePersonName;
    @JsonProperty("transaction_amount")
    private Double transactionAmount;
    @JsonProperty("transaction_type")
    private TransactionType transactionType;
    @JsonProperty("source_wallet_id")
    private int sourceWalletId;
    @JsonProperty("destiny_wallet_id")
    private int destinyWalletId;

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

    public Integer getSourceWalletId() {
        return sourceWalletId;
    }

    public void setSourceWalletId(Integer sourceWalletId) {
        this.sourceWalletId = sourceWalletId;
    }

    public Integer getDestinyWalletId() {
        return destinyWalletId;
    }

    public void setDestinyWalletId(Integer destinyWalletId) {
        this.destinyWalletId = destinyWalletId;
    }
}
