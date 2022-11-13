import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


public class InterfazCotizaciones extends JFrame implements ActionListener, MouseListener, ItemListener{

    private JPanel contentPane;
	private JTable table;
	DefaultTableModel modelo;
	private JScrollPane scrollPane;

    private void cargarCotizaciones(ResultSet cotizaciones) {
        this.modelo.setRowCount(0);
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
        this.table.updateUI();
    }
        

    public InterfazCotizaciones(ResultSet cotizaciones) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);		
		setContentPane(contentPane);
		
		modelo = new DefaultTableModel(	new Object[][] {,},	new String[] {			
			"ID COTIZACION", 
			"TIPO VEHICULO", 
			"CANTIDAD DIAS",
            "PRECIO"
            }
        )
		{
			public boolean isCellEditable(int row, int column)
		    {
		        return false;
		    }
		};
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 640, 282);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setShowVerticalLines(false);
		table.setFocusable(true);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setBackground(Color.WHITE);
		table.setModel(modelo);

		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(267, 318, 89, 23);
		contentPane.add(btnSalir);		
		
		
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent elevento ) {
				// Implementa el evento para el boton Salir
				if (elevento.getSource() == btnSalir) System.exit(0);			
				
				}			
			});
		
	    table.addMouseListener(this);
        cargarCotizaciones(cotizaciones);
    }

    @Override
	public void itemStateChanged(ItemEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void actionPerformed(ActionEvent arg0) {}
}
