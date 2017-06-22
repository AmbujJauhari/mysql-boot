package com.ambuj.services;

import com.ambuj.DataStore.CustomerDataStore;
import com.ambuj.DataStore.Recommendation;
import com.ambuj.domain.RecommendationMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Aj on 23-06-2017.
 */
@Service
public class RecommendationService {

    @Autowired
    private CustomerDataStore customerDataStore;

    public Recommendation getRecommendation(int productId) {
        Collection<Recommendation> allRecommendations = customerDataStore.getRecommendationStoreMap().values();
        //find recommendations for productid;
        List<Recommendation> productRecommendations = new ArrayList<>();
        for (Recommendation recommendation : allRecommendations) {
            if (productId == recommendation.getProductId()) {
                productRecommendations.add(recommendation);
            }
        }

        Collections.sort(productRecommendations, new Comparator<Recommendation>() {
            @Override
            public int compare(Recommendation o1, Recommendation o2) {
                return o1.getPoints().compareTo(o2.getPoints());
            }
        });

        Collections.reverse(productRecommendations);

        return productRecommendations.get(0);
    }
}
