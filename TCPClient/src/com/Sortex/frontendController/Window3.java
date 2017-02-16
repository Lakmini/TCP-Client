package com.Sortex.frontendController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Window3 {
	JPanel container;

	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	JPanel panel4;
	JPanel panel5;
	JButton startButton;
	JLabel label4;
	JLabel imgLabel;
	JButton resetButton;
	int numberOfFrames;
	JButton autoThresholdingButton;
	// parameters
	int redValue;
	int greenValue;
	int blueValue;
	String type;
	String category;

	public JPanel createTestPanel() {
		container = new JPanel();
		container.setLayout(new GridBagLayout());
		container.setBackground(Color.white);
		/***************************
		 * Panel 1
		 **************************************/
		panel1 = new JPanel(new BorderLayout());
		panel1.setBackground(Color.white);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel1.setBorder(BorderFactory.createEtchedBorder());
		TitledBorder border = new TitledBorder("Select Type");
		border.setTitleJustification(TitledBorder.LEFT);
		border.setTitlePosition(TitledBorder.TOP);
		panel1.setBorder(border);
		// add radio buttons
		JRadioButton stem = new JRadioButton("Stem");
		JRadioButton leaf = new JRadioButton("Leaf");
		ButtonGroup bG = new ButtonGroup();
		bG.add(stem);
		bG.add(leaf);
		panel1.add(stem);
		panel1.add(leaf);
		// read type of tea particles
		stem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				type = stem.getText();
				leaf.setEnabled(false);

			}
		});

		leaf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				type = leaf.getText();
				stem.setEnabled(false);

			}
		});

		/**********************************
		 * Panel 2
		 ***********************************/
		panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2.setBackground(Color.white);
		panel2.setBorder(BorderFactory.createEtchedBorder());
		TitledBorder border2 = new TitledBorder("Select Tea Category");
		border2.setTitleJustification(TitledBorder.LEFT);
		border2.setTitlePosition(TitledBorder.TOP);
		panel2.setBorder(border2);
		// add radio buttons
		JRadioButton cat1 = new JRadioButton("OPA 1");
		JRadioButton cat2 = new JRadioButton("OPA 2");
		ButtonGroup bG2 = new ButtonGroup();
		bG2.add(cat1);
		bG2.add(cat2);
		panel2.add(cat1);
		panel2.add(cat2);
		// read selected tea category
		cat1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				category = cat1.getText();
				cat2.setEnabled(false);
			}
		});

		cat2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				category = cat2.getText();
				cat1.setEnabled(false);
			}
		});
		/****************************************** panel3 ******************/
		panel3 = new JPanel(new BorderLayout(10, 10));
		panel3.setBackground(Color.white);

		startButton = new JButton("Start");
		resetButton = new JButton("Reset");
		final JTextField userText = new JTextField();
		userText.setPreferredSize(new Dimension(60, 25));
		JLabel noOfFrames = new JLabel("Number of Frames :");
		userText.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (userText.getText().length() >= 3) {
					e.consume();
					String temp = userText.getText();
					numberOfFrames = Integer.parseInt(temp);
					System.out.println("Number of Frames : " + numberOfFrames);
				}
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				

			}

		});
		;

		JPanel inputLabels = new JPanel();
		inputLabels.setBackground(Color.WHITE);
		JPanel inputField = new JPanel();
		inputField.setBackground(Color.WHITE);
		JPanel controlinputs = new JPanel(new BorderLayout());
		JPanel controls = new JPanel();
		controls.setBackground(Color.WHITE);
		controls.add(startButton);
		controls.add(resetButton);
		inputLabels.add(noOfFrames);
		inputField.add(userText);
		controlinputs.add(inputLabels, BorderLayout.WEST);
		controlinputs.add(inputField, BorderLayout.CENTER);
		panel3.add(controlinputs, BorderLayout.WEST);

		// reset button functions
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bG.clearSelection();
				bG2.clearSelection();
				stem.setEnabled(true);
				leaf.setEnabled(true);
				cat1.setEnabled(true);
				cat2.setEnabled(true);
				panel5.remove(imgLabel);
				panel5.validate();
				panel5.repaint();
				userText.setText(" ");

				type = null;
				category = null;

			}
		});

		/*******************************************
		 * panel 4
		 ***************************************/
		panel4 = new JPanel(new BorderLayout());
		panel4.setBackground(Color.white);
		ImageIcon loadingIcon = new ImageIcon(this.getClass().getResource("/com/Sortex/images/loader1.gif"));
		Image image = loadingIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(600, 20, Image.SCALE_FAST); 
		loadingIcon = new ImageIcon(newimg); // transform it back
		imgLabel = new JLabel(loadingIcon);
		panel4.add(controls, BorderLayout.CENTER);

		/******************** panel 5 ***************************************/
		panel5 = new JPanel(new BorderLayout());
		panel5.setBackground(Color.white);
		ImageIcon logoIcon = new ImageIcon(this.getClass().getResource("/com/Sortex/images/SORTEX.png"));
		Image image2 = logoIcon.getImage(); // transform it
		Image newimg2 = image2.getScaledInstance(150, 100, Image.SCALE_FAST);
		logoIcon = new ImageIcon(newimg2);
		JLabel logo = new JLabel(logoIcon);
		panel5.add(logo, BorderLayout.EAST);
		/************************** temp panel ********************/
		JPanel tempPanel = new JPanel();
		tempPanel.setBackground(Color.WHITE);

		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// send parameters type category , number of frames
				panel5.add(imgLabel, BorderLayout.PAGE_START);
				panel5.validate();
				panel5.repaint();

			}
		});

		container.add(panel1, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		container.add(panel2, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		container.add(panel3, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 5, 5));
		container.add(panel4, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		container.add(panel5, new GridBagConstraints(0, 3, 2, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		return container;
	}

}
