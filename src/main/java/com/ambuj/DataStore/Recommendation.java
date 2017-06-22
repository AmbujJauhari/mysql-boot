package com.ambuj.DataStore;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Aj on 23-06-2017.
 */
public class Recommendation {
    private Integer recommendationId;
    private String recommendationDescription;
    private Double points;
    private Integer productId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(Integer recommendationId) {
        this.recommendationId = recommendationId;
    }

    public String getRecommendationDescription() {
        return recommendationDescription;
    }

    public void setRecommendationDescription(String recommendationDescription) {
        this.recommendationDescription = recommendationDescription;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }
}
