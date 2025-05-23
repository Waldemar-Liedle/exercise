package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    void cheeseShouldLoseOneQualityPerDay() {
        Cheese cheese = new Cheese("Gouda", 35, LocalDate.now().plusDays(10), 4.20);
        cheese.update();
        assertEquals(34, cheese.getQuality());
    }

    @Test
    void cheeseShouldBeRemovedIfQualityBelow30() {
        Cheese cheese = new Cheese("Brie", 30, LocalDate.now().plusDays(10), 4.50);
        cheese.update();
        assertTrue(cheese.shouldBeRemoved(LocalDate.now()));
    }

    @Test
    void wineShouldIncreaseQualityAfterStichtag() {
        Wine wine = new Wine("Rotwein", 20, LocalDate.now().minusDays(20), 15.00);
        wine.update();
        assertTrue(wine.getQuality() > 20);
    }

    @Test
    void wineShouldNotBeRemoved() {
        Wine wine = new Wine("Weißwein", 40, LocalDate.now().minusDays(100), 10.00);
        assertFalse(wine.shouldBeRemoved(LocalDate.now()));
    }

    @Test
    void generalProductLosesQualityAfterExpiry() {
        GeneralProduct prod = new GeneralProduct("Brot", 25, LocalDate.now().minusDays(1), 2.00);
        prod.update();
        assertEquals(24, prod.getQuality());
    }

    @Test
    void generalProductNoChangeIfNotExpired() {
        GeneralProduct prod = new GeneralProduct("Brot", 25, LocalDate.now().plusDays(1), 2.00);
        prod.update();
        assertEquals(25, prod.getQuality());
    }
}
