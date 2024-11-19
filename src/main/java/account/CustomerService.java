package account;

import db.MyConnection;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerService {

    private final MyConnection myConnection;

    public CustomerService() {
        myConnection = new MyConnection();
    }


    public Customer findByCustomerNumber(int customerNumber) {
        String query = "SELECT * FROM customers WHERE customerNumber = ?";
        ResultSet resultSet = null;
        try (Connection connection = myConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, customerNumber);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerNumber(resultSet.getInt("customerNumber"));
                customer.setCustomerName(resultSet.getString("customerName"));
                customer.setContactLastName(resultSet.getString("contactLastName"));
                customer.setContactFirstName(resultSet.getString("contactFirstName"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setAddressLine1(resultSet.getString("addressLine1"));
                customer.setAddressLine2(resultSet.getString("addressLine2"));
                customer.setCity(resultSet.getString("city"));
                customer.setState(resultSet.getString("state"));
                customer.setPostalCode(resultSet.getString("postalCode"));
                customer.setCountry(resultSet.getString("country"));
                customer.setSalesRepEmployeeNumber(resultSet.getInt("salesRepEmployeeNumber"));
                customer.setCreditLimit(resultSet.getDouble("creditLimit"));
                return customer;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error: " + ex.getMessage());
        } finally {
            myConnection.closeResultSet(resultSet, "findByCustomerNumber");
        }
        return null;
    }

    public void insertNewCustomer(int customerNumber) {
        String query = "INSERT INTO customers (customerNumber) VALUES (?)";
        try (Connection connection = myConnection.connect();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, customerNumber);

            ps.executeUpdate(); // Trả về row affected

            System.out.println("Tạo mới customer thành công");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
