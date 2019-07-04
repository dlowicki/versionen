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
	    	new ExistsFile(Main.path);
	    	Wini ini = new Wini(new File(Main.path.toString()));
	    	
	    	String dec = decrypt(ini.get("Connection", "connection"));
	    	if(dec != null) {
		    	Main.connectionURL = dec;
		    	Main.urls.putAll((ini.get("url")));
		    	System.out.println("[Load] Die ConnectionURL und die URLS wurden geladen");
	    	}
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
  }
  
  private String decrypt(String key) {
	  try {
		  AESSync d = new AESSync();
		  return d.decrypt(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;
  }
}