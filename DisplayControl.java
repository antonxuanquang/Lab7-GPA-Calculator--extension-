
public class DisplayControl {
	
	DisplayView view;
	
	public DisplayControl(DisplayView fromView) {
		view = fromView;
	}
	
	public void initialize() {
		view.field.setEnabled(false);
		view.order.setEnabled(false);
		populate();
	}
	
	public void populate() {
		view.model.removeAllElements();
		view.model.addElement(setSpace("Student Name") 
				+ setSpace("ID").substring(0,15)
				+ setSpace("GPA").substring(0, 15));
		view.model.addElement("---------------------------------------------------------");
		if (view.frommodel.student_number == 0) {
			view.model.addElement("******No student in Database******");
			view.b_delete_all.setEnabled(false);
		} else {
			for (int i = 0; i < view.frommodel.student_number; i++) {
				view.model.addElement(setSpace(view.frommodel.studentData[i].name)
						+ setSpace("" + view.frommodel.studentData[i].id).substring(0,15)
						+ setSpace("" + view.frommodel.studentData[i].gpa).substring(0,15));
			}
			view.b_delete_all.setEnabled(true);
		}
		//view.lab7.gui.validate();
	}
	
	public String setSpace(String string) {
		String blank = "                          ";
		int string_length = string.length();
		int blank_length = blank.length();
		int cut = blank_length - string_length;
		string = string + blank.substring(0, cut);
		return string;
	}
	
	public void delete() {
		int index = view.list.getSelectedIndex();
		index = index - 2;
		view.frommodel.delete(index);
		view.b_delete.setEnabled(false);
		populate();
	}
	
	public void arrange() {
		int order = view.order.getSelectedIndex();
		int field = view.field.getSelectedIndex();
		
		String select = "" + order + field;
		
		switch (select) {
		case "11": view.frommodel.ascName(); break;
		case "12": view.frommodel.ascId(); break;
		case "13": view.frommodel.ascGPA(); break;
		case "21": view.frommodel.desName(); break;
		case "22": view.frommodel.desId(); break;
		case "23": view.frommodel.desGPA(); break;
		}
		
		populate();
	}
}
