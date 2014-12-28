import java.awt.*;
import java.text.DecimalFormat;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.event.*;

public class ChangeView extends JPanel implements ActionListener, ItemListener{
	
	Model model;
	Lab7 lab7;
	ChangeControl control;
	
	JLabel lb_welcome1 = new JLabel ("Welcome to the Texas Christian University", JLabel.CENTER), 
			lb_welcome2 = new JLabel ("GPA Calculation Program", JLabel.CENTER),
			lb_prompt = new JLabel ("Change Student Information", JLabel.CENTER),
				lb_id_num = new JLabel (" Student's ID Number: ", JLabel.CENTER),
				lb_student_name = new JLabel ("Student's Name: ", JLabel.CENTER),
				lb_gpa = new JLabel ("GPA = ", JLabel.CENTER),
				tf_gpa = new JLabel ("");
	
	JLabel[] lb_grade = new JLabel[7];
	JLabel[] lb_credit = new JLabel[7];
	
	JTextField[] tf_grade = new JTextField [7];
	JTextField[] tf_credit = new JTextField [7];
	JTextField tf_id_num = new JTextField (8),
			tf_student_name = new JTextField (15);
			
	
	public DecimalFormat decimal = new DecimalFormat("#.##");
	
	JRadioButton rb_name = new JRadioButton ("Student Name: "),
			rb_id = new JRadioButton("ID Number: "),
			rb_gpa = new JRadioButton ("GPA: ");
	
	ButtonGroup group = new ButtonGroup();
	
	JButton b_cancel = new JButton ("Cancel"),
			b_select = new JButton ("Select"),
			b_change = new JButton ("Change Student's Info");
	JButton b_cal = new JButton ("Calculate GPA"),
			b_clear = new JButton ("Clear Data Fields"),
			b_save = new JButton ("Save & Return"),
			b_return = new JButton ("Return");
	
	JComboBox cbb_name = new JComboBox(),
			cbb_ID = new JComboBox(),
			cbb_GPA = new JComboBox();
	
	
	JPanel p1 = new JPanel (new BorderLayout ());
	JPanel p1n = new JPanel (new GridLayout (2,1));
	JPanel p1s = new JPanel (),
			p2 = new JPanel (new BorderLayout()),
			p2n = new JPanel (),	
			p2c = new JPanel (new GridLayout(8,1)),
			p2c_inside = new JPanel (),
			p3 = new JPanel (),
			p3b = new JPanel();
	
	public void addCBBItems() {
		cbb_GPA.removeAllItems();
		cbb_ID.removeAllItems();
		cbb_name.removeAllItems();
		cbb_name.addItem("**Select Name**");
		cbb_ID.addItem("**Select ID Number**");
		cbb_GPA.addItem("**Select GPA**");
		for (int i = 0; i < model.student_number; i++) {
			cbb_name.addItem(model.studentData[i].name);
			cbb_ID.addItem("" + model.studentData[i].id);
			cbb_GPA.addItem("" + model.studentData[i].gpa);
		}
	}
	
	private void doNorth() {
		add("North", p1);
		p1.add("North", p1n);
			p1n.add(lb_welcome1);
			p1n.add(lb_welcome2);
				lb_welcome1.setFont(new Font("San Serif", Font.BOLD, 20));
				lb_welcome1.setForeground(Color.RED);
				lb_welcome2.setFont(new Font("San Serif", Font.ITALIC+ Font.BOLD, 18));
				lb_welcome2.setForeground(Color.RED);
		p1.add("Center", lb_prompt);
			lb_prompt.setFont(new Font("San Serif", Font.ITALIC, 15));
			lb_prompt.setForeground(Color.BLUE);
		p1.add("South", p1s);
			group.add(rb_gpa);
			group.add(rb_id);
			group.add(rb_name);
			p1s.add(rb_name);
			p1s.add(cbb_name);
			p1s.add(rb_id);
			p1s.add(cbb_ID);
			p1s.add(rb_gpa);
			p1s.add(cbb_GPA);
		addCBBItems();
	}
	
	public void doCenter() {
		add("Center", p2);
		p2.add("North", p2n);
		p2n.add(lb_student_name);
		p2n.add(tf_student_name);
		p2n.add(lb_id_num);
		p2n.add(tf_id_num);
		p2n.add(lb_gpa);
		p2n.add(tf_gpa);
		tf_gpa.setFont(new Font("San Serif", Font.BOLD, 15));
		tf_gpa.setForeground(Color.MAGENTA);
		p2.add("Center", p2c);
			createInputView();
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
	
	public void doSouth() {
		add("South", p3);	
			p3.add(b_select);
			p3.add(b_change);
			b_change.setEnabled(false);
			p3.add(b_cancel);
	}
	
	public void doSouth2() {
		add("South", p3b);
			p3b.add(b_cal);
			p3b.add(b_clear);
			p3b.add(b_save);
			p3b.add(b_return);
	}
	
	public ChangeView (Lab7 fromLab7, Model fromModel) {
		lab7 = fromLab7;
		model = fromModel;
		control = new ChangeControl (this);
		
		lab7.setSize(700,450);
		setLayout(new BorderLayout());
		doNorth();
		doCenter();
		p2.setVisible(false);
		doSouth2();
		doSouth();
		control.tfListener();
		
		b_cancel.addActionListener(this);
		b_select.addActionListener(this);
		b_change.addActionListener(this);
		
		b_cal.addActionListener(this);
		b_clear.addActionListener(this);
		b_return.addActionListener(this);
		b_save.addActionListener(this);
		
		rb_gpa.addItemListener(this);
		rb_id.addItemListener(this);
		rb_name.addItemListener(this);
		rb_name.setSelected(true);
	}
	
	int index = 0;
	
	public void actionPerformed (ActionEvent ae) {
		
		if (ae.getSource() == b_cancel) {
			lab7.createStartupView();
		} else if (ae.getSource() == b_select) {
			index = control.select();
			index--;
			if (index < 0) {
				b_change.setEnabled(false);
			} else {
				b_change.setEnabled(true);
			}
		}
		if (ae.getSource() == b_change) {
			control.displayInfo(index);
		} else if (ae.getSource() == b_return) {
			control.back();
		} else if (ae.getSource() == b_cal) {
			control.calculateGPA();
		} else if (ae.getSource() == b_clear) {
			control.clear();			
		} else if (ae.getSource() == b_save) {
			control.save(index);
		}
	}

	public void itemStateChanged(ItemEvent ie) {
		if (ie.getSource() == rb_name) {
			cbb_name.setEnabled(true);
			cbb_GPA.setSelectedIndex(0);
			cbb_GPA.setEnabled(false);
			cbb_ID.setSelectedIndex(0);
			cbb_ID.setEnabled(false);
		} else if (ie.getSource() == rb_gpa) {
			cbb_GPA.setEnabled(true);
			cbb_name.setSelectedIndex(0);
			cbb_name.setEnabled(false);
			cbb_ID.setSelectedIndex(0);
			cbb_ID.setEnabled(false);
		} else if (ie.getSource() == rb_id) {
			cbb_ID.setEnabled(true);
			cbb_GPA.setSelectedIndex(0);
			cbb_GPA.setEnabled(false);
			cbb_name.setSelectedIndex(0);
			cbb_name.setEnabled(false);
		}
		b_change.setEnabled(false);
	}
	
	
}
