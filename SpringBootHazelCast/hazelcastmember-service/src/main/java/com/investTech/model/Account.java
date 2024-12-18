package com.investTech.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Accounts")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Field(name = "state_id")
	private String stateId;
	
	@Field(name = "state_name")
	private String stateName;
	
	@Field(name = "year_reported")
	private String yearReported;
	
	@Field(name = "program_name")
	private String programName;
	
	@Field(name = "program_type")
	private String programType;	
	
	@Field(name = "disbursement_date")
	private String disbursementDate;
	
	@Field(name = "loan_investment_amount")
	private String loanInvestAmount;
	 
    @Field(name="ssbci_original_funds")
	private String sssbci_original_funds;
    
    @Field(name="zip_code")
  	private String zipCode;
    
    @Field(name="full_time_employees")
  	private String fulltimeEmployeesCount;         
    
    @Field(name="naics_code")
  	private String naicsCode;
	
    @Field(name="year_incorporated")
  	private String yearIncorporated;
    
    @Field(name="jobs_created")
  	private String jobsCreated;
	
    @Field(name="jobs_retained")
  	private String jobsRetained;
    
    @Field(name="trans_type")
  	private String transactionType;
    
    @Field(name="CDFI_type")
  	private String cdFiType;
    
    @Field(name="MDI_type")
  	private String mdiType;
    
    @Field(name="VC_cat")
  	private String vcCat;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getYearReported() {
		return yearReported;
	}

	public void setYearReported(String yearReported) {
		this.yearReported = yearReported;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProgramType() {
		return programType;
	}

	public void setProgramType(String programType) {
		this.programType = programType;
	}

	public String getDisbursementDate() {
		return disbursementDate;
	}

	public void setDisbursementDate(String disbursementDate) {
		this.disbursementDate = disbursementDate;
	}

	public String getLoanInvestAmount() {
		return loanInvestAmount;
	}

	public void setLoanInvestAmount(String loanInvestAmount) {
		this.loanInvestAmount = loanInvestAmount;
	}

	public String getSssbci_original_funds() {
		return sssbci_original_funds;
	}

	public void setSssbci_original_funds(String sssbci_original_funds) {
		this.sssbci_original_funds = sssbci_original_funds;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getFulltimeEmployeesCount() {
		return fulltimeEmployeesCount;
	}

	public void setFulltimeEmployeesCount(String fulltimeEmployeesCount) {
		this.fulltimeEmployeesCount = fulltimeEmployeesCount;
	}

	public String getNaicsCode() {
		return naicsCode;
	}

	public void setNaicsCode(String naicsCode) {
		this.naicsCode = naicsCode;
	}

	public String getYearIncorporated() {
		return yearIncorporated;
	}

	public void setYearIncorporated(String yearIncorporated) {
		this.yearIncorporated = yearIncorporated;
	}

	public String getJobsCreated() {
		return jobsCreated;
	}

	public void setJobsCreated(String jobsCreated) {
		this.jobsCreated = jobsCreated;
	}

	public String getJobsRetained() {
		return jobsRetained;
	}

	public void setJobsRetained(String jobsRetained) {
		this.jobsRetained = jobsRetained;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getCdFiType() {
		return cdFiType;
	}

	public void setCdFiType(String cdFiType) {
		this.cdFiType = cdFiType;
	}

	public String getMdiType() {
		return mdiType;
	}

	public void setMdiType(String mdiType) {
		this.mdiType = mdiType;
	}

	public String getVcCat() {
		return vcCat;
	}

	public void setVcCat(String vcCat) {
		this.vcCat = vcCat;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", stateId=" + stateId + ", stateName=" + stateName + ", yearReported="
				+ yearReported + ", programName=" + programName + ", programType=" + programType + ", disbursementDate="
				+ disbursementDate + ", loanInvestAmount=" + loanInvestAmount + ", sssbci_original_funds="
				+ sssbci_original_funds + ", zipCode=" + zipCode + ", fulltimeEmployeesCount=" + fulltimeEmployeesCount
				+ ", naicsCode=" + naicsCode + ", yearIncorporated=" + yearIncorporated + ", jobsCreated=" + jobsCreated
				+ ", jobsRetained=" + jobsRetained + ", transactionType=" + transactionType + ", cdFiType=" + cdFiType
				+ ", mdiType=" + mdiType + ", vcCat=" + vcCat + "]";
	}


  


}
