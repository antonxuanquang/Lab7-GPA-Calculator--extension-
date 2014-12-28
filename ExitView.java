import java.awt.*;
import java.text.DecimalFormat;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.event.*;

public class ExitView extends JPanel implements ActionListener {

	Lab7 lab7;
	
	JLabel lb_welcome1 = new JLabel ("Welcome to the Texas Christian University", JLabel.CENTER), 
			lb_welcome2 = new JLabel ("GPA Calculation Program", JLabel.CENTER),
			lb_prompt = new JLabel ("Do you want to exit???", JLabel.CENTER);
	
	JButton b_cancel_exit = new JButton ("Cancel"),
			b_terminate = new JButton ("Terminate");
	
	JPanel p1n = new JPanel (new GridLayout (2,1));
	JPanel p1s = new JPanel ();
	
	public ExitView(Lab7 fromLab7) {
		lab7 = fromLab7;
		lab7.setSize (500,200);
		setLayout(new BorderLayout());
		add("North", p1n);
			p1n.add(lb_welcome1);
			p1n.add(lb_welcome2);
			lb_welcome1.setFont(new Font("San Serif", Font.BOLD, 20));
			lb_welcome1.setForeground(Color.RED);
			lb_welcome2.setFont(new Font("San Serif", Font.ITALIC+ Font.BOLD, 18));
			lb_welcome2.setForeground(Color.RED);
		add("Center", lb_prompt);
			lb_prompt.setFont(new Font("San Serif", Font.ITALIC, 15));
			lb_prompt.setForeground(Color.BLUE);
		add("South", p1s);
			p1s.add(b_terminate);
			p1s.add(b_cancel_exit);
				b_cancel_exit.setForeground(Color.RED);
		b_cancel_exit.addActionListener(this);
		b_terminate.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == b_cancel_exit) {
			lab7.createStartupView();
		} else {
			lb_prompt.setText("Good Bye!!!!");
			lb_prompt.setFont(new Font ("San serif", Font.BOLD, 25));
			b_cancel_exit.setEnabled(false);
			b_terminate.setEnabled(false);
		}
	}
}
