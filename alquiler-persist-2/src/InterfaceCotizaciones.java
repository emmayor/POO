import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.BoxLayout; // added code

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfaceCotizaciones {

	private JFrame frame;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
    private ControllerCotizaciones controller;
    private DefaultTableModel modelo;


    private void showCotizationWindow() {
        JFrame localFrame = new JFrame("Agregar Cotizacion");
        JPanel localContentPane = new JPanel();
        JLabel vehicleMenuTitle = new JLabel("Tipo de vehículo");
        JLabel daysFieldTitle = new JLabel("Cantidad de días");
        JTextField daysTextField = new JTextField();
        JButton btnOk = new JButton("Agregar");
        JButton btnCancel = new JButton("Cancelar");
        vehicleMenuTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        localFrame.setBounds(500, 300, 320, 240);
        localFrame.setContentPane(localContentPane);
        localContentPane.setLayout(new BoxLayout(localContentPane, BoxLayout.Y_AXIS)); 	
        
        String[] vehicleOptions = {"Auto", "Minibus", "Furgoneta", "Camión"};
        
        JComboBox<String> vehicleDropDown = new JComboBox<String>(vehicleOptions);
        int vehicleChosen = vehicleDropDown.getSelectedIndex();
        int daysEntered = Integer.parseInt(daysTextField.getText());
        
        localFrame.setVisible(true);
        localContentPane.add(vehicleMenuTitle);
        localContentPane.add(vehicleDropDown);
        localContentPane.add(daysFieldTitle);
        localContentPane.add(daysTextField);
        localContentPane.add(btnOk);
        localContentPane.add(btnCancel);

        btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent elevento ) {
				// Implementa el evento para el boton Salir
				if (elevento.getSource() == btnOk) {
                    Vehiculo vehicle = controller.instanciarVehiculo(vehicleChosen,daysEntered);
                    controller.insertCotizacion(vehicle);
                    table.updateUI();
                    };
				}			
		});

        btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent elevento ) {
				// Implementa el evento para el boton Salir
				if (elevento.getSource() == btnCancel) localContentPane.setVisible(false);
				
				}			
		});

    }

    private void initWindow() {
        frame = new JFrame("Cotizador de alquiler de vehículos");
        contentPane = new JPanel();
        contentPane.setLayout(null);	
        frame.setBounds(100, 100, 800, 600);
        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    private void initModel() {
        modelo = new DefaultTableModel(	new Object[][] {,},	new String[] {			
			"ID", 
			"VEHICULO", 
			"DIAS",
			"PRECIO",
			"FECHA"
        })
		{
			public boolean isCellEditable(int row, int column)
		    {
		      return true;//Esta sentencia hace que todas las celdas no permitan edicion
		    }
		};
    }

    private void initTable() {
        table = new JTable();
        scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 640, 282);
		scrollPane.setViewportView(table);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setBackground(Color.WHITE);
		table.setModel(modelo);

		contentPane.add(scrollPane);
    }

    private void initButtons() {
        JButton btnAdd = new JButton("Agregar cotizacion");
		btnAdd.setBounds(167, 318, 189, 23);
		contentPane.add(btnAdd);		
		
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent elevento ) {
				// Implementa el evento para el boton Salir
				if (elevento.getSource() == btnAdd) showCotizationWindow();
				
				}			
		});

        JButton btnExit = new JButton("Salir");
		
		
		btnExit.setBounds(367, 318, 89, 23);
		btnExit.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent elevento ) {
                // Implementa el evento para el boton Salir
				if (elevento.getSource() == btnExit) System.exit(0);
				
            }			
		});
		
        contentPane.add(btnAdd);
        contentPane.add(btnExit);
    }

    public InterfaceCotizaciones(ControllerCotizaciones controller) {
        this.controller = controller;
        initWindow();
        initModel();
        initTable();
        initButtons();
    }
}
