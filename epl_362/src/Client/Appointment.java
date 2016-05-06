package Client;

public class Appointment {
	
	private String COMANT;
	private String APPOINTMENT_ID;
	private String CLIENT_ID;
	private String STAFF_ID;
	private String BRANCH_ID;
	private String DATE;
	private String TIME;
	private String ATTEND;
	
	public Appointment(){
		
	}
	public Appointment(String COMMANT, String APPOINTMENT_ID){
		this.setCOMANT(COMMANT);
		if (COMMANT.equals("getAppointmentByClient"))
			this.setCLIENT_ID(APPOINTMENT_ID);
		else
			this.setAPPOINTMENT_ID(APPOINTMENT_ID);
		
	}
	public Appointment(String COMANT,String CLIENT_ID,String STAFF_ID,String BRANCH_ID,String DATE,String TIME,String ATTEND){
		
		if(COMANT.equals("insertAppointment")){
			this.setCOMANT(COMANT);
		}
		else{
			this.setAPPOINTMENT_ID(COMANT);
		}
		
		this.setCLIENT_ID(CLIENT_ID);
		this.setSTAFF_ID(STAFF_ID);
		this.setBRANCH_ID(BRANCH_ID);
		this.setDATE(DATE);
		this.setTIME(TIME);
		this.setATTEND(ATTEND);
		
	}
public Appointment(String COMANT,String APPOINTMENT_ID, String CLIENT_ID,String STAFF_ID,String BRANCH_ID,String DATE,String TIME,String ATTEND){
		
		this.setCOMANT(COMANT);
		this.setAPPOINTMENT_ID(APPOINTMENT_ID);
		this.setCLIENT_ID(CLIENT_ID);
		this.setSTAFF_ID(STAFF_ID);
		this.setBRANCH_ID(BRANCH_ID);
		this.setDATE(DATE);
		this.setTIME(TIME);
		this.setATTEND(ATTEND);
		
	}
	 
	public String getCOMANT() {
		return COMANT;
	}

	public void setCOMANT(String cOMANT) {
		COMANT = cOMANT;
	}

	public String getCLIENT_ID() {
		return CLIENT_ID;
	}

	public void setCLIENT_ID(String cLIENT_ID) {
		CLIENT_ID = cLIENT_ID;
	}

	public String getSTAFF_ID() {
		return STAFF_ID;
	}

	public void setSTAFF_ID(String sTAFF_ID) {
		STAFF_ID = sTAFF_ID;
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

	public String getTIME() {
		return TIME;
	}

	public void setTIME(String tIME) {
		TIME = tIME;
	}

	public String getATTEND() {
		return ATTEND;
	}

	public void setATTEND(String aTTEND) {
		ATTEND = aTTEND;
	}
	public String getAPPOINTMENT_ID() {
		return APPOINTMENT_ID;
	}
	public void setAPPOINTMENT_ID(String aPPOINTMENT_ID) {
		APPOINTMENT_ID = aPPOINTMENT_ID;
	}

}
