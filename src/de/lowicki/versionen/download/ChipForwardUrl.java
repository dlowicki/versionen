package de.lowicki.versionen.download;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.lowicki.versionen.main.Main;

public class ChipForwardUrl {
	private String html = "";
	private ArrayList<String> forward = new ArrayList<String>();
	private ArrayList<String> access = new ArrayList<String>();
	
	public ChipForwardUrl() {
<<<<<<< HEAD
		System.out.println("[ChipForwardUrl] Access Seiten werden von Chip.de geladen");
		chipURL("7-Zip", Main.downloads.get("7-Zip"));
		chipURL("PDF24", Main.downloads.get("PDF24"));
		chipURL("Notepad++", Main.downloads.get("Notepad++"));
		System.out.println(forward);
		
		access.add(chipReceiveDataUrl(chipReceiveAccessURL(forward.get(0))));
		access.add(chipReceiveDataUrl(chipReceiveAccessURL(forward.get(1))));
		access.add(chipReceiveDataUrl(chipReceiveAccessURL(forward.get(2))));
		writeToDownloads();
		Main.downloads.replace("7-Zip", Main.downloads.get("7-Zip"), access.get(0));
		Main.downloads.replace("PDF24", Main.downloads.get("PDF24"), access.get(1));
		Main.downloads.replace("Notepad++", Main.downloads.get("Notepad++"), access.get(2));
	}
	
	private void writeToDownloads() {
		String temp = "";
		for(int d = 0; d < this.access.size(); d++) {
			temp = temp + d + "=" + this.access.get(d) + "\r\n";
		}
		
        try {
        	FileWriter w = new FileWriter(Main.pathDownloads.toString());
			w.write(temp);
			w.close();
			System.out.println("[ChipDownloadUrl] Downloads Variablen wurden gespeichert!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    private void chipURL(String program, String url) {
        try {
            String inputLine;
            URL oracle = new URL(url);
            
            URLConnection connection = oracle.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
		    
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            this.html = "";
            while ((inputLine = in.readLine()) != null) {
                this.html = String.valueOf(this.html) + inputLine;
            }
            in.close();
            
            Document doc = Jsoup.parse(this.html);
            Elements newsHeadlines = doc.select(".ManualInstallLink");
            
            for(Element headline : newsHeadlines) {
            	String href = headline.absUrl("href");
            	System.out.println(href);
=======
		chipURL("7-Zip", Main.downloads.get("7-Zip"));
		chipURL("PDF24", Main.downloads.get("PDF24"));
		chipURL("Notepad++", Main.downloads.get("Notepad++"));
		
		access.add(chipReceiveDataUrl(chipReceiveAccessURL(forward.get(0))));
		access.add(chipReceiveDataUrl(chipReceiveAccessURL(forward.get(1))));
		access.add(chipReceiveDataUrl(chipReceiveAccessURL(forward.get(2))));
		writeToDownloads();
	}
	
	private void writeToDownloads() {
		String temp = "";
		for(int d = 0; d < this.access.size(); d++) {
			temp = temp + d + "=" + this.access.get(d) + "\r\n";
		}
		
        try {
        	FileWriter w = new FileWriter(Main.pathDownloads.toString());
			w.write(temp);
			w.close();
			System.out.println("[ChipDownloadUrl] Downloads Variablen wurden gespeichert!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    private void chipURL(String program, String url) {
        try {
            String inputLine;
            URL oracle = new URL(url);
            
            URLConnection connection = oracle.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
		    
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            this.html = "";
            while ((inputLine = in.readLine()) != null) {
                this.html = String.valueOf(this.html) + inputLine;
            }
            in.close();
            
            Document doc = Jsoup.parse(this.html);
            Elements newsHeadlines = doc.select(".ManualInstallLink");
            
            for(Element headline : newsHeadlines) {
            	String href = headline.absUrl("href");
>>>>>>> refs/remotes/origin/master
            	if(href.length() > 20) {
            		forward.add(href);
            		break;
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private String chipReceiveAccessURL(String url) {
        try {
            String inputLine;
            URL oracle = new URL(url);
            
            URLConnection connection = oracle.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
		    
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            this.html = "";
            while ((inputLine = in.readLine()) != null) {
                this.html = String.valueOf(this.html) + inputLine;
            }
            in.close();
            
            Document doc = Jsoup.parse(this.html);
            Elements newsHeadlines = doc.select(".ButtonAuswahl__Icon--Arrow");
            
            for(Element headline : newsHeadlines) {
            	String href = headline.absUrl("href");
            	//System.out.println("HREF: " + href);
            	if(href.length() == 0)
            		continue;
            	
            	return href;
            }
            return "";
        } catch (IOException e) {
            e.printStackTrace();
        }
		return "";
    }
    
    private String chipReceiveDataUrl(String url) {
        try {
            String inputLine;
            URL oracle = new URL(url);
            
            URLConnection connection = oracle.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
		    
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            this.html = "";
            while ((inputLine = in.readLine()) != null) {
                this.html = String.valueOf(this.html) + inputLine;
            }
            in.close();
            
            Document doc = Jsoup.parse(this.html);
            Elements newsHeadlines = doc.select(".c-brand");
            
            for(Element headline : newsHeadlines) {
            	String href = headline.absUrl("href");
            	if(href.length() == 0)
            		continue;
            	
            	return href;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return "";
    }

}
