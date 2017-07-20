package nl.hu.ipass.firstapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nl.hu.ipass.firstapp.domain.Spel;

public class SpelDAO extends BaseDAO{
	private List<Spel> selectSpellen (String query){
		List<Spel> results = new ArrayList<Spel>();
		
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()){
				int spelNummer = dbResultSet.getInt("Spelnummer");
				String naam = dbResultSet.getString("Naam");
				String beschrijving = dbResultSet.getString("Beschrijving");
				String spelGenre = dbResultSet.getString("SpelGenre");
				String trailer = dbResultSet.getString("Trailer");
				String publisher = dbResultSet.getString("Publisher");
//				Date releaseDatum = dbResultSet.getDate("Releasedatum");
				
				Spel newSpel = new Spel(spelNummer, naam, beschrijving, spelGenre, trailer, publisher);
				
				results.add(newSpel);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}
	
	public void createSpel(Spel spel){
		String query = "INSERT INTO \"GameRevs\".\"Spel\" (\"Spelnummer\",\"Naam\", \"Beschrijving\", \"SpelGenre\", \"Trailer\", \"Publisher\") VALUES (?,?,?,?,?,?)";
		PreparedStatement statement = null;
		
		try (Connection con = super.getConnection()){
			statement = con.prepareStatement(query);
			
			statement.setInt(1, spel.getSpelNummer());
			statement.setString(2, spel.getNaam());
			statement.setString(3, spel.getBeschrijving());
			statement.setString(4, spel.getSpelGenre());
			statement.setString(5, spel.getTrailer());
			statement.setString(6, spel.getPublisher());
			
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public List<Spel> findall(){
		return selectSpellen("SELECT * FROM \"GameRevs\".\"Spel\"");
		}
		
	public Spel findById (int spelNummer){
		return selectSpellen("SELECT \"Spelnummer\", \"Naam\", \"Beschrijving\", \"SpelGenre\", \"Trailer\", \"Publisher\", \"Releasedatum\" FROM \"GameRevs\".\"Spel\" WHERE \"Spelnummer\" = " + spelNummer).get(0);
				
	}
}

