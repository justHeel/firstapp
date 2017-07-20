package nl.hu.ipass.firstapp.webservices;

import java.sql.SQLException;
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import nl.hu.ipass.firstapp.domain.Lid;
import nl.hu.ipass.firstapp.webservices.LidService;
import nl.hu.ipass.firstapp.webservices.LidServiceProvider;

@Path("/leden")
public class LidResource {
LidService lidService = LidServiceProvider.getLidservice();
	
	@GET
	@RolesAllowed({ "user", "admin" })
	@Produces("application/json")
	public String getLid(){
		LidService service = LidServiceProvider.getLidservice();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for (Lid lid : service.getLid()) {
		      JsonObjectBuilder job = Json.createObjectBuilder();
		      job.add("naam", lid.getGebruikersnaam());
		      job.add("email", lid.getEmail());
		      job.add("rol", lid.getRol());
		      jab.add(job);
		    }
		
		    JsonArray array = jab.build();
		    return array.toString();
		  }
	
	@POST
	@Produces ("application/json")
	public String createLid(@FormParam("naam") String nm, @FormParam("email") String email, @FormParam("password1") String pw1, @FormParam("password2") String pw2){
		Lid newLid = new Lid(nm, pw1, email);
		lidService.createLid(newLid);
		
		return lidToJson(newLid).build().toString();
	}
	
	private JsonObjectBuilder lidToJson(Lid lid){
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("naam", lid.getGebruikersnaam());
		job.add("email", lid.getEmail());
		job.add("password1", lid.getWachtwoord());
		
		return job;
	}
	
	@GET
	@RolesAllowed({"user", "admin"})
	@Path("getLid/{token}")
	@Produces("application/json")
	public String getLid(@PathParam("token") String token){
		JwtParser parser = Jwts.parser().setSigningKey(AuthenticationResource.key);
		System.out.println(token + "\n ");
		Claims claims = parser.parseClaimsJws(token).getBody();
//		
		String user = claims.getSubject();
		
		Lid loggedIn = lidService.getLidbyGebruikersnaam(user);
		
		 return getLidToJson(loggedIn).build().toString();
	}
	
	private JsonObjectBuilder getLidToJson (Lid lid){
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("nummer", lid.getLidnummer());
		job.add("naam", lid.getGebruikersnaam());
		job.add("email", lid.getEmail());
		
		return job;
	}
	
	@PUT
	@RolesAllowed({ "user", "admin" })
	@Path("update/{ID}")
	@Produces("application/json")
	public String updateLid(@PathParam("ID") int id,@FormParam("email") String email){
		LidService service = LidServiceProvider.getLidservice();
		if(service.getLidbyID(id) != null){
			service.updateLid(email, id);
			return updateLidToJson(service.getLidbyID(id)).build().toString();
		}
		throw new WebApplicationException("Lid niet gevonden");
	}
	
	private JsonObjectBuilder updateLidToJson (Lid lid){
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("naam", lid.getGebruikersnaam());
		job.add("email", lid.getEmail());
		
		return job;
	}

}
