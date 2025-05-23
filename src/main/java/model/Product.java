package model;

import java.time.LocalDate;

public abstract class Product {
    protected String name;
    protected int quality;
    protected LocalDate expiryDate;
    protected double basePrice;

    public Product(String name, int quality, LocalDate expiryDate, double basePrice) {
        this.name = name;
        this.quality = quality;
        this.expiryDate = expiryDate;
        this.basePrice = basePrice;
    }

    public abstract void update();

    public String getName() { return name; }
    public int getQuality() { return quality; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public double getPrice() { return basePrice + 0.10 * quality; }

    public boolean shouldBeRemoved(LocalDate today) {
        return expiryDate != null && today.isAfter(expiryDate);
    }
}