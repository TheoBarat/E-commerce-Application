package modèlePourEtudiants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class FicheCliente extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldPrenom;
	private JTextField textFieldNom;
	private JTextField textFieldAdresse;
	private JTextField textFieldAdresse2;
	private JTextField textFieldCodePostal;
	private JTextField textFieldVille;
	private JTextField textFieldTelephone;
	private JTextField textFieldMail;
	
	private Client client;
	private Panier panier;
	private int livraison;

	/**
	 * Create the dialog.
	 */
	public FicheCliente(Panier panierAppli, int fraisLivraison) {
		
		livraison = fraisLivraison;
		panier = panierAppli;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(8, 2, 0, 0));
		
		/**
		 * Création du nom de la page
		 */
		JPanel panel_16 = new JPanel();
		getContentPane().add(panel_16, BorderLayout.NORTH);
		
		
			JLabel lblNewLabel_8 = new JLabel("Fiche Cliente");
			lblNewLabel_8.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
			panel_16.add(lblNewLabel_8);
		
		
		
		JPanel panel = new JPanel();
		contentPanel.add(panel);
			
		
			JLabel lblNewLabel = new JLabel("Nom");
			panel.add(lblNewLabel);
			
			
		/**
		 * Panel qui contient un JTextField permettant de renseigner son prénom	
		 */
		JPanel panel_1 = new JPanel();
		contentPanel.add(panel_1);
			
			
			textFieldPrenom = new JTextField();
			panel_1.add(textFieldPrenom);
			textFieldPrenom.setColumns(10);
			
		
			
		JPanel panel_2 = new JPanel();
		contentPanel.add(panel_2);
			
		
			JLabel lblNewLabel_1 = new JLabel("Prénom");
			panel_2.add(lblNewLabel_1);
			

		/**
		 * Panel qui contient un JTextField permettant de renseigner son nom
		 */
		JPanel panel_3 = new JPanel();
		contentPanel.add(panel_3);
			
		
			textFieldNom = new JTextField();
			panel_3.add(textFieldNom);
			textFieldNom.setColumns(10);
			
		
			
		JPanel panel_4 = new JPanel();
		contentPanel.add(panel_4);
			
		
			JLabel lblNewLabel_2 = new JLabel("Adresse 1");
			panel_4.add(lblNewLabel_2);
			
		
		/**
		 * Panel qui contient un JTextField permettant de renseigner son adresse	
		 */
		JPanel panel_5 = new JPanel();
		contentPanel.add(panel_5);
			
		
			textFieldAdresse = new JTextField();
			panel_5.add(textFieldAdresse);
			textFieldAdresse.setColumns(10);
			
		
			
		JPanel panel_6 = new JPanel();
		contentPanel.add(panel_6);
			
		
			JLabel lblNewLabel_3 = new JLabel("Adresse 2 (*) ");
			panel_6.add(lblNewLabel_3);
			
		
		/**
		 * Panel qui contient un JTextField permettant de renseigner sa deuxième adresse si on en a une	
		 */
		JPanel panel_7 = new JPanel();
		contentPanel.add(panel_7);
		
		
			textFieldAdresse2 = new JTextField();
			panel_7.add(textFieldAdresse2);
			textFieldAdresse2.setColumns(10);
			
		
			
		JPanel panel_8 = new JPanel();
		contentPanel.add(panel_8);
			
		
			JLabel lblNewLabel_4 = new JLabel("Code Postal");
			panel_8.add(lblNewLabel_4);
			
			
		/**
		 * Panel qui contient un JTextField permettant de renseigner son code postal
		 */
		JPanel panel_9 = new JPanel();
		contentPanel.add(panel_9);
			
		
			textFieldCodePostal = new JTextField();
			panel_9.add(textFieldCodePostal);
			textFieldCodePostal.setColumns(10);
			
			
		
		JPanel panel_10 = new JPanel();
		contentPanel.add(panel_10);
			
		
			JLabel lblNewLabel_5 = new JLabel("Ville");
			panel_10.add(lblNewLabel_5);
			
		
		/**
		 * Panel qui contient un JTextField permettant de renseigner sa Ville	
		 */
		JPanel panel_11 = new JPanel();
		contentPanel.add(panel_11);
			
		
			textFieldVille = new JTextField();
			panel_11.add(textFieldVille);
			textFieldVille.setColumns(10);
			
			
		
		JPanel panel_12 = new JPanel();
		contentPanel.add(panel_12);
			
		
			JLabel lblNewLabel_6 = new JLabel("Téléphone");
			panel_12.add(lblNewLabel_6);
			
			
		/**
		 * Panel qui contient un JTextField permettant de renseigner son numéro de téléphone	
		 */
		JPanel panel_13 = new JPanel();
		contentPanel.add(panel_13);
			
		
			textFieldTelephone = new JTextField();
			panel_13.add(textFieldTelephone);
			textFieldTelephone.setColumns(10);
			
		
			
		JPanel panel_14 = new JPanel();
		contentPanel.add(panel_14);
			
		
			JLabel lblNewLabel_7 = new JLabel("Mail");
			panel_14.add(lblNewLabel_7);
			
		
		/**
		 * Panel qui contient un JTextField permettant de renseigner son adresse mail
		 */
		JPanel panel_15 = new JPanel();
		contentPanel.add(panel_15);
			
		
			textFieldMail = new JTextField();
			panel_15.add(textFieldMail);
			textFieldMail.setColumns(10);
			
		
		/**
		 * Panel qui contient les boutons permettant de valider notre fiche et de revenir à notre panier
		 */
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
		
				JButton okButton = new JButton("Valider");
				construireControleurButtonValider(okButton);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
		
				JButton cancelButton = new JButton("Annuler");
				construireControleButtonAnnuler(cancelButton);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				
				
	}

	/**
	 * Action qui permet de revenir à notre panier
	 * @param cancelButton
	 */
	private void construireControleButtonAnnuler(JButton cancelButton) {
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	/**
	 * action qui permet de valider notre fiche, mais que si tous les informations essentielles ont été rentrées (adresse 2 facultatif)
	 * @param okButton
	 */
	private void construireControleurButtonValider(JButton okButton) {
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okButton.setBackground(new Color(255, 165, 0));
					client = new Client(textFieldPrenom.getText(), textFieldNom.getText(), textFieldAdresse.getText(), textFieldAdresse2.getText(), textFieldCodePostal.getText(), textFieldVille.getText(),textFieldTelephone.getText(), textFieldMail.getText());
				if (client.informationsRemplies()) {	
					FactureFromages f = new FactureFromages(client,panier,livraison);
					f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					f.setVisible(true);
					dispose();
				}
			}
		});
	}
								 	
}
