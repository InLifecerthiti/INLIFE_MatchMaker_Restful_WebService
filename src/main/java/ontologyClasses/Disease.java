package ontologyClasses;

public class Disease {
	
	private String uri;
	private String hasCode;
	private String hasName;
	
	
	public Disease(String uri, String hasCode, String hasName) {
		super();
		this.uri = uri;
		this.hasCode = hasCode;
		this.hasName = hasName;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getHasCode() {
		return hasCode;
	}

	public void setHasCode(String hasCode) {
		this.hasCode = hasCode;
	}

	public String getHasName() {
		return hasName;
	}

	public void setHasName(String hasName) {
		this.hasName = hasName;
	}

	@Override
	public String toString() {
		return "Disease [uri=" + uri + ", hasCode=" + hasCode + ", hasName="
				+ hasName + "]";
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
		Disease other = (Disease) obj;
		if (hasCode == null) {
			if (other.hasCode != null)
				return false;
		} else if (!hasCode.equals(other.hasCode))
			return false;
		if (hasName == null) {
			if (other.hasName != null)
				return false;
		} else if (!hasName.equals(other.hasName))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}
	
	

}
