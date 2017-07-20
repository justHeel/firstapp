package nl.hu.ipass.firstapp.webservices;

import java.util.List;

import nl.hu.ipass.firstapp.domain.Recensie;
import nl.hu.ipass.firstapp.persistence.RecensieDAO;

public class RecensieService {
	
	private RecensieDAO recensieDAO = new RecensieDAO();
	
	public List<Recensie> getRecensiebySpel(int spelId){
		return recensieDAO.findRecensiebySpel(spelId);
	}
	
	public List<Recensie> getRecensiebyLid(int lidId){
		return recensieDAO.findRecensiebyLid(lidId);
	}
	
	public void createRecensie(Recensie recensie){
		if (recensie != null){
			recensieDAO.createRecensie(recensie);
		}
		else throw new IllegalArgumentException("Creating Recensie is not completed!");

	}
	
	public void updateRecensie(String comment, int cijfer, int recId){
		recensieDAO.updateRecensie(comment, cijfer, recId);
	}
	
	public void deleteRecensie(int redId){
		recensieDAO.deleteRecensie(redId);

	}
	
}
