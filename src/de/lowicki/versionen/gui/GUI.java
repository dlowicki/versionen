package de.lowicki.versionen.gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
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

import de.lowicki.versionen.link.Versionen;
import de.lowicki.versionen.main.Main;
import de.lowicki.versionen.manager.Load;
import de.lowicki.versionen.manager.CreateFile;

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
    private JMenuItem openDir;
    private JMenuItem startDownloads;
    
    private JPanel main;
    int i = 0;
    private String update = "";
    	
    public GUI() {
    	this.frame = new JFrame("Versionen");
    	
    	this.frame.setSize(1000,600);
    	this.frame.setTitle("Lade Daten");
        
    	this.frame.setLocationRelativeTo(null); 
    	this.frame.invalidate();
    	this.frame.validate();
    	this.frame.repaint();
    	this.frame.setVisible(true);
    	this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void initComponents() {
    	
    	Font content = new Font("Sans-Serif", Font.CENTER_BASELINE, 15);
        JMenuBar menuBar = new JMenuBar(); 
        JMenu menu = new JMenu("Menu"); 
        JMenu menuConfig = new JMenu("Config");
        JMenu menuDownloads = new JMenu("Downloads");
        this.label = new JLabel(); 
        this.area = new JTextArea();
        this.lfButton = new JButton("Aktualisieren"); 
        
        this.pathConfig = new JMenuItem("Config erstellen");
        this.openConfig = new JMenuItem("Config öffnen");
        this.pathDownloads = new JMenuItem("Downloads erstellen");
        this.openDownloads = new JMenuItem("Downloads öffnen");
        this.openDir = new JMenuItem("Ordner öffnen");
        this.startDownloads = new JMenuItem("Download starten");
        
        this.exit = new JMenuItem("Beenden");
        JPanel buttPanel = new JPanel(new FlowLayout());
        this.main = new JPanel(new BorderLayout());
        
        
        if(!this.update.isEmpty()) {
        	this.frame.setTitle("CHIP-Versionen neu geladen am " + update + " Uhr");
        } else {
        	this.frame.setTitle("CHIP-Versionen geladen am " + Main.getDate() + " Uhr");
        }  

        
        
        this.lfButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateClient();
			}
		}); 
        
        this.area.setLineWrap(false);
        this.area.setFont(content);
        
        if(Main.configStatus == false) {
        	this.area.insert("\r\nConfig wurde nicht gefunden! \r\nConfig --> Config erstellen und aktualisieren", i);
        } else {
            // Für jedes Chip Programm Name == P und Version == v
            Main.chipVersionen.forEach((p, v) -> {
            	// Wenn die Version in acmpVersion drinnen ist bedeutet es, dass es keine neue Version gibt
            	if(Main.acmpVersionen.containsValue(v)) {
            		this.area.insert(" \r\n [" + String.valueOf(p) + "] ACMP-Version " + v + " stimmt mit Chip überein", i);
                    i++;
            	} else {
                    Main.aktualisieren.put(String.valueOf(p), v);
            	}
            	
            });
            this.area.insert("\r\n-------------------------------------------------------------------", i);            
            Main.chipVersionen.forEach((k,v)->{
            	if(Main.aktualisieren.containsValue(v)) {
            		String acmpVer =  Main.acmpVersionen.get(k);
            		this.area.insert("\r\n [" + String.valueOf(k) + "] Versionen stimmen nicht überein ACMP-Version: " + acmpVer + " CHIP-Version: " + v, i);
            		i++;
            	}
            	
            });
            
        }
        
        
        this.exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CloseFrame(frame);
			}
		});
        
        
        this.pathConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CreateFile(Paths.get("C:\\ProgramData\\Chip Versionen\\config.ini"), "Config");
			}
		});
        
        this.pathDownloads.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CreateFile(Paths.get("C:\\ProgramData\\Chip Versionen\\downloads.ini"), "Downloads");
			}
		});
        
        
        this.openConfig.addActionListener(new ActionListener() {
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
        
        this.openDir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().open(new File(Main.pathDir.toString()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
        
        this.openDownloads.addActionListener(new ActionListener() {
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
        
        this.startDownloads.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				  downloadGUI d = new downloadGUI();
				  d.downloadGUI();
			}
		});
        

        this.pane = new JScrollPane(area); 
        
        menuConfig.add(this.openConfig);
        menuConfig.add(this.pathConfig);
        
        
        
        if(checkFileExists(Main.pathDownloads))
        	menuDownloads.add(this.openDownloads);
        menuDownloads.add(this.openDir);
        menuDownloads.add(this.pathDownloads);
        menuDownloads.add(this.startDownloads);
        
        
        menu.add(this.exit); 
        
        menuBar.add(menu);
        menuBar.add(menuConfig); 
        menuBar.add(menuDownloads); 
         
        buttPanel.add(this.lfButton); 

         
        this.main.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30)); 

        this.main.add(this.label, BorderLayout.NORTH); 
        this.main.add(this.pane, BorderLayout.CENTER); 
        this.main.add(buttPanel, BorderLayout.SOUTH); 
        
        
        this.frame.add(menuBar, BorderLayout.NORTH); 
        this.frame.add(this.main, BorderLayout.CENTER);
        this.frame.setLocationRelativeTo(null); 
        this.frame.invalidate();
        this.frame.validate();
        this.frame.repaint();
        // Die Zeilen zurücksetzen
        i=0;
    } 
    

    private void updateClient() {
		new CloseFrame(frame);		
		GUI x = new GUI();
		new Load();
		//new Connect_acmp();
		new Versionen();
		//new Compare(Main.acmpVersionen, Main.chipVersionen);
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
