import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartupView extends JPanel implements ActionListener {
	Lab7 lab7;
	
	JLabel lb_welcome1 = new JLabel ("Welcome to the Texas Christian University", JLabel.CENTER), 
			lb_welcome2 = new JLabel ("GPA Calculation Program", JLabel.CENTER),
			lb_prompt = new JLabel ("Choose an operation program", JLabel.CENTER);
	JButton b_new_student = new JButton ("Enter a New Student"),
			b_display = new JButton ("Display All Student Info"),
			b_exit = new JButton ("Exit the Program"),
			b_change = new JButton ("Change Student Info");
	JPanel p1 = new JPanel (new BorderLayout (3,5));
	JPanel p2 = new JPanel ();
	JPanel p3 = new JPanel (new GridLayout (2,1));
	
	public StartupView(Lab7 fromLab7) {
		lab7 = fromLab7;
		
		setLayout (new FlowLayout());
		lab7.setSize (700,200);
		add("Center", p1);
			p1.add("North", p3);
				p3.add(lb_welcome1);
				p3.add(lb_welcome2);
				lb_welcome1.setFont(new Font("San Serif", Font.BOLD, 20));
				lb_welcome1.setForeground(Color.RED);
				lb_welcome2.setFont(new Font("San Serif", Font.ITALIC+ Font.BOLD, 18));
				lb_welcome2.setForeground(Color.RED);
			p1.add("Center", lb_prompt);
				lb_prompt.setFont(new Font("San Serif", Font.ITALIC, 15));
				lb_prompt.setForeground(Color.BLUE);
			p1.add("South", p2);
				p2.add(b_new_student);
				p2.add(b_change);
				p2.add(b_display);
				p2.add(b_exit);
		add("East", new JLabel());
		add("West", new JLabel());
		
		b_new_student.addActionListener(this);
		b_display.addActionListener(this);
		b_exit.addActionListener(this);
		b_change.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == b_exit) {
			lab7.createExitView();
		} else if(ae.getSource() == b_display) {
			lab7.createDisplayView();
		} else if (ae.getSource() == b_new_student) {
			lab7.createEnterView();
		} else if (ae.getSource() == b_change) {
			lab7.createChangeView();
		}
	}

}
