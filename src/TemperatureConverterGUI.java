import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterGUI extends JFrame {
    private JTextField inputField;
    private JComboBox<String> unitComboBox;
    private JTextArea resultArea;

    public TemperatureConverterGUI() {
        setTitle("Temperature Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel label = new JLabel("Enter temperature:");
        label.setBounds(20, 20, 150, 20);
        add(label);

        inputField = new JTextField();
        inputField.setBounds(180, 20, 150, 20);
        add(inputField);

        JLabel unitLabel = new JLabel("Select unit:");
        unitLabel.setBounds(20, 50, 150, 20);
        add(unitLabel);

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        unitComboBox = new JComboBox<>(units);
        unitComboBox.setBounds(180, 50, 150, 20);
        add(unitComboBox);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(180, 80, 150, 30);
        add(convertButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setBounds(20, 120, 350, 100);
        add(resultArea);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        try {
            double temperature = Double.parseDouble(inputField.getText());
            String selectedUnit = (String) unitComboBox.getSelectedItem();

            double convertedCelsius = 0;
            double convertedFahrenheit = 0;
            double convertedKelvin = 0;

            switch (selectedUnit.toLowerCase()) {
                case "celsius":
                    convertedFahrenheit = (temperature * 9 / 5) + 32;
                    convertedKelvin = temperature + 273.15;
                    break;
                case "fahrenheit":
                    convertedCelsius = (temperature - 32) * 5 / 9;
                    convertedKelvin = (temperature + 459.67) * 5 / 9;
                    break;
                case "kelvin":
                    convertedCelsius = temperature - 273.15;
                    convertedFahrenheit = temperature * 9 / 5 - 459.67;
                    break;
            }

            resultArea.setText(String.format("%.2f %s is equal to %.2f Fahrenheit and %.2f Kelvin.",
                    temperature, selectedUnit, convertedFahrenheit, convertedKelvin));

        } catch (NumberFormatException ex) {
            resultArea.setText("Invalid input. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TemperatureConverterGUI().setVisible(true);
            }
        });
    }
}
