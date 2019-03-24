package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class DialogEliminacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton btnBusq;
	private JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public DialogEliminacion(String aEliminar, Class<?> c) {
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel label = new JLabel("Seguro que Desea Eliminar a: ");
		contentPanel.add(label);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				btnBusq = new JButton("Buscar Otro " + c.getSimpleName());
				buttonPane.add(btnBusq);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		label.setText(label.getText().concat(aEliminar));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public JButton getOkButton() {
		return okButton;
	}

	public JButton getBtnBusq() {
		return btnBusq;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

}
