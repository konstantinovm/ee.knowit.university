package ee.knowit.university;

import java.util.Arrays;

import org.junit.Test;

import ee.knowit.university.model.Student;
import ee.knowit.university.model.StudentStatistics;
import ee.knowit.university.model.Subject;

import static org.junit.Assert.*;

public class StudentStatisticsTest 
{
	@Test
	public void testEmptyAverageCalculation() 
	{
		Student student = new Student();
		student.setSubjects(Arrays.asList(new Subject[] {}));
		StudentStatistics stats = new StudentStatistics(student);
		assertEquals(0, stats.getAverageResult(), 0);
	}
	
	
	@Test
	public void testAverageCalculation() 
	{
		Student student = new Student();
		student.setSubjects(Arrays.asList(new Subject[] {new Subject(52), new Subject(23), new Subject(106), new Subject(2)}));
		StudentStatistics stats = new StudentStatistics(student);
		assertEquals(45.75, stats.getAverageResult(), 0);
	}
}
