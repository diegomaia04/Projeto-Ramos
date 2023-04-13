package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;

// TODO: Auto-generated Javadoc
/**
 * The Class Relatorio.
 */
public class Relatorio extends JDialog {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The content panel. */
	private final JPanel contentPanel = new JPanel();

	/** The btn relatorios concluidos. */
	private JButton btnRelatoriosConcluidos;

	/** The btn relatorios pendentes. */
	private JButton btnRelatoriosPendentes;

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			Relatorio dialog = new Relatorio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Relatorio() {
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Relatorio.class.getResource("/img/Logo.png")));
		setTitle("Professor Ramos - Relátorio");
		setBounds(100, 100, 490, 200);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(48, 48, 48));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JButton btnRelatoriosOS = new JButton("");
		btnRelatoriosOS.setContentAreaFilled(false);
		btnRelatoriosOS.setBackground(Color.DARK_GRAY);
		btnRelatoriosOS.setIcon(new ImageIcon(Relatorio.class.getResource("/img/btnTodasOS.png")));
		btnRelatoriosOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioOS();
			}
		});
		btnRelatoriosOS.setToolTipText("Todos as OS");
		btnRelatoriosOS.setBounds(65, 54, 64, 64);
		contentPanel.add(btnRelatoriosOS);

		btnRelatoriosConcluidos = new JButton("");
		btnRelatoriosConcluidos.setContentAreaFilled(false);
		btnRelatoriosConcluidos.setBackground(Color.DARK_GRAY);
		btnRelatoriosConcluidos.setIcon(new ImageIcon(Relatorio.class.getResource("/img/btnConcluidos.png")));
		btnRelatoriosConcluidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioConcluidos();
			}
		});
		btnRelatoriosConcluidos.setToolTipText("Relatorios Concluidos");
		btnRelatoriosConcluidos.setBounds(334, 54, 64, 64);
		contentPanel.add(btnRelatoriosConcluidos);

		btnRelatoriosPendentes = new JButton("");
		btnRelatoriosPendentes.setContentAreaFilled(false);
		btnRelatoriosPendentes.setBackground(Color.DARK_GRAY);
		btnRelatoriosPendentes.setOpaque(false);
		btnRelatoriosPendentes.setIcon(new ImageIcon(Relatorio.class.getResource("/img/btnPendentes.png")));
		btnRelatoriosPendentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioPendentes();
			}
		});
		btnRelatoriosPendentes.setToolTipText("Relatorios Pendentes");
		btnRelatoriosPendentes.setBounds(198, 54, 64, 64);
		contentPanel.add(btnRelatoriosPendentes);
	}

	/** The dao. */
	DAO dao = new DAO();

	/**
	 * Relatorio OS.
	 */
	private void relatorioOS() {

		Document document = new Document();

		try {

			PdfWriter.getInstance(document, new FileOutputStream("OS.pdf"));
			document.open();

			Image imagem = Image.getInstance(Relatorio.class.getResource("/img/LogoMaiorProfessorRamos.png"));
			imagem.scaleToFit(200, 110);
			imagem.setAbsolutePosition(350, 740);

			document.add(imagem);

			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);

			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Ordem De Serviço Cadastradas"));
			document.add(new Paragraph(" "));

			String relatorioOS = "select codigo,processador,memoria,hd,placa,refri,video,fan,gabinete,fonte,situacao,obs,datacad from os";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relatorioOS);
				ResultSet rs = pst.executeQuery();

				while (rs.next()) {

					document.add(new Paragraph(
							"----------------------------------------------------------------------------------------------------------------------------------"));
					Paragraph ordemdeservico = new Paragraph("Numero da Ordem De Serviço: " + rs.getString(1));
					ordemdeservico.setAlignment(Element.ALIGN_CENTER);
					document.add(ordemdeservico);
					document.add(new Paragraph(
							"----------------------------------------------------------------------------------------------------------------------------------"));
					document.add(new Paragraph(""));

					Paragraph situacao = new Paragraph("Situação: " + rs.getString(11));
					situacao.setAlignment(Element.ALIGN_CENTER);
					document.add(situacao);

					document.add(new Paragraph("-"));
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
					document.add(new Paragraph("-"));

					document.add(new Paragraph(
							"----------------------------------------------------------------------------------------------------------------------------------"));
					Paragraph datacad = new Paragraph("Data-Cadastro: " + rs.getString(13));
					datacad.setAlignment(Element.ALIGN_CENTER);
					document.add(datacad);
					document.add(new Paragraph(
							"----------------------------------------------------------------------------------------------------------------------------------"));
					document.add(new Paragraph(""));
					document.add(new Paragraph("-"));

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

	/**
	 * Relatorio pendentes.
	 */
	private void relatorioPendentes() {

		Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);

		try {

			PdfWriter.getInstance(document, new FileOutputStream("Relatorios_Pendentes.pdf"));
			document.open();

			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);

			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Relatório de OS Pendentes"));
			document.add(new Paragraph(" "));

			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("OS"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Nome_Cliente"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Telefone"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Situação"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);

			String usuarios = "select codigo,nomee,telefone,situacao from os "
					+ "where situacao = 'Aguardando Técnico' or situacao = 'Em Analise' or situacao = 'Aguardando Aprovação' or situacao = 'Aprovado' or situacao = 'Em Manutenção'";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(usuarios);
				ResultSet rs = pst.executeQuery();

				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));

				}

			} catch (Exception e) {
				System.out.println(e);
			}

			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			document.close();
		}

		try {
			Desktop.getDesktop().open(new File("Relatorios_Pendentes.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * Relatorio concluidos.
	 */
	private void relatorioConcluidos() {

		Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);

		try {

			PdfWriter.getInstance(document, new FileOutputStream("Relatorios_Entregues.pdf"));
			document.open();

			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);

			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Relatório de OS Concluidos"));
			document.add(new Paragraph(" "));

			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("OS"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Nome_Cliente"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Telefone"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Situação"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);

			String usuarios = "select codigo,nomee,telefone,situacao from os"
					+ " where situacao = 'Não Aprovado' or situacao = 'Concluído' or situacao = 'Retirado'";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(usuarios);
				ResultSet rs = pst.executeQuery();

				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));

				}

			} catch (Exception e) {
				System.out.println(e);
			}

			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			document.close();
		}

		try {
			Desktop.getDesktop().open(new File("Relatorios_Entregues.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
