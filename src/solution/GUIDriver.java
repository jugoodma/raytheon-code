package solution;

/**
 * GUIDriver class. Contains main method for spawning and showing the GUI.
 */
public class GUIDriver {

    /**
     * Run meeting occurrences with a GUI
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // keep it responsive
        // https://stackoverflow.com/questions/22534356/java-awt-eventqueue-invokelater-explained
        java.awt.EventQueue.invokeLater(() -> new Occurrences().setVisible(true));
    }
}