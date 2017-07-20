package nl.hu.ipass.firstapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.firstapp.domain.Lid;
import nl.hu.ipass.firstapp.domain.Recensie;
import nl.hu.ipass.firstapp.domain.Spel;

public class SearchDAO extends BaseDAO{

	private SpelDAO spelDAO = new SpelDAO();
	private LidDAO lidDAO = new LidDAO();
	
	// Querry voor het zoeken voor een spel
	public List<Spel> search(String input) {
		List<Spel> results =  new ArrayList<Spel>();
		String query = "SELECT * FROM \"GameRevs\".\"Spel\"  WHERE \"GameRevs\".\"Spel\".\"Naam\" LIKE %"+ input + "%";
		
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while(dbResultSet.next()){
				int spelNummer = dbResultSet.getInt("Spelnummer");
				String naam = dbResultSet.getString("Naam");
				String beschrijving = dbResultSet.getString("Beschrijving");
				String spelGenre = dbResultSet.getString("SpelGenre");
				String trailer = dbResultSet.getString("Trailer");
				String publisher = dbResultSet.getString("Publisher");
				
				Spel newSpel = new Spel(spelNummer, naam, beschrijving, spelGenre, trailer, publisher);
				
				results.add(newSpel);
				
				
				
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return results;
	}
}
