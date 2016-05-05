package Client;

public class Customer {
	
	private String COMANT;
	private	String CLIENT_ID;
	private String LAST_NAME;
	private String FIRST_NAME;
	private String DATE_OF_BIRTH;
	private String PHONE_NUMBER;
	private String EMAIL;
	
			
	public Customer(){
		
	}
	
	public Customer(String COMANT,String CLIENT_ID,String LAST_NAME,String FIRST_NAME,String DATE_OF_BIRTH,String PHONE_NUMBER,String EMAIL){
		this.setCOMANT(COMANT);
		this.setCLIENT_ID(CLIENT_ID);
		this.setLAST_NAME(LAST_NAME);
		this.setFIRST_NAME(FIRST_NAME);
		this.setDATE_OF_BIRTH(DATE_OF_BIRTH);
		this.setPHONE_NUMBER(PHONE_NUMBER);
		this.setEMAIL(EMAIL);
	}

	public String getCLIENT_ID() {
		return CLIENT_ID;
	}

	public void setCLIENT_ID(String cLIENT_ID) {
		CLIENT_ID = cLIENT_ID;
	}

	public String getLAST_NAME() {
		return LAST_NAME;
	}

	public void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME;
	}

	public String getFIRST_NAME() {
		return FIRST_NAME;
	}

	public void setFIRST_NAME(String fIRST_NAME) {
		FIRST_NAME = fIRST_NAME;
	}

	public String getDATE_OF_BIRTH() {
		return DATE_OF_BIRTH;
	}

	public void setDATE_OF_BIRTH(String dATE_OF_BIRTH) {
		DATE_OF_BIRTH = dATE_OF_BIRTH;
	}

	public String getPHONE_NUMBER() {
		return PHONE_NUMBER;
	}

	public void setPHONE_NUMBER(String pHONE_NUMBER) {
		PHONE_NUMBER = pHONE_NUMBER;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getCOMANT() {
		return COMANT;
	}

	public void setCOMANT(String cOMANT) {
		COMANT = cOMANT;
	}
	
	

}
