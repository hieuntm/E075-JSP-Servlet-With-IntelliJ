package account;

import db.MyConnection;
import model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final MyConnection myConnection;

    public ProductService() {
        myConnection = new MyConnection();
    }

    public List<Product> getAllProducts() {
        List<Product> result = new ArrayList<>();
        String query = "SELECT * FROM products LIMIT 10";
        try (Connection connection = myConnection.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String prdCode = resultSet.getString("productCode");
                String prdName = resultSet.getString("productName");
                String prdLine = resultSet.getString("productLine");
                String prdScale = resultSet.getString("productScale");
                String prdVendor = resultSet.getString("productVendor");
                String prdDescription = resultSet.getString("productDescription");
                int prdQty = Integer.parseInt(resultSet.getString("quantityInStock"));
                double prdPrice = Double.parseDouble(resultSet.getString("buyPrice"));
                double prdMSRP = Double.parseDouble(resultSet.getString("MSRP"));
                // Tạo new Product
                Product product = new Product(prdCode, prdName, prdLine, prdScale, prdVendor, prdDescription, prdQty, prdPrice, prdMSRP);
                result.add(product);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return result;
    }
}
