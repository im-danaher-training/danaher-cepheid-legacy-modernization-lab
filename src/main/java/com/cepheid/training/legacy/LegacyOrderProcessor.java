package com.cepheid.training.legacy;

import java.util.ArrayList;
import java.util.List;

// Intentionally written in an outdated style for the modernization lab.
// Do not "fix everything" at once -- follow the README hands-on tasks.
public class LegacyOrderProcessor {

    public String processOrder(String customerName, String region, List<String> productIds,
                                List<Double> unitPrices, List<Integer> quantities, String paymentType) {
        String result = "";
        double total = 0;
        int i = 0;
        while (i < productIds.size()) {
            double lineTotal = unitPrices.get(i) * quantities.get(i);
            if (region.equals("US-WEST")) {
                if (paymentType.equals("CREDIT")) {
                    lineTotal = lineTotal * 0.95;
                } else if (paymentType.equals("INVOICE")) {
                    lineTotal = lineTotal * 0.97;
                } else {
                    lineTotal = lineTotal;
                }
            } else if (region.equals("US-EAST")) {
                if (paymentType.equals("CREDIT")) {
                    lineTotal = lineTotal * 0.96;
                } else if (paymentType.equals("INVOICE")) {
                    lineTotal = lineTotal * 0.98;
                } else {
                    lineTotal = lineTotal;
                }
            } else {
                if (paymentType.equals("CREDIT")) {
                    lineTotal = lineTotal * 0.99;
                } else if (paymentType.equals("INVOICE")) {
                    lineTotal = lineTotal;
                } else {
                    lineTotal = lineTotal;
                }
            }
            total = total + lineTotal;
            i = i + 1;
        }

        try {
            if (total > 10000) {
                result = "ORDER_REQUIRES_APPROVAL";
            } else if (total > 0) {
                result = "ORDER_ACCEPTED";
            } else {
                result = "ORDER_REJECTED";
            }
        } catch (Exception e) {
            // swallow anything unexpected -- known legacy smell for the lab
            result = "ORDER_ERROR";
        }

        System.out.println("Processed order for " + customerName + " total=" + total + " result=" + result);
        return result;
    }

    public double calculateTotal(List<String> productIds, List<Double> unitPrices, List<Integer> quantities) {
        double total = 0;
        List<Double> lineTotals = new ArrayList<>();
        for (int i = 0; i < productIds.size(); i++) {
            double lt = unitPrices.get(i) * quantities.get(i);
            lineTotals.add(lt);
            total = total + lt;
        }
        return total;
    }
}
