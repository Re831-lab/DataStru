import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class ChemicalFormulaGUI {

    // Purple Color Scheme - نفس الألوان الأصلية
    private static final Color MAIN_PURPLE = new Color(109, 40, 217);     // #6D28D9
    private static final Color LIGHT_PURPLE = new Color(233, 213, 255);   // #E9D5FF
    private static final Color DARK_PURPLE = new Color(76, 29, 149);      // #4C1D95
    private static final Color BACKGROUND_PURPLE = new Color(245, 243, 255); // #F5F3FF
    private static final Color HOVER_PURPLE = new Color(139, 69, 255);     // Lighter hover effect

    // Additional colors
    private static final Color SUCCESS_COLOR = new Color(34, 197, 94);
    private static final Color ERROR_COLOR = new Color(239, 68, 68);
    private static final Color CARD_BACKGROUND = Color.WHITE;
    private static final Color TEXT_PRIMARY = new Color(55, 65, 81);
    private static final Color TEXT_SECONDARY = new Color(107, 114, 128);
    private static final Color BORDER_COLOR = new Color(229, 231, 235);

    // Components
    private JFrame frame;
    private JTextField formulaField;
    private JTextArea resultArea;
    private JButton quickCheckBtn, clearBtn; // حذفت stepCheckBtn
    private JLabel statusLabel;

    public ChemicalFormulaGUI() {
        setupGUI();
    }

    private void setupGUI() {
        // Main Frame
        frame = new JFrame("Chemical Formula Checker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 550);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(BACKGROUND_PURPLE);

        // Layout
        frame.setLayout(new BorderLayout(0, 0));

        // Add panels
        frame.add(createHeaderPanel(), BorderLayout.NORTH);
        frame.add(createMainPanel(), BorderLayout.CENTER);
        frame.add(createFooterPanel(), BorderLayout.SOUTH);

        frame.setVisible(true);
        showWelcomeMessage();
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(DARK_PURPLE);
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Create gradient effect
        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gradient = new GradientPaint(0, 0, DARK_PURPLE, 0, getHeight(), MAIN_PURPLE);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        gradientPanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Chemical Formula Bracket Checker", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Validate balanced brackets in chemical formulas", JLabel.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(220, 220, 255));

        gradientPanel.add(title, BorderLayout.CENTER);
        gradientPanel.add(subtitle, BorderLayout.SOUTH);

        panel.add(gradientPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(0, 20));
        mainPanel.setBackground(BACKGROUND_PURPLE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 15, 25));

        // Input Panel
        JPanel inputPanel = createInputPanel();

        // Result Panel
        JPanel resultPanel = createResultPanel();

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(resultPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(CARD_BACKGROUND);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add subtle shadow effect
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 2, 2, new Color(0, 0, 0, 20)),
                        BorderFactory.createLineBorder(BORDER_COLOR, 1)
                ),
                BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));

        // Input Label
        JLabel inputLabel = new JLabel("Enter Chemical Formula:");
        inputLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        inputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputLabel.setForeground(TEXT_PRIMARY);

        // Input Field
        formulaField = new JTextField();
        formulaField.setFont(new Font("Courier New", Font.PLAIN, 18));
        formulaField.setHorizontalAlignment(JTextField.CENTER);
        formulaField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(MAIN_PURPLE, 2),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        formulaField.setMaximumSize(new Dimension(350, 45));
        formulaField.addActionListener(e -> quickCheck());

        // Add focus listener for better UX
        formulaField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                formulaField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(HOVER_PURPLE, 2),
                        BorderFactory.createEmptyBorder(12, 15, 12, 15)
                ));
            }

            @Override
            public void focusLost(FocusEvent e) {
                formulaField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(MAIN_PURPLE, 2),
                        BorderFactory.createEmptyBorder(12, 15, 12, 15)
                ));
            }
        });

        // Buttons Panel - حذفت stepCheckBtn
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonPanel.setBackground(CARD_BACKGROUND);

        quickCheckBtn = createStyledButton("Quick Check", MAIN_PURPLE);
        quickCheckBtn.addActionListener(e -> quickCheck());

        clearBtn = createStyledButton("Clear", TEXT_SECONDARY);
        clearBtn.addActionListener(e -> clearAll());

        buttonPanel.add(quickCheckBtn);
        buttonPanel.add(clearBtn);

        // Examples Panel
        JPanel examplePanel = createExamplePanel();

        panel.add(inputLabel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(formulaField);
        panel.add(Box.createVerticalStrut(20));
        panel.add(buttonPanel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(examplePanel);

        return panel;
    }

    private JPanel createExamplePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
        panel.setBackground(LIGHT_PURPLE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(MAIN_PURPLE, 1),
                        "Try These Examples",
                        0, 0,
                        new Font("Segoe UI", Font.BOLD, 12),
                        MAIN_PURPLE
                ),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        String[] examples = {"Ca(OH)2", "Al2[SO4]3", "{[Ca(OH)2]3}2", "H2SO4(aq"};

        for (String example : examples) {
            JButton btn = new JButton(example);
            btn.setFont(new Font("Courier New", Font.BOLD, 12));
            btn.setBackground(CARD_BACKGROUND);
            btn.setForeground(MAIN_PURPLE);
            btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(MAIN_PURPLE, 1),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            btn.addActionListener(e -> formulaField.setText(example));
            btn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(MAIN_PURPLE);
                    btn.setForeground(Color.WHITE);
                }
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(CARD_BACKGROUND);
                    btn.setForeground(MAIN_PURPLE);
                }
            });
            panel.add(btn);
        }

        return panel;
    }

    private JPanel createResultPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CARD_BACKGROUND);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 2, 2, new Color(0, 0, 0, 20)),
                        BorderFactory.createLineBorder(BORDER_COLOR, 1)
                ),
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));

        JLabel titleLabel = new JLabel("Results & Analysis", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(TEXT_PRIMARY);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        resultArea = new JTextArea();
        resultArea.setFont(new Font("Courier New", Font.PLAIN, 13));
        resultArea.setEditable(false);
        resultArea.setBackground(new Color(250, 250, 255));
        resultArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(LIGHT_PURPLE, 1));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Custom scrollbar
        scrollPane.getVerticalScrollBar().setBackground(LIGHT_PURPLE);
        scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = MAIN_PURPLE;
                this.trackColor = LIGHT_PURPLE;
            }
        });

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createFooterPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gradient = new GradientPaint(0, 0, MAIN_PURPLE, 0, getHeight(), DARK_PURPLE);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        statusLabel = new JLabel("Ready to check formulas! Enter a formula above.");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        statusLabel.setForeground(Color.WHITE);

        panel.add(statusLabel);
        return panel;
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            private Color originalColor = color;

            public void mouseEntered(MouseEvent e) {
                if (color.equals(MAIN_PURPLE)) {
                    button.setBackground(HOVER_PURPLE);
                } else {
                    button.setBackground(color.brighter());
                }
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(originalColor);
            }

            public void mousePressed(MouseEvent e) {
                button.setBackground(color.darker());
            }

            public void mouseReleased(MouseEvent e) {
                if (color.equals(MAIN_PURPLE)) {
                    button.setBackground(HOVER_PURPLE);
                } else {
                    button.setBackground(color.brighter());
                }
            }
        });

        return button;
    }

    // حذفت stepByStepCheck() وخليت quickCheck بس
    private void quickCheck() {
        String formula = formulaField.getText().trim();

        if (formula.isEmpty()) {
            showMessage("Please enter a chemical formula!");
            return;
        }

        boolean isValid = isValidFormula(formula);

        resultArea.setText("QUICK CHECK RESULT\n");
        resultArea.append("===================\n\n");
        resultArea.append("Formula: " + formula + "\n");
        resultArea.append("Status: " + (isValid ? "VALID" : "INVALID") + "\n\n");

        if (isValid) {
            resultArea.append("Success! All brackets are properly balanced.\n");
            resultArea.append("This formula can be safely processed.\n");
            updateStatus("Formula is valid!", true);
        } else {
            resultArea.append("Issues detected. Please check for:\n");
            resultArea.append("• Each opening bracket has a closing bracket\n");
            resultArea.append("• Brackets are of the same type: () [] {}\n");
            resultArea.append("• Brackets are in correct order\n");
            updateStatus("Formula has errors!", false);
        }
    }

    private void clearAll() {
        formulaField.setText("");
        showWelcomeMessage();
        updateStatus("Cleared - Ready for new formula!", true);
    }

    private void showWelcomeMessage() {
        resultArea.setText("Welcome to Chemical Formula Bracket Checker\n");
        resultArea.append("==========================================\n\n");
        resultArea.append("Instructions:\n");
        resultArea.append("1. Enter a chemical formula in the field above\n");
        resultArea.append("2. Click 'Quick Check' for instant validation\n");
        resultArea.append("3. Try the example buttons for quick testing\n\n");
        resultArea.append("What we validate:\n");
        resultArea.append("• Balanced parentheses: ()\n");
        resultArea.append("• Balanced square brackets: []\n");
        resultArea.append("• Balanced curly braces: {}\n");
        resultArea.append("• Proper nesting order\n\n");
        resultArea.append("Valid Examples:\n");
        resultArea.append("Ca(OH)2, Al2[SO4]3, {[Ca(OH)2]3}2\n\n");
        resultArea.append("Invalid Examples:\n");
        resultArea.append("H2SO4(aq, Ca(OH]2, )Ca(OH2\n\n");
        resultArea.append("Ready to start! Enter your formula above.");
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Notice", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateStatus(String message, boolean isPositive) {
        statusLabel.setText(message);
    }

    // استخدام Java Stack بدل الكلاس المخصص
    public static boolean isValidFormula(String formula) {
        Stack<Character> stack = new Stack<>();

        for (char c : formula.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char last = stack.pop();
                if (!matches(last, c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }


}