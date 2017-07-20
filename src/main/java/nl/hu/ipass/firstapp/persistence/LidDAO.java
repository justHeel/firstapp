package nl.hu.ipass.firstapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.firstapp.domain.Lid;



public class LidDAO extends BaseDAO {
	
	private List<Lid> selectLeden(String query) {
		List<Lid> results = new ArrayList<Lid>();

		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int lidnummer = dbResultSet.getInt("Lidnummer");
				String gebruikersnaam = dbResultSet.getString("Gebruikersnaam");
				String wachtwoord = dbResultSet.getString("Wachtwoord");
				String email = dbResultSet.getString("E-mail");
				String rol = dbResultSet.getString("Rol");

				Lid newLid = new Lid(lidnummer, gebruikersnaam, wachtwoord, email, rol);

				results.add(newLid);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}
	
	public void createLid(Lid lid){
		String query = "INSERT INTO \"GameRevs\".\"Lid\" (\"Gebruikersnaam\", \"E-mail\", \"Wachtwoord\") VALUES (?, ?, ?)";
		PreparedStatement statement = null;
		
		try (Connection con = super.getConnection()) {
			statement = con.prepareStatement(query);
			
			//preparedStatement.setInt(1,);
			//statement.setInt(1, lid.getLidnummer());
			statement.setString(1, lid.getGebruikersnaam());
			statement.setString(2, lid.getEmail());
			statement.setString(3, lid.getWachtwoord());
			
			statement.executeUpdate();
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public List<Lid> findall(){
		return selectLeden("SELECT * FROM \"GameRevs\".\"Lid\"");
	}

	/*
	 * Find specific Lid 
	 */
	public Lid findById (int lidId) {
		return selectLeden("SELECT \"Lidnummer\", \"Gebruikersnaam\", \"Wachtwoord\", \"E-mail\", \"Rol\" FROM \"GameRevs\".\"Lid\" WHERE  \"Lidnummer\" = " + lidId).get(0);
	}
	
	public Lid findByGebruikersnaam (String gebruikersNaam){
		return selectLeden("SELECT \"Lidnummer\", \"Gebruikersnaam\", \"Wachtwoord\", \"E-mail\", \"Rol\"  FROM \"GameRevs\".\"Lid\" WHERE \"Gebruikersnaam\" = " + gebruikersNaam).get(0); 
	}
	
	/*
	 * Update the E-mail of a Lid
	 */
	public void UpdateLid (String mail, int lidId){
		String query = "UPDATE \"GameRevs\".\"Lid\" SET \"E-mail\" = ? WHERE \"Lidnummer\" = ? ";
		PreparedStatement statement = null;
		
		try (Connection con = super.getConnection()){
			statement =  con.prepareStatement(query);
			
			statement.setString(1, mail);
			statement.setInt(2, lidId);
			
			statement.executeUpdate();
			
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

}
