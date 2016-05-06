package Client;

public class Branch {
	private String BRANCH_ID;
	private String CITY;
	private String STREET_NAME;
	private String ZIP_CODE;
	private String COUNTRY;
	private String COMANT;
	private String MONTH;
	
public Branch(){
	}
public Branch(String COMANT, String BRANCH_ID, String MONTH){
	this.setBRANCH_ID(BRANCH_ID);
	this.setCOMANT(COMANT);
	this.setMONTH(MONTH);
}

public Branch(String COMANT, String BRANCH_ID, String STREET_NAME, String ZIP_CODE, String CITY,String COUNTRY ){
	
	this.setCOMANT(COMANT);
	this.setBRANCH_ID(BRANCH_ID);
	this.setCITY(CITY);
	this.setCOUNTRY(COUNTRY);
	this.setSTREET_NAME(STREET_NAME);
	this.setZIP_CODE(ZIP_CODE);
}
public Branch(String COMANT,String BRANCH_ID){
	this.setCOMANT(COMANT);
	this.setBRANCH_ID(BRANCH_ID);
}

public Branch(String BRANCH_ID, String STREET_NAME, String ZIP_CODE, String CITY,String COUNTRY  ){
	
	this.setBRANCH_ID(BRANCH_ID);
	this.setCITY(CITY);
	this.setCOUNTRY(COUNTRY);
	this.setSTREET_NAME(STREET_NAME);
	this.setZIP_CODE(ZIP_CODE);
}

public String getCITY() {
	return CITY;
}

public void setCITY(String cITY) {
	CITY = cITY;
}

public String getBRANCH_ID() {
	return BRANCH_ID;
}

public void setBRANCH_ID(String bRANCH_ID) {
	BRANCH_ID = bRANCH_ID;
}

public String getSTREET_NAME() {
	return STREET_NAME;
}

public void setSTREET_NAME(String sTREET_NAME) {
	STREET_NAME = sTREET_NAME;
}

public String getZIP_CODE() {
	return ZIP_CODE;
}

public void setZIP_CODE(String zIP_CODE) {
	ZIP_CODE = zIP_CODE;
}

public String getCOUNTRY() {
	return COUNTRY;
}

public void setCOUNTRY(String cOUNTRY) {
	COUNTRY = cOUNTRY;
}

public String getCOMANT() {
	return COMANT;
}

public void setCOMANT(String cOMANT) {
	COMANT = cOMANT;
}
public String getMONTH() {
	return MONTH;
}
public void setMONTH(String mONTH) {
	MONTH = mONTH;
}
}
