package ontologyClasses;

public class ConnectionDetails {
	
	private String uri;
	private String hasURL;
	private String hasPort;
	private String hasHost;
	
	public ConnectionDetails(String uri, String hasURL, String hasPort,
			String hasHost) {
		super();
		this.uri = uri;
		this.hasURL = hasURL;
		this.hasPort = hasPort;
		this.hasHost = hasHost;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getHasURL() {
		return hasURL;
	}

	public void setHasURL(String hasURL) {
		this.hasURL = hasURL;
	}

	public String getHasPort() {
		return hasPort;
	}

	public void setHasPort(String hasPort) {
		this.hasPort = hasPort;
	}

	public String getHasHost() {
		return hasHost;
	}

	public void setHasHost(String hasHost) {
		this.hasHost = hasHost;
	}

	@Override
	public String toString() {
		return "ConnectionDetails [uri=" + uri + ", hasURL=" + hasURL
				+ ", hasPort=" + hasPort + ", hasHost=" + hasHost + "]";
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
		ConnectionDetails other = (ConnectionDetails) obj;
		if (hasHost == null) {
			if (other.hasHost != null)
				return false;
		} else if (!hasHost.equals(other.hasHost))
			return false;
		if (hasPort == null) {
			if (other.hasPort != null)
				return false;
		} else if (!hasPort.equals(other.hasPort))
			return false;
		if (hasURL == null) {
			if (other.hasURL != null)
				return false;
		} else if (!hasURL.equals(other.hasURL))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}
	
	

}
