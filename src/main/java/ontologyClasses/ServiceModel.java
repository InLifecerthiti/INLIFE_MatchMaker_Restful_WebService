package ontologyClasses;

import java.util.ArrayList;

public class ServiceModel {

	private String uri;
	private String serviceHasName;
	private String serviceHasSescription;
	private int hasPriority;
	private boolean canBeUsedIndependently;
	private boolean serviceShowStats;
	private ArrayList<String> hasConnectionDetails;
	private ArrayList<String> hasInput;
	private ArrayList<String> hasOutput;
	private ArrayList<String> hasResult;
	private ArrayList<String> hasPrecondition;

	public ServiceModel(String uri, String serviceHasName,
			String serviceHasSescription, int hasPriority,
			boolean canBeUsedIndependently, boolean serviceShowStats,
			ArrayList<String> hasConnectionDetails, ArrayList<String> hasInput,
			ArrayList<String> hasOutput, ArrayList<String> hasResult,
			ArrayList<String> hasPrecondition) {
		super();
		this.uri = uri;
		this.serviceHasName = serviceHasName;
		this.serviceHasSescription = serviceHasSescription;
		this.hasPriority = hasPriority;
		this.canBeUsedIndependently = canBeUsedIndependently;
		this.serviceShowStats = serviceShowStats;
		this.hasConnectionDetails = hasConnectionDetails;
		this.hasInput = hasInput;
		this.hasOutput = hasOutput;
		this.hasResult = hasResult;
		this.hasPrecondition = hasPrecondition;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getServiceHasName() {
		return serviceHasName;
	}

	public void setServiceHasName(String serviceHasName) {
		this.serviceHasName = serviceHasName;
	}

	public String getServiceHasSescription() {
		return serviceHasSescription;
	}

	public void setServiceHasSescription(String serviceHasSescription) {
		this.serviceHasSescription = serviceHasSescription;
	}

	public int getHasPriority() {
		return hasPriority;
	}

	public void setHasPriority(int hasPriority) {
		this.hasPriority = hasPriority;
	}

	public boolean isCanBeUsedIndependently() {
		return canBeUsedIndependently;
	}

	public void setCanBeUsedIndependently(boolean canBeUsedIndependently) {
		this.canBeUsedIndependently = canBeUsedIndependently;
	}

	public boolean isServiceShowStats() {
		return serviceShowStats;
	}

	public void setServiceShowStats(boolean serviceShowStats) {
		this.serviceShowStats = serviceShowStats;
	}

	public ArrayList<String> getHasConnectionDetails() {
		return hasConnectionDetails;
	}

	public void setHasConnectionDetails(ArrayList<String> hasConnectionDetails) {
		this.hasConnectionDetails = hasConnectionDetails;
	}

	public ArrayList<String> getHasInput() {
		return hasInput;
	}

	public void setHasInput(ArrayList<String> hasInput) {
		this.hasInput = hasInput;
	}

	public ArrayList<String> getHasOutput() {
		return hasOutput;
	}

	public void setHasOutput(ArrayList<String> hasOutput) {
		this.hasOutput = hasOutput;
	}

	public ArrayList<String> getHasResult() {
		return hasResult;
	}

	public void setHasResult(ArrayList<String> hasResult) {
		this.hasResult = hasResult;
	}

	public ArrayList<String> getHasPrecondition() {
		return hasPrecondition;
	}

	public void setHasPrecondition(ArrayList<String> hasPrecondition) {
		this.hasPrecondition = hasPrecondition;
	}

	@Override
	public String toString() {
		return "ServiceModel [uri=" + uri + ", serviceHasName="
				+ serviceHasName + ", serviceHasSescription="
				+ serviceHasSescription + ", hasPriority=" + hasPriority
				+ ", canBeUsedIndependently=" + canBeUsedIndependently
				+ ", serviceShowStats=" + serviceShowStats
				+ ", hasConnectionDetails=" + hasConnectionDetails
				+ ", hasInput=" + hasInput + ", hasOutput=" + hasOutput
				+ ", hasResult=" + hasResult + ", hasPrecondition="
				+ hasPrecondition + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (canBeUsedIndependently ? 1231 : 1237);
		result = prime
				* result
				+ ((hasConnectionDetails == null) ? 0 : hasConnectionDetails
						.hashCode());
		result = prime * result
				+ ((hasInput == null) ? 0 : hasInput.hashCode());
		result = prime * result
				+ ((hasOutput == null) ? 0 : hasOutput.hashCode());
		result = prime * result
				+ ((hasPrecondition == null) ? 0 : hasPrecondition.hashCode());
		result = prime * result + hasPriority;
		result = prime * result
				+ ((hasResult == null) ? 0 : hasResult.hashCode());
		result = prime * result
				+ ((serviceHasName == null) ? 0 : serviceHasName.hashCode());
		result = prime
				* result
				+ ((serviceHasSescription == null) ? 0 : serviceHasSescription
						.hashCode());
		result = prime * result + (serviceShowStats ? 1231 : 1237);
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
		ServiceModel other = (ServiceModel) obj;
		if (canBeUsedIndependently != other.canBeUsedIndependently)
			return false;
		if (hasConnectionDetails == null) {
			if (other.hasConnectionDetails != null)
				return false;
		} else if (!hasConnectionDetails.equals(other.hasConnectionDetails))
			return false;
		if (hasInput == null) {
			if (other.hasInput != null)
				return false;
		} else if (!hasInput.equals(other.hasInput))
			return false;
		if (hasOutput == null) {
			if (other.hasOutput != null)
				return false;
		} else if (!hasOutput.equals(other.hasOutput))
			return false;
		if (hasPrecondition == null) {
			if (other.hasPrecondition != null)
				return false;
		} else if (!hasPrecondition.equals(other.hasPrecondition))
			return false;
		if (hasPriority != other.hasPriority)
			return false;
		if (hasResult == null) {
			if (other.hasResult != null)
				return false;
		} else if (!hasResult.equals(other.hasResult))
			return false;
		if (serviceHasName == null) {
			if (other.serviceHasName != null)
				return false;
		} else if (!serviceHasName.equals(other.serviceHasName))
			return false;
		if (serviceHasSescription == null) {
			if (other.serviceHasSescription != null)
				return false;
		} else if (!serviceHasSescription.equals(other.serviceHasSescription))
			return false;
		if (serviceShowStats != other.serviceShowStats)
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}

}
