package ontologyClasses;

public class ParameterTerm {
	
	private String uri;
	private String hasName;
	private String hasType;
	public ParameterTerm(String uri, String hasName, String hasType) {
		super();
		this.uri = uri;
		this.hasName = hasName;
		this.hasType = hasType;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getHasName() {
		return hasName;
	}
	public void setHasName(String hasName) {
		this.hasName = hasName;
	}
	public String getHasType() {
		return hasType;
	}
	public void setHasType(String hasType) {
		this.hasType = hasType;
	}
	@Override
	public String toString() {
		return "ParameterTerm [uri=" + uri + ", hasName=" + hasName
				+ ", hasType=" + hasType + "]";
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
		ParameterTerm other = (ParameterTerm) obj;
		if (hasName == null) {
			if (other.hasName != null)
				return false;
		} else if (!hasName.equals(other.hasName))
			return false;
		if (hasType == null) {
			if (other.hasType != null)
				return false;
		} else if (!hasType.equals(other.hasType))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}
	
	
	

}
