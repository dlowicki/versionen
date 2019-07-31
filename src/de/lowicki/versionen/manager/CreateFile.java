package de.lowicki.versionen.manager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFile {
	
	  public CreateFile(Path path, String name) {
	      try {
	    	  File f = new File(path.toString());
	    	  
	    	  if(f.exists()) {
	    		  System.out.println("[CreateConfig] " + name + " existiert bereits");
	    		  return;
	    	  }
	    	  
	    	  Path pathToFile = Paths.get("C:\\ProgramData\\Chip Versionen\\");
	    	  Files.createDirectories(pathToFile);
	    	  
	    	  // Die config.ini Datei erstellen    	  
	    	  f.createNewFile();
	    	  
	    	  if(name.equals("Config"))
		      		editConfig(path);

		        
		        System.out.println("[existsFile] Es wurde keine Datei gefunden - Datei wird erstellt");
		    	new Load("config");    	
		      } catch (java.io.IOException e) {
		        e.printStackTrace();
		      }
	  }
	  
	  private void editConfig(Path path) {
	        try {
	        	FileWriter w = new FileWriter(path.toString());
				w.write("[ACMP]\r\n" + 
						"connection=KGJUldblcX2nV1Y504JNjvsUv3rl1yeD5S/8rq2rZSNiPmXPYe14wUFUaLUnWXKTC0p/Y+Co12R+B2Ap0ehz1J9h595GRPnMjZjFeemadBAQFcfCNNwdWzzwvqlIqmcL\r\n" + 
						"\r\n" + 
						"[MYSQL]\r\n" +
						"host=rUm6YsEcjyG3wIfJYqMTsw==\r\n" +
						"port=3306\r\n" +
						"database=Dc4MU6mH7lICjGTn0kYOdw==\r\n" + 
						"user=Wz8gvD7s9Skl8un0iNCmZg==\r\n" +
						"pass=\r\n" + 
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
						"Adobe Flash Player=https://www.chip.de/downloads/Adobe-Flash-Player_13003561.html\r\n"
						+ "\r\n"
						+ "[download]\r\n"
						+ "CCleaner=https://download.ccleaner.com/ccsetup560.exe\r\n"
						+ "7-Zip=https://www.chip.de/downloads/c1_downloads_auswahl_38851231.html?t=1564131477&v=3600&s=c1dbdfe4ff042d228be5c9ddea93f255\r\n"
						+ "Notepad++=https://notepad-plus-plus.org/repository/7.x/7.7.1/npp.7.7.1.bin.x64.zip\r\n"
						+ "IrfanView=https://get.load-balance.net/?ZhokgJcAEdgl3GR1oTHQlf31p\r\n"
						+ "VLC Media Player=https://ftp.fau.de/videolan/vlc/3.0.7.1/win64/vlc-3.0.7.1-win64.exe\r\n"
						+ "Adobe Acrobat Reader DC=ftp://ftp.adobe.com/pub/adobe/reader/win/AcrobatDC/1901220034/AcroRdrDC1901220034_de_DE.exe\r\n"
						+ "Adobe Flash Player ActiveX=https://fpdownload.macromedia.com/get/flashplayer/distyfp/current/win/install_flash_player_32_active_x.msi\r\n"
						+ "Adobe Flash Player NPAPI=https://fpdownload.macromedia.com/get/flashplayer/distyfp/current/win/install_flash_player_32_plugin.msi\r\n"
						+ "Adobe Flash Player PPAPI=https://fpdownload.macromedia.com/get/flashplayer/distyfp/current/win/install_flash_player_32_ppapi.msi\r\n"
						+ "PDF24=https://www.chip.de/downloads/c1_downloads_auswahl_43805656.html?t=1564134167&v=3600&s=b0440f0b24e316f996ebf4d94f8322f8");
				w.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	  }

}
