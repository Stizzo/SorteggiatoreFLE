package it.stizzo.FleSorteggiatore;

public class Squadra {
	
	private String nome;
	private String lega;
	private int ranking;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLega() {
		return lega;
	}
	public void setLega(String lega) {
		this.lega = lega;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public Squadra(String nome, String lega, int ranking) {
		super();
		this.nome = nome;
		this.lega = lega;
		this.ranking = ranking;
	}
	
	public String toString() {
		return nome + " di " + lega + " (ranking " + ranking + ")";
	}

}
