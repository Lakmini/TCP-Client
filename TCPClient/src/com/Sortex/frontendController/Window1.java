package com.Sortex.frontendController;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UIManager.*;

public class Window1 {

	JFrame frame;
	Window2 window2;
	JPanel panel;
	JPanel panel2;
	JPanel panel3;
	JPanel container;
	JButton backButton;
	Window3 window3;
	JPanel panel4;
	JTabbedPane jtp;

	public void init() {
		frame = new JFrame("Sorter");
		frame.setResizable(false);
		jtp = new JTabbedPane();
		frame.getContentPane().add(jtp);
		window2 = new Window2();
		panel = new JPanel();
		panel.setBackground(Color.white);
		container = new JPanel();
		panel3 = new JPanel();
		panel3.setBackground(Color.white);
		window3 = new Window3();
	}

	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}
		Window1 window = new Window1();
		window.init();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				window.makeUI();
			}
		});
	}

	public void makeUI() {
		panel2 = window2.init();
		panel4 = window3.createTestPanel();
		// Setting the width and height of frame
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jtp.addTab("Auto Thresholding", panel4);
		jtp.addTab("Manual Thresholding", panel2);
		frame.setVisible(true);
	}

}
