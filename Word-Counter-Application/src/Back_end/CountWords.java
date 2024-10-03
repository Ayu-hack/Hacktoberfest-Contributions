package Back_end;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CountWords {

    public static void displayNumOfWordsChars(JTextArea textArea, JTextField displayWordsTextField,
            JTextField displayCharTextField, JButton countButton) {

        String texts = textArea.getText();

        // check if it is empty or not
        if (texts.isBlank()) {
            JOptionPane.showMessageDialog(countButton, "Please insert your text...");

        } else {
            
            // split the text
            String words[] = texts.split("\\s");

            // print number of words
            int numofWords = words.length;
            displayWordsTextField.setText(String.valueOf(numofWords));

            // print number of characters
            int numofChar = texts.length();
            displayCharTextField.setText(String.valueOf(numofChar));
        }

    }

    public static void clearFields(JTextArea textArea, JTextField displayWordsTextField,
            JTextField displayCharTextField, JButton clearButton) {

        String texts = textArea.getText();

        // check if it is empty or not
        if (texts.isBlank()) {
            JOptionPane.showMessageDialog(clearButton, "This field is already empty!");
        } else {
            textArea.setText("");
            displayWordsTextField.setText("");
            displayCharTextField.setText("");
        }
    }
}
