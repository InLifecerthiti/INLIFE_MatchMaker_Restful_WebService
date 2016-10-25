package InLifeMatchMaker;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.hp.hpl.jena.rdf.model.Model;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Utils {

	public static String hashMD5(String s) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(s.getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
					.substring(1));
		}

		return sb.toString();
	}

	public static void writeFile(String path, String content) {
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(path), "UTF-8"));
			out.write(content);
			out.close();
			System.out.println("* Generated file: " + path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String readFile(String pathname) throws IOException {
		String fileContents = "";
		FileReader fileReader = new FileReader(pathname);
		int i;
		while ((i = fileReader.read()) != -1) {
			char ch = (char) i;
			fileContents = fileContents + ch;
		}
		return fileContents;
	}

	public static void writeOntologyModelToFile(Model tmpModel,
			String tmpFilepath) {
		FileWriter out = null;
		try {
			out = new FileWriter(tmpFilepath);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try {
			tmpModel.write(out, "RDF/XML");
		} finally {
			try {
				out.close();
				System.out.println("* Generated file: " + tmpFilepath);
			} catch (IOException closeException) {
				closeException.printStackTrace();
			}
		}
	}

	public static String jsonPrettyPrint(String tmpJsonStr) {
		String res = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			res = mapper.defaultPrettyPrintingWriter().writeValueAsString(
					MatchmakerManager.getInstance().gson.fromJson(tmpJsonStr,
							Object.class));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return res;
	}

	public static Object transformValueSpace(Object o) {
		Object transVal = o;

		try {
			int i = Integer.parseInt(o.toString());
			transVal = i;
		} catch (NumberFormatException e) {
		}

		try {
			double i = Double.parseDouble(o.toString());
			transVal = Math.round(10.0 * i) / 10.0;

		} catch (NumberFormatException e) {
		}

		if (o.toString().equals("true") || o.toString().equals("false"))
			transVal = new Boolean(o.toString());

		return transVal;
	}

	public static String encrypt(String text) throws Exception {
		MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
		msgDigest.update(text.getBytes("UTF-8"));

		byte rawByte[] = msgDigest.digest();

		// convert the byte to hex format method 1
		String ret = new java.math.BigInteger(1, rawByte).toString(16);
		String zeros = "";
		for (int i = 0; i < 64 - ret.length(); i++)
			zeros += "0";

		return zeros + ret;
	}

	public static String convertDateToSpecificFormat(Date initialDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss");
		String reportDate = "";
		if (initialDate != null)
			reportDate = formatter.format(initialDate);

		return reportDate;
	}

	public static Date convertStringToDate(String dateInString) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss");
		Date date = null;

		try {

			date = formatter.parse(dateInString.replaceAll("Z$", "+0000"));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static void main(String args[]) throws Exception {
		// System.out.println(encrypt("1234"));
		Date date = Calendar.getInstance().getTime();

		String s = convertDateToSpecificFormat(date);
		Date newDate = convertStringToDate(s);
		System.out.println(newDate);
		// System.out.println(convertStringToDate("2011-12-03T10:15:30"));
	}

	public static Response addHTTPCode(String outputJsonStr) {

		if (outputJsonStr.contains("Tool is not registered")
				|| outputJsonStr.contains("Wrong password for tool")) {
			System.out.println("Status:401");
			return Response.status(401).entity(outputJsonStr).build();
		} else {
			System.out.println("Status:200");
			return Response.status(200).entity(outputJsonStr).build();
		}
	}

	public static GsonBuilder getGsonBuilder() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class,
				new JsonDeserializer<Date>() {
					DateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd'T'HH:mm:ss");

					@Override
					public Date deserialize(final JsonElement json,
							final Type typeOfT,
							final JsonDeserializationContext context)
							throws JsonParseException {
						try {
							return df.parse(json.getAsString());
						} catch (ParseException e) {
							return null;
						}
					}
				});

		return gsonBuilder;
	}

}
