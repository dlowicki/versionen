package de.lowicki.versionen.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import de.lowicki.versionen.link.Versionen;
import de.lowicki.versionen.main.Main;

public class GUI extends JFrame {
	
	
	JFrame frame;
	int row = 0;
	
    public GUI() {
        Label label = new Label("Chip.de Versionen");
        label.setBackground(Color.LIGHT_GRAY);
        Label lab2 = new Label("");
        TextField machineName = new TextField(200);
        Label lab = new Label("");
        Panel pan = new Panel();
        JScrollPane scrollPane = new JScrollPane();
        pan.add(label);
        pan.add(lab2);
        pan.add(machineName);
        pan.add(lab);
        
        pan.setLayout(new GridLayout(20, 20));
        pan.setBackground(Color.DARK_GRAY);
        
        frame = new JFrame("Versionen");
        
        Main.versionen.forEach((p, v) -> {
            Label row = new Label(String.valueOf(p) + " - " + v);
            row.setBackground(Color.gray);
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

}
