# Activiti Eclipse Designer Extension

[http://activiti.org/userguide/index.html#eclipseDesignerExtending](http://activiti.org/userguide/index.html#eclipseDesignerExtending)

## Features
- Disabling default shapes in the palette
- Adding shapes to the palette

## Applying extension

1. created your extension JAR (by performing a *mvn install* in the project to build it with Maven)
2. store the extension somewhere on the hard drive (the location must be outside the Eclipse workspace)
3. create a activiti project (this project type is required to see the Activiti Designer Extensions group in the preferences)
4. select *Window > Preferences* in Eclipse and search for *user*
5. access the *User Libraries* in the *Java* section
	1. you should see the default group where you can add extensions to Activiti Designer (depending on your Eclipse installation)
6. select the *Activiti Designer Extensions* group and click the *Add external JARs...*
	1. select the JAR from the 2. point
	2. you should see the extension as part of the Activiti Designer Extensions group
7. click the OK button and your diagrams you open will now perform your extension

## Notes
- new XML elements, attributes etc. have the 'activiti:' namespace prefix
