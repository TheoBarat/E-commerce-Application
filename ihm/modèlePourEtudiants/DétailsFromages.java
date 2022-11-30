package modèlePourEtudiants;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.BoxLayout;
import java.awt.Component;

public class DétailsFromages extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBox;
	private JSpinner spinner;
	
	private List<String> toStringArticles;
	private Fromage fromage;
	private int quantite;
	private Panier panier;
	
	/**
	 * Create the dialog.
	 */
	public DétailsFromages(Fromage fromageChoisi, Articles mesArticles, Panier panierAppli) {
		
		panier = panierAppli;
		fromage = fromageChoisi;
		
		setBounds(100, 100, 545, 304);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
			
			/**
			 * Affichage de la description du fromage choisi
			 */
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			
			
			
			JTextPane textPane = new JTextPane();
			textPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
			textPane.setEditable(false);
			scrollPane.setViewportView(textPane);
			textPane.setText(fromage.toString()+"\n"+ fromage.getDescription());
					
			
			/**
			 * Création de la JList de base qui se crée a l'ouverture du JDialog et qui contient tous les articles du fromage choisi 
			 */
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
			
				comboBox = new JComboBox(fromage.getArticles().toArray());
				spinner = new JSpinner();
				quantite = fromage.getArticles().get(comboBox.getSelectedIndex()).getQuantitéEnStock();
				construireMethodeComboBoxStockSpinner(comboBox, spinner);
				panel.add(comboBox);
				spinner.setModel(new SpinnerNumberModel(0, 0, quantite , 1));
				panel.add(spinner);
				
					
			/**
			 * Panel qui contient les boutons pour ajouter un article au panier et revenir au menu de selection	
			 */
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
	            
        
				JButton okButton = new JButton("Ajouter au panier");
				construireControleurButtonAjouterAupanier(okButton);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
	            
				JButton cancelButton = new JButton("Annuler");
				construireControleurButtonAnnuler(cancelButton);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton); 
	}

	
	/**
	 * Action qui permet de revenir au menu de selection des fromages
	 * @param cancelButton
	 */
	private void construireControleurButtonAnnuler(JButton cancelButton) {
		cancelButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        dispose();
		    }
		});
	}
	     
	/**
	 * Action qui permet d'ajouter un article au panier et d'enlever la quantité choisi au spinner pour ne pas dépasser le stock. On ne peut ajouter un article que si la quantité choisi estsupérieur à 0.
	 * @param okButton
	 */
	private void construireControleurButtonAjouterAupanier(JButton okButton) {
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((int)spinner.getValue() != 0) {
					fromage.getArticles().get(comboBox.getSelectedIndex()).préempterQuantité((int)spinner.getValue());	
					panier.addPanier(fromage.getArticles().get(comboBox.getSelectedIndex()), (int)spinner.getValue());
					dispose();
				}
			}
		});
	}

	/**
	 * Action qui permet de modifier la valeur max du spinner si on ajoute un article au panier ou bien qu'on RAZ le panier
	 * @param comboBox
	 * @param spinner
	 */
	private void construireMethodeComboBoxStockSpinner(JComboBox comboBox, JSpinner spinner) {
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantite = fromage.getArticles().get(comboBox.getSelectedIndex()).getQuantitéEnStock();
				spinner.setModel(new SpinnerNumberModel(0, 0, quantite , 1));
			}
		});
	}

}