package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class DialogOrdenacion extends JDialog {

	private JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JList<String> list;
	private JList<String> list_1;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public DialogOrdenacion(String[] criterio) {

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 2, 0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));

			list_1 = new JList<String>(criterio);
			list_1.setSelectedIndex(0);
			JScrollPane scrollPane_1 = new JScrollPane(list_1);
			panel.add(scrollPane_1);

			JLabel lblCriterio = new JLabel("Criterio");
			panel.add(lblCriterio, BorderLayout.NORTH);

		}

		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));

			list = new JList<String>(new String[] { "Ascendente", "descendente" });
			list.setSelectedIndex(0);
			scrollPane = new JScrollPane(list);
			panel.add(scrollPane);

			JLabel lblOrden = new JLabel("Orden");
			panel.add(lblOrden, BorderLayout.NORTH);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			okButton = new JButton("OK");
			buttonPane.add(okButton);
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setModal(true);
	}

	public String[] getSeleccion() {
		String[] se = new String[2];
		se[0] = list_1.getSelectedValue();
		se[1] = list.getSelectedValue();
		return se;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

}
