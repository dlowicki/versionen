package de.lowicki.versionen.manager;

import java.io.File;
import java.nio.file.Path;

import de.lowicki.versionen.main.Main;

public class ExistsFile {
	  public ExistsFile(Path path) {
	    File f = new File(path.toString());
	    
	    if(f.exists()) {
		    if ((!f.isDirectory()) && (f.isFile())) {
		    	if(path == Main.pathConfig)
		    		Main.configStatus = true;
		    	
		    	System.out.println("[existsFile] " + path + " existiert!");
		    }
	    } else {
	    	if(path == Main.pathConfig)
	    		Main.configStatus = false;
	    }
	  }
}
