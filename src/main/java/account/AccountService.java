package account;

import db.MyConnection;
import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountService {

    private final MyConnection connection;

    public AccountService() {
        connection = new MyConnection();
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
}
