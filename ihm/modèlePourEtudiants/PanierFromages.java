package modèlePourEtudiants;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;

public class PanierFromages extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTable table_1;
	
	private int livraison;
	private Panier panier;
	private Articles mesArticles;
	private NumberFormat nf = new DecimalFormat("0.##");

	/**
	 * Create the dialog.
	 */
	public PanierFromages(Articles articles, Panier panierAppli) {
		
		panier = panierAppli;
		mesArticles = articles;
		
		setBounds(100, 100, 660, 398);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
			
			
			/**
			 * Création du logo et du nom de la page
			 */
			JLabel lblNewLabel = new JLabel("");
			ImageIcon logo = new ImageIcon(new ImageIcon(LeFromi.class.getResource("/modèlePourEtudiants/icons/logoSouris.png")).getImage().getScaledInstance(46, 62, Image.SCALE_DEFAULT));
			lblNewLabel.setIcon(logo);
			contentPanel.add(lblNewLabel);
		
			JLabel Panier = new JLabel("Mon Panier");
			Panier.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
			contentPanel.add(Panier);
		
		
		/**
		 * Panel qui contient les boutons pour commander le panier,  pour RAZ le panier et pour revenir à la selection des fromages	
		 */
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
		
			JButton okButton = new JButton("Commander");
			if (! panier.isEmpty()) {
				construireControleurButtonCommander(okButton);
			}
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);							
			
			JButton cancelButton = new JButton("RAZ le panier");
			construireControleurButtonRAZLePanier(cancelButton);			
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
			
			JButton btnNewButton = new JButton("Continuer les achats");
			construireControleurButtonContinuerAchats(btnNewButton);
			buttonPane.add(btnNewButton);
			
			
		/**
		 * Panel qui contient deux JTable qui permettent d'afficher au client les articles qu'il a selectionnés et lui montrer le prix total de ses achats
		 */
		JPanel Articles = new JPanel();
		getContentPane().add(Articles, BorderLayout.CENTER);
		Articles.setLayout(new GridLayout(3, 1, 0, 0));
		livraison = 1;
				
		
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Produit");
			model.addColumn("Prix");
			model.addColumn("Quantité");
			model.addColumn("Total");
			for (int i = 0; i < panier.getSizePanier(); i++ ) {
				model.addRow(new Object[] { panier.getPanier().get(i).toStringIHM(), panier.getPanier().get(i).getPrixTTC() + " €", 
						panier.getQuantiteArticle(i), nf.format(panier.getTotalArticle(i)) + " €"});
			}
			JTable tableauArticle = new JTable(model);
			JScrollPane sc = new JScrollPane(tableauArticle);
			sc.setVerticalScrollBarPolicy(
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			Articles.add(sc);
						
			table_1 = new JTable();
			table_1.setModel(new DefaultTableModel(
					new Object[][] {
						{"SOUS TOTAL TTC :", nf.format(panier.getSousTotalTTC()) + " €"},
						{"FRAIS DE PORTS : ", panier.getLivraison(livraison) + " €"},
						{"TOTAL TTC :", nf.format(panier.getTotalTTC(livraison)) + " €"},
						},
					new String[] {
							"Terme", "Valeur"
					}
					));
			Articles.add(table_1);
			
			
		/**
		 * Panel qui contient 3 JRadioButton quui permettent de choisir le moyen de livraison qui nous convient
		 */
		JPanel livraisons = new JPanel();
		Articles.add(livraisons);
		livraisons.setLayout(new GridLayout(3, 1, 0, 0));
		
		
			JRadioButton Colizion = new JRadioButton("Colizion : 4.90€");
			Colizion.setSelected(true);
			setColizion(Colizion);
			buttonGroup_1.add(Colizion);
			livraisons.add(Colizion);
					
			JRadioButton Croutissimo = new JRadioButton("Croutissimo : 4.90€");
			setCroutissimo(Croutissimo);
			buttonGroup_1.add(Croutissimo);
			livraisons.add(Croutissimo);
					
			JRadioButton Chronofromage = new JRadioButton("Chronofromage : 9.90€");
			setChronofromage(Chronofromage);
			buttonGroup_1.add(Chronofromage);
			livraisons.add(Chronofromage);
				
	}
	
	/**
	 * Action qui permet de revenir au menu de selection des fromages
	 * @param btnNewButton
	 */
	private void construireControleurButtonContinuerAchats(JButton btnNewButton) {
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	/**
	 * Action qui permet de passer a la commande de notre panier (fiche cliente). S'effectue que si le panier contient au moins 1 article
	 * @param okButton
	 */
	private void construireControleurButtonCommander(JButton okButton) {
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FicheCliente a = new FicheCliente(panier, livraison);
				a.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				a.setVisible(true);
				dispose();
			}
});
	}
	
	/**
	 * Action qui permet de choisir le mode de livraisonn Chronofromage
	 * @param Chronofromage
	 */
	private void setChronofromage(JRadioButton Chronofromage) {
		Chronofromage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panier.getSousTotalTTC()< 80) {
					livraison = 3;
					table_1.setValueAt(Panier.CHRONOFROMAGE + " €",1,1 );
					table_1.setValueAt(nf.format(panier.getTotalTTC(livraison)) + " €",2,1);
				} else {
					Chronofromage.setEnabled(false);
				}
			}
		});
	}
	
	/**
	 * Action qui permet de choisir le mode de livraisonn Croutissimo
	 * @param Croutissimo
	 */
	private void setCroutissimo(JRadioButton Croutissimo) {
		Croutissimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panier.getSousTotalTTC()< 80) {
					livraison = 2;
					table_1.setValueAt(Panier.CROUTISSIMO + " €",1,1 );
					table_1.setValueAt(nf.format(panier.getTotalTTC(livraison)) + " €",2,1);
				} else {
					Croutissimo.setEnabled(false);
				}
			}
		});
	}
	
	/**
	 * Action qui permet de choisir le mode de livraisonn Colizion
	 * @param Colizion
	 */
	private void setColizion(JRadioButton Colizion) {
		Colizion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panier.getSousTotalTTC()< 80) {
					livraison = 1;
					table_1.setValueAt(Panier.COLIZION + " €",1,1 );
					table_1.setValueAt(nf.format(panier.getTotalTTC(livraison)) + " €",2,1);
				} else {
					Colizion.setEnabled(false);
				}
			}
		});
	}
	
	/**
	 * Action qui permet de RAZ notre panier et de rendre la la quantité choisi au spinner pour ne pas dépasser le stock. S'effectue que si le panier contient au moins 1 article.
	 * @param cancelButton
	 */
	private void construireControleurButtonRAZLePanier(JButton cancelButton) {
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (! panier.isEmpty()) {
					panier.razPanier(mesArticles);
					dispose();
				}
			}
		});
	}
}