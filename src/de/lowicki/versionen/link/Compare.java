package de.lowicki.versionen.link;

import java.util.HashMap;

import de.lowicki.versionen.main.Main;

public class Compare {

	String name;
	
	public Compare(HashMap<String, String> acmp, HashMap<String, String> chip) {
			Main.acmpVersionen = getAcmpHash(acmp, chip);
			System.out.println("Vergleiche Daten von ACMP mit Chip.de");
	}
	
	private HashMap<String, String> getAcmpHash(HashMap<String, String> current, HashMap<String, String> chip) {
		HashMap<String, String> hash = new HashMap<String, String>();
		
		current.forEach((k,v) -> {
			String ver = v;
			String verSpl[] = ver.split(" ");
			String cv = verSpl[verSpl.length-1];
			
			
			for(int d = 0; d < verSpl.length-1; d++) {
				if(d == 0) {
					name = verSpl[d];
					continue;
				}
				name = name + " " + verSpl[d];
			}

			// Für jedes CHIP Programm; v = Chip.de Versionen und cv = ACMP Versionen
			/*chip.forEach((key, value) -> {
				// Wenn CHIP Programm-Name gleich ACMP-Name entspricht 
				/*if(key.contentEquals(name)) {
					// Wenn Chip.de == ACMP Version werden beide Versionen ausgegeben
					if(value.equals(cv)) {
						System.out.println(value + " - " + cv);
					} else {
						System.out.println("Neue Version bei " + key + " alte Version: " + cv + " neue Version: " + value);
					}
				}
			});*/
			
			hash.put(name, cv);
			name = "";
		});

		//System.out.println(hash);
		return hash;
	}

}
