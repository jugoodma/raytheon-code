package solution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

/**
 * Occurrences class actually initializes the GUI and places all GUI components.
 */
class Occurrences extends JFrame {
    private JTextArea start;
    private JTextArea end;
    private JComboBox<String> day;
    private JLabel output; // will display the number of meeting occurrences

    /**
     * Creates the "count occurrences" interface.
     */
    Occurrences() {
        // start up the visuals
        initComponents();
        // do other stuff

    }

    /**
     * Places all the components into the JFrame GUI.
     */
    private void initComponents() {
        /*
        interface sketch:

        + Count Meeting Occurrences.

            Start day: [date]
            end day: [date]
            Day of week: [(v) S/M/Tu/W/...]

            Import calendar? (*) yes () no

            [calculate!]    Meeting occurrences: <###>
                                                       +

        uh yeah
         */
        // build the interface

        // don't look at this code

        GridBagLayout grid = new GridBagLayout();
        this.setLayout(grid);
        // remember to reset constraints per added component!!
        GridBagConstraints gbc = new GridBagConstraints(); // gbc is fall-through, like a switch statement
        gbc.insets = new Insets(5,25,5,25);
        // start date
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.2;
        this.add(new JLabel("Start date [yyyy-MM-dd]: "), gbc);
        //
        gbc.gridx = 1;
        gbc.weightx = 0.5;
        start = new JTextArea("2019-12-31");
        this.add(start, gbc);
        //
        gbc.weightx = 0; // reset
        // end date
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(new JLabel("End date [yyyy-MM-dd]: "), gbc);
        //
        gbc.gridx = 1;
        end = new JTextArea("2020-01-01");
        this.add(end, gbc);
        // day of the week
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(new JLabel("Day of the week: "), gbc);
        //
        gbc.gridx = 1;
        String[] items = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        day = new JComboBox<>(items);
        this.add(day, gbc);
        // import calendar
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(new JLabel("Import calendar?"), gbc);
        //
        gbc.gridx = 1;
        JRadioButton yes = new JRadioButton("Yes", true);
        JRadioButton no = new JRadioButton("No", false);
        ButtonGroup cal = new ButtonGroup(); // needed?
        cal.add(yes);
        cal.add(no);
        gbc.insets = new Insets(5,25,0,25);
        this.add(yes, gbc);
        gbc.gridy = 5;
        gbc.insets = new Insets(0,25,5,25);
        this.add(no, gbc);
        //
        gbc.insets = new Insets(5,25,20,25);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.NONE;
        JButton b = new JButton("Calculate!");
        b.addActionListener(this::calculate);
        this.add(b, gbc);
        //
        gbc.insets = new Insets(5,25,5,25);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(new JLabel("Meeting occurrences:"), gbc);
        //
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.NONE;
        output = new JLabel("-1");
        this.add(output, gbc);

        this.setSize(650, 300);
        this.setPreferredSize(this.getSize());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBackground(new Color(204, 255, 255));
        // this.setBounds(new Rectangle(10, 10, 0, 0));
        this.setTitle("Count Meeting Occurrences");
        // this.setResizable(false);
    }

    // some display helpers

    /**
     * Method that runs onClick of the 'calculate' button.
     *
     * @param e (unused for this method) contains the ActionEvent for when the calculate button is clicked
     */
    private void calculate(ActionEvent e) {
        System.out.println(e); // ?
        // the (String) cast here should be fine
        output.setText(Utilities.Count(start.getText(), end.getText(), (String) day.getSelectedItem()) + "");
    }
}