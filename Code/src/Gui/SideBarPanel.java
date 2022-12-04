package Gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SideBarPanel extends JPanel{
	JButton[] buttons;
	JLabel title;
	
	public SideBarPanel() {
		setLayout(new GridLayout(10,1,20,20));
	}
	/**
	 * 
	 * @param e - ActionEvent
	 * @return Index of Button that triggered e
	 */
	protected int findButton(ActionEvent e) {
		for(int i = 0; i< buttons.length;i++) {
			if(e.getSource() == buttons[i])
				return i;
		}
		return -1;
	}
}
