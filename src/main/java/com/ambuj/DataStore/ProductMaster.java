package com.ambuj.DataStore;

/**
 * Created by Aj on 23-06-2017.
 */
public class ProductMaster {
    private Integer id;
    private String assetClassName;
    private String productName;

    public ProductMaster(Integer id, String assetClassName, String productName) {
        this.id = id;
        this.assetClassName = assetClassName;
        this.productName = productName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssetClassName() {
        return assetClassName;
    }

    public void setAssetClassName(String assetClassName) {
        this.assetClassName = assetClassName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


}
