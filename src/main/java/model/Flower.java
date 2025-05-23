package model;

import java.time.LocalDate;

public class Flower extends Product {

    public Flower(String name, int quality, LocalDate expiryDate, double basePrice) {
        super(name, quality, expiryDate, basePrice);
    }

    @Override
    public void update() {
        quality = Math.max(quality - 2, 0);
    }

    @Override
    public boolean shouldBeRemoved(LocalDate today) {
        return super.shouldBeRemoved(today) || quality < 10;
    }

    @Override
    public double getPrice() {
        return basePrice + 0.05 * quality;
    }
}
