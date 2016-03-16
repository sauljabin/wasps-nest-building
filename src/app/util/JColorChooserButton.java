/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of WaspsNestBuilding.
 * 
 * WaspsNestBuilding is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;

public class JColorChooserButton extends JButton implements ActionListener {

	private static final long serialVersionUID = -4014979660357753001L;
	private Color color;
	private int padding;

	public JColorChooserButton() {
		this(Color.BLACK);
	}

	public JColorChooserButton(Color color) {
		this.color = color;
		padding = 8;
		addActionListener(this);
		setMinimumSize(new Dimension(30, 25));
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		repaint();
	}

	public int getPadding() {
		return padding;
	}

	public void setPadding(int padding) {
		this.padding = padding;
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(color);
		g.fillRect(padding, padding, getWidth() - padding * 2, getHeight() - padding * 2);
		g.setColor(Color.BLACK);
		g.drawRect(padding, padding, getWidth() - padding * 2 - 1, getHeight() - padding * 2 - 1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Color colorTemp = JColorChooser.showDialog(null, "Color", color);
		if (colorTemp == null)
			colorTemp = color;
		setColor(colorTemp);
	}

}
