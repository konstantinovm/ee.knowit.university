REQUIREMENTS
=================
Java 1.8
Application GUI is based on Eclipse SWT framework which uses native GUI libraries, therefore needs to perform separate builds for each OS.
Currently build profiles are setup only for Windows (no possibility to test builds for other systems)

BUILDING APPLICATION
=================
CMD:
Run "mvn package -P <profile corresponding to your OS>" in the projects root catalog.
Available proflies are:
	- windows64 (enabled by default)
	- windows32 
	- macosx32
	- macosx64
	- linux32
	- linux64
(Maven installation instructions: https://maven.apache.org/install.html)

Eclipse/Other IDE with maven plugin:
	set the following to the profile, which corresponds to your OS
		<activation>
	      <activeByDefault>true</activeByDefault>
	    </activation>

RUNNING APPLICATION
=================
Execute runnable jar 
	1) if using included built application (tested for Windows x64 only)
		a) double click on bin(target)/university_*.jar (choose file which corresponds to your OP)
		b) or call "java -jar university.jar" in cmd opened in /bin

