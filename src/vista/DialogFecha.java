package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JCalendar;

public class DialogFecha extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JCalendar calendar;
	private LocalDate fecha;
	private JPanel panel;
	private JPanel panel_1;
	public static final String PRESTAMO = "Prestamo";
	public static final String DEVOLUCION = "Devolucion";

	public DialogFecha(String cad) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Selecciona la fecha de " + cad, TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			contentPanel.add(panel);
			{
				calendar = new JCalendar();
				panel.add(calendar);
			}
			{
				JCheckBox chckbxFechaActual = new JCheckBox("Fecha Actual");
				chckbxFechaActual.setSelected(true);
				panel.add(chckbxFechaActual);
				calendar.setEnabled(false);
				chckbxFechaActual.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						calendar.setEnabled(!calendar.isEnabled());
					}
				});
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				{
					panel_1 = new JPanel();
					panel_1.setBorder(
							new TitledBorder(null, "Opcion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
					buttonPane.add(panel_1);
					okButton = new JButton("OK");
					panel_1.add(okButton);
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
							if (calendar.isEnabled()) {
								Date fech = calendar.getDate();
								fecha = LocalDate.parse(sd.format(fech));
							} else
								fecha = (LocalDate.parse(sd.format(new Date())));
							dispose();
						}
					});
					okButton.setActionCommand("OK");
					getRootPane().setDefaultButton(okButton);
					{
						cancelButton = new JButton("Cancel");
						panel_1.add(cancelButton);
						cancelButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								dispose();
							}
						});
						cancelButton.setActionCommand("Cancel");
					}
				}
			}
		}
		pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}

	public LocalDate getFecha() {
		return fecha;
	}

}
