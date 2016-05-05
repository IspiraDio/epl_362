package Client;

public class staff {
	
	private String COMANT;
	private String STAFF_ID;
	private String FIRST_NAME;
	private String LAST_NAME;
	private String DATE_OF_BIRTH;
	private String PHONE_NUMBER;
	private String EMAIL;
	private String ADDRESS;
	
	public staff(){
		
	}
	
	public staff(String COMANT,String STAFF_ID,String FIRST_NAME,String LAST_NAME,String DATE_OF_BIRTH,String PHONE_NUMBER,String EMAIL,String ADDRESS){
		
		this.setCOMANT(COMANT);		
		this.setSTAFF_ID(STAFF_ID);
		this.setFIRST_NAME(FIRST_NAME);
		this.setLAST_NAME(LAST_NAME);
		this.setDATE_OF_BIRTH(DATE_OF_BIRTH);
		this.setPHONE_NUMBER(PHONE_NUMBER);
		this.setEMAIL(EMAIL);
		this.setADDRESS(ADDRESS);
		
	}

	public String getCOMANT() {
		return COMANT;
	}

	public void setCOMANT(String cOMANT) {
		COMANT = cOMANT;
	}

	public String getFIRST_NAME() {
		return FIRST_NAME;
	}

	public void setFIRST_NAME(String fIRST_NAME) {
		FIRST_NAME = fIRST_NAME;
	}

	public String getSTAFF_ID() {
		return STAFF_ID;
	}

	public void setSTAFF_ID(String sTAFF_ID) {
		STAFF_ID = sTAFF_ID;
	}

	public String getLAST_NAME() {
		return LAST_NAME;
	}

	public void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME;
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

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

}
