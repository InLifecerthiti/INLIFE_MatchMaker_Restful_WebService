package InLifeMatchMaker;

import java.io.IOException;

import junit.framework.TestCase;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TestIT extends TestCase {

	public void test_() {

		if (MatchmakerManager.getInstance().PERFORM_INTEGRATION_TESTS) {

			_sendOntology();
			_runRules();
			_recommendServices();
			_defineUserTaxonomy();
			_userIsRegistered();
			_userIsAuthorized();
			_userIsRelatedToCaregiver();
			_isCaregiver();
			_getUser();
			_getConnectedCaregivers();
			_getConnectedUsers();
			_getLastUseOfService();
			_getNumberOfUsesOfService();
			_createUser();

		}

	}

	private void _sendOntology() {
		System.out
				.println("\n*****************************************************");
		System.out.println("* Testing Call '*******************");
		System.out
				.println("*******************************************************");

		String filepathIN = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/preferences/inlifeData.json";
		String filepathExpectedOUT1 = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/expectedTestOutcomes/sendOntologyOUT.json";

		performTest(filepathIN, filepathExpectedOUT1, "sendOntology");
	}

	private void _runRules() {
		System.out
				.println("\n*****************************************************");
		System.out.println("* Testing Call '*******************");
		System.out
				.println("*******************************************************");

		String filepathIN = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/preferences/testPayload.json";
		String filepathExpectedOUT1 = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/expectedTestOutcomes/runRulesOUT.json";

		performTest(filepathIN, filepathExpectedOUT1, "runRules");
	}

	private void _recommendServices() {
		System.out
				.println("\n*****************************************************");
		System.out.println("* Testing Call '*******************");
		System.out
				.println("*******************************************************");

		String filepathIN = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/preferences/testPayload.json";
		String filepathExpectedOUT1 = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/expectedTestOutcomes/recommendServicesOUT.json";

		performTest(filepathIN, filepathExpectedOUT1, "recommendServices");
	}

	private void _defineUserTaxonomy() {
		System.out
				.println("\n*****************************************************");
		System.out.println("* Testing Call '*******************");
		System.out
				.println("*******************************************************");

		String filepathIN = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/preferences/testPayload.json";
		String filepathExpectedOUT1 = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/expectedTestOutcomes/defineUserTaxonomyOUT.json";

		performTest(filepathIN, filepathExpectedOUT1, "defineUserTaxonomy");
	}

	private void _userIsRegistered() {
		System.out
				.println("\n*****************************************************");
		System.out.println("* Testing Call '*******************");
		System.out
				.println("*******************************************************");

		String filepathIN = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/preferences/userIsRegistered.json";
		String filepathExpectedOUT1 = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/expectedTestOutcomes/userIsRegisteredOUT.json";

		performTest(filepathIN, filepathExpectedOUT1, "userIsRegistered");
	}

	private void _userIsAuthorized() {
		System.out
				.println("\n*****************************************************");
		System.out.println("* Testing Call '*******************");
		System.out
				.println("*******************************************************");

		String filepathIN = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/preferences/userIsAuthorized.json";
		String filepathExpectedOUT1 = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/expectedTestOutcomes/userIsAuthorizedOUT.json";

		performTest(filepathIN, filepathExpectedOUT1, "userIsAuthorized");
	}

	private void _userIsRelatedToCaregiver() {
		System.out
				.println("\n*****************************************************");
		System.out.println("* Testing Call '*******************");
		System.out
				.println("*******************************************************");

		String filepathIN = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/preferences/userIsRelatedToCaregiver.json";
		String filepathExpectedOUT1 = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/expectedTestOutcomes/userIsRelatedToCaregiverOUT.json";

		performTest(filepathIN, filepathExpectedOUT1,
				"userIsRelatedToCaregiver");
	}

	private void _isCaregiver() {
		System.out
				.println("\n*****************************************************");
		System.out.println("* Testing Call '*******************");
		System.out
				.println("*******************************************************");

		String filepathIN = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/preferences/isCaregiver.json";
		String filepathExpectedOUT1 = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/expectedTestOutcomes/isCaregiverOUT.json";

		performTest(filepathIN, filepathExpectedOUT1, "isCaregiver");
	}

	private void _getUser() {
		System.out
				.println("\n*****************************************************");
		System.out.println("* Testing Call '*******************");
		System.out
				.println("*******************************************************");

		String filepathIN = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/preferences/getUser.json";
		String filepathExpectedOUT1 = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/expectedTestOutcomes/getUserOUT.json";

		performTest(filepathIN, filepathExpectedOUT1, "getUser");
	}

	private void _getConnectedCaregivers() {
		System.out
				.println("\n*****************************************************");
		System.out.println("* Testing Call '*******************");
		System.out
				.println("*******************************************************");

		String filepathIN = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/preferences/getConnectedCaregivers.json";
		String filepathExpectedOUT1 = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/expectedTestOutcomes/getConnectedCaregiversOUT.json";

		performTest(filepathIN, filepathExpectedOUT1, "getConnectedCaregivers");
	}

	private void _getConnectedUsers() {
		System.out
				.println("\n*****************************************************");
		System.out.println("* Testing Call '*******************");
		System.out
				.println("*******************************************************");

		String filepathIN = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/preferences/getConnectedUsers.json";
		String filepathExpectedOUT1 = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/expectedTestOutcomes/getConnectedUsersOUT.json";

		performTest(filepathIN, filepathExpectedOUT1, "getConnectedUsers");
	}

	private void _getLastUseOfService() {
		System.out
				.println("\n*****************************************************");
		System.out.println("* Testing Call '*******************");
		System.out
				.println("*******************************************************");

		String filepathIN = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/preferences/testStatistics.json";
		String filepathExpectedOUT1 = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/expectedTestOutcomes/getLastUseOfServiceOUT.json";

		performTest(filepathIN, filepathExpectedOUT1, "getLastUseOfService");
	}

	private void _getNumberOfUsesOfService() {
		System.out
				.println("\n*****************************************************");
		System.out.println("* Testing Call '*******************");
		System.out
				.println("*******************************************************");

		String filepathIN = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/preferences/testStatistics.json";
		String filepathExpectedOUT1 = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/expectedTestOutcomes/getNumberOfUsesOfServiceOUT.json";

		performTest(filepathIN, filepathExpectedOUT1,
				"getNumberOfUsesOfService");
	}

	private void _createUser() {
		System.out
				.println("\n*****************************************************");
		System.out.println("* Testing Call '*******************");
		System.out
				.println("*******************************************************");

		String filepathIN = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/preferences/createUser.json";
		String filepathExpectedOUT1 = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/testData/expectedTestOutcomes/createUserOUT.json";

		performTest(filepathIN, filepathExpectedOUT1, "createUser");
	}

	private void performTest(String filepathIN, String filepathExpectedOUT1,
			String test) {

		String filepathActualOUT = System.getProperty("user.dir")
				+ "/src/main/webapp/WEB-INF/debug/5_RBMMJsonOutput.json";

		String inputJsonStr = "";
		String actualOutputStr = null;
		String expectedOutputJsonStr1 = null;

		// read input & expected output
		try {
			inputJsonStr = Utils.readFile(filepathIN);
			expectedOutputJsonStr1 = Utils.readFile(filepathExpectedOUT1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Client client = Client.create();
		WebResource webResource = client
				.resource("http://localhost:8080/INLIFE/" + test);

		ClientResponse response = webResource.type("application/json").post(
				ClientResponse.class, inputJsonStr);

		String output = response.getEntity(String.class);

		if (!test.equals("sendOntology")) {

			// read actual output
			try {
				actualOutputStr = Utils.readFile(filepathActualOUT);
			} catch (IOException e) {
				e.printStackTrace();
			}

//			System.out.println(actualOutputStr);
//
//			System.out.println("-----------------------------");
//
//			System.out.println(expectedOutputJsonStr1);

			boolean outputIsSimilarToOneOfTheExpected = false;
			if (actualOutputStr.equals(expectedOutputJsonStr1))
				outputIsSimilarToOneOfTheExpected = true;
			else {
				System.out.println("\n* ERROR -> " + test
						+ " INTEGRATION TEST FAILED!");
				// System.exit(-1);
			}

			assertEquals(outputIsSimilarToOneOfTheExpected, true);
		}

	}

}
