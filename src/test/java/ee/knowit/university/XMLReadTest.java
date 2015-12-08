package ee.knowit.university;

import java.util.List;

import org.junit.Test;

import ee.knowit.university.model.Student;
import ee.knowit.university.model.xml.InvalidSourceXMLContentException;
import ee.knowit.university.model.xml.student.StudentsReader;

import static org.junit.Assert.*;

public class XMLReadTest 
{
	@Test(expected=InvalidSourceXMLContentException.class)
	public void testInvalidXML() throws InvalidSourceXMLContentException 
	{
		StudentsReader.getInstance().read(XMLReadTest.class.getResourceAsStream("students_invalid_xml.xml"));
	} 
	
	@Test
	public void testEmptyXML() throws InvalidSourceXMLContentException {
		List<Student> stds = StudentsReader.getInstance().read(XMLReadTest.class.getResourceAsStream("students_wrong_data.xml"));
		assertEquals(0, stds.size());
	} 
	
	@Test
	public void testCorrectXML() throws InvalidSourceXMLContentException {
		List<Student> stds = StudentsReader.getInstance().read(XMLReadTest.class.getResourceAsStream("students.xml"));
		assertEquals(5, stds.size());
		
		Student student = stds.get(2);
		
		assertEquals("fname3", student.getFirstName());
		assertEquals("lname3", student.getLastName());
		assertEquals(3, student.getSubjects().size());
		assertEquals(22, student.getSubjects().iterator().next().getResult());
	}

}
