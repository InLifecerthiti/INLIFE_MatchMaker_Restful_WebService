package ontologyClasses;

import java.util.ArrayList;
import java.util.Date;

import InLifeMatchMaker.Utils;

public class Questionnaire {

	private String uri;
	private Date hasEventDate;
	private String hasUserId;
	private ArrayList<String> hasUserAnswer;

	public Questionnaire(String uri, String hasEventDate, String hasUserId,
			ArrayList<String> hasUserAnswer) {
		super();
		this.uri = uri;
		this.hasEventDate = Utils.convertStringToDate(hasEventDate);
		this.hasUserId = hasUserId;
		this.hasUserAnswer = hasUserAnswer;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Date getHasEventDate() {
		return hasEventDate;
	}

	public void setHasEventDate(Date hasEventDate) {
		this.hasEventDate = hasEventDate;
	}

	public String getHasUserId() {
		return hasUserId;
	}

	public void setHasUserId(String hasUserId) {
		this.hasUserId = hasUserId;
	}

	public ArrayList<String> getHasUserAnswer() {
		return hasUserAnswer;
	}

	public void setHasUserAnswer(ArrayList<String> hasUserAnswer) {
		this.hasUserAnswer = hasUserAnswer;
	}

	@Override
	public String toString() {
		return "Questionnaire [uri=" + uri + ", hasEventDate=" + hasEventDate
				+ ", hasUserId=" + hasUserId + ", hasUserAnswer="
				+ hasUserAnswer + "]";
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
		Questionnaire other = (Questionnaire) obj;
		if (hasEventDate == null) {
			if (other.hasEventDate != null)
				return false;
		} else if (!hasEventDate.equals(other.hasEventDate))
			return false;
		if (hasUserAnswer == null) {
			if (other.hasUserAnswer != null)
				return false;
		} else if (!hasUserAnswer.equals(other.hasUserAnswer))
			return false;
		if (hasUserId == null) {
			if (other.hasUserId != null)
				return false;
		} else if (!hasUserId.equals(other.hasUserId))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}

}
