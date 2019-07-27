package de.lowicki.versionen.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import de.lowicki.versionen.download.Download;
import de.lowicki.versionen.main.Main;
import de.lowicki.versionen.main.State;

public class downloadGUI {
	
	JFrame frame;
    private JTextArea area; 
    private JScrollPane pane; 
    
    private JPanel auswahl;
	
	public void downloadGUI() {
		
		if(!(Main.getState() == State.DOWNLOADING)) {
			Main.setState(State.DOWNLOADING);
			System.out.println("State zu Downloading geändert");
		}
		
    	frame = new JFrame("Downloading");
    	
    	frame.setSize(500, 200);
        frame.setTitle("Download starten");

        JPanel buttPanel = new JPanel(new FlowLayout());
        auswahl = new JPanel(new BorderLayout());
        Font content = new Font("Sans-Serif", Font.CENTER_BASELINE, 15);

        
        JButton dbButton = new JButton("Starten");
        JButton cfButton = new JButton("Beenden"); 
        
        buttPanel.add(dbButton);
        buttPanel.add(cfButton); 
        
        dbButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(Main.getDate() + " Download gestartet\r\n");
				Download d = new Download();
				d.downloadFile();
			}
		});
        
        cfButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Main.downloadStop == false) {
					Main.downloadStop = true;
					add(Main.getDate() + " Download wird beendet\r\n");
					Main.setState(State.MAIN);
				}
			}
		});
        
        area = new JTextArea();
        area.setLineWrap(false);
        area.setFont(content);
        
        area.insert("Downloade Dateien \r\n", 0);
        
        pane = new JScrollPane(area); 
        auswahl.add(pane, BorderLayout.CENTER); 
        auswahl.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30)); 
        auswahl.add(buttPanel, BorderLayout.SOUTH);
        frame.add(auswahl);
        
        frame.setLocationRelativeTo(null); 
        frame.invalidate();
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
	}
	
	public void add(String str) {
		area.insert(str, area.getText().length());
		area.update(area.getGraphics());
		
	}

}
