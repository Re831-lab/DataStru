import javax.swing.*;


public class Main {

    public static void main(String[] args) {
        // Set system look and feel for better appearance
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception e) {
            System.err.println("Could not set system look and feel: " + e.getMessage());
        }

        // Launch the GUI application
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Starting Music Player Application...");
                new MusicPlayerGUI().setVisible(true);
                System.out.println("Music Player GUI launched successfully!");
            }
        });

        // Show simple startup message
        System.out.println("Music Player started successfully!");
    }
}