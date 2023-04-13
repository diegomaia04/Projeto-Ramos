package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;

public class Sobre extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JEditorPane dtrpnOlMeuNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Sobre dialog = new Sobre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setLayout(null);

		dtrpnOlMeuNome = new JEditorPane();
		dtrpnOlMeuNome.setForeground(Color.WHITE);
		dtrpnOlMeuNome.setBackground(Color.DARK_GRAY);
		dtrpnOlMeuNome.setText(
				" Projeto utilizando Java e MYSQL para um sistema de Montagem e  Conserto de Computadores. \r\n Feito por: Allan Gomes, Bruno Henrique, Diego Maia, Igor Oliveira. \r\n \r\n Perante à licença MIT");
		dtrpnOlMeuNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		dtrpnOlMeuNome.setEditable(false);
		dtrpnOlMeuNome.setBounds(0, 159, 424, 102);
		getContentPane().add(dtrpnOlMeuNome);

		JLabel lblMit = new JLabel("");
		lblMit.setIcon(new ImageIcon(Sobre.class.getResource("/img/legal_license_mit_icon_157533.png")));
		lblMit.setBounds(324, 35, 90, 74);
		getContentPane().add(lblMit);

		JLabel lblVersion = new JLabel("Versão: 1.0");
		lblVersion.setForeground(Color.WHITE);
		lblVersion.setBounds(344, 104, 80, 14);
		getContentPane().add(lblVersion);

		JLabel lblImagemR = new JLabel("");
		lblImagemR.setIcon(new ImageIcon(Sobre.class.getResource("/img/LogoMaiorProfessorRamos.png")));
		lblImagemR.setBounds(10, 37, 254, 81);
		getContentPane().add(lblImagemR);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/Logo.png")));
		setTitle("Professor Ramos - Sobre ");
		setModal(true);
		setBounds(100, 100, 450, 300);
	}
}
