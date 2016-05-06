package Client;

public class Consultation_Comment {
	private String STAFF_ID;
	private String COMMENT;
	private String CONSULTATION_ID;
	private String COMMANT;
	
	public Consultation_Comment(){
		
	}
	public Consultation_Comment(String STAFF_ID,String COMMENT, String CONSULTATION_ID){
		this.setCOMMENT(COMMENT);
		this.setCONSULTATION_ID(CONSULTATION_ID);
		this.setSTAFF_ID(STAFF_ID);
	}
	
	public Consultation_Comment(String COMMANT, String STAFF_ID,String COMMENT, String CONSULTATION_ID){
		this.setCOMMANT(COMMANT);
		this.setCOMMENT(COMMENT);
		this.setCONSULTATION_ID(CONSULTATION_ID);
		this.setSTAFF_ID(STAFF_ID);
	}
	public String getSTAFF_ID() {
		return STAFF_ID;
	}

	public void setSTAFF_ID(String sTAFF_ID) {
		STAFF_ID = sTAFF_ID;
	}

	public String getCONSULTATION_ID() {
		return CONSULTATION_ID;
	}

	public void setCONSULTATION_ID(String cONSULTATION_ID) {
		CONSULTATION_ID = cONSULTATION_ID;
	}

	public String getCOMMENT() {
		return COMMENT;
	}

	public void setCOMMENT(String cOMMENT) {
		COMMENT = cOMMENT;
	}
	public String getCOMMANT() {
		return COMMANT;
	}
	public void setCOMMANT(String cOMMANT) {
		COMMANT = cOMMANT;
	}
}
