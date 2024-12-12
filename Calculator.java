import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {
    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton, convButton;
    JPanel panel;
    Font myFont = new Font("Arial", Font.BOLD, 30);
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 600);
        frame.setLayout(null);

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        negButton = new JButton("(-)");
        convButton = new JButton("Convert");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        negButton.setBounds(50, 480, 100, 50);
        delButton.setBounds(150, 480, 100, 50);
        clrButton.setBounds(250, 480, 100, 50);
        convButton.setBounds(50, 430, 300, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(convButton);
        frame.add(textfield);
        frame.setVisible(true);

        // Add action listener for the conversion button
        convButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUnitConversionDialog();
            }
        });
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0 ; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton) {
            textfield.setText(textfield.getText().concat("."));
        }
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '+';
            textfield.setText("");
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '-';
            textfield.setText("");
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '*';
            textfield.setText("");
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '/';
            textfield.setText("");
        }
        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textfield.getText());

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            }
            textfield.setText(String.valueOf(result));
            num1 = result;
        }
        if (e.getSource() == clrButton) {
            textfield.setText("");
        }
        if (e.getSource() == delButton) {
            String string = textfield.getText();
            textfield.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                textfield.setText(textfield.getText() + string.charAt(i));
            }
        }
        if (e.getSource() == negButton) {
            double temp = Double.parseDouble(textfield.getText());
            temp *= -1;
            textfield.setText(String.valueOf(temp));
        }
    }

    private void showUnitConversionDialog() {
        JDialog dialog = new JDialog(frame, "Unit Conversion", true);
        dialog.setSize(500, 350);
        dialog.setLayout(null);
    
        JLabel inputLabel = new JLabel("Enter value:");
        inputLabel.setBounds(30, 30, 100, 30);
        JTextField inputField = new JTextField();
        inputField.setBounds(150, 30, 200, 30);
    
        JLabel fromLabel = new JLabel("From unit:");
        fromLabel.setBounds(30, 80, 100, 30);
        String[] units = {"Meters", "Kilometers", "Miles", "Yards", "Feet", "Inches", "Centimeters", "Millimeters"};
        JComboBox<String> fromUnitBox = new JComboBox<>(units);
        fromUnitBox.setBounds(150, 80, 200, 30);
    
        JLabel toLabel = new JLabel("To unit:");
        toLabel.setBounds(30, 130, 100, 30);
        JComboBox<String> toUnitBox = new JComboBox<>(units);
        toUnitBox.setBounds(150, 130, 200, 30);
    
        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(150, 180, 100, 30);
    
        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setBounds(30, 230, 400, 30);
    
        convertButton.addActionListener(e -> {
            String inputValue = inputField.getText();
            String fromUnit = (String) fromUnitBox.getSelectedItem();
            String toUnit = (String) toUnitBox.getSelectedItem();
    
            try {
                double value = Double.parseDouble(inputValue);
                double convertedValue = convertLength(value, fromUnit, toUnit);
                resultLabel.setText("Result: " + convertedValue + " " + toUnit);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid input! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        dialog.add(inputLabel);
        dialog.add(inputField);
        dialog.add(fromLabel);
        dialog.add(fromUnitBox);
        dialog.add(toLabel);
        dialog.add(toUnitBox);
        dialog.add(convertButton);
        dialog.add(resultLabel);
    
        dialog.setVisible(true);
    }
    
    private double convertLength(double value, String fromUnit, String toUnit) {
        double meters = switch (fromUnit) {
            case "Meters" -> value;
            case "Kilometers" -> value * 1000;
            case "Miles" -> value * 1609.34;
            case "Yards" -> value * 0.9144;
            case "Feet" -> value * 0.3048;
            case "Inches" -> value * 0.0254;
            case "Centimeters" -> value * 0.01;
            case "Millimeters" -> value * 0.001;
            default -> 0;
        };
    
        // Convert from meters to the target unit
        return switch (toUnit) {
            case "Meters" -> meters;
            case "Kilometers" -> meters / 1000;
            case "Miles" -> meters / 1609.34;
            case "Yards" -> meters / 0.9144;
            case "Feet" -> meters / 0.3048;
            case "Inches" -> meters / 0.0254;
            case "Centimeters" -> meters / 0.01;
            case "Millimeters" -> meters / 0.001;
            default -> 0;
        };
    }    
    
}