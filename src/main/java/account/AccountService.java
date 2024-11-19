package account;

import db.MyConnection;
import model.Account;

import java.sql.*;

public class AccountService {

    private final MyConnection connection;
    private final CustomerService customerService;

    public AccountService() {
        connection = new MyConnection();
        customerService = new CustomerService();
    }

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
