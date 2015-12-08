package ee.knowit.university.model;

public class StudentStatistics 
{
	protected Student student;
	
	protected double average;
	
	public StudentStatistics(Student student) {
		this.student = student;
		calculateAndSetAverage();
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}
	
	protected void calculateAndSetAverage() 
	{
		if(student.getSubjects() != null && student.getSubjects().size() != 0)
			average = student.getSubjects().stream().mapToDouble(Subject::getResult).average().getAsDouble();
		else 
			average = 0;
	}
	
	
}
