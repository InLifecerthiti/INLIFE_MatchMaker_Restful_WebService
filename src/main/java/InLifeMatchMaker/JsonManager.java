package InLifeMatchMaker;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import ontologyClasses.Answer;
import ontologyClasses.AssistedPerson;
import ontologyClasses.Caregiver;
import ontologyClasses.ConnectionDetails;
import ontologyClasses.Connections;
import ontologyClasses.Disability;
import ontologyClasses.Disease;
import ontologyClasses.Education;
import ontologyClasses.FunctionalLimitation;
import ontologyClasses.Impairment;
import ontologyClasses.Login;
import ontologyClasses.MaritalStatus;
import ontologyClasses.Message;
import ontologyClasses.Ontology;
import ontologyClasses.Parameter;
import ontologyClasses.ParameterTerm;
import ontologyClasses.Profile;
import ontologyClasses.Provider;
import ontologyClasses.Question;
import ontologyClasses.Questionnaire;
import ontologyClasses.ServiceModel;
import ontologyClasses.Status;
import ontologyClasses.Tool;
import ontologyClasses.ToolCategory;
import ontologyClasses.Usage;
import ontologyClasses.UserActivity;
import ontologyClasses.UserAnswer;

import com.github.jsonldjava.jena.JenaJSONLD;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.impl.IndividualImpl;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.rdf.model.impl.StatementImpl;

public class JsonManager {
	// ------users ----------
	public HashMap<String, AssistedPerson> assistedPeopleHashmap_byUsername = new HashMap<String, AssistedPerson>();
	public HashMap<String, AssistedPerson> assistedPeopleHashmap_byUri = new HashMap<String, AssistedPerson>();
	public HashMap<String, Provider> providersHashmap_byUri = new HashMap<String, Provider>();
	public HashMap<String, Provider> providersHashmap_byUsername = new HashMap<String, Provider>();
	public HashMap<String, Caregiver> caregiversHashmap_byUsername = new HashMap<String, Caregiver>();
	public HashMap<String, Caregiver> caregiversHashmap_byUri = new HashMap<String, Caregiver>();

	// -------------------------
	public HashMap<String, Disability> disabilities = new HashMap<String, Disability>();
	public HashMap<String, Impairment> impairments = new HashMap<String, Impairment>();
	public HashMap<String, FunctionalLimitation> functionalLimitations = new HashMap<String, FunctionalLimitation>();
	public HashMap<String, Disease> diseases = new HashMap<String, Disease>();
	public HashMap<String, Education> education = new HashMap<String, Education>();
	public HashMap<String, MaritalStatus> maritalStatuses = new HashMap<String, MaritalStatus>();

	public HashMap<String, ConnectionDetails> connectionDetails = new HashMap<String, ConnectionDetails>();
	public HashMap<String, ServiceModel> serviceModels = new HashMap<String, ServiceModel>();
	public HashMap<String, Parameter> parameters = new HashMap<String, Parameter>();
	public HashMap<String, ParameterTerm> parameterTerms = new HashMap<String, ParameterTerm>();
	public HashMap<String, Status> statuses = new HashMap<String, Status>();
	public HashMap<String, UserActivity> userActivities = new HashMap<String, UserActivity>();
	public HashMap<String, Message> messages = new HashMap<String, Message>();
	public HashMap<String, Usage> usages = new HashMap<String, Usage>();
	public HashMap<String, Answer> answers = new HashMap<String, Answer>();
	public HashMap<String, Login> logins = new HashMap<String, Login>();
	public HashMap<String, Question> questions = new HashMap<String, Question>();
	public HashMap<String, Questionnaire> questionnaires = new HashMap<String, Questionnaire>();
	public HashMap<String, UserAnswer> userAnswers = new HashMap<String, UserAnswer>();
	public HashMap<String, ArrayList<Tool>> toolsByCategory = new HashMap<String, ArrayList<Tool>>();
	public HashMap<String, Tool> toolsByUri = new HashMap<String, Tool>();

	// new users
	public ArrayList<AssistedPerson> assistedPeople = new ArrayList<AssistedPerson>();
	public ArrayList<Caregiver> caregivers = new ArrayList<Caregiver>();

	private static JsonManager instance = null;

	public static void main(String args[]) throws IOException {
		String input = Utils
				.readFile("C:\\Users\\konstadinidou\\inlifeData.json");
		// System.out.println(input);
		JsonManager.getInstance().parseJson(input);
	}

	private JsonManager() {

		// JenaJSONLD must be initialized so that the readers and writers are
		// registered with Jena.
		JenaJSONLD.init();
	}

	public static JsonManager getInstance() {
		if (instance == null)
			instance = new JsonManager();
		return instance;
	}

	public void parseJson(String inputJson) {

		GsonBuilder gsonBuilder = Utils.getGsonBuilder();

		Gson gson = gsonBuilder.create();

		Type type = new TypeToken<Ontology>() {
		}.getType();

		// System.out.println(inputJson.toString());

		Ontology allRecords = (Ontology) gson.fromJson(inputJson, type);

		System.out.println(allRecords);

		// this method initializes all hash maps with objects of the json
		initializeMaps(allRecords);

	}

	public void initializeMaps(Ontology ontology) {

		assistedPeople = new ArrayList<AssistedPerson>();
		caregivers = new ArrayList<Caregiver>();
		// assisted people by username, by uri
		this.assistedPeopleHashmap_byUsername = new HashMap<String, AssistedPerson>();
		this.assistedPeopleHashmap_byUri = new HashMap<String, AssistedPerson>();
		for (AssistedPerson temp : ontology.getAssistedPeople()) {
			this.assistedPeopleHashmap_byUsername.put(temp.getProfile()
					.getHasUsername(), temp);
			this.assistedPeopleHashmap_byUri.put(temp.getProfile().getUri(),
					temp);
		}

		// providers by uri
		this.providersHashmap_byUri = new HashMap<String, Provider>();
		this.providersHashmap_byUsername = new HashMap<String, Provider>();
		System.out.println(ontology.getProviders().toString());
		for (Provider temp : ontology.getProviders()) {
			this.providersHashmap_byUsername.put(temp.getProfile()
					.getHasUsername(), temp);
			this.providersHashmap_byUri.put(temp.getProfile().getUri(), temp);
		}

		// caregivers by uri, by username
		this.caregiversHashmap_byUsername = new HashMap<String, Caregiver>();
		this.caregiversHashmap_byUri = new HashMap<String, Caregiver>();

		for (Caregiver temp : ontology.getCaregivers()) {
			this.caregiversHashmap_byUsername.put(temp.getProfile()
					.getHasUsername(), temp);
			this.caregiversHashmap_byUri.put(temp.getProfile().getUri(), temp);
		}

		this.disabilities = new HashMap<String, Disability>();
		for (Disability temp : ontology.getDisabilities()) {
			this.disabilities.put(temp.getUri(), temp);
		}

		this.impairments = new HashMap<String, Impairment>();
		for (Impairment temp : ontology.getImpairments()) {
			this.impairments.put(temp.getUri(), temp);
		}

		this.functionalLimitations = new HashMap<String, FunctionalLimitation>();
		for (FunctionalLimitation temp : ontology.getFunctionalLimitations()) {
			this.functionalLimitations.put(temp.getUri(), temp);
		}

		this.diseases = new HashMap<String, Disease>();
		for (Disease temp : ontology.getDiseases()) {
			this.diseases.put(temp.getUri(), temp);
		}

		this.education = new HashMap<String, Education>();
		for (Education temp : ontology.getEducation()) {
			this.education.put(temp.getUri(), temp);
		}

		this.maritalStatuses = new HashMap<String, MaritalStatus>();
		for (MaritalStatus temp : ontology.getMaritalStatuses()) {
			this.maritalStatuses.put(temp.getUri(), temp);
		}

		this.connectionDetails = new HashMap<String, ConnectionDetails>();
		for (ConnectionDetails temp : ontology.getConnectionDetails()) {
			this.connectionDetails.put(temp.getUri(), temp);
		}

		this.serviceModels = new HashMap<String, ServiceModel>();
		for (ServiceModel temp : ontology.getServiceModels()) {
			this.serviceModels.put(temp.getUri(), temp);
		}

		this.parameters = new HashMap<String, Parameter>();
		for (Parameter temp : ontology.getParameters()) {
			JsonManager.getInstance().parameters.put(temp.getUri(), temp);
		}

		this.parameterTerms = new HashMap<String, ParameterTerm>();
		for (ParameterTerm temp : ontology.getParameterTerms()) {
			JsonManager.getInstance().parameterTerms.put(temp.getUri(), temp);
		}

		this.statuses = new HashMap<String, Status>();
		for (Status temp : ontology.getStatus()) {
			JsonManager.getInstance().statuses.put(temp.getUri(), temp);
		}

		this.userActivities = new HashMap<String, UserActivity>();
		for (UserActivity temp : ontology.getUserActivities()) {
			this.userActivities.put(temp.getUri(), temp);
		}

		this.messages = new HashMap<String, Message>();
		for (Message temp : ontology.getMessages()) {
			this.messages.put(temp.getUri(), temp);
		}

		this.usages = new HashMap<String, Usage>();
		for (Usage temp : ontology.getUsage()) {
			this.usages.put(temp.getUri(), temp);
		}

		this.answers = new HashMap<String, Answer>();
		for (Answer temp : ontology.getAnswers()) {
			this.answers.put(temp.getUri(), temp);
		}

		this.logins = new HashMap<String, Login>();
		for (Login temp : ontology.getLogin()) {
			this.logins.put(temp.getUri(), temp);
		}

		this.questions = new HashMap<String, Question>();
		for (Question temp : ontology.getQuestions()) {
			this.questions.put(temp.getUri(), temp);
		}

		this.questionnaires = new HashMap<String, Questionnaire>();
		for (Questionnaire temp : ontology.getQuestionnaire()) {
			this.questionnaires.put(temp.getUri(), temp);
		}

		this.userAnswers = new HashMap<String, UserAnswer>();
		for (UserAnswer temp : ontology.getUserAnswers()) {
			this.userAnswers.put(temp.getUri(), temp);
		}

		this.toolsByCategory = new HashMap<String, ArrayList<Tool>>();
		this.toolsByUri = new HashMap<String, Tool>();

		for (ToolCategory temp : ontology.getAllTools()) {
			this.toolsByCategory.put(temp.getToolCategory(), temp.getTools());
			for (Tool tempTool : temp.getTools()) {
				this.toolsByUri.put(tempTool.getUri(), tempTool);
			}
		}

		System.out.println("Initialization of hashmaps is done");
	}

	public String getInputs(String inputString, String serviceToBeCalled)
			throws Exception {
		JSONTokener inputTokener = new JSONTokener(inputString);
		String currentUserExists = "";
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

		if (authorizedSource.equals(OntologyManager.authorizedAccess)) {

			if (serviceToBeCalled.equals(WebServices.isUserRegistered)) {

				currentUserExists = checkIfUserExists(currentUsername,
						currentUserPassword);

				if (currentUserExists.equals(OntologyManager.userIsRegistered)) {
					returnedObject.put("Result", true);
					returnedObject.put("ResultDescription",
							OntologyManager.currentUserIsRegistered);
				} else if (currentUserExists
						.equals(OntologyManager.userIsNotRegistered)) {
					returnedObject.put("Result", false);
					returnedObject.put("ResultDescription",
							OntologyManager.currentUserIsNotRegistered);
				} else {
					returnedObject.put("Result", false);
					returnedObject.put("ResultDescription",
							OntologyManager.wrongPasswordForCurrentUser);
				}

			} else if (serviceToBeCalled.equals(WebServices.userIsAuthorized)) {

				result = methodForCompletedUserConnection(currentUsername,
						currentUserPassword, requestedUsername);
				returnedObject.put("ResultDescription", result);
				if (result.equals(OntologyManager.usersAreConnected))
					returnedObject.put("Result", true);
				else
					returnedObject.put("Result", false);

			} else if (serviceToBeCalled
					.equals(WebServices.userIsRelatedToCaregiver)) {
				result = methodForUserConnection(currentUsername,
						requestedUsername);

				returnedObject.put("ResultDescription", result);

				if (result.equals(OntologyManager.usersAreConnected))
					returnedObject.put("Result", true);
				else
					returnedObject.put("Result", false);

			} else if (serviceToBeCalled.equals(WebServices.isCaregiver)) {

				result = checkIfUserNameExists(currentUsername);

				if (result.equals(OntologyManager.userNameExists)) {
					isCaregiver = checkIfUserIsCaregiver(currentUsername);
					returnedObject.put("Result", isCaregiver);

					if (isCaregiver)
						returnedObject.put("ResultDescription",
								OntologyManager.userIsCaregiver);
					else
						returnedObject.put("ResultDescription",
								OntologyManager.userIsNotCaregiver);

				} else {
					returnedObject.put("Result", false);
					returnedObject.put("ResultDescription",
							OntologyManager.currentUserIsNotRegistered);
				}

			} else if (serviceToBeCalled.equals(WebServices.getUser)) {

				result = methodForCompletedUserConnection(currentUsername,
						currentUserPassword, requestedUsername);

				if (result.contains(OntologyManager.usersAreConnected)) {
					temp = getOntologyUser(requestedUsername);
					returnedObject.put("ResultDescription",
							OntologyManager.getUser);
					returnedObject.put("Result", temp);
				} else {
					returnedObject.put("Result", false);
					returnedObject.put("ResultDescription", result);
				}

			} else if (serviceToBeCalled
					.equals(WebServices.getConnectedCaregivers)) {

				result = methodForCompletedUserConnection(currentUsername,
						currentUserPassword, requestedUsername);

				if (result.contains(OntologyManager.usersAreConnected)) {
					returnedObject.put("ResultDescription",
							OntologyManager.getConnectedCaregivers);
					temp = getOntologyCaregivers(requestedUsername);
					returnedObject.put("Result", temp);
				} else {
					returnedObject.put("Result", false);
					returnedObject.put("ResultDescription", result);
				}

			} else if (serviceToBeCalled.equals(WebServices.getConnectedUsers)) {

				result = checkIfUserExists(currentUsername, currentUserPassword);

				if (result.equals(OntologyManager.userIsRegistered)) {

					returnedObject.put("ResultDescription",
							OntologyManager.getConnectedUsers);
					temp = getConnectedUsers(currentUsername);
					returnedObject.put("Result", temp);

				} else if (result.equals(OntologyManager.wrongPassword)) {
					returnedObject.put("Result", false);
					returnedObject.put("ResultDescription",
							OntologyManager.wrongPasswordForCurrentUser);
				} else {
					returnedObject.put("Result", false);
					returnedObject.put("ResultDescription",
							OntologyManager.currentUserIsNotRegistered);
				}

			} else if (serviceToBeCalled
					.equals(WebServices.getLastUseOfService)
					|| serviceToBeCalled
							.equals(WebServices.getNumberOfUsesOfService)) {

				result = methodForCompletedUserConnection(currentUsername,
						currentUserPassword, requestedUsername);

				if (result.contains(OntologyManager.usersAreConnected)) {

					// check if service name exists
					result = checkIfServiceExists(serviceName);

					if (result.equals(OntologyManager.serviceExists)) {

						// get the service usage
						temp = getServiceUsage(requestedUsername, serviceName);

						if (serviceToBeCalled
								.equals(WebServices.getLastUseOfService)) {
							returnedObject.put("ResultDescription",
									OntologyManager.getLastUseOfService);
							temp.remove("numberOfUses");
						} else {
							returnedObject.put("ResultDescription",
									OntologyManager.getNumberOfUsesOfService);
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

				if (result.equals(OntologyManager.userIsRegisteredSuccessfully))
					returnedObject.put("Result", true);
				else
					returnedObject.put("Result", false);

				// after creating user send the data to the IN LIFE APP CENTRE

				// 1. create the object
				String userToSend = createJsonOfNewUsers();

				// 2. post it to the IN LIFE APP CENTRE
				Utils.postNewUsers(userToSend);

			}
		} else {

			if (authorizedSource.equals(OntologyManager.toolIsNotRegistered)) {
				returnedObject.put("Result", false);
				returnedObject.put("ResultDescription",
						OntologyManager.toolIsNotRegistered);
			} else {
				returnedObject.put("Result", false);
				returnedObject.put("ResultDescription",
						OntologyManager.wrongPasswordForTool);
			}

		}

		// format json to return and add response code

		returnedObject = OntologyManager.addResultCodeToJSON(returnedObject);

		Utils.writeFile(MatchmakerManager.rbmmJsonOutputFilepath,
				Utils.jsonPrettyPrint(returnedObject.toString(5)));

		return returnedObject.toString(5);
	}

	public String methodForCompletedUserConnection(String currentUsername,
			String currentUserPassword, String requestedUsername)
			throws JSONException {

		String result = "";
		String currentUserExists = "", requestedUserExists = "";
		currentUserExists = checkIfUserExists(currentUsername,
				currentUserPassword);

		if (currentUserExists.equals(OntologyManager.userIsRegistered)) {

			System.out.println("Current user:" + currentUserExists);
			requestedUserExists = checkIfUserNameExists(requestedUsername);

			if (requestedUserExists.equals(OntologyManager.userNameExists)) {
				// check the connection if both users exist
				System.out.println("Requested user:" + requestedUserExists);
				if (requestedUsername.equals(currentUsername))
					return OntologyManager.usersAreConnected;
				result = checkIfUsersAreConnected(requestedUsername,
						currentUsername, currentUserPassword);

			} else {
				System.out.println("Requested user:" + requestedUserExists);
				if (requestedUserExists
						.equals(OntologyManager.userNameNotExist)) {

					result = OntologyManager.requestedUserIsNotRegistered;
				}

			}

		} else {

			System.out.println("Current user:" + currentUserExists);
			if (currentUserExists.equals(OntologyManager.userIsNotRegistered)) {

				result = OntologyManager.currentUserIsNotRegistered;
			} else {

				result = OntologyManager.wrongPasswordForCurrentUser;
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

		if (currentUserExists.equals(OntologyManager.userNameExists)) {

			System.out.println("Current user:" + currentUserExists);
			requestedUserExists = checkIfUserNameExists(requestedUsername);

			if (requestedUserExists.equals(OntologyManager.userNameExists)) {
				// check the connection if both users exist
				System.out.println("Requested user:" + requestedUserExists);
				result = checkIfUserIsRelatedToCaregiver(requestedUsername,
						currentUsername);

			} else {
				System.out.println("Requested user:" + requestedUserExists);
				if (requestedUserExists
						.equals(OntologyManager.userNameNotExist))
					result = OntologyManager.requestedUserIsNotRegistered;

			}

		} else {

			System.out.println("Current user:" + currentUserExists);
			result = OntologyManager.currentUserIsNotRegistered;

		}

		return result;
	}

	public String checkIfUsersAreConnected(String requestedUser,
			String currentUser, String password) throws JSONException {

		// currentUser, password --> caregiver, requestedUser--> elderly

		AssistedPerson assisted = (AssistedPerson) assistedPeopleHashmap_byUsername
				.get(requestedUser);
		Caregiver caregiver = (Caregiver) caregiversHashmap_byUsername
				.get(currentUser);

		if (assisted != null && caregiver != null) {

			for (String s : caregiver.getConnections().getApprovedConnected()) {
				if (s.equals(assisted.getProfile().getUri()))
					return OntologyManager.usersAreConnected;
			}
		}

		return OntologyManager.usersAreNotConnected;
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
				if (s.equals(OntologyManager.userNameNotExist)) {
					addCaregiverProfileToOntology(userToCreateUsername,
							userToCreatePassword, userToCreateRole);
					System.out.println("caregiver is created "
							+ userToCreateUsername);
					return OntologyManager.userIsRegisteredSuccessfully;
				} else {
					return OntologyManager.userToCreateUsernameExists;
				}

			} else if (userToCreateRole.equals("AssistedPerson")) {
				// B. case of a new assisted person
				String s = checkIfUserNameExists(userToCreateUsername);
				if (s.equals(OntologyManager.userNameNotExist)) {
					addAssistedPersonProfileToOntology(userToCreateUsername,
							userToCreatePassword);
					System.out.println("assisted person is created "
							+ userToCreateUsername);
					return OntologyManager.userIsRegisteredSuccessfully;
				} else {
					return OntologyManager.userToCreateUsernameExists;
				}

			} else {
				return OntologyManager.emptyRole;
			}

		} else {

			// C. case where a caregiver enrolls an assisted person

			// 1. check if caregiver exists
			currentUserExists = checkIfUserExists(currentUsername,
					currentUserPassword);
			// 2.check the role for which the account will be created
			if (currentUserExists.equals(OntologyManager.userIsRegistered)) {
				String s = checkIfUserNameExists(userToCreateUsername);
				if (s.equals(OntologyManager.userNameNotExist)) {

					// 3. create account for assisted
					AssistedPerson assisted = addAssistedPersonProfileToOntology(
							userToCreateUsername, userToCreatePassword);

					Caregiver carer = (Caregiver) caregiversHashmap_byUsername
							.get(currentUsername);

					// 4. add connection between assisted and caregiver
					carer.getConnections().getApprovedConnected()
							.add(assisted.getProfile().getUri());
					assisted.getConnections().getApprovedConnected()
							.add(carer.getProfile().getUri());

					// update hashmaps
					caregiversHashmap_byUsername.remove(currentUsername);
					caregiversHashmap_byUsername.put(currentUsername, carer);
					caregiversHashmap_byUri.remove(carer.getProfile().getUri());
					caregiversHashmap_byUri.put(carer.getProfile().getUri(),
							carer);

					assistedPeopleHashmap_byUsername
							.remove(userToCreateUsername);
					assistedPeopleHashmap_byUsername.put(userToCreateUsername,
							assisted);
					assistedPeopleHashmap_byUri.remove(assisted.getProfile()
							.getUri());
					assistedPeopleHashmap_byUri.put(assisted.getProfile()
							.getUri(), assisted);

					System.out
							.println("caregiver case:assisted person is created "
									+ userToCreateUsername);
					return OntologyManager.userIsRegisteredSuccessfully;
				} else {
					return OntologyManager.userToCreateUsernameExists;
				}
			} else if (currentUserExists
					.equals(OntologyManager.userIsNotRegistered)) {
				return OntologyManager.currentUserIsNotRegistered;
			} else {
				return OntologyManager.wrongPasswordForCurrentUser;
			}
		}

	}

	public String checkIfUserNameExists(String username) {

		if (username == null || username.isEmpty())
			return OntologyManager.userNameNotExist;

		AssistedPerson assisted = (AssistedPerson) assistedPeopleHashmap_byUsername
				.get(username);
		if (assisted != null)
			return OntologyManager.userNameExists;

		Caregiver caregiver = (Caregiver) caregiversHashmap_byUsername
				.get(username);
		if (caregiver != null)
			return OntologyManager.userNameExists;

		Provider provider = (Provider) providersHashmap_byUsername
				.get(username);
		if (provider != null)
			return OntologyManager.userNameExists;

		return OntologyManager.userNameNotExist;
	}

	public Caregiver addCaregiverProfileToOntology(String username,
			String password, String type) throws Exception {

		String str = "";
		str = username.replace(" ", "_").replaceAll("[^\\p{L}\\p{Nd}]", "");

		String uri = OntologyManager.USERS_NS + str + "_subprofile";

		Date date = Calendar.getInstance().getTime();

		Profile profile = new Profile(uri, "", "", username, password, "", "",
				date, null, "", null, "");
		Connections connections = new Connections(new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>());
		Caregiver carer = new Caregiver(profile, type, connections);

		addNewCarerToHashMaps(carer);

		return carer;

	}

	private void addNewCarerToHashMaps(Caregiver carer) {

		caregivers.add(carer);
		caregiversHashmap_byUri.put(carer.getProfile().getUri(), carer);
		caregiversHashmap_byUsername.put(carer.getProfile().getHasUsername(),
				carer);
	}

	private void addNewAssistedToHashMaps(AssistedPerson assisted) {

		assistedPeople.add(assisted);
		assistedPeopleHashmap_byUri.put(assisted.getProfile().getUri(),
				assisted);
		assistedPeopleHashmap_byUsername.put(assisted.getProfile()
				.getHasUsername(), assisted);
	}

	public AssistedPerson addAssistedPersonProfileToOntology(String username,
			String password) throws Exception {
		String str = "";
		str = username.replace(" ", "_").replaceAll("[^\\p{L}\\p{Nd}]", "");

		String uri = OntologyManager.USERS_NS + str + "_subprofile";

		Date date = Calendar.getInstance().getTime();

		Profile profile = new Profile(uri, "", "", username, password, "", "",
				date, null, "", null, "");
		Connections connections = new Connections(new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>());
		AssistedPerson assisted = new AssistedPerson(profile, "", "", "", "",
				"", false, "", "", "", "", new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>(), connections, "");

		addNewAssistedToHashMaps(assisted);

		return assisted;

	}

	public String checkIfUserExists(String username, String password) {

		if (username == null || username.isEmpty())
			return OntologyManager.userIsNotRegistered;

		if (password == null || password.isEmpty())
			password = "";

		Iterator it = assistedPeopleHashmap_byUsername.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (pair.getKey().equals(username)) {

				AssistedPerson assisted = (AssistedPerson) pair.getValue();
				if (assisted.getProfile().getHasPassword().equals(password)) {
					System.out.println(username);
					System.out.println(password);
					return OntologyManager.userIsRegistered;
				} else {
					return OntologyManager.wrongPassword;
				}

			}

		}

		it = caregiversHashmap_byUsername.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (pair.getKey().equals(username)) {

				Caregiver caregiver = (Caregiver) pair.getValue();
				if (caregiver.getProfile().getHasPassword().equals(password)) {
					System.out.println(username);
					System.out.println(password);
					return OntologyManager.userIsRegistered;
				} else {
					return OntologyManager.wrongPassword;
				}

			}

		}

		it = providersHashmap_byUsername.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();

			if (pair.getKey().equals(username)) {

				Profile profile = (Profile) pair.getValue();
				if (profile.getHasPassword().equals(password)) {
					System.out.println(username);
					System.out.println(password);
					return OntologyManager.userIsRegistered;
				} else {
					return OntologyManager.wrongPassword;
				}

			}

		}

		return OntologyManager.userIsNotRegistered;
	}

	public boolean checkIfUserIsCaregiver(String username) {

		Iterator it = caregiversHashmap_byUsername.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (pair.getKey().equals(username))
				return true;

		}

		return false;

	}

	public String checkIfServiceExists(String serviceName) {

		if (serviceName == null || serviceName.isEmpty())
			return OntologyManager.serviceNotExist;

		Iterator it = serviceModels.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			ServiceModel service = (ServiceModel) pair.getValue();
			if (service.getServiceHasName().equals(serviceName))
				return OntologyManager.serviceExists;

		}

		return OntologyManager.serviceNotExist;

	}

	public String checkAuthorizationOfSource(String username, String password) {

		if (username == null || username.isEmpty())
			return OntologyManager.toolIsNotRegistered;

		if (password == null || password.isEmpty())
			password = "";

		// check app center
		if (username.equals(OntologyManager.appCenterUsername)
				&& password.equals(OntologyManager.appCenterPassword))
			return OntologyManager.authorizedAccess;
		else if (username.equals(OntologyManager.appCenterUsername)
				&& !password.equals(OntologyManager.appCenterPassword))
			return OntologyManager.wrongPassword;

		System.out.println(this.toolsByUri.size());

		Iterator it = this.toolsByUri.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Tool tool = (Tool) pair.getValue();
			if (tool.getToolHasUsername().equals(username)) {
				if (tool.getToolHasPassword().equals(password))
					return OntologyManager.authorizedAccess;
				else
					return OntologyManager.wrongPasswordForTool;

			}

		}

		return OntologyManager.toolIsNotRegistered;

	}

	public String checkIfUserIsRelatedToCaregiver(String requestedUser,
			String currentUser) throws JSONException {

		AssistedPerson assisted = (AssistedPerson) assistedPeopleHashmap_byUsername
				.get(requestedUser);
		Caregiver caregiver = (Caregiver) caregiversHashmap_byUsername
				.get(currentUser);

		if (assisted != null && caregiver != null) {
			for (String s : assisted.getConnections().getApprovedConnected()) {
				if (s.equals(caregiver.getProfile().getUri()))
					return OntologyManager.usersAreConnected;
			}
		}

		return OntologyManager.usersAreNotConnected;
	}

	public JSONObject getOntologyUser(String username) throws JSONException {

		JSONObject jsonOutput = new JSONObject();

		AssistedPerson assisted = (AssistedPerson) assistedPeopleHashmap_byUsername
				.get(username);
		Caregiver caregiver = (Caregiver) caregiversHashmap_byUsername
				.get(username);
		ArrayList<String> disabilitiesList = new ArrayList<String>();
		ArrayList<String> impairmentsList = new ArrayList<String>();
		ArrayList<String> functionalLimitationsList = new ArrayList<String>();
		ArrayList<JSONObject> diseasesList = new ArrayList<JSONObject>();
		Disability disability = null;
		FunctionalLimitation functionalLimitation = null;
		Impairment impairment = null;
		Disease disease = null;

		// return assisted user
		if (assisted != null) {

			jsonOutput
					.put("firstName", assisted.getProfile().getHasFirstName());
			jsonOutput.put("lastName", assisted.getProfile().getHasLastName());
			jsonOutput.put("birthDate", Utils
					.convertDateToSpecificFormat(assisted.getProfile()
							.getHasBirthDate()));
			jsonOutput.put("username", assisted.getProfile().getHasUsername());
			jsonOutput.put("email", assisted.getProfile().getHasEmail());
			jsonOutput.put("location", assisted.getProfile().getHasLocation());
			jsonOutput.put("phoneNumber", assisted.getProfile()
					.getHasPhoneNumber());
			jsonOutput.put("status", assisted.getHasStatus());
			jsonOutput.put("gender", assisted.getHasGender());
			jsonOutput.put("preferredLanguage", assisted.getProfile()
					.getHasLanguage());

			if (assisted.getUser_has_Disability() != null)
				for (String s : assisted.getUser_has_Disability()) {
					disability = (Disability) this.disabilities.get(s);
					disabilitiesList.add(disability.getHasName());
				}

			if (assisted.getUser_linksTo_FunctionalLimitation() != null)
				for (String s : assisted.getUser_linksTo_FunctionalLimitation()) {
					functionalLimitation = (FunctionalLimitation) this.functionalLimitations
							.get(s);
					functionalLimitationsList.add(functionalLimitation
							.getHasName());
				}

			if (assisted.getUser_linksTo_Impairment() != null)
				for (String s : assisted.getUser_linksTo_Impairment()) {
					impairment = (Impairment) this.impairments.get(s);
					impairmentsList.add(impairment.getHasName());
				}

			if (assisted.getUser_linksTo_Disease() != null) {
				JSONObject diseaseObj = null;
				for (String s : assisted.getUser_linksTo_Disease()) {
					disease = (Disease) this.diseases.get(s);
					diseaseObj = new JSONObject();
					diseaseObj.put("name", disease.getHasName());
					diseaseObj.put("code", disease.getHasCode());
					diseasesList.add(diseaseObj);
				}
			}

			jsonOutput.put("disabilities", disabilitiesList);
			jsonOutput.put("functionalLimitations", functionalLimitationsList);
			jsonOutput.put("impairments", impairmentsList);
			jsonOutput.put("diseases", diseasesList);

			return jsonOutput;

		} // return caregiver
		else if (caregiver != null) {

			jsonOutput.put("firstName", caregiver.getProfile()
					.getHasFirstName());
			jsonOutput.put("lastName", caregiver.getProfile().getHasLastName());
			jsonOutput.put("birthDate", Utils
					.convertDateToSpecificFormat(caregiver.getProfile()
							.getHasBirthDate()));
			jsonOutput.put("username", caregiver.getProfile().getHasUsername());
			jsonOutput.put("email", caregiver.getProfile().getHasEmail());
			jsonOutput.put("location", caregiver.getProfile().getHasLocation());
			jsonOutput.put("phoneNumber", caregiver.getProfile()
					.getHasPhoneNumber());
			jsonOutput.put("type", caregiver.getType());
			return jsonOutput;

		}

		jsonOutput.put("Result", "Requested User does not exist");
		return jsonOutput;
	}

	public JSONObject getOntologyCaregivers(String username)
			throws JSONException {
		JSONObject jsonOutput = new JSONObject();
		JSONArray caregiversArray = new JSONArray();
		JSONObject tempObj = null;

		// username refers to the assisted

		AssistedPerson assisted = (AssistedPerson) assistedPeopleHashmap_byUsername
				.get(username);
		if (assisted != null) {

			for (String s : assisted.getConnections().getApprovedConnected()) {
				Caregiver caregiver = (Caregiver) caregiversHashmap_byUri
						.get(s);
				// add the information to the array
				tempObj = new JSONObject();
				tempObj.put("firstName", caregiver.getProfile()
						.getHasFirstName());
				tempObj.put("lastName", caregiver.getProfile().getHasLastName());
				tempObj.put("phoneNumber", caregiver.getProfile()
						.getHasPhoneNumber());
				tempObj.put("email", caregiver.getProfile().getHasEmail());
				tempObj.put("username", caregiver.getProfile().getHasUsername());
				tempObj.put("type", caregiver.getType());
				caregiversArray.put(tempObj);
			}
			jsonOutput.put("caregivers", caregiversArray);
		}

		return jsonOutput;
	}

	public JSONObject getConnectedUsers(String username) throws JSONException {

		JSONObject jsonOutput = new JSONObject();

		// username is the caregiver

		JSONArray connectedUsers = new JSONArray();
		JSONObject temp = new JSONObject();

		Caregiver caregiver = (Caregiver) caregiversHashmap_byUsername
				.get(username);
		if (caregiver != null) {
			for (String s : caregiver.getConnections().getApprovedConnected()) {
				AssistedPerson assisted = (AssistedPerson) assistedPeopleHashmap_byUri
						.get(s);
				if (assisted != null) {
					temp = getOntologyUser(assisted.getProfile()
							.getHasUsername());
					connectedUsers.put(temp);
				}

			}

			jsonOutput.put("connectedUsers", connectedUsers);

			return jsonOutput;

		}

		jsonOutput.put("Result", "User does not exist");
		return jsonOutput;
	}

	public JSONObject getServiceUsage(String userName, String serviceName)
			throws JSONException, ParseException {
		JSONObject jsonOutput = new JSONObject();
		ArrayList<String> list = new ArrayList<String>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		Iterator it = usages.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Usage usage = (Usage) pair.getValue();
			if (usage.getHasUserId().equals(userName)
					&& usage.getHasServiceName().equals(serviceName)) {
				if (usage.getHasEventDate() != null)
					list.add(df.format(usage.getHasEventDate()));
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

	public String createJsonOfNewUsers() throws JSONException {
		JSONObject object = new JSONObject();

		JSONArray assistedPeopleArray = new JSONArray();
		JSONArray caregiversArray = new JSONArray();
		JSONArray connections = null;
		JSONObject profile = null;
		JSONObject user = null;

		// create json array of assistedPeople
		for (AssistedPerson temp : assistedPeople) {
			user = new JSONObject();
			profile = new JSONObject();
			connections = new JSONArray();
			profile.put("uri", temp.getProfile().getUri());
			profile.put("hasUsername", temp.getProfile().getHasUsername());
			profile.put("hasPassword", temp.getProfile().getHasPassword());
			profile.put("hasInsertDate", Utils.convertDateToSpecificFormat(temp
					.getProfile().getHasInsertDate()));

			for (String s : temp.getConnections().getApprovedConnected()) {
				connections.put(s);
			}

			user.put("profile", profile);
			user.put("approvedConnected", connections);
			assistedPeopleArray.put(user);
		}

		// create json array of caregivers
		for (Caregiver temp : caregivers) {
			user = new JSONObject();
			profile = new JSONObject();
			connections = new JSONArray();
			profile.put("uri", temp.getProfile().getUri());
			profile.put("hasUsername", temp.getProfile().getHasUsername());
			profile.put("hasPassword", temp.getProfile().getHasPassword());
			profile.put("hasInsertDate", Utils.convertDateToSpecificFormat(temp
					.getProfile().getHasInsertDate()));

			for (String s : temp.getConnections().getApprovedConnected()) {
				connections.put(s);
			}

			user.put("profile", profile);
			user.put("type", temp.getType());
			user.put("approvedConnected", connections);
			caregiversArray.put(user);
		}

		object.put("assistedPeople", assistedPeopleArray);
		object.put("caregivers", caregiversArray);

		caregivers = new ArrayList<Caregiver>();
		assistedPeople = new ArrayList<AssistedPerson>();

		return object.toString(5);
	}

}
