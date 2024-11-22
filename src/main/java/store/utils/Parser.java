package store.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import store.domain.Product;
import store.domain.Promotion;

public class Parser {

    public static Map<String, Promotion> parsePromotions(String fileName) {
        Map<String, Promotion> promotions = new HashMap<>();

        try (InputStream inputStream = Parser.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int condition = Integer.parseInt(parts[1]);
                int bonus = Integer.parseInt(parts[2]);
                promotions.put(name, new Promotion(name, condition, bonus));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return promotions;
    }

    public static List<Product> parseProducts(String fileName, Map<String, Promotion> promotions) {
        List<Product> products = new ArrayList<>();

        try (InputStream inputStream = Parser.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                double price = Double.parseDouble(parts[1]);
                int quantity = Integer.parseInt(parts[2]);
                String promotionName = parts[3].equals("null") ? null : parts[3];

                Promotion promotion = promotionName != null ? promotions.get(promotionName) : null;
                products.add(new Product(name, price, quantity, promotion));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }
}
