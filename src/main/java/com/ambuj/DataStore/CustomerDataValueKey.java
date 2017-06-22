package com.ambuj.DataStore;

import java.util.Date;

/**
 * Created by Aj on 22-06-2017.
 */
public class CustomerDataValueKey {
    private Integer productKey;
    private Date tradeDate;

    public Integer getProductKey() {
        return productKey;
    }

    public void setProductKey(Integer productKey) {
        this.productKey = productKey;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerDataValueKey that = (CustomerDataValueKey) o;

        if (!productKey.equals(that.productKey)) return false;
        return tradeDate.equals(that.tradeDate);

    }

    @Override
    public int hashCode() {
        int result = productKey.hashCode();
        result = 31 * result + tradeDate.hashCode();
        return result;
    }
}
