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

public class Download {
	
	public void downloadFile() {
		createDir(Paths.get("C:\\Users\\David\\Desktop\\downloads"));
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
			    
			    FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\David\\Desktop\\downloads\\" + k + end));

			    int length = -1;
			    byte[] buffer = new byte[1024];// buffer for portion of data from
			    // connection
			    while ((length = in.read(buffer)) > -1) {
			        fos.write(buffer, 0, length);
			    }
			    fos.close();
			    in.close();
			    System.out.println("Download beendet!");
			    TimeUnit.SECONDS.sleep(1);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		});
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
