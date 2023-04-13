package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private JLabel lblData;
	private JLabel lblStatus;
	private JButton btnNewButton;
	private JButton btnClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/img/Logo.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
				Date data = new Date();
				DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
				lblData.setText(formatador.format(data));
			}
		});
		setTitle("Professor Ramos - Menu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 380, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(48, 48, 48));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBounds(0, 300, 364, 61);
		panel.setBackground(new Color(30, 144, 255));
		contentPane.add(panel);
		panel.setLayout(null);
		
				lblData = new JLabel("");
				lblData.setBounds(31, 11, 233, 35);
				panel.add(lblData);
				lblData.setHorizontalAlignment(SwingConstants.CENTER);
				
						lblStatus = new JLabel("");
						lblStatus.setBounds(300, 0, 64, 64);
						panel.add(lblStatus);
						lblStatus.setIcon(new ImageIcon(Main.class.getResource("/img/dboff.png")));

		btnNewButton = new JButton("");
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setIcon(new ImageIcon(Main.class.getResource("/img/btnOS.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Telaos telaos = new Telaos();
				telaos.setVisible(true);
			}
		});
		btnNewButton.setBounds(45, 22, 128, 128);
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.setBorder(null);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(btnNewButton);

		btnClientes = new JButton("");
		btnClientes.setIcon(new ImageIcon(Main.class.getResource("/img/btnClientes.png")));
		btnClientes.setContentAreaFilled(false);
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes clientes = new Clientes();
				clientes.setVisible(true);
			}
		});
		btnClientes.setBounds(45, 161, 128, 128);
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setBorder(null);
		btnClientes.setBackground(SystemColor.menu);
		contentPane.add(btnClientes);

		JButton btnRelatrios = new JButton("");
		btnRelatrios.setToolTipText("Relatórios");
		btnRelatrios.setIcon(new ImageIcon(Main.class.getResource("/img/btnRelatorios.png")));
		btnRelatrios.setContentAreaFilled(false);
		btnRelatrios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatorio relatorio = new Relatorio();
				relatorio.setVisible(true);
			}
		});
		btnRelatrios.setBounds(197, 22, 128, 128);
		btnRelatrios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatrios.setBorder(null);
		btnRelatrios.setBackground(SystemColor.menu);
		contentPane.add(btnRelatrios);

		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);

			}
		});
		btnSobre.setContentAreaFilled(false);
		btnSobre.setIcon(new ImageIcon(Main.class.getResource("/img/btnSobre.png")));
		btnSobre.setBounds(197, 161, 128, 128);
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setBorder(null);
		btnSobre.setBackground(SystemColor.menu);
		contentPane.add(btnSobre);

	}

	DAO dao = new DAO();

	private void status() {

		try {
			Connection con = dao.conectar();
			if (con == null) {

				lblStatus.setIcon(new ImageIcon(Main.class.getResource("/img/dboff.png")));

			} else {

				lblStatus.setIcon(new ImageIcon(Main.class.getResource("/img/dbon.png")));
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}
	}
}
