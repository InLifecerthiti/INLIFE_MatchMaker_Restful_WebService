package ontologyClasses;

import java.util.ArrayList;

public class Disability {
	private String uri;
	private String hasCode;
	private String hasName;
	private ArrayList<String> Disability_belongsTo_Impairment;

	public Disability(String uri, String hasCode, String hasName,
			ArrayList<String> disability_belongsTo_Impairment) {
		super();
		this.uri = uri;
		this.hasCode = hasCode;
		this.hasName = hasName;
		Disability_belongsTo_Impairment = disability_belongsTo_Impairment;
	}

	public ArrayList<String> getDisability_belongsTo_Impairment() {
		return Disability_belongsTo_Impairment;
	}

	public void setDisability_belongsTo_Impairment(
			ArrayList<String> disability_belongsTo_Impairment) {
		Disability_belongsTo_Impairment = disability_belongsTo_Impairment;
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
		Disability other = (Disability) obj;
		if (Disability_belongsTo_Impairment == null) {
			if (other.Disability_belongsTo_Impairment != null)
				return false;
		} else if (!Disability_belongsTo_Impairment
				.equals(other.Disability_belongsTo_Impairment))
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
		return "Disability [uri=" + uri + ", hasCode=" + hasCode + ", hasName="
				+ hasName + ", Disability_belongsTo_Impairment="
				+ Disability_belongsTo_Impairment + "]";
	}

}
