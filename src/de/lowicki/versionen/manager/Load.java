package de.lowicki.versionen.manager;

import java.io.File;

import org.ini4j.Profile.Section;
import org.ini4j.Wini;

import de.lowicki.versionen.main.Main;

public class Load {

	/*
	 * Hier werden die Daten aus der config.ini Datei in verschiedene Arrays und
	 * HashMaps gepackt
	 */

	public Load(String pl) {
		try {
			new ExistsFile(Main.pathConfig);
			new ExistsFile(Main.pathDownloads);
			Wini ini = new Wini(new File(Main.pathConfig.toString()));

			String dec = decrypt(ini.get("ACMP", "connection"));
			Section mysqlData = ini.get("MYSQL");
			if (dec != null && mysqlData != null) {
				Main.connectionURL = dec;

				/*
				 * Main.mysql.add(decrypt(ini.get("MYSQL", "host")));
				 * Main.mysql.add(ini.get("MYSQL", "port"));
				 * Main.mysql.add(decrypt(ini.get("MYSQL", "database")));
				 * Main.mysql.add(decrypt(ini.get("MYSQL", "user")));
				 * Main.mysql.add(decrypt(ini.get("MYSQL", "pass")));
				 */

				Main.mysql.add(ini.get("MYSQL", "host"));
				Main.mysql.add(ini.get("MYSQL", "port"));
				Main.mysql.add(ini.get("MYSQL", "database"));
				Main.mysql.add(ini.get("MYSQL", "user"));
				Main.mysql.add(ini.get("MYSQL", "pass"));
				
				if (pl.equalsIgnoreCase("config")) {
					Main.urls.putAll((ini.get("url")));
					Main.downloads.putAll((ini.get("download")));
					System.out.println("URL und DOWNLOAD geladen");
				}

				System.out.println("[Load] Die ConnectionURL, die URLS und die Downloads wurden geladen");
				return;
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