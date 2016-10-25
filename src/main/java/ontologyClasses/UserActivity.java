package ontologyClasses;

import java.util.ArrayList;

public class UserActivity {

	private String uri;
	private ArrayList<String> activityIsMappedWith;// mapping with service model

	public UserActivity(String uri, ArrayList<String> activityIsMappedWith) {
		super();
		this.uri = uri;
		this.activityIsMappedWith = activityIsMappedWith;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public ArrayList<String> getActivityIsMappedWith() {
		return activityIsMappedWith;
	}

	public void setActivityIsMappedWith(ArrayList<String> activityIsMappedWith) {
		this.activityIsMappedWith = activityIsMappedWith;
	}

	@Override
	public String toString() {
		return "UserActivity [uri=" + uri + ", activityIsMappedWith="
				+ activityIsMappedWith + "]";
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
		UserActivity other = (UserActivity) obj;
		if (activityIsMappedWith == null) {
			if (other.activityIsMappedWith != null)
				return false;
		} else if (!activityIsMappedWith.equals(other.activityIsMappedWith))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}

}
