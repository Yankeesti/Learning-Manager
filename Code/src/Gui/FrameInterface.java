package Gui;

import java.awt.BorderLayout;
import java.awt.MenuBar;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import defaultPackage.Interface;

public class FrameInterface extends JFrame{
	static int[] size = {1000,800}; //width|height
	Interface study;
	public FrameInterface(Interface study) {
		super();
		this.study = study;
		setTitle("Lern Manager");
		setSize(size[0], size[1]);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		setLayout(new BorderLayout());
		//Add Menu Bar
		
		setJMenuBar(new Gui.MenuBar(study));
		
		JPanel topBar = new LernPhasePanel(study);
		JPanel sideBar = new SideBar(study);
		
		add(topBar,BorderLayout.NORTH);
		add(sideBar,BorderLayout.WEST);
		
		setVisible(true);
		
	}

}
