import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class InterfaceCotizaciones {

	private JFrame frame = new JFrame("Cotizador de alquiler de vehículos");
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
    private ControllerCotizaciones controller;


    public InterfaceCotizaciones(ControllerCotizaciones controller) {
        this.controller = controller;
    }

}
