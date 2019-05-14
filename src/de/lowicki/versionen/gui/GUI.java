package de.lowicki.versionen.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import de.lowicki.versionen.main.Main;

public class GUI extends JFrame {
	
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
        Main.versionen.forEach((p, v) -> {
            Label row = new Label(String.valueOf(p) + " - " + v);
            row.setBackground(Color.gray);
            pan.add(row);
        });
        this.getContentPane().add(scrollPane);
        this.add("North", pan);
        this.setSize(600, 600);
        this.setBackground(Color.blue);
        this.setVisible(true);
    }

}
