package it.stizzo.FleSorteggiatore;

import java.util.ArrayList;

public class Girone {

	private ArrayList<Squadra> girone;
	
	
	public boolean gironeHaGiaSquadraDiQuestaLega(String lega) {
		boolean trovato = false;
		
		if (girone.size() == 0) { return false;}
		
		for (Squadra s : girone) {
			if (s.getLega().contentEquals(lega)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean gironeHaGiaSquadraDiQuestoRanking(int ranking) {
		boolean trovato = false;
		
		if (girone.size() == 0) { return false;}
		
		for (Squadra s : girone) {
			if (s.getRanking() == ranking) {
				return true;
			}
		}
		return false;
	}
	
	public void add(Squadra s) {
		girone.add(s);
	}
	
	public int size() {
		return girone.size();
	}

	public Girone() {
		girone = new ArrayList<Squadra>();
	}
	
	public Squadra get(int indice) {
		return girone.get(indice);
	}
}
