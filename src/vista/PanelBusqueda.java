package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import modelo.Alumno;
import modelo.ComparadorInicioAlumnos;
import modelo.ComparadorInicioPrestamos;
import modelo.Devoluciones;
import modelo.Prestamo;
import modelo.Prestamos;

public class PanelBusqueda extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	private DefaultTableModel modeloTabla;
	private JComboBox<String> comboBox;
	private Prestamos prestamos;
	private Object[] titulos;

	public PanelBusqueda(Prestamos prestamos, Object[] titulo, String[] crterios) {

		this.prestamos = prestamos;
		this.titulos = titulo;

		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Ingrese los Datos:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_1);

		JLabel lblCriterio = new JLabel("Criterio:");
		panel_1.add(lblCriterio);

		comboBox = new JComboBox<>();
		panel_1.add(comboBox);

		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		textField.getDocument().addDocumentListener(new DocumentListener() {
			private Prestamo[] l;

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				method();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				method();
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				method();
			}

			public void method() {
				l = ComparadorInicioPrestamos.concidencias(prestamos.getList(), comboBox.getSelectedItem().toString(),
						textField.getText());
				modeloTabla.setRowCount(0);
				agregarPrestamos(l);
			}

		});

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(
				new TitledBorder(null, "Datos del Prestamo:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTablaDeAlumnos = new JLabel("Tabla de Prestamos");
		panel_3.add(lblTablaDeAlumnos);

		modeloTabla = new DefaultTableModel();
		table = new JTable(modeloTabla);

		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel_2.add(scrollPane, BorderLayout.CENTER);

		if (titulos != null) {
			setTitulos(titulos);
		}
		setCriterio(crterios);

	}

	public String getNumControlSeleccionado() {
		int fila = table.getSelectedRow();
		String dato = (String) table.getValueAt(fila, 0);
		return dato;
	}

	public boolean hayFilaSeleccionada() {
		return table.getSelectedRow() > -1;
	}

	public void setCriterio(Object[] lista) {
		for (int i = 0; i < lista.length; i++) {
			comboBox.addItem(lista[i].toString());
		}

	}

	public void setTitulos(Object[] cols) {
		modeloTabla.setColumnIdentifiers(cols);
	}

	public void agregarFila(Object[] fila) {
		modeloTabla.addRow(fila);
	}

	public void agregarPrestamos(List<Prestamo> lista) {
		for (int i = 0; i < lista.size(); i++) {
			Object[] linea = new Object[6];
			Prestamo a = lista.get(i);
			linea[0] = a.getAlumno().getNoControl();
			linea[1] = a.getAlumno().getNombreCompleto();
			linea[2] = a.getLibro().getIsbn();
			linea[3] = a.getLibro().getTitulo();
			linea[4] = a.getFechaDePrestamo().toString();
			linea[5] = a.getFechaDeEntrega().toString();
			modeloTabla.addRow(linea);
		}
	}

	public void agregarPrestamos(Prestamo[] lista) {
		for (int i = 0; i < lista.length; i++) {
			Object[] linea = new Object[6];
			Prestamo a = lista[i];
			linea[0] = a.getAlumno().getNoControl();
			linea[1] = a.getAlumno().getNombreCompleto();
			linea[2] = a.getLibro().getIsbn();
			linea[3] = a.getLibro().getTitulo();
			linea[4] = a.getFechaDePrestamo().toString();
			linea[5] = a.getFechaDeEntrega().toString();
			modeloTabla.addRow(linea);
		}
	}

	public void pedirFoco() {
		textField.requestFocus();
	}

	public void actualizarTabla() {
		// modeloTabla.fireTableDataChanged();
		// repaint();
		// table.repaint();
		// table.setVisible(true);
		// setVisible(true);
		modeloTabla.setRowCount(0);
		agregarPrestamos(prestamos.getList());
	}

	public <T> void agregarFilas(T[][] filas) {

		for (int i = 0; i < filas.length; i++)
			modeloTabla.addRow(filas[i]);

	}

}
