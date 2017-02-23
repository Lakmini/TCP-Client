package com.Sortex.frontendController;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Window2 {

	JPanel panel3;
	JPanel panel4;
	JPanel panel5;
	JPanel container;
	JLabel label4;
	JLabel imgLabel;
	JButton resetButton;
	JSlider sliderR, sliderG, sliderB;
	JSlider sliderRX, sliderGX, sliderBX;
	JLabel labelRed = new JLabel("Red");
	JLabel labelGreen = new JLabel("Green");
	JLabel labelBlue = new JLabel("Blue");
	JLabel labelRedX = new JLabel("Red");
	JLabel labelGreenX = new JLabel("Green");
	JLabel labelBlueX = new JLabel("Blue");
	JLabel labelRedValue = new JLabel("Red: 0");
	JLabel labelGreenValue = new JLabel("Green: 0");
	JLabel labelBlueValue = new JLabel("Blue: 0");
	JLabel labelRedValueX = new JLabel("Red: 0");
	JLabel labelGreenValueX = new JLabel("Green: 0");
	JLabel labelBlueValueX = new JLabel("Blue: 0");
	JButton autoThresholdingButton;
	JSlider sensitivitySlider;
	JLabel sensitivity = new JLabel("Sensitivity");
	// parameters
	int redValue;
	int greenValue;
	int blueValue;
	int redValueX;
	int greenValueX;
	int blueValueX;
	String type;
	String category;
	int sensitivityValue;

	public JPanel init() {

		sliderR = getSlider(0, 255, 0, 50, 5);
		sliderG = getSlider(0, 255, 0, 50, 5);
		sliderB = getSlider(0, 255, 0, 50, 5);
		sliderRX = getSlider(0, 255, 0, 50, 5);
		sliderGX = getSlider(0, 255, 0, 50, 5);
		sliderBX = getSlider(0, 255, 0, 50, 5);
		sensitivitySlider = getSlider(0, 100, 50, 10, 5);
		container = new JPanel();
		container.setLayout(new GridBagLayout());

		/**********************
		 * panel 3
		 **************************************************/
		panel3 = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(6, 6, 6, 6);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.VERTICAL;

		resetButton = new JButton("Reset");
		autoThresholdingButton = new JButton("Auto Thresholding");
		//panel3.add(resetButton);
		panel3.add(sensitivity, new GridBagConstraints(0, 0, 1, 1, 0.2,0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		panel3.add(sensitivitySlider, new GridBagConstraints(1, 0, 2, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		panel3.add(resetButton, new GridBagConstraints(0, 1, 1, 1, 0.2, 0.1, GridBagConstraints.WEST,
			GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		sensitivitySlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				sensitivityValue=sensitivitySlider.getValue();
				try {
					com.Sortex.controller.TCPClient.sendSensitivityParams(sensitivityValue);
				} catch (UnknownHostException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			}
		});
		
		/*********************************** panel4 ******************/

		panel4 = new JPanel();
		panel4.setLayout(new GridBagLayout());
		panel4.setBorder(BorderFactory.createEtchedBorder());
		TitledBorder border4 = new TitledBorder("Stem/Leaf Thresholding");
		border4.setTitleJustification(TitledBorder.LEFT);
		border4.setTitlePosition(TitledBorder.TOP);
		panel4.setBorder(border4);
		panel4.add(labelRedX, new GridBagConstraints(0, 0, 1, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		panel4.add(sliderRX, new GridBagConstraints(1, 0, 2, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));

		panel4.add(labelGreenX, new GridBagConstraints(0, 1, 1, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		panel4.add(sliderGX, new GridBagConstraints(1, 1, 2, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		panel4.add(labelBlueX, new GridBagConstraints(0, 2, 1, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		panel4.add(sliderBX, new GridBagConstraints(1, 2, 2, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));

		panel4.add(labelRedValueX, new GridBagConstraints(0, 3, 1, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		panel4.add(labelGreenValueX, new GridBagConstraints(1, 3, 1, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		panel4.add(labelBlueValueX, new GridBagConstraints(2, 3, 1, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));

		/************************* Panel 5 ******************************/

		panel5 = new JPanel();
		panel5.setLayout(new GridBagLayout());
		panel5.setBorder(BorderFactory.createEtchedBorder());
		TitledBorder border3 = new TitledBorder("Background Thresholding");
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

		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sliderR.setValue(0);
				sliderG.setValue(0);
				sliderB.setValue(0);
				sliderRX.setValue(0);
				sliderGX.setValue(0);
				sliderBX.setValue(0);
				sensitivitySlider.setValue(0);
				type = null;
				category = null;

			}
		});

		// new GridBagConstraints(columnNumber, rowNumber, columnSpan, rowSpan,
		// columnWeigth, rowWeigth, alignment, fillType, insets, padX, pady)
		container.add(panel5, new GridBagConstraints(0, 1, 3, 1, 2, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		container.add(panel3, new GridBagConstraints(0, 3, 3, 1, 0.5, 0.2, GridBagConstraints.WEST,
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

				}

			} else if (slider == sliderG) {
				labelGreenValue.setText("Green : " + slider.getValue());
				greenValue = slider.getValue();

			} else if (slider == sliderB) {
				labelBlueValue.setText("Blue : " + slider.getValue());
				blueValue = slider.getValue();
			} else if (slider == sliderRX) {
				labelRedValueX.setText("Red : " + slider.getValue());
				redValueX = slider.getValue();
			} else if (slider == sliderGX) {
				labelGreenValueX.setText("Green : " + slider.getValue());
				greenValueX = slider.getValue();

			} else if (slider == sliderBX) {
				labelBlueValueX.setText("Blue : " + slider.getValue());
				blueValueX = slider.getValue();
			}

			try {
				if(slider== sliderR || slider== sliderG || slider == sliderB){
					
					com.Sortex.controller.TCPClient.sendBackgroundColorParameters(redValue, greenValue, blueValue);
					}
				
				if(slider== sliderRX || slider== sliderGX || slider == sliderBX)
				{
					com.Sortex.controller.TCPClient.sendStemLeafTresholds(redValueX, greenValueX, blueValueX);
				}

			} catch (UnknownHostException e1) {

				e1.printStackTrace();
			} catch (IOException e1) {

				e1.printStackTrace();
			}

		}

	}

}
