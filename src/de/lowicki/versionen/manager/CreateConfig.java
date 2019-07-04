package de.lowicki.versionen.manager;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.ini4j.Wini;

import de.lowicki.versionen.main.Main;

public class CreateConfig {
	
	  public CreateConfig(Path path) {
	      try {
	    	  File f = new File(path.toString());
	    	  
	    	  if(f.exists()) {
	    		  System.out.println("[CreateConfig] Config existiert bereits");
	    		  return;
	    	  }
	    	  
	    	  Path pathToFile = Paths.get("C:\\ProgramData\\Chip Versionen\\");
	    	  Files.createDirectories(pathToFile);
	    	 	    	  
	    	  f.createNewFile();
		        
		        
		        FileWriter w = new FileWriter(path.toString());
		        w.write("[Connection]\r\n" + 
		        		"connection=KGJUldblcX2nV1Y504JNjvsUv3rl1yeD5S/8rq2rZSNiPmXPYe14wUFUaLUnWXKTC0p/Y+Co12R+B2Ap0ehz1J9h595GRPnMjZjFeemadBAQFcfCNNwdWzzwvqlIqmcL\r\n" + 
		        		"\r\n" + 
		        		"[url]\r\n" + 
		        		"CCleaner=https://www.chip.de/downloads/CCleaner_16317939.html\r\n" + 
		        		"Notepad++=https://www.chip.de/downloads/Notepad_12996935.html\r\n" + 
		        		"PDFCreator=https://www.chip.de/downloads/PDFCreator_13009777.html\r\n" + 
		        		"PDF24=https://www.chip.de/downloads/PDF24-Creator_43805654.html\r\n" + 
		        		"VLC Media Player=https://www.chip.de/downloads/VLC-player-64-Bit_53513913.html\r\n" + 
		        		"7-Zip=https://www.chip.de/downloads/7-Zip-64-Bit_38851222.html\r\n" + 
		        		"IrfanView=https://www.chip.de/downloads/IrfanView-64-Bit_81722226.html\r\n" + 
		        		"Adobe Acrobat Reader DC=https://www.chip.de/downloads/Adobe-Acrobat-Reader-DC_12998358.html\r\n" + 
		        		"Adobe Flash Player=https://www.chip.de/downloads/Adobe-Flash-Player_13003561.html\r\n" + 
		        		"\r\n" + 
		        		"[versionen]\r\n" + 
		        		"CCleaner=5.56\r\n" + 
		        		"Notepad++=7.6.6");
		        w.close();
		        System.out.println("[existsFile] Es wurde keine Datei gefunden - Datei wird erstellt");
		    	new Load();		    	
		      } catch (java.io.IOException e) {
		        e.printStackTrace();
		      }
	  }

}
