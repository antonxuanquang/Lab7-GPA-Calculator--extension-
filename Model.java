
public class Model {
	Student [] studentData = new Student [50];
	int student_number = 0;
	
	public Model () {
		
	}
	
	public void save(String name, int id, double gpa, String[] grade, String[] hour) {
		Student temp = new Student();
		temp.gpa = gpa;
		temp.id = id;
		temp.name = name;
		temp.hour = hour;
		temp.grade = grade;
		studentData[student_number] = temp;
		student_number++;
	}
	
	public void delete(int index) {
		for (int i = index; i < student_number; i++) {
			studentData[i] = studentData[i+1];
		}
		student_number--;
	}
	
	public void deleteAll() {
		student_number = 0;
	}
	
	public void ascName() {
		for (int i = 1; i < student_number; i++) {
			Student temp = studentData[i];
			int index = i;
			while ((index >= 1) && (temp.name.compareTo(studentData[index -1].name) < 0)) {
				studentData[index] = studentData[index -1];
				index --;
			}
			studentData[index] = temp;
		}
	}
	
	public void desName () {
		for (int i = 1; i < student_number; i++) {
			Student temp = studentData[i];
			int index = i;
			while ((index >=1) && (temp.name.compareTo(studentData[index-1].name)) > 0) {
				studentData[index] = studentData[index-1];
				index--;
			}
			studentData[index] = temp;
		}
	}
	
	public void ascId() {
		for (int i = 1; i < student_number; i++) {
			Student temp = studentData[i];
			int index = i;
			while ((index >= 1) && (temp.id < studentData[index-1].id)) {
				studentData[index] = studentData[index-1];
				index--;
			}
			studentData[index] = temp;
		}
	}
	
	public void desId() {
		for (int i = 1; i < student_number; i++) {
			Student temp = studentData[i];
			int index = i;
			while ((index >= 1) && (temp.id > studentData[index-1].id)) {
				studentData[index] = studentData[index-1];
				index--;
			}
			studentData[index] = temp;
		}
	}
	
	public void ascGPA () {
		for (int i = 1; i < student_number; i++) {
			Student temp = studentData[i];
			int index = i;
			while ((index >= 1) && (temp.gpa < studentData[index-1].gpa)) {
				studentData[index] = studentData[index-1];
				index--;
			}
			studentData[index] = temp;
		}
	}
	
	public void desGPA () {
		for (int i = 1; i < student_number; i++) {
			Student temp = studentData[i];
			int index = i;
			while ((index >= 1) && (temp.gpa > studentData[index-1].gpa)) {
				studentData[index] = studentData[index-1];
				index--;
			}
			studentData[index] = temp;
		}
	}
}
