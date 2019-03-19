package vista;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class EditorCeldas extends JLabel implements TableCellRenderer {
	int Columns;

	public void setColumns(int Columns) {
		this.Columns = Columns;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		if ((column == Columns && Long.parseLong(value.toString()) > 3)) {
			// System.out.println(value + " " + (value.toString().compareTo("3") > 0));
			setForeground(Color.RED);
			setText(String.valueOf(value));
			isSelected = true;
			// table.setCellSelectionEnabled(true);
		} else {
			setForeground(Color.GREEN);
			setText(String.valueOf(value));
		}

		return this;
	}

}