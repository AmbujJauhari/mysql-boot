package com.ambuj.domain;

/**
 * Created by Aj on 22-06-2017.
 */
public class Deviation {
    private String customerId;
    private String assetClassName;
    private Integer prodId;
    private Double deviation;
    private Double percentageChange;

    public String getAssetClassName() {
        return assetClassName;
    }

    public void setAssetClassName(String assetClassName) {
        this.assetClassName = assetClassName;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Double getDeviation() {
        return deviation;
    }

    public void setDeviation(Double deviation) {
        this.deviation = deviation;
    }

    public Double getPercentageChange() {
        return percentageChange;
    }

    public void setPercentageChange(Double percentageChange) {
        this.percentageChange = percentageChange;
    }
}
