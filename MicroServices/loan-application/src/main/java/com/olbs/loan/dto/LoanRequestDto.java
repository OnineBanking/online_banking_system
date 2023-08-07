package com.olbs.loan.dto;


	import lombok.Data;
	

    @Data 
	public class LoanRequestDto {

	 	private int lnumber;
		private String bid; 
	    private int loan_amount;
	    private double ltenure;
	    private double lemi; 
		private int custid;
		private String status;
		private int acnumber;
		
		public int getAcnumber() {
			return acnumber;
		}
		public void setAcnumber(int acnumber) {
			this.acnumber = acnumber;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getLnumber() {
			return lnumber;
		}
		public void setLnumber(int lnumber) {
			this.lnumber = lnumber;
		}
		public String getBid() {
			return bid;
		}
		public void setBid(String bid) {
			this.bid = bid;
		}
		public int getLoan_amount() {
			return loan_amount;
		}
		public void setLoan_amount(int loan_amount) {
			this.loan_amount = loan_amount;
		}
		public double getLtenure() {
			return ltenure;
		}
		public void setLtenure(double ltenure) {
			this.ltenure = ltenure;
		}
		public double getLemi() {
			return lemi;
		}
		public void setLemi(double lemi) {
			this.lemi = lemi;
		}
		public int getCustId() {
			return custid;
		}
		public void setCustId(int custId) {
			this.custid = custId;
		}

		

}
