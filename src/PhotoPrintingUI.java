import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhotoPrintingUI extends JFrame implements ActionListener {

    private JTextField textField;
    private JTextField costTextField;
    private JButton button;
    private JLabel label;

    private final Rectangle QUANTITY_TEXTFIELD_BOUNDS = new Rectangle(150, 150, 100, 25);
    private final Rectangle COST_TEXTFIELD_BOUNDS =     new Rectangle(150, 250, 100, 25);
    private final Rectangle CALCULATE_BUTTON_BOUNDS =   new Rectangle(150, 350, 100, 25);
    private final Rectangle TOTAL_LABEL_BOUNDS =        new Rectangle(150, 450, 100, 25);

    private Calculator priceCalculator = new Calculator();

    public PhotoPrintingUI() {
        this.setSize(400, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initFields();

        this.setLayout(null);
        this.setVisible(true);
    }

    private void initFields() {
        button = new JButton("Calculate");
        button.setBounds(CALCULATE_BUTTON_BOUNDS);
        button.addActionListener(this);

        textField = new JTextField();
        textField.setBounds(QUANTITY_TEXTFIELD_BOUNDS);

        costTextField = new JTextField();
        costTextField.setBounds(COST_TEXTFIELD_BOUNDS);

        label = new JLabel();
        label.setBounds(TOTAL_LABEL_BOUNDS);

        this.add(button);
        this.add(textField);
        this.add(costTextField);
        this.add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Double quantity = null;
        Double cost = null;

        try {
            quantity = Double.parseDouble(textField.getText());
            cost = Double.parseDouble(costTextField.getText());
        } catch (Exception exception) {
            //TODO - there was an error with the input fields
        }

        double result = priceCalculator.calculate(quantity, cost);
        label.setText(String.valueOf(result));
    }
}
