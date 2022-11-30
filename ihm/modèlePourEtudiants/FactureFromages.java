package modèlePourEtudiants;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.table.TableModel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class FactureFromages extends JDialog {
	
	
        private final ButtonGroup buttonGroup = new ButtonGroup();
        private JTextPane textPane;
        private JTable informations;
        private NumberFormat nf = new DecimalFormat("0.##");
        
        private Facture facture;
        private Panier panier;
        private Client client;
        
        
        /**
         * Create the dialog.
         */
        public FactureFromages(Client clientAppli,Panier panierAppli,int livraison) {
        	
        	panier = panierAppli;
        	client = clientAppli;
        	facture = new Facture(client,panier);
        	
            setBounds(100, 100, 660, 592);
            
            
            JPanel contentPanel = new JPanel();
            contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
            getContentPane().add(contentPanel, BorderLayout.NORTH);
            FlowLayout fl_contentPanel = new FlowLayout();
            contentPanel.setLayout(fl_contentPanel);
           
            
            	/**
            	 * Création du logo et du nom de la page
            	 */
            	JLabel Panier = new JLabel("LA FROMI FACTURE");
            	Panier.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
            	contentPanel.add(Panier);
            
            	
            	JLabel lblNewLabel = new JLabel("");
            	ImageIcon logo = new ImageIcon(new ImageIcon(LeFromi.class.getResource("/modèlePourEtudiants/icons/logoSouris.png")).getImage().getScaledInstance(46, 62, Image.SCALE_DEFAULT));
            	lblNewLabel.setIcon(logo);
            	contentPanel.add(lblNewLabel);
            
            	
            /**
             * Panel contenant le bouton permettant de fermer l'application	
             */
            JPanel buttonPane = new JPanel();
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            
            
            
            	JButton btnNewButton = new JButton("Quitter");
            	construireControleurbuttonQuitter(btnNewButton);
            	buttonPane.add(btnNewButton);
            
            	
            	
            /**
             * Panel contenant une JTable qui affiche les informations du client et un JTextPane permettant d'afficher le récapitulatif des achats du client	
             */
            JPanel Articles = new JPanel();
            getContentPane().add(Articles, BorderLayout.CENTER);
            
            
            informations = new JTable();
            Articles.setLayout(new BorderLayout(0, 0));
            informations = new JTable();
            informations.setModel(new DefaultTableModel(
					new Object[][] {
						{"INFORMATION DU CLIENT : ",  },
						{"Nom ", client.getPrenom()},
						{"Prénom ", client.getNom() },
						{"Adresse", client.getAdresse()},
						{"Adresse 2", client.getAdresse2()},
						{"Code Postal", client.getCodePostal()},
						{"Ville", client.getVille()},
						{"Téléphone", client.getTéléphone()},
						{"Mail", client.getMail() },
						},
					new String[] {
							"Champ", "Valeur"
					}
					));
            Articles.add(informations, BorderLayout.NORTH);
            
            
            JScrollPane scrollPane = new JScrollPane();
            Articles.add(scrollPane, BorderLayout.CENTER);
            
            
            JTextPane textPane_1 = new JTextPane();
            scrollPane.setViewportView(textPane_1);
            textPane_1.setText(facture.toString(livraison));
                         	     
}

        /**
         * action permettant de fermer l'application
         * @param btnNewButton
         */
		private void construireControleurbuttonQuitter(JButton btnNewButton) {
			btnNewButton.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		dispose();
            		LeFromi.frame.dispose();
            	}
            });
		}
        
        
		
               
}