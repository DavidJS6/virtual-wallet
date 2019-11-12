package soe.mdeis.modulo5.virtualwallet.web.wallets;

import soe.mdeis.modulo5.virtualwallet.database.models.User;

public class WalletResponseDto {

    private int id;
    private String walletNumber;
    private Double balance;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWalletNumber() {
        return walletNumber;
    }

    public void setWalletNumber(String walletNumber) {
        this.walletNumber = walletNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
