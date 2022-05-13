package it.polito.tdp.extflightdelays.model;

public class CoppiaAirport {
	private int aPartenzaId;
	private int aArrivoId;
	private int avgDist;
	
	public CoppiaAirport(int aPartenzaId, int aArrivoId, int avgDist) {
		this.aPartenzaId = aPartenzaId;
		this.aArrivoId = aArrivoId;
		this.avgDist = avgDist;
	}
	
	public int getaPartenzaId() {
		return aPartenzaId;
	}
	public void setaPartenzaId(int aPartenzaId) {
		this.aPartenzaId = aPartenzaId;
	}
	public int getaArrivoId() {
		return aArrivoId;
	}
	public void setaArrivoId(int aArrivoId) {
		this.aArrivoId = aArrivoId;
	}
	public int getAvgDist() {
		return avgDist;
	}
	public void setAvgDist(int avgDist) {
		this.avgDist = avgDist;
	}
	
	
}
