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

import de.lowicki.versionen.main.Main;
import de.lowicki.versionen.main.State;

public class StartGUI {
	
	JFrame frame;
    private JTextArea area; 
    private JScrollPane pane; 
    
    private JPanel auswahl;
	
	public void StartGUI() {
		
		if(!(Main.getState() == State.AUSWAHL)) {
			Main.setState(State.AUSWAHL);
			System.out.println("State zu AUSWAHL geändert");
		}
		
    	frame = new JFrame("Versionen");
    	
    	frame.setSize(500, 200);
        frame.setTitle("Lade Daten");

        JPanel buttPanel = new JPanel(new FlowLayout());
        auswahl = new JPanel(new BorderLayout());
        Font content = new Font("Sans-Serif", Font.CENTER_BASELINE, 15);

        
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
					GUI x = new GUI();
					x.initComponents();
					new CloseFrame(frame);
					Main.setState(State.MAIN);
				}
			}
		});
        
        area = new JTextArea();
        area.setLineWrap(false);
        area.setFont(content);
        
        area.insert("Lade Daten...\r\n", 0);
        
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
	public void add(String str) {
		area.insert(str, area.getText().length());
		area.update(area.getGraphics());
		
	}
}
