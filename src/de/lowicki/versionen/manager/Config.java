package de.lowicki.versionen.manager;

import java.io.File;

import org.ini4j.Wini;

import de.lowicki.versionen.main.Main;

public class Config
{
  private String path = "C:\\Users\\dlowicki\\Desktop\\David\\Java\\Projekt\\config.ini";
  
  public Config() {
    try { new existsFile(path);
      Wini ini = new Wini(new File(path));
      Main.urls.putAll((ini.get("url")));
      
      System.out.println("Die URLS wurden geladen");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
