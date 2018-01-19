package com.mmnttech.mb.merchant.server.common.dto;

/**
 * @author Stelo
 * @createAt 2018/1/19
 **/
public class MerchantAuthDto {

    private boolean isApprove;

    private String merchantId;

    private String reason;

    public boolean isApprove() {
        return isApprove;
    }

    public void setApprove(boolean approve) {
        isApprove = approve;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
