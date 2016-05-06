package Client;

public class Login {
	private String COMANT;
	private String USERNAME;
	private String PASSWORD;
	
	public Login(){
		
	}
	
	public Login(String COMANT, String USERNAME, String PASSWORD){
		this.setCOMANT(COMANT);
		this.setPASSWORD(PASSWORD);
		this.setUSERNAME(USERNAME);
	}
	public String getCOMANT() {
		return COMANT;
	}
	public void setCOMANT(String cOMANT) {
		COMANT = cOMANT;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	
}
