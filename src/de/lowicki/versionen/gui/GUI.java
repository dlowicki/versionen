package de.lowicki.versionen.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.lowicki.versionen.link.Versionen;
import de.lowicki.versionen.main.Main;

public class GUI extends JFrame {
	
	
	JFrame frame;
	int row = 0;
	
    public GUI() {
    	frame = new JFrame("Versionen");
    	
    	Font content = new Font("Sans-Serif", Font.CENTER_BASELINE, 20);
    	
    	
        createHeadline(frame);
        
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
        
        
        
        
        
        
        Main.versionen.forEach((p, v) -> {
        	Label row = new Label(String.valueOf(p) + " - " + v);
            row.setBackground(Color.gray);
            row.setFont(content);
            pan.add(row);
        });
        
        
        JButton button = new JButton("Aktualisieren");
        button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Versionen x = new Versionen();
				System.out.println("Neue Versionen");
				closeFrame(frame);
				new GUI();
			}
		});

        
        pan.add(button);
        
        frame.getContentPane().add(scrollPane);
        frame.add("North", pan);
        frame.pack();
        frame.setBackground(Color.blue);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void closeFrame(JFrame frame) {
    	frame.dispose();
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

}
