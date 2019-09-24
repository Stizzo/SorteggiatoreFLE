package it.stizzo.FleSorteggiatore;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Sorteggiatore {
	
	ArrayList<Squadra> squadre;
	ArrayList<String> leghe;
	ArrayList<Girone> risultato;

	
	public int leggiSquadre(String path) { 
		
		squadre = new ArrayList<Squadra>();
		Scanner scanner = null;
		String line;
		FileReader reader;
		try {
		
			reader = new FileReader(path);
			scanner = new Scanner(reader);
			
		
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				String[] arraySplittate = line.split("\\|");
			
				Squadra s = new Squadra(arraySplittate[0].trim(), arraySplittate[1].trim(), Integer.parseInt(arraySplittate[2].trim()));
			
				squadre.add(s);
		}
		scanner.close();
		//JOptionPane.showMessageDialog(null, "Lettura e caricamento della struttura MCC avvenuta con successo!");
		Collections.shuffle(squadre);
		}catch (Exception e) {
			return 1;
		}
		return 0;
	}

	public void leggiLeghe(ArrayList<Squadra> squadre){
		leghe = new ArrayList<String>();
		for (int i = 0; i < squadre.size(); i++) {
			boolean trovato = false;
			
			for (int j = 0; j < leghe.size(); j++) {
				if (squadre.get(i).getLega().contentEquals(leghe.get(j))) {
					trovato = true;
				}
			}
			
			if (!trovato) {
				leghe.add(squadre.get(i).getLega());
			}
			
		}
		
	}
	
	public ArrayList<Girone> procediAllEstrazione(int numGironi, int squadrePerGirone){
		risultato = new ArrayList<Girone>();
		
		for (int i = 0; i < numGironi; i++) {
			Girone g = new Girone();
				
			for (int j = squadre.size()-1; j >= 0; j--) {
				if (!g.gironeHaGiaSquadraDiQuestaLega(squadre.get(j).getLega()) &&
					!g.gironeHaGiaSquadraDiQuestoRanking(squadre.get(j).getRanking()) &&
					g.size() < squadrePerGirone){
					
					g.add(squadre.get(j));
					squadre.remove(j);
				}
				//if (j == 0 && squadre.size() != 0) {
				//	j = squadre.size();
				//}
			}
				
			risultato.add(g);
		}
		
		if (autoControlloRisultato(numGironi, squadrePerGirone, risultato)) {
			return risultato;
		}
		
		return risultato;
		
	}
	
	
	
	private boolean autoControlloRisultato(int numGironi, int squadrePerGirone, ArrayList<Girone> risultato) {
		
		for (int k = 0; k < risultato.size(); k++) {
			if (risultato.get(k).size() != squadrePerGirone) {
				procediAllEstrazione(numGironi, squadrePerGirone);
			}
		}
		return true;
		
	}
}
