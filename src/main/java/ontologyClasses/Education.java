package ontologyClasses;

public class Education {
	
	private String uri;
	private String hasName;
	private int hasValue;
	
	
	public Education(String uri, String hasName, int hasValue) {
		super();
		this.uri = uri;
		this.hasName = hasName;
		this.hasValue = hasValue;
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
	public int getHasValue() {
		return hasValue;
	}
	public void setHasValue(int hasValue) {
		this.hasValue = hasValue;
	}
	@Override
	public String toString() {
		return "Education [uri=" + uri + ", hasName=" + hasName + ", hasValue="
				+ hasValue + "]";
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
		Education other = (Education) obj;
		if (hasName == null) {
			if (other.hasName != null)
				return false;
		} else if (!hasName.equals(other.hasName))
			return false;
		if (hasValue != other.hasValue)
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}
	
	

}
