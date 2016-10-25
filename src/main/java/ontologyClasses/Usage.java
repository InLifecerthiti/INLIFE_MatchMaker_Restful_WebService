package ontologyClasses;

import java.util.Date;

import InLifeMatchMaker.Utils;

public class Usage {
	
	private String uri;
	private Date hasEventDate;
	private String hasUserId;
	private String hasServiceName;
	public Usage(String uri, String hasEventDate, String hasUserId,
			String hasServiceName) {
		super();
		this.uri = uri;
		this.hasEventDate = Utils.convertStringToDate(hasEventDate);
		this.hasUserId = hasUserId;
		this.hasServiceName = hasServiceName;
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
	public String getHasServiceName() {
		return hasServiceName;
	}
	public void setHasServiceName(String hasServiceName) {
		this.hasServiceName = hasServiceName;
	}
	@Override
	public String toString() {
		return "Usage [uri=" + uri + ", hasEventDate=" + hasEventDate
				+ ", hasUserId=" + hasUserId + ", hasServiceName="
				+ hasServiceName + "]";
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
		Usage other = (Usage) obj;
		if (hasEventDate == null) {
			if (other.hasEventDate != null)
				return false;
		} else if (!hasEventDate.equals(other.hasEventDate))
			return false;
		if (hasServiceName == null) {
			if (other.hasServiceName != null)
				return false;
		} else if (!hasServiceName.equals(other.hasServiceName))
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
