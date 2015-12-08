package ee.knowit.university.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {
	private String firstName;
	private String lastName;

	private Collection<Subject> subjects;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@XmlElementWrapper(name="subjects")
	@XmlElement(name="subject")
	public Collection<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(Collection<Subject> subjects) {
		this.subjects = subjects;
	}
}
