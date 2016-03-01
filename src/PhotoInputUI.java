import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PhotoInputUI extends JFrame implements ActionListener {

    private Calculator calculator;
    private SingleOptionCalculator singleOptionCalculator;
    private MultipleOptionsCalculator multipleOptionsCalculator;

    private JPanel rootPanel;
    private JButton addSectionButton;
    private JButton calculateButton;
    private JPanel sectionsPanel;
    private JButton resetButton;
    private JTextField couponTextField;
    private JLabel couponLabel;
    private JComboBox deliveryComboBox;
    private JScrollPane scrollPanel;

    private List<PhotoInputSection> sections;

    public PhotoInputUI() {
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sections = new ArrayList<>();

        addSectionButton.setActionCommand("ADD_SECTION");
        addSectionButton.addActionListener(this);

        resetButton.setActionCommand("RESET");
        resetButton.addActionListener(this);

        calculateButton.setActionCommand("CALCULATE");
        calculateButton.addActionListener(this);

        calculator = new Calculator();
        singleOptionCalculator = new SingleOptionCalculator();
        multipleOptionsCalculator = new MultipleOptionsCalculator();

        deliveryComboBox.addItem("NEXT DAY");
        deliveryComboBox.addItem("1-HOUR");

        initSections();
        setCouponVisibilityTo(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ADD_SECTION")) {
            if (allSectionsValid() && quantityTotalUnder100()) {
                addSection();
            }
        } else if (e.getActionCommand().equals("RESET")) {
            initSections();
        } else if (e.getActionCommand().equals("CALCULATE")) {
            calculate();
        }

        setCouponVisibilityTo(sections.size() < 2);

        refreshScreen();
    }

    private void calculate() {
        for (PhotoInputSection section : sections) {
            if (!section.entriesValid()) {
                return;
            }
        }

        if (sections.size() > 1 && isSectionsDifferent()) {
            double total = multipleOptionsCalculator.calculateMode2(sections, (String) deliveryComboBox.getSelectedItem());
            showTotal(total);
        } else {
            double total = singleOptionCalculator.calculate(sections, (String) deliveryComboBox.getSelectedItem(), couponTextField.getText());
            showTotal(total);
        }
    }

    private void showTotal(double total) {
        String result = String.format("%.2f", total);
        JOptionPane.showMessageDialog(this.getRootPane(), "Your total is: $" + result);
    }

    private void addSection() {
        PhotoInputSection section = new PhotoInputSection();
        this.sections.add(section);
        this.sectionsPanel.add(section.getRootPanel());
    }

    private void initSections() {
        this.sections.clear();
        this.sectionsPanel.removeAll();

        addSection();
    }

    private void refreshScreen() {
        int width = 390;
        int height = sections.size() * 150;

        this.sectionsPanel.setPreferredSize(new Dimension(width, height));

        this.revalidate();
        this.repaint();
    }

    private void setCouponVisibilityTo(boolean visible) {
        this.couponLabel.setVisible(visible);
        this.couponTextField.setVisible(visible);
    }

    private boolean allSectionsValid() {
        for (PhotoInputSection section : sections) {
            if (!section.entriesValid()) {
                return false;
            }
        }

        return true;
    }

    private boolean quantityTotalUnder100() {
        int quantityTotal = 0;
        for (PhotoInputSection section : sections) {
            quantityTotal+= section.getCount();
            if (quantityTotal >= 100) {
                return false;
            }
        }

        return true;
    }

    private boolean isSectionsDifferent() {
        if (sections.size() == 1) {
            return false;
        }

        PhotoInputSection firstSection = sections.get(0);

        for (PhotoInputSection section : sections) {
            if (!firstSection.getFinish().equals(section.getFinish()) ||
                    !firstSection.getImageSize().equals(section.getImageSize())) {
                return true;
            }
        }

        return false;
    }
}
