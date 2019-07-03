package de.lowicki.versionen.manager;

import java.io.File;
import java.nio.file.Path;

import de.lowicki.versionen.main.Main;

public class existsFile {
	  public existsFile(Path path) {
	    File f = new File(path.toString());
	    
	    if(f.exists()) {
		    if ((!f.isDirectory()) && (f.isFile())) {
		      System.out.println("[existsFile] Es wurde eine Datei gefunden");
		      Main.configStatus = true;
		    }
	    } else {
	    	Main.configStatus = false;
	    }
	  }
	  

}
