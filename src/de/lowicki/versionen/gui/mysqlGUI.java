package de.lowicki.versionen.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;

public class mysqlGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8887034994866502124L;
	private JPanel contentPane;
	private JTable table;
	private JFrame frame;
	
	public mysqlGUI() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(900, 550);
		
		setTitle("Datenbank");
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 975, 510);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel connection = new Panel();
		connection.setBackground(Color.GRAY);
		connection.setBounds(10, 10, 435, 42);
		contentPane.add(connection);
		connection.setLayout(null);
		
		JTextPane txtpnVerbindungIstAktiv = new JTextPane();
		txtpnVerbindungIstAktiv.setFont(new Font("Arial", Font.PLAIN, 18));
		txtpnVerbindungIstAktiv.setText("Verbindung ist aktiv");
		txtpnVerbindungIstAktiv.setEditable(false);
		txtpnVerbindungIstAktiv.setBackground(Color.LIGHT_GRAY);
		txtpnVerbindungIstAktiv.setBounds(0, 0, 435, 31);
		connection.add(txtpnVerbindungIstAktiv);
		
		Panel message = new Panel();
		message.setBackground(Color.ORANGE);
		message.setBounds(300, 300, 135, 42);
		contentPane.add(message);
		message.setLayout(null);
		
		JTextPane txtpnMessage = new JTextPane();
		txtpnMessage.setFont(new Font("Arial", Font.PLAIN, 18));
		txtpnMessage.setText("Error Message");
		txtpnMessage.setEditable(false);
		txtpnMessage.setBackground(Color.LIGHT_GRAY);
		txtpnMessage.setBounds(0, 0, 435, 31);
		message.add(txtpnMessage); 
		
		ScrollPane data = new ScrollPane();
		data.setBackground(new Color(112, 128, 144));
		data.setForeground(Color.DARK_GRAY);
		data.setFont(new Font("Arial", Font.PLAIN, 12));
		data.setBounds(480, 91, 452, 370);
		contentPane.add(data);	
		
		frame.add(data);
		frame.add(connection);
		frame.add(message);
		
		frame.setLocationRelativeTo(null); 
        frame.invalidate();
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
