package repository;

import model.*;
import factory.ProductFactory;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvProductRepository implements ProductRepository {

    private final String filePath;

    public CsvProductRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext(); // skip header
            while ((line = reader.readNext()) != null) {
                String type = line[0];
                String name = line[1];
                int quality = Integer.parseInt(line[2]);
                String expiryStr = line[3];
                double basePrice = Double.parseDouble(line[4]);
                String stichtagStr = line.length > 5 ? line[5] : null;

                products.add(ProductFactory.createProduct(type, name, quality, expiryStr, basePrice, stichtagStr));
            }
        } catch (Exception e) {
            System.err.println("Fehler beim Laden der CSV: " + e.getMessage());
        }
        return products;
    }
}
