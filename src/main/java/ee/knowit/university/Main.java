package ee.knowit.university;


import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ee.knowit.university.ui.StudentsListUI;

public class Main {
	
	
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		Display display = Display.getDefault();
		
		Shell shell = new StudentsListUI().getShell();

		shell.open();
		shell.layout();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();

			}
		}
	}
	
	
	    
	   
}
