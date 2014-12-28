import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.event.*;

import java.awt.*;
import java.text.DecimalFormat;

import javax.swing.*;

public class EnterView extends JPanel implements ActionListener {

	Lab7 lab7;
	Model model;
	EnterControl control;
	
	JLabel lb_welcome1 = new JLabel ("Welcome to the Texas Christian University", JLabel.CENTER), 
			lb_welcome2 = new JLabel ("GPA Calculation Program", JLabel.CENTER),
			lb_prompt = new JLabel ("Enter the student's name, ID#, and grade information",JLabel.CENTER),
			lb_id_num = new JLabel ("Enter Student's ID Number: ", JLabel.CENTER),
			lb_student_name = new JLabel ("Enter Student's Name: ", JLabel.CENTER),
			lb_gpa = new JLabel ("GPA = ", JLabel.CENTER),
			tf_gpa = new JLabel ("");
	JLabel[] lb_grade = new JLabel[7];
	JLabel[] lb_credit = new JLabel[7];
	
	
	JTextField tf_id_num = new JTextField (10),
			tf_student_name = new JTextField (20);
			
	JTextField[] tf_grade = new JTextField [7];
	JTextField[] tf_credit = new JTextField [7];
	
	
	JButton b_cal = new JButton ("Calculate GPA"),
			b_clear = new JButton ("Clear Data Fields"),
			b_save = new JButton ("Save to Database"),
			b_cancel_student = new JButton ("Cancel");
	
	
	JPanel p1 = new JPanel (new BorderLayout()),
			p1n = new JPanel (new GridLayout(2,1)),
			p1s = new JPanel (),
			p2 = new JPanel (new BorderLayout()),
			p2n = new JPanel (),	
			p2c = new JPanel (new GridLayout(8,1)),
			p2c_inside = new JPanel (),
			p2s = new JPanel () ;
	
	public DecimalFormat decimal = new DecimalFormat("#.##");
	
	public void doNorth() {
		add("North", p1);
		p1.add("North", p1n);
			p1n.add(lb_welcome1);
			p1n.add(lb_welcome2);
				lb_welcome1.setFont(new Font("San Serif", Font.BOLD, 20));
				lb_welcome1.setForeground(Color.RED);
				lb_welcome2.setFont(new Font("San Serif", Font.ITALIC + Font.BOLD, 18));
				lb_welcome2.setForeground(Color.RED);
		p1.add("Center", lb_prompt);
			lb_prompt.setFont(new Font("San Serif", Font.ITALIC, 15));
			lb_prompt.setForeground(Color.BLUE);
		p1.add("South", p1s);
			p1s.add(lb_id_num);
			p1s.add(tf_id_num);
	}
	
	public void doCenter() {
		add("Center", p2);
		p2.add("North", p2n);
			p2n.add(lb_student_name);
			p2n.add(tf_student_name);
			p2n.add(lb_gpa);
			p2n.add(tf_gpa);
				tf_gpa.setFont(new Font("San Serif", Font.BOLD, 15));
				tf_gpa.setForeground(Color.MAGENTA);
		p2.add("Center", p2c);
			createInputView();		
		p2.add("South", p2s);
			p2s.add(b_cal);
			p2s.add(b_clear);
			p2s.add(b_save);
			p2s.add(b_cancel_student);
				b_cancel_student.setForeground(Color.RED);
	}
	
	public void createInputView () {
		for (int i = 0; i < lb_grade.length; i++) {
			p2c_inside = new JPanel ();
			p2c.add(p2c_inside);
			lb_grade[i] = new JLabel ("Course Grade " + (i+1) + ":");
				p2c_inside.add(lb_grade[i]);
			tf_grade[i] = new JTextField (6);
				p2c_inside.add(tf_grade[i]);
			lb_credit[i] = new JLabel ("Hours Credit " + (i+1) + ":");
				p2c_inside.add(lb_credit[i]);
			tf_credit[i] = new JTextField (6);
				p2c_inside.add(tf_credit[i]);
		}
	}
	
	public EnterView(Lab7 fromLab7, Model fromModel) {
		lab7 = fromLab7;
		model = fromModel;
		control = new EnterControl (this);
		
		lab7.setSize(700, 450);
	//create the view
		setLayout(new BorderLayout());
		doNorth();
		doCenter();
	//events	
		b_cal.addActionListener(this);
		b_cancel_student.addActionListener(this);
		b_clear.addActionListener(this);
		b_save.addActionListener(this);
	//control class	
		control.tfListener();
		control.controlButtons(false);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == b_cancel_student) {
			lab7.createStartupView();
		} else if (ae.getSource() == b_cal) {
			control.calculateGPA();
		} else if (ae.getSource() == b_clear) {
			control.clear();
		} else if (ae.getSource() == b_save) {
			control.save();
		}
	}
}







