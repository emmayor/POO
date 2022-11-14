import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CotizadorGrafico {

	private JFrame frame;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
    private ControllerCotizaciones controller;
    private DefaultTableModel modelo;

    private void ventanaAgregarCotizacion() {
        JFrame localFrame = new JFrame("Agregar Cotizacion");
        JPanel localContentPane = new JPanel();
        JLabel tituloMenuVehiculo = new JLabel("Tipo de vehículo");
        JLabel tituloCampoDias = new JLabel("Cantidad de días");
        JTextField campoDias = new JTextField();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnCancelar = new JButton("Cancelar");
        tituloMenuVehiculo.setAlignmentX(Component.CENTER_ALIGNMENT);
        localFrame.setBounds(500, 300, 320, 240);
        localFrame.setContentPane(localContentPane);
        localContentPane.setLayout(new BoxLayout(localContentPane, BoxLayout.Y_AXIS)); 	
        
        String[] opcionesMenuVehiculo = {"Auto", "Minibus", "Furgoneta", "Camión"};
        
        JComboBox<String> vehicleDropDown = new JComboBox<String>(opcionesMenuVehiculo);
        
        
        localFrame.setVisible(true);
        localContentPane.add(tituloMenuVehiculo);
        localContentPane.add(vehicleDropDown);
        localContentPane.add(tituloCampoDias);
        localContentPane.add(campoDias);
        localContentPane.add(btnAgregar);
        localContentPane.add(btnCancelar);

        btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent elevento ) {
				// Implementa el evento para el boton Salir
				if (elevento.getSource() == btnAgregar) {
                    int vehiculoElegido = vehicleDropDown.getSelectedIndex();
                    int cantDias = Integer.parseInt(campoDias.getText());
                    controller.agregarCotizacion(vehiculoElegido, cantDias);
                    localFrame.setVisible(false);
                    actualizarTabla();
                    };
				}			
		});

        btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent elevento ) {
				// Implementa el evento para el boton Salir
				if (elevento.getSource() == btnCancelar) localFrame.setVisible(false);

				}			
		});

    }

    private void inicializarVentana() {
        frame = new JFrame("Cotizador de alquiler de vehículos");
        contentPane = new JPanel();
        contentPane.setLayout(null);	
        frame.setBounds(100, 100, 800, 600);
        frame.setContentPane(contentPane);
        frame.setVisible(true);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                cerrarPrograma();
            }
        });
    }

    private void inicializarModelo() {
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

    private void inicializarTabla() {
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

    private void actualizarTabla() {
        modelo.setRowCount(0);
        ResultSet cotizaciones = controller.fetchCotizaciones();
		Object detalle[] = new Object[9];
        try {
            while(cotizaciones.next()){
                detalle[0] = String.valueOf(cotizaciones.getInt("idCotizacion"));
                detalle[1] = cotizaciones.getString("tipoVehiculo");
                detalle[2] = cotizaciones.getString("cantidadDias");
                detalle[3] = String.valueOf(cotizaciones.getDouble("precioCotizacion"));
                detalle[4] = cotizaciones.getString("Fecha_Creacion").toString(); 
                this.modelo.addRow(detalle);
            }
        } catch (SQLException error) {
            System.out.println("ERROR: No se pudo generar la tabla de cotizaciones");
            error.printStackTrace();
        }
        table.updateUI();  
}

    private void inicializarBotones() {
        JButton btnAgregarCotizacion = new JButton("Agregar cotización");
		btnAgregarCotizacion.setBounds(167, 318, 189, 23);
		contentPane.add(btnAgregarCotizacion);		
		btnAgregarCotizacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent elevento ) {
				// Implementa el evento para el boton Salir
				if (elevento.getSource() == btnAgregarCotizacion) ventanaAgregarCotizacion();
				
				}			
		});

        JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(367, 318, 89, 23);
		btnSalir.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent elevento ) {
                // Implementa el evento para el boton Salir
				if (elevento.getSource() == btnSalir) cerrarPrograma();
				
            }			
		});
		
        contentPane.add(btnAgregarCotizacion);
        contentPane.add(btnSalir);
    }

    private void cerrarPrograma() {
        System.out.println("Cerrando conexión...");
        controller.closeConnection();
        System.exit(0);
    }

    public void iniciarBucle() {
        inicializarVentana();
        inicializarModelo();
        inicializarTabla();
        inicializarBotones();
        actualizarTabla();
    }

    public CotizadorGrafico(ControllerCotizaciones controller) {
        this.controller = controller;
    }
}
