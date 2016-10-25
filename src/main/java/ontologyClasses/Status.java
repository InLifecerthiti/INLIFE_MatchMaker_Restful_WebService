package ontologyClasses;

import java.util.ArrayList;

public class Status {
	
	private String uri;
	private String hasID;
	private ArrayList<String> statusIsMappedWith;//mapping with activity
	private ArrayList<String> statusIsMappedToMessage;
	public Status(String uri, String hasID,
			ArrayList<String> statusIsMappedWith,
			ArrayList<String> statusIsMappedToMessage) {
		super();
		this.uri = uri;
		this.hasID = hasID;
		this.statusIsMappedWith = statusIsMappedWith;
		this.statusIsMappedToMessage = statusIsMappedToMessage;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getHasID() {
		return hasID;
	}
	public void setHasID(String hasID) {
		this.hasID = hasID;
	}
	public ArrayList<String> getStatusIsMappedWith() {
		return statusIsMappedWith;
	}
	public void setStatusIsMappedWith(ArrayList<String> statusIsMappedWith) {
		this.statusIsMappedWith = statusIsMappedWith;
	}
	public ArrayList<String> getStatusIsMappedToMessage() {
		return statusIsMappedToMessage;
	}
	public void setStatusIsMappedToMessage(ArrayList<String> statusIsMappedToMessage) {
		this.statusIsMappedToMessage = statusIsMappedToMessage;
	}
	@Override
	public String toString() {
		return "Status [uri=" + uri + ", hasID=" + hasID
				+ ", statusIsMappedWith=" + statusIsMappedWith
				+ ", statusIsMappedToMessage=" + statusIsMappedToMessage + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hasID == null) ? 0 : hasID.hashCode());
		result = prime
				* result
				+ ((statusIsMappedToMessage == null) ? 0
						: statusIsMappedToMessage.hashCode());
		result = prime
				* result
				+ ((statusIsMappedWith == null) ? 0 : statusIsMappedWith
						.hashCode());
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
		Status other = (Status) obj;
		if (hasID == null) {
			if (other.hasID != null)
				return false;
		} else if (!hasID.equals(other.hasID))
			return false;
		if (statusIsMappedToMessage == null) {
			if (other.statusIsMappedToMessage != null)
				return false;
		} else if (!statusIsMappedToMessage
				.equals(other.statusIsMappedToMessage))
			return false;
		if (statusIsMappedWith == null) {
			if (other.statusIsMappedWith != null)
				return false;
		} else if (!statusIsMappedWith.equals(other.statusIsMappedWith))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}
	
	

}
