import javax.swing.*;

public class InputSection extends JPanel {

    private final static String[] MATTE_OPTIONS = {"Matte", "Glossy"};
    private final static String[] SIZE_OPTIONS = {"4 X 6", "6 x 8"};

    private JTextField quantityTextField;
    private JComboBox matteComboBox;
    private JComboBox sizeComboBox;

    public InputSection() {
        this.setSize(400, 100);
        this.setVisible(true);

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS); // top to bottom
        this.setLayout(boxLayout);

        quantityTextField = new JTextField();
        quantityTextField.setSize(100, 25);
        this.add(quantityTextField);

        matteComboBox = new JComboBox(MATTE_OPTIONS);
        matteComboBox.setSelectedIndex(0);
        this.add(matteComboBox);

        sizeComboBox = new JComboBox(SIZE_OPTIONS);
        sizeComboBox.setSelectedIndex(0);
        this.add(sizeComboBox);
    }

}
