package de.lowicki.versionen.link;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import de.lowicki.versionen.gui.StartGUI;
import de.lowicki.versionen.main.Main;

public class Versionen {
    private String html = "";

    public Versionen() {
        //System.out.println(Main.urls);
        Main.urls.forEach((k, v) -> {
            //System.out.println("Das Program: " + k + " Die Url: " + v);
            this.chipVersion((String)k, (String)v);
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("Gespeichert wurde: " + Main.chipVersionen);
        Main.configReady = true;
    }

    private void chipVersion(String program, String url) {
        try {
            String inputLine;
            URL oracle = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
            this.html = "";
            while ((inputLine = in.readLine()) != null) {
                this.html = String.valueOf(this.html) + inputLine;
            }
            in.close();
            Document doc = Jsoup.parse(this.html);
            String version = doc.select("span.dl-version").text();
            
            version = cutVersion(version);
            
            Main.chipVersionen.put(program, version);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private String cutVersion(String version) {
    	String temp = version.replaceAll("[A-Za-z]","");
    	temp = temp.replaceAll(" ","");
    	return temp;
    }

}
