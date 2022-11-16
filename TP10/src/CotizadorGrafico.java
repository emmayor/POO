import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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

	private JFrame mainFrame;
	private JFrame cotizacionFrame;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
    private ControllerCotizaciones controller;
    private DefaultTableModel modelo;


    private void ventanaConfirmacionVaciar(JFrame owner) {
        JDialog dialog = new JDialog(owner,"Advertencia");
        dialog.setLayout(null);
        
        JLabel tituloAdvertencia = new JLabel("<html>¿Está seguro que desea eliminar TODOS los datos de la tabla? Este cambio no se puede deshacer</html>");
        JButton btnSi = new JButton("SI");
        JButton btnNo = new JButton("NO");

        tituloAdvertencia.setBounds(10, 0, 400, 100);
        btnSi.setBounds(10, 100, 60, 20);
        btnNo.setBounds(100, 100, 60, 20);

        btnSi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent elevento ) {
				// Implementa el evento para el boton Salir
				if (elevento.getSource() == btnSi) 
                    vaciarTabla();
                    dialog.setVisible(false);
				}			
		});

        btnNo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent elevento ) {
				// Implementa el evento para el boton Salir
				if (elevento.getSource() == btnNo) dialog.setVisible(false);

				}			
		});

        dialog.add(tituloAdvertencia);
        dialog.add(btnSi);
        dialog.add(btnNo);
        dialog.setBounds(500, 200, 500, 250);
        dialog.setVisible(true);
    }

    private JFrame inicializarVentanaAgregarCotizacion() {
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
				if (elevento.getSource() == btnCancelar) cerrarVentanaAgregarCotizacion();

				}			
		});

        return localFrame;
    }

    private void inicializarVentana() {
        mainFrame = new JFrame("Cotizador de alquiler de vehículos");
        contentPane = new JPanel();
        contentPane.setLayout(null);	
        mainFrame.setBounds(100, 100, 800, 600);
        mainFrame.setContentPane(contentPane);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
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
		      return false;
		}};
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

    private void vaciarTabla() {
        controller.truncateCotizacion();
        actualizarTabla();
    }

    private void inicializarBotones() {
        // AGREGAR COTIZACION
        JButton btnAgregarCotizacion = new JButton("Agregar cotización");
		btnAgregarCotizacion.setBounds(10, 318, 169, 23);
		contentPane.add(btnAgregarCotizacion);		
		btnAgregarCotizacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent elevento ) {
				if (elevento.getSource() == btnAgregarCotizacion) abrirVentanaAgregarCotizacion();
			}			
		});

		
        //VACIAR TABLA
        JButton btnVaciarTabla = new JButton("Vaciar Tabla");
        btnVaciarTabla.setBounds(200, 318, 150, 23);
        contentPane.add(btnVaciarTabla);		
        btnVaciarTabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent elevento ) {
                if (elevento.getSource() == btnVaciarTabla) ventanaConfirmacionVaciar(mainFrame);
            }			
        });

        //SALIR
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(550, 318, 89, 23);
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent elevento ) {
                if (elevento.getSource() == btnSalir) cerrarPrograma();
            }			
        });
        
        contentPane.add(btnAgregarCotizacion);
        contentPane.add(btnVaciarTabla);
        contentPane.add(btnSalir);
    }

    private void cerrarPrograma() {
        System.out.println("Cerrando conexión...");
        controller.closeConnection();
        System.exit(0);
    }

    private void abrirVentanaAgregarCotizacion() {
        if (cotizacionFrame == null) {
            cotizacionFrame = inicializarVentanaAgregarCotizacion();
        }
        cotizacionFrame.setVisible(true);
    }
    
    private void cerrarVentanaAgregarCotizacion() {
        cotizacionFrame.setVisible(false);
    }

    public void inicializar() {
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
