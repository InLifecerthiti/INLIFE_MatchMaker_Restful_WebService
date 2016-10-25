package InLifeMatchMaker;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import sun.net.www.protocol.http.HttpURLConnection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Path("/INLIFE")
public class WebServices {

	public static String isUserRegistered = "userIsRegistered";
	public static String userIsAuthorized = "userIsAuthorized";
	public static String userIsRelatedToCaregiver = "userIsRelatedToCaregiver";
	public static String isCaregiver = "isCaregiver";
	public static String getUser = "getUser";
	public static String getConnectedCaregivers = "getConnectedCaregivers";
	public static String getConnectedUsers = "getConnectedUsers";
	public static String getLastUseOfService = "getLastUseOfService";
	public static String getNumberOfUsesOfService = "getNumberOfUsesOfService";
	public static String createUser = "createUser";

	// http://localhost:8080/INLIFE_Matchmaker_Restful_WebService/INLIFE/runRules
	/**
	 * 
	 * @param tmpInput
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	@POST
	@Path("/runRules")
	@Consumes("application/json")
	public Response runRules(Object tmpInput) throws IOException, JSONException {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = MatchmakerManager.getInstance().runRules(
				inputJsonStr,
				MatchmakerManager.getInstance().serviceSynthesisRules);

		return Response.status(200).entity(outputJsonStr).build();
	}

	// http://localhost:8080/INLIFE_Matchmaker_Restful_WebService/INLIFE/recommendServices
	/**
	 * 
	 * @param tmpInput
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	@POST
	@Path("/recommendServices")
	@Consumes("application/json")
	public Response recommendServices(Object tmpInput) throws IOException,
			JSONException {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = MatchmakerManager.getInstance()
				.runRules(inputJsonStr,
						MatchmakerManager.getInstance().recommendServicesRules);

		return Response.status(200).entity(outputJsonStr).build();
	}

	// http://localhost:8080/INLIFE_Matchmaker_Restful_WebService/INLIFE/defineUserTaxonomy
	/**
	 * 
	 * @param tmpInput
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	@POST
	@Path("/defineUserTaxonomy")
	@Consumes("application/json")
	public Response defineUserTaxonomy(Object tmpInput) throws IOException,
			JSONException {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = MatchmakerManager.getInstance().runRules(
				inputJsonStr,
				MatchmakerManager.getInstance().defineUserTaxonomyRules);

		return Response.status(200).entity(outputJsonStr).build();
	}

	// curl -X POST -H "Content-Type: application/json"
	// http://localhost:8080/INLIFE_Matchmaker_Restful_WebService/INLIFE/userIsRegistered
	// -d @INPUT.json
	/**
	 * This method returns the user email if the user is registered to the
	 * system, given the username and the password
	 * 
	 * @param tmpInput
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/userIsRegistered")
	@Consumes("application/json")
	public Response userIsRegistered(Object tmpInput) throws Exception {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = JsonManager.getInstance().getInputs(
				inputJsonStr, WebServices.isUserRegistered);

		return Utils.addHTTPCode(outputJsonStr);

	}

	public Response userIsRegisteredOld(Object tmpInput) throws Exception {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = OntologyManager.getInstance().getInputs(
				inputJsonStr, WebServices.isUserRegistered);
		return Utils.addHTTPCode(outputJsonStr);

	}

	// curl -X POST -H "Content-Type: application/json"
	// http://localhost:8080/INLIFE_Matchmaker_Restful_WebService/INLIFE/userIsAuthorized
	// -d @INPUT.json
	/**
	 * This method returns the user email if the user is registered to the
	 * system, given the username and the password
	 * 
	 * @param tmpInput
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/userIsAuthorized")
	@Consumes("application/json")
	public Response userIsAuthorized(Object tmpInput) throws Exception {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = JsonManager.getInstance().getInputs(
				inputJsonStr, WebServices.userIsAuthorized);
		return Utils.addHTTPCode(outputJsonStr);

	}

	public Response userIsAuthorizedOld(Object tmpInput) throws Exception {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = OntologyManager.getInstance().getInputs(
				inputJsonStr, WebServices.userIsAuthorized);
		return Utils.addHTTPCode(outputJsonStr);

	}

	// curl -X POST -H "Content-Type: application/json"
	// http://localhost:8080/INLIFE_Matchmaker_Restful_WebService/INLIFE/userIsAuthorized
	// -d @INPUT.json
	/**
	 * This method returns the user email if the user is registered to the
	 * system, given the username and the password
	 * 
	 * @param tmpInput
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/userIsRelatedToCaregiver")
	@Consumes("application/json")
	public Response userIsRelatedToCaregiver(Object tmpInput) throws Exception {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = JsonManager.getInstance().getInputs(
				inputJsonStr, WebServices.userIsRelatedToCaregiver);
		return Utils.addHTTPCode(outputJsonStr);

	}

	public Response userIsRelatedToCaregiverOld(Object tmpInput)
			throws Exception {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = OntologyManager.getInstance().getInputs(
				inputJsonStr, WebServices.userIsRelatedToCaregiver);
		return Utils.addHTTPCode(outputJsonStr);

	}

	// curl -X POST -H "Content-Type: application/json"
	// http://localhost:8080/INLIFE_Matchmaker_Restful_WebService/INLIFE/isCaregiver
	// -d @INPUT.json
	/**
	 * 
	 * 
	 * 
	 * @param tmpInput
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/isCaregiver")
	@Consumes("application/json")
	public Response isCaregiver(Object tmpInput) throws Exception {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = JsonManager.getInstance().getInputs(
				inputJsonStr, WebServices.isCaregiver);
		return Utils.addHTTPCode(outputJsonStr);

	}

	public Response isCaregiverOld(Object tmpInput) throws Exception {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = OntologyManager.getInstance().getInputs(
				inputJsonStr, WebServices.isCaregiver);
		return Utils.addHTTPCode(outputJsonStr);

	}

	// curl -X POST -H "Content-Type: application/json"
	// http://localhost:8080/INLIFE_Matchmaker_Restful_WebService/INLIFE/getUser
	// -d @INPUT.json
	/**
	 * This method returns the user profile given the user email
	 * 
	 * @param tmpInput
	 * @return
	 * @throws Exception
	 */

	@POST
	@Path("/getUser")
	@Consumes("application/json")
	public Response getUser(Object tmpInput) throws Exception {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = JsonManager.getInstance().getInputs(
				inputJsonStr, WebServices.getUser);
		return Utils.addHTTPCode(outputJsonStr);
	}

	public Response getUserOld(Object tmpInput) throws Exception {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = OntologyManager.getInstance().getInputs(
				inputJsonStr, WebServices.getUser);
		return Utils.addHTTPCode(outputJsonStr);
	}

	// curl -X POST -H "Content-Type: application/json"
	// http://localhost:8080/INLIFE_Matchmaker_Restful_WebService/INLIFE/getConnectedUsers
	// -d @INPUT.json
	/**
	 * This method returns the user profile given the user email
	 * 
	 * @param tmpInput
	 * @return
	 * @throws Exception
	 */

	@POST
	@Path("/getConnectedUsers")
	@Consumes("application/json")
	public Response getConnectedUsers(Object tmpInput) throws Exception {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = JsonManager.getInstance().getInputs(
				inputJsonStr, WebServices.getConnectedUsers);
		return Utils.addHTTPCode(outputJsonStr);
	}

	public Response getConnectedUsersOld(Object tmpInput) throws Exception {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = OntologyManager.getInstance().getInputs(
				inputJsonStr, WebServices.getConnectedUsers);
		return Utils.addHTTPCode(outputJsonStr);
	}

	/**
	 * This method returns a list with the caregivers given the user email
	 * 
	 * @param tmpInput
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/getConnectedCaregivers")
	@Consumes("application/json")
	public Response getConnectedCaregivers(Object tmpInput) throws Exception {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = JsonManager.getInstance().getInputs(
				inputJsonStr, WebServices.getConnectedCaregivers);
		return Utils.addHTTPCode(outputJsonStr);
	}

	public Response getConnectedCaregiversOld(Object tmpInput) throws Exception {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = OntologyManager.getInstance().getInputs(
				inputJsonStr, WebServices.getConnectedCaregivers);
		return Utils.addHTTPCode(outputJsonStr);
	}

	/**
	 * this method returns the last date of a service use
	 * 
	 * @param tmpInput
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/getLastUseOfService")
	@Consumes("application/json")
	public Response getLastUseOfService(Object tmpInput) throws Exception {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = JsonManager.getInstance().getInputs(
				inputJsonStr, WebServices.getLastUseOfService);
		return Utils.addHTTPCode(outputJsonStr);
	}

	public Response getLastUseOfServiceOld(Object tmpInput) throws Exception {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = OntologyManager.getInstance().getInputs(
				inputJsonStr, WebServices.getLastUseOfService);
		return Utils.addHTTPCode(outputJsonStr);
	}

	/**
	 * this method returns the total number of uses of a service
	 * 
	 * @param tmpInput
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/getNumberOfUsesOfService")
	@Consumes("application/json")
	public Response getNumberOfUsesOfService(Object tmpInput) throws Exception {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = JsonManager.getInstance().getInputs(
				inputJsonStr, WebServices.getNumberOfUsesOfService);
		return Utils.addHTTPCode(outputJsonStr);
	}

	public Response getNumberOfUsesOfServiceOld(Object tmpInput)
			throws Exception {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = OntologyManager.getInstance().getInputs(
				inputJsonStr, WebServices.getNumberOfUsesOfService);
		return Utils.addHTTPCode(outputJsonStr);
	}

	/**
	 * this method returns the total number of uses of a service
	 * 
	 * @param tmpInput
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/createUser")
	@Consumes("application/json")
	public Response createUser(Object tmpInput) throws Exception {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = JsonManager.getInstance().getInputs(
				inputJsonStr, WebServices.createUser);
		return Utils.addHTTPCode(outputJsonStr);
	}

	public Response createUserOld(Object tmpInput) throws Exception {
		OntologyManager.getInstance().debug = "";
		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		String outputJsonStr = OntologyManager.getInstance().getInputs(
				inputJsonStr, WebServices.createUser);
		return Utils.addHTTPCode(outputJsonStr);
	}

	/**
	 * 
	 * 
	 * @param tmpInput
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 * @throws ParseException
	 */
	@POST
	@Path("/sendOntology")
	@Consumes("application/json")
	public Response sendOntology(Object tmpInput) throws IOException,
			JSONException, ParseException {

		String inputJsonStr = MatchmakerManager.getInstance().gson
				.toJson(tmpInput);
		
		//keep the initial input JSON in a string
		MatchmakerManager.getInstance().initialOntologyJSON = inputJsonStr;
		
		//parse json and format objects, initialize hashmaps
		JsonManager.getInstance().parseJson(inputJsonStr);

		String outputJsonStr = "true";

		if (outputJsonStr.contains("false"))
			return Response.status(401).entity(outputJsonStr).build();
		else
			return Response.status(200).entity(outputJsonStr).build();
	}
	
	/**
	 * 
	 * 
	 * @param tmpInput
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 * @throws ParseException
	 */
	@GET
	@Path("/getNewUsers")
	public Response getNewUsers() throws IOException,
			JSONException, ParseException {

		String outputJsonStr = JsonManager.getInstance().createJsonOfNewUsers();

		if (outputJsonStr.contains("false"))
			return Response.status(401).entity(outputJsonStr).build();
		else
			return Response.status(200).entity(outputJsonStr).build();
	}

	
}
