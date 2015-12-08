package ee.knowit.university.model.xml.student;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;

import ee.knowit.university.model.Student;
import ee.knowit.university.model.Subject;
import ee.knowit.university.model.xml.InvalidSourceXMLContentException;

public class StudentsReader {
	protected JAXBContext context;

	private volatile static StudentsReader studentsReader;

	public static StudentsReader getInstance() {
		if (studentsReader == null) {
			synchronized (StudentsReader.class) {
				if (studentsReader == null) {
					studentsReader = new StudentsReader();
				}
			}
		}
		return studentsReader;
	}

	protected StudentsReader() {
		try {
			context = JAXBContext.newInstance(Students.class, Student.class, Subject.class);
		} catch (JAXBException e) {
			new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Student> read(InputStream is) throws InvalidSourceXMLContentException {
		
		try {
			Students wrapper = context.createUnmarshaller()
					.unmarshal(new StreamSource(is), Students.class).getValue();
			
			List<Student> list = wrapper.getStudents();
			if(list == null) return Collections.EMPTY_LIST;
			return list;
		} catch (JAXBException e) {
			throw new InvalidSourceXMLContentException(e);
		}
	
	}
}
