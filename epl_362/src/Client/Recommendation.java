package Client;

public class Recommendation {
	private String CLIENT_ID;
	private String RECOMMENDATION;
	private String COMANT;
	
	public Recommendation() {

	}
	
	public Recommendation(String COMANT, String CLIENT_ID) {
		if (COMANT.equals("geRecommendationByClient"))
			this.setCOMANT(COMANT);
		else 
			this.setRECOMMENDATION(COMANT);
		
		this.setCLIENT_ID(CLIENT_ID);
		
	}
	/*public Recommendation(String COMANT, String CLIENT_ID, String RECOMMENDATION) {
		this.setCLIENT_ID(CLIENT_ID);
		this.setRECOMMENDATION(RECOMMENDATION);
		this.setCOMANT(COMANT);
		
	}*/
	public String getCLIENT_ID() {
		return CLIENT_ID;
	}

	public void setCLIENT_ID(String cLIENT_ID) {
		CLIENT_ID = cLIENT_ID;
	}

	public String getRECOMMENDATION() {
		return RECOMMENDATION;
	}

	public void setRECOMMENDATION(String rECOMMENDATION) {
		RECOMMENDATION = rECOMMENDATION;
	}

	public String getCOMANT() {
		return COMANT;
	}

	public void setCOMANT(String cOMANT) {
		COMANT = cOMANT;
	}
}