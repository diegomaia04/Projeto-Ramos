package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.jdbc.Blob;
import com.toedter.calendar.JDateChooser;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

public class Telaos extends JDialog {

	/**
	 * 
	 */
	private JTextField txtFan;
	private JTextField txtFonte;
	private JTextField txtVideo;
	private JTextField txtHD;
	private JTextField txtGabinete;
	private JTextField txtRefri;
	private JTextField txtMemoria;
	private JTextField txtProcessador;
	private JButton btnPesquisar;
	private JButton btnUpdate;
	private JTextArea txtaObs;
	private JButton btnAdicionar;
	private JButton btnDelete;
	private JComboBox<Object> cboStatus;
	private JLabel lblID;
	private JTextField txtId;
	private JTextField txtCli;
	private JTextField txtFone;
	private JLabel lblTelefone;
	private JPanel panel_3;
	private JTextField txtClienteO;
	private JScrollPane scrollPane_1;
	private JTable table;
	private JLabel lblFoto3;
	private JLabel lblFoto2;
	private JLabel lblFoto4;
	private JButton btnSelect1;
	private JButton btnSelect2;
	private JButton btnSelect3;
	private JButton btnSelect4;
	private JLabel lblFoto1;
	private JButton btnImprimir;
	private JTextField txtClienteT;
	private JTable table_2;

	private static final long serialVersionUID = 1L;
	private JTextField txtOsNumero;
	private JTextField txtPlaca;
	private JDateChooser dateEntrada;
	private FileInputStream fis;
	private int tamanho;
	private FileInputStream fis2;
	private int tamanho2;
	private FileInputStream fis3;
	private int tamanho3;
	private FileInputStream fis4;
	private int tamanho4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Telaos dialog = new Telaos();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Telaos() {
		setResizable(false);
		getContentPane().setForeground(SystemColor.menu);

		setIconImage(Toolkit.getDefaultToolkit().getImage(Telaos.class.getResource("/img/Logo.png")));
		getContentPane().setBackground(new Color(48, 48, 48));
		setTitle("Professor Ramos - Ordem de Serviço");
		setBounds(100, 100, 1220, 681);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblOSNumero = new JLabel("O.S Número:");
		lblOSNumero.setHorizontalAlignment(SwingConstants.CENTER);
		lblOSNumero.setForeground(SystemColor.menu);
		lblOSNumero.setFont(new Font("Arial", Font.BOLD, 12));
		lblOSNumero.setBounds(48, 10, 93, 14);
		getContentPane().add(lblOSNumero);

		JLabel lblDataEntrada = new JLabel("Data Entrada:");
		lblDataEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataEntrada.setForeground(SystemColor.menu);
		lblDataEntrada.setFont(new Font("Arial", Font.BOLD, 12));
		lblDataEntrada.setBounds(281, 14, 97, 14);
		getContentPane().add(lblDataEntrada);

		txtOsNumero = new JTextField();
		txtOsNumero.setBackground(Color.WHITE);
		txtOsNumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321-";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtOsNumero.setHorizontalAlignment(SwingConstants.CENTER);
		txtOsNumero.setFont(new Font("Arial", Font.PLAIN, 15));
		txtOsNumero.setBounds(26, 31, 136, 20);
		getContentPane().add(txtOsNumero);
		txtOsNumero.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(26, 264, 615, 336);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel lblPlacaMae = new JLabel("Placa-Mãe");
		lblPlacaMae.setForeground(SystemColor.menu);
		lblPlacaMae.setFont(new Font("Arial", Font.BOLD, 12));
		lblPlacaMae.setBounds(76, 120, 75, 14);
		panel_2.add(lblPlacaMae);

		txtPlaca = new JTextField();
		txtPlaca.setFont(new Font("Arial", Font.PLAIN, 11));
		txtPlaca.setBounds(76, 138, 133, 20);
		panel_2.add(txtPlaca);
		txtPlaca.setColumns(10);

		dateEntrada = new JDateChooser();
		dateEntrada.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		dateEntrada.setEnabled(false);
		dateEntrada.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				String caracteres = "0987654321.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		dateEntrada.setBounds(239, 31, 150, 20);
		getContentPane().add(dateEntrada);

		RestrictedTextField os = new RestrictedTextField(txtOsNumero);
		os.setLimit(250);

		RestrictedTextField problema = new RestrictedTextField(txtPlaca);
		problema.setLimit(40);

		JLabel lblFan = new JLabel("Fan(s)");
		lblFan.setForeground(SystemColor.menu);
		lblFan.setFont(new Font("Arial", Font.BOLD, 12));
		lblFan.setBounds(76, 169, 75, 14);
		panel_2.add(lblFan);

		txtFan = new JTextField();
		txtFan.setFont(new Font("Arial", Font.PLAIN, 11));
		txtFan.setColumns(10);
		txtFan.setBounds(76, 184, 133, 20);
		panel_2.add(txtFan);

		JLabel lblFonte = new JLabel("Fonte ");
		lblFonte.setForeground(SystemColor.menu);
		lblFonte.setFont(new Font("Arial", Font.BOLD, 12));
		lblFonte.setBounds(376, 168, 75, 14);
		panel_2.add(lblFonte);

		txtFonte = new JTextField();
		txtFonte.setFont(new Font("Arial", Font.PLAIN, 11));
		txtFonte.setColumns(10);
		txtFonte.setBounds(376, 184, 133, 20);
		panel_2.add(txtFonte);

		JLabel lblPlacaV = new JLabel("Placa-Vídeo");
		lblPlacaV.setForeground(SystemColor.menu);
		lblPlacaV.setFont(new Font("Arial", Font.BOLD, 12));
		lblPlacaV.setBounds(376, 120, 75, 14);
		panel_2.add(lblPlacaV);

		txtVideo = new JTextField();
		txtVideo.setFont(new Font("Arial", Font.PLAIN, 11));
		txtVideo.setColumns(10);
		txtVideo.setBounds(376, 138, 133, 20);
		panel_2.add(txtVideo);

		JLabel lblHdSsd = new JLabel("HD ou SSD");
		lblHdSsd.setForeground(SystemColor.menu);
		lblHdSsd.setFont(new Font("Arial", Font.BOLD, 12));
		lblHdSsd.setBounds(376, 75, 75, 14);
		panel_2.add(lblHdSsd);

		txtHD = new JTextField();
		txtHD.setFont(new Font("Arial", Font.PLAIN, 11));
		txtHD.setColumns(10);
		txtHD.setBounds(376, 89, 133, 20);
		panel_2.add(txtHD);

		txtGabinete = new JTextField();
		txtGabinete.setFont(new Font("Arial", Font.PLAIN, 11));
		txtGabinete.setColumns(10);
		txtGabinete.setBounds(227, 184, 133, 20);
		panel_2.add(txtGabinete);

		JLabel lblGabinete = new JLabel("Gabinete");
		lblGabinete.setForeground(SystemColor.menu);
		lblGabinete.setFont(new Font("Arial", Font.BOLD, 12));
		lblGabinete.setBounds(227, 166, 75, 14);
		panel_2.add(lblGabinete);

		txtRefri = new JTextField();
		txtRefri.setFont(new Font("Arial", Font.PLAIN, 11));
		txtRefri.setColumns(10);
		txtRefri.setBounds(227, 138, 133, 20);
		panel_2.add(txtRefri);

		JLabel lblRefri = new JLabel("Refrigeração");
		lblRefri.setForeground(SystemColor.menu);
		lblRefri.setFont(new Font("Arial", Font.BOLD, 12));
		lblRefri.setBounds(227, 123, 75, 14);
		panel_2.add(lblRefri);

		txtMemoria = new JTextField();
		txtMemoria.setFont(new Font("Arial", Font.PLAIN, 11));
		txtMemoria.setColumns(10);
		txtMemoria.setBounds(227, 89, 133, 20);
		panel_2.add(txtMemoria);

		JLabel lblMemoria = new JLabel("Memória");
		lblMemoria.setForeground(SystemColor.menu);
		lblMemoria.setHorizontalAlignment(SwingConstants.LEFT);
		lblMemoria.setFont(new Font("Arial", Font.BOLD, 12));
		lblMemoria.setBounds(227, 75, 75, 14);
		panel_2.add(lblMemoria);

		txtProcessador = new JTextField();
		txtProcessador.setFont(new Font("Arial", Font.PLAIN, 11));
		txtProcessador.setColumns(10);
		txtProcessador.setBounds(76, 89, 133, 20);
		panel_2.add(txtProcessador);

		JLabel lblProcessador = new JLabel("Processador");
		lblProcessador.setForeground(SystemColor.menu);
		lblProcessador.setFont(new Font("Arial", Font.BOLD, 12));
		lblProcessador.setBounds(76, 73, 75, 14);
		panel_2.add(lblProcessador);

		txtaObs = new JTextArea();
		txtaObs.setLineWrap(true);
		txtaObs.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtaObs.setBounds(111, 230, 362, 95);
		panel_2.add(txtaObs);

		JLabel lblExtraObservacoes = new JLabel("Extra e Observações ");
		lblExtraObservacoes.setForeground(SystemColor.menu);
		lblExtraObservacoes.setFont(new Font("Arial", Font.BOLD, 12));
		lblExtraObservacoes.setBounds(220, 215, 143, 14);
		panel_2.add(lblExtraObservacoes);

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setForeground(SystemColor.menu);
		lblCliente.setBounds(30, 31, 47, 14);
		panel_2.add(lblCliente);
		lblCliente.setFont(new Font("Arial", Font.BOLD, 12));

		txtCli = new JTextField();
		txtCli.setEditable(false);
		txtCli.setFont(new Font("Arial", Font.PLAIN, 11));
		txtCli.setColumns(10);
		txtCli.setBounds(75, 27, 240, 20);
		panel_2.add(txtCli);

		txtFone = new JTextField();
		txtFone.setEditable(false);
		txtFone.setFont(new Font("Arial", Font.PLAIN, 11));
		txtFone.setColumns(10);
		txtFone.setBounds(374, 29, 121, 20);
		panel_2.add(txtFone);

		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setForeground(SystemColor.menu);
		lblTelefone.setFont(new Font("Arial", Font.BOLD, 12));
		lblTelefone.setBounds(319, 31, 60, 14);
		panel_2.add(lblTelefone);

		JLabel lblStatus = new JLabel("      Status:");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setForeground(SystemColor.menu);
		lblStatus.setFont(new Font("Arial", Font.BOLD, 12));
		lblStatus.setBounds(471, 13, 64, 14);
		getContentPane().add(lblStatus);

		cboStatus = new JComboBox<Object>();
		cboStatus.setModel(new DefaultComboBoxModel<Object>(new String[] { "", "Pendente", "Em Análise",
				"Em Manutenção", "A Espera do Cliente", "Concluído", "Retirado" }));
		cboStatus.setToolTipText("Pendente Resolvido");
		cboStatus.setFont(new Font("Arial", Font.PLAIN, 11));
		cboStatus.setBounds(424, 31, 159, 20);
		getContentPane().add(cboStatus);

		btnPesquisar = new JButton("");
		btnPesquisar.setIcon(new ImageIcon(Telaos.class.getResource("/img/btnSearch.png")));
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setContentAreaFilled(false);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarOS();
				buscarFoto1();
				buscarFoto2();
				buscarFoto3();
				buscarFoto4();

			}
		});

		btnPesquisar.setBorder(null);
		btnPesquisar.setToolTipText("Pesquisar OS");
		btnPesquisar.setBounds(172, 10, 48, 48);
		getContentPane().add(btnPesquisar);

		btnAdicionar = new JButton("");
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBackground(new Color(240, 240, 240));
		btnAdicionar.setForeground(new Color(48, 48, 48));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addOs();

			}
		});
		btnAdicionar.setIcon(new ImageIcon(Telaos.class.getResource("/img/btnAddCliente.png")));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setBorder(null);
		btnAdicionar.setBounds(668, 556, 64, 64);
		getContentPane().add(btnAdicionar);

		btnUpdate = new JButton("");
		btnUpdate.setEnabled(false);
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateOs();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setIcon(new ImageIcon(Telaos.class.getResource("/img/btnUpdate.png")));
		btnUpdate.setToolTipText("Atualizar");
		btnUpdate.setBorder(null);
		btnUpdate.setBounds(822, 556, 64, 64);
		getContentPane().add(btnUpdate);

		btnDelete = new JButton("");
		btnDelete.setEnabled(false);
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setContentAreaFilled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirOs();
			}
		});
		btnDelete.setIcon(new ImageIcon(Telaos.class.getResource("/img/delet icon.png")));
		btnDelete.setToolTipText("Deletar OS");
		btnDelete.setBorder(null);
		btnDelete.setBounds(978, 556, 64, 64);
		getContentPane().add(btnDelete);

		JPanel panel_1 = new JPanel();
		panel_1.setForeground(SystemColor.menu);
		panel_1.setBounds(668, 11, 526, 534);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBackground(Color.DARK_GRAY);

		lblFoto3 = new JLabel("");
		lblFoto3.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblFoto3.setAlignmentX(0.5f);
		lblFoto3.setBounds(23, 276, 230, 193);
		panel_1.add(lblFoto3);

		lblFoto2 = new JLabel("");
		lblFoto2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblFoto2.setAlignmentX(0.5f);
		lblFoto2.setBounds(279, 29, 230, 193);
		panel_1.add(lblFoto2);

		lblFoto4 = new JLabel("");
		lblFoto4.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblFoto4.setAlignmentX(0.5f);
		lblFoto4.setBounds(279, 276, 230, 193);
		panel_1.add(lblFoto4);

		btnSelect2 = new JButton("Selecionar Foto");
		btnSelect2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarFoto2();
			}
		});
		btnSelect2.setBounds(319, 233, 151, 32);
		panel_1.add(btnSelect2);

		btnSelect3 = new JButton("Selecionar Foto");
		btnSelect3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarFoto3();
			}
		});
		btnSelect3.setBounds(63, 480, 151, 32);
		panel_1.add(btnSelect3);

		btnSelect4 = new JButton("Selecionar Foto");
		btnSelect4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarFoto4();
			}
		});
		btnSelect4.setBounds(319, 480, 151, 32);
		panel_1.add(btnSelect4);

		lblFoto1 = new JLabel("");
		lblFoto1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblFoto1.setAlignmentX(0.5f);
		lblFoto1.setBounds(23, 29, 230, 193);
		panel_1.add(lblFoto1);

		btnSelect1 = new JButton("Selecionar Foto");
		btnSelect1.setBounds(63, 233, 151, 32);
		panel_1.add(btnSelect1);
		btnSelect1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarFoto();
			}
		});

		panel_3 = new JPanel();
		panel_3.setBackground(Color.GRAY);
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"N\u00FAmero da OS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(26, 60, 316, 193);
		getContentPane().add(panel_3);

		txtClienteO = new JTextField();
		txtClienteO.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tabelaOS();
			}
		});
		txtClienteO.setColumns(10);
		txtClienteO.setBounds(10, 23, 152, 20);
		panel_3.add(txtClienteO);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBounds(10, 54, 296, 128);
		panel_3.add(scrollPane_1);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos1();
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID da OS", "Cliente", "Telefone" }));
		scrollPane_1.setViewportView(table);

		RestrictedTextField validar3 = new RestrictedTextField(txtFone);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(534, 28, 47, 20);
		panel_2.add(txtId);
		txtId.setColumns(10);

		lblID = new JLabel("ID:");
		lblID.setForeground(SystemColor.menu);
		lblID.setBackground(SystemColor.menu);
		lblID.setBounds(505, 31, 24, 14);
		panel_2.add(lblID);

		JButton btnLimpar = new JButton("");
		btnLimpar.setIcon(new ImageIcon(Telaos.class.getResource("/img/btnLimpar.png")));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setToolTipText("Limpar Campos");
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.setBorder(null);
		btnLimpar.setBounds(610, 10, 48, 48);
		getContentPane().add(btnLimpar);

		btnImprimir = new JButton("");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioOS();
			}
		});
		btnImprimir.setIcon(new ImageIcon(Telaos.class.getResource("/img/btnImprimir.png")));
		btnImprimir.setToolTipText("Atualizar");
		btnImprimir.setEnabled(false);
		btnImprimir.setContentAreaFilled(false);
		btnImprimir.setBorder(null);
		btnImprimir.setBounds(1130, 556, 64, 64);
		getContentPane().add(btnImprimir);

		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.GRAY);
		panel2.setLayout(null);
		panel2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Clientes",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel2.setBounds(356, 60, 285, 193);
		getContentPane().add(panel2);

		txtClienteT = new JTextField();
		txtClienteT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisaAvançada();
			}
		});
		txtClienteT.setColumns(10);
		txtClienteT.setBounds(10, 23, 152, 20);
		panel2.add(txtClienteT);

		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setEnabled(false);
		scrollPane_1_1.setBounds(10, 54, 265, 128);
		panel2.add(scrollPane_1_1);

		table_2 = new JTable();
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
			}
		});
		table_2.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id do Cliente", "Nome do Cliente" }));
		scrollPane_1_1.setViewportView(table_2);
		validar3.setLimit(250);
		validar3.setOnlyNums(true);

		RestrictedTextField Os = new RestrictedTextField(txtOsNumero);
		Os.setOnlyNums(true);

		RestrictedTextField Cliente = new RestrictedTextField(txtCli);
		Cliente.setLimit(250);
		Cliente.setAcceptSpace(true);

		RestrictedTextField ID = new RestrictedTextField(txtId);
		ID.setLimit(250);

	}

	DAO dao = new DAO();

	private void pesquisaAvançada() {
		String read3 = "select id as Id, nome as cliente, telefone as contato from clientes where nome like ?";

		try {
			Connection con = dao.conectar();

			PreparedStatement pst = con.prepareStatement(read3);
			pst.setString(1, txtClienteT.getText() + "%"); // ATENÇÃO TEM Q SER DESSE MODO "%"
			ResultSet rs = pst.executeQuery();

			// uso da Biblioteca rx2xml para encher a tabela
			table_2.setModel(DbUtils.resultSetToTableModel(rs)); /// so da pra usar isso dai se estiver a biblioteca lá

			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}
	}

	private void tabelaOS() {
		String read4 = "select codigo as ID_Da_OS, nomee as Cliente,telefone as Telefone, id as ID_Cliente from os where nomee like ?";

		try {
			Connection con = dao.conectar();

			PreparedStatement pst = con.prepareStatement(read4);
			pst.setString(1, txtClienteO.getText() + "%");
			ResultSet rs = pst.executeQuery();

			table.setModel(DbUtils.resultSetToTableModel(rs));

			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}
	}

	private void pesquisarOS() {

		if (txtOsNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O Número da Ordem de Serviço");
			txtOsNumero.requestFocus();
		} else {
			String read = "select * from os where codigo = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(read);
				pst.setString(1, txtOsNumero.getText());
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {

					txtProcessador.setText(rs.getString(2));
					txtMemoria.setText(rs.getString(3));
					txtHD.setText(rs.getString(4));
					txtPlaca.setText(rs.getString(5));
					txtRefri.setText(rs.getString(6));
					txtVideo.setText(rs.getString(7));
					txtFan.setText(rs.getString(8));
					txtGabinete.setText(rs.getString(9));
					txtFonte.setText(rs.getString(10));
					cboStatus.setSelectedItem(rs.getString(11));
					txtaObs.setText(rs.getString(12));
					String setarData = rs.getString(15);
					Date dataFormatada = new SimpleDateFormat("yyyy-MM-dd").parse(setarData);
					dateEntrada.setDate(dataFormatada);
					txtCli.setText(rs.getString(13));
					txtFone.setText(rs.getString(14));
					txtId.setText(rs.getString(20));

					txtCli.setEnabled(false);
					txtFone.setEnabled(false);
					txtId.setEnabled(false);
					btnAdicionar.setEnabled(false);
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);
					btnImprimir.setEnabled(true);
					

				} else {
					JOptionPane.showMessageDialog(null, "Ordem de Serviço não cadastrada");
					limpar();

				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	private void buscarFoto1() {
		String read = "select foto1 from os where codigo = ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, txtOsNumero.getText());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {

				Blob blob = (Blob) rs.getBlob(1);
				byte[] img = blob.getBytes(1, (int) blob.length());
				BufferedImage imagem = null;
				try {

					imagem = ImageIO.read(new ByteArrayInputStream(img));
				} catch (Exception e) {
					System.out.println(e);
				}

				ImageIcon icone = new ImageIcon(imagem);
				Icon foto = new ImageIcon(icone.getImage().getScaledInstance(lblFoto1.getWidth(), lblFoto1.getHeight(),
						Image.SCALE_SMOOTH));
				lblFoto1.setIcon(foto);

			} else {
				JOptionPane.showMessageDialog(null, "Foto(s) não cadastrada(s)");

			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void buscarFoto2() {
		String read = "select foto2 from os where codigo = ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, txtOsNumero.getText());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {

				Blob blob2 = (Blob) rs.getBlob(1);
				byte[] img2 = blob2.getBytes(1, (int) blob2.length());
				BufferedImage imagem2 = null;
				try {

					imagem2 = ImageIO.read(new ByteArrayInputStream(img2));
				} catch (Exception e) {
					System.out.println(e);
				}

				ImageIcon icone2 = new ImageIcon(imagem2);
				Icon foto2 = new ImageIcon(icone2.getImage().getScaledInstance(lblFoto2.getWidth(),
						lblFoto2.getHeight(), Image.SCALE_SMOOTH));
				lblFoto2.setIcon(foto2);

			} else {

			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void buscarFoto3() {
		String read = "select foto3 from os where codigo = ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, txtOsNumero.getText());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {

				Blob blob3 = (Blob) rs.getBlob(1);
				byte[] img3 = blob3.getBytes(1, (int) blob3.length());
				BufferedImage imagem3 = null;
				try {

					imagem3 = ImageIO.read(new ByteArrayInputStream(img3));
				} catch (Exception e) {
					System.out.println(e);
				}

				ImageIcon icone3 = new ImageIcon(imagem3);
				Icon foto3 = new ImageIcon(icone3.getImage().getScaledInstance(lblFoto3.getWidth(),
						lblFoto3.getHeight(), Image.SCALE_SMOOTH));
				lblFoto3.setIcon(foto3);

			} else {

			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void buscarFoto4() {
		String read = "select foto4 from os where codigo = ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, txtOsNumero.getText());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {

				Blob blob4 = (Blob) rs.getBlob(1);
				byte[] img4 = blob4.getBytes(1, (int) blob4.length());
				BufferedImage imagem4 = null;
				try {

					imagem4 = ImageIO.read(new ByteArrayInputStream(img4));
				} catch (Exception e) {
					System.out.println(e);
				}

				ImageIcon icone4 = new ImageIcon(imagem4);
				Icon foto4 = new ImageIcon(icone4.getImage().getScaledInstance(lblFoto4.getWidth(),
						lblFoto4.getHeight(), Image.SCALE_SMOOTH));
				lblFoto4.setIcon(foto4);

			} else {

			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void addOs() {

		if (txtCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome dos Clientes e seus Dados");
			txtOsNumero.requestFocus();
		} else {

			String insert = "insert into os (processador, memoria, hd, placa, refri, video, fan, gabinete,fonte,situacao,obs,id,nomee,telefone,foto1,foto2,foto3,foto4) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {

				Connection con = dao.conectar();

				PreparedStatement pst = con.prepareStatement(insert);
				pst.setString(1, txtProcessador.getText());
				pst.setString(2, txtMemoria.getText());
				pst.setString(3, txtHD.getText());
				pst.setString(4, txtPlaca.getText());
				pst.setString(5, txtRefri.getText());
				pst.setString(6, txtVideo.getText());
				pst.setString(7, txtFan.getText());
				pst.setString(8, txtGabinete.getText());
				pst.setString(9, txtFonte.getText());
				pst.setString(10, cboStatus.getSelectedItem().toString());
				pst.setString(11, txtaObs.getText());
				pst.setString(12, txtId.getText());
				pst.setString(13, txtCli.getText());
				pst.setString(14, txtFone.getText());
				pst.setBlob(15, fis, tamanho);
				pst.setBlob(16, fis2, tamanho2);
				pst.setBlob(17, fis3, tamanho3);
				pst.setBlob(18, fis4, tamanho4);

				btnImprimir.setEnabled(true);

				int confirma1 = pst.executeUpdate();

				if (confirma1 == 1) {
					JOptionPane.showMessageDialog(null, "Ordem de serviço Cadastrada Com Sucesso!!!");

					limpar();
					recuperarOS();
					btnImprimir.setEnabled(true);

				} else {
					JOptionPane.showMessageDialog(null, "ATENÇÃO ORDEM DE SERVIÇO NÃO CADASTRADO");

				}

				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) { // personalizar os erros do java
				JOptionPane.showMessageDialog(null, "ESSA ORDEM DE SERVIÇO JÁ ESTÁ CADASTRADO!!");

			}

			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Esse Cliente não Existe");

			}
		}
	}

	private void updateOs() throws SQLException {

		String update = "update os set processador  = ? ,memoria = ? ,hd = ? ,placa = ? ,refri = ? ,video  = ?,fan = ? ,gabinete = ?,fonte = ?,situacao = ?,obs = ?, id = ?, nomee = ?, telefone = ?, foto1 = ?, foto2 = ?, foto3 = ?, foto4 = ? where codigo = ?";
		try {
			Connection con = dao.conectar();

			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, txtProcessador.getText());
			pst.setString(2, txtMemoria.getText());
			pst.setString(3, txtHD.getText());
			pst.setString(4, txtPlaca.getText());
			pst.setString(5, txtRefri.getText());
			pst.setString(6, txtVideo.getText());
			pst.setString(7, txtFan.getText());
			pst.setString(8, txtGabinete.getText());
			pst.setString(9, txtFonte.getText());
			pst.setString(10, cboStatus.getSelectedItem().toString());
			pst.setString(11, txtaObs.getText());
			pst.setString(12, txtId.getText());
			pst.setString(13, txtCli.getText());
			pst.setString(14, txtFone.getText());
			pst.setBlob(15, fis, tamanho);
			pst.setBlob(16, fis2, tamanho2);
			pst.setBlob(17, fis3, tamanho3);
			pst.setBlob(18, fis4, tamanho4);
			pst.setString(19, txtOsNumero.getText());

			int confirma = pst.executeUpdate();

			if (confirma == 1) {
				JOptionPane.showMessageDialog(null, "Ordem De Serviço Atualizada");
				btnPesquisar.setEnabled(true);
				limpar();
			}

			con.close();
		} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
			JOptionPane.showMessageDialog(null, "Essa OS Já Existe!!");

		}
	}

	private void excluirOs() {

		int confirma = JOptionPane.showConfirmDialog(null, "Deseja Excluir Essa Ordem De Serviço??", "Exluir Ordem!!",
				JOptionPane.YES_NO_OPTION);

		if (confirma == JOptionPane.YES_NO_OPTION) {

			String delete = "delete from os where codigo = ?";

			try {

				Connection con = dao.conectar();

				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtOsNumero.getText());

				int confirmaExcluir = pst.executeUpdate();

				if (confirmaExcluir == 1) {
					JOptionPane.showMessageDialog(null, " Ordem De Serviço Excluida Com Sucesso");

					limpar();

				} else {
					JOptionPane.showMessageDialog(null, "Ordem de Serviço não deletada");

				}

				con.close();

			} catch (Exception e) {
				System.out.println(e);

			}

		}

	}

	private void setarCampos() {
		int setar = table_2.getSelectedRow();
		txtId.setText(table_2.getModel().getValueAt(setar, 0).toString());
		txtCli.setText(table_2.getModel().getValueAt(setar, 1).toString());
		txtClienteT.setText(table_2.getModel().getValueAt(setar, 1).toString());
		txtFone.setText(table_2.getModel().getValueAt(setar, 2).toString());

	}

	private void setarCampos1() {
		int setar1 = table.getSelectedRow();

		txtOsNumero.setText(table.getModel().getValueAt(setar1, 0).toString());
		txtCli.setText(table.getModel().getValueAt(setar1, 1).toString());
		txtFone.setText(table.getModel().getValueAt(setar1, 2).toString());
		txtId.setText(table.getModel().getValueAt(setar1, 3).toString());

	}

	private void selecionarFoto() {

		JFileChooser jfc = new JFileChooser();

		jfc.setDialogTitle("Selecionar arquivo");

		jfc.setFileFilter(
				new FileNameExtensionFilter("Arquivo de Imagens(*.PNG, *.JPG, *.JPEG)", "png", "jpg", "jpeg"));

		int resultado = jfc.showOpenDialog(this);

		if (resultado == JFileChooser.APPROVE_OPTION) {

			try {

				fis = new FileInputStream(jfc.getSelectedFile());

				tamanho = (int) jfc.getSelectedFile().length();

				Image foto = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(lblFoto1.getWidth(),
						lblFoto1.getHeight(), Image.SCALE_SMOOTH);

				lblFoto1.setIcon(new ImageIcon(foto));
				lblFoto1.updateUI();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void selecionarFoto2() {

		JFileChooser jfc2 = new JFileChooser();
		jfc2.setDialogTitle("Selecionar arquivo");
		jfc2.setFileFilter(
				new FileNameExtensionFilter("Arquivo de Imagens(*.PNG, *.JPG, *.JPEG)", "png", "jpg", "jpeg"));
		int resultado = jfc2.showOpenDialog(this);
		if (resultado == JFileChooser.APPROVE_OPTION) {

			try {

				fis2 = new FileInputStream(jfc2.getSelectedFile());
				tamanho2 = (int) jfc2.getSelectedFile().length();
				Image foto2 = ImageIO.read(jfc2.getSelectedFile()).getScaledInstance(lblFoto2.getWidth(),
						lblFoto2.getHeight(), Image.SCALE_SMOOTH);
				lblFoto2.setIcon(new ImageIcon(foto2));
				lblFoto2.updateUI();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void selecionarFoto3() {

		JFileChooser jfc3 = new JFileChooser();

		jfc3.setDialogTitle("Selecionar arquivo");

		jfc3.setFileFilter(
				new FileNameExtensionFilter("Arquivo de Imagens(*.PNG, *.JPG, *.JPEG)", "png", "jpg", "jpeg"));

		int resultado = jfc3.showOpenDialog(this);

		if (resultado == JFileChooser.APPROVE_OPTION) {

			try {

				fis3 = new FileInputStream(jfc3.getSelectedFile());

				tamanho3 = (int) jfc3.getSelectedFile().length();

				Image foto3 = ImageIO.read(jfc3.getSelectedFile()).getScaledInstance(lblFoto3.getWidth(),
						lblFoto3.getHeight(), Image.SCALE_SMOOTH);

				lblFoto3.setIcon(new ImageIcon(foto3));
				lblFoto3.updateUI();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void selecionarFoto4() {

		JFileChooser jfc4 = new JFileChooser();

		jfc4.setDialogTitle("Selecionar arquivo");

		jfc4.setFileFilter(
				new FileNameExtensionFilter("Arquivo de Imagens(*.PNG, *.JPG, *.JPEG)", "png", "jpg", "jpeg"));

		int resultado = jfc4.showOpenDialog(this);

		if (resultado == JFileChooser.APPROVE_OPTION) {

			try {

				fis4 = new FileInputStream(jfc4.getSelectedFile());
				tamanho4 = (int) jfc4.getSelectedFile().length();

				Image foto4 = ImageIO.read(jfc4.getSelectedFile()).getScaledInstance(lblFoto4.getWidth(),
						lblFoto4.getHeight(), Image.SCALE_SMOOTH);

				lblFoto4.setIcon(new ImageIcon(foto4));
				lblFoto4.updateUI();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void recuperarOS() {
		String readOS = "select max(codigo) from os";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(readOS);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				txtOsNumero.setText(rs.getString(1));
				txtId.setText(rs.getString(2));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void relatorioOS() {

		Document document = new Document();

		try {

			PdfWriter.getInstance(document, new FileOutputStream("OS.pdf"));
			document.open();

			com.itextpdf.text.Image imagem1 = com.itextpdf.text.Image
					.getInstance(Telaos.class.getResource("/img/LogoMaiorProfessorRamos.png"));
			imagem1.scaleToFit(200, 110);
			imagem1.setAbsolutePosition(350, 740);
			document.add(imagem1);

			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);

			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Relatório Da Ordem De Serviço"));
			document.add(new Paragraph(" "));

			String relatorioOS = "select * from os where codigo  = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relatorioOS);
				pst.setString(1, txtOsNumero.getText());
				ResultSet rs = pst.executeQuery();

				while (rs.next()) {

					document.add(new Paragraph(
							"----------------------------------------------------------------------------------------------------------------------------------"));
					Paragraph ordemdeservico = new Paragraph("Número da Ordem De Serviço: " + rs.getString(1));
					ordemdeservico.setAlignment(Element.ALIGN_CENTER);
					document.add(ordemdeservico);
					document.add(new Paragraph(
							"----------------------------------------------------------------------------------------------------------------------------------"));
					document.add(new Paragraph(""));

					Paragraph situacao = new Paragraph("Situação: " + rs.getString(11));
					situacao.setAlignment(Element.ALIGN_CENTER);
					document.add(situacao);

					document.add(new Paragraph("Dados Do Cliente: "));

					Paragraph nome = new Paragraph("Nome: " + rs.getString(13));
					nome.setAlignment(Element.ALIGN_LEFT);
					document.add(nome);

					Paragraph telefone = new Paragraph("Telefone: " + rs.getString(14));
					telefone.setAlignment(Element.ALIGN_LEFT);
					document.add(telefone);

					document.add(new Paragraph(
							"----------------------------------------------------------------------------"));

					document.add(new Paragraph("Peças"));

					document.add(new Paragraph(
							"----------------------------------------------------------------------------"));

					Paragraph processador = new Paragraph("Processador: " + rs.getString(2));
					processador.setAlignment(Element.ALIGN_LEFT);
					document.add(processador);

					Paragraph memoria = new Paragraph("Memoria: " + rs.getString(3));
					memoria.setAlignment(Element.ALIGN_LEFT);
					document.add(memoria);

					Paragraph hd = new Paragraph("HD: " + rs.getString(4));
					hd.setAlignment(Element.ALIGN_LEFT);
					document.add(hd);

					Paragraph placa = new Paragraph("Placa Mãe: " + rs.getString(5));
					placa.setAlignment(Element.ALIGN_LEFT);
					document.add(placa);

					Paragraph refri = new Paragraph("Refrigeração: " + rs.getString(6));
					refri.setAlignment(Element.ALIGN_LEFT);
					document.add(refri);

					Paragraph video = new Paragraph("Placa de Video: " + rs.getString(7));
					video.setAlignment(Element.ALIGN_LEFT);
					document.add(video);

					Paragraph fan = new Paragraph("Fan: " + rs.getString(8));
					fan.setAlignment(Element.ALIGN_LEFT);
					document.add(fan);

					Paragraph gabinete = new Paragraph("Gabinete: " + rs.getString(9));
					gabinete.setAlignment(Element.ALIGN_LEFT);
					document.add(gabinete);

					Paragraph fonte = new Paragraph("Fonte: " + rs.getString(10));
					fonte.setAlignment(Element.ALIGN_LEFT);
					document.add(fonte);

					document.add(new Paragraph(""));
					Paragraph obs = new Paragraph("Observação: " + rs.getString(12));
					obs.setAlignment(Element.ALIGN_LEFT);
					document.add(obs);
					document.add(new Paragraph(" "));

				}

			} catch (Exception e) {
				System.out.println(e);
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			document.close();
		}

		try {
			Desktop.getDesktop().open(new File("OS.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void limpar() {

		txtOsNumero.setText(null);
		txtClienteO.setText(null);
		txtClienteT.setText(null);
		txtId.setText(null);
		txtCli.setText(null);
		txtCli.requestFocus();
		txtFone.setText(null);
		txtProcessador.setText(null);
		txtMemoria.setText(null);
		txtHD.setText(null);
		txtPlaca.setText(null);
		txtRefri.setText(null);
		txtVideo.setText(null);
		txtFan.setText(null);
		txtGabinete.setText(null);
		txtFonte.setText(null);
		txtaObs.setText(null);
		cboStatus.setSelectedItem("");
		dateEntrada.setDate(null);
		btnAdicionar.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		btnImprimir.setEnabled(false);
		lblFoto1.setIcon(null);
		lblFoto2.setIcon(null);
		lblFoto3.setIcon(null);
		lblFoto4.setIcon(null);
		((DefaultTableModel) table.getModel()).setRowCount(0);
		((DefaultTableModel) table_2.getModel()).setRowCount(0);
	}
}
