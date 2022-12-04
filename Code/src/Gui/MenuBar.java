package Gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import defaultPackage.Interface;

public class MenuBar extends JMenuBar{
	JMenu fileMenu, editMenu;
	Interface study;
	public MenuBar(Interface study) {
		this.study = study;
		setUpFileMenu();
		setUpEditMenu();
		add(fileMenu);
		add(editMenu);
		}
	
	/**
	 * Sets up the File menue
	 */
	private void setUpFileMenu() {
		fileMenu = new JMenu("file");
		
		JMenuItem loadItem = new JMenuItem("Laden");
		loadItem.addActionListener(e -> loadFile());
		
		JMenuItem safeItem = new JMenuItem("Speicher");
		safeItem.addActionListener(e -> safeFile());
		
		JMenuItem safeAsItem = new JMenuItem("Speicher als");
		safeAsItem.addActionListener(e -> safeAs());
		
		fileMenu.add(loadItem);
		fileMenu.add(safeItem);
		fileMenu.add(safeAsItem);
		
		
	}
	
	private void setUpEditMenu() {
		editMenu = new JMenu("edit");
		
	}
	
	private void loadFile() {
		
	}
	
	private void safeFile() {
		study.safeData(study.getCurrentFile());
	}
	
	private void safeAs() {
		
	}
}
