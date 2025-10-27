import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    private JTextField equationField, lengthField, widthField, heightField, holesField;
    private JTextArea resultArea;
    private ExpressionConverter converter;
    private Calculator calculator;

    // ألوان التصميم
    private static final Color DARK_GRAY = new Color(45, 45, 48);
    private static final Color DARKER_GRAY = new Color(35, 35, 38);
    private static final Color ORANGE = new Color(255, 140, 0);
    private static final Color ORANGE_HOVER = new Color(255, 165, 0);
    private static final Color WHITE_TEXT = Color.WHITE;
    private static final Color LIGHT_GRAY = new Color(200, 200, 200);

    public GUI() {
        setTitle("EngineerCalc - Concrete Calculator");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // تطبيق الثيم الداكن
        getContentPane().setBackground(DARK_GRAY);

        converter = new ExpressionConverter();
        calculator = new Calculator();

        // إنشاء الألواح الرئيسية
        createHeaderPanel();
        createInputPanel();
        createResultPanel();
        createButtonPanel();

        setLocationRelativeTo(null); // توسيط النافذة
        setVisible(true);
    }

    private void createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(DARKER_GRAY);
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("EngineerCalc - حاسبة الخرسانة", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(WHITE_TEXT);

        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);
    }

    private void createInputPanel() {
        JPanel mainInputPanel = new JPanel(new BorderLayout(10, 10));
        mainInputPanel.setBackground(DARK_GRAY);
        mainInputPanel.setBorder(new EmptyBorder(20, 20, 10, 20));

        // لوحة المعادلة
        JPanel equationPanel = new JPanel(new BorderLayout(5, 5));
        equationPanel.setBackground(DARK_GRAY);

        JLabel equationLabel = new JLabel("أدخل المعادلة (Infix):");
        equationLabel.setForeground(WHITE_TEXT);
        equationLabel.setFont(new Font("Arial", Font.BOLD, 12));

        equationField = createStyledTextField();
        equationField.setFont(new Font("Monospace", Font.PLAIN, 14));

        equationPanel.add(equationLabel, BorderLayout.NORTH);
        equationPanel.add(equationField, BorderLayout.CENTER);

        // لوحة المتغيرات
        JPanel variablesPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        variablesPanel.setBackground(DARK_GRAY);
        variablesPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(ORANGE, 1),
                "المتغيرات",
                0, 0,
                new Font("Arial", Font.BOLD, 12),
                WHITE_TEXT
        ));

        // إنشاء الحقول مع التسميات
        lengthField = createStyledTextField();
        widthField = createStyledTextField();
        heightField = createStyledTextField();
        holesField = createStyledTextField();

        variablesPanel.add(createStyledLabel("الطول (Length):"));
        variablesPanel.add(createStyledLabel("العرض (Width):"));
        variablesPanel.add(createStyledLabel("الارتفاع (Height):"));
        variablesPanel.add(createStyledLabel("حجم الفراغات (Holes):"));

        variablesPanel.add(lengthField);
        variablesPanel.add(widthField);
        variablesPanel.add(heightField);
        variablesPanel.add(holesField);

        mainInputPanel.add(equationPanel, BorderLayout.NORTH);
        mainInputPanel.add(variablesPanel, BorderLayout.CENTER);

        add(mainInputPanel, BorderLayout.CENTER);
    }

    private void createResultPanel() {
        JPanel resultPanel = new JPanel(new BorderLayout(5, 5));
        resultPanel.setBackground(DARK_GRAY);
        resultPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel resultLabel = new JLabel("النتائج:");
        resultLabel.setForeground(WHITE_TEXT);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 12));

        resultArea = new JTextArea(8, 50);
        resultArea.setEditable(false);
        resultArea.setBackground(DARKER_GRAY);
        resultArea.setForeground(LIGHT_GRAY);
        resultArea.setFont(new Font("Monospace", Font.PLAIN, 12));
        resultArea.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(ORANGE, 1));

        resultPanel.add(resultLabel, BorderLayout.NORTH);
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        add(resultPanel, BorderLayout.SOUTH);
    }

    private void createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(DARK_GRAY);
        buttonPanel.setBorder(new EmptyBorder(10, 20, 20, 20));

        JButton calculateButton = createStyledButton("احسب", ORANGE);
        JButton clearButton = createStyledButton("مسح", new Color(220, 53, 69));
        JButton exampleButton = createStyledButton("مثال", new Color(40, 167, 69));

        calculateButton.addActionListener(e -> calculateResult());
        clearButton.addActionListener(e -> clearFields());
        exampleButton.addActionListener(e -> loadExample());

        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exampleButton);

        // إضافة لوحة الأزرار للوحة الرئيسية
        JPanel centerPanel = (JPanel) getContentPane().getComponent(1);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setBackground(DARKER_GRAY);
        field.setForeground(WHITE_TEXT);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ORANGE, 1),
                new EmptyBorder(5, 8, 5, 8)
        ));
        field.setFont(new Font("Arial", Font.PLAIN, 12));
        return field;
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(WHITE_TEXT);
        label.setFont(new Font("Arial", Font.PLAIN, 11));
        return label;
    }

    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // تأثير الهوفر
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(backgroundColor.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(backgroundColor);
            }
        });

        return button;
    }

    private void calculateResult() {
        try {
            String infix = equationField.getText().trim();
            if (infix.isEmpty()) {
                showError("يرجى إدخال معادلة");
                return;
            }

            String postfix = converter.infixToPostfix(infix);
            String prefix = converter.infixToPrefix(infix);

            double length = parseDouble(lengthField.getText(), "الطول");
            double width = parseDouble(widthField.getText(), "العرض");
            double height = parseDouble(heightField.getText(), "الارتفاع");
            double holes = parseDouble(holesField.getText(), "حجم الفراغات");

            double result = calculator.evaluatePostfix(postfix, length, width, height, holes);

            StringBuilder output = new StringBuilder();
            output.append("════════════════════════════════════════\n");
            output.append("                   النتائج\n");
            output.append("════════════════════════════════════════\n\n");
            output.append("المعادلة الأصلية (Infix):\n").append(infix).append("\n\n");
            output.append("المعادلة اللاحقة (Postfix):\n").append(postfix).append("\n\n");
            output.append("المعادلة السابقة (Prefix):\n").append(prefix).append("\n\n");
            output.append("════════════════════════════════════════\n");
            output.append("المتغيرات المستخدمة:\n");
            output.append("الطول (Length) = ").append(length).append("\n");
            output.append("العرض (Width) = ").append(width).append("\n");
            output.append("الارتفاع (Height) = ").append(height).append("\n");
            output.append("حجم الفراغات (Holes_Volume) = ").append(holes).append("\n\n");
            output.append("════════════════════════════════════════\n");
            output.append("النتيجة النهائية: ").append(String.format("%.4f", result));
            output.append("\n════════════════════════════════════════");

            resultArea.setText(output.toString());
            resultArea.setCaretPosition(0);

        } catch (NumberFormatException ex) {
            showError("خطأ في تنسيق الأرقام: " + ex.getMessage());
        } catch (Exception ex) {
            showError("خطأ في المعادلة: " + ex.getMessage());
        }
    }

    private double parseDouble(String text, String fieldName) throws NumberFormatException {
        if (text.trim().isEmpty()) {
            throw new NumberFormatException("حقل " + fieldName + " فارغ");
        }
        return Double.parseDouble(text.trim());
    }

    private void showError(String message) {
        resultArea.setText("❌ خطأ: " + message);
        resultArea.setForeground(new Color(220, 53, 69));

        // إرجاع اللون الأصلي بعد 3 ثوانٍ
        Timer timer = new Timer(3000, e -> resultArea.setForeground(LIGHT_GRAY));
        timer.setRepeats(false);
        timer.start();
    }

    private void clearFields() {
        equationField.setText("");
        lengthField.setText("");
        widthField.setText("");
        heightField.setText("");
        holesField.setText("");
        resultArea.setText("");
        resultArea.setForeground(LIGHT_GRAY);
        equationField.requestFocus();
    }

    private void loadExample() {
        equationField.setText("Length * Width * Height - Holes_Volume");
        lengthField.setText("10");
        widthField.setText("5");
        heightField.setText("3");
        holesField.setText("2");

        // حساب المثال تلقائياً
        calculateResult();
    }
}