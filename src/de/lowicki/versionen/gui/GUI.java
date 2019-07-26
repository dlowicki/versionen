package de.lowicki.versionen.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import de.lowicki.versionen.database.Connect_acmp;
import de.lowicki.versionen.link.Compare;
import de.lowicki.versionen.link.Versionen;
import de.lowicki.versionen.main.Main;
import de.lowicki.versionen.manager.Load;
import de.lowicki.versionen.manager.CreateFile;
import de.lowicki.versionen.manager.ExistsFile;

public class GUI extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	JFrame frame;
    private JLabel label; 
    private JButton lfButton; 
    private JTextArea area; 
    private JScrollPane pane; 
    private JMenuItem exit;
    
    private JMenuItem pathConfig;
    private JMenuItem openConfig;
    private JMenuItem pathDownloads;
    private JMenuItem openDownloads;
    
    private JPanel auswahl;
    private JPanel main;
    int i = 0;
    private String update = "";
    	
    public GUI() {
    	frame = new JFrame("Versionen");
    	
    	frame.setSize(500, 200);
        frame.setTitle("Lade Daten");
        
        loadAuswahl(frame);
        
        frame.setLocationRelativeTo(null); 
        frame.invalidate();
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void closeFrame(JFrame frame) {
    	frame.dispose();
    }
    
    private void loadAuswahl(JFrame fr) {
        JPanel buttPanel = new JPanel(new FlowLayout());
        auswahl = new JPanel(new BorderLayout());
        
        JButton dbButton = new JButton("Datenbank");
        JButton cfButton = new JButton("Config"); 
        
        buttPanel.add(dbButton);
        buttPanel.add(cfButton); 
        
        dbButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
        
        cfButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Main.configReady == true) {
					initComponents();
				}
			}
		});

        
        auswahl.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30)); 
        auswahl.add(buttPanel, BorderLayout.SOUTH);
        fr.add(auswahl);
    }
    
    public void initComponents() {
    	frame.remove(auswahl);
    	frame.setSize(1000,600);
    	Font content = new Font("Sans-Serif", Font.CENTER_BASELINE, 15);
        JMenuBar menuBar = new JMenuBar(); 
        JMenu menu = new JMenu("Menu"); 
        JMenu menuConfig = new JMenu("Config");
        JMenu menuDownloads = new JMenu("Downloads");
        label = new JLabel(); 
        area = new JTextArea();
        lfButton = new JButton("Aktualisieren"); 
        
        pathConfig = new JMenuItem("Config öffnen");
        openConfig = new JMenuItem("Config erstellen");
        pathDownloads = new JMenuItem("Downloads erstellen");
        openDownloads = new JMenuItem("Downloads öffnen");
        
        exit = new JMenuItem("Beenden");
        JPanel buttPanel = new JPanel(new FlowLayout());
        main = new JPanel(new BorderLayout());
        
        
        if(!update.isEmpty()) {
        	frame.setTitle("CHIP-Versionen neu geladen am " + update + " Uhr");
        } else {
        	frame.setTitle("CHIP-Versionen geladen am " + getDate() + " Uhr");
        }  

        
        
        lfButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateClient();
			}
		}); 
        
        area.setLineWrap(false);
        area.setFont(content);
        
        if(Main.configStatus == false) {
        	area.insert("\r\nConfig wurde nicht gefunden! \r\nConfig --> Config erstellen und aktualisieren", i);
        } else {
            // Für jedes Chip Programm Name == P und Version == v
            Main.chipVersionen.forEach((p, v) -> {
            	// Wenn die Version in acmpVersion drinnen ist bedeutet es, dass es keine neue Version gibt
            	if(Main.acmpVersionen.containsValue(v)) {
                    area.insert(" \r\n [" + String.valueOf(p) + "] ACMP-Version " + v + " stimmt mit Chip überein", i);
                    i++;
            	} else {
                    Main.aktualisieren.put(String.valueOf(p), v);
            	}
            	
            });
            area.insert("\r\n-------------------------------------------------------------------", i);            
            Main.chipVersionen.forEach((k,v)->{
            	if(Main.aktualisieren.containsValue(v)) {
            		String acmpVer =  Main.acmpVersionen.get(k);
            		area.insert("\r\n [" + String.valueOf(k) + "] Versionen stimmen nicht überein ACMP-Version: " + acmpVer + " CHIP-Version: " + v, i);
            		i++;
            	}
            	
            });
            
        }
        

        
        
        
        exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeFrame(frame);
			}
		});
        
        
        pathConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CreateFile(Paths.get("C:\\ProgramData\\Chip Versionen\\config.ini"), "Config");
			}
		});
        
        pathDownloads.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CreateFile(Paths.get("C:\\ProgramData\\Chip Versionen\\downloads.ini"), "Downloads");
			}
		});
        
        
        openConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProcessBuilder pb = new ProcessBuilder("Notepad.exe", Main.pathConfig.toString());
				try {
					pb.start();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
        
        openDownloads.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProcessBuilder pb = new ProcessBuilder("Notepad.exe", Main.pathDownloads.toString());
				try {
					pb.start();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
        

        pane = new JScrollPane(area); 
        
        menuConfig.add(pathConfig);
        menuConfig.add(openConfig);
        
        
        if(checkFileExists(Main.pathDownloads))
        	menuDownloads.add(openDownloads);
        menuDownloads.add(pathDownloads);
        
        
        menu.add(exit); 
        
        menuBar.add(menu);
        menuBar.add(menuConfig); 
        menuBar.add(menuDownloads); 
         
        buttPanel.add(lfButton); 

         
        main.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30)); 

        main.add(label, BorderLayout.NORTH); 
        main.add(pane, BorderLayout.CENTER); 
        main.add(buttPanel, BorderLayout.SOUTH); 
        
        
        frame.add(menuBar, BorderLayout.NORTH); 
        frame.add(main, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null); 
        frame.invalidate();
        frame.validate();
        frame.repaint();
        // Die Zeilen zurücksetzen
        i=0;
    } 
    
    private String getDate() {
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); 
	    LocalDateTime myDateObj = LocalDateTime.now(); 
	    String formattedDate = myDateObj.format(myFormatObj); 
	    
		return formattedDate;
    }
    

    
    private void updateClient() {
		closeFrame(frame);		
		GUI x = new GUI();
		new Load();
		new Connect_acmp();
		new Versionen();
		new Compare(Main.acmpVersionen, Main.chipVersionen);
		System.out.println("[GUI] Neue Versionen werden geladen");
		x.initComponents();
		  
		System.out.println("[GUI] Daten wurden aktualisiert");
	}
    
	  private Boolean checkFileExists(Path path) {
		    File f = new File(path.toString());
		    if(f.exists()) {
			    if ((!f.isDirectory()) && (f.isFile())) {
			    	return true;
			    }
		    }
		  return false;
	  }
 

}
