package vista;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class Footer extends JPanel {

	/**
	 * Create the panel.
	 */
	public Footer() {
		setBackground(Color.LIGHT_GRAY);
		
		JLabel lblTecnmInstitutoTecnolgico = new JLabel("TecNM Instituto Tecnologico de Zitacuaro Co.");
		lblTecnmInstitutoTecnolgico.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 10));
		add(lblTecnmInstitutoTecnolgico);

	}

}
