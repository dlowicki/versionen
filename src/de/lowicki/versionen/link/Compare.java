package de.lowicki.versionen.link;

import java.util.ArrayList;
import java.util.HashMap;

public class Compare {
	
	private HashMap<String, String> acmpVersionen = new HashMap<String, String>();
	String name;
	
	public Compare(ArrayList<String> acmp, HashMap<String, String> chip) {
		//, HashMap<String, String> chip
		getAcmpHash(acmp, chip);
		//getChipHash(chip);
	}
	
	private void getChipHash(HashMap<String, String> chip) {
		

		
	}
	
	private HashMap<String, String> getAcmpHash(ArrayList<String> current, HashMap<String, String> chip) {
		HashMap<String, String> hash = new HashMap<String, String>();
		
		for(int i = 0; i < current.size(); i++) {
			String ver = current.get(i);
			String verSpl[] = ver.split(" ");
			String cv = verSpl[verSpl.length-1];
			
			
			for(int d = 0; d < verSpl.length-1; d++) {
				if(d == 0) {
					name = verSpl[d];
					continue;
				}
				name = name + " " + verSpl[d];
			}
			
			//System.out.println(name + " - " + cv);
			
			
			// Für jedes CHIP Programm
			chip.forEach((k, v) -> {
				// Wenn CHIP Programm-Name gleich ACMP-Name entspricht
				if(k.contentEquals(name)) {
					System.out.println(v + " - " + cv);
					
				} else {
					//System.out.println(k + " - " + name);
				}
			});			
			
			hash.put(name, cv);
			name = "";
		}
		
		
		
		return hash;
	}

}
