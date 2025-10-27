import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class DictionaryGUI {
    private JFrame frame;
    private JTextField wordField;
    private JTextArea meaningArea, displayArea;
    private DictionaryManager manager;

    // ألوان الكتاب التقليدي
    private final Color BOOK_BG = new Color(245, 235, 215);
    private final Color PAPER_COLOR = new Color(255, 248, 230);
    private final Color INK_COLOR = new Color(101, 67, 33);
    private final Color BORDER_COLOR = new Color(139, 90, 43);
    private final Color TITLE_COLOR = new Color(85, 51, 15);

    public DictionaryGUI() {
        manager = new DictionaryManager();

        frame = new JFrame("Dictionary");
        frame.setSize(750, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(15, 15));
        frame.getContentPane().setBackground(BOOK_BG);

        // العنوان بتصميم كتاب قديم
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(BOOK_BG);
        titlePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 3, 0, BORDER_COLOR),
                BorderFactory.createEmptyBorder(15, 10, 15, 10)
        ));

        JLabel title = new JLabel("DICTIONARY", SwingConstants.CENTER);
        title.setFont(new Font("Georgia", Font.BOLD, 32));
        title.setForeground(TITLE_COLOR);
        titlePanel.add(title);
        frame.add(titlePanel, BorderLayout.NORTH);

        // الجزء الأيسر - صفحة الإدخال
        JPanel leftPage = new JPanel(new BorderLayout(10, 10));
        leftPage.setBackground(PAPER_COLOR);
        leftPage.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 3),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // لوحة الإدخال
        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 10, 15));
        inputPanel.setBackground(PAPER_COLOR);

        // حقل الكلمة
        JPanel wordPanel = new JPanel(new BorderLayout(5, 5));
        wordPanel.setBackground(PAPER_COLOR);
        JLabel wordLabel = new JLabel("Word:");
        wordLabel.setFont(new Font("Georgia", Font.BOLD, 16));
        wordLabel.setForeground(INK_COLOR);
        wordField = new JTextField();
        wordField.setFont(new Font("Serif", Font.PLAIN, 15));
        wordField.setBackground(new Color(255, 253, 245));
        wordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        wordPanel.add(wordLabel, BorderLayout.NORTH);
        wordPanel.add(wordField, BorderLayout.CENTER);

        // حقل المعنى
        JPanel meaningPanel = new JPanel(new BorderLayout(5, 5));
        meaningPanel.setBackground(PAPER_COLOR);
        JLabel meaningLabel = new JLabel("Meaning:");
        meaningLabel.setFont(new Font("Georgia", Font.BOLD, 16));
        meaningLabel.setForeground(INK_COLOR);
        meaningArea = new JTextArea(4, 20);
        meaningArea.setFont(new Font("Serif", Font.PLAIN, 15));
        meaningArea.setLineWrap(true);
        meaningArea.setWrapStyleWord(true);
        meaningArea.setBackground(new Color(255, 253, 245));
        meaningArea.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
        JScrollPane meaningScroll = new JScrollPane(meaningArea);
        meaningScroll.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        meaningPanel.add(meaningLabel, BorderLayout.NORTH);
        meaningPanel.add(meaningScroll, BorderLayout.CENTER);

        inputPanel.add(wordPanel);
        inputPanel.add(meaningPanel);

        // أزرار التحكم
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBackground(PAPER_COLOR);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JButton addBtn = createBookButton("Add");
        JButton searchBtn = createBookButton("Search");
        JButton deleteBtn = createBookButton("Delete");
        JButton showAllBtn = createBookButton("Show All");

        buttonPanel.add(addBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(showAllBtn);

        leftPage.add(inputPanel, BorderLayout.CENTER);
        leftPage.add(buttonPanel, BorderLayout.SOUTH);

        // الجزء الأيمن - صفحة العرض
        JPanel rightPage = new JPanel(new BorderLayout());
        rightPage.setBackground(PAPER_COLOR);
        rightPage.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 3),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel displayLabel = new JLabel("Contents:", SwingConstants.LEFT);
        displayLabel.setFont(new Font("Georgia", Font.BOLD, 18));
        displayLabel.setForeground(TITLE_COLOR);
        displayLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Georgia", Font.PLAIN, 14));
        displayArea.setForeground(INK_COLOR);
        displayArea.setBackground(new Color(255, 253, 245));
        displayArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));

        rightPage.add(displayLabel, BorderLayout.NORTH);
        rightPage.add(scrollPane, BorderLayout.CENTER);

        // تقسيم الصفحتين
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPage, rightPage);
        splitPane.setDividerLocation(320);
        splitPane.setDividerSize(8);
        splitPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        splitPane.setBackground(BOOK_BG);
        frame.add(splitPane, BorderLayout.CENTER);

        // أحداث الأزرار
        addBtn.addActionListener(e -> addWord());
        searchBtn.addActionListener(e -> searchWord());
        deleteBtn.addActionListener(e -> deleteWord());
        showAllBtn.addActionListener(e -> showAllWords());

        // إعدادات العرض
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JButton createBookButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Georgia", Font.BOLD, 13));
        btn.setForeground(PAPER_COLOR);
        btn.setBackground(BORDER_COLOR);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(INK_COLOR, 2),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(160, 110, 63));
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(BORDER_COLOR);
            }
        });

        return btn;
    }

    private void addWord() {
        String word = wordField.getText().trim();
        String meaning = meaningArea.getText().trim();
        if (manager.addWord(word, meaning)) {
            displayArea.setText("Added successfully\n\n" + word + " : " + meaning);
            wordField.setText("");
            meaningArea.setText("");
        } else {
            displayArea.setText("Please fill both fields");
        }
    }

    private void searchWord() {
        String word = wordField.getText().trim();
        String meaning = manager.searchWord(word);
        if (meaning != null)
            displayArea.setText("Search Result:\n\n" + word + " : " + meaning);
        else
            displayArea.setText("Word not found");
    }

    private void deleteWord() {
        String word = wordField.getText().trim();
        if (manager.deleteWord(word))
            displayArea.setText("Deleted successfully: " + word);
        else
            displayArea.setText("Word not found (الكلمة غير موجودة)");
    }

    private void showAllWords() {
        displayArea.setText(manager.showAll());
    }
}