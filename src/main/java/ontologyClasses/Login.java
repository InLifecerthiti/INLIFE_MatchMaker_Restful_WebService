package ontologyClasses;

import java.util.Date;

public class Login {

	private String uri;
	private Date hasEventDate;
	private String ghasUserId;

	public Login(String uri, Date hasEventDate, String ghasUserId) {
		super();
		this.uri = uri;
		this.hasEventDate = hasEventDate;
		this.ghasUserId = ghasUserId;
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

	public String getGhasUserId() {
		return ghasUserId;
	}

	public void setGhasUserId(String ghasUserId) {
		this.ghasUserId = ghasUserId;
	}

	@Override
	public String toString() {
		return "Login [uri=" + uri + ", hasEventDate=" + hasEventDate
				+ ", ghasUserId=" + ghasUserId + "]";
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
		Login other = (Login) obj;
		if (ghasUserId == null) {
			if (other.ghasUserId != null)
				return false;
		} else if (!ghasUserId.equals(other.ghasUserId))
			return false;
		if (hasEventDate == null) {
			if (other.hasEventDate != null)
				return false;
		} else if (!hasEventDate.equals(other.hasEventDate))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}

}
