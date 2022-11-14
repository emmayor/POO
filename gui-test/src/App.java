import java.awt.EventQueue;

public class App {

	private static void initGui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
    public static void main (String[] args) {
		initGui();
    }
}