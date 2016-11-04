package ontologyClasses;

import java.util.Date;

public class Profile {

	private String uri;
	private String hasFirstName;
	private String hasLastName;
	private String hasUsername;
	private String hasPassword;
	private String hasEmail;
	private String hasLocation;
	private Date hasInsertDate;
	private Date hasUpdateDate;
	private String hasPhoneNumber;
	private Date hasBirthDate;
	private String hasLanguage;

	public Profile(String uri, String hasFirstName, String hasLastName,
			String hasUsername, String hasPassword, String hasEmail,
			String hasLocation, Date hasInsertDate, Date hasUpdateDate,
			String hasPhoneNumber, Date hasBirthDate,String hasLanguage) {
		super();
		this.uri = uri;
		this.hasFirstName = hasFirstName;
		this.hasLastName = hasLastName;
		this.hasUsername = hasUsername;
		this.hasPassword = hasPassword;
		this.hasEmail = hasEmail;
		this.hasLocation = hasLocation;
		this.hasInsertDate = hasInsertDate;
		this.hasUpdateDate = hasUpdateDate;
		this.hasPhoneNumber = hasPhoneNumber;
		this.hasBirthDate = hasBirthDate;
		this.hasLanguage = hasLanguage;
	}
	

	public String getHasLanguage() {
		return hasLanguage;
	}

	public void setHasLanguage(String hasLanguage) {
		this.hasLanguage = hasLanguage;
	}

	public String getHasPhoneNumber() {
		return hasPhoneNumber;
	}

	public void setHasPhoneNumber(String hasPhoneNumber) {
		this.hasPhoneNumber = hasPhoneNumber;
	}

	public Date getHasBirthDate() {
		return hasBirthDate;
	}

	public void setHasBirthDate(Date hasBirthDate) {
		this.hasBirthDate = hasBirthDate;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getHasFirstName() {
		return hasFirstName;
	}

	public void setHasFirstName(String hasFirstName) {
		this.hasFirstName = hasFirstName;
	}

	public String getHasLastName() {
		return hasLastName;
	}

	public void setHasLastName(String hasLastName) {
		this.hasLastName = hasLastName;
	}

	public String getHasUsername() {
		return hasUsername;
	}

	public void setHasUsername(String hasUsername) {
		this.hasUsername = hasUsername;
	}

	public String getHasPassword() {
		return hasPassword;
	}

	public void setHasPassword(String hasPassword) {
		this.hasPassword = hasPassword;
	}

	public String getHasEmail() {
		return hasEmail;
	}

	public void setHasEmail(String hasEmail) {
		this.hasEmail = hasEmail;
	}

	public String getHasLocation() {
		return hasLocation;
	}

	public void setHasLocation(String hasLocation) {
		this.hasLocation = hasLocation;
	}

	public Date getHasInsertDate() {
		return hasInsertDate;
	}

	public void setHasInsertDate(Date hasInsertDate) {
		this.hasInsertDate = hasInsertDate;
	}

	public Date getHasUpdateDate() {
		return hasUpdateDate;
	}

	public void setHasUpdateDate(Date hasUpdateDate) {
		this.hasUpdateDate = hasUpdateDate;
	}


	@Override
	public String toString() {
		return "Profile [uri=" + uri + ", hasFirstName=" + hasFirstName
				+ ", hasLastName=" + hasLastName + ", hasUsername="
				+ hasUsername + ", hasPassword=" + hasPassword + ", hasEmail="
				+ hasEmail + ", hasLocation=" + hasLocation
				+ ", hasInsertDate=" + hasInsertDate + ", hasUpdateDate="
				+ hasUpdateDate + ", hasPhoneNumber=" + hasPhoneNumber
				+ ", hasBirthDate=" + hasBirthDate + ", hasLanguage="
				+ hasLanguage + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((hasBirthDate == null) ? 0 : hasBirthDate.hashCode());
		result = prime * result
				+ ((hasEmail == null) ? 0 : hasEmail.hashCode());
		result = prime * result
				+ ((hasFirstName == null) ? 0 : hasFirstName.hashCode());
		result = prime * result
				+ ((hasInsertDate == null) ? 0 : hasInsertDate.hashCode());
		result = prime * result
				+ ((hasLanguage == null) ? 0 : hasLanguage.hashCode());
		result = prime * result
				+ ((hasLastName == null) ? 0 : hasLastName.hashCode());
		result = prime * result
				+ ((hasLocation == null) ? 0 : hasLocation.hashCode());
		result = prime * result
				+ ((hasPassword == null) ? 0 : hasPassword.hashCode());
		result = prime * result
				+ ((hasPhoneNumber == null) ? 0 : hasPhoneNumber.hashCode());
		result = prime * result
				+ ((hasUpdateDate == null) ? 0 : hasUpdateDate.hashCode());
		result = prime * result
				+ ((hasUsername == null) ? 0 : hasUsername.hashCode());
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
		Profile other = (Profile) obj;
		if (hasBirthDate == null) {
			if (other.hasBirthDate != null)
				return false;
		} else if (!hasBirthDate.equals(other.hasBirthDate))
			return false;
		if (hasEmail == null) {
			if (other.hasEmail != null)
				return false;
		} else if (!hasEmail.equals(other.hasEmail))
			return false;
		if (hasFirstName == null) {
			if (other.hasFirstName != null)
				return false;
		} else if (!hasFirstName.equals(other.hasFirstName))
			return false;
		if (hasInsertDate == null) {
			if (other.hasInsertDate != null)
				return false;
		} else if (!hasInsertDate.equals(other.hasInsertDate))
			return false;
		if (hasLanguage == null) {
			if (other.hasLanguage != null)
				return false;
		} else if (!hasLanguage.equals(other.hasLanguage))
			return false;
		if (hasLastName == null) {
			if (other.hasLastName != null)
				return false;
		} else if (!hasLastName.equals(other.hasLastName))
			return false;
		if (hasLocation == null) {
			if (other.hasLocation != null)
				return false;
		} else if (!hasLocation.equals(other.hasLocation))
			return false;
		if (hasPassword == null) {
			if (other.hasPassword != null)
				return false;
		} else if (!hasPassword.equals(other.hasPassword))
			return false;
		if (hasPhoneNumber == null) {
			if (other.hasPhoneNumber != null)
				return false;
		} else if (!hasPhoneNumber.equals(other.hasPhoneNumber))
			return false;
		if (hasUpdateDate == null) {
			if (other.hasUpdateDate != null)
				return false;
		} else if (!hasUpdateDate.equals(other.hasUpdateDate))
			return false;
		if (hasUsername == null) {
			if (other.hasUsername != null)
				return false;
		} else if (!hasUsername.equals(other.hasUsername))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}

	
}
