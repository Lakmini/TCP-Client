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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UIManager.*;

public class Window1 extends JFrame {

	JFrame frame;
	Window2 window2;
	JPanel panel;
	JPanel panel2;
	JPanel panel3;
	JPanel container;
	JButton backButton;
	Window3 window3;
	JPanel panel4;

	public void init() {
		frame = new JFrame("Sorter");
		frame.setResizable(false);
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

		// Setting the width and height of frame
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// adding panel to frame
		frame.add(container);
		/*
		 * calling user defined method for adding components to the panel.
		 */
		placeComponents();

		// Setting the frame visibility to true
		frame.setVisible(true);
	}

	public void placeComponents() {

		container.setLayout(new GridBagLayout());
		// Creating login button
		panel.setLayout(new GridBagLayout());
		panel.setBackground(Color.white);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(6, 6, 6, 6);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		JButton trainButton = new JButton("Train");
		trainButton.setBounds(50, 80, 200, 200);
		panel.add(trainButton);
		JButton testButton = new JButton("Test");
		testButton.setBounds(300, 80, 200, 200);
		panel.add(testButton);
		backButton = new JButton("Back");
		panel3.add(backButton);
		container.add(panel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		trainButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				container.remove(panel);
				panel2 = window2.init();
				container.add(panel2, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.8, GridBagConstraints.WEST,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				container.add(panel3, new GridBagConstraints(0, 1, 3, 1, 1.0, 0.2, GridBagConstraints.WEST,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				// container.add(panel2);
				// container.add(panel3);
				container.revalidate();
				container.repaint();
			}
		});

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				container.removeAll();
				container.add(panel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1, GridBagConstraints.WEST,
						GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
				container.revalidate();
				container.repaint();

			}
		});

		panel4 = window3.createTestPanel();
		testButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				container.remove(panel);
				container.add(panel4, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.8, GridBagConstraints.WEST,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				container.add(panel3, new GridBagConstraints(0, 1, 3, 1, 1.0, 0.2, GridBagConstraints.WEST,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				container.revalidate();
				container.repaint();

			}
		});

	}

}
