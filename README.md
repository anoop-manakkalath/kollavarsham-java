kollavarsham-java
=================

## Information
The JAVA API port for Kollavarsham (http://kollavarsham.org)

## Getting Started
Download the sources and build using ant in the project root directory.

To build the project:
```
mvn clean install
```
To view the report, go to the 'site/jacoco' folder of the 'target' folder and open 'index.html'

To use Kollavarsham API in a different project, add 'kollavarsham-java-1.0.0-SNAPSHOT.jar' from {project-root}/target to the classpath

## Usage

```
import org.kollavarsham.Kollavarsham;
...
...
...
	Kollavarsham malayalamYear = new Kollavarsham();
	Calendar modernDate = Calendar.getInstance();
	modernDate.set(2017, Calendar.APRIL, 2);
	malayalamYear.setModernDate(modernDate);
	malayalamYear.setOptions(true, "Ujjain");
	malayalamYear.FromGregorian();
	malayalamYear.getMalayalamYear();
	malayalamYear.getMalayalamMonthNum();
	malayalamYear.getMalayalamNakshatram();
```

## Documentation
TODO

## Contributing
In lieu of a formal styleguide, take care to maintain the existing coding style. Add unit tests for any new or changed functionality.

## Release History
_(Nothing yet)_

## License
Copyright (c) 2014-2017 The Kollavarsham Team. Licensed under the MIT license.
