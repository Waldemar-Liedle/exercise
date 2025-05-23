package factory;

import model.*;

import java.time.LocalDate;

public class ProductFactory {
    public static Product createProduct(String type, String name, int quality, String expiryStr, double basePrice, String stichtagStr) {
        return switch (type.toLowerCase()) {
            case "cheese" -> new Cheese(name, quality, LocalDate.parse(expiryStr), basePrice);
            case "wine" -> new Wine(name, quality, LocalDate.parse(stichtagStr), basePrice);
            case "general" -> new GeneralProduct(name, quality, LocalDate.parse(expiryStr), basePrice);
            case "flower" -> new Flower(name, quality, LocalDate.parse(expiryStr), basePrice);
            default -> throw new IllegalArgumentException("Unbekannter Produkttyp: " + type);
        };
    }
}
