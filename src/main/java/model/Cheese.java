package model;

import java.time.LocalDate;

public class Cheese extends Product {

    public Cheese(String name, int quality, LocalDate expiryDate, double basePrice) {
        super(name, quality, expiryDate, basePrice);
    }

    @Override
    public void update() {
        quality = Math.max(quality - 1, 0);
    }

    @Override
    public boolean shouldBeRemoved(LocalDate today) {
        return super.shouldBeRemoved(today) || quality < 30;
    }
}