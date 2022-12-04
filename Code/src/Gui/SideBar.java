package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import defaultPackage.*;

public class SideBar extends JPanel{
	Interface study;
	public SideBar(Interface study) {
		this.study = study;
		setBackground(Color.black);
		setPreferredSize(new Dimension(200,200));
		setLayout(new BorderLayout());
		add(new SemesterPanel(study,this),BorderLayout.CENTER);
		
	}
}
