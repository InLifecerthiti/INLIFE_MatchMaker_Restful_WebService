package ontologyClasses;

public class UserAnswer {
	
	private String uri;
	private String hasAnswer;
	private String hasQuestion;
	
	public UserAnswer(String uri, String hasAnswer, String hasQuestion) {
		super();
		this.uri = uri;
		this.hasAnswer = hasAnswer;
		this.hasQuestion = hasQuestion;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getHasAnswer() {
		return hasAnswer;
	}
	public void setHasAnswer(String hasAnswer) {
		this.hasAnswer = hasAnswer;
	}
	public String getHasQuestion() {
		return hasQuestion;
	}
	public void setHasQuestion(String hasQuestion) {
		this.hasQuestion = hasQuestion;
	}
	@Override
	public String toString() {
		return "UserAnswer [uri=" + uri + ", hasAnswer=" + hasAnswer
				+ ", hasQuestion=" + hasQuestion + "]";
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
		UserAnswer other = (UserAnswer) obj;
		if (hasAnswer == null) {
			if (other.hasAnswer != null)
				return false;
		} else if (!hasAnswer.equals(other.hasAnswer))
			return false;
		if (hasQuestion == null) {
			if (other.hasQuestion != null)
				return false;
		} else if (!hasQuestion.equals(other.hasQuestion))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}
	

}
