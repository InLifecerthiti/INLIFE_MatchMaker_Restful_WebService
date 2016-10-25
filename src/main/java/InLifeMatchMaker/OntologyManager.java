package InLifeMatchMaker;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.apache.xerces.parsers.SAXParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

import com.github.jsonldjava.jena.JenaJSONLD;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.impl.IndividualImpl;
import com.hp.hpl.jena.rdf.arp.SAX2Model;
import com.hp.hpl.jena.rdf.arp.SAX2RDF;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.rdf.model.impl.StatementImpl;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

/**
 * 
 * @author aggeliki konstadinidou
 * 
 * 
 */
public class OntologyManager {
	private static OntologyManager instance = null;
	boolean printDebugInfo;
	public String debug;

	public String transformedOntologyInput;
	public static OntModel serviceOntologyModel;
	public static OntModel userOntologyModel;
	public static OntModel sensorOntologyModel;
	public static String USERS_NS = "http://www.AccessibleOntology.com/GenericOntology.owl#";
	private String SENSOR_NS = "http://purl.oclc.org/NET/ssnx/ssn#";
	String serviceOntologyFileName = "serviceOntologyInLife.owl";
	String serviceOntologyPath = "";
	String SERVICE_SOURCE = "http://www.inlife.eu/serviceOntologyInLife.owl";
	private String SERVICE_NS = "http://inlife.ServiceOntology#";

	String S_NS = SERVICE_SOURCE + "#";
	public static Model _dmodel;
	// ----------------------------- -messages for external use
	// -------------------------//
	public static String requestedUserIsRegistered = "Requested user is registered"; // 101
	public static String requestedUserIsNotRegistered = "Requested user is not registered";// 101
	public static String currentUserIsNotRegistered = "Current user is not registered";// 101
	public static String currentUserIsRegistered = "Current user is registered"; // 101
	public static String toolIsNotRegistered = "Tool is not registered";// 101
	public static String wrongPasswordForTool = "Wrong password for tool";// 101
	public static String wrongPasswordForCurrentUser = "Wrong password for current user";// 101
	public static String usersAreConnected = "Users are connected"; // 101
	public static String usersAreNotConnected = "Users are not connected"; // 101
	public static String serviceExists = "Service exists";
	public static String userToCreateUsernameExists = "UserToCreate username exists"; // 101
	public static String emptyRole = "Given role does not correspond to permitted roles"; // 101
	public static String userIsRegisteredSuccessfully = "User is registered successfully";// 101
	public static String userIsCaregiver = "Current user is caregiver"; // 101
	public static String userIsNotCaregiver = "Current user is not caregiver"; // 101

	public static String getUser = "getUser returned information"; // 102
	public static String getConnectedCaregivers = "getConnectedCaregivers returned information"; // 103
	public static String getConnectedUsers = "getConnectedUsers returned information"; // 104
	public static String serviceNotExist = "Service does not exist"; // 201
	public static String getLastUseOfService = "getLastUseOfService returned information";// 202
	public static String getNumberOfUsesOfService = "getNumberOfUsesOfService returned information";// 203

	// ----- messages for internal use ----------------------------
	public static String authorizedAccess = "Authorized access";
	public static String wrongPassword = "Wrong password";
	public static String userIsRegistered = "User is registered";
	public static String userIsNotRegistered = "User is not registered";
	public static String userNameExists = "Username exists";
	public static String userNameNotExist = "Username does not exist";

	// -------------------------- result codes
	// -------------------------------------------//

	// public static int generalSuccess_code = 100;
	public static int getUser_code = 102;
	public static int getConnectedCaregivers_code = 103;
	public static int getConnectedUsers_code = 104;
	public static int getLastUseOfService_code = 202;
	public static int getNumberOfUsesOfService_code = 203;
	public static int serviceNotExist_code = 201;

	public static int booleanResult_code = 101;

	// ----------------------------------------------------------------------------------------

	public static String appCenterUsername = "InlifeAppCentre";
	public static String appCenterPassword = "f3e62180db5f4f943b2b95acd95a57b3bf835031cb530433afd6f45b64c78755"; // ba4zM2rmKkHMyXkz

	private OntologyManager() {
		debug = "";
		printDebugInfo = false;

		// create an empty models
		// serviceOntologyModel = ModelFactory.createOntologyModel();
		// userOntologyModel = ModelFactory.createOntologyModel();

		// JenaJSONLD must be initialized so that the readers and writers are
		// registered with Jena.
		JenaJSONLD.init();
	}

	public static OntologyManager getInstance() {
		if (instance == null)
			instance = new OntologyManager();
		return instance;
	}

	public String getInputs(String inputString, String serviceToBeCalled)
			throws Exception {
		JSONTokener inputTokener = new JSONTokener(inputString);
		String currentUserExists = "";
		String requestedUserExists = "";
		String serviceExists = "";
		boolean isCaregiver = false;
		JSONObject returnedObject = new JSONObject();
		String serviceName = "";
		String requestedUsername = "";
		String requestedUserPassword = "";
		String currentUserPassword = "";
		String currentUsername = "";
		String toolUsername = "";
		String toolPassword = "";
		String userToCreateUsername = "";
		String userToCreatePassword = "";
		String userToCreateRole = ""; // AssistedPerson, FormalCaregiver,
										// InformalCaregiver
		boolean currentUser = true;

		JSONObject jsonInput = null;

		try {
			jsonInput = new JSONObject(inputTokener);
		} catch (org.json.JSONException e) {
		}

		// get information from requested user
		if (jsonInput.has("requestedUser")) {

			if (jsonInput.getJSONObject("requestedUser").has("username"))
				requestedUsername = jsonInput.getJSONObject("requestedUser")
						.get("username").toString();

			if (jsonInput.getJSONObject("requestedUser").has("serviceName"))
				serviceName = jsonInput.getJSONObject("requestedUser")
						.get("serviceName").toString();

			if (jsonInput.getJSONObject("requestedUser").has("password"))
				requestedUserPassword = jsonInput
						.getJSONObject("requestedUser").get("password")
						.toString();
		}

		// get information from current user
		if (jsonInput.has("currentUser")) {

			if (jsonInput.getJSONObject("currentUser").has("username"))
				currentUsername = jsonInput.getJSONObject("currentUser")
						.get("username").toString();

			if (jsonInput.getJSONObject("currentUser").has("password"))
				currentUserPassword = jsonInput.getJSONObject("currentUser")
						.get("password").toString();
		} else {
			currentUser = false;
		}

		// get information from tool
		if (jsonInput.has("tool")) {

			if (jsonInput.getJSONObject("tool").has("username"))
				toolUsername = jsonInput.getJSONObject("tool").get("username")
						.toString();

			if (jsonInput.getJSONObject("tool").has("password"))
				toolPassword = jsonInput.getJSONObject("tool").get("password")
						.toString();

			System.out.println("toolUsername: " + toolUsername);
			System.out.println("toolPassword: " + toolPassword);
		}

		// get the information for the user to create
		if (jsonInput.has("userToCreate")) {

			if (jsonInput.getJSONObject("userToCreate").has("username"))
				userToCreateUsername = jsonInput.getJSONObject("userToCreate")
						.get("username").toString();

			if (jsonInput.getJSONObject("userToCreate").has("password"))
				userToCreatePassword = jsonInput.getJSONObject("userToCreate")
						.get("password").toString();

			if (jsonInput.getJSONObject("userToCreate").has("role"))
				userToCreateRole = jsonInput.getJSONObject("userToCreate")
						.get("role").toString();

		}

		// check authorized access
		String authorizedSource = "";
		String result = "";
		JSONObject temp = new JSONObject();

		authorizedSource = checkAuthorizationOfSource(toolUsername,
				toolPassword);

		if (authorizedSource.equals(authorizedAccess)) {

			if (serviceToBeCalled.equals(WebServices.isUserRegistered)) {

				currentUserExists = checkIfUserExists(currentUsername,
						currentUserPassword);

				if (currentUserExists.equals(userIsRegistered)) {
					returnedObject.put("Result", true);
					returnedObject.put("ResultDescription",
							currentUserIsRegistered);
				} else if (currentUserExists.equals(userIsNotRegistered)) {
					returnedObject.put("Result", false);
					returnedObject.put("ResultDescription",
							currentUserIsNotRegistered);
				} else {
					returnedObject.put("Result", false);
					returnedObject.put("ResultDescription",
							wrongPasswordForCurrentUser);
				}

			} else if (serviceToBeCalled.equals(WebServices.userIsAuthorized)) {

				result = methodForCompletedUserConnection(currentUsername,
						currentUserPassword, requestedUsername);
				returnedObject.put("ResultDescription", result);
				if (result.equals(usersAreConnected))
					returnedObject.put("Result", true);
				else
					returnedObject.put("Result", false);

			} else if (serviceToBeCalled
					.equals(WebServices.userIsRelatedToCaregiver)) {
				result = methodForUserConnection(currentUsername,
						requestedUsername);

				returnedObject.put("ResultDescription", result);

				if (result.equals(usersAreConnected))
					returnedObject.put("Result", true);
				else
					returnedObject.put("Result", false);

			} else if (serviceToBeCalled.equals(WebServices.isCaregiver)) {

				result = checkIfUserNameExists(currentUsername);

				if (result.equals(userNameExists)) {
					isCaregiver = checkIfUserIsCaregiver(currentUsername);
					returnedObject.put("Result", isCaregiver);

					if (isCaregiver)
						returnedObject
								.put("ResultDescription", userIsCaregiver);
					else
						returnedObject.put("ResultDescription",
								userIsNotCaregiver);

				} else {
					returnedObject.put("Result", false);
					returnedObject.put("ResultDescription",
							currentUserIsNotRegistered);
				}

			} else if (serviceToBeCalled.equals(WebServices.getUser)) {

				result = methodForCompletedUserConnection(currentUsername,
						currentUserPassword, requestedUsername);

				if (result.contains(usersAreConnected)) {
					temp = getOntologyUser(requestedUsername);
					returnedObject.put("ResultDescription", getUser);
					returnedObject.put("Result", temp);
				} else {
					returnedObject.put("Result", false);
					returnedObject.put("ResultDescription", result);
				}

			} else if (serviceToBeCalled
					.equals(WebServices.getConnectedCaregivers)) {

				result = methodForCompletedUserConnection(currentUsername,
						currentUserPassword, requestedUsername);

				if (result.contains(usersAreConnected)) {
					returnedObject.put("ResultDescription",
							getConnectedCaregivers);
					temp = getOntologyCaregivers(requestedUsername);
					returnedObject.put("Result", temp);
				} else {
					returnedObject.put("Result", false);
					returnedObject.put("ResultDescription", result);
				}

			} else if (serviceToBeCalled.equals(WebServices.getConnectedUsers)) {

				result = checkIfUserExists(currentUsername, currentUserPassword);

				if (result.equals(userIsRegistered)) {

					returnedObject.put("ResultDescription", getConnectedUsers);
					temp = getConnectedUsers(currentUsername);
					returnedObject.put("Result", temp);

				} else if (result.equals(wrongPassword)) {
					returnedObject.put("Result", false);
					returnedObject.put("ResultDescription",
							wrongPasswordForCurrentUser);
				} else {
					returnedObject.put("Result", false);
					returnedObject.put("ResultDescription",
							currentUserIsNotRegistered);
				}

			} else if (serviceToBeCalled
					.equals(WebServices.getLastUseOfService)
					|| serviceToBeCalled
							.equals(WebServices.getNumberOfUsesOfService)) {

				result = methodForCompletedUserConnection(currentUsername,
						currentUserPassword, requestedUsername);

				if (result.contains(usersAreConnected)) {

					// check if service name exists
					result = checkIfServiceExists(serviceName);

					if (result.equals(OntologyManager.serviceExists)) {

						// get the service usage
						temp = getServiceUsage(requestedUsername, serviceName);

						if (serviceToBeCalled
								.equals(WebServices.getLastUseOfService)) {
							returnedObject.put("ResultDescription",
									getLastUseOfService);
							temp.remove("numberOfUses");
						} else {
							returnedObject.put("ResultDescription",
									getNumberOfUsesOfService);
							temp.remove("lastDateOfUse");
						}

						returnedObject.put("Result", temp);

					} else {
						returnedObject.put("Result", false);
						returnedObject.put("ResultDescription", result);
					}

				} else {
					returnedObject.put("Result", false);
					returnedObject.put("ResultDescription", result);
				}

			} else if (serviceToBeCalled.equals(WebServices.createUser)) {

				result = createUser(currentUsername, currentUserPassword,
						currentUser, userToCreateUsername,
						userToCreatePassword, userToCreateRole);
				returnedObject.put("ResultDescription", result);

				if (result.equals(userIsRegisteredSuccessfully))
					returnedObject.put("Result", true);
				else
					returnedObject.put("Result", false);

				saveOntologyModel(MatchmakerManager.getInstance().userOntology,
						"userOntology.owl", userOntologyModel);

			}
		} else {

			if (authorizedSource.equals(toolIsNotRegistered)) {
				returnedObject.put("Result", false);
				returnedObject.put("ResultDescription", toolIsNotRegistered);
			} else {
				returnedObject.put("Result", false);
				returnedObject.put("ResultDescription", wrongPasswordForTool);
			}

		}

		// format json to return and add response code

		returnedObject = addResultCodeToJSON(returnedObject);

		return returnedObject.toString(5);
	}

	public static JSONObject addResultCodeToJSON(JSONObject json)
			throws JSONException {

		String resultDescription = "";
		if (json.has("ResultDescription")) {
			resultDescription = json.get("ResultDescription").toString();
		}

		if (resultDescription.equals(getUser)) {
			json.put("ResultCode", getUser_code);

		} else if (resultDescription.equals(getConnectedCaregivers)) {
			json.put("ResultCode", getConnectedCaregivers_code);

		} else if (resultDescription.equals(getConnectedUsers)) {
			json.put("ResultCode", getConnectedUsers_code);

		} else if (resultDescription.equals(serviceNotExist)) {
			json.put("ResultCode", serviceNotExist_code);

		} else if (resultDescription.equals(getLastUseOfService)) {
			json.put("ResultCode", getLastUseOfService_code);
		} else if (resultDescription.equals(getNumberOfUsesOfService)) {
			json.put("ResultCode", getNumberOfUsesOfService_code);
		}

		else {
			json.put("ResultCode", booleanResult_code);

		}

		return json;
	}

	private String createUser(String currentUsername,
			String currentUserPassword, boolean currentUser,
			String userToCreateUsername, String userToCreatePassword,
			String userToCreateRole) throws Exception {

		String currentUserExists = "";

		// check the case
		if (currentUser == false) {
			// A. case of a new caregiver
			if (userToCreateRole.equals("FormalCaregiver")
					|| userToCreateRole.equals("InformalCaregiver")) {
				String s = checkIfUserNameExists(userToCreateUsername);
				if (s.equals(userNameNotExist)) {
					addCaregiverProfileToOntology(userToCreateUsername,
							userToCreatePassword, userToCreateRole);
					System.out.println("caregiver is created "
							+ userToCreateUsername);
					return userIsRegisteredSuccessfully;
				} else {
					return userToCreateUsernameExists;
				}

			} else if (userToCreateRole.equals("AssistedPerson")) {
				// B. case of a new assisted person
				String s = checkIfUserNameExists(userToCreateUsername);
				if (s.equals(userNameNotExist)) {
					addAssistedPersonProfileToOntology(userToCreateUsername,
							userToCreatePassword);
					System.out.println("assisted person is created "
							+ userToCreateUsername);
					return userIsRegisteredSuccessfully;
				} else {
					return userToCreateUsernameExists;
				}

			} else {
				return emptyRole;
			}

		} else {

			// C. case where a caregiver enrolls an assisted person

			// 1. check if caregiver exists
			currentUserExists = checkIfUserExists(currentUsername,
					currentUserPassword);
			// 2.check the role for which the account will be created
			if (currentUserExists.equals(userIsRegistered)) {
				String s = checkIfUserNameExists(userToCreateUsername);
				if (s.equals(userNameNotExist)) {

					// 3. create account for assisted
					IndividualImpl assistedSubprofile = addAssistedPersonProfileToOntology(
							userToCreateUsername, userToCreatePassword);

					IndividualImpl caregiverSubprofile = getCaregiverSubProfile(currentUsername);

					// 4. add connection between assisted and caregiver
					addConnectionsToProfile(assistedSubprofile,
							caregiverSubprofile);
					System.out
							.println("caregiver case:assisted person is created "
									+ userToCreateUsername);
					return userIsRegisteredSuccessfully;
				} else {
					return userToCreateUsernameExists;
				}
			} else if (currentUserExists.equals(userIsNotRegistered)) {
				return currentUserIsNotRegistered;
			} else {
				return wrongPasswordForCurrentUser;
			}
		}

	}

	public String methodForCompletedUserConnection(String currentUsername,
			String currentUserPassword, String requestedUsername)
			throws JSONException {

		String result = "";
		String currentUserExists = "", requestedUserExists = "";
		currentUserExists = checkIfUserExists(currentUsername,
				currentUserPassword);

		if (currentUserExists.equals(userIsRegistered)) {

			System.out.println("Current user:" + currentUserExists);
			requestedUserExists = checkIfUserNameExists(requestedUsername);

			if (requestedUserExists.equals(userNameExists)) {
				// check the connection if both users exist
				System.out.println("Requested user:" + requestedUserExists);
				if (requestedUsername.equals(currentUsername))
					return usersAreConnected;
				result = checkIfUsersAreConnected(requestedUsername,
						currentUsername, currentUserPassword);

			} else {
				System.out.println("Requested user:" + requestedUserExists);
				if (requestedUserExists.equals(userNameNotExist)) {

					result = requestedUserIsNotRegistered;
				}

			}

		} else {

			System.out.println("Current user:" + currentUserExists);
			if (currentUserExists.equals(userIsNotRegistered)) {

				result = currentUserIsNotRegistered;
			} else {

				result = wrongPasswordForCurrentUser;
			}

		}

		return result;
	}

	public String methodForUserConnection(String currentUsername,
			String requestedUsername) throws JSONException {
		JSONObject returnedObject = new JSONObject();
		String currentUserExists = "", requestedUserExists = "";
		String result = "";

		currentUserExists = checkIfUserNameExists(currentUsername);

		if (currentUserExists.equals(userNameExists)) {

			System.out.println("Current user:" + currentUserExists);
			requestedUserExists = checkIfUserNameExists(requestedUsername);

			if (requestedUserExists.equals(userNameExists)) {
				// check the connection if both users exist
				System.out.println("Requested user:" + requestedUserExists);
				result = checkIfUserIsRelatedToCaregiver(requestedUsername,
						currentUsername);

			} else {
				System.out.println("Requested user:" + requestedUserExists);
				if (requestedUserExists.equals(userNameNotExist))
					result = requestedUserIsNotRegistered;

			}

		} else {

			System.out.println("Current user:" + currentUserExists);
			result = currentUserIsNotRegistered;

		}

		return result;
	}

	public String checkAuthorizationOfSource(String username, String password) {

		if (username == null || username.isEmpty())
			return toolIsNotRegistered;

		if (password == null || password.isEmpty())
			password = "";

		// check app center
		if (username.equals(appCenterUsername)
				&& password.equals(appCenterPassword))
			return authorizedAccess;
		else if (username.equals(appCenterUsername)
				&& !password.equals(appCenterPassword))
			return wrongPassword;

		// check other tools
		if (serviceOntologyModel == null)
			serviceOntologyModel = populateOntologyModel(
					MatchmakerManager.getInstance().serviceOntology,
					serviceOntologyModel);

		DatatypeProperty hasUsername = serviceOntologyModel
				.getDatatypeProperty(SERVICE_NS + "hasUsername");

		DatatypeProperty hasPassword = serviceOntologyModel
				.getDatatypeProperty(SERVICE_NS + "hasPassword");

		OntClass toolClass = serviceOntologyModel.getOntClass(SERVICE_NS
				+ "Tool");

		String ontUsername = "";
		String ontPassword = "";

		Iterator it = toolClass.listInstances();
		while (it.hasNext()) {
			IndividualImpl in = (IndividualImpl) it.next();

			if (in.getPropertyValue(hasUsername) != null)
				ontUsername = in
						.getPropertyValue(hasUsername)
						.asLiteral()
						.toString()
						.replace("^^http://www.w3.org/2001/XMLSchema#string",
								"");
			if (in.getPropertyValue(hasPassword) != null)
				ontPassword = in
						.getPropertyValue(hasPassword)
						.asLiteral()
						.toString()
						.replace("^^http://www.w3.org/2001/XMLSchema#string",
								"");

			if (ontUsername.equals(username)) {
				if (ontPassword.equals(password)) {
					return authorizedAccess;
				} else {
					return wrongPasswordForTool;
				}
			}

		}

		return toolIsNotRegistered;

	}

	public IndividualImpl addAssistedPersonProfileToOntology(String username,
			String password) throws Exception {

		ObjectProperty hasSubProfile = userOntologyModel
				.getObjectProperty(USERS_NS + "hasSubProfile");
		ObjectProperty hasProfile = userOntologyModel
				.getObjectProperty(USERS_NS + "hasProfile");
		DatatypeProperty hasUsername = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasUsername");
		DatatypeProperty hasPassword = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasPassword");

		String str = "";
		str = username.replace(" ", "_").replaceAll("[^\\p{L}\\p{Nd}]", "");
		if (Character.isDigit(str.charAt(0))) {
			str = "ap_profilable";
		}

		// create assisted Person Profilable instance
		OntClass profilableClass = userOntologyModel.getOntClass(USERS_NS
				+ "AssistedPerson");
		IndividualImpl profilable = (IndividualImpl) userOntologyModel
				.createIndividual(USERS_NS + str + "_profilable",
						profilableClass);

		// create assisted Person Profile instance
		OntClass profileClass = userOntologyModel.getOntClass(USERS_NS
				+ "AssistedPersonProfile");
		IndividualImpl profile = (IndividualImpl) userOntologyModel
				.createIndividual(USERS_NS + str + "_profile", profileClass);

		// create assisted Person subProfile instance
		OntClass subprofileClass = userOntologyModel.getOntClass(USERS_NS
				+ "AssistedPersonSubProfile");

		IndividualImpl subprofile = (IndividualImpl) userOntologyModel
				.createIndividual(USERS_NS + str + "_subprofile",
						subprofileClass);

		// password = Utils.encrypt(password);

		profilable.addProperty(hasProfile, profile);
		profile.addProperty(hasSubProfile, subprofile);
		subprofile.addProperty(hasUsername, username);
		subprofile.addProperty(hasPassword, password);

		return subprofile;

	}

	public IndividualImpl addCaregiverProfileToOntology(String username,
			String password, String type) throws Exception {

		ObjectProperty hasSubProfile = userOntologyModel
				.getObjectProperty(USERS_NS + "hasSubProfile");
		ObjectProperty hasProfile = userOntologyModel
				.getObjectProperty(USERS_NS + "hasProfile");
		DatatypeProperty hasUsername = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasUsername");
		DatatypeProperty hasPassword = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasPassword");

		String str = "";
		str = username.replace(" ", "_").replaceAll("[^\\p{L}\\p{Nd}]", "");
		if (Character.isDigit(str.charAt(0))) {
			str = "ap_profilable";
		}

		// create assisted Person Profilable instance
		OntClass profilableClass = userOntologyModel.getOntClass(USERS_NS
				+ "Caregiver");
		IndividualImpl profilable = (IndividualImpl) userOntologyModel
				.createIndividual(USERS_NS + str + "_profilable",
						profilableClass);

		// create assisted Person Profile instance
		OntClass profileClass = userOntologyModel.getOntClass(USERS_NS
				+ "FormalCaregiver");
		if (type.equals("FormalCaregiver"))
			profileClass = userOntologyModel.getOntClass(USERS_NS
					+ "InformalCaregiver");
		IndividualImpl profile = (IndividualImpl) userOntologyModel
				.createIndividual(USERS_NS + str + "_profile", profileClass);

		// create assisted Person subProfile instance
		OntClass subprofileClass = userOntologyModel.getOntClass(USERS_NS
				+ "CaregiverSubProfile");

		IndividualImpl subprofile = (IndividualImpl) userOntologyModel
				.createIndividual(USERS_NS + str + "_subprofile",
						subprofileClass);

		// password = Utils.encrypt(password);

		profilable.addProperty(hasProfile, profile);
		profile.addProperty(hasSubProfile, subprofile);
		subprofile.addProperty(hasUsername, username);
		subprofile.addProperty(hasPassword, password);

		return subprofile;

	}

	public IndividualImpl getCaregiverSubProfile(String username) {

		if (userOntologyModel == null)
			userOntologyModel = populateOntologyModel(
					MatchmakerManager.getInstance().userOntology,
					userOntologyModel);

		DatatypeProperty hasUsername = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasUsername");

		String ontUsername = "";

		OntClass subProfileClass = userOntologyModel.getOntClass(USERS_NS
				+ "CaregiverSubProfile");

		Iterator it = subProfileClass.listInstances();
		while (it.hasNext()) {
			IndividualImpl in = (IndividualImpl) it.next();

			// hasUsername
			if (in.getPropertyValue(hasUsername) != null) {
				ontUsername = in
						.getPropertyValue(hasUsername)
						.asLiteral()
						.toString()
						.replace("^^http://www.w3.org/2001/XMLSchema#string",
								"");

			}

			if (ontUsername.equals(username)) {

				return in;

			}

		}

		return null;

	}

	public void addConnectionsToProfile(IndividualImpl assistedSubprofile,
			IndividualImpl caregiverSubprofile) {

		ObjectProperty tempProperty = userOntologyModel
				.getObjectProperty(USERS_NS + "approvedConnected");

		assistedSubprofile.addProperty(tempProperty, caregiverSubprofile);
		caregiverSubprofile.addProperty(tempProperty, assistedSubprofile);

	}

	public String checkIfUserIsRelatedToCaregiver(String requestedUser,
			String currentUser) throws JSONException {
		String ontEmail = "";
		String assistedEmail = "";
		JSONObject json = new JSONObject();

		if (userOntologyModel == null)
			userOntologyModel = populateOntologyModel(
					MatchmakerManager.getInstance().userOntology,
					userOntologyModel);

		ObjectProperty hasSubProfile = userOntologyModel
				.getObjectProperty(USERS_NS + "hasSubProfile");

		ObjectProperty approvedConnected = userOntologyModel
				.getObjectProperty(USERS_NS + "approvedConnected");

		DatatypeProperty hasUsername = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasUsername");

		OntClass formalCaregiversProfileClass = userOntologyModel
				.getOntClass(USERS_NS + "FormalCaregiver");

		OntClass informalCaregiversProfileClass = userOntologyModel
				.getOntClass(USERS_NS + "InformalCaregiver");

		// find the type of the carer: formal/informal
		// search for FORMAL carers
		Iterator it2 = formalCaregiversProfileClass.listInstances();
		while (it2.hasNext()) {
			IndividualImpl in2 = (IndividualImpl) it2.next();

			if (in2.getPropertyResourceValue(hasSubProfile) != null) {
				StmtIterator it4 = in2.listProperties(hasSubProfile);

				while (it4.hasNext()) {

					StatementImpl st = (StatementImpl) it4.next();
					Resource r = st.getResource();
					Individual tempInd = userOntologyModel.getIndividual(r
							.getURI());

					// hasUsername
					if (tempInd.getPropertyValue(hasUsername) != null)
						ontEmail = tempInd
								.getPropertyValue(hasUsername)
								.asLiteral()
								.toString()
								.replace(
										"^^http://www.w3.org/2001/XMLSchema#string",
										"");

					// if the current user is found, check his connections
					if (ontEmail.equalsIgnoreCase(currentUser)) {

						if (tempInd.getPropertyResourceValue(approvedConnected) != null) {
							StmtIterator it = tempInd
									.listProperties(approvedConnected);

							while (it.hasNext()) {

								StatementImpl st1 = (StatementImpl) it.next();
								Resource r1 = st1.getResource();
								Individual assistedInd = userOntologyModel
										.getIndividual(r1.getURI());

								if (assistedInd.getPropertyValue(hasUsername) != null) {
									assistedEmail = assistedInd
											.getPropertyValue(hasUsername)
											.asLiteral()
											.toString()
											.replace(
													"^^http://www.w3.org/2001/XMLSchema#string",
													"");

									if (assistedEmail
											.equalsIgnoreCase(requestedUser)) {
										return usersAreConnected;
									}

								}
							}
						}

					}

				}
			}
		}

		// search for INFORMAL carers
		it2 = informalCaregiversProfileClass.listInstances();
		while (it2.hasNext()) {
			IndividualImpl in2 = (IndividualImpl) it2.next();

			if (in2.getPropertyResourceValue(hasSubProfile) != null) {
				StmtIterator it4 = in2.listProperties(hasSubProfile);

				while (it4.hasNext()) {

					StatementImpl st = (StatementImpl) it4.next();
					Resource r = st.getResource();
					Individual tempInd = userOntologyModel.getIndividual(r
							.getURI());

					// hasUsername
					if (tempInd.getPropertyValue(hasUsername) != null)
						ontEmail = tempInd
								.getPropertyValue(hasUsername)
								.asLiteral()
								.toString()
								.replace(
										"^^http://www.w3.org/2001/XMLSchema#string",
										"");
					// if the current user is found, check his connections
					if (ontEmail.equalsIgnoreCase(currentUser)) {

						if (tempInd.getPropertyResourceValue(approvedConnected) != null) {
							StmtIterator it = tempInd
									.listProperties(approvedConnected);

							while (it.hasNext()) {

								StatementImpl st1 = (StatementImpl) it.next();
								Resource r1 = st1.getResource();
								Individual assistedInd = userOntologyModel
										.getIndividual(r1.getURI());

								if (assistedInd.getPropertyValue(hasUsername) != null) {
									assistedEmail = assistedInd
											.getPropertyValue(hasUsername)
											.asLiteral()
											.toString()
											.replace(
													"^^http://www.w3.org/2001/XMLSchema#string",
													"");

									if (assistedEmail
											.equalsIgnoreCase(requestedUser)) {
										return usersAreConnected;
									}
								}
							}
						}

					}

				}
			}

		}

		return usersAreNotConnected;
	}

	public String checkIfUserNameExists(String username) {

		if (username == null || username.isEmpty())
			return userNameNotExist;

		String ontUsername = "";
		if (userOntologyModel == null)
			userOntologyModel = populateOntologyModel(
					MatchmakerManager.getInstance().userOntology,
					userOntologyModel);
		DatatypeProperty hasUsername = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasUsername");

		OntClass subProfileClass = userOntologyModel.getOntClass(USERS_NS
				+ "SubProfile");

		Iterator it = subProfileClass.listInstances();
		while (it.hasNext()) {
			IndividualImpl in = (IndividualImpl) it.next();

			// hasUsername
			if (in.getPropertyValue(hasUsername) != null)
				ontUsername = in
						.getPropertyValue(hasUsername)
						.asLiteral()
						.toString()
						.replace("^^http://www.w3.org/2001/XMLSchema#string",
								"");

			if (ontUsername.equals(username))
				return userNameExists;
		}

		return userNameNotExist;
	}

	public String checkIfUsersAreConnected(String requestedUser,
			String currentUser, String password) throws JSONException {
		boolean caregiverFlag = false;
		String ontEmail = "";
		String ontPassword = "";
		String assistedEmail = "";
		JSONObject json = new JSONObject();

		if (userOntologyModel == null)
			userOntologyModel = populateOntologyModel(
					MatchmakerManager.getInstance().userOntology,
					userOntologyModel);

		ObjectProperty hasSubProfile = userOntologyModel
				.getObjectProperty(USERS_NS + "hasSubProfile");

		ObjectProperty approvedConnected = userOntologyModel
				.getObjectProperty(USERS_NS + "approvedConnected");

		DatatypeProperty hasUsername = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasUsername");

		DatatypeProperty hasPassword = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasPassword");

		OntClass formalCaregiversProfileClass = userOntologyModel
				.getOntClass(USERS_NS + "FormalCaregiver");

		OntClass informalCaregiversProfileClass = userOntologyModel
				.getOntClass(USERS_NS + "InformalCaregiver");
		String caregiverType = "";

		// find the type of the carer: formal/informal
		// search for FORMAL carers
		Iterator it2 = formalCaregiversProfileClass.listInstances();
		while (it2.hasNext()) {
			IndividualImpl in2 = (IndividualImpl) it2.next();

			if (in2.getPropertyResourceValue(hasSubProfile) != null) {
				StmtIterator it4 = in2.listProperties(hasSubProfile);

				while (it4.hasNext()) {

					StatementImpl st = (StatementImpl) it4.next();
					Resource r = st.getResource();
					Individual tempInd = userOntologyModel.getIndividual(r
							.getURI());

					// hasUsername
					if (tempInd.getPropertyValue(hasUsername) != null)
						ontEmail = tempInd
								.getPropertyValue(hasUsername)
								.asLiteral()
								.toString()
								.replace(
										"^^http://www.w3.org/2001/XMLSchema#string",
										"");

					// hasPassword
					if (tempInd.getPropertyValue(hasPassword) != null)
						ontPassword = tempInd
								.getPropertyValue(hasPassword)
								.asLiteral()
								.toString()
								.replace(
										"^^http://www.w3.org/2001/XMLSchema#string",
										"");

					System.out.println(ontEmail);
					System.out.println(ontPassword);
					System.out.println("----------------------------------");

					System.out.println(password);
					// if the current user is found, check his connections
					if (ontEmail.equalsIgnoreCase(currentUser)) {

						if (ontPassword.equals(password)) {

							caregiverFlag = true;
							System.out.println("true");

							if (tempInd
									.getPropertyResourceValue(approvedConnected) != null) {
								StmtIterator it = tempInd
										.listProperties(approvedConnected);

								while (it.hasNext()) {

									StatementImpl st1 = (StatementImpl) it
											.next();
									Resource r1 = st1.getResource();
									Individual assistedInd = userOntologyModel
											.getIndividual(r1.getURI());

									if (assistedInd
											.getPropertyValue(hasUsername) != null) {
										assistedEmail = assistedInd
												.getPropertyValue(hasUsername)
												.asLiteral()
												.toString()
												.replace(
														"^^http://www.w3.org/2001/XMLSchema#string",
														"");

										if (assistedEmail
												.equalsIgnoreCase(requestedUser)) {
											return usersAreConnected;
										}

									}
								}
							}

						}

					}

				}
			}

		}

		// search for INFORMAL carers
		if (!caregiverFlag) {
			it2 = informalCaregiversProfileClass.listInstances();
			while (it2.hasNext()) {
				IndividualImpl in2 = (IndividualImpl) it2.next();

				if (in2.getPropertyResourceValue(hasSubProfile) != null) {
					StmtIterator it4 = in2.listProperties(hasSubProfile);

					while (it4.hasNext()) {

						StatementImpl st = (StatementImpl) it4.next();
						Resource r = st.getResource();
						Individual tempInd = userOntologyModel.getIndividual(r
								.getURI());

						// hasUsername
						if (tempInd.getPropertyValue(hasUsername) != null)
							ontEmail = tempInd
									.getPropertyValue(hasUsername)
									.asLiteral()
									.toString()
									.replace(
											"^^http://www.w3.org/2001/XMLSchema#string",
											"");

						// hasPassword
						if (tempInd.getPropertyValue(hasPassword) != null)
							ontPassword = tempInd
									.getPropertyValue(hasPassword)
									.asLiteral()
									.toString()
									.replace(
											"^^http://www.w3.org/2001/XMLSchema#string",
											"");

						System.out.println(ontEmail);
						System.out.println(ontPassword);
						System.out
								.println("----------------------------------");

						System.out.println(password);
						if (ontEmail.equalsIgnoreCase(currentUser)) {

							caregiverFlag = true;
							if (ontPassword.equals(password)) {

								System.out.println("true");
								if (tempInd
										.getPropertyResourceValue(approvedConnected) != null) {
									StmtIterator it = tempInd
											.listProperties(approvedConnected);

									while (it.hasNext()) {

										StatementImpl st1 = (StatementImpl) it
												.next();
										Resource r1 = st1.getResource();
										Individual assistedInd = userOntologyModel
												.getIndividual(r1.getURI());

										if (assistedInd
												.getPropertyValue(hasUsername) != null) {
											assistedEmail = assistedInd
													.getPropertyValue(
															hasUsername)
													.asLiteral()
													.toString()
													.replace(
															"^^http://www.w3.org/2001/XMLSchema#string",
															"");

											if (assistedEmail
													.equalsIgnoreCase(requestedUser)) {
												return usersAreConnected;
											}

										}
									}
								}

							}
						}

					}
				}

			}
		}
		return usersAreNotConnected;
	}

	public JSONObject getOntologyCaregivers(String username)
			throws JSONException {
		JSONObject jsonOutput = new JSONObject();
		JSONArray caregiversArray = new JSONArray();
		JSONObject tempObj = null;

		if (userOntologyModel == null)
			userOntologyModel = populateOntologyModel(
					MatchmakerManager.getInstance().userOntology,
					userOntologyModel);

		DatatypeProperty hasUsername = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasUsername");

		DatatypeProperty hasEmail = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasEmail");

		DatatypeProperty hasFirstName = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasFirstName");

		DatatypeProperty hasLastName = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasLastName");

		DatatypeProperty hasPhoneNumber = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasPhoneNumber");

		ObjectProperty approvedConnected = userOntologyModel
				.getObjectProperty(USERS_NS + "approvedConnected");

		ObjectProperty hasSubProfile = userOntologyModel
				.getObjectProperty(USERS_NS + "hasSubProfile");

		String email = "";
		String caregiverFirstName = "";
		String caregiverLastName = "";
		String caregiverEmail = "";
		String caregiverPhoneNumber = "";
		String caregiverType = "";
		String caregiverUsername = "";
		boolean flag = false;

		OntClass subProfileClass = userOntologyModel.getOntClass(USERS_NS
				+ "AssistedPersonSubProfile");

		OntClass formalCaregiversProfileClass = userOntologyModel
				.getOntClass(USERS_NS + "FormalCaregiver");

		OntClass informalCaregiversProfileClass = userOntologyModel
				.getOntClass(USERS_NS + "InformalCaregiver");

		System.out.println("Subprofile class found");

		Iterator it = subProfileClass.listInstances();
		while (it.hasNext()) {
			IndividualImpl in = (IndividualImpl) it.next();

			// hasUsername
			if (in.getPropertyValue(hasUsername) != null)
				email = in
						.getPropertyValue(hasUsername)
						.asLiteral()
						.toString()
						.replace("^^http://www.w3.org/2001/XMLSchema#string",
								"");

			if (email.equals(username)) {
				System.out.println("USER FOUND");

				if (in.getPropertyResourceValue(approvedConnected) != null) {
					StmtIterator it3 = in.listProperties(approvedConnected);
					while (it3.hasNext()) {

						StatementImpl st3 = (StatementImpl) it3.next();
						Resource disR = st3.getResource();
						Individual carerInd = userOntologyModel
								.getIndividual(disR.getURI());

						// initializations

						tempObj = new JSONObject();
						caregiverFirstName = "";
						caregiverLastName = "";
						caregiverPhoneNumber = "";
						caregiverEmail = "";
						caregiverUsername = "";
						caregiverType = "";
						flag = false;

						// caregiver first name
						if (carerInd.getProperty(hasFirstName) != null)
							caregiverFirstName = carerInd
									.getPropertyValue(hasFirstName)
									.asLiteral()
									.toString()
									.replace(
											"^^http://www.w3.org/2001/XMLSchema#string",
											"");

						// caregiver last name
						if (carerInd.getProperty(hasLastName) != null)
							caregiverLastName = carerInd
									.getPropertyValue(hasLastName)
									.asLiteral()
									.toString()
									.replace(
											"^^http://www.w3.org/2001/XMLSchema#string",
											"");

						// caregiver phone number
						if (carerInd.getProperty(hasPhoneNumber) != null)
							caregiverPhoneNumber = carerInd
									.getPropertyValue(hasPhoneNumber)
									.asLiteral()
									.toString()
									.replace(
											"^^http://www.w3.org/2001/XMLSchema#string",
											"");

						// caregiver username
						if (carerInd.getProperty(hasUsername) != null)
							caregiverUsername = carerInd
									.getPropertyValue(hasUsername)
									.asLiteral()
									.toString()
									.replace(
											"^^http://www.w3.org/2001/XMLSchema#string",
											"");

						// caregiver email
						if (carerInd.getProperty(hasEmail) != null)
							caregiverEmail = carerInd
									.getPropertyValue(hasEmail)
									.asLiteral()
									.toString()
									.replace(
											"^^http://www.w3.org/2001/XMLSchema#string",
											"");

						// find the type of the carer: formal/informal
						// search for FORMAL carers
						Iterator it2 = formalCaregiversProfileClass
								.listInstances();
						while (it2.hasNext()) {
							IndividualImpl in2 = (IndividualImpl) it2.next();

							if (in2.getPropertyResourceValue(hasSubProfile) != null) {
								StmtIterator it4 = in2
										.listProperties(hasSubProfile);

								while (it4.hasNext()) {

									StatementImpl st = (StatementImpl) it4
											.next();
									Resource r = st.getResource();
									Individual tempInd = userOntologyModel
											.getIndividual(r.getURI());

									System.out.println(r.getURI().toString());

									if (tempInd.equals(carerInd)) {
										caregiverType = in2.getOntClass()
												.getLocalName().toString();
										flag = true;
										break;
									}
								}
							}

							if (flag)
								break;
						}

						// search for INFORMAL carers
						if (!flag) {
							it2 = informalCaregiversProfileClass
									.listInstances();
							while (it2.hasNext()) {
								IndividualImpl in2 = (IndividualImpl) it2
										.next();

								if (in2.getPropertyResourceValue(hasSubProfile) != null) {
									StmtIterator it4 = in2
											.listProperties(hasSubProfile);

									while (it4.hasNext()) {

										StatementImpl st = (StatementImpl) it4
												.next();
										Resource r = st.getResource();
										Individual tempInd = userOntologyModel
												.getIndividual(r.getURI());

										System.out.println(r.getURI()
												.toString());

										if (tempInd.equals(carerInd)) {
											caregiverType = in2.getOntClass()
													.getLocalName().toString();
											flag = true;
											break;
										}
									}
								}

								if (flag)
									break;
							}
						}

						// add the information to the array
						tempObj.put("firstName", caregiverFirstName);
						tempObj.put("lastName", caregiverLastName);
						tempObj.put("phoneNumber", caregiverPhoneNumber);
						tempObj.put("email", caregiverEmail);
						tempObj.put("username", caregiverUsername);
						tempObj.put("type", caregiverType);
						caregiversArray.put(tempObj);

					}
				}

				jsonOutput.put("caregivers", caregiversArray);
				return jsonOutput;

			}

		}
		return jsonOutput;
	}

	public String checkIfServiceExists(String serviceName) {

		if (serviceName == null || serviceName.isEmpty())
			return serviceNotExist;

		if (serviceOntologyModel == null)
			serviceOntologyModel = populateOntologyModel(
					MatchmakerManager.getInstance().serviceOntology,
					serviceOntologyModel);

		DatatypeProperty serviceHasName = serviceOntologyModel
				.getDatatypeProperty(SERVICE_NS + "serviceHasName");

		String ontServiceName = "";

		// check if service name exists
		OntClass serviceModelClass = serviceOntologyModel
				.getOntClass(SERVICE_NS + "ServiceModel");

		Iterator it2 = serviceModelClass.listInstances();
		while (it2.hasNext()) {
			IndividualImpl in2 = (IndividualImpl) it2.next();

			// hasUserId
			if (in2.getPropertyValue(serviceHasName) != null)
				ontServiceName = in2
						.getPropertyValue(serviceHasName)
						.asLiteral()
						.toString()
						.replace("^^http://www.w3.org/2001/XMLSchema#string",
								"");

			if (ontServiceName.equals(serviceName)) {
				return serviceExists;
			}
		}

		return serviceNotExist;

	}

	public JSONObject getServiceUsage(String userName, String serviceName)
			throws JSONException, ParseException {
		JSONObject jsonOutput = new JSONObject();

		if (sensorOntologyModel == null)
			sensorOntologyModel = populateOntologyModel(
					MatchmakerManager.getInstance().sensorOntology,
					sensorOntologyModel);

		DatatypeProperty hasUserId = sensorOntologyModel
				.getDatatypeProperty(SENSOR_NS + "hasUserId");

		DatatypeProperty hasServiceName = sensorOntologyModel
				.getDatatypeProperty(SENSOR_NS + "hasServiceName");

		DatatypeProperty hasEventDate = sensorOntologyModel
				.getDatatypeProperty(SENSOR_NS + "hasEventDate");

		String email = "";
		String ontServiceName = "";
		String dateTime = "";
		Date date = new Date();
		ArrayList<String> list = new ArrayList<String>();

		OntClass usageClass = sensorOntologyModel.getOntClass(SENSOR_NS
				+ "Usage");

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

		Iterator it = usageClass.listInstances();
		while (it.hasNext()) {
			IndividualImpl in = (IndividualImpl) it.next();

			// hasUserId
			if (in.getPropertyValue(hasUserId) != null)
				email = in
						.getPropertyValue(hasUserId)
						.asLiteral()
						.toString()
						.replace("^^http://www.w3.org/2001/XMLSchema#string",
								"");
			// hasServiceName
			if (in.getPropertyValue(hasServiceName) != null)
				ontServiceName = in
						.getPropertyValue(hasServiceName)
						.asLiteral()
						.toString()
						.replace("^^http://www.w3.org/2001/XMLSchema#string",
								"");

			if (email.equalsIgnoreCase(userName)
					&& ontServiceName.equalsIgnoreCase(serviceName)) {

				if (in.getPropertyValue(hasEventDate) != null) {
					String string = in.getPropertyValue(hasEventDate)
							.asLiteral().getString();
					list.add(string);
					if (!string.isEmpty())
						date = (Date) sdf1.parse(string);
				}

			}

		}

		Collections.sort(list);
		String lastDateOfUse = "-";
		if (!list.isEmpty())
			lastDateOfUse = list.get(list.size() - 1);

		jsonOutput.put("serviceName", serviceName);
		jsonOutput.put("lastDateOfUse", lastDateOfUse);
		jsonOutput.put("numberOfUses", list.size());

		return jsonOutput;
	}

	public JSONObject getOntologyUser(String username) throws JSONException {

		JSONObject jsonOutput = new JSONObject();

		if (userOntologyModel == null)
			userOntologyModel = populateOntologyModel(
					MatchmakerManager.getInstance().userOntology,
					userOntologyModel);

		DatatypeProperty hasName = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasName");
		DatatypeProperty hasUsername = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasUsername");
		DatatypeProperty hasFirstName = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasFirstName");
		DatatypeProperty hasLastName = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasLastName");
		DatatypeProperty hasLocation = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasLocation");
		DatatypeProperty hasPhoneNumber = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasPhoneNumber");
		DatatypeProperty hasStatus = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasStatus");

		ObjectProperty user_linksTo_Impairment = userOntologyModel
				.getObjectProperty(USERS_NS + "User_linksTo_Impairment");

		ObjectProperty user_linksTo_FunctionalLimitations = userOntologyModel
				.getObjectProperty(USERS_NS
						+ "User_linksTo_FunctionalLimitation");

		ObjectProperty user_has_Disability = userOntologyModel
				.getObjectProperty(USERS_NS + "User_has_Disability");

		String email = "";
		String firstName = "";
		String lastName = "";
		String location = "";
		String phoneNumber = "";
		String status = "";
		String name = "";
		JSONArray disabilities = new JSONArray();
		JSONArray impairments = new JSONArray();
		JSONArray functionalLimitations = new JSONArray();

		OntClass subProfileClass = userOntologyModel.getOntClass(USERS_NS
				+ "AssistedPersonSubProfile");

		System.out.println("Subprofile class found");

		Iterator it = subProfileClass.listInstances();
		while (it.hasNext()) {
			IndividualImpl in = (IndividualImpl) it.next();

			// hasUsername
			if (in.getPropertyValue(hasUsername) != null)
				email = in
						.getPropertyValue(hasUsername)
						.asLiteral()
						.toString()
						.replace("^^http://www.w3.org/2001/XMLSchema#string",
								"");

			if (email.equals(username)) {
				System.out.println("USER FOUND");

				// hasFirstName
				if (in.getPropertyValue(hasFirstName) != null)
					firstName = in
							.getPropertyValue(hasFirstName)
							.asLiteral()
							.toString()
							.replace(
									"^^http://www.w3.org/2001/XMLSchema#string",
									"");
				// hasLastName
				if (in.getPropertyValue(hasLastName) != null)
					lastName = in
							.getPropertyValue(hasLastName)
							.asLiteral()
							.toString()
							.replace(
									"^^http://www.w3.org/2001/XMLSchema#string",
									"");
				// hasLocation
				if (in.getPropertyValue(hasLocation) != null)
					location = in
							.getPropertyValue(hasLocation)
							.asLiteral()
							.toString()
							.replace(
									"^^http://www.w3.org/2001/XMLSchema#string",
									"");

				// hasPhoneNumber
				if (in.getPropertyValue(hasPhoneNumber) != null)
					phoneNumber = in
							.getPropertyValue(hasPhoneNumber)
							.asLiteral()
							.toString()
							.replace(
									"^^http://www.w3.org/2001/XMLSchema#string",
									"");

				// hasStatus
				if (in.getPropertyValue(hasStatus) != null)
					status = in
							.getPropertyValue(hasStatus)
							.asLiteral()
							.toString()
							.replace(
									"^^http://www.w3.org/2001/XMLSchema#string",
									"");

				// get disabilities
				if (in.getPropertyResourceValue(user_has_Disability) != null) {
					StmtIterator it2 = in.listProperties(user_has_Disability);

					while (it2.hasNext()) {

						StatementImpl st1 = (StatementImpl) it2.next();
						Resource r1 = st1.getResource();
						Individual disabilityInd = userOntologyModel
								.getIndividual(r1.getURI());

						if (disabilityInd.getPropertyValue(hasName) != null) {
							name = disabilityInd
									.getPropertyValue(hasName)
									.asLiteral()
									.toString()
									.replace(
											"^^http://www.w3.org/2001/XMLSchema#string",
											"");

						}

						if (!name.isEmpty())
							disabilities.put(name);
						name = "";
					}
				}

				// get functional limitations
				if (in.getPropertyResourceValue(user_linksTo_FunctionalLimitations) != null) {
					StmtIterator it2 = in
							.listProperties(user_linksTo_FunctionalLimitations);

					while (it2.hasNext()) {

						StatementImpl st1 = (StatementImpl) it2.next();
						Resource r1 = st1.getResource();
						Individual functionalLimitInd = userOntologyModel
								.getIndividual(r1.getURI());

						if (functionalLimitInd.getPropertyValue(hasName) != null) {
							name = functionalLimitInd
									.getPropertyValue(hasName)
									.asLiteral()
									.toString()
									.replace(
											"^^http://www.w3.org/2001/XMLSchema#string",
											"");

						}

						if (!name.isEmpty())
							functionalLimitations.put(name);
						name = "";
					}
				}

				// get impairments
				if (in.getPropertyResourceValue(user_linksTo_Impairment) != null) {
					StmtIterator it2 = in
							.listProperties(user_linksTo_Impairment);

					while (it2.hasNext()) {

						StatementImpl st1 = (StatementImpl) it2.next();
						Resource r1 = st1.getResource();
						Individual impairmentInd = userOntologyModel
								.getIndividual(r1.getURI());

						if (impairmentInd.getPropertyValue(hasName) != null) {
							name = impairmentInd
									.getPropertyValue(hasName)
									.asLiteral()
									.toString()
									.replace(
											"^^http://www.w3.org/2001/XMLSchema#string",
											"");

						}

						if (!name.isEmpty())
							impairments.put(name);
						name = "";
					}
				}
				jsonOutput.put("firstName", firstName);
				jsonOutput.put("lastName", lastName);
				jsonOutput.put("location", location);
				jsonOutput.put("phoneNumber", phoneNumber);
				jsonOutput.put("status", status);
				jsonOutput.put("disabilities", disabilities);
				jsonOutput.put("functionalLimitations", functionalLimitations);
				jsonOutput.put("impairments", impairments);

				return jsonOutput;

			}

		}

		jsonOutput.put("Result", "Requested User does not exist");
		return jsonOutput;
	}

	public JSONObject getConnectedUsers(String username) throws JSONException {

		JSONObject jsonOutput = new JSONObject();

		if (userOntologyModel == null)
			userOntologyModel = populateOntologyModel(
					MatchmakerManager.getInstance().userOntology,
					userOntologyModel);

		JSONArray connectedUsers = new JSONArray();
		JSONObject temp = new JSONObject();

		DatatypeProperty hasPassword = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasPassword");

		DatatypeProperty hasName = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasName");
		DatatypeProperty hasUsername = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasUsername");
		DatatypeProperty hasFirstName = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasFirstName");
		DatatypeProperty hasLastName = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasLastName");
		DatatypeProperty hasLocation = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasLocation");
		DatatypeProperty hasPhoneNumber = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasPhoneNumber");
		DatatypeProperty hasStatus = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasStatus");

		ObjectProperty user_linksTo_Impairment = userOntologyModel
				.getObjectProperty(USERS_NS + "User_linksTo_Impairment");

		ObjectProperty user_linksTo_FunctionalLimitations = userOntologyModel
				.getObjectProperty(USERS_NS
						+ "User_linksTo_FunctionalLimitation");

		ObjectProperty user_has_Disability = userOntologyModel
				.getObjectProperty(USERS_NS + "User_has_Disability");

		ObjectProperty approvedConnected = userOntologyModel
				.getObjectProperty(USERS_NS + "approvedConnected");

		String email = "";
		String firstName = "";
		String userUsername = "";
		String lastName = "";
		String location = "";
		String phoneNumber = "";
		String status = "";
		String name = "";
		String ontPassword = "";
		JSONArray disabilities = new JSONArray();
		JSONArray impairments = new JSONArray();
		JSONArray functionalLimitations = new JSONArray();

		OntClass subProfileClass = userOntologyModel.getOntClass(USERS_NS
				+ "SubProfile");

		System.out.println("Subprofile class found");

		Iterator it = subProfileClass.listInstances();
		while (it.hasNext()) {
			IndividualImpl in = (IndividualImpl) it.next();

			// hasUsername
			if (in.getPropertyValue(hasUsername) != null)
				email = in
						.getPropertyValue(hasUsername)
						.asLiteral()
						.toString()
						.replace("^^http://www.w3.org/2001/XMLSchema#string",
								"");

			if (in.getPropertyValue(hasPassword) != null)
				ontPassword = in
						.getPropertyValue(hasPassword)
						.asLiteral()
						.toString()
						.replace("^^http://www.w3.org/2001/XMLSchema#string",
								"");

			if (email.equals(username)) {

				// get approved connected users
				if (in.getPropertyResourceValue(approvedConnected) != null) {
					StmtIterator it2 = in.listProperties(approvedConnected);
					while (it2.hasNext()) {

						StatementImpl st1 = (StatementImpl) it2.next();
						Resource r1 = st1.getResource();
						Individual connectedInd = userOntologyModel
								.getIndividual(r1.getURI());

						// hasFirstName
						if (connectedInd.getPropertyValue(hasFirstName) != null)
							firstName = connectedInd
									.getPropertyValue(hasFirstName)
									.asLiteral()
									.toString()
									.replace(
											"^^http://www.w3.org/2001/XMLSchema#string",
											"");
						// hasLastName
						if (connectedInd.getPropertyValue(hasLastName) != null)
							lastName = connectedInd
									.getPropertyValue(hasLastName)
									.asLiteral()
									.toString()
									.replace(
											"^^http://www.w3.org/2001/XMLSchema#string",
											"");

						if (connectedInd.getPropertyValue(hasUsername) != null)
							userUsername = connectedInd
									.getPropertyValue(hasUsername)
									.asLiteral()
									.toString()
									.replace(
											"^^http://www.w3.org/2001/XMLSchema#string",
											"");
						// hasLocation
						if (connectedInd.getPropertyValue(hasLocation) != null)
							location = connectedInd
									.getPropertyValue(hasLocation)
									.asLiteral()
									.toString()
									.replace(
											"^^http://www.w3.org/2001/XMLSchema#string",
											"");

						// hasPhoneNumber
						if (connectedInd.getPropertyValue(hasPhoneNumber) != null)
							phoneNumber = connectedInd
									.getPropertyValue(hasPhoneNumber)
									.asLiteral()
									.toString()
									.replace(
											"^^http://www.w3.org/2001/XMLSchema#string",
											"");

						// hasStatus
						if (connectedInd.getPropertyValue(hasStatus) != null)
							status = connectedInd
									.getPropertyValue(hasStatus)
									.asLiteral()
									.toString()
									.replace(
											"^^http://www.w3.org/2001/XMLSchema#string",
											"");

						// get disabilities
						if (connectedInd
								.getPropertyResourceValue(user_has_Disability) != null) {
							StmtIterator it3 = connectedInd
									.listProperties(user_has_Disability);

							while (it3.hasNext()) {

								StatementImpl st3 = (StatementImpl) it3.next();
								Resource r3 = st3.getResource();
								Individual disabilityInd = userOntologyModel
										.getIndividual(r3.getURI());

								if (disabilityInd.getPropertyValue(hasName) != null) {
									name = disabilityInd
											.getPropertyValue(hasName)
											.asLiteral()
											.toString()
											.replace(
													"^^http://www.w3.org/2001/XMLSchema#string",
													"");

								}

								if (!name.isEmpty())
									disabilities.put(name);
								name = "";
							}
						}

						// get functional limitations
						if (connectedInd
								.getPropertyResourceValue(user_linksTo_FunctionalLimitations) != null) {
							StmtIterator it3 = connectedInd
									.listProperties(user_linksTo_FunctionalLimitations);

							while (it3.hasNext()) {

								StatementImpl st3 = (StatementImpl) it3.next();
								Resource r3 = st3.getResource();
								Individual functionalLimitInd = userOntologyModel
										.getIndividual(r3.getURI());

								if (functionalLimitInd
										.getPropertyValue(hasName) != null) {
									name = functionalLimitInd
											.getPropertyValue(hasName)
											.asLiteral()
											.toString()
											.replace(
													"^^http://www.w3.org/2001/XMLSchema#string",
													"");

								}

								if (!name.isEmpty())
									functionalLimitations.put(name);
								name = "";
							}
						}

						// get impairments
						if (connectedInd
								.getPropertyResourceValue(user_linksTo_Impairment) != null) {
							StmtIterator it3 = connectedInd
									.listProperties(user_linksTo_Impairment);

							while (it3.hasNext()) {

								StatementImpl st3 = (StatementImpl) it3.next();
								Resource r3 = st3.getResource();
								Individual impairmentInd = userOntologyModel
										.getIndividual(r3.getURI());

								if (impairmentInd.getPropertyValue(hasName) != null) {
									name = impairmentInd
											.getPropertyValue(hasName)
											.asLiteral()
											.toString()
											.replace(
													"^^http://www.w3.org/2001/XMLSchema#string",
													"");

								}

								if (!name.isEmpty())
									impairments.put(name);
								name = "";
							}
						}
						temp = new JSONObject();
						
						System.out.println(connectedInd.getOntClass().toString());
						
						if (connectedInd.getOntClass().toString().contains("AssistedPersonSubProfile")) {
							temp.put("firstName", firstName);
							temp.put("lastName", lastName);
							temp.put("username", userUsername);
							temp.put("location", location);
							temp.put("phoneNumber", phoneNumber);
							temp.put("status", status);
							temp.put("disabilities", disabilities);
							temp.put("functionalLimitations",
									functionalLimitations);
							temp.put("impairments", impairments);
							connectedUsers.put(temp);
							disabilities = new JSONArray();
							impairments = new JSONArray();
							functionalLimitations = new JSONArray();

							firstName = "";
							lastName = "";
							userUsername = "";
							location = "";
							phoneNumber = "";
							status = "";
							name = "";
						}

					}
				}

				jsonOutput.put("connectedUsers", connectedUsers);

				return jsonOutput;

			}

		}

		jsonOutput.put("Result", "User does not exist");
		return jsonOutput;
	}

	public boolean checkIfUserIsCaregiver(String username) {

		if (userOntologyModel == null)
			userOntologyModel = populateOntologyModel(
					MatchmakerManager.getInstance().userOntology,
					userOntologyModel);

		DatatypeProperty hasUsername = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasUsername");

		String ontologyEmail = "";

		OntClass subProfileClass = userOntologyModel.getOntClass(USERS_NS
				+ "CaregiverSubProfile");

		Iterator it = subProfileClass.listInstances();
		while (it.hasNext()) {
			IndividualImpl in = (IndividualImpl) it.next();

			// hasUsername
			if (in.getPropertyValue(hasUsername) != null) {
				ontologyEmail = in
						.getPropertyValue(hasUsername)
						.asLiteral()
						.toString()
						.replace("^^http://www.w3.org/2001/XMLSchema#string",
								"");
				System.out.println(ontologyEmail);
			}

			if (ontologyEmail.equals(username)) {

				System.out.println(ontologyEmail);

				return true;

			}

		}

		return false;

	}

	public String checkIfUserExists(String username, String password) {

		if (username == null || username.isEmpty())
			return userIsNotRegistered;

		if (password == null || password.isEmpty())
			password = "";

		if (userOntologyModel == null)
			userOntologyModel = populateOntologyModel(
					MatchmakerManager.getInstance().userOntology,
					userOntologyModel);

		DatatypeProperty hasUsername = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasUsername");
		DatatypeProperty hasPassword = userOntologyModel
				.getDatatypeProperty(USERS_NS + "hasPassword");
		String ontUsername = "";
		String ontPassword = "";

		OntClass subProfileClass = userOntologyModel.getOntClass(USERS_NS
				+ "SubProfile");

		Iterator it = subProfileClass.listInstances();
		while (it.hasNext()) {
			IndividualImpl in = (IndividualImpl) it.next();

			// hasUsername
			if (in.getPropertyValue(hasUsername) != null) {
				ontUsername = in
						.getPropertyValue(hasUsername)
						.asLiteral()
						.toString()
						.replace("^^http://www.w3.org/2001/XMLSchema#string",
								"");
				System.out.println(ontUsername);
			}

			// hasPassword
			if (in.getPropertyValue(hasPassword) != null) {
				ontPassword = in
						.getPropertyValue(hasPassword)
						.asLiteral()
						.toString()
						.replace("^^http://www.w3.org/2001/XMLSchema#string",
								"");
				System.out.println(ontPassword);
			}

			if (ontUsername.equals(username)) {

				if (ontPassword.equals(password)) {

					System.out.println(ontUsername);
					System.out.println(ontPassword);
					return userIsRegistered;

				}
				{
					return wrongPassword;
				}
			}

		}

		return userIsNotRegistered;
	}

	public OntModel populateOntologyModel(String ontology,
			OntModel ontologyModel) {
		InputStream in = null;
		ontologyModel = ModelFactory.createOntologyModel();
		try {
			in = new FileInputStream(System.getProperty("user.dir")
					+ MatchmakerManager.getInstance().WEBINF_PATH + ontology);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		ontologyModel.read(in, "RDF/XML");
		System.out.println("ontology loaded");
		return ontologyModel;

	}

	public void populateInitialModel(String transIn, String[] semantics) {

		// read the json-ld input
		InputStream is = new ByteArrayInputStream(transIn.getBytes());
		_dmodel = ModelFactory.createOntologyModel().read(is, null, "JSON-LD");

		InputStream in = null;

		try {
			// i=2 (merge only userOntology and serviceOntology)
			for (int i = 0; i < 2; i++) {
				in = new FileInputStream(System.getProperty("user.dir")
						+ MatchmakerManager.getInstance().WEBINF_PATH
						+ semantics[i]);
				_dmodel.read(in, "RDF/XML");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void populateInitialModelForRecomm(String transIn, String jsonldInput) {

		// read the json-ld input
		InputStream is = new ByteArrayInputStream(transIn.getBytes());
		InputStream isforJsonLd = new ByteArrayInputStream(
				jsonldInput.getBytes());
		_dmodel = ModelFactory.createOntologyModel().read(is, null, "JSON-LD");
		_dmodel.read(isforJsonLd, null, "JSON-LD");

	}

	public void saveOntologyModel(String ontologyPath, String ontologyName,
			OntModel ontologyModel) {
		try {
			System.out.println("saveToOWL is called..");

			DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd(HH_mm)");
			Calendar cal = Calendar.getInstance();
			System.out.println(dateFormat.format(cal.getTime()));
			String date = String.valueOf(dateFormat.format(cal.getTime()));

			System.out.println(System.getProperty("user.dir")
					+ MatchmakerManager.getInstance().WEBINF_PATH
					+ ontologyPath);

			String realPath = System.getProperty("user.dir")
					+ MatchmakerManager.getInstance().WEBINF_PATH
					+ ontologyPath;

			// create a new backup file
			String path = realPath.replace("\\" + ontologyName, "");

			path = path.replace(ontologyName, "");

			String newFileName = path + "\\" + "userOntology" + "_" + date
					+ ".owl";

			System.out.println(newFileName);

			File file = new File(newFileName);

			OutputStream out = new FileOutputStream(file);

			if (out != null && !out.toString().isEmpty()) {
				ontologyModel.write(out, "RDF/XML-ABBREV"); // readable
															// rdf/xml
			}
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
