package vista;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RowsRender extends DefaultTableCellRenderer {
	private int columna;

	public RowsRender(int Colpatron) {
		this.columna = Colpatron;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
			int row, int column) {
		setBackground(Color.white);
		table.setForeground(Color.black);
		super.getTableCellRendererComponent(table, value, selected, focused, row, column);
		if (table.getValueAt(row, columna).toString().compareTo("3") > 0) {
			this.setForeground(Color.RED);
		}
		return this;
	}
}