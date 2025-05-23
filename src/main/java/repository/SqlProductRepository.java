package repository;

import model.*;
import factory.ProductFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlProductRepository implements ProductRepository {

    private static final String JDBC_URL = "jdbc:h2:mem:superdupermarkt;DB_CLOSE_DELAY=-1";

    public SqlProductRepository() {
        initializeDatabase();
    }

    private void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE products (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "type VARCHAR(20)," +
                    "name VARCHAR(100)," +
                    "quality INT," +
                    "expiry_date DATE," +
                    "base_price DOUBLE," +
                    "stichtag DATE)");

            stmt.executeUpdate("INSERT INTO products (type, name, quality, expiry_date, base_price, stichtag) VALUES " +
                    "('cheese', 'Gouda', 35, '2025-07-15', 4.20, NULL)," +
                    "('wine', 'Chateau SuperDuper', 20, NULL, 15.00, '2025-06-01')," +
                    "('general', 'Apfel', 50, '2025-06-10', 1.50, NULL)," +
                    "('flower', 'Rose', 18, '2025-06-15', 2.50, NULL)");

        } catch (SQLException e) {
            System.err.println("Fehler bei der Datenbankinitialisierung: " + e.getMessage());
        }
    }

    @Override
    public List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM products")) {

            while (rs.next()) {
                String type = rs.getString("type");
                String name = rs.getString("name");
                int quality = rs.getInt("quality");
                String expiryStr = rs.getString("expiry_date");
                double basePrice = rs.getDouble("base_price");
                String stichtagStr = rs.getString("stichtag");

                products.add(ProductFactory.createProduct(type, name, quality, expiryStr, basePrice, stichtagStr));
            }

        } catch (SQLException e) {
            System.err.println("Fehler beim Laden der Produkte aus SQL: " + e.getMessage());
        }
        return products;
    }
}
