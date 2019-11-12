package soe.mdeis.modulo5.virtualwallet.web.wallets;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WalletRequestDto {

    @JsonProperty("wallet_number")
    private String walletNumber;
    @JsonProperty("user_id")
    private Integer userId;

    public String getWalletNumber() {
        return walletNumber;
    }

    public void setWalletNumber(String walletNumber) {
        this.walletNumber = walletNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
