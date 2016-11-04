package InLifeMatchMaker;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

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
import ontologyClasses.Parameter;
import ontologyClasses.ParameterTerm;
import ontologyClasses.Profile;
import ontologyClasses.Provider;
import ontologyClasses.Question;
import ontologyClasses.Questionnaire;
import ontologyClasses.ServiceModel;
import ontologyClasses.Status;
import ontologyClasses.Tool;
import ontologyClasses.Usage;
import ontologyClasses.UserActivity;
import ontologyClasses.UserAnswer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;

public class TransformerManager {

	static String defaultNameSpace = "http://inlife.ServiceOntology#";

	public static String transformJsonInputToJsonLd(String in)
			throws JSONException {
		String inputString = in;
		JSONTokener inputTokener = new JSONTokener(inputString);

		JSONObject mmIn = null;

		try {
			mmIn = new JSONObject(inputTokener);
		} catch (org.json.JSONException e) {
		}

		JSONObject outPreProc = new JSONObject();
		JSONObject outContext = new JSONObject();
		JSONArray outGraph = new JSONArray();

		JSONArray usersArray = new JSONArray();
		JSONArray providersArray = new JSONArray();
		JSONArray caregiversArray = new JSONArray();
		JSONArray disabilitiesArray = new JSONArray();
		JSONArray impairmentsArray = new JSONArray();
		JSONArray functionalLimitationsArray = new JSONArray();
		JSONArray diseasesArray = new JSONArray();
		JSONArray educationArray = new JSONArray();
		JSONArray maritalStatusArray = new JSONArray();
		JSONArray connectionDetailsArray = new JSONArray();
		JSONArray serviceModelsArray = new JSONArray();
		JSONArray parametersArray = new JSONArray();
		JSONArray parameterTermsArray = new JSONArray();
		JSONArray statusArray = new JSONArray();
		JSONArray userActivitiesArray = new JSONArray();
		JSONArray messagesArray = new JSONArray();
		JSONArray usageArray = new JSONArray();
		JSONArray loginArray = new JSONArray();
		JSONArray answersArray = new JSONArray();
		JSONArray userAnswersArray = new JSONArray();
		JSONArray questionsArray = new JSONArray();
		JSONArray questionnairesArray = new JSONArray();
		JSONArray array = new JSONArray();

		// get assisted people
		if (mmIn.has("assistedPeople")) {
			JSONArray assistedPeople = (JSONArray) mmIn
					.getJSONArray("assistedPeople");
			JSONObject userObj, temp = null;
			JSONObject profile = null;

			for (int i = 0; i < assistedPeople.length(); i++) {

				userObj = new JSONObject();
				profile = new JSONObject();
				temp = assistedPeople.getJSONObject(i);

				if (temp.has("profile")) {
					profile = temp.getJSONObject("profile");

					// transform basic information of user profile
					userObj = transformUserProfile(profile,
							"u:AssistedPersonSubProfile");
				}

				if (temp.has("hasCapacityToFunction"))
					userObj.put("u:hasCapacityToFunction",
							temp.get("hasCapacityToFunction").toString());

				if (temp.has("hasSocioEconomicStatus"))
					userObj.put("u:hasSocioEconomicStatus",
							temp.get("hasSocioEconomicStatus").toString());

				if (temp.has("hasEducation"))
					userObj.put("u:hasEducation", temp.get("hasEducation")
							.toString());

				if (temp.has("hasGender"))
					userObj.put("u:hasGender", temp.get("hasGender").toString());

				if (temp.has("hasIMEI"))
					userObj.put("u:hasIMEI", temp.get("hasIMEI").toString());

				if (temp.has("hasSocialSupportNetworks"))
					userObj.put(
							"u:hasSocialSupportNetworks",
							Boolean.parseBoolean(temp.get(
									"hasSocialSupportNetworks").toString()));

				if (temp.has("hasAnnualIncome"))
					userObj.put("u:hasAnnualIncome", temp
							.get("hasAnnualIncome").toString());

				if (temp.has("hasTechnologyUsage"))
					userObj.put("u:hasTechnologyUsage",
							temp.get("hasTechnologyUsage").toString());

				if (temp.has("user_activity_status"))
					userObj.put("u:user_activity_status",
							temp.get("user_activity_status").toString());

				if (temp.has("hasMaritalStatus"))
					userObj.put("u:hasMaritalStatus",
							temp.get("hasMaritalStatus").toString());

				if (temp.has("hasJob"))
					userObj.put("u:hasJob", temp.get("hasJob").toString());

				if (temp.has("hasStatus"))
					userObj.put("u:hasStatus", temp.get("hasStatus").toString());

				// object property
				if (temp.has("User_has_Disability")) {

					array = new JSONArray();
					array = getJsonLdObjectArray(temp
							.getJSONArray("User_has_Disability"));
					userObj.put("u:User_has_Disability", array);
				}

				if (temp.has("User_linksTo_Impairment")) {
					array = new JSONArray();
					array = getJsonLdObjectArray(temp
							.getJSONArray("User_linksTo_Impairment"));
					userObj.put("u:User_linksTo_Impairment", array);
				}

				// object property
				if (temp.has("User_linksTo_FunctionalLimitation")) {

					array = new JSONArray();
					array = getJsonLdObjectArray(temp
							.getJSONArray("User_linksTo_FunctionalLimitation"));
					userObj.put("u:User_linksTo_FunctionalLimitation", array);

				}

				// object property
				if (temp.has("User_linksTo_Disease")) {

					array = new JSONArray();
					array = getJsonLdObjectArray(temp
							.getJSONArray("User_linksTo_Disease"));
					userObj.put("u:User_linksTo_Disease", array);

				}

				// object properties
				if (temp.has("connections")) {
					JSONObject conn = temp.getJSONObject("connections");
					if (conn.has("approvedConnected")) {

						array = new JSONArray();
						array = getJsonLdObjectArray(conn
								.getJSONArray("approvedConnected"));
						userObj.put("u:approvedConnected", array);
					}

					if (conn.has("pendingConnected")) {

						array = new JSONArray();
						array = getJsonLdObjectArray(conn
								.getJSONArray("pendingConnected"));
						userObj.put("u:pendingConnected", array);

					}

					if (conn.has("askedConnected")) {

						array = new JSONArray();
						array = getJsonLdObjectArray(conn
								.getJSONArray("askedConnected"));
						userObj.put("u:askedConnected", array);

					}

				}

				usersArray.put(userObj);
			}

			outGraph.put(usersArray);

		}

		// get providers
		if (mmIn.has("providers")) {
			JSONArray providers = (JSONArray) mmIn.getJSONArray("providers");
			JSONObject userObj, temp = null;
			JSONObject profile = null;

			for (int i = 0; i < providers.length(); i++) {

				temp = providers.getJSONObject(i);
				userObj = new JSONObject();

				if (temp.has("profile")) {
					profile = temp.getJSONObject("profile");

					// transform basic information of user profile
					userObj = transformUserProfile(profile,
							"u:ProviderSubProfile");
				}

				providersArray.put(userObj);
			}

			outGraph.put(providersArray);
		}

		// get caregivers
		// TODO type of caregiver (formal/informal)
		if (mmIn.has("caregivers")) {
			JSONArray caregivers = (JSONArray) mmIn.getJSONArray("caregivers");
			JSONObject userObj, temp = null;
			JSONObject profile = null;

			for (int i = 0; i < caregivers.length(); i++) {

				temp = caregivers.getJSONObject(i);
				userObj = new JSONObject();

				if (temp.has("profile")) {
					profile = temp.getJSONObject("profile");

					// transform basic information of user profile
					userObj = transformUserProfile(profile,
							"u:CaregiverSubProfile");
				}

				// object properties
				if (temp.has("connections")) {
					JSONObject conn = (JSONObject) temp.get("connections");
					if (conn.has("approvedConnected")) {

						array = new JSONArray();
						array = getJsonLdObjectArray(conn
								.getJSONArray("approvedConnected"));
						userObj.put("u:approvedConnected", array);

					}

					if (conn.has("pendingConnected")) {

						array = new JSONArray();
						array = getJsonLdObjectArray(conn
								.getJSONArray("pendingConnected"));
						userObj.put("u:pendingConnected", array);

					}

					if (conn.has("askedConnected")) {

						array = new JSONArray();
						array = getJsonLdObjectArray(conn
								.getJSONArray("askedConnected"));
						userObj.put("u:askedConnected", array);

					}

				}

				caregiversArray.put(userObj);
			}

			outGraph.put(caregiversArray);
		}

		// get disabilities
		if (mmIn.has("disabilities")) {
			JSONArray disabilities = (JSONArray) mmIn
					.getJSONArray("disabilities");
			JSONObject userObj, temp = null;

			for (int i = 0; i < disabilities.length(); i++) {
				userObj = new JSONObject();
				temp = disabilities.getJSONObject(i);
				userObj.put("@type", "u:Disability");

				if (temp.has("uri"))
					userObj.put("@id", temp.get("uri").toString());

				if (temp.has("hasName"))
					userObj.put("u:hasName", temp.get("hasName").toString());

				if (temp.has("hasCode"))
					userObj.put("u:hasCode", temp.get("hasCode").toString());

				// object property
				if (temp.has("Disability_belongsTo_Impairment")) {

					array = new JSONArray();
					array = getJsonLdObjectArray(temp
							.getJSONArray("Disability_belongsTo_Impairment"));
					userObj.put("u:Disability_belongsTo_Impairment", array);

				}

				disabilitiesArray.put(userObj);
			}

			outGraph.put(disabilitiesArray);
		}

		// get impairments
		if (mmIn.has("impairments")) {
			JSONArray impairments = (JSONArray) mmIn
					.getJSONArray("impairments");
			JSONObject userObj, temp = null;

			for (int i = 0; i < impairments.length(); i++) {
				userObj = new JSONObject();
				temp = impairments.getJSONObject(i);
				userObj.put("@type", "u:Impairment");

				if (temp.has("uri"))
					userObj.put("@id", temp.get("uri").toString());

				if (temp.has("hasName"))
					userObj.put("u:hasName", temp.get("hasName").toString());

				if (temp.has("hasCode"))
					userObj.put("u:hasCode", temp.get("hasCode").toString());

				// object property
				if (temp.has("Impairment_linksTo_FunctionalLimitation")) {

					array = new JSONArray();
					array = getJsonLdObjectArray(temp
							.getJSONArray("Impairment_linksTo_FunctionalLimitation"));
					userObj.put("u:Impairment_linksTo_FunctionalLimitation",
							array);

				}

				impairmentsArray.put(userObj);
			}

			outGraph.put(impairmentsArray);
		}

		// get functional limitations
		if (mmIn.has("functionalLimitations")) {
			JSONArray functionalLimitations = (JSONArray) mmIn
					.getJSONArray("functionalLimitations");
			JSONObject userObj, temp = null;

			for (int i = 0; i < functionalLimitations.length(); i++) {
				userObj = new JSONObject();
				temp = functionalLimitations.getJSONObject(i);
				userObj.put("@type", "u:FunctionalLimitation");

				if (temp.has("uri"))
					userObj.put("@id", temp.get("uri").toString());

				if (temp.has("hasName"))
					userObj.put("u:hasName", temp.get("hasName").toString());

				if (temp.has("hasCode"))
					userObj.put("u:hasCode", temp.get("hasCode").toString());

				// object property

				if (temp.has("FunctionalLimitation_belongsTo_Disability")) {

					array = new JSONArray();
					array = getJsonLdObjectArray(temp
							.getJSONArray("FunctionalLimitation_belongsTo_Disability"));
					userObj.put("u:FunctionalLimitation_belongsTo_Disability",
							array);

				}

				functionalLimitationsArray.put(userObj);
			}

			outGraph.put(functionalLimitationsArray);
		}

		// get diseases
		if (mmIn.has("diseases")) {
			JSONArray diseases = (JSONArray) mmIn.getJSONArray("diseases");
			JSONObject userObj, temp = null;

			for (int i = 0; i < diseases.length(); i++) {
				userObj = new JSONObject();
				temp = diseases.getJSONObject(i);
				userObj.put("@type", "u:Disease");

				if (temp.has("uri"))
					userObj.put("@id", temp.get("uri").toString());

				if (temp.has("hasName"))
					userObj.put("u:hasName", temp.get("hasName").toString());

				if (temp.has("hasCode"))
					userObj.put("u:hasCode", temp.get("hasCode").toString());

				diseasesArray.put(userObj);
			}

			outGraph.put(diseasesArray);
		}

		// get education
		if (mmIn.has("education")) {
			JSONArray education = (JSONArray) mmIn.getJSONArray("education");
			JSONObject userObj, temp = null;

			for (int i = 0; i < education.length(); i++) {
				userObj = new JSONObject();
				temp = education.getJSONObject(i);
				userObj.put("@type", "u:Education");

				if (temp.has("uri"))
					userObj.put("@id", temp.get("uri").toString());

				if (temp.has("hasName"))
					userObj.put("u:hasName", temp.get("hasName").toString());

				if (temp.has("hasValue"))
					userObj.put("u:hasValue", temp.get("hasValue").toString());

				educationArray.put(userObj);
			}

			outGraph.put(educationArray);
		}

		// get marital status
		if (mmIn.has("maritalStatuses")) {
			JSONArray maritalStatuses = (JSONArray) mmIn
					.getJSONArray("maritalStatuses");
			JSONObject userObj, temp = null;

			for (int i = 0; i < maritalStatuses.length(); i++) {
				userObj = new JSONObject();
				temp = maritalStatuses.getJSONObject(i);
				userObj.put("@type", "u:MaritalStatus");

				if (temp.has("uri"))
					userObj.put("@id", temp.get("uri").toString());

				if (temp.has("hasName"))
					userObj.put("u:hasName", temp.get("hasName").toString());

				maritalStatusArray.put(userObj);
			}

			outGraph.put(maritalStatusArray);
		}

		// get tools and categories of tools
		if (mmIn.has("allTools")) {
			JSONArray allTools = (JSONArray) mmIn.getJSONArray("allTools");
			JSONObject categoryObject = null;
			JSONObject toolObject = null;
			JSONArray toolsPerCategory = null;

			for (int i = 0; i < allTools.length(); i++) {

				categoryObject = new JSONObject();

				JSONObject temp = allTools.getJSONObject(i);

				// get categories
				if (temp.has("toolCategory")) {
					categoryObject.put("@type", "p:"
							+ temp.get("toolCategory").toString());
					categoryObject.put("rdfs:comment", temp.get("toolCategory")
							.toString());
					categoryObject.put("rdfs:subClassOf", "Tool");
				}

				JSONArray tempTools = temp.getJSONArray("tools");
				toolsPerCategory = new JSONArray();

				// get tools per category

				for (int j = 0; j < tempTools.length(); j++) {

					JSONObject tempTool = tempTools.getJSONObject(j);
					toolObject = new JSONObject();
					toolObject.put("@type", "p:"
							+ temp.get("toolCategory").toString());

					if (tempTool.has("uri"))
						toolObject.put("@id", tempTool.get("uri"));

					if (tempTool.has("toolHasName"))
						toolObject.put("p:toolHasName",
								tempTool.get("toolHasName"));

					if (tempTool.has("toolHasUsername"))
						toolObject.put("p:toolHasUsername",
								tempTool.get("toolHasUsername"));

					if (tempTool.has("toolHasPassword"))
						toolObject.put("p:toolHasPassword",
								tempTool.get("toolHasPassword"));

					if (tempTool.has("toolHasDescription"))
						toolObject.put("p:toolHasDescription",
								tempTool.get("toolHasDescription"));

					if (tempTool.has("hasImageURL"))
						toolObject.put("p:hasImageURL",
								tempTool.get("hasImageURL"));

					if (tempTool.has("toolHasId"))
						toolObject
								.put("p:toolHasId", tempTool.get("toolHasId"));

					if (tempTool.has("toolHasUrl"))
						toolObject.put("p:toolHasUrl",
								tempTool.get("toolHasUrl"));

					// object property
					if (tempTool.has("toolBelongsToVendor")) {

						array = new JSONArray();
						array = getJsonLdObjectArray(tempTool
								.getJSONArray("toolBelongsToVendor"));
						toolObject.put("p:toolBelongsToVendor", array);

					}

					// object property
					if (tempTool.has("toolHasService")) {

						array = new JSONArray();
						array = getJsonLdObjectArray(tempTool
								.getJSONArray("toolHasService"));
						toolObject.put("p:toolHasService", array);

					}

					toolsPerCategory.put(toolObject);
				}

				// categoryObject.put("tools", tempToolsArray);
				outGraph.put(toolsPerCategory);
				outGraph.put(categoryObject);
			}

		}

		// get connection details
		if (mmIn.has("connectionDetails")) {
			JSONArray connectionDetails = (JSONArray) mmIn
					.getJSONArray("connectionDetails");
			JSONObject userObj, temp = null;

			for (int i = 0; i < connectionDetails.length(); i++) {
				userObj = new JSONObject();
				temp = connectionDetails.getJSONObject(i);
				userObj.put("@type", "p:ConnectionDetails");

				if (temp.has("uri"))
					userObj.put("@id", temp.get("uri").toString());

				if (temp.has("hasURL"))
					userObj.put("p:hasURL", temp.get("hasURL").toString());

				if (temp.has("hasPort"))
					userObj.put("p:hasPort", temp.get("hasPort").toString());

				if (temp.has("hasHost"))
					userObj.put("p:hasHost", temp.get("hasHost").toString());

				connectionDetailsArray.put(userObj);
			}

			outGraph.put(connectionDetailsArray);
		}

		// get service models
		if (mmIn.has("serviceModels")) {
			JSONArray serviceModels = (JSONArray) mmIn
					.getJSONArray("serviceModels");
			JSONObject userObj, temp = null;

			for (int i = 0; i < serviceModels.length(); i++) {
				userObj = new JSONObject();
				temp = serviceModels.getJSONObject(i);
				userObj.put("@type", "p:ServiceModel");

				if (temp.has("uri"))
					userObj.put("@id", temp.get("uri").toString());

				if (temp.has("serviceHasName"))
					userObj.put("p:serviceHasName", temp.get("serviceHasName")
							.toString());

				if (temp.has("serviceHasDescription"))
					userObj.put("p:serviceHasDescription",
							temp.get("serviceHasDescription").toString());

				if (temp.has("hasPriority"))
					userObj.put("p:hasPriority", temp.get("hasPriority")
							.toString());

				if (temp.has("canBeUsedIndependently"))
					userObj.put(
							"p:canBeUsedIndependently",
							Boolean.parseBoolean(temp.get(
									"canBeUsedIndependently").toString()));

				if (temp.has("serviceShowStats"))
					userObj.put("p:serviceShowStats", Boolean.parseBoolean(temp
							.get("serviceShowStats").toString()));

				// object property
				if (temp.has("hasConnectionDetails")) {

					array = new JSONArray();
					array = getJsonLdObjectArray(temp
							.getJSONArray("hasConnectionDetails"));
					userObj.put("p:hasConnectionDetails", array);

				}

				// object property
				if (temp.has("hasInput")) {

					array = new JSONArray();
					array = getJsonLdObjectArray(temp.getJSONArray("hasInput"));
					userObj.put("p:hasInput", array);

				}

				// object property
				if (temp.has("hasOutput")) {

					array = new JSONArray();
					array = getJsonLdObjectArray(temp.getJSONArray("hasOutput"));
					userObj.put("p:hasOutput", array);

				}

				// object property
				if (temp.has("hasResult")) {

					array = new JSONArray();
					array = getJsonLdObjectArray(temp.getJSONArray("hasResult"));
					userObj.put("p:hasResult", array);

				}

				// object property
				if (temp.has("hasPreconditions")) {

					array = new JSONArray();
					array = getJsonLdObjectArray(temp
							.getJSONArray("hasPreconditions"));
					userObj.put("p:hasPreconditions", array);

				}

				serviceModelsArray.put(userObj);
			}

			outGraph.put(serviceModelsArray);

		}

		// get parameters
		if (mmIn.has("parameters")) {
			JSONArray parameters = (JSONArray) mmIn.getJSONArray("parameters");
			JSONObject userObj, temp = null;

			for (int i = 0; i < parameters.length(); i++) {
				userObj = new JSONObject();
				temp = parameters.getJSONObject(i);
				userObj.put("@type", "p:Parameter");

				if (temp.has("uri"))
					userObj.put("@id", temp.get("uri").toString());

				if (temp.has("parameterHasName"))
					userObj.put("p:parameterHasName",
							temp.get("parameterHasName").toString());

				if (temp.has("parameterHasDescription"))
					userObj.put("p:parameterHasDescription",
							temp.get("parameterHasDescription").toString());

				if (temp.has("parameterHasRange"))
					userObj.put("p:parameterHasRange",
							temp.get("parameterHasRange").toString());

				if (temp.has("parameterHasDefaultValue"))
					userObj.put("p:parameterHasDefaultValue",
							temp.get("parameterHasDefaultValue").toString());

				if (temp.has("parameterHasMaxValue"))
					userObj.put("p:parameterHasMaxValue",
							temp.get("parameterHasMaxValue").toString());

				if (temp.has("parameterHasMinValue"))
					userObj.put("p:parameterHasMinValue",
							temp.get("parameterHasMinValue").toString());

				if (temp.has("parameterHasType"))
					userObj.put("p:parameterHasType",
							temp.get("parameterHasType").toString());

				// object property
				if (temp.has("isMappedToParameterTerm")) {

					JSONObject pterm = new JSONObject();
					pterm.put("@id", temp.get("isMappedToParameterTerm")
							.toString());
					array = new JSONArray();
					array.put(pterm);
					userObj.put("p:isMappedToParameterTerm", array);
				}

				parametersArray.put(userObj);
			}

			outGraph.put(parametersArray);
		}

		// get parameters terms
		if (mmIn.has("parameterTerms")) {
			JSONArray parameterTerms = (JSONArray) mmIn
					.getJSONArray("parameterTerms");
			JSONObject userObj, temp = null;

			for (int i = 0; i < parameterTerms.length(); i++) {
				userObj = new JSONObject();
				temp = parameterTerms.getJSONObject(i);
				userObj.put("@type", "p:ParameterTerm");

				if (temp.has("uri"))
					userObj.put("@id", temp.get("uri").toString());

				if (temp.has("hasName"))
					userObj.put("p:hasName", temp.get("hasName").toString());

				if (temp.has("hasType"))
					userObj.put("p:hasType", temp.get("hasType").toString());

				parameterTermsArray.put(userObj);
			}

			outGraph.put(parameterTermsArray);

		}

		// get status
		if (mmIn.has("status")) {
			JSONArray status = (JSONArray) mmIn.getJSONArray("status");
			JSONObject userObj, temp = null;

			for (int i = 0; i < status.length(); i++) {
				userObj = new JSONObject();
				temp = status.getJSONObject(i);
				userObj.put("@type", "p:Status");

				if (temp.has("uri"))
					userObj.put("@id", temp.get("uri").toString());

				if (temp.has("hasID"))
					userObj.put("p:hasID", temp.get("hasID").toString());

				// object property
				if (temp.has("statusIsMappedWith")) {

					array = new JSONArray();
					array = getJsonLdObjectArray(temp
							.getJSONArray("statusIsMappedWith"));

					userObj.put("p:statusIsMappedWith", array);
				}

				// object property
				if (temp.has("statusIsMappedToMessage")) {

					array = new JSONArray();
					array = getJsonLdObjectArray(temp
							.getJSONArray("statusIsMappedToMessage"));
					userObj.put("p:statusIsMappedToMessage", array);
				}

				statusArray.put(userObj);
			}

			outGraph.put(statusArray);

		}

		// get userActivities
		if (mmIn.has("userActivities")) {
			JSONArray userActivities = (JSONArray) mmIn
					.getJSONArray("userActivities");
			JSONObject userObj, temp = null;

			for (int i = 0; i < userActivities.length(); i++) {
				userObj = new JSONObject();
				temp = userActivities.getJSONObject(i);
				userObj.put("@type", "p:UserActivity");

				if (temp.has("uri"))
					userObj.put("@id", temp.get("uri").toString());

				// object property
				if (temp.has("activityIsMappedWith")) {

					array = new JSONArray();
					array = getJsonLdObjectArray(temp
							.getJSONArray("activityIsMappedWith"));
					userObj.put("p:activityIsMappedWith", array);
				}

				userActivitiesArray.put(userObj);
			}
			outGraph.put(userActivitiesArray);
		}

		// get messages
		if (mmIn.has("messages")) {
			JSONArray messages = (JSONArray) mmIn.getJSONArray("messages");
			JSONObject userObj, temp = null;

			for (int i = 0; i < messages.length(); i++) {
				userObj = new JSONObject();
				temp = messages.getJSONObject(i);
				userObj.put("@type", "p:Message");

				if (temp.has("uri"))
					userObj.put("@id", temp.get("uri").toString());

				if (temp.has("hasName"))
					userObj.put("p:hasName", temp.get("hasName").toString());

				messagesArray.put(userObj);
			}

			outGraph.put(messagesArray);

		}
		// -------------- SENSOR ONTOLOGY -------------------------
		// get usage
		if (mmIn.has("usage")) {
			JSONArray usage = (JSONArray) mmIn.getJSONArray("usage");
			JSONObject userObj, temp = null;

			for (int i = 0; i < usage.length(); i++) {
				userObj = new JSONObject();
				temp = usage.getJSONObject(i);
				userObj.put("@type", "s:Usage");

				if (temp.has("uri"))
					userObj.put("@id", temp.get("uri").toString());

				if (temp.has("hasEventDate"))
					userObj.put("s:hasEventDate", temp.get("hasEventDate")
							.toString());

				if (temp.has("hasUserId"))
					userObj.put("s:hasUserId", temp.get("hasUserId").toString());

				if (temp.has("hasServiceName"))
					userObj.put("s:hasServiceName", temp.get("hasServiceName")
							.toString());

				usageArray.put(userObj);
			}

			outGraph.put(usageArray);

		}

		// get answers
		if (mmIn.has("answers")) {
			JSONArray answers = (JSONArray) mmIn.getJSONArray("answers");
			JSONObject userObj, temp = null;

			for (int i = 0; i < answers.length(); i++) {
				userObj = new JSONObject();
				temp = answers.getJSONObject(i);
				userObj.put("@type", "s:Answer");

				if (temp.has("uri"))
					userObj.put("@id", temp.get("uri").toString());

				if (temp.has("hasName"))
					userObj.put("s:hasName", temp.get("hasName").toString());

				if (temp.has("hasValue"))
					userObj.put("s:hasValue",
							Integer.parseInt(temp.get("hasValue").toString()));

				answersArray.put(userObj);
			}
			outGraph.put(answersArray);
		}

		// get login
		if (mmIn.has("login")) {
			JSONArray login = (JSONArray) mmIn.getJSONArray("login");
			JSONObject userObj, temp = null;

			for (int i = 0; i < login.length(); i++) {
				userObj = new JSONObject();
				temp = login.getJSONObject(i);
				userObj.put("@type", "s:Login");

				if (temp.has("uri"))
					userObj.put("@id", temp.get("uri").toString());

				if (temp.has("hasUserId"))
					userObj.put("s:hasUserId", temp.get("hasUserId").toString());

				if (temp.has("hasEventDate"))
					userObj.put("s:hasEventDate", temp.get("hasEventDate")
							.toString());

				loginArray.put(userObj);
			}

			outGraph.put(loginArray);

		}

		// get questions
		if (mmIn.has("questions")) {
			JSONArray questions = (JSONArray) mmIn.getJSONArray("questions");
			JSONObject userObj, temp = null;

			for (int i = 0; i < questions.length(); i++) {
				userObj = new JSONObject();
				temp = questions.getJSONObject(i);
				userObj.put("@type", "s:Question");

				if (temp.has("uri"))
					userObj.put("@id", temp.get("uri").toString());

				if (temp.has("hasLabel"))
					userObj.put("s:hasLabel", temp.get("hasLabel").toString());

				if (temp.has("hasName"))
					userObj.put("s:hasName", temp.get("hasName").toString());

				// object property
				if (temp.has("hasPossibleAnswers")) {

					array = new JSONArray();
					array = getJsonLdObjectArray(temp
							.getJSONArray("hasPossibleAnswers"));
					userObj.put("s:hasPossibleAnswers", array);

				}

				questionsArray.put(userObj);
			}

			outGraph.put(questionsArray);

		}

		// get questionnaire
		if (mmIn.has("questionnaire")) {
			JSONArray questionnaire = (JSONArray) mmIn
					.getJSONArray("questionnaire");
			JSONObject userObj, temp = null;

			for (int i = 0; i < questionnaire.length(); i++) {
				userObj = new JSONObject();
				temp = questionnaire.getJSONObject(i);
				userObj.put("@type", "s:Questionnaire");

				if (temp.has("uri"))
					userObj.put("@id", temp.get("uri").toString());

				if (temp.has("hasUserId"))
					userObj.put("s:hasUserId", temp.get("hasUserId").toString());

				if (temp.has("hasEventDate"))
					userObj.put("s:hasEventDate", temp.get("hasEventDate")
							.toString());

				// object property
				if (temp.has("hasUserAnswer")) {

					array = new JSONArray();
					array = getJsonLdObjectArray(temp
							.getJSONArray("hasUserAnswer"));
					userObj.put("s:hasUserAnswer", array);

				}

				questionnairesArray.put(userObj);
			}

			outGraph.put(questionnairesArray);

		}

		// get userAnswers
		if (mmIn.has("userAnswers")) {
			JSONArray userAnswers = (JSONArray) mmIn
					.getJSONArray("userAnswers");
			JSONObject userObj, temp = null;

			for (int i = 0; i < userAnswers.length(); i++) {
				userObj = new JSONObject();
				temp = userAnswers.getJSONObject(i);
				userObj.put("@type", "s:UserAnswer");

				if (temp.has("uri"))
					userObj.put("@id", temp.get("uri").toString());

				// object property
				if (temp.has("hasAnswer")) {

					array = new JSONArray();
					JSONObject answer = new JSONObject();
					answer.put("@id", temp.get("hasAnswer").toString());
					array.put(answer);
					userObj.put("s:hasAnswer", array);

				}

				// object property
				if (temp.has("hasQuestion")) {

					array = new JSONArray();
					JSONObject answer = new JSONObject();
					answer.put("@id", temp.get("hasQuestion").toString());
					array.put(answer);
					userObj.put("s:hasQuestion", array);

				}

				userAnswersArray.put(userObj);
			}

			outGraph.put(userAnswersArray);

		}

		outContext.put("u",
				"http://www.AccessibleOntology.com/GenericOntology.owl#");
		outContext.put("p", "http://inlife.ServiceOntology#");
		outContext.put("s", "http://inlife.eu/sensorOntology#");
		outContext.put("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		outContext.put("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
		outContext.put("xsd", "http://www.w3.org/2001/XMLSchema#");
		outPreProc.put("@context", outContext);
		outPreProc.put("@graph", outGraph);

		return outPreProc.toString(5);
	}

	public static String transformJsonInputToJsonLdNew() throws JSONException {

		JSONObject outPreProc = new JSONObject();
		JSONObject outContext = new JSONObject();
		JSONArray outGraph = new JSONArray();

		JSONArray usersArray = new JSONArray();
		JSONArray providersArray = new JSONArray();
		JSONArray caregiversArray = new JSONArray();
		JSONArray disabilitiesArray = new JSONArray();
		JSONArray impairmentsArray = new JSONArray();
		JSONArray functionalLimitationsArray = new JSONArray();
		JSONArray diseasesArray = new JSONArray();
		JSONArray educationArray = new JSONArray();
		JSONArray maritalStatusArray = new JSONArray();
		JSONArray connectionDetailsArray = new JSONArray();
		JSONArray serviceModelsArray = new JSONArray();
		JSONArray parametersArray = new JSONArray();
		JSONArray parameterTermsArray = new JSONArray();
		JSONArray statusArray = new JSONArray();
		JSONArray userActivitiesArray = new JSONArray();
		JSONArray messagesArray = new JSONArray();
		JSONArray usageArray = new JSONArray();
		JSONArray loginArray = new JSONArray();
		JSONArray answersArray = new JSONArray();
		JSONArray userAnswersArray = new JSONArray();
		JSONArray questionsArray = new JSONArray();
		JSONArray questionnairesArray = new JSONArray();
		JSONArray array = new JSONArray();

		// get assisted people

		JSONObject userObj = null;
		Profile profile = null;

		Iterator it = JsonManager.getInstance().assistedPeopleHashmap_byUri
				.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			AssistedPerson temp = (AssistedPerson) pair.getValue();

			userObj = new JSONObject();
			profile = null;

			if (temp.getProfile() != null) {
				profile = temp.getProfile();

				// transform basic information of user profile
				userObj = transformUserProfileToJsonLdProfile(profile,
						"u:AssistedPersonSubProfile");
			}

			if (temp.getHasCapacityToFunction() != null)
				userObj.put("u:hasCapacityToFunction", temp
						.getHasCapacityToFunction().toString());

			if (temp.getHasSocioEconomicStatus() != null)
				userObj.put("u:hasSocioEconomicStatus", temp
						.getHasSocioEconomicStatus().toString());

			if (temp.getHasEducation() != null)
				userObj.put("u:hasEducation", temp.getHasEducation().toString());

			if (temp.getHasGender() != null)
				userObj.put("u:hasGender", temp.getHasGender().toString());

			if (temp.getHasIMEI() != null)
				userObj.put("u:hasIMEI", temp.getHasIMEI().toString());

			if (temp.isHasSocialSupportNetworks())
				userObj.put("u:hasSocialSupportNetworks",
						temp.isHasSocialSupportNetworks());

			if (temp.getHasAnnualIncome() != null)
				userObj.put("u:hasAnnualIncome", temp.getHasAnnualIncome()
						.toString());

			if (temp.getHasTechnologyUsage() != null)
				userObj.put("u:hasTechnologyUsage", temp
						.getHasTechnologyUsage().toString());

			if (temp.getUser_activity_status() != null)
				userObj.put("u:user_activity_status", temp
						.getUser_activity_status().toString());

			if (temp.getHasMaritalStatus() != null)
				userObj.put("u:hasMaritalStatus", temp.getHasMaritalStatus()
						.toString());

			// TODO hasJob

			if (temp.getHasStatus() != null)
				userObj.put("u:hasStatus", temp.getHasStatus().toString());

			// object property
			if (temp.getUser_has_Disability() != null) {

				array = new JSONArray();
				array = getObjectArray(temp.getUser_has_Disability());
				userObj.put("u:User_has_Disability", array);
			}

			if (temp.getUser_linksTo_Impairment() != null) {
				array = new JSONArray();
				array = getObjectArray(temp.getUser_linksTo_Impairment());
				userObj.put("u:User_linksTo_Impairment", array);
			}

			// object property
			if (temp.getUser_linksTo_FunctionalLimitation() != null) {

				array = new JSONArray();
				array = getObjectArray(temp
						.getUser_linksTo_FunctionalLimitation());
				userObj.put("u:User_linksTo_FunctionalLimitation", array);

			}

			// object property
			if (temp.getUser_linksTo_Disease() != null) {

				array = new JSONArray();
				array = getObjectArray(temp.getUser_linksTo_Disease());
				userObj.put("u:User_linksTo_Disease", array);

			}

			// object properties
			if (temp.getConnections() != null) {
				Connections conn = temp.getConnections();
				if (conn.getApprovedConnected() != null) {

					array = new JSONArray();
					array = getObjectArray(conn.getApprovedConnected());
					userObj.put("u:approvedConnected", array);
				}

				if (conn.getPendingConnected() != null) {

					array = new JSONArray();
					array = getObjectArray(conn.getPendingConnected());
					userObj.put("u:pendingConnected", array);

				}

				if (conn.getAskedConnected() != null) {

					array = new JSONArray();
					array = getObjectArray(conn.getAskedConnected());
					userObj.put("u:askedConnected", array);

				}

			}

			usersArray.put(userObj);
		}

		outGraph.put(usersArray);

		// get providers

		it = JsonManager.getInstance().providersHashmap_byUri.entrySet()
				.iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Provider temp = (Provider) pair.getValue();

			userObj = null;
			profile = null;

			userObj = new JSONObject();

			if (temp.getProfile() != null) {
				profile = temp.getProfile();

				// transform basic information of user profile
				userObj = transformUserProfileToJsonLdProfile(profile,
						"u:ProviderSubProfile");
			}

			providersArray.put(userObj);
		}

		outGraph.put(providersArray);

		// get caregivers
		// TODO type of caregiver (formal/informal)

		it = JsonManager.getInstance().caregiversHashmap_byUri.entrySet()
				.iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Caregiver temp = (Caregiver) pair.getValue();

			profile = null;
			userObj = new JSONObject();

			if (temp.getProfile() != null) {
				profile = temp.getProfile();

				// transform basic information of user profile
				userObj = transformUserProfileToJsonLdProfile(profile,
						"u:CaregiverSubProfile");
			}

			// object properties
			if (temp.getConnections() != null) {
				Connections conn = temp.getConnections();
				if (conn.getApprovedConnected() != null) {

					array = new JSONArray();
					array = getObjectArray(conn.getApprovedConnected());
					userObj.put("u:approvedConnected", array);
				}

				if (conn.getPendingConnected() != null) {

					array = new JSONArray();
					array = getObjectArray(conn.getPendingConnected());
					userObj.put("u:pendingConnected", array);

				}

				if (conn.getAskedConnected() != null) {

					array = new JSONArray();
					array = getObjectArray(conn.getAskedConnected());
					userObj.put("u:askedConnected", array);

				}

			}

			caregiversArray.put(userObj);
		}

		outGraph.put(caregiversArray);

		// get disabilities
		it = JsonManager.getInstance().disabilities.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Disability temp = (Disability) pair.getValue();

			userObj = new JSONObject();

			userObj.put("@type", "u:Disability");

			if (temp.getUri() != null)
				userObj.put("@id", temp.getUri().toString());

			if (temp.getHasName() != null)
				userObj.put("u:hasName", temp.getHasName().toString());

			if (temp.getHasCode() != null)
				userObj.put("u:hasCode", temp.getHasCode().toString());

			// object property
			if (temp.getDisability_belongsTo_Impairment() != null) {

				array = new JSONArray();
				array = getObjectArray(temp
						.getDisability_belongsTo_Impairment());
				userObj.put("u:Disability_belongsTo_Impairment", array);

			}

			disabilitiesArray.put(userObj);
		}

		outGraph.put(disabilitiesArray);

		// get impairments
		it = JsonManager.getInstance().impairments.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Impairment temp = (Impairment) pair.getValue();
			userObj = new JSONObject();

			userObj.put("@type", "u:Impairment");

			if (temp.getUri() != null)
				userObj.put("@id", temp.getUri().toString());

			if (temp.getHasName() != null)
				userObj.put("u:hasName", temp.getHasName().toString());

			if (temp.getHasCode() != null)
				userObj.put("u:hasCode", temp.getHasCode().toString());

			// object property
			if (temp.getImpairment_linksTo_FunctionalLimitation() != null) {

				array = new JSONArray();
				array = getObjectArray(temp
						.getImpairment_linksTo_FunctionalLimitation());
				userObj.put("u:Impairment_linksTo_FunctionalLimitation", array);

			}

			impairmentsArray.put(userObj);
		}

		outGraph.put(impairmentsArray);

		// get functional limitations

		it = JsonManager.getInstance().functionalLimitations.entrySet()
				.iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			FunctionalLimitation temp = (FunctionalLimitation) pair.getValue();
			userObj = new JSONObject();

			userObj.put("@type", "u:FunctionalLimitation");

			if (temp.getUri() != null)
				userObj.put("@id", temp.getUri().toString());

			if (temp.getHasName() != null)
				userObj.put("u:hasName", temp.getHasName().toString());

			if (temp.getHasCode() != null)
				userObj.put("u:hasCode", temp.getHasCode().toString());

			// object property

			if (temp.getFunctionalLimitation_belongsTo_Disability() != null) {

				array = new JSONArray();
				array = getObjectArray(temp
						.getFunctionalLimitation_belongsTo_Disability());
				userObj.put("u:FunctionalLimitation_belongsTo_Disability",
						array);

			}

			functionalLimitationsArray.put(userObj);
		}

		outGraph.put(functionalLimitationsArray);

		// get diseases

		it = JsonManager.getInstance().diseases.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Disease temp = (Disease) pair.getValue();
			userObj = new JSONObject();

			userObj.put("@type", "u:Disease");

			if (temp.getUri() != null)
				userObj.put("@id", temp.getUri().toString());

			if (temp.getHasName() != null)
				userObj.put("u:hasName", temp.getHasName().toString());

			if (temp.getHasCode() != null)
				userObj.put("u:hasCode", temp.getHasCode().toString());

			diseasesArray.put(userObj);
		}

		outGraph.put(diseasesArray);

		// get education
		it = JsonManager.getInstance().education.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Education temp = (Education) pair.getValue();
			userObj = new JSONObject();

			userObj.put("@type", "u:Education");

			if (temp.getUri() != null)
				userObj.put("@id", temp.getUri().toString());

			if (temp.getHasName() != null)
				userObj.put("u:hasName", temp.getHasName().toString());

			userObj.put("u:hasValue", temp.getHasValue());

			educationArray.put(userObj);
		}

		outGraph.put(educationArray);

		// get marital status

		it = JsonManager.getInstance().maritalStatuses.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			MaritalStatus temp = (MaritalStatus) pair.getValue();
			userObj = new JSONObject();

			userObj.put("@type", "u:MaritalStatus");

			if (temp.getUri() != null)
				userObj.put("@id", temp.getUri().toString());

			if (temp.getHasName() != null)
				userObj.put("u:hasName", temp.getHasName().toString());

			maritalStatusArray.put(userObj);
		}

		outGraph.put(maritalStatusArray);

		// get tools and categories of tools
		it = JsonManager.getInstance().toolsByCategory.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();

			JSONObject categoryObject = new JSONObject();
			JSONObject toolObject = null;
			JSONArray toolsPerCategory = null;

			// get categories

			categoryObject.put("@type", "p:" + pair.getKey().toString());
			categoryObject.put("rdfs:comment", pair.getKey().toString());
			categoryObject.put("rdfs:subClassOf", "Tool");

			toolsPerCategory = new JSONArray();

			ArrayList<Tool> toolsList = (ArrayList<Tool>) pair.getValue();
			// get tools per category

			for (Tool tempTool : toolsList) {

				toolObject = new JSONObject();
				toolObject.put("@type", "p:" + pair.getKey().toString());

				if (tempTool.getUri() != null)
					toolObject.put("@id", tempTool.getUri());

				if (tempTool.getToolHasName() != null)
					toolObject.put("p:toolHasName", tempTool.getToolHasName());

				if (tempTool.getToolHasUsername() != null)
					toolObject.put("p:toolHasUsername",
							tempTool.getToolHasUsername());

				if (tempTool.getToolHasPassword() != null)
					toolObject.put("p:toolHasPassword",
							tempTool.getToolHasPassword());

				if (tempTool.getToolHasDescription() != null)
					toolObject.put("p:toolHasDescription",
							tempTool.getToolHasDescription());

				if (tempTool.getHasImageURL() != null)
					toolObject.put("p:hasImageURL", tempTool.getHasImageURL());

				if (tempTool.getToolHasId() != null)
					toolObject.put("p:toolHasId", tempTool.getToolHasId());

				if (tempTool.getToolHasUrl() != null)
					toolObject.put("p:toolHasUrl", tempTool.getToolHasUrl());

				// object property
				if (tempTool.getToolBelongsToVendor() != null) {

					array = new JSONArray();
					array = getObjectArray(tempTool.getToolBelongsToVendor());
					toolObject.put("p:toolBelongsToVendor", array);

				}

				// object property
				if (tempTool.getToolHasService() != null) {

					array = new JSONArray();
					array = getObjectArray(tempTool.getToolHasService());
					toolObject.put("p:toolHasService", array);

				}

				toolsPerCategory.put(toolObject);

			}
			outGraph.put(toolsPerCategory);
			// categoryObject.put("tools", tempToolsArray);

			outGraph.put(categoryObject);
		}

		// get connection details
		it = JsonManager.getInstance().connectionDetails.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			ConnectionDetails temp = (ConnectionDetails) pair.getValue();
			userObj = new JSONObject();

			userObj.put("@type", "p:ConnectionDetails");

			if (temp.getUri() != null)
				userObj.put("@id", temp.getUri().toString());

			if (temp.getHasURL() != null)
				userObj.put("p:hasURL", temp.getHasURL().toString());

			if (temp.getHasPort() != null)
				userObj.put("p:hasPort", temp.getHasPort().toString());

			if (temp.getHasHost() != null)
				userObj.put("p:hasHost", temp.getHasHost().toString());

			connectionDetailsArray.put(userObj);
		}

		outGraph.put(connectionDetailsArray);

		// get service models
		it = JsonManager.getInstance().serviceModels.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			ServiceModel temp = (ServiceModel) pair.getValue();
			userObj = new JSONObject();

			userObj.put("@type", "p:ServiceModel");

			if (temp.getUri() != null)
				userObj.put("@id", temp.getUri().toString());

			if (temp.getServiceHasName() != null)
				userObj.put("p:serviceHasName", temp.getServiceHasName()
						.toString());

			if (temp.getServiceHasSescription() != null)
				userObj.put("p:serviceHasDescription", temp
						.getServiceHasSescription().toString());

			userObj.put("p:hasPriority", temp.getHasPriority());

			userObj.put("p:canBeUsedIndependently",
					temp.isCanBeUsedIndependently());

			userObj.put("p:serviceShowStats", temp.isServiceShowStats());

			// object property
			if (temp.getHasConnectionDetails() != null) {

				array = new JSONArray();
				array = getObjectArray(temp.getHasConnectionDetails());
				userObj.put("p:hasConnectionDetails", array);

			}

			// object property
			if (temp.getHasInput() != null) {

				array = new JSONArray();
				array = getObjectArray(temp.getHasInput());
				userObj.put("p:hasInput", array);

			}

			// object property
			if (temp.getHasOutput() != null) {

				array = new JSONArray();
				array = getObjectArray(temp.getHasOutput());
				userObj.put("p:hasOutput", array);

			}

			// object property
			if (temp.getHasResult() != null) {

				array = new JSONArray();
				array = getObjectArray(temp.getHasResult());
				userObj.put("p:hasResult", array);

			}

			// object property
			if (temp.getHasPrecondition() != null) {

				array = new JSONArray();
				array = getObjectArray(temp.getHasPrecondition());
				userObj.put("p:hasPreconditions", array);

			}

			serviceModelsArray.put(userObj);
		}

		outGraph.put(serviceModelsArray);

		// get parameters
		it = JsonManager.getInstance().parameters.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Parameter temp = (Parameter) pair.getValue();
			userObj = new JSONObject();

			userObj.put("@type", "p:Parameter");

			if (temp.getUri() != null)
				userObj.put("@id", temp.getUri().toString());

			if (temp.getParameterHasName() != null)
				userObj.put("p:parameterHasName", temp.getParameterHasName()
						.toString());

			if (temp.getParameterHasDescription() != null)
				userObj.put("p:parameterHasDescription", temp
						.getParameterHasDescription().toString());

			if (temp.getParameterHasRange() != null)
				userObj.put("p:parameterHasRange", temp.getParameterHasRange()
						.toString());

			if (temp.getParameterHasDefaultValue() != null)
				userObj.put("p:parameterHasDefaultValue", temp
						.getParameterHasDefaultValue().toString());

			if (temp.getParameterHasMaxValue() != null)
				userObj.put("p:parameterHasMaxValue", temp
						.getParameterHasMaxValue().toString());

			if (temp.getParameterHasMinValue() != null)
				userObj.put("p:parameterHasMinValue", temp
						.getParameterHasMinValue().toString());

			if (temp.getParameterHasType() != null)
				userObj.put("p:parameterHasType", temp.getParameterHasType()
						.toString());

			// object property
			if (temp.getIsMappedToParameterTerm() != null) {

				JSONObject pterm = new JSONObject();
				pterm.put("@id", temp.getIsMappedToParameterTerm());
				array = new JSONArray();
				array.put(pterm);
				userObj.put("p:isMappedToParameterTerm", array);
			}

			parametersArray.put(userObj);
		}

		outGraph.put(parametersArray);

		// get parameters terms
		it = JsonManager.getInstance().parameterTerms.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			ParameterTerm temp = (ParameterTerm) pair.getValue();
			userObj = new JSONObject();
			userObj.put("@type", "p:ParameterTerm");

			if (temp.getUri() != null)
				userObj.put("@id", temp.getUri().toString());

			if (temp.getHasName() != null)
				userObj.put("p:hasName", temp.getHasName().toString());

			if (temp.getHasType() != null)
				userObj.put("p:hasType", temp.getHasType().toString());

			parameterTermsArray.put(userObj);
		}

		outGraph.put(parameterTermsArray);

		// get status
		it = JsonManager.getInstance().statuses.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Status temp = (Status) pair.getValue();
			userObj = new JSONObject();
			userObj.put("@type", "p:Status");

			if (temp.getUri() != null)
				userObj.put("@id", temp.getUri().toString());

			if (temp.getHasID() != null)
				userObj.put("p:hasID", temp.getHasID().toString());

			// object property
			if (temp.getStatusIsMappedWith() != null) {

				array = new JSONArray();
				array = getObjectArray(temp.getStatusIsMappedWith());
				userObj.put("p:statusIsMappedWith", array);
			}

			// object property
			if (temp.getStatusIsMappedToMessage() != null) {

				array = new JSONArray();
				array = getObjectArray(temp.getStatusIsMappedToMessage());
				userObj.put("p:statusIsMappedToMessage", array);
			}

			statusArray.put(userObj);
		}

		outGraph.put(statusArray);

		// get userActivities
		it = JsonManager.getInstance().userActivities.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			UserActivity temp = (UserActivity) pair.getValue();
			userObj = new JSONObject();
			userObj.put("@type", "p:UserActivity");

			if (temp.getUri() != null)
				userObj.put("@id", temp.getUri().toString());

			// object property
			if (temp.getActivityIsMappedWith() != null) {

				array = new JSONArray();
				array = getObjectArray(temp.getActivityIsMappedWith());
				userObj.put("p:activityIsMappedWith", array);
			}

			userActivitiesArray.put(userObj);
		}
		outGraph.put(userActivitiesArray);

		// get messages
		it = JsonManager.getInstance().messages.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Message temp = (Message) pair.getValue();
			userObj = new JSONObject();
			userObj.put("@type", "p:Message");

			if (temp.getUri() != null)
				userObj.put("@id", temp.getUri().toString());

			if (temp.getHasName() != null)
				userObj.put("p:hasName", temp.getHasName().toString());

			messagesArray.put(userObj);
		}

		outGraph.put(messagesArray);

		// -------------- SENSOR ONTOLOGY -------------------------
		// get usage
		it = JsonManager.getInstance().usages.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Usage temp = (Usage) pair.getValue();
			userObj = new JSONObject();
			userObj.put("@type", "s:Usage");

			if (temp.getUri() != null)
				userObj.put("@id", temp.getUri().toString());

			if (temp.getHasEventDate() != null)
				userObj.put("s:hasEventDate", temp.getHasEventDate().toString());

			if (temp.getHasUserId() != null)
				userObj.put("s:hasUserId", temp.getHasUserId().toString());

			if (temp.getHasServiceName() != null)
				userObj.put("s:hasServiceName", temp.getHasServiceName()
						.toString());

			usageArray.put(userObj);
		}

		outGraph.put(usageArray);

		// get answers
		it = JsonManager.getInstance().answers.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Answer temp = (Answer) pair.getValue();
			userObj = new JSONObject();
			userObj.put("@type", "s:Answer");

			if (temp.getUri() != null)
				userObj.put("@id", temp.getUri().toString());

			if (temp.getHasName() != null)
				userObj.put("s:hasName", temp.getHasName().toString());

			userObj.put("s:hasValue", temp.getHasValue());

			answersArray.put(userObj);
		}
		outGraph.put(answersArray);

		// get login
		it = JsonManager.getInstance().logins.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Login temp = (Login) pair.getValue();
			userObj = new JSONObject();
			userObj.put("@type", "s:Login");

			if (temp.getUri() != null)
				userObj.put("@id", temp.getUri().toString());

			if (temp.getGhasUserId() != null)
				userObj.put("s:hasUserId", temp.getGhasUserId().toString());

			if (temp.getHasEventDate() != null)
				userObj.put("s:hasEventDate", temp.getHasEventDate());

			loginArray.put(userObj);
		}

		outGraph.put(loginArray);

		// get questions
		it = JsonManager.getInstance().questions.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Question temp = (Question) pair.getValue();
			userObj = new JSONObject();
			userObj.put("@type", "s:Question");

			if (temp.getUri() != null)
				userObj.put("@id", temp.getUri().toString());

			if (temp.getHasLabel() != null)
				userObj.put("s:hasLabel", temp.getHasLabel().toString());

			if (temp.getHasName() != null)
				userObj.put("s:hasName", temp.getHasName().toString());

			// object property
			if (temp.getHasPossibleAnswers() != null) {

				array = new JSONArray();
				array = getObjectArray(temp.getHasPossibleAnswers());
				userObj.put("s:hasPossibleAnswers", array);

			}

			questionsArray.put(userObj);
		}

		outGraph.put(questionsArray);

		// get questionnaire
		it = JsonManager.getInstance().questionnaires.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Questionnaire temp = (Questionnaire) pair.getValue();
			userObj = new JSONObject();
			userObj.put("@type", "s:Questionnaire");

			if (temp.getUri() != null)
				userObj.put("@id", temp.getUri().toString());

			if (temp.getHasUserId() != null)
				userObj.put("s:hasUserId", temp.getHasUserId().toString());

			if (temp.getHasEventDate() != null)
				userObj.put("s:hasEventDate", temp.getHasEventDate());

			// object property
			if (temp.getHasUserAnswer() != null) {

				array = new JSONArray();
				array = getObjectArray(temp.getHasUserAnswer());
				userObj.put("s:hasUserAnswer", array);

			}

			questionnairesArray.put(userObj);
		}

		outGraph.put(questionnairesArray);

		// get userAnswers
		it = JsonManager.getInstance().userAnswers.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			UserAnswer temp = (UserAnswer) pair.getValue();
			userObj = new JSONObject();
			userObj.put("@type", "s:UserAnswer");

			if (temp.getUri() != null)
				userObj.put("@id", temp.getUri().toString());

			// object property
			if (temp.getHasAnswer() != null) {

				array = new JSONArray();
				JSONObject answer = new JSONObject();
				answer.put("@id", temp.getHasAnswer());
				array.put(answer);
				userObj.put("s:hasAnswer", array);

			}

			// object property
			if (temp.getHasQuestion() != null) {

				array = new JSONArray();
				JSONObject answer = new JSONObject();
				answer.put("@id", temp.getHasQuestion());
				array.put(answer);
				userObj.put("s:hasQuestion", array);

			}

			userAnswersArray.put(userObj);
		}

		outGraph.put(userAnswersArray);

		outContext.put("u",
				"http://www.AccessibleOntology.com/GenericOntology.owl#");
		outContext.put("p", "http://inlife.ServiceOntology#");
		outContext.put("s", "http://inlife.eu/sensorOntology#");
		outContext.put("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		outContext.put("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
		outContext.put("xsd", "http://www.w3.org/2001/XMLSchema#");
		outPreProc.put("@context", outContext);
		outPreProc.put("@graph", outGraph);

		return outPreProc.toString(5);
	}

	public static String transformInput(String in) throws JSONException {
		String inputString = in;
		JSONTokener inputTokener = new JSONTokener(inputString);
		String username = "";

		JSONObject mmIn = null;

		try {
			mmIn = new JSONObject(inputTokener);
		} catch (org.json.JSONException e) {
		}

		// original:
		JSONObject outPreProc = new JSONObject();
		JSONObject outContext = new JSONObject();
		JSONArray outGraph = new JSONArray();

		if (mmIn.has("User")) {
			JSONObject prefs = (JSONObject) mmIn.getJSONObject("User");

			JSONObject innerObj = new JSONObject();

			username = prefs.get("username").toString();
			innerObj.put("@type", "p:PreferenceSet");
			innerObj.put("p:userName", username);

			outGraph.put(innerObj);
		}

		outContext.put("p", defaultNameSpace);
		outContext.put("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
		outContext.put("xsd", "http://www.w3.org/2001/XMLSchema#");
		outPreProc.put("@context", outContext);
		outPreProc.put("@graph", outGraph);

		return outPreProc.toString(5);
	}

	public static JSONArray getJsonLdObjectArray(JSONArray originalArray)
			throws JSONException {
		JSONArray array = new JSONArray();
		String s = "";
		JSONObject name = null;
		for (int k = 0; k < originalArray.length(); k++) {
			name = new JSONObject();
			s = originalArray.getString(k);
			name.put("@id", s);
			array.put(name);

		}

		return array;
	}

	public static JSONArray getObjectArray(ArrayList<String> originalArray)
			throws JSONException {
		JSONArray array = new JSONArray();
		JSONObject name = null;
		for (String tempString : originalArray) {
			name = new JSONObject();

			name.put("@id", tempString);
			array.put(name);

		}

		return array;
	}

	public static JSONArray getCaregiversInformation(JSONObject cFormalCarer,
			String carerType) throws JSONException {
		// TODO in the case we have an array of formal/informal caregivers
		JSONArray outFormalCarerArray = new JSONArray();
		int counter = 0;
		// get the name
		JSONObject tempObject = new JSONObject();
		String name = cFormalCarer.get("name").toString();
		tempObject.put("p:value", name);
		tempObject.put("p:isMappedTo", "textSMS");
		tempObject.put("p:belongsTo", carerType + "_" + counter);
		tempObject.put("@type", "p:Property");
		outFormalCarerArray.put(tempObject);

		tempObject = new JSONObject();
		// get the phone number
		String phone = cFormalCarer.get("phone").toString();
		tempObject.put("p:value", phone);
		tempObject.put("p:belongsTo", carerType + "_" + counter);
		tempObject.put("p:isMappedTo", "phoneNumber");
		tempObject.put("@type", "p:Property");
		outFormalCarerArray.put(tempObject);

		return outFormalCarerArray;
	}

	public static JSONObject transformUserProfile(JSONObject profile,
			String type) throws JSONException {

		JSONObject userObj = new JSONObject();
		userObj.put("@type", type);

		if (profile.has("uri"))
			userObj.put("@id", profile.get("uri").toString());

		if (profile.has("hasUsername"))
			userObj.put("u:hasUsername", profile.get("hasUsername").toString());

		if (profile.has("hasEmail"))
			userObj.put("u:hasEmail", profile.get("hasEmail").toString());

		if (profile.has("hasPassword"))
			userObj.put("u:hasPassword", profile.get("hasPassword"));

		if (profile.has("hasPhoneNumber"))
			userObj.put("u:hasPhoneNumber", profile.get("hasPhoneNumber")
					.toString());

		if (profile.has("hasLocation"))
			userObj.put("u:hasLocation", profile.get("hasLocation").toString());

		if (profile.has("hasBirthDate"))
			userObj.put("u:hasBirthDate", profile.get("hasBirthDate")
					.toString());

		if (profile.has("hasInsertDate"))
			userObj.put("u:hasInsertDate", profile.get("hasInsertDate")
					.toString());

		if (profile.has("hasUpdateDate"))
			userObj.put("u:hasUpdateDate", profile.get("hasUpdateDate")
					.toString());

		if (profile.has("hasFirstName"))
			userObj.put("u:hasFirstName", profile.get("hasFirstName")
					.toString());

		if (profile.has("hasLastName"))
			userObj.put("u:hasLastName", profile.get("hasLastName").toString());

		return userObj;
	}

	public static JSONObject transformUserProfileToJsonLdProfile(
			Profile profile, String type) throws JSONException {

		JSONObject userObj = new JSONObject();
		userObj.put("@type", type);

		if (profile.getUri() != null)
			userObj.put("@id", profile.getUri().toString());

		if (profile.getHasUsername() != null)
			userObj.put("u:hasUsername", profile.getHasUsername().toString());

		if (profile.getHasEmail() != null)
			userObj.put("u:hasEmail", profile.getHasEmail().toString());

		if (profile.getHasPassword() != null)
			userObj.put("u:hasPassword", profile.getHasPassword().toString());

		if (profile.getHasPhoneNumber() != null)
			userObj.put("u:hasPhoneNumber", profile.getHasPhoneNumber()
					.toString());

		if (profile.getHasLocation() != null)
			userObj.put("u:hasLocation", profile.getHasLocation().toString());

		if (profile.getHasBirthDate() != null)
			userObj.put("u:hasBirthDate", profile.getHasBirthDate().toString());

		if (profile.getHasInsertDate() != null)
			userObj.put("u:hasInsertDate", profile.getHasInsertDate()
					.toString());

		if (profile.getHasUpdateDate() != null)
			userObj.put("u:hasUpdateDate", profile.getHasUpdateDate()
					.toString());

		if (profile.getHasFirstName() != null)
			userObj.put("u:hasFirstName", profile.getHasFirstName().toString());

		if (profile.getHasLastName() != null)
			userObj.put("u:hasLastName", profile.getHasLastName().toString());

		if (profile.getHasLanguage() != null)
			userObj.put("u:hasLanguage", profile.getHasLanguage().toString());

		return userObj;
	}

	public static JSONObject createCombinedServiceObjectForJson(
			ArrayList<QuerySolution> allServices, ArrayList<String> serviceNames)
			throws JSONException {

		ArrayList<Element> objectServices = new ArrayList<Element>();
		JSONObject combinedSolution = new JSONObject();
		JSONObject settings = new JSONObject();
		JSONObject settingsInner = new JSONObject();
		JSONObject combinedInput = new JSONObject();
		JSONArray input = new JSONArray();
		JSONArray mappedVariables = new JSONArray();
		Element el = null;
		JSONObject innerService;
		JSONObject serviceInput;
		boolean appActive = true;
		String toServiceName = "";
		String toVariableName = "";
		String fromServiceName = "";
		String fromVariableName = "";
		String name = "";
		String propName = "";
		String propValue = "";
		JSONObject tempObj = null;
		JSONObject innerMapping = null;
		JSONObject tempMapping = null;
		boolean hasMapping = false;
		int position = -1;
		combinedSolution.put("active", true);

		// settings.put("service-synthesis", settingsInner);
		settingsInner.put("serviceName", "callCombinedServices");
		settingsInner.put("serviceInput", combinedInput);

		ArrayList<QuerySolution> allServicesClone = new ArrayList<QuerySolution>();
		for (QuerySolution temp : allServices) {
			allServicesClone.add(temp);
		}

		for (QuerySolution temp : allServicesClone) {
			if (temp.contains("?serviceName"))
				name = temp.get("?serviceName").toString();

		}

		// create json objects for all services to add the services
		for (String s : serviceNames) {
			serviceInput = new JSONObject();
			el = new Element(s, serviceInput);
			objectServices.add(el);
		}

		// add the properties
		for (QuerySolution temp : allServices) {

			if (temp.contains("?active"))
				appActive = new Boolean(temp.get("?active").toString());
			if (temp.contains("?fromServiceName"))
				fromServiceName = temp.get("?fromServiceName").toString();
			if (temp.contains("?toServiceName"))
				toServiceName = temp.get("?toServiceName").toString();
			if (temp.contains("?toVariableName"))
				toVariableName = temp.get("?toVariableName").toString();
			if (temp.contains("?fromVariableName"))
				fromVariableName = temp.get("?fromVariableName").toString();
			if (temp.contains("?propName"))
				propName = temp.get("?propName").toString();
			if (temp.contains("?serviceName"))
				name = temp.get("?serviceName").toString();
			if (temp.contains("?propValue"))
				propValue = temp.get("?propValue").toString();
			position = -1;

			if (appActive) {
				if (propName.equals(toVariableName)) {

					// if ((screenReaderEnabled &&
					// !toServiceName.equalsIgnoreCase("CallWebAnywhere") &&
					// !fromServiceName
					// .equals("CallWebAnywhere")) || !screenReaderEnabled) {
					// create the mapping
					innerMapping = new JSONObject();
					innerMapping.put("fromServiceName", fromServiceName);
					innerMapping.put("fromVariableName", fromVariableName);
					innerMapping.put("toServiceName", toServiceName);

					if (toVariableName.equalsIgnoreCase("originalURL"))
						toVariableName = "inputUrl";

					innerMapping.put("toVariableName", toVariableName);
					// add the mapping to block of mapping objects
					// check if the mapping was already added to the
					// array
					boolean added = false;
					for (int i = 0; i < mappedVariables.length(); i++) {
						tempMapping = mappedVariables.getJSONObject(i);
						if (tempMapping.toString().equals(
								innerMapping.toString())) {
							added = true;
							break;
						}

					}
					if (!added)
						mappedVariables.put(innerMapping);
				}

			} else {

				tempObj = new JSONObject();
				// find the object to add the properties using the position
				for (int i = 0; i < objectServices.size(); i++) {
					if (objectServices.get(i).getName().equals(name)) {
						tempObj = objectServices.get(i).getServiceInput();
						position = i;
						break;
					}
				}

				tempObj.put(propName, propValue);
				objectServices.remove(position);
				objectServices.add(position, new Element(name, tempObj));
			}
		}

		// check duplicates
		for (QuerySolution temp : allServices) {

			if (temp.contains("?toServiceName"))
				toServiceName = temp.get("?toServiceName").toString();
			if (temp.contains("?propName"))
				propName = temp.get("?propName").toString();
			if (temp.contains("?serviceName"))
				name = temp.get("?serviceName").toString();

			String s1 = "\"toServiceName\":" + "\"" + name + "\"";
			String s2 = "\"toVariableName\":" + "\"" + propName + "\"";

			for (int i = 0; i < objectServices.size(); i++) {
				if (objectServices.get(i).getName().equals(name)) {
					tempObj = objectServices.get(i).getServiceInput();
					position = i;
					break;
				}
			}

			boolean tempFlag = false;
			for (int i = 0; i < mappedVariables.length(); i++) {
				tempMapping = mappedVariables.getJSONObject(i);

				if (tempMapping.toString().contains(s1)
						&& tempMapping.toString().contains(s2)) {

					tempFlag = true;
					break;
				}

			}

			if (tempFlag) {

				Element test = objectServices.get(position);
				if (test.getServiceInput().has(propName))
					test.getServiceInput().remove(propName);

				objectServices.remove(position);
				objectServices.add(position,
						new Element(name, test.getServiceInput()));
			}

		}

		// complete the jsonobjects
		for (Element tempEl : objectServices) {
			innerService = new JSONObject();
			innerService.put("serviceName", tempEl.getName());
			if (!tempEl.getServiceInput().toString().equals("{}"))
				innerService.put("serviceInput", tempEl.getServiceInput());
			input.put(innerService);
		}
		combinedInput.put("input", input);
		combinedInput.put("mappedVariables", mappedVariables);
		combinedSolution.put("settings", settingsInner);

		// System.out.println(combinedSolution.toString(5));
		return combinedSolution;
	}

	/**
	 * Queries all required information from the rdf model and transforms the
	 * result the specific C4a JSON Structure.
	 * 
	 * @param model
	 * @param queries
	 * @return
	 * @throws JSONException
	 */
	public static String transformOutput(Model model, String[] queries)
			throws JSONException {
		/**
		 * mmOut - JSON Object spec the matchmaker output
		 * 
		 */
		JSONObject mmOut = new JSONObject();
		boolean singleService = true;

		ArrayList<QuerySolution> services = new ArrayList<QuerySolution>();
		ArrayList<String> serviceNames = new ArrayList<String>();

		for (String url : queries) {
			Query query = QueryFactory.read(System.getProperty("user.dir")
					+ MatchmakerManager.getInstance().WEBINF_PATH + url);

			QueryExecution qexec = QueryExecutionFactory.create(query, model);

			try {
				ResultSet response = qexec.execSelect();

				JSONObject solution = null;
				JSONObject settings;
				JSONObject extraWrap;
				ArrayList<String> foundServices = new ArrayList<String>();
				JSONObject combinedServicesObject = null;
				boolean combinedObjectAdded = false;

				String queryType = null;

				// find if the output contains single or combined services
				// and update the flag singleServices

				// TODO to make an appropriate format for
				// the input of the services and the synthesis
				if (url.contains("outServices")) {

					Query TempQuery = QueryFactory
							.read(System.getProperty("user.dir")
									+ MatchmakerManager.getInstance().WEBINF_PATH
									+ url);

					QueryExecution Tempqexec = QueryExecutionFactory.create(
							TempQuery, model);

					ResultSet tempResponse = Tempqexec.execSelect();
					while (tempResponse.hasNext()) {
						QuerySolution soln = tempResponse.nextSolution();
						if (soln.contains("?type"))
							queryType = soln.get("?type").toString();

						if (queryType.equals(defaultNameSpace
								+ "ServiceSetting")) {

							// System.out.println("soln: " + soln);

							boolean isActive = Boolean.valueOf(soln.get(
									"?active").toString());
							String serviceName = soln.get("?serviceName")
									.toString();

							// add solution if it is active
							if (!serviceNames.contains(serviceName) && isActive)
								serviceNames.add(soln.get("?serviceName")
										.toString());

							if (isActive)
								services.add(soln);

						}

					}

					if (serviceNames.size() > 1) {
						singleService = false;
						System.out.println("*******COMBINED SERVICES*********");
						System.out.println(serviceNames.size());
						combinedServicesObject = createCombinedServiceObjectForJson(
								services, serviceNames);
					}
				}

				// default
				while (response.hasNext()) {
					QuerySolution soln = response.nextSolution();

					// get query type, e.g. Condition
					if (soln.contains("?type"))
						queryType = soln.get("?type").toString();

					if (queryType.equals(defaultNameSpace + "ServiceSetting")) {

						JSONObject outerObj = null;
						if (mmOut.has("servicesToCall"))
							outerObj = mmOut.getJSONObject("servicesToCall");
						else {
							outerObj = new JSONObject();
							mmOut.put("servicesToCall", outerObj);
							outerObj = mmOut.getJSONObject("servicesToCall");

						}

						String serviceName = "";
						String propName = "";
						String propValue = "";
						JSONObject serviceInput;

						// get SPARQL attributes
						if (soln.contains("?serviceName")) {
							serviceName = soln.get("?serviceName").toString();
						}

						if (soln.contains("?propName")) {
							propName = soln.get("?propName").toString();
						}
						if (soln.contains("?propValue"))
							propValue = soln.get("?propValue").toString();

						if (singleService) {
							// single scenario with services

							System.out
									.println("*******SINGLE SERVICE*********");

							if (soln.contains("?serviceName")) {

								outerObj.put("serviceName", serviceName);

								if (outerObj.has("serviceInput"))
									serviceInput = outerObj
											.getJSONObject("serviceInput");
								else {
									serviceInput = new JSONObject();
									outerObj.put("serviceInput", serviceInput);
									serviceInput = outerObj
											.getJSONObject("serviceInput");
								}

								serviceInput.put(propName, propValue);

							}

						} else if (!singleService && !combinedObjectAdded) {
							// TODO : COMBINED SCENARIO WITH SERVICES
							System.out.println("MPIKE");

							outerObj.put("service-synthesis",
									combinedServicesObject);
							combinedObjectAdded = true;
						}

					}

					if (queryType.equals(defaultNameSpace + "categorySetting")) {

						// System.out.println(soln);

						String toolName = "";
						String toolCategory = "";
						String userTaxonomy = "";
						String similarityValue = "";
						JSONObject toolCategoryObject = null;
						JSONArray recommendedTools = null;
						JSONArray tools = null;

						// get SPARQL attributes
						if (soln.contains("?toolName"))
							toolName = soln.get("?toolName").toString();

						if (soln.contains("?categoryName"))
							toolCategory = soln.get("?categoryName").toString();

						if (soln.contains("?similarityValue"))
							similarityValue = soln.get("?similarityValue")
									.toString();

						if (soln.contains("?taxonomyName"))
							userTaxonomy = soln.get("?taxonomyName").toString();

						// create JSON output structure
						if (!mmOut.has("userTaxonomy")) {
							mmOut.put("userTaxonomy", userTaxonomy);
						}

						// create a JSON array for the tool categories
						if (mmOut.has("recommendedTools"))
							recommendedTools = mmOut
									.getJSONArray("recommendedTools");
						else {
							recommendedTools = new JSONArray();
							mmOut.put("recommendedTools", recommendedTools);
							recommendedTools = mmOut
									.getJSONArray("recommendedTools");
						}

						// create an object for each tool category if
						// it has not been created yet

						if (soln.contains("?categoryName")) {
							toolCategory = soln.get("?categoryName").toString();

							// check if the tool category exists
							for (int i = 0; i < recommendedTools.length(); i++) {

								if (recommendedTools.getJSONObject(i).has(
										"toolCategory")) {

									String tempName = recommendedTools
											.getJSONObject(i)
											.get("toolCategory").toString();

									if (tempName.equalsIgnoreCase(toolCategory)) {
										toolCategoryObject = recommendedTools
												.getJSONObject(i);
										break;
									}
								}
							}

							// create a new tool category
							if (toolCategoryObject == null) {
								toolCategoryObject = new JSONObject();
								toolCategoryObject.put("toolCategory",
										toolCategory);
								toolCategoryObject.put("similarityValue",
										similarityValue);
								recommendedTools.put(toolCategoryObject);
								toolCategoryObject = recommendedTools
										.getJSONObject(recommendedTools
												.length() - 1);
							}

						}

						// add each tool to its category
						if (soln.contains("?toolName")) {
							toolName = soln.get("?toolName").toString();

							if (toolCategoryObject.has("tools")) {
								tools = toolCategoryObject
										.getJSONArray("tools");
							} else {
								tools = new JSONArray();
								toolCategoryObject.put("tools", tools);
								tools = toolCategoryObject
										.getJSONArray("tools");
							}

							tools.put(toolName);
						}

					}

				}
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				qexec.close();
			}
		}

		/**
		 * make it configurable to spec the indent factor (number of spaces to
		 * add to each level of indentation).
		 */
		return mmOut.toString(5);
	}

	private JSONObject objectContains(JSONArray metaData, String metaType,
			String metaScope) throws JSONException {
		boolean typeSupported = false;
		boolean scopeSupported = false;
		JSONObject match = null;
		JSONObject next = null;

		if (metaData.length() > 0) {
			for (int i = 0; i < metaData.length(); i++) {
				next = metaData.getJSONObject(i);

				// type
				if (next.get("type").toString().equals(metaType))
					typeSupported = true;

				// scope
				JSONArray scopeSet = next.getJSONArray("scope");

				for (int j = 0; j < scopeSet.length(); j++) {
					if (scopeSet.get(j).toString().equals(metaScope))
						scopeSupported = true;
				}
			}
			if (typeSupported && scopeSupported)
				match = next;
		}
		return match;
	}

	public boolean contains(JSONArray array, String string)
			throws JSONException {
		boolean r = false;

		if (array.length() > 0) {
			for (int i = 0; i < array.length(); i++) {
				if (array.getString(i).equals(string))
					r = true;
				else
					r = false;
			}
		} else
			r = false;
		return r;
	}

}
