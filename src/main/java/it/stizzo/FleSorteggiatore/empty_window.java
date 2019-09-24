package it.stizzo.FleSorteggiatore;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class empty_window extends JFrame{

	private static final long serialVersionUID = -3596060649888940443L;
	private JTextField txtNumGironi;
	private JTextField TxtSquadrePerGirone;
	private JTextField TxtPercorsoFileSquadre;
	private JTextField txtSquadreCaricate;
	private JTextField txtLegheIndividuate;
	private Sorteggiatore sorteggiatore;
	private JTextArea txtRisultato;
	private JButton btnSucc;
	private JButton btnPrev;
	private JLabel icona0;
	private JLabel icona1;
	private JLabel icona2;
	
	private int visCorrente = 0;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					empty_window window = new empty_window();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public empty_window() {
		setResizable(false);
		this.setTitle("Sorteggiatore FLE by Stizzo");
		this.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sorteggiatore Fantalega Europea");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Sitka Small", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel.setBounds(10, 10, 585, 51);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNumeroGironi = new JLabel("Numero gironi:");
		lblNumeroGironi.setBounds(10, 259, 171, 13);
		getContentPane().add(lblNumeroGironi);
		
		txtNumGironi = new JTextField();
		txtNumGironi.setBounds(191, 256, 96, 19);
		getContentPane().add(txtNumGironi);
		txtNumGironi.setColumns(10);
		
		JLabel lblSquadrePerGirone = new JLabel("Squadre per girone:");
		lblSquadrePerGirone.setBounds(10, 282, 171, 13);
		getContentPane().add(lblSquadrePerGirone);
		
		TxtSquadrePerGirone = new JTextField();
		TxtSquadrePerGirone.setBounds(191, 279, 96, 19);
		getContentPane().add(TxtSquadrePerGirone);
		TxtSquadrePerGirone.setColumns(10);
		
		JLabel lblSelezionaFileSquadre = new JLabel("Seleziona file squadre:");
		lblSelezionaFileSquadre.setBounds(10, 230, 126, 13);
		getContentPane().add(lblSelezionaFileSquadre);
		
		TxtPercorsoFileSquadre = new JTextField();
		TxtPercorsoFileSquadre.setBounds(146, 227, 334, 19);
		getContentPane().add(TxtPercorsoFileSquadre);
		TxtPercorsoFileSquadre.setColumns(10);
		
		JButton TxtApri = new JButton("Apri...");
		TxtApri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Seleziona il file delle squadre");
				//chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.showOpenDialog(null);
				TxtPercorsoFileSquadre.setText(chooser.getSelectedFile().toString());
				try {
					sorteggiatore = new Sorteggiatore();
					sorteggiatore.leggiSquadre(TxtPercorsoFileSquadre.getText().toString());
					sorteggiatore.leggiLeghe(sorteggiatore.squadre);
					
					txtSquadreCaricate.setText("" + sorteggiatore.squadre.size());
					txtLegheIndividuate.setText("" + sorteggiatore.leghe.size());
					
				} catch(Exception ex) {
					System.out.print(ex.getMessage());
				}
			}
		});
		TxtApri.setBounds(490, 226, 105, 21);
		getContentPane().add(TxtApri);
		
		JLabel lblSquadreCaricate = new JLabel("Squadre caricate:");
		lblSquadreCaricate.setBounds(323, 259, 103, 13);
		getContentPane().add(lblSquadreCaricate);
		
		txtSquadreCaricate = new JTextField();
		txtSquadreCaricate.setEditable(false);
		txtSquadreCaricate.setBounds(436, 256, 96, 19);
		getContentPane().add(txtSquadreCaricate);
		txtSquadreCaricate.setColumns(10);
		
		JLabel lblLegheIndividuate = new JLabel("Leghe individuate:");
		lblLegheIndividuate.setBounds(323, 282, 103, 13);
		getContentPane().add(lblLegheIndividuate);
		
		txtLegheIndividuate = new JTextField();
		txtLegheIndividuate.setEditable(false);
		txtLegheIndividuate.setBounds(436, 279, 96, 19);
		getContentPane().add(txtLegheIndividuate);
		txtLegheIndividuate.setColumns(10);
		
		JButton btnProcediAiSorteggi = new JButton("Procedi ai sorteggi");
		btnProcediAiSorteggi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				sorteggiatore.procediAllEstrazione(Integer.parseInt(txtNumGironi.getText()), Integer.parseInt(TxtSquadrePerGirone.getText()));
				
				for (int i = 0; i < sorteggiatore.risultato.size(); i++) {
					System.out.println(sorteggiatore.risultato.get(i).toString());
				}
				
				if (sorteggiatore.risultato.size() > 0) {
					txtRisultato.setText("Girone " + (visCorrente+1));
					for (int i = 0; i < sorteggiatore.risultato.get(visCorrente).size(); i++) {
						txtRisultato.append("\n"+sorteggiatore.risultato.get(visCorrente).get(i).toString());
					}
				}
				
				btnSucc.setEnabled(true);
				btnPrev.setEnabled(false);
				
			}
		});
		btnProcediAiSorteggi.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		btnProcediAiSorteggi.setBounds(163, 350, 275, 43);
		getContentPane().add(btnProcediAiSorteggi);
		
		txtRisultato = new JTextArea();
		txtRisultato.setEditable(false);
		txtRisultato.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		txtRisultato.setBounds(105, 403, 405, 152);
		getContentPane().add(txtRisultato);
		txtRisultato.setColumns(10);
		
		btnPrev = new JButton("<<");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visCorrente--;
				if (visCorrente == 0) {
					btnPrev.setEnabled(false);
				}
				if (sorteggiatore.risultato.size()>0) {
					btnSucc.setEnabled(true);
				}
				
				if (sorteggiatore.risultato.size() > 0) {
					txtRisultato.setText("Girone " + (visCorrente+1));
					for (int i = 0; i < sorteggiatore.risultato.get(visCorrente).size(); i++) {
						txtRisultato.append("\n"+sorteggiatore.risultato.get(visCorrente).get(i).toString());
					}
				}
				
			}
		});
		btnPrev.setBounds(10, 453, 85, 34);
		getContentPane().add(btnPrev);
		
		btnSucc = new JButton(">>");
		btnSucc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visCorrente++;
				if (visCorrente == Integer.parseInt(txtNumGironi.getText())-1) {
					btnSucc.setEnabled(false);
				}
				if (sorteggiatore.risultato.size()>0) {
					btnPrev.setEnabled(true);
				}
				
				if (sorteggiatore.risultato.size() > 0) {
					txtRisultato.setText("Girone " + (visCorrente+1));
					for (int i = 0; i < sorteggiatore.risultato.get(visCorrente).size(); i++) {
						txtRisultato.append("\n"+sorteggiatore.risultato.get(visCorrente).get(i).toString());
					}
				}
			}
		});
		btnSucc.setBounds(520, 453, 85, 34);
		getContentPane().add(btnSucc);
		
		icona0 = new JLabel("");
		icona0.setBounds(10, 63, 171, 140);
		getContentPane().add(icona0);
		
		icona1 = new JLabel("");
		icona1.setBounds(222, 63, 171, 108);
		getContentPane().add(icona1);
		
		icona2 = new JLabel("");
		icona2.setBounds(442, 63, 163, 134);
		getContentPane().add(icona2);
		this.setBounds(100, 100, 628, 593);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon a = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/0.png")).getScaledInstance(icona0.getWidth(), icona0.getHeight(), java.awt.Image.SCALE_SMOOTH));
		icona0.setIcon(a);
		ImageIcon b = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/1.jpg")).getScaledInstance(icona1.getWidth(), icona1.getHeight(), java.awt.Image.SCALE_SMOOTH));
		icona1.setIcon(b);
		ImageIcon c = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/2.png")).getScaledInstance(icona2.getWidth(), icona2.getHeight(), java.awt.Image.SCALE_SMOOTH));
		icona2.setIcon(c);
	}
}
