# Raytheon Take-Home Coding Project -- Calendar

Language: `Java 1.8`

## Info

Some code that should help answer the question:
> "I need help counting meeting occurrences. If I schedule on Wednesdays, how many meetings will we have between today
and the end of 2019? If we move to Thursdays next year, how many meetings will we have in total? We might have to miss
certain dates for vacation or holidays."

Rough time breakdown:
* `< 30min` actually coding the algorithm for calculating weekday occurrences
* `the rest of the time` thinking through the solution, creating the interface, making the parts work together,
testing, documentation, clean-up, etc

## How to run

### Text/File input

1. Place `.csv` file in the root directory of this project
 * Note: file should be formatted as follows:
 ```
 # this is a comment and will be ignored
 # whitespace is trimmed
 # date format: yyyy-MM-dd
 # weekday should be spelled correctly

 startDate,endDate,weekday

 # eg:

 2019-01-01, 2019-12-31, Monday
 ```
1. Run `CLIDriver.java`
 * You can put the `.csv` file as the first command-line argument: `java.exe CLIDriver.java x.csv`, **or** you the
 program will prompt you for the `.csv` file if you run it without command-line arguments.

### GUI input

1. Run `GUIDriver.java`
1. Fill in the form (todo: describe how)
1. Click "Calculate!"

### Testing suite

Testing uses `JUnit 4`. `TestingSuite.java` contains two public test methods. The first, `testOne()`, allows you to run
a given `String fName` `.csv` file in `/test-inputs` -- simply edit the `fName` variable to the file you want to run.
The file you run will be tested against the same corresponding file-name located in `/test-outputs`. The second method,
`testAll()`, allows you to test every file in `/test-inputs` (and checks them against `/test-outputs`).

`/test-inputs` file format is listed above. `/test-outputs` format is a list of integers as follows:
```
int,int,int, ... ,int
```
where each integer in column `i` of file `/test-outputs/x.csv` corresponds to row `i` (without comments/newlines)
of the corresponding file `/test-inputs/x.csv`. The integers are the number of meeting occurrences for that specific
test case.

## Thoughts

Interesting project. This doesn't feel representative of something I would be doing at Raytheon, however I understand
the point of the project is to show-off coding skills and see what you can come up with given vague project details.

Some things we could implement later:
* "today" button, to fill the date as the current day
* [a date picker](https://www.codejava.net/java-se/swing/how-to-use-jdatepicker-to-display-calendar-component)
* back-fill meetings already set (like holidays and already-set time constraints)
* better form layout (eg make radio button side-by-side)
* create another mode for `CLIDriver.java` that works the same way as `GUIDriver.java`
* all of the `todo`s placed in the code
* etc
