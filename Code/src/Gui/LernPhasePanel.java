package Gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.security.auth.Subject;
import javax.swing.*;

import defaultPackage.Interface;
import defaultPackage.LearningPhase;

public class LernPhasePanel extends JPanel{
	JButton start,end,breakControll;
	JPanel started;
	Interface study;
	boolean breakStarted;
	public LernPhasePanel(Interface study) {
		super();
		this.study = study;
		setPreferredSize(new Dimension(50,50));
		initaliseButtons();
		initaliseJPanel();
		
		if(study.getCurrentLearningPhase() == null) {
			 started.setVisible(false);
			 breakStarted = false;}
		else {
			start.setVisible(false);
			//check if there is already a Break started in the learning Phase
			breakStarted = study.getCurrentLearningPhase().breakStarted();
			if(breakStarted)
				breakControll.setText("Pause beenden");
			}
		
		
		
		
		
		
	}
	
	private void initaliseJPanel() {
		 started = new JPanel();
		 
		 end = new JButton("Lernen beenden");
		 end.setBounds(200,100,0,0);
		 end.addActionListener(e -> finishLearningPhase());
		 
		 
		 
		 breakControll = new JButton("Pause starten");
		 breakControll.setBounds(200,100,0,0);
		 breakControll.addActionListener(e -> breakLearningPhase());
		 
		 
		 started.add(end);
		 started.add(breakControll);
		 
			 
		 add(started);
	
	}
	
	private void initaliseButtons() {
		start = new JButton("Lernen beginen");
		start.setBounds(200,100,0,0);
		start.addActionListener(e -> startLearningPhase());
		
		add(start);
	}
	
	private void breakLearningPhase() {
		if(breakStarted){//there is a running Break --> end Break
			study.endBreak();
			breakControll.setText("Pause starten");
			breakStarted = false;
		}else {
			study.startBreak();
			breakControll.setText("Pause beenden");
			breakStarted = true;
		}
		
	}

	private void finishLearningPhase() {
		long learned = study.finishLearningPhase();
		JOptionPane.showMessageDialog(null, "Sie Haben "+ learned/60+" Minuten und "+ learned%60+" Sekunden gelernt","Learn Info",JOptionPane.INFORMATION_MESSAGE);
		started.setVisible(false);
		start.setVisible(true);
	}

	private void startLearningPhase() {
		defaultPackage.Subject[] subjects = study.getCurrentSubjects();
		String[] options = new String[subjects.length];
		
		for(int i = 0; i<subjects.length;i++) {
			options[i] = subjects[i].getSubjectName();
		}
		
		String input = (String) JOptionPane.showInputDialog(null, "Für welches Fach möchten sie lernen",
		        "Fach auswahl", JOptionPane.QUESTION_MESSAGE, null, // Use
		                                                                        // default
		                                                                        // icon
		        options, // Array of choices
		        options[0]); // Initial choice
		 
		study.startLearningPhase(input); 
		start.setVisible(false);
		started.setVisible(true);
	}
}
