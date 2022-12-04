package Gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import defaultPackage.*;

public class SemesterPanel extends SideBarPanel implements ActionListener{
	Interface study;
	JPanel topPanel;
	public SemesterPanel(Interface study,JPanel topPanel) {
		//settings for the Panel
		setPreferredSize(new Dimension(100,100));
		
		this.study = study;
		this.topPanel = topPanel;
		
		title = new JLabel("Semester");
		add(title);
		
		
		//Load all Semesters
		Semester[] semesters = study.getSemesters(); 
		buttons = new SideBarButton[semesters.length];
		loadButtons();
		
	
	}
	
	/**
	 * Loads and adds a Button for each semester
	 */
	private void loadButtons() {
		Semester[] semesters = study.getSemesters();
		for(int i = 0; i<buttons.length;i++) {
			buttons[i] = new SideBarButton(""+semesters[i].getSemester());
			buttons[i].addActionListener(this);
 			add(buttons[i]);
		}
	}

	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int button = findButton(e);
		changeMainPanel(study.getSemesters()[button].getSemester());
	}
	
	/**
	 * changes the Main panel to semester view and sideBar to subject view of this semester
	 * @param semester - number of Semester
	 */
	private void changeMainPanel(int semester) {
		setVisible(false);
		topPanel.add(new SubjectPanel(study.getSemesters()[semester-1],this));
		//To Do : Change Main Panel
		
		//Change SideBar Panel to Subject Panel of Semester
	}
	
	public JPanel getTopPanel() {
		return topPanel;
	}
	
}
