package Gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import defaultPackage.*;

public class SubjectPanel extends SideBarPanel implements ActionListener{
	Semester semester;
	SemesterPanel topPanel;
	public SubjectPanel(Semester p,SemesterPanel topPanel) {
		semester = p;
		this.topPanel = topPanel;
		setPreferredSize(new Dimension(100,100));
		
		title = new JLabel("Subjects");
		add(title);
		
		//load all Subjects
		Subject[] subjects = p.getSubjects();
		buttons = new SideBarButton[subjects.length+1];
		loadButtons();
	}
	/**
	 * Loads and adds a Button for each Subjects and a button at the top to get back to Semesters
	 */
	private void loadButtons() {
		//Add Button to get Back to Semesters
		buttons[0] = new SideBarButton("Semesters");
		buttons[0].addActionListener(this);
		add(buttons[0]);
		
		Subject[] subjects = semester.getSubjects();
		for(int i = 1; i<buttons.length;i++) {
			buttons[i] = new SideBarButton(subjects[i-1].getSubjectName());
			buttons[i].addActionListener(this);
 			add(buttons[i]);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int button = findButton(e);
		if(button == 0) {//Back to Semester Button wurde gedrueckt
			setVisible(false);
			topPanel.setVisible(true);
			topPanel.getTopPanel().remove(this);
		}else {
			changeMainPanel(semester.getSubjects()[button-1]);
			System.out.println(semester.getSubjects()[button-1].getSubjectName());
		}
		
		//System.out.println(semester.getSubjects()[button].getSubjectName());
		
	}
	private void changeMainPanel(Subject subject) {
		
	}
}
