package de.lowicki.versionen.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

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

import de.lowicki.versionen.link.Versionen;
import de.lowicki.versionen.main.Main;

public class GUI extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	JFrame frame;
    private JLabel label; 
    private JButton lfButton; 
    private JTextArea area; 
    private JScrollPane pane; 
    private JMenuItem exit;
    private JMenuItem pfad;
    int i = 0;
    private String update = "";
    
    private HashMap<String, String> aktualisieren = new HashMap<String, String>();
	
    public GUI() {
    	frame = new JFrame("Versionen");
    	
    	frame.setSize(1000, 600);
        frame.setTitle("Lade Daten");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
    private void closeFrame(JFrame frame) {
    	frame.dispose();
    }
    
    public void initComponents() {
    	Font content = new Font("Sans-Serif", Font.CENTER_BASELINE, 15);
        JMenuBar menuBar = new JMenuBar(); 
        JMenu menu = new JMenu("Menu");
        
        if(!update.isEmpty()) {
        	frame.setTitle("CHIP-Versionen neu geladen am " + update + " Uhr");
        } else {
        	frame.setTitle("CHIP-Versionen geladen am " + getDate() + " Uhr");
        }  

        lfButton = new JButton("Aktualisieren"); 
        
        lfButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Versionen();
				System.out.println("Neue Versionen");
				//closeFrame(frame);
				update = getDate();
				initComponents();
			}
		}); 
        
        label = new JLabel(); 
        area = new JTextArea();
        area.setLineWrap(false);
        area.setFont(content);
        
        
        
        

        
        // Für jedes Chip Programm Name == P und Version == v
        Main.chipVersionen.forEach((p, v) -> {
        	
        	// Wenn die Version in acmpVersion drinnen ist bedeutet es, dass es keine neue Version gibt
        	if(Main.acmpVersionen.containsValue(v)) {
                area.insert(" \r\n [" + String.valueOf(p) + "] ACMP-Version " + v + " stimmt mit Chip überein", i);
                i++;
        	} else {
                aktualisieren.put(String.valueOf(p), v);
        	}
        	
        });
        
        area.insert("\r\n----------------------------------------------------------", i);
        
        Main.chipVersionen.forEach((k,v)->{
        	if(aktualisieren.containsValue(v)) {
        		String acmpVer =  Main.acmpVersionen.get(k);
        		area.insert("\r\n [" + String.valueOf(k) + "] Versionen stimmen nicht überein ACMP-Version: " + acmpVer + " CHIP-Version: " + v, i);
        		i++;
        	}
        	
        });
        
        
        exit = new JMenuItem("exit"); 
        exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeFrame(frame);
			}
		});
        
        pfad = new JMenuItem("Config öffnen");
        pfad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ProcessBuilder pb = new ProcessBuilder("Notepad.exe", Main.path);
				try {
					pb.start();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
        

        pane = new JScrollPane(area); 
        

        menu.add(pfad);
        menu.add(exit); 
        menuBar.add(menu); 
        
        JPanel buttPanel = new JPanel(new FlowLayout()); 
        buttPanel.add(lfButton); 

        JPanel mainPanel = new JPanel(new BorderLayout()); 
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30)); 

        mainPanel.add(label, BorderLayout.NORTH); 
        mainPanel.add(pane, BorderLayout.CENTER); 
        mainPanel.add(buttPanel, BorderLayout.SOUTH); 

        frame.add(menuBar, BorderLayout.NORTH); 
        frame.add(mainPanel, BorderLayout.CENTER); 
        frame.setLocationRelativeTo(null); 
        frame.invalidate();
        frame.validate();
        frame.repaint();
        i=0;
    } 
    
    private String getDate() {
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); 
	    LocalDateTime myDateObj = LocalDateTime.now(); 
	    String formattedDate = myDateObj.format(myFormatObj); 
	    
		return formattedDate;
    }
    
    /*private void editConfigPfad() {
    	JFrame fenster = new JFrame("Pfad zur Config");
    	fenster.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	fenster.setSize(650, 350);
    	fenster.setResizable(false);
    	
    	JTextArea textfield = new JTextArea(5, 5);
    	
    	fenster.add(textfield);
    	fenster.setVisible(true);
    }*/
 

}
