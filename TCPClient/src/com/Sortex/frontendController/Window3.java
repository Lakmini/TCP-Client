package com.Sortex.frontendController;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.ws.soap.AddressingFeature;

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
	JSlider sensitivitySlider;
	JLabel sensitivity = new JLabel("Sensitivity");
	JButton statistics = new JButton("Statistics");
	JButton result = new JButton("Display");

	// parameters
	int redValue;
	int greenValue;
	int blueValue;
	String type;
	String category;
	int sensitivityValue;
	int currentPosition;

	ImageIcon frameIcon;
	Image frameimage;
	Image newframeimg;

	JLabel frameLabel;

	public JPanel createTestPanel() {
		container = new JPanel();
		container.setLayout(new GridBagLayout());
		sensitivity.setFont(new Font("Arial", Font.BOLD, 25));
		/***************************
		 * Panel 1
		 **************************************/
		panel1 = new JPanel(new BorderLayout());
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel1.setBorder(BorderFactory.createEtchedBorder());
		TitledBorder border = new TitledBorder("Select Type");
		 border.setTitleFont(new Font("Arial", Font.ITALIC, 25));
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
		panel2.setBorder(BorderFactory.createEtchedBorder());
		TitledBorder border2 = new TitledBorder("Select Tea Category");
		border2.setTitleFont(new Font("Arial", Font.ITALIC, 25));
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
		startButton = new JButton("Start");
		resetButton = new JButton("Reset");
		int[] timeStrings = { 1,2,5,10,20,30 };
		
		JComboBox timeList = new JComboBox();
		timeList.addItem(1);
		timeList.addItem(2);
		timeList.addItem(5);
		timeList.addItem(10);
		timeList.addItem(20);
		timeList.addItem(30);
		timeList.setSize(5, 10);
		JLabel noOfFrames = new JLabel("Capturing Time:");
		
		noOfFrames.setFont(new Font("Arial", Font.BOLD, 25));
		JPanel inputLabels = new JPanel();
		JPanel inputField = new JPanel();
		JPanel controlinputs = new JPanel(new BorderLayout());
		JPanel controls = new JPanel();
		controls.add(startButton);
		controls.add(resetButton);
		controls.add(statistics);
		controls.add(result);
		inputLabels.add(noOfFrames);
		inputField.add(timeList);
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
				
				sensitivitySlider.setValue(0);
				type = null;
				category = null;
				cleanDirectory("../TCPClient/stemRowData");

				cleanDirectory("../TCPClient/leafRowData");

				cleanDirectory("../TCPClient/testInStem");
				cleanDirectory("../TCPClient/testInLeaf");

			}
		});
		statistics.addActionListener(new ActionListener() {

			String path = "../TCPClient/TrainingModule/Stats";

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					display(path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		result.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String path = null;
				if (stem.isSelected()) {
					path = "../TCPClient/TrainingModule/testOutStem";
				}
				if (leaf.isSelected()) {
					path = "../TCPClient/TrainingModule/testOutStem";
				}

				try {
					display(path);
				} catch (IOException e) {

					e.printStackTrace();
				}
				// runexe(path);

			}
		});

		/*******************************************
		 * panel 4
		 ***************************************/
		panel4 = new JPanel(new BorderLayout());
		ImageIcon loadingIcon = new ImageIcon(this.getClass().getResource("/com/Sortex/images/loader1.gif"));
		Image image = loadingIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(600, 20, Image.SCALE_FAST);
		loadingIcon = new ImageIcon(newimg); // transform it back
		imgLabel = new JLabel(loadingIcon);
		panel4.add(controls, BorderLayout.CENTER);

		/******************** panel 5 ***************************************/
		panel5 = new JPanel(new BorderLayout());
		ImageIcon logoIcon = new ImageIcon(this.getClass().getResource("/com/Sortex/images/SORTEX.png"));
		Image image2 = logoIcon.getImage(); // transform it
		Image newimg2 = image2.getScaledInstance(200, 150, Image.SCALE_FAST);
		logoIcon = new ImageIcon(newimg2);
		JLabel logo = new JLabel(logoIcon);
		panel5.add(logo, BorderLayout.EAST);

		/************************** temp panel ********************/
		sensitivitySlider = getSlider(0, 100, 0, 10, 5);
		JPanel tempPanel = new JPanel();
		tempPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(6, 6, 6, 6);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.VERTICAL;

		tempPanel.add(sensitivity, new GridBagConstraints(0, 0, 1, 1, 0.2, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		tempPanel.add(sensitivitySlider, new GridBagConstraints(1, 0, 2, 1, 0.5, 0.1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		sensitivitySlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				sensitivityValue = sensitivitySlider.getValue();
				try {
					com.Sortex.controller.TCPClient.sendSensitivityParams(sensitivityValue);
				} catch (UnknownHostException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}

			}
		});

		startButton.addActionListener(new ActionListener() {
			Thread thread2;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int temp = (int) timeList.getItemAt(timeList.getSelectedIndex());
				numberOfFrames = temp;
				
				System.out.println(temp);
				com.Sortex.controller.TCPClient.NUMBER_OF_FRAMES = numberOfFrames;
				// System.out.println("Number of Frames : " + numberOfFrames);

				Thread thread = new Thread() {
					public void run() {

						panel5.add(imgLabel, BorderLayout.PAGE_START);
						panel5.validate();
						panel5.repaint();

					}
				};
				thread.start();

				if ( bG.getSelection() == null || bG2.getSelection() == null) {

					JOptionPane.showMessageDialog(null, "Please Fill all the details");
				} else {

					if (stem.isSelected()) {
						thread2 = new Thread() {
							public void run() {
								try {
									com.Sortex.controller.TCPClient.train("stemRowData");
								} catch (UnknownHostException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								BufferedReader br = null;
								String[] stringBuffer = new String[3];
								String line;
								int i = 0;
								boolean empty = true;

								if (com.Sortex.controller.TCPClient.status) {

									panel5.remove(imgLabel);
									panel5.validate();
									panel5.repaint();
									thread.interrupt();
									thread2.interrupt();
									runexe("../TCPClient/TraningModule/Sorter/for_Testing/Sorter.exe");

									try {
										br = new BufferedReader(new FileReader("../TCPClient/config.txt"));
										while (br.readLine() == null || (!isLastLine()))
											;

										while ((line = br.readLine()) != null) {
											stringBuffer[i++] = line;
										}
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}

							}
						};
						thread2.start();

					}
					if (leaf.isSelected()) {
						thread2 = new Thread() {
							public void run() {
								try {
									com.Sortex.controller.TCPClient.train("leafRowData");
								} catch (UnknownHostException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								BufferedReader br = null;
								String[] stringBuffer = new String[3];
								String line;
								int i = 0;
								boolean empty = true;

								if (com.Sortex.controller.TCPClient.status) {

									panel5.remove(imgLabel);
									panel5.validate();
									panel5.repaint();
									thread.interrupt();
									thread2.interrupt();
									runexe("../TCPClient/TraningModule/Sorter/for_Testing/Sorter.exe");

									try {
										br = new BufferedReader(new FileReader("../TCPClient/config.txt"));
										while (br.readLine() == null || (!isLastLine()))
											;

										while ((line = br.readLine()) != null) {
											stringBuffer[i++] = line;
										}
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}

							}
						};
						thread2.start();

					}
			

				}

			}
		});

		container.add(panel1, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		container.add(panel2, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		container.add(tempPanel, new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		container.add(panel3, new GridBagConstraints(0, 2, 2, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 5, 5));
		container.add(panel4, new GridBagConstraints(0, 3, 2, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		container.add(panel5, new GridBagConstraints(0, 4, 2, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		return container;
	}

	public void runexe(String commandline) {
		// String commandline =
		// "E:\\Semester8\\fyp\\Sorter\\Sorter\\for_testing\\Sorter.exe";
		try {
			String line;
			Process p = Runtime.getRuntime().exec(commandline);
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			input.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	public void cleanDirectory(String folderName) {

		final File dir = new File(folderName);
		String msg = "Folder" + folderName + " Does not exists";

		if (!dir.exists()) {
			JOptionPane.showMessageDialog(null, msg);
		}
		for (File file : dir.listFiles()) {
			if (!file.isDirectory())
				file.delete();
		}
	}

	public JSlider getSlider(int min, int max, int init, int mjrTkSp, int mnrTkSp) {
		JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, init);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(mjrTkSp);
		slider.setMinorTickSpacing(mnrTkSp);
		slider.setPaintLabels(true);
		// slider.addChangeListener(new SliderListener());
		return slider;
	}

	public boolean isLastLine() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("../TCPClient/config.txt"));

		String lastLine = "";

		String sCurrentLine;
		while ((sCurrentLine = br.readLine()) != null) {
			System.out.println(sCurrentLine);
			lastLine = sCurrentLine;
		}
		if (lastLine == "END") {
			return true;
		} else {
			return false;
		}

	}

	public void display(String folderName) throws IOException {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(6, 6, 6, 6);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.VERTICAL;

		frame.add(mainPanel);
		frame.setSize(800, 480);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JButton nextButton = new JButton("Next");
		JButton prevButton = new JButton("Previous");
		panel2.add(nextButton);
		panel2.add(prevButton);

		File[] fileList = readImages(folderName);

		String imagepath = folderName + "/" + fileList[0].getName();

		// System.out.println(imagepath);
		frameIcon = new ImageIcon(imagepath);
		frameimage = frameIcon.getImage(); // transform it
		newframeimg = frameimage.getScaledInstance(200, 150, Image.SCALE_FAST);
		frameIcon = new ImageIcon(newframeimg);
		frameLabel = new JLabel(frameIcon);
		panel1.add(frameLabel);
		currentPosition = 0;

		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				currentPosition++;
				if (currentPosition > fileList.length) {
					JOptionPane.showMessageDialog(null, "End of files");
				} else {
					panel1.remove(frameLabel);
					String imagepath = folderName + "/" + fileList[currentPosition].getName();
					System.out.println(imagepath);
					frameIcon = new ImageIcon(imagepath);
					frameimage = frameIcon.getImage(); // transform it
					newframeimg = frameimage.getScaledInstance(200, 150, Image.SCALE_FAST);
					frameIcon = new ImageIcon(newframeimg);
					frameLabel = new JLabel(frameIcon);

					panel1.add(frameLabel);
					panel1.repaint();
					panel1.validate();
				}

			}
		});

		prevButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentPosition--;
				if (currentPosition < 0) {
					JOptionPane.showMessageDialog(null, "Begin");
				} else {
					panel1.remove(frameLabel);
					String imagepath = folderName + "/" + fileList[currentPosition].getName();
					System.out.println(imagepath);
					frameIcon = new ImageIcon(imagepath);
					frameimage = frameIcon.getImage(); // transform it
					newframeimg = frameimage.getScaledInstance(200, 150, Image.SCALE_FAST);
					frameIcon = new ImageIcon(newframeimg);
					frameLabel = new JLabel(frameIcon);

					panel1.add(frameLabel);
					panel1.repaint();
					panel1.validate();
				}

			}
		});

		mainPanel.add(panel1, new GridBagConstraints(0, 1, 3, 1, 2, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));

		mainPanel.add(panel2, new GridBagConstraints(0, 2, 1, 1, 2, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));

	}

	public File[] readImages(String folderName) throws IOException {
		File folder = new File(folderName);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println(listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}

		return listOfFiles;
	}

}
