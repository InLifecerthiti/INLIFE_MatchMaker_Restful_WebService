package InLifeMatchMaker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.google.gson.Gson;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.util.FileManager;

public class MatchmakerManager {
	public static final int FINAL_SERVER_DEPLOYMENT_MODE = 0;
	public static final int JETTY_INTEGRATION_TESTS_MODE = 1;
	public int currentDeploymentMode;
	public String WEBINF_PATH;

	public boolean PERFORM_INTEGRATION_TESTS;
	// static input files
	public String[] semantics;
	public String[] semanticsForRecommendation;
	public String serviceOntology;
	public String userOntology;
	public String sensorOntology;
	public String[] serviceSynthesisRules;
	public String[] recommendServicesRules;
	public String[] defineUserTaxonomyRules;

	public String[] queries;
	public String querryCondPath;
	public String querryAppsPath;
	public String querryMetaDataPath;

	// files for debugging
	public String initialOntologyJSON;
	public String initialJsonInputFilepath;
	public String transformedJsonLDInputFilepath;
	public static String transformedOntologyJsonToJsonLdFilePath;
	public String initialOntModelFilepath;
	public String inferredOntModelFilepath;
	public static String rbmmJsonOutputFilepath;
	// -files for debugging

	public Gson gson;

	private static MatchmakerManager instance = null;

	private MatchmakerManager() {
		// read properties file
		Properties prop = new Properties();
		InputStream configInputStream = null;

		File f = new File(
				System.getProperty("user.dir")
						+ "/../webapps/INLIFE_Matchmaker_Restful_WebService/WEB-INF/config.properties");

		try {
			if (f.exists()) // Deployment mode
			{
				currentDeploymentMode = FINAL_SERVER_DEPLOYMENT_MODE;
				configInputStream = new FileInputStream(
						System.getProperty("user.dir")
								+ "/../webapps/INLIFE_Matchmaker_Restful_WebService/WEB-INF/config.properties");

				// read properties file
				prop.load(configInputStream);

				WEBINF_PATH = prop.getProperty("DEPLOYMENT_WEBINF_PATH");
			} else // Jetty integration tests
			{
				currentDeploymentMode = JETTY_INTEGRATION_TESTS_MODE;
				configInputStream = new FileInputStream(
						System.getProperty("user.dir")
								+ "\\src\\main\\webapp\\WEB-INF\\config.properties");

				// read properties file
				prop.load(configInputStream);

				WEBINF_PATH = prop.getProperty("JETTY_WEBINF_PATH");
			}

			// static input files: semantics, rules according to the case
			semantics = prop.getProperty("semantics").split(";");

			semanticsForRecommendation = prop.getProperty(
					"semanticsForRecommendation").split(";");

			serviceOntology = semantics[0];

			userOntology = semantics[1];

			sensorOntology = semantics[2];

			serviceSynthesisRules = prop.getProperty("serviceSynthesisRules")
					.split(";");

			recommendServicesRules = prop.getProperty("recommendServicesRules")
					.split(";");

			defineUserTaxonomyRules = prop.getProperty(
					"defineUserTaxonomyRules").split(";");

			// queries

			if (prop.getProperty("queries").contains(";"))
				queries = prop.getProperty("queries").split(";");
			else
				queries[0] = prop.getProperty("queries");

			// debug
			initialJsonInputFilepath = System.getProperty("user.dir")
					+ WEBINF_PATH
					+ prop.getProperty("initialJsonInputFilepath");
			transformedJsonLDInputFilepath = System.getProperty("user.dir")
					+ WEBINF_PATH
					+ prop.getProperty("transformedJsonLDInputFilepath");

			transformedOntologyJsonToJsonLdFilePath = System
					.getProperty("user.dir")
					+ WEBINF_PATH
					+ prop.getProperty("transformedOntologyJsonToJsonLdFilePath");

			initialOntModelFilepath = System.getProperty("user.dir")
					+ WEBINF_PATH + prop.getProperty("initialOntModelFilepath");
			inferredOntModelFilepath = System.getProperty("user.dir")
					+ WEBINF_PATH
					+ prop.getProperty("inferredOntModelFilepath");
			rbmmJsonOutputFilepath = System.getProperty("user.dir")
					+ WEBINF_PATH + prop.getProperty("rbmmJsonOutputFilepath");
			
			// -debug

			PERFORM_INTEGRATION_TESTS = Boolean.parseBoolean(prop
					.getProperty("PERFORM_INTEGRATION_TESTS"));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (configInputStream != null) {
				try {
					configInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		gson = new Gson();
	}

	public static MatchmakerManager getInstance() {
		if (instance == null)
			instance = new MatchmakerManager();
		return instance;
	}

	public String runJSONLDTests(String tmpInputJsonStr, String[] ruleFiles)
			throws IOException, JSONException {
		String resJsonStr = "OK";

		/*************************/
		/* PREPROCESSING - Start */
		/*************************/

		// debug - write initial JSON input to file
		String content = Utils.jsonPrettyPrint(tmpInputJsonStr);
		Utils.writeFile(initialJsonInputFilepath, content);

		// transform input to JSON-LD
		String transIn = TransformerManager.transformInput(tmpInputJsonStr);

		// debug - write transformed JSON-LD input to file
		Utils.writeFile(transformedJsonLDInputFilepath,
				Utils.jsonPrettyPrint(transIn));

		// populate all JSON-LD input to a model
		OntologyManager.getInstance().populateInitialModel(transIn, semantics);

		// debug - write initial model to .owl
		Utils.writeOntologyModelToFile(OntologyManager._dmodel,
				initialOntModelFilepath);

		/***********************/
		/* PREPROCESSING - End */
		/***********************/

		/*******************************/
		/* INFER CONFIGURATION - Start */
		/*******************************/
		// run the rules
		Model imodel = inferConfiguration(OntologyManager._dmodel, ruleFiles);

		// debug - write inferred model to .owl
		Utils.writeOntologyModelToFile(imodel, inferredOntModelFilepath);

		/*****************************/
		/* INFER CONFIGURATION - End */
		/*****************************/

		/***************************/
		/* POST PROCESSING - Start */
		/***************************/

		// TODO create MM output
		resJsonStr = TransformerManager.transformOutput(imodel, queries);
		//
		// // debug - write final RBMM JSON output to file
		Utils.writeFile(rbmmJsonOutputFilepath,
				Utils.jsonPrettyPrint(resJsonStr));

		/*************************/
		/* POST PROCESSING - End */
		/*************************/

		return resJsonStr;
	}

	public String runRules(String tmpInputJsonStr, String[] ruleFiles)
			throws IOException, JSONException {
		String resJsonStr = "OK";

		/*************************/
		/* PREPROCESSING - Start */
		/*************************/

		// 1. debug - write initial JSON input to file
		String content = Utils.jsonPrettyPrint(tmpInputJsonStr);
		Utils.writeFile(initialJsonInputFilepath, content);

		// 2. transform input to JSON-LD
		String transIn = TransformerManager.transformInput(tmpInputJsonStr);

		// 3. debug - write transformed JSON-LD input to file
		Utils.writeFile(transformedJsonLDInputFilepath,
				Utils.jsonPrettyPrint(transIn));

		// 4. transform json ontology to json ld
//		String transformedInputOntology = TransformerManager
//				.transformJsonInputToJsonLd(MatchmakerManager.getInstance().initialOntologyJSON);
		
		String transformedInputOntology = TransformerManager
				.transformJsonInputToJsonLdNew();

		if (transformedInputOntology != null)
			OntologyManager.getInstance().transformedOntologyInput = transformedInputOntology;

		// 5. write transformed ontology to json ld into a file
		Utils.writeFile(
				MatchmakerManager.transformedOntologyJsonToJsonLdFilePath,
				Utils.jsonPrettyPrint(transformedInputOntology));

		String transformedJSONInput = OntologyManager.getInstance().transformedOntologyInput;

		// 6. populate all JSON-LD input to a model
		OntologyManager.getInstance().populateInitialModelForRecomm(transIn,
				transformedJSONInput);

		// 7. debug - write initial model to .owl
		Utils.writeOntologyModelToFile(OntologyManager._dmodel,
				initialOntModelFilepath);

		/***********************/
		/* PREPROCESSING - End */
		/***********************/

		/*******************************/
		/* INFER CONFIGURATION - Start */
		/*******************************/
		// 8. run the rules
		Model imodel = inferConfiguration(OntologyManager._dmodel, ruleFiles);

		// debug - write inferred model to .owl
		Utils.writeOntologyModelToFile(imodel, inferredOntModelFilepath);

		/*****************************/
		/* INFER CONFIGURATION - End */
		/*****************************/

		/***************************/
		/* POST PROCESSING - Start */
		/***************************/
		resJsonStr = TransformerManager.transformOutput(imodel, queries);
		//
		// // debug - write final RBMM JSON output to file
		Utils.writeFile(rbmmJsonOutputFilepath,
				Utils.jsonPrettyPrint(resJsonStr));

		/*************************/
		/* POST PROCESSING - End */
		/*************************/

		return resJsonStr;
	}

	public Model inferConfiguration(Model model, String[] ruleFiles) {

		MatchmakerManager.getInstance();

		for (String path : ruleFiles) {
			File f = new File(System.getProperty("user.dir") + WEBINF_PATH
					+ path);
			if (f.exists()) {
				List<Rule> rules = Rule.rulesFromURL("file:" + f.getPath());

				// System.out.println(rules.toString());

				GenericRuleReasoner r = new GenericRuleReasoner(rules);

				InfModel infModel = ModelFactory.createInfModel(r, model);

				infModel.prepare();

				Model deducedModel = infModel.getDeductionsModel();

				model.add(deducedModel);

				// deducedModel.write(System.out, "N-TRIPLE");
				// model.write(System.out, "N-TRIPLE");
			} else
				System.out.println("Rule file does not exist: " + path);
		}
		return model;
	}
}
