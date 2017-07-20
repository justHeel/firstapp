package nl.hu.ipass.firstapp.webservices;

import java.util.List;

import nl.hu.ipass.firstapp.domain.Spel;
import nl.hu.ipass.firstapp.persistence.SpelDAO;

public class SpelService {
	
private SpelDAO spelDAO = new SpelDAO();

public List<Spel> getSpel(){
	return spelDAO.findall();
}

public Spel getSpelbyID(int id){
	return spelDAO.findById(id);
	
}

public void createSpel(Spel spel){
	if (spel != null){
		spelDAO.createSpel(spel);
	}
	else throw new IllegalArgumentException("Creating Game is not completed!");
	
}

}
