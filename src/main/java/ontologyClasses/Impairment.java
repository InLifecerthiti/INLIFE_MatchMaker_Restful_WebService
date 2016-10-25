package ontologyClasses;

import java.util.ArrayList;

public class Impairment {
	private String uri;
	private String hasCode;
	private String hasName;
	private ArrayList<String> Impairment_linksTo_FunctionalLimitation;

	public Impairment(String uri, String hasCode, String hasName,
			ArrayList<String> impairment_linksTo_FunctionalLimitation) {
		super();
		this.uri = uri;
		this.hasCode = hasCode;
		this.hasName = hasName;
		Impairment_linksTo_FunctionalLimitation = impairment_linksTo_FunctionalLimitation;
	}

	public ArrayList<String> getImpairment_linksTo_FunctionalLimitation() {
		return Impairment_linksTo_FunctionalLimitation;
	}

	public void setImpairment_linksTo_FunctionalLimitation(
			ArrayList<String> impairment_linksTo_FunctionalLimitation) {
		Impairment_linksTo_FunctionalLimitation = impairment_linksTo_FunctionalLimitation;
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
		Impairment other = (Impairment) obj;
		if (Impairment_linksTo_FunctionalLimitation == null) {
			if (other.Impairment_linksTo_FunctionalLimitation != null)
				return false;
		} else if (!Impairment_linksTo_FunctionalLimitation
				.equals(other.Impairment_linksTo_FunctionalLimitation))
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
		return "Impairment [uri=" + uri + ", hasCode=" + hasCode + ", hasName="
				+ hasName + ", Impairment_linksTo_FunctionalLimitation="
				+ Impairment_linksTo_FunctionalLimitation + "]";
	}

}
