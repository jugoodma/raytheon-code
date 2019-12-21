package solution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * CLIDriver class. Contains main method for running the command-line interface providing simple access to the back-end
 * meeting occurrence calculation tool.
 */
public class CLIDriver {
    /**
     * Read .csv input file and return results
     *
     * @param args Command line arguments. The first argument can be the .csv file you want to run -- otherwise
     *             the program will prompt you for the .csv file. Files should be placed in the root directory of
     *             this project (or can be run from any sub-directory of the root -- the project root is the current
     *             Java root working directory).
     */
    public static void main(String[] args) {
        String file;
        // get the .csv input file
        if (args.length > 0) {
            file = args[0]; // todo: sanitize?
        } else {
            System.out.print("Input .csv file: ");
            Scanner input = new Scanner(System.in);
            file = input.nextLine(); // todo: sanitize?
            input.close();
        }

        // process the file
        String[][] data = processFile(file);

        // pretty-print the meeting occurrences
        prettyPrint(data, getOccurrences(data));
    }

    /**
     * Takes in a .csv file and returns the string representation of the contained data.
     *
     * @param file the input .csv file we need to process
     * @return a [length x 3] String list (of String lists) representing the cells in the input file. Each row is a list
     * of the form: <pre>["start date", "end date", "weekday"]</pre>
     */
    public static String[][] processFile(String file) {
        ArrayList<String[]> temp = new ArrayList<>();
        /*
        file setup:

        # input.csv
        # start, end, day of week
        2018-05-02, 2018-12-31, Wednesday
        2019-01-01, 2019-12-31, Thursday
        date,date,day\n

        are we handling quoted-out strings? todo.
         */
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            // https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
            while ((line = br.readLine()) != null) {
                // get rid of nasty whitespace
                line = line.trim();
                // check if line is a comment
                if (line.length() <= 0 || line.charAt(0) == '#') {
                    continue;
                }
                // split the line
                String[] splitLine = line.split(",");
                // todo : have a better test condition for .csv lines
                // ensure the line is 3 columns
                if (splitLine.length != 3) {
                    continue;
                }
                // get rid of whitespace in each column
                // todo : ensure the .csv file fits csv standards -- ie if a cell is quoted out, get rid of the quotes
                for (int i = 0; i < splitLine.length; i++) {
                    splitLine[i] = splitLine[i].trim();
                }
                temp.add(splitLine);
            }
        } catch (IOException e) {
            System.out.println("We couldn't find the requested file: " + file + "\nIs the file path correct?\n\nHere's the stack trace:");
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace(); // todo : clients are scared of stack traces -- make this pretty, or handle a different way
                }
            }
        }
        // put extend-able ArrayList into String array
        String[][] result = new String[temp.size()][];
        return temp.toArray(result);
    }

    /**
     * Iterate through each row in the input data, and count the number of meeting occurrences. Each row contains
     * a String list (of length 3) containing the start date, the end date, and the weekday. We want the number of
     * occurrences of the input weekday that happen between the start and end dates.
     *
     * @see #processFile(String)
     * @see Utilities#Count(String, String, String)
     *
     * @param data a String list of String lists representing the data
     * @return a list of the number of meeting occurrences, with each position corresponding to the row in the input data
     */
    public static int[] getOccurrences(String[][] data) {
        int[] result = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            assert data[i].length == 3;
            // count the number of occurrences
            result[i] = Utilities.Count(data[i][0], data[i][1], data[i][2]);
            // todo : this is a public method, so ensure the input is correct
        }
        return result;
    }

    /**
     * Print the meeting occurrence count in the console.
     *
     * @see #processFile(String)
     * @see #getOccurrences(String[][])
     *
     * @param data String list data of a .csv file
     * @param occ integer occurrences corresponding to the input data
     */
    private static void prettyPrint(String[][] data, int[] occ) {
        // lengths of the input lists should be equal
        assert data.length == occ.length;
        // do the pretty print
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i][0] + " to " + data[i][1] + " (" + data[i][2].substring(0,2) + "): " + occ[i]);
        }
    }
}
