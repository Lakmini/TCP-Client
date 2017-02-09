package com.Sortex.frontendController;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Window3  {
	JPanel container;
	JLabel resultLabel;
	public JPanel createTestPanel()
	{
		container = new JPanel();
		container.setBackground(Color.white);
		resultLabel = new JLabel();
		 TitledBorder border = new TitledBorder("Result of classification");
			border.setTitleJustification(TitledBorder.LEFT);
			border.setTitlePosition(TitledBorder.TOP);
		resultLabel.setBorder(border);
		resultLabel.setPreferredSize(new Dimension(400, 400));
		//resultLabel.setBackground(Color.white);
		container.add(resultLabel);
		return container;
	}

}
