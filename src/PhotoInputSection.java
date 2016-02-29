import javax.swing.*;

public class PhotoInputSection extends JPanel {

    public enum Finish {GLOSSY, MATT}
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

    public ImageSize getImageSize() {
        return (ImageSize) sizeComboBox.getSelectedItem();
    }

    public Finish getFinish() {
        return (Finish) finishComboBox.getSelectedItem();
    }

    private void initComboBoxItems() {
        sizeComboBox.addItem(ImageSize.SMALL);
        sizeComboBox.addItem(ImageSize.MEDIUM);
        sizeComboBox.addItem(ImageSize.LARGE);

        finishComboBox.addItem(Finish.MATT);
        finishComboBox.addItem(Finish.GLOSSY);
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
