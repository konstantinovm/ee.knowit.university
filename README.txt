REQUIREMENTS
=================
Java 1.8
Application GUI is based on Eclipse SWT framework which uses native GUI libraries, therefore needs to perform separate builds for each OS.
Currently build profiles are setup only for Windows (no possibility to test builds for other systems)

BUILDING APPLICATION
=================
Run "mvn package" in the projects root catalog. 
(Maven installation instructions: https://maven.apache.org/install.html)

RUNNING APPLICATION
=================
Execute runnable jar 
	1) if using included built application (tested for Windows x64 only)
		a) double click on bin/university_*.jar (choose file which corresponds to your OP)
		b) or call "java -jar university.jar" in cmd opened in /bin

