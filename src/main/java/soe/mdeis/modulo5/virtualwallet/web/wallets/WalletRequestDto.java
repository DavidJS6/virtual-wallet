package soe.mdeis.modulo5.virtualwallet.web.wallets;

public class WalletRequestDto {

    private int id;
    private String wallet_number;
    private Double balance;
    private Integer user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWallet_number() {
        return wallet_number;
    }

    public void setWallet_number(String wallet_number) {
        this.wallet_number = wallet_number;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
