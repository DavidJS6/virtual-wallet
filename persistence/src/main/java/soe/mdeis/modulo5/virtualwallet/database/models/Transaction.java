package soe.mdeis.modulo5.virtualwallet.database.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "transactions", schema = "virtual-wallet")
public class Transaction {
    private Integer id;
    private Timestamp date;
    private String responsiblePersonName;
    private Double transactionAmount;
    private TransactionType transactionType;
    private Wallet sourceWallet;
    private Wallet destinyWallet;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "responsible_person_name")
    public String getResponsiblePersonName() {
        return responsiblePersonName;
    }

    public void setResponsiblePersonName(String responsiblePersonName) {
        this.responsiblePersonName = responsiblePersonName;
    }

    @Basic
    @Column(name = "transaction_amount")
    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    @Basic
    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(responsiblePersonName, that.responsiblePersonName) &&
                Objects.equals(transactionAmount, that.transactionAmount) &&
                Objects.equals(transactionType, that.transactionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, responsiblePersonName, transactionAmount, transactionType);
    }

    @ManyToOne
    @JoinColumn(name = "source_wallet_id", referencedColumnName = "id")
    public Wallet getSourceWallet() {
        return sourceWallet;
    }

    public void setSourceWallet(Wallet sourceWallet) {
        this.sourceWallet = sourceWallet;
    }

    @ManyToOne
    @JoinColumn(name = "destiny_wallet_id", referencedColumnName = "id", nullable = false)
    public Wallet getDestinyWallet() {
        return destinyWallet;
    }

    public void setDestinyWallet(Wallet destinyWallet) {
        this.destinyWallet = destinyWallet;
    }
}
