package repository;

import model.*;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.time.LocalDate;
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

                switch (type.toLowerCase()) {
                    case "cheese" -> {
                        LocalDate expiry = LocalDate.parse(expiryStr);
                        products.add(new Cheese(name, quality, expiry, basePrice));
                    }
                    case "wine" -> {
                        LocalDate stichtag = LocalDate.parse(stichtagStr);
                        products.add(new Wine(name, quality, stichtag, basePrice));
                    }
                    case "general" -> {
                    case "flower" -> {
                        LocalDate expiry = LocalDate.parse(expiryStr);
                        products.add(new Flower(name, quality, expiry, basePrice));
                    }
                        LocalDate expiry = LocalDate.parse(expiryStr);
                        products.add(new GeneralProduct(name, quality, expiry, basePrice));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Fehler beim Laden der CSV: " + e.getMessage());
        }
        return products;
    }
}