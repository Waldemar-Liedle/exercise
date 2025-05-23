package model;

import java.time.LocalDate;

public class GeneralProduct extends Product {

    public GeneralProduct(String name, int quality, LocalDate expiryDate, double basePrice) {
        super(name, quality, expiryDate, basePrice);
    }

    @Override
    public void update() {
        if (expiryDate != null && expiryDate.isBefore(LocalDate.now())) {
            quality = Math.max(quality - 1, 0);
        }
    }
}