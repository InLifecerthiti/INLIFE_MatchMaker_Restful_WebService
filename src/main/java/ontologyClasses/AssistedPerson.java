package ontologyClasses;

import java.util.ArrayList;
import java.util.Date;

public class AssistedPerson {

	private Profile profile;
	private String hasCapacityToFunction;
	private String hasSocioEconomicStatus;

	private String hasGender;
	private String hasEducation;
	private String hasIMEI;
	private boolean hasSocialSupportNetworks;
	private String hasAnnualIncome;

	private String hasTechnologyUsage;
	private String hasMaritalStatus;
	private String hasStatus;
	private String user_activity_status;
	private ArrayList<String> User_has_Disability = new ArrayList<String>();
	private ArrayList<String> User_linksTo_Impairment = new ArrayList<String>();
	private ArrayList<String> User_linksTo_FunctionalLimitation = new ArrayList<String>();
	private ArrayList<String> User_linksTo_Disease = new ArrayList<String>();
	private Connections connections;

	public AssistedPerson(Profile profile, String hasCapacityToFunction,
			String hasSocioEconomicStatus, String hasGender,
			String hasEducation, String hasIMEI,
			boolean hasSocialSupportNetworks, String hasAnnualIncome,
			String hasTechnologyUsage, String hasMaritalStatus,
			String hasStatus, ArrayList<String> user_has_Disability,
			ArrayList<String> user_linksTo_Impairment,
			ArrayList<String> user_linksTo_FunctionalLimitation,
			ArrayList<String> user_linksTo_Disease, Connections connections,
			String user_activity_status) {
		super();
		this.profile = profile;
		this.hasCapacityToFunction = hasCapacityToFunction;
		this.hasSocioEconomicStatus = hasSocioEconomicStatus;
		this.hasGender = hasGender;
		this.hasEducation = hasEducation;
		this.hasIMEI = hasIMEI;
		this.hasSocialSupportNetworks = hasSocialSupportNetworks;
		this.hasAnnualIncome = hasAnnualIncome;
		this.hasTechnologyUsage = hasTechnologyUsage;
		this.hasMaritalStatus = hasMaritalStatus;
		this.hasStatus = hasStatus;

		this.User_has_Disability = new ArrayList<String>();
		if (user_has_Disability != null)
			this.User_has_Disability = user_has_Disability;

		this.User_linksTo_Impairment = new ArrayList<String>();
		if (user_linksTo_Impairment != null)
			this.User_linksTo_Impairment = user_linksTo_Impairment;

		this.User_linksTo_FunctionalLimitation = new ArrayList<String>();
		if (user_linksTo_FunctionalLimitation != null)
			this.User_linksTo_FunctionalLimitation = user_linksTo_FunctionalLimitation;

		this.User_linksTo_Disease = new ArrayList<String>();
		if (user_linksTo_Disease != null)
			this.User_linksTo_Disease = user_linksTo_Disease;

		this.connections = connections;
		this.user_activity_status = user_activity_status;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getHasCapacityToFunction() {
		return hasCapacityToFunction;
	}

	public void setHasCapacityToFunction(String hasCapacityToFunction) {
		this.hasCapacityToFunction = hasCapacityToFunction;
	}

	public String getHasSocioEconomicStatus() {
		return hasSocioEconomicStatus;
	}

	public void setHasSocioEconomicStatus(String hasSocioEconomicStatus) {
		this.hasSocioEconomicStatus = hasSocioEconomicStatus;
	}

	public String getHasGender() {
		return hasGender;
	}

	public void setHasGender(String hasGender) {
		this.hasGender = hasGender;
	}

	public String getHasEducation() {
		return hasEducation;
	}

	public void setHasEducation(String hasEducation) {
		this.hasEducation = hasEducation;
	}

	public String getHasIMEI() {
		return hasIMEI;
	}

	public void setHasIMEI(String hasIMEI) {
		this.hasIMEI = hasIMEI;
	}

	public boolean isHasSocialSupportNetworks() {
		return hasSocialSupportNetworks;
	}

	public void setHasSocialSupportNetworks(boolean hasSocialSupportNetworks) {
		this.hasSocialSupportNetworks = hasSocialSupportNetworks;
	}

	public String getHasAnnualIncome() {
		return hasAnnualIncome;
	}

	public void setHasAnnualIncome(String hasAnnualIncome) {
		this.hasAnnualIncome = hasAnnualIncome;
	}

	public String getHasTechnologyUsage() {
		return hasTechnologyUsage;
	}

	public void setHasTechnologyUsage(String hasTechnologyUsage) {
		this.hasTechnologyUsage = hasTechnologyUsage;
	}

	public String getHasMaritalStatus() {
		return hasMaritalStatus;
	}

	public void setHasMaritalStatus(String hasMaritalStatus) {
		this.hasMaritalStatus = hasMaritalStatus;
	}

	public String getHasStatus() {
		return hasStatus;
	}

	public void setHasStatus(String hasStatus) {
		this.hasStatus = hasStatus;
	}

	public ArrayList<String> getUser_has_Disability() {
		return User_has_Disability;
	}

	public void setUser_has_Disability(ArrayList<String> user_has_Disability) {
		User_has_Disability = user_has_Disability;
	}

	public ArrayList<String> getUser_linksTo_Impairment() {
		return User_linksTo_Impairment;
	}

	public void setUser_linksTo_Impairment(
			ArrayList<String> user_linksTo_Impairment) {
		User_linksTo_Impairment = user_linksTo_Impairment;
	}

	public ArrayList<String> getUser_linksTo_FunctionalLimitation() {
		return User_linksTo_FunctionalLimitation;
	}

	public void setUser_linksTo_FunctionalLimitations(
			ArrayList<String> user_linksTo_FunctionalLimitation) {
		User_linksTo_FunctionalLimitation = user_linksTo_FunctionalLimitation;
	}

	public ArrayList<String> getUser_linksTo_Disease() {
		return User_linksTo_Disease;
	}

	public void setUser_linksTo_Disease(ArrayList<String> user_linksTo_Disease) {
		User_linksTo_Disease = user_linksTo_Disease;
	}

	public Connections getConnections() {
		return connections;
	}

	public void setConnections(Connections connections) {
		this.connections = connections;
	}

	public String getUser_activity_status() {
		return user_activity_status;
	}

	public void setUser_activity_status(String user_activity_status) {
		this.user_activity_status = user_activity_status;
	}

	public void setUser_linksTo_FunctionalLimitation(
			ArrayList<String> user_linksTo_FunctionalLimitation) {
		User_linksTo_FunctionalLimitation = user_linksTo_FunctionalLimitation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((User_has_Disability == null) ? 0 : User_has_Disability
						.hashCode());
		result = prime
				* result
				+ ((User_linksTo_Disease == null) ? 0 : User_linksTo_Disease
						.hashCode());
		result = prime
				* result
				+ ((User_linksTo_FunctionalLimitation == null) ? 0
						: User_linksTo_FunctionalLimitation.hashCode());
		result = prime
				* result
				+ ((User_linksTo_Impairment == null) ? 0
						: User_linksTo_Impairment.hashCode());
		result = prime * result
				+ ((connections == null) ? 0 : connections.hashCode());
		result = prime * result
				+ ((hasAnnualIncome == null) ? 0 : hasAnnualIncome.hashCode());
		result = prime
				* result
				+ ((hasCapacityToFunction == null) ? 0 : hasCapacityToFunction
						.hashCode());
		result = prime * result
				+ ((hasEducation == null) ? 0 : hasEducation.hashCode());
		result = prime * result
				+ ((hasGender == null) ? 0 : hasGender.hashCode());
		result = prime * result + ((hasIMEI == null) ? 0 : hasIMEI.hashCode());
		result = prime
				* result
				+ ((hasMaritalStatus == null) ? 0 : hasMaritalStatus.hashCode());
		result = prime * result + (hasSocialSupportNetworks ? 1231 : 1237);
		result = prime
				* result
				+ ((hasSocioEconomicStatus == null) ? 0
						: hasSocioEconomicStatus.hashCode());
		result = prime * result
				+ ((hasStatus == null) ? 0 : hasStatus.hashCode());
		result = prime
				* result
				+ ((hasTechnologyUsage == null) ? 0 : hasTechnologyUsage
						.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		result = prime
				* result
				+ ((user_activity_status == null) ? 0 : user_activity_status
						.hashCode());
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
		AssistedPerson other = (AssistedPerson) obj;
		if (User_has_Disability == null) {
			if (other.User_has_Disability != null)
				return false;
		} else if (!User_has_Disability.equals(other.User_has_Disability))
			return false;
		if (User_linksTo_Disease == null) {
			if (other.User_linksTo_Disease != null)
				return false;
		} else if (!User_linksTo_Disease.equals(other.User_linksTo_Disease))
			return false;
		if (User_linksTo_FunctionalLimitation == null) {
			if (other.User_linksTo_FunctionalLimitation != null)
				return false;
		} else if (!User_linksTo_FunctionalLimitation
				.equals(other.User_linksTo_FunctionalLimitation))
			return false;
		if (User_linksTo_Impairment == null) {
			if (other.User_linksTo_Impairment != null)
				return false;
		} else if (!User_linksTo_Impairment
				.equals(other.User_linksTo_Impairment))
			return false;
		if (connections == null) {
			if (other.connections != null)
				return false;
		} else if (!connections.equals(other.connections))
			return false;
		if (hasAnnualIncome == null) {
			if (other.hasAnnualIncome != null)
				return false;
		} else if (!hasAnnualIncome.equals(other.hasAnnualIncome))
			return false;
		if (hasCapacityToFunction == null) {
			if (other.hasCapacityToFunction != null)
				return false;
		} else if (!hasCapacityToFunction.equals(other.hasCapacityToFunction))
			return false;
		if (hasEducation == null) {
			if (other.hasEducation != null)
				return false;
		} else if (!hasEducation.equals(other.hasEducation))
			return false;
		if (hasGender == null) {
			if (other.hasGender != null)
				return false;
		} else if (!hasGender.equals(other.hasGender))
			return false;
		if (hasIMEI == null) {
			if (other.hasIMEI != null)
				return false;
		} else if (!hasIMEI.equals(other.hasIMEI))
			return false;
		if (hasMaritalStatus == null) {
			if (other.hasMaritalStatus != null)
				return false;
		} else if (!hasMaritalStatus.equals(other.hasMaritalStatus))
			return false;
		if (hasSocialSupportNetworks != other.hasSocialSupportNetworks)
			return false;
		if (hasSocioEconomicStatus == null) {
			if (other.hasSocioEconomicStatus != null)
				return false;
		} else if (!hasSocioEconomicStatus.equals(other.hasSocioEconomicStatus))
			return false;
		if (hasStatus == null) {
			if (other.hasStatus != null)
				return false;
		} else if (!hasStatus.equals(other.hasStatus))
			return false;
		if (hasTechnologyUsage == null) {
			if (other.hasTechnologyUsage != null)
				return false;
		} else if (!hasTechnologyUsage.equals(other.hasTechnologyUsage))
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		if (user_activity_status == null) {
			if (other.user_activity_status != null)
				return false;
		} else if (!user_activity_status.equals(other.user_activity_status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AssistedPerson [profile=" + profile
				+ ", hasCapacityToFunction=" + hasCapacityToFunction
				+ ", hasSocioEconomicStatus=" + hasSocioEconomicStatus
				+ ", hasGender=" + hasGender + ", hasEducation=" + hasEducation
				+ ", hasIMEI=" + hasIMEI + ", hasSocialSupportNetworks="
				+ hasSocialSupportNetworks + ", hasAnnualIncome="
				+ hasAnnualIncome + ", hasTechnologyUsage="
				+ hasTechnologyUsage + ", hasMaritalStatus=" + hasMaritalStatus
				+ ", hasStatus=" + hasStatus + ", user_activity_status="
				+ user_activity_status + ", User_has_Disability="
				+ User_has_Disability + ", User_linksTo_Impairment="
				+ User_linksTo_Impairment
				+ ", User_linksTo_FunctionalLimitation="
				+ User_linksTo_FunctionalLimitation + ", User_linksTo_Disease="
				+ User_linksTo_Disease + ", connections=" + connections + "]";
	}

}
