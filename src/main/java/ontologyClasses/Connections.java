package ontologyClasses;

import java.util.ArrayList;

public class Connections {

	private ArrayList<String> approvedConnected;
	private ArrayList<String> askedConnected;
	private ArrayList<String> pendingConnected;

	public Connections(ArrayList<String> approvedConnected,
			ArrayList<String> askedConnected, ArrayList<String> pendingConnected) {
		super();
		this.approvedConnected = new ArrayList<String>();
		if (approvedConnected != null)
			this.approvedConnected = approvedConnected;

		this.askedConnected = new ArrayList<String>();
		if (askedConnected != null)
			this.askedConnected = askedConnected;

		this.pendingConnected = new ArrayList<String>();
		if (pendingConnected != null)
			this.pendingConnected = pendingConnected;
	}

	public ArrayList<String> getApprovedConnected() {
		return approvedConnected;
	}

	public void setApprovedConnected(ArrayList<String> approvedConnected) {
		this.approvedConnected = approvedConnected;
	}

	public ArrayList<String> getAskedConnected() {
		return askedConnected;
	}

	public void setAskedConnected(ArrayList<String> askedConnected) {
		this.askedConnected = askedConnected;
	}

	public ArrayList<String> getPendingConnected() {
		return pendingConnected;
	}

	public void setPendingConnected(ArrayList<String> pendingConnected) {
		this.pendingConnected = pendingConnected;
	}

	@Override
	public String toString() {
		return "Connections [approvedConnected=" + approvedConnected
				+ ", askedConnected=" + askedConnected + ", pendingConnected="
				+ pendingConnected + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((approvedConnected == null) ? 0 : approvedConnected
						.hashCode());
		result = prime * result
				+ ((askedConnected == null) ? 0 : askedConnected.hashCode());
		result = prime
				* result
				+ ((pendingConnected == null) ? 0 : pendingConnected.hashCode());
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
		Connections other = (Connections) obj;
		if (approvedConnected == null) {
			if (other.approvedConnected != null)
				return false;
		} else if (!approvedConnected.equals(other.approvedConnected))
			return false;
		if (askedConnected == null) {
			if (other.askedConnected != null)
				return false;
		} else if (!askedConnected.equals(other.askedConnected))
			return false;
		if (pendingConnected == null) {
			if (other.pendingConnected != null)
				return false;
		} else if (!pendingConnected.equals(other.pendingConnected))
			return false;
		return true;
	}

}
