package com.asaitec.exam.shop.service;

import com.asaitec.exam.shop.model.Offer;
import com.asaitec.exam.shop.model.ProductBill;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class ShopService {

    public Map<String, Double> getProduct(List<String> products) {
        Map<String, Double> data = new HashMap<>();
        String[] productPrice;
        //System.out.println("PRODUCTS: ");
        for (String product : products) {
            productPrice = product.split(",");
            //System.out.println(productPrice[0].trim() + " - " + productPrice[1].trim());
            data.put(productPrice[0].trim(), Double.parseDouble(productPrice[1].trim()));
        }
        return data;
    }

    public Map<String, Integer> getOrder(List<String> ordered) {
        Map<String, Integer> data = new HashMap<>();
        String[] productAmount;
        //System.out.println("ORDER: ");
        for (String s : ordered) {
            productAmount = s.split(",");
            //System.out.println(productAmount[0].trim() + " - " + productAmount[1].trim());
            data.put(productAmount[0].trim(), Integer.parseInt(productAmount[1].trim()));
        }
        //System.out.println();
        return data;
    }

    public List<ProductBill> getBillList(Map<String, Double> products,
                                         Map<String, Integer> order) {
        List<ProductBill> bill = new ArrayList<>();
        Set<String> productsNamesBought = order.keySet();
        double price;
        System.out.println("-----------------");
        System.out.println("SUPERMARKET BILL");
        System.out.println("-----------------");
        System.out.println("Product - Items - Price - Total");
        for (String s : productsNamesBought) {
            price = products.get(s)*order.get(s);
            System.out.println(s + " - " + order.get(s) + " - " + products.get(s) + " - " + price);
            bill.add(new ProductBill(s, order.get(s), products.get(s), price));
        }
        return bill;
    }

    public double getTotal(List<ProductBill> billList) {
        double total = 0.0;
        for (ProductBill p: billList) {
            total += p.getTotal();
        }
        System.out.println("TOTAL: " + total);
        return total;
    }

    public List<Offer> getOffers(){
        List<Offer> offers = new ArrayList<>();
        offers.add(new Offer("BuyXPayY", "Apple", "Apple", 3, 2));
        offers.add(new Offer("OneFree", "Pear", "Orange", 2, 1));
        offers.add(new Offer("Discount", "Pear", "Total", 4, 1));
        return offers;
    }

    public List<String> makeDiscounts(List<Offer> offers,
                                      Map<String, Double> products,
                                      Map<String, Integer> ordered,
                                      double total) {
        double totalDiscount = total;
        int amountOrdered;
        int productsFree;
        int timesApplied;
        double discount;
        double moneySpent;
        String message;
        List<String> discounts = new ArrayList<>();
        for (Offer offer : offers) {
            //if costumer bought one product with active offer
            if(ordered.get(offer.getProductOne()) != null) {
                switch (offer.getType()) {
                    case "BuyXPayY":
                        amountOrdered = ordered.get(offer.getProductOne());
                        if (amountOrdered >= offer.getAmountOne()) {
                            productsFree = offer.getAmountOne() - offer.getAmountTwo();
                            timesApplied = amountOrdered / offer.getAmountOne();
                            discount = products.get(offer.getProductOne()) * (productsFree * timesApplied);
                            message = "Discount applied! " + offer.getAmountOne() + "x" + offer.getAmountTwo() +
                                    " " + offer.getProductOne() + " ..... -" + discount;
                            System.out.println(message);
                            discounts.add(message);
                            totalDiscount -= discount;
                        }
                        break;
                    case "OneFree":
                        if (ordered.get(offer.getProductTwo()) != null) {
                            amountOrdered = ordered.get(offer.getProductOne());
                            if (amountOrdered >= offer.getAmountOne()) {
                                discount = products.get(offer.getProductTwo());
                                message = "Discount applied! " + offer.getType() + " " + offer.getProductTwo()
                                        + " ..... -" + discount;
                                System.out.println(message);
                                discounts.add(message);
                                totalDiscount -= discount;
                            }
                        }
                        break;
                    case "Discount":
                        moneySpent = ordered.get(offer.getProductOne())*products.get(offer.getProductOne());
                        if (moneySpent >= offer.getAmountOne()) {
                            amountOrdered = ordered.get(offer.getProductOne());
                            moneySpent = amountOrdered * products.get(offer.getProductOne());
                            timesApplied = (int) moneySpent / offer.getAmountOne();
                            discount = timesApplied * offer.getAmountTwo();
                            message = "Discount applied! " + offer.getType() + " " + offer.getAmountTwo() +
                                    "€ every " + offer.getAmountOne() + "€ " + offer.getProductOne() +
                                    " ..... -" + discount;
                            System.out.println(message);
                            discounts.add(message);
                            totalDiscount -= discount;
                            break;
                        }
                }
            }

        }
        DecimalFormat resultFormat = new DecimalFormat("#.00");
        message = "TO PAY: " + resultFormat.format(totalDiscount);
        System.out.println(message);
        discounts.add(message);
        return discounts;
    }
}
