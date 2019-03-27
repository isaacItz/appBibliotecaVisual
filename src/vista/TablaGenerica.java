package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class TablaGenerica extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	private DefaultTableModel modeloTabla;
	private JComboBox<String> comboBox;
	private Object[] identificadores;
	private Object[][] datos;
	private boolean b;
	private JScrollPane scrollPane;

	public TablaGenerica(Object[][] datos, Object[] identificadores, String nombreTabla) {
		b = true;
		this.datos = datos;
		this.identificadores = identificadores;

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

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				buscarCoincidencias();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				buscarCoincidencias();
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				buscarCoincidencias();
			}
		});

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTablaDeAlumnos = new JLabel("Tabla de " + nombreTabla);
		panel_3.add(lblTablaDeAlumnos);

		modeloTabla = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(modeloTabla);

		table.getActionMap().put("copy", new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				String cellValue = table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn())
						.toString();
				StringSelection stringSelection = new StringSelection(cellValue);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		panel_2.add(scrollPane, BorderLayout.CENTER);

		modeloTabla.setDataVector(datos, identificadores);
		setCriterio(identificadores);

	}

	private void setEspacioRegistrosop2() {

		if (b) {
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumn columna;

			for (int i = 0; i < table.getColumnCount(); i++) {
				columna = table.getColumn(identificadores[i]);
				columna.setMinWidth(medirLon(i) * 8);
			}
		}

		if (table.getWidth() < scrollPane.getWidth()) {
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.repaint();
			repaint();
			setVisible(true);
			table.setVisible(true);
			b = false;
		}

	}

	private int medirLon(int i) {

		int max = 0;

		for (int j = 0; j < modeloTabla.getRowCount(); j++) {
			int tam = String.valueOf(modeloTabla.getValueAt(j, i)).length();
			if (tam > max)
				max = tam;
		}
		return max;

	}

	public String getClaveSeleccionada() {
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

	private void buscarCoincidencias() {

		modeloTabla.setRowCount(0);

		for (int i = 0; i < datos.length; i++)
			if (datos[i][comboBox.getSelectedIndex()].toString().startsWith(textField.getText()))
				modeloTabla.addRow(datos[i]);

	}

	public void pedirFoco() {
		textField.requestFocus();
	}

	public void actualizarTabla(Object[][] data) {
		modeloTabla.setRowCount(0);
		modeloTabla.setDataVector(data, identificadores);
		setEspacioRegistrosop2();
	}

	public <T> void agregarFilas(T[][] filas) {

		for (int i = 0; i < filas.length; i++)
			modeloTabla.addRow(filas[i]);

	}

}
