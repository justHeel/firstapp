package nl.hu.ipass.firstapp.webservices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

import nl.hu.ipass.firstapp.domain.Spel;
import nl.hu.ipass.firstapp.webservices.SpelService;
import nl.hu.ipass.firstapp.webservices.SpelServiceProvider;

@Path("/spellen")
public class SpelResource {
	SpelService spelService = SpelServiceProvider.getSpelservice();

	@GET
	@Produces("application/json")
	public String getSpel() {
		SpelService service = SpelServiceProvider.getSpelservice();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Spel spel : service.getSpel()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("nummer", spel.getSpelNummer());
			job.add("naam", spel.getNaam());
			job.add("beschrijving", spel.getBeschrijving());
			job.add("spelGenre", spel.getSpelGenre());
			job.add("trailer", spel.getTrailer());
			job.add("publisher", spel.getPublisher());

			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("{ID}")
	@Produces("application/json")
	public String getSpelbyID(@PathParam("ID") int ID) {
		SpelService service = SpelServiceProvider.getSpelservice();
		Spel spel = service.getSpelbyID(ID);
		JsonObjectBuilder job = Json.createObjectBuilder();
		
		job.add("nummer", spel.getSpelNummer());
		job.add("naam", spel.getNaam());
		job.add("beschrijving", spel.getBeschrijving());
		job.add("genre", spel.getSpelGenre());
		job.add("trailer", spel.getTrailer());
		job.add("publisher", spel.getPublisher());

		return job.build().toString();
	}

	@POST
	@Produces("application/json")
	public String createSpel(@FormParam("spelnummer") int speln,@FormParam("naam") String nm, @FormParam("publisher") String pbs, @FormParam("genre") String gen, @FormParam("trailer") String trl, @FormParam("omschrijving") String oms) {
		
		Spel newSpel = new Spel(speln,nm, oms, gen, trl, pbs);
		spelService.createSpel(newSpel);

		return spelToJson(newSpel).build().toString();

	}

	private JsonObjectBuilder spelToJson(Spel spel) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("spelnummer", spel.getSpelNummer());
		job.add("naam", spel.getNaam());
		job.add("publisher", spel.getPublisher());
		job.add("genre", spel.getSpelGenre());
		job.add("trailer", spel.getTrailer());
		job.add("omschrijving", spel.getBeschrijving());

		return job;
	}

}
