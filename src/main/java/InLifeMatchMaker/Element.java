package InLifeMatchMaker;

import org.json.JSONObject;

public class Element {
	private String name;
	private JSONObject serviceInput;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JSONObject getServiceInput() {
		return serviceInput;
	}

	public void setServiceInput(JSONObject serviceInput) {
		this.serviceInput = serviceInput;
	}

	public Element(String name, JSONObject serviceInput) {
		super();
		this.name = name;
		this.serviceInput = serviceInput;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.getName().equals(((Element) obj).getName())) {
			return true;
		} else
			return false;
	}
}
