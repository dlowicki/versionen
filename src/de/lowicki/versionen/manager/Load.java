package de.lowicki.versionen.manager;

import java.io.File;

import org.ini4j.Wini;

import de.lowicki.versionen.main.Main;

public class Load {
  
  public Load() {
	  loadConfig();
  }
  
  private void loadConfig() {
	    try {
	    	new existsFile(Main.path);
	    	Wini ini = new Wini(new File(Main.path));
	    	Main.connectionURL = ini.get("Connection", "connection");
	    	Main.urls.putAll((ini.get("url")));
	    	System.out.println("[Load] Die ConnectionURL und die URLS wurden geladen");
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
  }
}