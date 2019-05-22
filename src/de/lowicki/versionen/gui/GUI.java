package de.lowicki.versionen.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.lowicki.versionen.link.Versionen;
import de.lowicki.versionen.main.Main;

public class GUI extends JFrame implements ActionListener {
	
	
	JFrame frame;
    private JLabel label; 
    private JButton lfButton; 
    private JTextArea area; 
    private JScrollPane pane; 
    private JMenuItem exit;
    private int lAF = 1; 
	
    public GUI() {
    	frame = new JFrame("Versionen");
    	Font content = new Font("Sans-Serif", Font.CENTER_BASELINE, 20);
    	
    	createHeadline(frame);
    	initComponents();
        
        Label lab2 = new Label("");
        TextField machineName = new TextField(200);
        Label lab = new Label("");
        Panel pan = new Panel();
        JScrollPane scrollPane = new JScrollPane();
        
        
        pan.add(lab2);
        pan.add(machineName);
        pan.add(lab);
        
        pan.setLayout(new GridLayout(14, 10));
        pan.setBackground(Color.DARK_GRAY);
        
        // Für jedes Chip Programm Name == P und Version == v
        Main.chipVersionen.forEach((p, v) -> {
        	
        	// Wenn die Version in acmpVersion drinnen ist bedeutet es, dass es keine neue Version gibt
        	if(Main.acmpVersionen.containsValue(v)) {
        		Label row = new Label(String.valueOf(p) + " Version " + v + " stimmt mit Chip überein");
                row.setBackground(Color.gray);
                row.setFont(content);
                row.setBounds(100, 100, 100, 100);
                pan.add(row);
        	} else {
        		String acmpVer = Main.acmpVersionen.get(p);
        		Label row = new Label("[" + String.valueOf(p) + "] ACMP-Version: " + acmpVer + " CHIP-Version: " + v);
                row.setBackground(Color.CYAN);
                row.setFont(content);
                pan.add(row);
        	}
        	
        });
        
        JButton button = new JButton("Aktualisieren");
        button.setBounds(50, 50, 100, 50);
        button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Versionen();
				System.out.println("Neue Versionen");
				closeFrame(frame);
				new GUI();
			}
		});

        
        pan.add(button);
        
        //frame.getContentPane().add(scrollPane);
        //frame.add("North", pan);
        frame.setSize(400, 400);
        frame.setTitle("CHIP-Versionen");
        frame.setBackground(Color.blue);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void closeFrame(JFrame frame) {
    	frame.dispose();
    }
    
    private void initComponents() {
        JMenuBar menuBar = new JMenuBar(); 
        JMenu menu = new JMenu("Datei"); 
        exit = new JMenuItem("exit"); 
        exit.addActionListener(this); 
        menu.add(exit); 
        menuBar.add(menu); 
        lfButton = new JButton("L & F"); 
        lfButton.addActionListener(this); 
        label = new JLabel(); 
        area = new JTextArea();
        pane = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 

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
    } 
    
    private void createHeadline(JFrame frame) {
    	JPanel pan = new JPanel();
    	Font headline = new Font("Sans-Serif", Font.CENTER_BASELINE, 25);
    	Label label = new Label("Chip.de Versionen");
    	label.setBackground(Color.LIGHT_GRAY);
        label.setFont(headline);
        pan.add(label);
        frame.add(pan);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
	} 

}
