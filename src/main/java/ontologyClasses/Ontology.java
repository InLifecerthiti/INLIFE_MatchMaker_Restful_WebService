package ontologyClasses;

import java.util.ArrayList;
import java.util.HashMap;

import InLifeMatchMaker.JsonManager;

public class Ontology {

	// objects parsed from JSON
	private ArrayList<AssistedPerson> assistedPeople = new ArrayList<AssistedPerson>();
	private ArrayList<Provider> providers = new ArrayList<Provider>();
	private ArrayList<Caregiver> caregivers = new ArrayList<Caregiver>();
	private ArrayList<Disability> disabilities = new ArrayList<Disability>();
	private ArrayList<Impairment> impairments = new ArrayList<Impairment>();
	private ArrayList<FunctionalLimitation> functionalLimitations = new ArrayList<FunctionalLimitation>();
	private ArrayList<Disease> diseases = new ArrayList<Disease>();
	private ArrayList<Education> education = new ArrayList<Education>();
	private ArrayList<MaritalStatus> maritalStatuses = new ArrayList<MaritalStatus>();
	private ArrayList<ToolCategory> allTools = new ArrayList<ToolCategory>();
	private ArrayList<ConnectionDetails> connectionDetails = new ArrayList<ConnectionDetails>();
	private ArrayList<ServiceModel> serviceModels = new ArrayList<ServiceModel>();
	private ArrayList<Parameter> parameters = new ArrayList<Parameter>();
	private ArrayList<ParameterTerm> parameterTerms = new ArrayList<ParameterTerm>();
	private ArrayList<Status> status = new ArrayList<Status>();
	private ArrayList<UserActivity> userActivities = new ArrayList<UserActivity>();
	private ArrayList<Message> messages = new ArrayList<Message>();
	private ArrayList<Usage> usage = new ArrayList<Usage>();
	private ArrayList<Answer> answers = new ArrayList<Answer>();
	private ArrayList<Login> login = new ArrayList<Login>();
	private ArrayList<Question> questions = new ArrayList<Question>();
	private ArrayList<Questionnaire> questionnaire = new ArrayList<Questionnaire>();
	private ArrayList<UserAnswer> userAnswers = new ArrayList<UserAnswer>();

	public Ontology(ArrayList<AssistedPerson> assistedPeople,
			ArrayList<Provider> providers, ArrayList<Caregiver> caregivers,
			ArrayList<Disability> disabilities,
			ArrayList<Impairment> impairments,
			ArrayList<FunctionalLimitation> functionalLimitations,
			ArrayList<Disease> diseases, ArrayList<Education> education,
			ArrayList<MaritalStatus> maritalStatuses,
			ArrayList<ToolCategory> allTools,
			ArrayList<ConnectionDetails> connectionDetails,
			ArrayList<ServiceModel> serviceModels,
			ArrayList<Parameter> parameters,
			ArrayList<ParameterTerm> parameterTerms, ArrayList<Status> status,
			ArrayList<UserActivity> userActivities,
			ArrayList<Message> messages, ArrayList<Usage> usage,
			ArrayList<Answer> answers, ArrayList<Login> login,
			ArrayList<Question> questions,
			ArrayList<Questionnaire> questionnaire,
			ArrayList<UserAnswer> userAnswers) {
		super();
		this.assistedPeople = assistedPeople;
		this.providers = providers;
		this.caregivers = caregivers;
		this.disabilities = disabilities;
		this.impairments = impairments;
		this.functionalLimitations = functionalLimitations;
		this.diseases = diseases;
		this.education = education;
		this.maritalStatuses = maritalStatuses;
		this.allTools = allTools;
		this.connectionDetails = connectionDetails;
		this.serviceModels = serviceModels;
		this.parameters = parameters;
		this.parameterTerms = parameterTerms;
		this.status = status;
		this.userActivities = userActivities;
		this.messages = messages;
		this.usage = usage;
		this.answers = answers;
		this.login = login;
		this.questions = questions;
		this.questionnaire = questionnaire;
		this.userAnswers = userAnswers;

		

	}

	public ArrayList<AssistedPerson> getAssistedPeople() {
		return assistedPeople;
	}

	public void setAssistedPeople(ArrayList<AssistedPerson> assistedPeople) {
		this.assistedPeople = assistedPeople;
	}

	public ArrayList<Provider> getProviders() {
		return providers;
	}

	public void setProviders(ArrayList<Provider> providers) {
		this.providers = providers;
	}

	public ArrayList<Caregiver> getCaregivers() {
		return caregivers;
	}

	public void setCaregivers(ArrayList<Caregiver> caregivers) {
		this.caregivers = caregivers;
	}

	public ArrayList<Disability> getDisabilities() {
		return disabilities;
	}

	public void setDisabilities(ArrayList<Disability> disabilities) {
		this.disabilities = disabilities;
	}

	public ArrayList<Impairment> getImpairments() {
		return impairments;
	}

	public void setImpairments(ArrayList<Impairment> impairments) {
		this.impairments = impairments;
	}

	public ArrayList<FunctionalLimitation> getFunctionalLimitations() {
		return functionalLimitations;
	}

	public void setFunctionalLimitations(
			ArrayList<FunctionalLimitation> functionalLimitations) {
		this.functionalLimitations = functionalLimitations;
	}

	public ArrayList<Disease> getDiseases() {
		return diseases;
	}

	public void setDiseases(ArrayList<Disease> diseases) {
		this.diseases = diseases;
	}

	public ArrayList<Education> getEducation() {
		return education;
	}

	public void setEducation(ArrayList<Education> education) {
		this.education = education;
	}

	public ArrayList<MaritalStatus> getMaritalStatuses() {
		return maritalStatuses;
	}

	public void setMaritalStatuses(ArrayList<MaritalStatus> maritalStatuses) {
		this.maritalStatuses = maritalStatuses;
	}

	public ArrayList<ToolCategory> getAllTools() {
		return allTools;
	}

	public void setAllTools(ArrayList<ToolCategory> allTools) {
		this.allTools = allTools;
	}

	public ArrayList<ConnectionDetails> getConnectionDetails() {
		return connectionDetails;
	}

	public void setConnectionDetails(
			ArrayList<ConnectionDetails> connectionDetails) {
		this.connectionDetails = connectionDetails;
	}

	public ArrayList<ServiceModel> getServiceModels() {
		return serviceModels;
	}

	public void setServiceModels(ArrayList<ServiceModel> serviceModels) {
		this.serviceModels = serviceModels;
	}

	public ArrayList<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(ArrayList<Parameter> parameters) {
		this.parameters = parameters;
	}

	public ArrayList<ParameterTerm> getParameterTerms() {
		return parameterTerms;
	}

	public void setParameterTerms(ArrayList<ParameterTerm> parameterTerms) {
		this.parameterTerms = parameterTerms;
	}

	public ArrayList<Status> getStatus() {
		return status;
	}

	public void setStatus(ArrayList<Status> status) {
		this.status = status;
	}

	public ArrayList<UserActivity> getUserActivities() {
		return userActivities;
	}

	public void setUserActivities(ArrayList<UserActivity> userActivities) {
		this.userActivities = userActivities;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}

	public ArrayList<Usage> getUsage() {
		return usage;
	}

	public void setUsage(ArrayList<Usage> usage) {
		this.usage = usage;
	}

	public ArrayList<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}

	public ArrayList<Login> getLogin() {
		return login;
	}

	public void setLogin(ArrayList<Login> login) {
		this.login = login;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public ArrayList<Questionnaire> getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(ArrayList<Questionnaire> questionnaire) {
		this.questionnaire = questionnaire;
	}

	public ArrayList<UserAnswer> getUserAnswers() {
		return userAnswers;
	}

	public void setUserAnswers(ArrayList<UserAnswer> userAnswers) {
		this.userAnswers = userAnswers;
	}

	@Override
	public String toString() {
		return "Ontology [assistedPeople=" + assistedPeople + ", providers="
				+ providers + ", caregivers=" + caregivers + ", disabilities="
				+ disabilities + ", impairments=" + impairments
				+ ", functionalLimitations=" + functionalLimitations
				+ ", diseases=" + diseases + ", education=" + education
				+ ", maritalStatuses=" + maritalStatuses + ", allTools="
				+ allTools + ", connectionDetails=" + connectionDetails
				+ ", serviceModels=" + serviceModels + ", parameters="
				+ parameters + ", parameterTerms=" + parameterTerms
				+ ", status=" + status + ", userActivities=" + userActivities
				+ ", messages=" + messages + ", usage=" + usage + ", answers="
				+ answers + ", login=" + login + ", questions=" + questions
				+ ", questionnaire=" + questionnaire + ", userAnswers="
				+ userAnswers + "]";
	}

}
