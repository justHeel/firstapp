package nl.hu.ipass.firstapp.webservices;

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

import nl.hu.ipass.firstapp.domain.Lid;
import nl.hu.ipass.firstapp.domain.Recensie;
import nl.hu.ipass.firstapp.domain.Spel;
import nl.hu.ipass.firstapp.webservices.SpelService;
import nl.hu.ipass.firstapp.webservices.SpelServiceProvider;

@Path("/recensies")
public class RecensieResource {

	@GET
	@Path("spel/{spelId}")
	@Produces("application/json")
	public String getRecensiebySpelID(@PathParam("spelId") int spelId) {
		RecensieService service = RecensieServiceProvider.getRecensieservice();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Recensie recensie : service.getRecensiebySpel(spelId)) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("spelNaam", recensie.getSpel().getNaam());
			job.add("spelBeschrijving", recensie.getSpel().getBeschrijving());
			job.add("spelTrailer", recensie.getSpel().getTrailer());
			job.add("spelPublisher", recensie.getSpel().getPublisher());
			job.add("spelNummer", recensie.getSpel().getSpelNummer());
			job.add("cijfer", recensie.getRecensieCijfer());
			job.add("comment", recensie.getComment());

			jab.add(job);

		}

		JsonArray array = jab.build();
		return array.toString();

	}

	@GET
	@RolesAllowed({ "user", "admin" })
	@Path("lid/{lidId}")
	@Produces("application/json")
	public String getRecensiebyLidID(@PathParam("lidId") int lidId) {
		RecensieService service = RecensieServiceProvider.getRecensieservice();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Recensie recensie : service.getRecensiebySpel(lidId)) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("cijfer", recensie.getRecensieCijfer());
			job.add("comment", recensie.getComment());

			jab.add(job);

		}

		JsonArray array = jab.build();
		return array.toString();

	}

	// @POST
	// @Path("{spelId}")
	// @Produces("application/json")
	// public String createRecensie(@FormParam("cijfer") int cfr,
	// @FormParam("comment") String cmt){
	// Recensie newRecensie = new Recensie(cfr, cmt);
	// recensieService.createRecensie(newRecensie);
	//
	// return recensieToJson(newRecensie).build().toString();
	//
	// }
	//
	// private JsonObjectBuilder recensieToJson(Recensie recensie){
	// JsonObjectBuilder job = Json.createObjectBuilder();
	// job.add("cijfer", recensie.getRecensieCijfer());
	// job.add("comment", recensie.getComment());
	//
	// return job;
	// }

}
