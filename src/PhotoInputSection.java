import javax.swing.*;

public class PhotoInputSection extends JPanel {

    public static enum Finish {GLOSSY, MATTE}
    public enum ImageSize {SMALL, MEDIUM, LARGE}

    private JPanel panel1;
    private JTextField quantityTextField;
    private JComboBox sizeComboBox;
    private JComboBox finishComboBox;

    public PhotoInputSection() {
        initComboBoxItems();

        panel1.setVisible(true);
    }

    public JPanel getRootPanel() {
        return panel1;
    }

    public int getCount() {
        return Integer.parseInt(quantityTextField.getText());
    }

    public String getImageSize() {
        return (String) sizeComboBox.getSelectedItem();
    }

    public Finish getFinish() {
        return (Finish) finishComboBox.getSelectedItem();
    }

    private void initComboBoxItems() {
        sizeComboBox.addItem("4x6");
        sizeComboBox.addItem("5x7");
        sizeComboBox.addItem("8x10");

        finishComboBox.addItem(Finish.GLOSSY);
        finishComboBox.addItem(Finish.MATTE);
    }

    public boolean entriesValid() {
        String quantityText = quantityTextField.getText();
        int quantity;

        try {
           quantity = Integer.parseInt(quantityText);
        } catch(Exception e) {
            return false;
        }

        if (quantity > 100 || quantity < 1) {
            return false;
        }

        return true;
    }
}
