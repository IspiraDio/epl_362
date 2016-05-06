package Client;

public class Consultation {
	private String CONSULTATION_ID;
	private String DATE;
	private String CLIENT_ID;
	private String BRANCH_ID;
	private String STAFF_ID;
	private String RECOMMENTATION;
	private String ATTEND;
	private String COMANT;
	
	public Consultation (){
		
	}
	
	public Consultation (String COMANT, String CONSULTATION_ID){
		this.setCOMMANT(COMANT);
		this.setCONSULTATION_ID(CONSULTATION_ID);
	}
	
	public Consultation (String COMANT, String CLIENT_ID, String STAFF_ID, String BRANCH_ID, String DATE, String RECOMMENTATION,String ATTEND){
		if(COMANT.equals("insertConsultation")){
			this.setCOMMANT(COMANT);
		}
		else{
			this.setCONSULTATION_ID(COMANT);
		}
		this.setCLIENT_ID(CLIENT_ID);
		this.setSTAFF_ID(STAFF_ID);
		this.setBRANCH_ID(BRANCH_ID);
		this.setDATE(DATE);
		this.setRECOMMENTATION(RECOMMENTATION);
		this.setATTEND(ATTEND);
		
	}
	
	public Consultation (String COMMANT, String CONSULTATION, String CLIENT_ID, String STAFF_ID, String BRANCH_ID, String DATE, String RECOMMENTATION,String ATTEND){
		this.setCOMMANT(COMMANT);
		this.setCONSULTATION_ID(CONSULTATION);
		this.setCLIENT_ID(CLIENT_ID);
		this.setSTAFF_ID(STAFF_ID);
		this.setBRANCH_ID(BRANCH_ID);
		this.setDATE(DATE);
		this.setRECOMMENTATION(RECOMMENTATION);
		this.setATTEND(ATTEND);
	 
	}
		
	public Consultation (String CLIENT_ID, String STAFF_ID, String BRANCH_ID, String DATE, String RECOMMENTATION,String ATTEND){
		this.setCOMMANT(COMANT);
		this.setCONSULTATION_ID(CONSULTATION_ID);
	}

	public String getCONSULTATION_ID() {
		return CONSULTATION_ID;
	}

	public void setCONSULTATION_ID(String cONSULTATION_ID) {
		CONSULTATION_ID = cONSULTATION_ID;
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

	public String getSTAFF_ID() {
		return STAFF_ID;
	}

	public void setSTAFF_ID(String sTAFF_ID) {
		STAFF_ID = sTAFF_ID;
	}

 
	public String getATTEND() {
		return ATTEND;
	}

	public void setATTEND(String aTTEND) {
		ATTEND = aTTEND;
	}

	public String getCOMMANT() {
		return COMANT;
	}

	public void setCOMMANT(String cOMMANT) {
		COMANT = cOMMANT;
	}

	public String getRECOMMENTATION() {
		return RECOMMENTATION;
	}

	public void setRECOMMENTATION(String rECOMMENTATION) {
		RECOMMENTATION = rECOMMENTATION;
	}
}
