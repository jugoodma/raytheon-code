package tests;

import org.junit.Test;
import solution.CLIDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * TestSuite class contains a few JUnit 4 tests cases to test the back-end of the Utilities class. 4 test-cases are
 * already provided in the proper testing folder.
 *
 * @see solution
 */
public class TestSuite {
    private static final String testInputLocation = "test-inputs/";
    private static final String testOutputLocation = "test-outputs/";

    /**
     * A method to
     * @param fName the .csv file name we are testing
     * @return the list of integers (in the same order) given in fName
     */
    private static int[] outputFileToOcc(String fName) {
        int[] cmp;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(testOutputLocation + fName));
            String[] line = br.readLine().split(",");
            cmp = new int[line.length];
            for (int i = 0; i < line.length; i++) {
                cmp[i] = Integer.valueOf(line[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
            cmp = new int[0];
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return cmp;
    }

    /**
     * Tests a single .csv file given in <code>/test-inputs</code> against the corresponding expected-output in the
     * same-name file in <code>/test-outputs</code>.
     */
    @Test
    public void testOne() {
        // run individual test cases here
        String fName = "test04.csv"; // put the test file you want to run here
        File f = new File(testInputLocation + fName);
        int[] occ = CLIDriver.getOccurrences(CLIDriver.processFile(f.getAbsolutePath()));
        int[] cmp = outputFileToOcc(fName);
        assert occ.length == cmp.length;
        // do the comparison
        assertArrayEquals(cmp, occ);
    }

    /**
     * Tests every file in <code>/test-inputs</code> against the expected-output in <code>/test-outputs</code>.
     */
    @Test
    public void testAll() {
        File idir = new File(testInputLocation);
        File[] testFiles = idir.listFiles();
        if (testFiles == null) {
            fail("implement some test cases, stupid");
        }
        File odir = new File(testOutputLocation);
        File[] compFiles = odir.listFiles();
        if (compFiles == null) {
            fail("need to compare to something, stupid");
        }
        assert testFiles.length == compFiles.length;
        Arrays.sort(testFiles);
        Arrays.sort(compFiles);
        for (int i = 0; i < testFiles.length; i++) {
            assertEquals(testFiles[i].getName(), compFiles[i].getName());
        }
        for (File test : testFiles) {
            System.out.println(test.getName());
            int[] occ = CLIDriver.getOccurrences(CLIDriver.processFile(test.getAbsolutePath()));
            int[] cmp = outputFileToOcc(test.getName());
            assertArrayEquals(cmp, occ);
            CLIDriver.processFile(test.getAbsolutePath());
        }
    }
}
