package soe.mdeis.modulo5.virtualwallet.database.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "wallets", schema = "virtual-wallet")
public class Wallet {
    private Integer id;
    private String walletNumber;
    private Double balance;
    @JsonIgnore
    private Collection<Transaction> transactionsById;
    @JsonIgnore
    private Collection<Transaction> transactionsByDestinyWalletId;
    private User user;

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
    @Column(name = "wallet_number")
    public String getWalletNumber() {
        return walletNumber;
    }

    public void setWalletNumber(String walletNumber) {
        this.walletNumber = walletNumber;
    }

    @Basic
    @Column(name = "balance")
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Objects.equals(id, wallet.id) &&
                Objects.equals(walletNumber, wallet.walletNumber) &&
                Objects.equals(balance, wallet.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, walletNumber, balance);
    }

    @OneToMany(mappedBy = "sourceWallet")
    public Collection<Transaction> getTransactionsById() {
        return transactionsById;
    }

    public void setTransactionsById(Collection<Transaction> transactionsById) {
        this.transactionsById = transactionsById;
    }

    @OneToMany(mappedBy = "destinyWallet")
    public Collection<Transaction> getTransactionsByDestinyWalletId() {
        return transactionsByDestinyWalletId;
    }

    public void setTransactionsByDestinyWalletId(Collection<Transaction> transactionsByDestinyWalletId) {
        this.transactionsByDestinyWalletId = transactionsByDestinyWalletId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User usersByUserId) {
        this.user = usersByUserId;
    }

    @PrePersist
    public void prePersist(){
        if(balance == null){
            balance = 0.0;
        }
    }

}
