package service;

import model.Product;
import java.time.LocalDate;
import java.util.List;

public class ProductService {

    private List<Product> products;
    private LocalDate today;

    public ProductService(List<Product> products) {
        this.products = products;
        this.today = LocalDate.now();
    }

    public void simulateDays(int days) {
        for (int i = 0; i < days; i++) {
            System.out.println("\n==== Tag " + i + " ====");
            displayProducts();
            updateProducts();
            today = today.plusDays(1);
        }
    }

    private void displayProducts() {
        for (Product product : products) {
            System.out.printf("Name: %s | Preis: %.2f€ | Qualität: %d | Entfernen: %s%n",
                    product.getName(),
                    product.getPrice(),
                    product.getQuality(),
                    product.shouldBeRemoved(today) ? "JA" : "NEIN");
        }
    }

    private void updateProducts() {
        for (Product product : products) {
            product.update();
        }
    }
}