import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog;


public class MainWindow extends JFrame {
    
    private JPanel contentPane;
    private JButton btnExit;
    private JButton btnAdd;

    

    public MainWindow() {
        // Definir comportamiento por defecto para cerrar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crea el panel que contendrá a los componentes
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
        
        // Asigna el panel a la ventana 
		setContentPane(contentPane);
        
        // Creamos los componentes dentro de la ventana
        btnAdd = new JButton("Agregar Cotización");
		btnAdd.setBounds(167, 318, 189, 23);
        
        
        btnExit = new JButton("Salir");
		btnExit.setBounds(367, 318, 89, 23);
		
		
		btnExit.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent elevento) {
                // Implementa el evento para el boton Salir
				if (elevento.getSource() == btnExit) System.exit(0);			
            }			
        });
		
        btnAdd.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent elevento) {
                // Implementa el evento para el boton Salir
				if (elevento.getSource() == btnAdd) addRow();			
            }			
        });

        // Añadimos los componentes al panel
        contentPane.add(btnExit);		
        contentPane.add(btnAdd);		

    }
}
