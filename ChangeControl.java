import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


	public class ChangeControl {

	ChangeView view;
	
	
	int error = 0;
	double gpa,grade;
	
	public ChangeControl (ChangeView fromView) {
		view = fromView;
	}
//select	
	public int select() {
		int index = 0;
		if (view.cbb_GPA.isEnabled()) {
			index = view.cbb_GPA.getSelectedIndex();
		} else if (view.cbb_ID.isEnabled()) {
			index = view.cbb_ID.getSelectedIndex();
		} else if (view.cbb_name.isEnabled()) {
			index = view.cbb_name.getSelectedIndex();
		}
		view.cbb_GPA.setSelectedIndex(index);
		view.cbb_ID.setSelectedIndex(index);
		view.cbb_name.setSelectedIndex(index);
		
		return index;
	}
	
	public void displayInfo(int fromindex) {
		int index = fromindex;
		view.add("South", view.p3b);
		view.p3b.setVisible(true);
		view.p3.setVisible(false);
		view.p2.setVisible(true);
		view.p1s.setVisible(false);
		view.tf_student_name.setText("" + view.cbb_name.getSelectedItem());
		view.tf_gpa.setText("" + view.cbb_GPA.getSelectedItem());
		view.tf_id_num.setText("" + view.cbb_ID.getSelectedItem());
		view.lab7.gui.validate();
		Student temp = view.model.studentData[index];
		for (int i = 0; i < 7; i++) {
			view.tf_credit[i].setText(temp.hour[i]);
			view.tf_grade[i].setText(temp.grade[i]);
		}
	}
	
// calculate GPA	
	public int calculateGPA() {
		int error = 0;
		double total_grades = 0;
		double total_hours = 0;
		double grade = 0;
		double hour = 0;
		double gpa = 0;
		error += testID();
		for (int i = 0; i < 7; i++) {
			if (view.tf_grade[i].getText().equals("") && view.tf_credit[i].getText().equals("")) {
				
			} else {
				error += testGrade(view.tf_grade[i].getText(), i);
				error += testHour(i);
				if (error == 0) {
					grade = returnGrade(view.tf_grade[i].getText(), i);
					hour = returnHour(i);
					total_grades += grade * hour;
					total_hours += hour;
				}
			}
		}
		if (total_hours == 0) {
			gpa = 0.0;
		} else {
			gpa = total_grades/total_hours;
		}
		
		if (error > 0) {
			view.tf_gpa.setText("invalid input");
		} else {
			view.tf_gpa.setText(view.decimal.format(gpa));
		}
		return error;
	}
	
	public int testID () {
		int error = 0;
		try {
			Integer.parseInt(view.tf_id_num.getText());
		} catch (NumberFormatException e) {
			error++;
			view.tf_id_num.setText("invalid input");
		}
		return error;
	}
	
	public int testGrade(String s, int i) {
		int error = 0;
		if (returnGrade(s, i) < 0) {
			error++;
			view.tf_grade[i].setText("invalid input");
		}
		return error;
	}
	
	public double returnGrade(String s, int i) {
		double grade = 0;
		s = s.toUpperCase();
		switch (s) {
		case "A": grade = 4.0; break;
		case "B": grade = 3.0; break;
		case "C": grade = 2.0; break;
		case "D": grade = 1.0; break;
		case "F": grade = 0.0; break;
		default: {
			grade = -1.0;
			
			break;
		}
		}
		return grade;
	}
	
	public int testHour(int i) {
		int error = 0;
		if (returnHour(i) < 0) {
			error++;
			view.tf_credit[i].setText("invalid input");
		}
		return error;
	}
	
	public double returnHour(int i) {
		int hour = 0;
		try {
			hour = Integer.parseInt(view.tf_credit[i].getText());
			if (hour < 0 || hour > 4) {
				System.out.println("@if");
				hour = -1;
			}
		} catch (NumberFormatException e) {
			System.out.println("@catch");
			hour = -1;
		}
		double hourD = hour;
		return hourD;
	}
	
	public int testGPA() {
		int error = 0;
		try {
			Double.parseDouble(view.tf_gpa.getText());
		} catch (NumberFormatException e) {
			error++;
			view.tf_gpa.setText("invalid input");
		}
		return error;
	}
	
	public void tfListener() {//makes sure that buttons is enabled only when user has provided enough information: ID, name, grade and corresponding credit hours
		DocumentListener documentListener = new DocumentListener() {
			public void changedUpdate(DocumentEvent documentEvent) {
				enableButtons();
			}

			public void insertUpdate(DocumentEvent documentEvent) {
				enableButtons();
			}

			public void removeUpdate(DocumentEvent documentEvent) {
				enableButtons();
			}
			
			public boolean textInTf () {
				boolean b;
				String s = "";
				for (int i = 0; i < view.tf_credit.length; i++) {
					s += view.tf_credit[i].getText();
					s += view.tf_grade[i].getText();
				}
				if (s.length() > 0) {
					b = true;
				} else {
					b = false;
				}
				return b;
			}
			
			public void enableButtons() {
				if (view.tf_id_num.getText().length() > 0) {
					view.b_clear.setEnabled(true);
					if (view.tf_student_name.getText().length() > 0 && textInTf()) {
						view.b_cal.setEnabled(true);
						view.b_save.setEnabled(true);
					}
				}
			}
		};
		
		view.tf_id_num.getDocument().addDocumentListener(documentListener);
		view.tf_student_name.getDocument().addDocumentListener(documentListener);
		for (int i = 0; i < view.tf_credit.length; i++) {
			view.tf_credit[i].getDocument().addDocumentListener(documentListener);
			view.tf_grade[i].getDocument().addDocumentListener(documentListener);
		}
	}
	
	public void controlButtons (boolean b) {
		view.b_cal.setEnabled(b);
		view.b_clear.setEnabled(b);
		view.b_save.setEnabled(b);
	}
	
// clear fields
	public void clear () {
		view.tf_gpa.setText("");
		view.tf_id_num.setText("");
		view.tf_student_name.setText("");
		for (int i = 0; i < view.tf_credit.length; i++) {
			view.tf_credit[i].setText("");
			view.tf_grade[i].setText("");
		}
		controlButtons(false);
	}
	
// save
	public void save (int index) {
		if (calculateGPA() == 0) {
			Student temp = new Student();
			temp.id = Integer.parseInt(view.tf_id_num.getText());
			temp.gpa = Double.parseDouble(view.tf_gpa.getText());
			temp.name = view.tf_student_name.getText();
			temp.grade = new String [7];
			temp.hour = new String [7];
			for (int i = 0; i < 7; i++) {
				temp.grade[i] = view.tf_grade[i].getText();
				temp.hour[i] = view.tf_credit[i].getText();
			}
			view.model.studentData[index] = temp;
			back();
			view.addCBBItems();
		}
	}
	
// return button
	public void back() {
		view.add("South", view.p3);
		view.p3.setVisible(true);
		view.p3b.setVisible(false);
		view.p2.setVisible(false);
		view.p1s.setVisible(true);
		view.cbb_GPA.setSelectedIndex(0);
		view.cbb_name.setSelectedIndex(0);
		view.cbb_ID.setSelectedIndex(0);
		clear();
		view.b_change.setEnabled(false);
		view.lab7.gui.validate();
	}



}


