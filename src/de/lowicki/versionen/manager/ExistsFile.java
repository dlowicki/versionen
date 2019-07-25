package de.lowicki.versionen.manager;

import java.io.File;
import java.nio.file.Path;

import de.lowicki.versionen.main.Main;

public class ExistsFile {
	  public ExistsFile(Path path) {
	    File f = new File(path.toString());
	    
	    if(f.exists()) {
		    if ((!f.isDirectory()) && (f.isFile())) {
		    	Main.configStatus = true;
		    	System.out.println("[existsFile] Es wurde eine Datei gefunden");
		    }
	    } else {
	    	Main.configStatus = false;
	    }
	  }
}
