package Gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Back_end.CountWords;

public class Window extends JFrame {

    // create the text color of GUI
    final Color TEXT_COLOR = Color.decode("#170F5C");

    public Window() {
        // set the title of the window
        super("Word Counter");

        // size of the GUI
        setSize(800, 400);

        // set layout null to place components in the page
        setLayout(null);

        // set GUI in the center of the screen
        setLocationRelativeTo(null);

        // prevent any resize of our GUI
        setResizable(false);

        // configure GUI to end the program's process once it has been closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // create the background color of GUI
        final Color PRIMARY_COLOR = Color.decode("#D2CEEF");
        getContentPane().setBackground(PRIMARY_COLOR);

        addGuiComponents();
    }

    private void addGuiComponents() {
        // set the logo
        ImageIcon icon = new ImageIcon(getClass().getResource("/Assets/computer.png"));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH); // Adjust width and height of the image
        icon = new ImageIcon(scaledImg);
        JLabel logo = new JLabel(icon);
        logo.setBounds(0, 0, 400, 400);

        // set the title
        JLabel titleLable = new JLabel("Word Counter");
        titleLable.setBounds(510, 50, 180, 25);
        titleLable.setForeground(TEXT_COLOR);
        titleLable.setFont(new Font("Tahoma", Font.BOLD, 25));
        titleLable.setHorizontalAlignment(SwingConstants.CENTER);

        // set text area
        JTextArea textArea = new JTextArea();
        textArea.setBounds(430, 100, 325, 110);
        textArea.setForeground(TEXT_COLOR);
        textArea.setFont(new Font("Arial", Font.PLAIN, 12));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(5, 5, 5, 5));

        // set scroll pane for the text area
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setBounds(430, 100, 325, 120);

        // set the COUNT button
        JButton countButton = new JButton("Count");
        countButton.setBounds(675, 230, 80, 25);
        countButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        countButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        countButton.setHorizontalAlignment(SwingConstants.CENTER);

        // set the CLEAR button
        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(430, 230, 80, 25);
        clearButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        clearButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clearButton.setHorizontalAlignment(SwingConstants.CENTER);

        // set the display words
        JLabel WordsJLable = new JLabel("Total Words:");
        WordsJLable.setBounds(430, 270, 90, 25);
        WordsJLable.setForeground(TEXT_COLOR);
        WordsJLable.setFont(new Font("Tahoma", Font.PLAIN, 15));

        // set the text box to display total number of words
        JTextField displayWordsTextField = new JTextField();
        displayWordsTextField.setBounds(520, 270, 70, 25);
        displayWordsTextField.setForeground(TEXT_COLOR);
        displayWordsTextField.setFont(new Font("Arial", Font.PLAIN, 12));

        // set the display characters
        JLabel charJLable = new JLabel("Characters:");
        charJLable.setBounds(430, 305, 90, 25);
        charJLable.setForeground(TEXT_COLOR);
        charJLable.setFont(new Font("Tahoma", Font.PLAIN, 15));

        // set the text box to display total number of characters
        JTextField displayCharTextField = new JTextField();
        displayCharTextField.setBounds(520, 305, 70, 25);
        displayCharTextField.setForeground(TEXT_COLOR);
        displayCharTextField.setFont(new Font("Arial", Font.PLAIN, 12));

        // set the text fields of words and character as disable
        displayWordsTextField.setEditable(false);
        displayCharTextField.setEditable(false);

        // add action listener for COUNT button
        countButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                CountWords.displayNumOfWordsChars(textArea, displayWordsTextField, displayCharTextField, countButton);
            }

        });

        // add action listener for CLEAR button
        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // method to clear area and text fields
                CountWords.clearFields(textArea, displayWordsTextField, displayCharTextField, clearButton);

            }

        });

        // add components
        add(logo);
        add(titleLable);
        add(scroll);
        add(countButton);
        add(clearButton);
        add(WordsJLable);
        add(displayWordsTextField);
        add(charJLable);
        add(displayCharTextField);
    }

}