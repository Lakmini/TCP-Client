package com.Sortex.frontendController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.Sortex.controller.*;

public class Window2 extends JPanel {
	// Jcomponents
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	JPanel panel4;
	JPanel panel5;
	JPanel container;
	JLabel label4;
	JLabel imgLabel;
	JButton resetButton;
	JSlider sliderR, sliderG, sliderB;
	JLabel labelRed = new JLabel("Red");
	JLabel labelGreen = new JLabel("Green");
	JLabel labelBlue = new JLabel("Blue");
	JLabel labelRedValue = new JLabel("Red: 0");
	JLabel labelGreenValue = new JLabel("Green: 0");
	JLabel labelBlueValue = new JLabel("Blue: 0");
	JButton autoThresholdingButton;
	// parameters
	int redValue;
	int greenValue;
	int blueValue;
	String type;
	String category;

	public JPanel init() {
		System.out.println("Inside Panel 2");
		sliderR = getSlider(0, 255, 0, 50, 5);
		sliderG = getSlider(0, 255, 0, 50, 5);
		sliderB = getSlider(0, 255, 0, 50, 5);
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
		panel1.setPreferredSize(new Dimension(50, 5));
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

		/**********************
		 * panel 3
		 **************************************************/
		panel3 = new JPanel(new GridBagLayout());
		panel3.setBackground(Color.white);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(6, 6, 6, 6);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.VERTICAL;

		JButton trainButton = new JButton("Manual Thresholding");
		resetButton = new JButton("Reset");
		autoThresholdingButton =new JButton("Auto Thresholding");
		//panel3.add(trainButton, new GridBagConstraints(0, 0, 1, 1, 0.5, 0.1, GridBagConstraints.WEST,
				//GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		panel3.add(resetButton, new GridBagConstraints(0, 1, 1, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		panel3.add(autoThresholdingButton, new GridBagConstraints(0, 2, 1, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		

		/*********************************** panel4 ******************/

		panel4 = new JPanel(new BorderLayout());
		panel4.setBackground(Color.white);
		panel4.setBorder(BorderFactory.createEtchedBorder());

		/************************* Panel 5 ******************************/

		panel5 = new JPanel();
		panel5.setBackground(Color.white);
		panel5.setLayout(new GridBagLayout());
		panel5.setBorder(BorderFactory.createEtchedBorder());
		TitledBorder border3 = new TitledBorder("Select R G B Values");
		border3.setTitleJustification(TitledBorder.LEFT);
		border3.setTitlePosition(TitledBorder.TOP);
		panel5.setBorder(border3);
		panel5.add(labelRed, new GridBagConstraints(0, 0, 1, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		panel5.add(sliderR, new GridBagConstraints(1, 0, 2, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));

		panel5.add(labelGreen, new GridBagConstraints(0, 1, 1, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		panel5.add(sliderG, new GridBagConstraints(1, 1, 2, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		panel5.add(labelBlue, new GridBagConstraints(0, 2, 1, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		panel5.add(sliderB, new GridBagConstraints(1, 2, 2, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));

		panel5.add(labelRedValue, new GridBagConstraints(0, 3, 1, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		panel5.add(labelGreenValue, new GridBagConstraints(1, 3, 1, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		panel5.add(labelBlueValue, new GridBagConstraints(2, 3, 1, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		/*****************************************************************************************/
		// train button functions
autoThresholdingButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				imgLabel = new JLabel(new ImageIcon(this.getClass().getResource("/com/Sortex/images/loading.gif")));
				panel4.add(imgLabel, BorderLayout.CENTER);
				
				panel4.revalidate();
				panel4.repaint();
				try {
					com.Sortex.controller.TCPClient.train();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		// reset button functions
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sliderR.setValue(0);
				sliderG.setValue(0);
				sliderB.setValue(0);
				bG.clearSelection();

				bG2.clearSelection();
				stem.setEnabled(true);
				leaf.setEnabled(true);
				cat1.setEnabled(true);
				cat2.setEnabled(true);

				type = null;
				category = null;

			}
		});

		// Here goes the interesting code
		// new GridBagConstraints(columnNumber, rowNumber, columnSpan, rowSpan,
		// columnWeigth, rowWeigth, alignment, fillType, insets, padX, pady)
		container.add(panel1, new GridBagConstraints(0, 0, 1, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		container.add(panel2, new GridBagConstraints(1, 0, 1, 1, 0.5, 0.2, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		container.add(panel5, new GridBagConstraints(0, 1, 3, 1, 2, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		container.add(panel3, new GridBagConstraints(2, 0, 1, 1, 0.5, 0.2, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));

		// next row
		container.add(panel4, new GridBagConstraints(0, 2, 3, 1, 0.5, 1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));

		container.setPreferredSize(new Dimension(400, 200));
		return container;

	}

	public JSlider getSlider(int min, int max, int init, int mjrTkSp, int mnrTkSp) {
		JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, init);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(mjrTkSp);
		slider.setMinorTickSpacing(mnrTkSp);
		slider.setPaintLabels(true);
		slider.addChangeListener(new SliderListener());
		return slider;
	}

	class SliderListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider slider = (JSlider) e.getSource();

			if (slider == sliderR) {
				labelRedValue.setText("Red : " + slider.getValue());
				if (!slider.getValueIsAdjusting()) {
					redValue = slider.getValue();
					System.out.println(redValue);
					try {
						com.Sortex.controller.TCPClient.sendParameters(redValue, greenValue, blueValue);
						//
					} catch (UnknownHostException e1) {
						
						e1.printStackTrace();
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
					
				}

			} else if (slider == sliderG) {
				labelGreenValue.setText("Green : " + slider.getValue());
				if (!slider.getValueIsAdjusting()) {
					greenValue = slider.getValue();
					try {
						com.Sortex.controller.TCPClient.sendParameters(redValue, greenValue, blueValue);
						//com.Sortex.controller.TCPClient.train();
					} catch (UnknownHostException e1) {
						
						e1.printStackTrace();
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
				}
				// greenValue=slider.getValue();
			} else if (slider == sliderB) {
				labelBlueValue.setText("Blue : " + slider.getValue());
				try {
					com.Sortex.controller.TCPClient.sendParameters(redValue, greenValue, blueValue);
					//com.Sortex.controller.TCPClient.train();
				} catch (UnknownHostException e1) {
					
					e1.printStackTrace();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				if (!slider.getValueIsAdjusting()) {
					blueValue = slider.getValue();
				}
				// blueValue=slider.getValue();
			}

		}

	}

}
