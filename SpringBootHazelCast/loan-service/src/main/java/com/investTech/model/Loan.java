package com.investTech.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "LoanData")
public class Loan implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Field(name = "credit_score")
	private String creditScore;
	
	@Field(name = "first_payment_date")
	private String firstPaymentDate;

	@Field(name = "first_time_homebuyer_flag")
	private String firstTimeHomebuyerFlag;

	@Field(name = "maturity_date")
	private String maturityDate;

	@Field(name = "msa_or_metropolitan_division")
	private String msaOrMetropolitanDivision;

	@Field(name = "mortgage_insurance_percent")
	private String mortgageInsurancePercent;

	@Field(name = "no_of_units")
	private String noOfUnits;

	@Field(name = "occupancy_status")
	private String occupancyStatus;
	
	@Field(name = "property_state")
	private String propertyState;
	
	@Field(name = "property_type")
	private String propertyType;
	
	@Field(name = "postal_code")
	private String postalCode;

	@Field(name = "loan_sequence_number")
	private String loanSequenceNumber;
	
	@Field(name = "loan_purpose")
	private String loanPurpose;
	
	@Field(name = "original_loan_term")
	private String originalLoanTerm;
	
	@Field(name = "no_of_borrowers")
	private String noOfBorrowers;
	
	@Field(name = "seller_name")
	private String sellerName;
	
	@Field(name = "servicer_name")
	private String servicerName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(String creditScore) {
		this.creditScore = creditScore;
	}

	public String getFirstPaymentDate() {
		return firstPaymentDate;
	}

	public void setFirstPaymentDate(String firstPaymentDate) {
		this.firstPaymentDate = firstPaymentDate;
	}

	public String getFirstTimeHomebuyerFlag() {
		return firstTimeHomebuyerFlag;
	}

	public void setFirstTimeHomebuyerFlag(String firstTimeHomebuyerFlag) {
		this.firstTimeHomebuyerFlag = firstTimeHomebuyerFlag;
	}

	public String getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String getMsaOrMetropolitanDivision() {
		return msaOrMetropolitanDivision;
	}

	public void setMsaOrMetropolitanDivision(String msaOrMetropolitanDivision) {
		this.msaOrMetropolitanDivision = msaOrMetropolitanDivision;
	}

	public String getMortgageInsurancePercent() {
		return mortgageInsurancePercent;
	}

	public void setMortgageInsurancePercent(String mortgageInsurancePercent) {
		this.mortgageInsurancePercent = mortgageInsurancePercent;
	}

	public String getNoOfUnits() {
		return noOfUnits;
	}

	public void setNoOfUnits(String noOfUnits) {
		this.noOfUnits = noOfUnits;
	}

	public String getOccupancyStatus() {
		return occupancyStatus;
	}

	public void setOccupancyStatus(String occupancyStatus) {
		this.occupancyStatus = occupancyStatus;
	}

	public String getPropertyState() {
		return propertyState;
	}

	public void setPropertyState(String propertyState) {
		this.propertyState = propertyState;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getLoanSequenceNumber() {
		return loanSequenceNumber;
	}

	public void setLoanSequenceNumber(String loanSequenceNumber) {
		this.loanSequenceNumber = loanSequenceNumber;
	}

	public String getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public String getOriginalLoanTerm() {
		return originalLoanTerm;
	}

	public void setOriginalLoanTerm(String originalLoanTerm) {
		this.originalLoanTerm = originalLoanTerm;
	}

	public String getNoOfBorrowers() {
		return noOfBorrowers;
	}

	public void setNoOfBorrowers(String noOfBorrowers) {
		this.noOfBorrowers = noOfBorrowers;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getServicerName() {
		return servicerName;
	}

	public void setServicerName(String servicerName) {
		this.servicerName = servicerName;
	}

	
}
