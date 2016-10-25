package ontologyClasses;

import java.util.ArrayList;

public class Tool {
	
	private String uri;
	private String toolHasName;
	private String toolHasUsername;
	private String toolHasPassword;
	private String toolHasDescription;
	private String hasImageURL;
	private String toolHasId;
	private String toolHasUrl;
	private ArrayList<String> toolBelongsToVendor;
	private ArrayList<String> toolHasService;
	
	public Tool(String uri,String toolHasName, String toolHasUsername,
			String toolHasPassword, String toolHasDescription,
			String hasImageURL, String toolHasId, String toolHasUrl,
			ArrayList<String> toolBelongsToVendor,
			ArrayList<String> toolHasService) {
		super();
		this.uri = uri.trim();
		this.toolHasName = toolHasName.trim();
		this.toolHasUsername = toolHasUsername.trim();
		this.toolHasPassword = toolHasPassword.trim();
		this.toolHasDescription = toolHasDescription.trim();
		this.hasImageURL = hasImageURL.trim();
		this.toolHasId = toolHasId.trim();
		this.toolHasUrl = toolHasUrl.trim();
		this.toolBelongsToVendor = toolBelongsToVendor;
		this.toolHasService = toolHasService;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getToolHasName() {
		return toolHasName;
	}

	public void setToolHasName(String toolHasName) {
		this.toolHasName = toolHasName;
	}

	public String getToolHasUsername() {
		return toolHasUsername;
	}

	public void setToolHasUsername(String toolHasUsername) {
		this.toolHasUsername = toolHasUsername;
	}

	public String getToolHasPassword() {
		return toolHasPassword;
	}

	public void setToolHasPassword(String toolHasPassword) {
		this.toolHasPassword = toolHasPassword;
	}

	public String getToolHasDescription() {
		return toolHasDescription;
	}

	public void setToolHasDescription(String toolHasDescription) {
		this.toolHasDescription = toolHasDescription;
	}

	public String getHasImageURL() {
		return hasImageURL;
	}

	public void setHasImageURL(String hasImageURL) {
		this.hasImageURL = hasImageURL;
	}

	public String getToolHasId() {
		return toolHasId;
	}

	public void setToolHasId(String toolHasId) {
		this.toolHasId = toolHasId;
	}

	public String getToolHasUrl() {
		return toolHasUrl;
	}

	public void setToolHasUrl(String toolHasUrl) {
		this.toolHasUrl = toolHasUrl;
	}

	public ArrayList<String> getToolBelongsToVendor() {
		return toolBelongsToVendor;
	}

	public void setToolBelongsToVendor(ArrayList<String> toolBelongsToVendor) {
		this.toolBelongsToVendor = toolBelongsToVendor;
	}

	public ArrayList<String> getToolHasService() {
		return toolHasService;
	}

	public void setToolHasService(ArrayList<String> toolHasService) {
		this.toolHasService = toolHasService;
	}

	@Override
	public String toString() {
		return "Tool [uri=" + uri + ", toolHasName=" + toolHasName
				+ ", toolHasUsername=" + toolHasUsername + ", toolHasPassword="
				+ toolHasPassword + ", toolHasDescription="
				+ toolHasDescription + ", hasImageURL=" + hasImageURL
				+ ", toolHasId=" + toolHasId + ", toolHasUrl=" + toolHasUrl
				+ ", toolBelongsToVendor=" + toolBelongsToVendor
				+ ", toolHasService=" + toolHasService + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tool other = (Tool) obj;
		if (hasImageURL == null) {
			if (other.hasImageURL != null)
				return false;
		} else if (!hasImageURL.equals(other.hasImageURL))
			return false;
		if (toolBelongsToVendor == null) {
			if (other.toolBelongsToVendor != null)
				return false;
		} else if (!toolBelongsToVendor.equals(other.toolBelongsToVendor))
			return false;
		if (toolHasDescription == null) {
			if (other.toolHasDescription != null)
				return false;
		} else if (!toolHasDescription.equals(other.toolHasDescription))
			return false;
		if (toolHasId == null) {
			if (other.toolHasId != null)
				return false;
		} else if (!toolHasId.equals(other.toolHasId))
			return false;
		if (toolHasName == null) {
			if (other.toolHasName != null)
				return false;
		} else if (!toolHasName.equals(other.toolHasName))
			return false;
		if (toolHasPassword == null) {
			if (other.toolHasPassword != null)
				return false;
		} else if (!toolHasPassword.equals(other.toolHasPassword))
			return false;
		if (toolHasService == null) {
			if (other.toolHasService != null)
				return false;
		} else if (!toolHasService.equals(other.toolHasService))
			return false;
		if (toolHasUrl == null) {
			if (other.toolHasUrl != null)
				return false;
		} else if (!toolHasUrl.equals(other.toolHasUrl))
			return false;
		if (toolHasUsername == null) {
			if (other.toolHasUsername != null)
				return false;
		} else if (!toolHasUsername.equals(other.toolHasUsername))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}
	

}
