package ee.knowit.university.model;


/**
 * Wrapper for Student with additional data/logic
 */
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

	public double getAverageResult() {
		return average;
	}

	public void setAverageResult(double average) {
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
