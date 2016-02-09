package auth;

import database.Database;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by meranote on 2/9/2016 AD.
 */
public class Authorization {

    private Authorization() {}

    public static boolean login(String username, String password) {
        try {
            String sql = "SELECT * FROM user WHERE username = ?";
            PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet result = stmt.executeQuery();

            if(result.last() && BCrypt.checkpw(password, result.getString("password"))) {
                stmt.close();
                return true;
            } else {
                stmt.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
