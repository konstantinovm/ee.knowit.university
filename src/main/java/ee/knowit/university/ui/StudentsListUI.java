package ee.knowit.university.ui;

import static java.util.Comparator.comparing;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ee.knowit.university.Main;
import ee.knowit.university.model.Student;
import ee.knowit.university.model.StudentStatistics;
import ee.knowit.university.model.xml.InvalidSourceXMLContentException;
import ee.knowit.university.model.xml.student.StudentsReader;


public class StudentsListUI

{
	private static final Logger logger = LoggerFactory.getLogger(StudentsListUI.class);
	
	private static final String[] TABLE_HEADER = new String[] { "First Name", "Last Name", "Weighted Average" }; 
	private static final NumberFormat FORMATTER = new DecimalFormat("#0.##");
	private static final String[] FILE_NAME_FILTER = new String[] {"*.xml"};
	private Shell shell;
	
	
	public StudentsListUI() {
		shell = new Shell();
		initUI();
	}
	
	protected void initUI(){
		shell.setSize(576, 374);
		shell.setText("Knowit University Student List");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(0, 0, 560, 300);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		Table table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayout(new FillLayout());

		for (String title : TABLE_HEADER) {
			TableColumn tableColumn = new TableColumn(table, SWT.LEFT);
			tableColumn.setText(title);
			tableColumn.pack();
		}
		
		scrolledComposite.setContent(table);
		scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		Button btnBrowse = new Button(shell, SWT.NONE);
		
		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {

				FileDialog dialog = new FileDialog(shell, SWT.NULL);
				dialog.setFilterExtensions(FILE_NAME_FILTER);
				String path = dialog.open();
				if (path == null) return;

				List<Student> students;
				List<StudentStatistics> studentStatisticsList; 
				
				try {
					students = StudentsReader.getInstance().read(new FileInputStream(path));
				} catch (FileNotFoundException | InvalidSourceXMLContentException e) {
					handleException(String.format("Problem parsing %s ", path), e);
					return;
				}
				
				//try {
					studentStatisticsList = students.stream().map(StudentStatistics::new).collect(Collectors.toList());
				/*} catch (ClassCastException e) { //should happen when list of students is actually list of something else
					handleException(String.format("Problem parsing %s. Incorrect file format.", path), e);
					return;
				}*/
					
				if(studentStatisticsList.size() == 0) handleException(String.format("No student data found in %s. Please refer to the sample.", path), null);
					
				table.removeAll();

				studentStatisticsList.stream()
				.sorted(comparing(StudentStatistics::getAverage)
						.thenComparing(s -> s.getStudent().getFirstName())
						.thenComparing(s -> s.getStudent().getLastName()))
				.forEachOrdered(s -> new TableItem(table, SWT.NONE)
						.setText(new String[]{ 
								s.getStudent().getFirstName(),  
								s.getStudent().getLastName(),
								FORMATTER.format(s.getAverage())}));
							
				table.redraw();
			}
		});
		
		btnBrowse.setBounds(473, 306, 75, 25);
		btnBrowse.setText("Browse");
		
		Button btnSample = new Button(shell, SWT.NONE);
		
		btnSample.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				showSampleFile();
			}
		});
		
		btnSample.setBounds(10, 306, 120, 25);
		btnSample.setText("View sample input");
	}
	
	public Shell getShell() 
	{
		return shell;
	}
	
	public void handleException(String displayMessage, Exception e)  {
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK );
		messageBox.setText("Error");
		StringBuilder sb = new StringBuilder(displayMessage);
		
		
		if(e != null) {
			sb.append("\n\rPlease see log file for more information.");
			logger.error(displayMessage, e);
		}
		
		messageBox.setMessage(sb.toString());
		messageBox.open();
		
	}
	
	public void showSampleFile() 
	{
		File temp;
		try {
			temp = File.createTempFile("sample_knowit_university_input", ".xml");
			OutputStream out = new FileOutputStream(temp);
			InputStream in = StudentsListUI.class.getResourceAsStream("sample.xml"); 
			
			IOUtils.copy(in,out);
			in.close();
			out.close();
		} catch (IOException e) {
			handleException("", e);
			return;
		} 
		temp.deleteOnExit();
		Program.launch(temp.getAbsolutePath());
	}
}
