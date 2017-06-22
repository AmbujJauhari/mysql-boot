package com.ambuj.analyze;

import com.ambuj.DataStore.CustomerDataStore;
import com.ambuj.DataStore.CustomerDataValueKey;
import com.ambuj.DataStore.CustomerRecommendationKey;
import com.ambuj.domain.Deviation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Component
public class DeviationCalculator {
    public static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");

    @Autowired
    private CustomerDataStore customerDataStore;

    private Map<Integer, List<Deviation>> TOP_20_BUCKET = new HashMap<>();
    private Map<Integer, List<Deviation>> TOP_10_BUCKET = new HashMap<>();
    private Map<Integer, List<Deviation>> TOP_5_BUCKET = new HashMap<>();

    public void calculateDeviationForAllProducts() throws ParseException {

        Map<String, Map<CustomerDataValueKey, Double>> customerData = customerDataStore.getCustomerData();

        for (Integer productId : customerDataStore.getProductMasterData().keySet()) {
            for (String customerId : customerData.keySet()) {
                Map<CustomerDataValueKey, Double> map = customerData.get(customerId);
                CustomerDataValueKey currentDateCustomer = new CustomerDataValueKey();
                currentDateCustomer.setProductKey(productId);
                currentDateCustomer.setTradeDate(formatter.parse(LocalDate.now().toString()));
                Double currentDateTradeVolume = map.get(currentDateCustomer);

                CustomerDataValueKey prevDateCustomer = new CustomerDataValueKey();
                prevDateCustomer.setProductKey(productId);
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -1);

                prevDateCustomer.setTradeDate(formatter.parse(cal.getTime().toString()));
                Double prevDateTradeVolume = map.get(prevDateCustomer);

                Deviation deviation = new Deviation();
                deviation.setCustomerId(customerId);
                deviation.setDeviation(currentDateTradeVolume - prevDateTradeVolume);
                deviation.setProdId(productId);

                //calculate percentage
                double percentage = (deviation.getDeviation() / prevDateTradeVolume) * 100;
                deviation.setPercentageChange(percentage);
                if (percentage < -5 && percentage >= -10) {
                    bucketAllocation(productId, customerId, cal, deviation, TOP_5_BUCKET, -0.25);
                } else if (percentage < -10 && percentage >= -20) {
                    bucketAllocation(productId, customerId, cal, deviation, TOP_10_BUCKET, -0.50);
                } else if (percentage < -20) {
                    bucketAllocation(productId, customerId, cal, deviation, TOP_20_BUCKET, -0.75);
                } else if (percentage > 5 && percentage <= 10) {
                    recommendationRanking(productId, customerId, cal, 0.25);
                } else if (percentage > 10 && percentage <= 20) {
                    recommendationRanking(productId, customerId, cal, 0.50);
                } else if (percentage > 20) {
                    recommendationRanking(productId, customerId, cal, 0.75);
                }
            }
        }
    }


    private void bucketAllocation(
            Integer productId, String customerId,
            Calendar cal, Deviation deviation,
            Map<Integer, List<Deviation>> bucket,
            Double points) throws ParseException {
        if (bucket.containsKey(productId)) {
            bucket.get(productId).add(deviation);
        } else {
            List<Deviation> deviations = new ArrayList<>();
            deviations.add(deviation);
            bucket.put(productId, deviations);
        }
        recommendationRanking(productId, customerId, cal, points);
    }

    private void recommendationRanking(Integer productId, String customerId, Calendar cal, Double points) throws ParseException {
        CustomerRecommendationKey customerRecommendationKey =
                new CustomerRecommendationKey(customerId, productId, formatter.parse(cal.getTime().toString()));
        Integer recommendationId = customerDataStore.getCustomerRecommendationMap().get(customerRecommendationKey);
        if (null != recommendationId) {
            customerDataStore.getRecommendationStoreMap().get(recommendationId).setPoints(
                    customerDataStore.getRecommendationStoreMap().get(recommendationId).getPoints() + points);
        }
    }


    public Map<Integer, List<Deviation>> getTOP_20_BUCKET() {
        return TOP_20_BUCKET;
    }

    public Map<Integer, List<Deviation>> getTOP_10_BUCKET() {
        return TOP_10_BUCKET;
    }

    public Map<Integer, List<Deviation>> getTOP_5_BUCKET() {
        return TOP_5_BUCKET;
    }
}
