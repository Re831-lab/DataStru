import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ChemicalFormulaGUI();
                    System.out.println(" Chemical Formula Checker started successfully!");
                } catch (Exception e) {
                    System.err.println(" Error starting the application:");
                    e.printStackTrace();
                }
            }
        });
    }
}