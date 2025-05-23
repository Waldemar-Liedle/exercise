package app;

import model.Cheese;
import model.GeneralProduct;
import model.Product;
import model.Wine;
import repository.CsvProductRepository;
import repository.ProductRepository;
import repository.SqlProductRepository;
import service.ProductService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SuperDuperMarktApp {

    public static void main(String[] args) {
        
        // inline
        /*
        List<Product> products = createInitialProducts();
        ProductService service = new ProductService(products);
        service.simulateDays(20);
        */

        // csv
        /*
        ProductRepository repo = new CsvProductRepository("src/main/resources/produkte.csv");
        List<Product> products = repo.loadProducts();
        ProductService service = new ProductService(products);
        service.simulateDays(20);
        */
        
        ProductRepository repo = new SqlProductRepository();
        List<Product> products = repo.loadProducts();
    }

    private static List<Product> createInitialProducts() {
        List<Product> products = new ArrayList<>();
        LocalDate today = LocalDate.now();

        products.add(new GeneralProduct("Apfel", 50, today.plusDays(10), 1.50));
        products.add(new Cheese("Gouda", 35, today.plusDays(60), 4.20));
        products.add(new Wine("Chateau SuperDuper", 20, today.plusDays(5), 15.00));
        products.add(new GeneralProduct("Brot", 25, today.plusDays(5), 2.00));
        products.add(new Cheese("Camembert", 32, today.plusDays(55), 5.50));

        return products;
    }
}