package de.lowicki.versionen.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import de.lowicki.versionen.main.Main;
import de.lowicki.versionen.manager.BreakException;

public class Download {
	
	public void downloadFile() {
		createDir(Paths.get(Main.pathDir.toString()));
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Main.downloads.forEach((k,v) -> {
						
						
						
						System.out.println("Starte download von " + Main.downloads.get(k));
						
						try {
							URL url;
							String end = "";
							url = new URL(v);
							URLConnection connection = url.openConnection();
							connection.setRequestProperty("User-Agent", "Mozilla/5.0");
						    InputStream in = connection.getInputStream();
						    
						    
						    if(k.equals("7-Zip") || k.equals("Adobe Flash Player ActiveX") || k.equals("Adobe Flash Player NPAPI") || k.equals("Adobe Flash Player PPAPI")) {
						    	end = ".msi";
						    } else if(k.equals("Notepad++")) {
						    	end = ".zip";
						    } else {
						    	end = ".exe";
						    }
						    
						    FileOutputStream fos = new FileOutputStream(new File(Main.pathDir.toString() + "\\" + k + end));
			
						    int length = -1;
						    byte[] buffer = new byte[1024]; // buffer for portion of data from connection
						    while ((length = in.read(buffer)) > -1) {
						    	if(Main.downloadStop == true) {
									throw new BreakException();
								}
						        fos.write(buffer, 0, length);
						    }
						    fos.close();
						    in.close();
						    System.out.println("Download beendet! Datei befindet sich -> " + Main.pathDir.toString() + "\\" + k + end);
						    TimeUnit.SECONDS.sleep(1);
						} catch (IOException | InterruptedException e) {
							e.printStackTrace();
						}
					});
				} catch(BreakException ex) {
					
				}
			}
		}).start();
		
		
	}
	
	private void createDir(Path path) {
		if(Files.notExists(path)) {
			try {
				Files.createDirectory(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
