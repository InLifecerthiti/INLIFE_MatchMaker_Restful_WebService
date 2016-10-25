package ontologyClasses;

public class Parameter {
	private String uri;
	private String parameterHasName;
	private String parameterHasRange;
	private String parameterHasDefaultValue;
	private String parameterHasMaxValue;
	private String parameterHasMinValue;
	private String parameterHasType;
	private String isMappedToParameterTerm;
	private String parameterHasDescription;
	
	public Parameter(String uri, String parameterHasName,
			String parameterHasRange, String parameterHasDefaultValue,
			String parameterHasMaxValue, String parameterHasMinValue,
			String parameterHasType, String isMappedToParameterTerm, String parameterHasDescription) {
		super();
		this.uri = uri;
		this.parameterHasName = parameterHasName;
		this.parameterHasRange = parameterHasRange;
		this.parameterHasDefaultValue = parameterHasDefaultValue;
		this.parameterHasMaxValue = parameterHasMaxValue;
		this.parameterHasMinValue = parameterHasMinValue;
		this.parameterHasType = parameterHasType;
		this.isMappedToParameterTerm = isMappedToParameterTerm;
		this.parameterHasDescription = parameterHasDescription;
	}

	public String getParameterHasDescription() {
		return parameterHasDescription;
	}

	public void setParameterHasDescription(String parameterHasDescription) {
		this.parameterHasDescription = parameterHasDescription;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getParameterHasName() {
		return parameterHasName;
	}

	public void setParameterHasName(String parameterHasName) {
		this.parameterHasName = parameterHasName;
	}

	public String getParameterHasRange() {
		return parameterHasRange;
	}

	public void setParameterHasRange(String parameterHasRange) {
		this.parameterHasRange = parameterHasRange;
	}

	public String getParameterHasDefaultValue() {
		return parameterHasDefaultValue;
	}

	public void setParameterHasDefaultValue(String parameterHasDefaultValue) {
		this.parameterHasDefaultValue = parameterHasDefaultValue;
	}

	public String getParameterHasMaxValue() {
		return parameterHasMaxValue;
	}

	public void setParameterHasMaxValue(String parameterHasMaxValue) {
		this.parameterHasMaxValue = parameterHasMaxValue;
	}

	public String getParameterHasMinValue() {
		return parameterHasMinValue;
	}

	public void setParameterHasMinValue(String parameterHasMinValue) {
		this.parameterHasMinValue = parameterHasMinValue;
	}

	public String getParameterHasType() {
		return parameterHasType;
	}

	public void setParameterHasType(String parameterHasType) {
		this.parameterHasType = parameterHasType;
	}

	public String getIsMappedToParameterTerm() {
		return isMappedToParameterTerm;
	}

	public void setIsMappedToParameterTerm(String isMappedToParameterTerm) {
		this.isMappedToParameterTerm = isMappedToParameterTerm;
	}

	@Override
	public String toString() {
		return "Parameter [uri=" + uri + ", parameterHasName="
				+ parameterHasName + ", parameterHasRange=" + parameterHasRange
				+ ", parameterHasDefaultValue=" + parameterHasDefaultValue
				+ ", parameterHasMaxValue=" + parameterHasMaxValue
				+ ", parameterHasMinValue=" + parameterHasMinValue
				+ ", parameterHasType=" + parameterHasType
				+ ", isMappedToParameterTerm=" + isMappedToParameterTerm
				+ ", parameterHasDescription=" + parameterHasDescription + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((isMappedToParameterTerm == null) ? 0
						: isMappedToParameterTerm.hashCode());
		result = prime
				* result
				+ ((parameterHasDefaultValue == null) ? 0
						: parameterHasDefaultValue.hashCode());
		result = prime
				* result
				+ ((parameterHasDescription == null) ? 0
						: parameterHasDescription.hashCode());
		result = prime
				* result
				+ ((parameterHasMaxValue == null) ? 0 : parameterHasMaxValue
						.hashCode());
		result = prime
				* result
				+ ((parameterHasMinValue == null) ? 0 : parameterHasMinValue
						.hashCode());
		result = prime
				* result
				+ ((parameterHasName == null) ? 0 : parameterHasName.hashCode());
		result = prime
				* result
				+ ((parameterHasRange == null) ? 0 : parameterHasRange
						.hashCode());
		result = prime
				* result
				+ ((parameterHasType == null) ? 0 : parameterHasType.hashCode());
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
		Parameter other = (Parameter) obj;
		if (isMappedToParameterTerm == null) {
			if (other.isMappedToParameterTerm != null)
				return false;
		} else if (!isMappedToParameterTerm
				.equals(other.isMappedToParameterTerm))
			return false;
		if (parameterHasDefaultValue == null) {
			if (other.parameterHasDefaultValue != null)
				return false;
		} else if (!parameterHasDefaultValue
				.equals(other.parameterHasDefaultValue))
			return false;
		if (parameterHasDescription == null) {
			if (other.parameterHasDescription != null)
				return false;
		} else if (!parameterHasDescription
				.equals(other.parameterHasDescription))
			return false;
		if (parameterHasMaxValue == null) {
			if (other.parameterHasMaxValue != null)
				return false;
		} else if (!parameterHasMaxValue.equals(other.parameterHasMaxValue))
			return false;
		if (parameterHasMinValue == null) {
			if (other.parameterHasMinValue != null)
				return false;
		} else if (!parameterHasMinValue.equals(other.parameterHasMinValue))
			return false;
		if (parameterHasName == null) {
			if (other.parameterHasName != null)
				return false;
		} else if (!parameterHasName.equals(other.parameterHasName))
			return false;
		if (parameterHasRange == null) {
			if (other.parameterHasRange != null)
				return false;
		} else if (!parameterHasRange.equals(other.parameterHasRange))
			return false;
		if (parameterHasType == null) {
			if (other.parameterHasType != null)
				return false;
		} else if (!parameterHasType.equals(other.parameterHasType))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}

	
	
	
}
