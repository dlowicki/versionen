package de.lowicki.versionen.manager;

import java.io.File;

public class existsFile {
	  public existsFile(String path)
	  {
	    File f = new File(path);
	    if ((f.exists()) && (!f.isDirectory()) && (f.isFile())) {
	      System.out.println("[existsFile] Es wurde eine Datei gefunden");
	    } else {
	      try {
	        f.createNewFile();
	        System.out.println("[existsFile] Es wurde keine Datei gefunden - Datei wird erstellt");
	      } catch (java.io.IOException e) {
	        e.printStackTrace();
	      }
	    }
	  }
}
