package nl.hu.ipass.firstapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.firstapp.domain.Lid;
import nl.hu.ipass.firstapp.domain.Recensie;
import nl.hu.ipass.firstapp.domain.Spel;



public class RecensieDAO extends BaseDAO {
	private LidDAO lidDAO = new LidDAO();
	private SpelDAO spelDAO = new SpelDAO();
	
	private List<Recensie> selectRecensies(String query){
		List<Recensie> results = new ArrayList<Recensie>();
		
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()){
				
				int recensieNummer = dbResultSet.getInt("Recensienummer");
				int recensieCijfer = dbResultSet.getInt("RecensieCijfer");
				String comment = dbResultSet.getString("Comment");
				
				Spel spel = spelDAO.findById(dbResultSet.getInt("Recensie_spelNummer"));
				Lid lid = lidDAO.findById(dbResultSet.getInt("Recensie_lidNummer"));
				
				Recensie newRecensie = new Recensie(recensieNummer, recensieCijfer, comment, spel, lid);
				
				results.add(newRecensie);
						
			}
		} catch(SQLException sqle){
			sqle.printStackTrace();
			
		}
		
		return results;
	}
	
	public List<Recensie> findRecensiebySpel(int spelId){
		return selectRecensies("SELECT \"Recensienummer\", \"RecensieCijfer\", \"Comment\", \"Recensie_spelNummer\", \"Recensie_lidNummer\" FROM \"GameRevs\".\"Recensie\" WHERE \"Recensie_spelNummer\" = " + spelId );
	}
	
	public List<Recensie> findRecensiebyLid(int lidId){
		return selectRecensies("SELECT \"Recensienummer\", \"RecensieCijfer\", \"Comment\", \"Recensie_spelNummer\", \"Recensie_lidNummer\" FROM \"GameRevs\".\"Recensie\" WHERE \"Recensie_lidNummer\" = " + lidId );
	}
	
	public void createRecensie(Recensie recensie){
		String query = "INSERT INTO \"GameRevs\".\"Recensie\" (\"RecensieCijfer\", \"Comment\", \"Recensie_spelNummer\", \"Recensie_lidNummer\") VALUES(?,?,?,?)";
		PreparedStatement statement = null;
		
		try (Connection con = super.getConnection()) {
			statement = con.prepareStatement(query);
			
			statement.setInt(1, recensie.getRecensieCijfer());
			statement.setString(2, recensie.getComment());
			statement.setInt(3, recensie.getSpel().getSpelNummer());
			statement.setInt(4, recensie.getLid().getLidnummer());
			
			statement.executeUpdate();
			statement.close();
			
		} catch (SQLException e){
			e.printStackTrace();
			
		}
		
	}
	
	public void updateRecensie(String comment, int recensieCijfer, int recensieNummer){
		String query = "UPDATE \"GameRevs\".\"Recensie\" SET \"Comment\" = ?, \"RecensieCijfer\" = ? WHERE \"Recensienummer\" = ?";
		PreparedStatement statement = null;
				
		try (Connection con = super.getConnection()){
			statement = con.prepareStatement(query);
			
			statement.setString(1, comment);
			statement.setInt(2, recensieCijfer);
//			statement.setInt(3, recensieNummer);
			
			statement.executeUpdate();
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}

	}
	
	public void deleteRecensie(int recensieNummer){
		String query = "DELETE FROM \"GameRevs\".\"Recensie\" WHERE \"RecensieNummer\" = ?";
		
		PreparedStatement statement = null;
				
		try (Connection con = super.getConnection()){
			statement = con.prepareStatement(query);
			
			statement.setInt(1, recensieNummer);
			
			statement.executeUpdate();
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}

	}

	
}
