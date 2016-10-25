package ontologyClasses;

import java.util.ArrayList;

public class FunctionalLimitation {

	private String uri;
	private String hasCode;
	private String hasName;
	private ArrayList<String> FunctionalLimitation_belongsTo_Disability;

	public FunctionalLimitation(String uri, String hasCode, String hasName,
			ArrayList<String> functionalLimitation_belongsTo_Disability) {
		super();
		this.uri = uri;
		this.hasCode = hasCode;
		this.hasName = hasName;
		FunctionalLimitation_belongsTo_Disability = functionalLimitation_belongsTo_Disability;
	}

	public ArrayList<String> getFunctionalLimitation_belongsTo_Disability() {
		return FunctionalLimitation_belongsTo_Disability;
	}

	public void setFunctionalLimitation_belongsTo_Disability(
			ArrayList<String> functionalLimitation_belongsTo_Disability) {
		FunctionalLimitation_belongsTo_Disability = functionalLimitation_belongsTo_Disability;
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
		FunctionalLimitation other = (FunctionalLimitation) obj;
		if (FunctionalLimitation_belongsTo_Disability == null) {
			if (other.FunctionalLimitation_belongsTo_Disability != null)
				return false;
		} else if (!FunctionalLimitation_belongsTo_Disability
				.equals(other.FunctionalLimitation_belongsTo_Disability))
			return false;
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

	@Override
	public String toString() {
		return "FunctionalLimitation [uri=" + uri + ", hasCode=" + hasCode
				+ ", hasName=" + hasName
				+ ", FunctionalLimitation_belongsTo_Disability="
				+ FunctionalLimitation_belongsTo_Disability + "]";
	}

}
