package soe.mdeis.modulo5.virtualwallet.web.users;

public class WalletResponseDto {
    private Integer id;
    private String walletNumber;
    private Double balance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
