package ee.knowit.university.model.xml.student;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ee.knowit.university.model.Student;

@XmlRootElement
public class Students {

	private List<Student> students;

	@XmlElement(name="student")
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
}
