package it.polito.tdp.extflightdelays.db;

public class CoppiaAirports implements Comparable<CoppiaAirports>{
	private String a1;
	private String a2;
	private int peso;
	public CoppiaAirports(String a1, String a2, int peso) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.peso = peso;
	}
	public String getA1() {
		return a1;
	}
	public void setA1(String a1) {
		this.a1 = a1;
	}
	public String getA2() {
		return a2;
	}
	public void setA2(String a2) {
		this.a2 = a2;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public int compareTo(CoppiaAirports o) {
		// TODO Auto-generated method stub
		return -(this.peso-o.getPeso());
	}
	
}
