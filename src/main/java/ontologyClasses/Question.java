package ontologyClasses;

import java.util.ArrayList;

public class Question {

	private String uri;
	private String hasLabel;
	private String hasName;
	private ArrayList<String> hasPossibleAnswers;

	public Question(String uri, String hasLabel, String hasName,
			ArrayList<String> hasPossibleAnswers) {
		super();
		this.uri = uri;
		this.hasLabel = hasLabel;
		this.hasName = hasName;
		this.hasPossibleAnswers = hasPossibleAnswers;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getHasLabel() {
		return hasLabel;
	}

	public void setHasLabel(String hasLabel) {
		this.hasLabel = hasLabel;
	}

	public String getHasName() {
		return hasName;
	}

	public void setHasName(String hasName) {
		this.hasName = hasName;
	}

	public ArrayList<String> getHasPossibleAnswers() {
		return hasPossibleAnswers;
	}

	public void setHasPossibleAnswers(ArrayList<String> hasPossibleAnswers) {
		this.hasPossibleAnswers = hasPossibleAnswers;
	}

	@Override
	public String toString() {
		return "Question [uri=" + uri + ", hasLabel=" + hasLabel + ", hasName="
				+ hasName + ", hasPossibleAnswers=" + hasPossibleAnswers + "]";
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
		Question other = (Question) obj;
		if (hasLabel == null) {
			if (other.hasLabel != null)
				return false;
		} else if (!hasLabel.equals(other.hasLabel))
			return false;
		if (hasName == null) {
			if (other.hasName != null)
				return false;
		} else if (!hasName.equals(other.hasName))
			return false;
		if (hasPossibleAnswers == null) {
			if (other.hasPossibleAnswers != null)
				return false;
		} else if (!hasPossibleAnswers.equals(other.hasPossibleAnswers))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}

}
