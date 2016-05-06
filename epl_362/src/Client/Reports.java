package Client;

public class Reports {
	private String REPORT_ID;
	private String MONTH;
	private String RESULT;
	
	public Reports() {

	}
	
	public Reports(String MONTH, String RESULT) {
		this.setMONTH(MONTH);
		this.setRESULT(RESULT);
	}
	
	public Reports(String REPORT_ID, String MONTH, String NUM_OF_CLIENTS) {
		this.setREPORT_ID(REPORT_ID);
		this.setMONTH(MONTH);
		this.setRESULT(NUM_OF_CLIENTS);
	}


	public String getMONTH() {
		return MONTH;
	}

	public void setMONTH(String mONTH) {
		MONTH = mONTH;
	}

	public String getRESULT() {
		return RESULT;
	}

	public void setRESULT(String rESULT) {
		RESULT = rESULT;
	}

	public String getREPORT_ID() {
		return REPORT_ID;
	}

	public void setREPORT_ID(String rEPORT_ID) {
		REPORT_ID = rEPORT_ID;
	}
}
