import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
 * Beautiful Pink Music Player with Vinyl Record Animation
 * Fixed button jittering and improved layout
 */
public class MusicPlayerGUI extends JFrame {

    // Enhanced Pink Color Palette
    private static final Color PRIMARY_PINK = new Color(236, 72, 153);      // Modern Pink
    private static final Color LIGHT_PINK = new Color(251, 113, 133);       // Soft Light Pink
    private static final Color ACCENT_PINK = new Color(244, 63, 94);        // Vibrant Pink
    private static final Color ROSE_GOLD = new Color(225, 29, 72);          // Rose Gold
    private static final Color DEEP_ROSE = new Color(190, 18, 60);          // Deep Rose
    private static final Color PINK_BG = new Color(253, 242, 248);          // Very Light Pink
    private static final Color CREAM_WHITE = new Color(255, 251, 235);      // Warm White
    private static final Color DARK_TEXT = new Color(51, 65, 85);           // Slate Gray
    private static final Color LIGHT_TEXT = new Color(100, 116, 139);       // Medium Gray
    private static final Color SHADOW_COLOR = new Color(0, 0, 0, 50);      // Soft Shadow

    // Beautiful Modern Fonts
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.ITALIC, 14);
    private static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font BODY_FONT = new Font("Segoe UI", Font.PLAIN, 12);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 11);
    private static final Font SMALL_FONT = new Font("Segoe UI", Font.PLAIN, 10);
    private static final Font HELP_FONT = new Font("Segoe UI", Font.PLAIN, 10);

    // Components
    private MusicPlayer player;
    private JTextField songInput;
    private JList<String> playlistList;
    private DefaultListModel<String> listModel;
    private JLabel currentSongLabel;
    private JLabel statusLabel;
    private JLabel currentInQueueLabel;  // New label for current song in status
    private JLabel vinylTitleLabel;      // Reference to vinyl title label
    private JProgressBar capacityBar;
    private JButton addButton, playButton, peekButton, clearButton, stopButton, helpButton;
    private VinylRecord vinylRecord;
    private Timer vinylTimer;

    public MusicPlayerGUI() {
        player = new MusicPlayer();
        vinylRecord = new VinylRecord();
        setupGUI();
        updateDisplay();
        setupVinylAnimation();
    }

    private void setupGUI() {
        setTitle("Beautiful Music Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        // Use BorderLayout with proper spacing
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(PINK_BG);

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createMainContentPanel(), BorderLayout.CENTER);
        add(createFooterPanel(), BorderLayout.SOUTH);

        setupKeyboard();
    }

    private void setupVinylAnimation() {
        vinylTimer = new Timer(50, e -> {
            if (player.getCurrentSong() != null) {
                vinylRecord.rotate();
            } else {
                vinylRecord.stopRotation();
            }
            vinylRecord.repaint();
        });
        vinylTimer.start();
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                // Create beautiful gradient background
                GradientPaint gradient = new GradientPaint(
                        0, 0, PRIMARY_PINK,
                        getWidth(), getHeight(), DEEP_ROSE
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setPreferredSize(new Dimension(0, 100));
        panel.setBorder(new EmptyBorder(20, 30, 20, 30));

        // Create title
        JLabel title = new JLabel("Beautiful Music Player", JLabel.CENTER);
        title.setFont(TITLE_FONT);
        title.setForeground(CREAM_WHITE);

        currentSongLabel = new JLabel("Ready to play your favorite music", JLabel.CENTER);
        currentSongLabel.setFont(SUBTITLE_FONT);
        currentSongLabel.setForeground(new Color(255, 220, 235));

        panel.add(title, BorderLayout.CENTER);
        panel.add(currentSongLabel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createMainContentPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(PINK_BG);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Left section - Vinyl and controls
        JPanel leftSection = createLeftSection();
        leftSection.setPreferredSize(new Dimension(280, 0));

        // Center section - Playlist (now wider)
        JPanel centerSection = createPlaylistSection();

        // Right section - Status info (now more compact)
        JPanel rightSection = createInfoSection();
        rightSection.setPreferredSize(new Dimension(280, 0));

        mainPanel.add(leftSection, BorderLayout.WEST);
        mainPanel.add(centerSection, BorderLayout.CENTER);
        mainPanel.add(rightSection, BorderLayout.EAST);

        return mainPanel;
    }

    private JPanel createLeftSection() {
        JPanel section = new JPanel(new BorderLayout(0, 15));
        section.setBackground(PINK_BG);

        // Vinyl panel
        section.add(createVinylPanel(), BorderLayout.NORTH);

        // Controls panel
        section.add(createControlsPanel(), BorderLayout.CENTER);

        return section;
    }

    private JPanel createVinylPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CREAM_WHITE);
        panel.setPreferredSize(new Dimension(280, 220));
        panel.setBorder(createModernBorder());

        // Store reference to title label for updates
        vinylTitleLabel = new JLabel("Ready to Play", JLabel.CENTER);
        vinylTitleLabel.setFont(HEADER_FONT);
        vinylTitleLabel.setForeground(DEEP_ROSE);
        vinylTitleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        vinylRecord.setPreferredSize(new Dimension(170, 170));

        panel.add(vinylTitleLabel, BorderLayout.NORTH);
        panel.add(vinylRecord, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createControlsPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setBackground(CREAM_WHITE);
        panel.setBorder(createModernBorder());

        // Input section
        JPanel inputSection = createInputSection();

        // Button section
        JPanel buttonSection = createButtonSection();

        panel.add(inputSection, BorderLayout.NORTH);
        panel.add(buttonSection, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createInputSection() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(CREAM_WHITE);
        panel.setBorder(new EmptyBorder(15, 15, 10, 15));

        JLabel inputLabel = new JLabel("Add Your Song", JLabel.CENTER);
        inputLabel.setFont(HEADER_FONT);
        inputLabel.setForeground(DEEP_ROSE);

        songInput = createStyledTextField();
        songInput.addActionListener(e -> addSong());

        panel.add(inputLabel, BorderLayout.NORTH);
        panel.add(songInput, BorderLayout.CENTER);

        return panel;
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Rounded background
                g2d.setColor(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

                super.paintComponent(g);
            }
        };

        field.setFont(BODY_FONT);
        field.setPreferredSize(new Dimension(0, 35));
        field.setBackground(new Color(254, 249, 252));
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(LIGHT_PINK, 2, true),
                new EmptyBorder(8, 12, 8, 12)
        ));

        return field;
    }

    private JPanel createButtonSection() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 8, 8));
        panel.setBackground(CREAM_WHITE);
        panel.setBorder(new EmptyBorder(5, 15, 15, 15));

        // Create buttons with fixed sizes to prevent jittering
        addButton = createStableButton("Add", PRIMARY_PINK, LIGHT_PINK);
        addButton.addActionListener(e -> addSong());

        playButton = createStableButton("Play", ACCENT_PINK, LIGHT_PINK);
        playButton.addActionListener(e -> playNext());

        peekButton = createStableButton("Preview", LIGHT_PINK, new Color(253, 164, 175));
        peekButton.addActionListener(e -> peekNext());

        stopButton = createStableButton("Stop", new Color(255, 107, 107), new Color(252, 165, 165));
        stopButton.addActionListener(e -> stopMusic());

        clearButton = createStableButton("Clear", ROSE_GOLD, DEEP_ROSE);
        clearButton.addActionListener(e -> clearPlaylist());

        helpButton = createStableButton("Help", new Color(139, 69, 19), new Color(160, 82, 45));
        helpButton.addActionListener(e -> showHelpDialog());

        panel.add(addButton);
        panel.add(playButton);
        panel.add(peekButton);
        panel.add(stopButton);
        panel.add(clearButton);
        panel.add(helpButton);

        return panel;
    }

    private JPanel createPlaylistSection() {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw shadow
                g2d.setColor(SHADOW_COLOR);
                g2d.fillRoundRect(3, 3, getWidth()-3, getHeight()-3, 15, 15);

                // Draw main panel
                g2d.setColor(CREAM_WHITE);
                g2d.fillRoundRect(0, 0, getWidth()-3, getHeight()-3, 15, 15);

                // Draw border
                g2d.setColor(LIGHT_PINK);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRoundRect(1, 1, getWidth()-5, getHeight()-5, 13, 13);
            }
        };
        panel.setOpaque(false);

        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setOpaque(false);
        titlePanel.setBorder(new EmptyBorder(15, 20, 10, 20));

        JLabel titleLabel = new JLabel("Your Beautiful Playlist");
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(DEEP_ROSE);
        titlePanel.add(titleLabel);

        // List setup
        listModel = new DefaultListModel<>();
        playlistList = createStyledList();

        JScrollPane scrollPane = new JScrollPane(playlistList);
        scrollPane.setBorder(new EmptyBorder(0, 15, 15, 15));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JList<String> createStyledList() {
        JList<String> list = new JList<>(listModel);
        list.setFont(BODY_FONT);
        list.setSelectionBackground(LIGHT_PINK);
        list.setSelectionForeground(DARK_TEXT);
        list.setFixedCellHeight(35);
        list.setOpaque(false);

        // Simple cell renderer without dynamic content
        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                setFont(BODY_FONT);
                setBorder(new EmptyBorder(8, 15, 8, 15));
                setOpaque(true);

                if (isSelected) {
                    setBackground(LIGHT_PINK);
                    setForeground(DARK_TEXT);
                } else {
                    setBackground(index % 2 == 0 ? CREAM_WHITE : new Color(254, 249, 252));
                    setForeground(DARK_TEXT);
                }
                return this;
            }
        });

        return list;
    }

    private JPanel createInfoSection() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setBackground(PINK_BG);

        // Status panel only
        panel.add(createStatusPanel(), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createStatusPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(CREAM_WHITE);
        panel.setBorder(createModernBorder());

        JLabel statusTitle = new JLabel("Queue Status");
        statusTitle.setFont(HEADER_FONT);
        statusTitle.setForeground(DEEP_ROSE);
        statusTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusTitle.setBorder(new EmptyBorder(15, 0, 15, 0));

        statusLabel = new JLabel("0 of 20 songs");
        statusLabel.setFont(BODY_FONT);
        statusLabel.setForeground(PRIMARY_PINK);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Custom progress bar
        capacityBar = createStyledProgressBar();

        // Current playing song display
        JLabel playingTitle = new JLabel("Currently Playing");
        playingTitle.setFont(SMALL_FONT);
        playingTitle.setForeground(DEEP_ROSE);
        playingTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        playingTitle.setBorder(new EmptyBorder(15, 0, 5, 0));

        currentInQueueLabel = new JLabel("No song playing");
        currentInQueueLabel.setFont(BODY_FONT);
        currentInQueueLabel.setForeground(LIGHT_TEXT);
        currentInQueueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Next in queue display
        JLabel nextTitle = new JLabel("Next in Queue");
        nextTitle.setFont(SMALL_FONT);
        nextTitle.setForeground(DEEP_ROSE);
        nextTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextTitle.setBorder(new EmptyBorder(15, 0, 5, 0));

        JLabel nextInQueueLabel = new JLabel("Queue empty") {
            @Override
            public void paintComponent(Graphics g) {
                String nextSong = player.peekNext();
                if (nextSong != null) {
                    setText(nextSong);
                    setForeground(PRIMARY_PINK);
                } else {
                    setText("Queue empty");
                    setForeground(LIGHT_TEXT);
                }
                super.paintComponent(g);
            }
        };
        nextInQueueLabel.setFont(BODY_FONT);
        nextInQueueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(statusTitle);
        panel.add(statusLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(capacityBar);
        panel.add(playingTitle);
        panel.add(currentInQueueLabel);
        panel.add(nextTitle);
        panel.add(nextInQueueLabel);
        panel.add(Box.createVerticalStrut(15));

        return panel;
    }

    private JProgressBar createStyledProgressBar() {
        JProgressBar bar = new JProgressBar(0, 20) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Background
                g2d.setColor(PINK_BG);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

                // Progress with gradient
                if (getValue() > 0) {
                    int progressWidth = (int) ((double) getValue() / getMaximum() * getWidth());
                    GradientPaint gp = new GradientPaint(0, 0, LIGHT_PINK, progressWidth, 0, PRIMARY_PINK);
                    g2d.setPaint(gp);
                    g2d.fillRoundRect(0, 0, progressWidth, getHeight(), 10, 10);
                }

                // Border
                g2d.setColor(LIGHT_PINK);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 8, 8);

                // Text
                if (isStringPainted()) {
                    g2d.setColor(DARK_TEXT);
                    g2d.setFont(SMALL_FONT);
                    FontMetrics fm = g2d.getFontMetrics();
                    String text = getString();
                    int x = (getWidth() - fm.stringWidth(text)) / 2;
                    int y = (getHeight() + fm.getAscent()) / 2;
                    g2d.drawString(text, x, y);
                }
            }
        };

        bar.setValue(0);
        bar.setStringPainted(true);
        bar.setString("Empty");
        bar.setPreferredSize(new Dimension(0, 25));
        bar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        bar.setOpaque(false);

        return bar;
    }

    private JPanel createHelpTextPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CREAM_WHITE);
        panel.setBorder(createModernBorder());

        JTextArea helpText = new JTextArea();
        helpText.setText("QUEUE Data Structure:\n\n" +
                "• FIFO: First In, First Out\n" +
                "• Add songs to rear (Enqueue)\n" +
                "• Play removes from front (Dequeue)\n" +
                "• Preview shows front element (Peek)\n" +
                "• Fixed capacity: 20 songs maximum\n\n" +
                "Queue Operations:\n" +
                "• Add: Only works when not full\n" +
                "• Play: Removes and returns front song\n" +
                "• Preview: Shows next without removing\n" +
                "• Clear: Empties entire queue\n\n" +
                "Learning Points:\n" +
                "Experience queue limitations and FIFO behavior through music playlist management.");
        helpText.setEditable(false);
        helpText.setFont(HELP_FONT);
        helpText.setForeground(LIGHT_TEXT);
        helpText.setBackground(CREAM_WHITE);
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);
        helpText.setBorder(new EmptyBorder(15, 15, 15, 15));

        panel.add(helpText, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createFooterPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER)) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                GradientPaint gradient = new GradientPaint(
                        0, 0, DEEP_ROSE,
                        getWidth(), 0, new Color(120, 20, 70)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setPreferredSize(new Dimension(0, 50));
        panel.setBorder(new EmptyBorder(12, 30, 12, 30));

        JLabel footer = new JLabel("Beautiful Music Player - Crafted with Love");
        footer.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        footer.setForeground(new Color(255, 192, 203));

        panel.add(footer);
        return panel;
    }

    private Border createModernBorder() {
        return BorderFactory.createCompoundBorder(
                new LineBorder(SHADOW_COLOR, 0),
                new CompoundBorder(
                        new LineBorder(LIGHT_PINK, 2, true),
                        new EmptyBorder(12, 12, 12, 12)
                )
        );
    }

    // Fixed button creation method to prevent jittering
    private JButton createStableButton(String text, Color startColor, Color endColor) {
        JButton button = new JButton(text) {
            private boolean isHovered = false;

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Button gradient
                GradientPaint gradient = new GradientPaint(0, 0, startColor, getWidth(), getHeight(), endColor);
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

                // Hover effect
                if (isHovered) {
                    g2d.setColor(new Color(255, 255, 255, 30));
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                }

                // Highlight effect
                g2d.setColor(new Color(255, 255, 255, 25));
                g2d.fillRoundRect(2, 2, getWidth()-4, getHeight()/3, 6, 6);

                // Draw text
                super.paintComponent(g);
            }

            @Override
            public void setEnabled(boolean enabled) {
                super.setEnabled(enabled);
                repaint();
            }
        };

        button.setFont(BUTTON_FONT);
        button.setForeground(CREAM_WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(100, 32)); // Fixed size
        button.setMinimumSize(new Dimension(100, 32));   // Prevent shrinking
        button.setMaximumSize(new Dimension(100, 32));   // Prevent expansion
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Stable hover effect without border changes
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.putClientProperty("isHovered", true);
                button.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.putClientProperty("isHovered", false);
                button.repaint();
            }
        });

        return button;
    }

    // Vinyl Record Component (unchanged but optimized)
    private class VinylRecord extends JComponent {
        private double rotation = 0;
        private boolean isPlaying = false;

        public void rotate() {
            isPlaying = true;
            rotation += 2;
            if (rotation >= 360) rotation = 0;
        }

        public void stopRotation() {
            isPlaying = false;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int radius = Math.min(centerX, centerY) - 5;

            // Shadow
            g2d.setColor(new Color(0, 0, 0, 30));
            g2d.fillOval(centerX - radius + 2, centerY - radius + 2, radius * 2, radius * 2);

            // Vinyl record
            GradientPaint vinylGradient = new GradientPaint(
                    centerX - radius, centerY - radius, new Color(20, 20, 20),
                    centerX + radius, centerY + radius, new Color(60, 60, 60)
            );
            g2d.setPaint(vinylGradient);
            g2d.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

            // Record grooves
            g2d.setColor(new Color(40, 40, 40));
            g2d.setStroke(new BasicStroke(1));
            for (int i = radius - 8; i > 18; i -= 6) {
                g2d.drawOval(centerX - i, centerY - i, i * 2, i * 2);
            }

            // Center label
            int labelRadius = 18;
            g2d.setColor(DEEP_ROSE);
            g2d.fillOval(centerX - labelRadius, centerY - labelRadius, labelRadius * 2, labelRadius * 2);

            // Center hole
            g2d.setColor(new Color(20, 20, 20));
            g2d.fillOval(centerX - 3, centerY - 3, 6, 6);

            // Rotation indicator
            if (isPlaying) {
                g2d.setColor(CREAM_WHITE);
                double radians = Math.toRadians(rotation);
                int dotX = centerX + (int)(12 * Math.cos(radians));
                int dotY = centerY + (int)(12 * Math.sin(radians));
                g2d.fillOval(dotX - 2, dotY - 2, 4, 4);
            }

            // Stylus arm
            g2d.setColor(new Color(120, 120, 120));
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine(centerX + radius - 12, centerY - radius + 12, centerX + radius - 3, centerY - 6);
        }
    }

    // Core functionality methods
    private void addSong() {
        String song = songInput.getText().trim();
        if (song.isEmpty()) {
            songInput.requestFocus();
            return;
        }

        // Strict Queue behavior - show proper Queue limitations
        if (player.isFull()) {
            showQueueFullMessage();
            return;
        }

        if (player.addSong(song)) {
            songInput.setText("");
            songInput.requestFocus();
            updateDisplay();
        } else {
            showErrorMessage("Invalid song name");
        }
    }

    // Helper methods for Queue education
    private void showQueueFullMessage() {
        statusLabel.setText("Queue Full! Play or clear songs first");
        statusLabel.setForeground(new Color(200, 100, 0)); // Orange color for warning

        Timer warningTimer = new Timer(4000, e -> {
            statusLabel.setForeground(PRIMARY_PINK); // Reset color
            int size = player.size();
            statusLabel.setText(size + " of 20 songs - FULL");
        });
        warningTimer.setRepeats(false);
        warningTimer.start();
    }

    private void showErrorMessage(String message) {
        statusLabel.setText(message);
        statusLabel.setForeground(new Color(220, 20, 20)); // Red color for error

        Timer errorTimer = new Timer(3000, e -> {
            statusLabel.setForeground(PRIMARY_PINK); // Reset color
            int size = player.size();
            statusLabel.setText(size + " of 20 songs");
        });
        errorTimer.setRepeats(false);
        errorTimer.start();
    }

    private void playNext() {
        if (player.isEmpty()) {
            return;
        }

        player.playNext();
        updateDisplay();
    }

    private void peekNext() {
        if (player.isEmpty()) {
            return;
        }

        String next = player.peekNext();
        for (int i = 0; i < listModel.size(); i++) {
            String item = listModel.getElementAt(i);
            if (item.contains(next)) {
                playlistList.setSelectedIndex(i);
                playlistList.ensureIndexIsVisible(i);
                break;
            }
        }

        statusLabel.setText("Next: " + next);

        // Flash effect
        Timer flashTimer = new Timer(100, null);
        final int[] flashCount = {0};
        flashTimer.addActionListener(e -> {
            if (flashCount[0] % 2 == 0) {
                currentSongLabel.setForeground(ACCENT_PINK);
            } else {
                currentSongLabel.setForeground(new Color(255, 220, 235));
            }
            flashCount[0]++;
            if (flashCount[0] >= 6) {
                flashTimer.stop();
                Timer resetTimer = new Timer(3000, ev -> {
                    int size = player.size();
                    statusLabel.setText(size + " of 20 songs");
                });
                resetTimer.setRepeats(false);
                resetTimer.start();
            }
        });
        flashTimer.start();
    }

    private void stopMusic() {
        if (player.getCurrentSong() == null) {
            return;
        }

        player.stopCurrentSong();
        updateDisplay();
    }

    private void clearPlaylist() {
        if (player.isEmpty()) {
            return;
        }

        player.clearPlaylist();
        updateDisplay();
    }

    private void updateDisplay() {
        // Update playlist display - show currently playing song with indicator
        listModel.clear();
        String[] songs = player.getPlaylistArray();
        String currentSong = player.getCurrentSong();

        for (int i = 0; i < songs.length; i++) {
            String displayText = (i + 1) + ". " + songs[i];
            listModel.addElement(displayText);
        }

        // Update current song display in header
        if (currentSong != null) {
            currentSongLabel.setText(" NOW PLAYING: " + currentSong );
            currentSongLabel.setForeground(CREAM_WHITE);

            // Update vinyl title
            vinylTitleLabel.setText(" NOW PLAYING ");
            vinylTitleLabel.setForeground(PRIMARY_PINK);

            // Update status panel current song
            currentInQueueLabel.setText(currentSong);
            currentInQueueLabel.setForeground(ACCENT_PINK);
        } else {
            currentSongLabel.setText("Ready to play your favorite music");
            currentSongLabel.setForeground(new Color(255, 220, 235));

            // Update vinyl title
            vinylTitleLabel.setText("Ready to Play");
            vinylTitleLabel.setForeground(DEEP_ROSE);

            // Update status panel
            currentInQueueLabel.setText("No song playing");
            currentInQueueLabel.setForeground(LIGHT_TEXT);
        }

        // Update status with more detailed info
        int size = player.size();
        String nextSong = player.peekNext();

        if (currentSong != null && nextSong != null) {
            statusLabel.setText("Playing • " + size + " songs in queue");
        } else if (currentSong != null && nextSong == null) {
            statusLabel.setText("Playing • Queue empty");
        } else {
            statusLabel.setText(size + " of 20 songs");
        }

        capacityBar.setValue(size);

        if (size == 0) {
            capacityBar.setString("Empty & Ready");
        } else if (size == 1) {
            capacityBar.setString("1 beautiful song");
        } else {
            capacityBar.setString(size + " beautiful songs");
        }

        // Update button states and text
        playButton.setEnabled(!player.isEmpty());
        peekButton.setEnabled(!player.isEmpty());
        stopButton.setEnabled(player.getCurrentSong() != null);
        clearButton.setEnabled(!player.isEmpty());
        addButton.setEnabled(true);
        songInput.setEnabled(true);

        // Update button text based on state
        if (currentSong != null) {
            playButton.setText("Next");
        } else {
            playButton.setText("Play");
        }

        // Refresh the display
        repaint();
    }

    // Remove the unused helper method
    // private void updateVinylTitle(boolean isPlaying) - removed

    private void showHelpDialog() {
        JDialog helpDialog = new JDialog(this, "Music Player Help", true);
        helpDialog.setSize(480, 580);
        helpDialog.setLocationRelativeTo(this);
        helpDialog.setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(CREAM_WHITE);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Beautiful Music Player - Help Guide", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(DEEP_ROSE);
        titleLabel.setBorder(new EmptyBorder(0, 0, 15, 0));

        // Help content
        JTextArea helpContent = new JTextArea();
        helpContent.setText(
                "BASIC OPERATIONS:\n\n" +
                        "• Type song name and click 'Add' or press Enter\n" +
                        "• Click 'Play' or press Space to play next song\n" +
                        "• Click 'Preview' or press F1 to highlight next song\n" +
                        "• Click 'Stop' or press S to stop current music\n" +
                        "• Click 'Clear' or press Escape to empty playlist\n\n" +
                        "AUTO-QUEUE SYSTEM:\n\n" +
                        "• Maximum playlist capacity: 20 songs\n" +
                        "• When full, adding new song automatically plays first song\n" +
                        "• This creates continuous music flow\n" +
                        "• No need to manually manage playlist space\n\n" +
                        "KEYBOARD SHORTCUTS:\n\n" +
                        "• Enter → Add song to playlist\n" +
                        "• Space → Play next song\n" +
                        "• S → Stop current music\n" +
                        "• Escape → Clear entire playlist\n" +
                        "• F1 → Preview next song\n\n" +
                        "VISUAL FEATURES:\n\n" +
                        "• Vinyl record spins when music plays\n" +
                        "• Progress bar shows playlist capacity\n" +
                        "• Songs highlight alternately in playlist\n" +
                        "• Preview highlights next song to play\n\n" +
                        "TIPS:\n\n" +
                        "• Input field and Add button always enabled\n" +
                        "• Current song displays in header area\n" +
                        "• Status shows playlist count and next song info\n" +
                        "• All buttons provide instant visual feedback"
        );
        helpContent.setEditable(false);
        helpContent.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        helpContent.setForeground(DARK_TEXT);
        helpContent.setBackground(CREAM_WHITE);
        helpContent.setLineWrap(true);
        helpContent.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(helpContent);
        scrollPane.setBorder(BorderFactory.createLineBorder(LIGHT_PINK, 1));
        scrollPane.setPreferredSize(new Dimension(430, 400));

        // Close button
        JButton closeButton = createStableButton("Close", PRIMARY_PINK, LIGHT_PINK);
        closeButton.addActionListener(e -> helpDialog.dispose());
        closeButton.setPreferredSize(new Dimension(80, 30));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(CREAM_WHITE);
        buttonPanel.add(closeButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        helpDialog.add(mainPanel);
        helpDialog.setVisible(true);
    }

    private void setupKeyboard() {
        getRootPane().setDefaultButton(addButton);

        // Space key for playing next song
        KeyStroke spaceKey = KeyStroke.getKeyStroke("SPACE");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(spaceKey, "play");
        getRootPane().getActionMap().put("play", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!songInput.hasFocus()) playNext();
            }
        });

        // S key for stopping music
        KeyStroke sKey = KeyStroke.getKeyStroke("S");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(sKey, "stop");
        getRootPane().getActionMap().put("stop", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!songInput.hasFocus()) stopMusic();
            }
        });

        // Escape key for clearing playlist
        KeyStroke escapeKey = KeyStroke.getKeyStroke("ESCAPE");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKey, "clear");
        getRootPane().getActionMap().put("clear", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearPlaylist();
            }
        });

        // F1 key for peek
        KeyStroke f1Key = KeyStroke.getKeyStroke("F1");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f1Key, "peek");
        getRootPane().getActionMap().put("peek", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                peekNext();
            }
        });
    }
}