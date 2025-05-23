package model;

import java.time.LocalDate;

public class Wine extends Product {

    private LocalDate stichtag;

    public Wine(String name, int quality, LocalDate stichtag, double basePrice) {
        super(name, quality, null, basePrice);
        this.stichtag = stichtag;
    }

    @Override
    public void update() {
        LocalDate today = LocalDate.now();
        if (!today.isBefore(stichtag)) {
            long daysSinceStichtag = java.time.temporal.ChronoUnit.DAYS.between(stichtag, today);
            int bonusQuality = (int) (daysSinceStichtag / 10);
            quality = Math.min(50, quality + bonusQuality);
        }
    }

    @Override
    public double getPrice() {
        return basePrice;
    }

    @Override
    public boolean shouldBeRemoved(LocalDate today) {
        return false;
    }
}