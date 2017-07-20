package nl.hu.ipass.firstapp.webservices;

import java.util.List;

import nl.hu.ipass.firstapp.domain.Lid;
import nl.hu.ipass.firstapp.persistence.LidDAO;


public class LidService {

private LidDAO lidDAO = new LidDAO();
	
	public List<Lid> getLid(){
		return lidDAO.findall();
	}
	
	public Lid getLidbyID(int id){
		return lidDAO.findById(id);
	}
	
	public Lid getLidbyGebruikersnaam(String gebruikersNaam){
		return lidDAO.findByGebruikersnaam(gebruikersNaam);
	}

	public void createLid(Lid lid){
		if (lid != null){
			lidDAO.createLid(lid);
		}
		else throw new IllegalArgumentException("Sign up not completed!");
	}
	
	public void updateLid(String mail, int lidId){
		lidDAO.UpdateLid(mail, lidId);
	}
}
