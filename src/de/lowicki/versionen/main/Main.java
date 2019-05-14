package de.lowicki.versionen.main;

import java.util.HashMap;

import de.lowicki.versionen.gui.GUI;
import de.lowicki.versionen.link.Versionen;
import de.lowicki.versionen.manager.Config;

public class Main {
  private Config config;
  
  public static HashMap<String, String> urls = new HashMap();
  public static HashMap<String, String> versionen = new HashMap();
  
  public static void main(String[] args) {
    
	new Config();
    new Versionen();
    new GUI();
  }
  
  public Config getConfig()
  {
    return config;
  }
}
