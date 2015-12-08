REQUIREMENTS
=================
Java 1.8
Application GUI is based on Eclipse SWT framework which uses native GUI libraries, therefore needs to perform separate builds for each OS.
Currently build profiles are setup only for Windows (no possibility to test builds for other systems)

BUILDING APPLICATION
=================
Run "mvn package" in the projects root catalog. 
(Maven installation instructions: https://maven.apache.org/install.html)

This option is needed if you run 

RUNNING APPLICATION
=================
Execute runnable jar 
	1) if using included built application (built for Windows x64)
		a) double click on bin/university.jar
		b) or call "java -jar university.jar" in cmd opened in /bin
	2) if running a built app
		a) double click on target/university-jar-with-dependencies.jar
		b) or call "java -jar university-jar-with-dependencies.jar" in cmd opened in /target
