package nl.hu.ipass.firstapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import nl.hu.ipass.firstapp.domain.Lid;

public class UserDAO extends BaseDAO {

	public String findRoleForUsernameAndPassword(String username, String password) {
		String role = null;
		String query = "SELECT \"Rol\" FROM \"GameRevs\".\"Lid\" WHERE \"Gebruikersnaam\" = ? AND \"Wachtwoord\" = ?";
		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				role = rs.getString("Rol");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return role;
	}
}