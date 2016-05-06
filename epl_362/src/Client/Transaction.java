package Client;

public class Transaction {
	private String CLIENT_ID;
	private String BRANCH_ID;
	private String DATE;
	private String AMOUNT;
	private String COMANT;
	
	public Transaction(){
	}
	public Transaction(String COMANT, String CLIENT_ID){
		this.setCOMANT(COMANT);
		this.setCLIENT_ID(CLIENT_ID);
	}
	
	public Transaction(String CLIENT_ID, String BRANCH_ID, String DATE, String AMOUNT){
		this.setBRANCH_ID(BRANCH_ID);
		this.setCLIENT_ID(CLIENT_ID);
		this.setDATE(DATE);
		this.setAMOUNT(AMOUNT);
	}
	public Transaction(String COMANT, String CLIENT_ID, String BRANCH_ID, String DATE, String AMOUNT){
		this.setCOMANT(COMANT);
		this.setBRANCH_ID(BRANCH_ID);
		this.setCLIENT_ID(CLIENT_ID);
		this.setDATE(DATE);
		this.setAMOUNT(AMOUNT);
	}

	public String getCLIENT_ID() {
		return CLIENT_ID;
	}

	public void setCLIENT_ID(String cLIENT_ID) {
		CLIENT_ID = cLIENT_ID;
	}

	public String getBRANCH_ID() {
		return BRANCH_ID;
	}

	public void setBRANCH_ID(String bRANCH_ID) {
		BRANCH_ID = bRANCH_ID;
	}

	public String getDATE() {
		return DATE;
	}

	public void setDATE(String dATE) {
		DATE = dATE;
	}

	public String getAMOUNT() {
		return AMOUNT;
	}

	public void setAMOUNT(String aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public String getCOMANT() {
		return COMANT;
	}
	public void setCOMANT(String cOMANT) {
		COMANT = cOMANT;
	}
}
