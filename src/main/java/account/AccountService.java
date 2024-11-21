package account;

import db.MyConnection;
import model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountService {

    private final MyConnection connection;
    private final CustomerService customerService;

    public AccountService() {
        connection = new MyConnection();
        customerService = new CustomerService();
    }

    // Mục đích = login
    public Account getAccountByUsernameAndPassword(String usernameInput, String passwordInput) {
        String query = "SELECT * FROM accounts where username = ? and password = ?";
        ResultSet rs = null;
        try (Connection conn = connection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, usernameInput);
            ps.setString(2, passwordInput);

            rs = ps.executeQuery();
            // Nếu mà rs.next() => trả về null, tức là nó không có data
            if (rs.next()) { // nếu mà trả ra 1 ghi duy nhất thì dùng if, còn không trả về list thì dùng while
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                return new Account(username, password, role); // Điều kiện 1 là có data
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeResultSet(rs, "getAccountByUsernameAndPassword");
        }
        return null; // điều kiện 2 ko có
    }

    public List<Account> getAllAccount() {
        List<Account> result = new ArrayList<>();
        String query = "SELECT * FROM accounts";
        try (Connection conn = connection.connect();
             Statement ps = conn.createStatement();
             ResultSet rs = ps.executeQuery(query);) {
            // Nếu mà rs.next() => trả về null, tức là nó không có data
            while (rs.next()) { // nếu mà trả ra 1 ghi duy nhất thì dùng if, còn không trả về list thì dùng while
                long id = rs.getLong("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                result.add(new Account(id, username, password, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void deleteAccount(String username) {
        String query = " DELETE FROM accounts  WHERE username = ?";
        try (Connection conn = connection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            int row = ps.executeUpdate();
            System.out.println("Account deleted: " + row);
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void updateAccount(String username, String password, String role) {
        String query = " UPDATE accounts SET password = ?, role = ? WHERE username = ?";

        try (Connection conn = connection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, password);
            ps.setString(2, role);
            ps.setString(3, username);
            ps.executeUpdate();
            System.out.println("Account với username: " + username + " đã được update: ");
        } catch (SQLException ex) {
            System.out.println("Lỗi update account: " + ex.getMessage());
        }
    }

    public void insertAccount(String username, String password) {
        String query = "INSERT INTO accounts (username, password) VALUES (?, ?)";
        try (Connection conn = connection.connect();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, username); // add cố định
            ps.setString(2, password);

            ps.executeUpdate(); // Trả về row affected
            System.out.println("Tạo account thành công");

            try (ResultSet rs = ps.getGeneratedKeys()) { // trả về key generated
                if (rs.next()) {
                    // Lấy id từ accounts vừa insert rs.getInt(1) -> insert thêm 1 records vào bảng customer nữa
                    System.out.println("Id account mới: " + rs.getInt(1));
                    // account id của table account
                    // mình lấy id này, mình insert vào bảng customer
                    customerService.insertNewCustomer(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
