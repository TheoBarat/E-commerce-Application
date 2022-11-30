package modèlePourEtudiants;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class LeFromi {
	
	JPanel page;

	public static JFrame frame;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	
	private Fromage fromage;
	private Articles mesArticles = GenerationFromages.générationBaseFromages();
	public static Panier panier = new Panier();
	private NumberFormat nf = new DecimalFormat("0.##");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeFromi window = new LeFromi();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public  LeFromi() {
		initialize();
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 810, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
			/**
			 * Création du logo et du nom de l'application
			 */
			JPanel Entreprise = new JPanel();
			panel.add(Entreprise);
			Entreprise.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
				JLabel lblNewLabel = new JLabel("");
				ImageIcon logo = new ImageIcon(new ImageIcon(LeFromi.class.getResource("/modèlePourEtudiants/icons/logoSouris.png")).getImage().getScaledInstance(46, 62, Image.SCALE_DEFAULT));
				lblNewLabel.setIcon(logo);
				Entreprise.add(lblNewLabel);
		
				JLabel lblNewLabel_1 = new JLabel("LE FROMI");
				lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
				Entreprise.add(lblNewLabel_1);
			
				
				
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
					
			/**
			 * Création de la JList de base qui se crée a l'ouverture de l'application et qui contient tous les fromages
			 */
			JList list = new JList(mesArticles.getDesignationLesFromagesOrdreAlphabétique().toArray());	
			construireContrôleurListDetailsFromages(list);
			scrollPane.setViewportView(list);
		
			
		/**
		 * Panel qui contient les boutons pour filtrer les fromages et le bouton qui permet d'accéder au panier	
		 */
		JPanel menu = new JPanel();
		panel.add(menu);
		menu.setLayout(new GridLayout(0, 5, 0, 0));
		
			
				btnNewButton = new JButton("Tous");
				btnNewButton.setBackground(new Color(255, 165, 0));
				construireContrôleurButtonTousFromages(scrollPane, btnNewButton);
				menu.add(btnNewButton);
		
				btnNewButton_1 = new JButton("Vache");
				btnNewButton_1.setIcon(new ImageIcon(LeFromi.class.getResource("/modèlePourEtudiants/icons/vache.png")));
				btnNewButton_1.setBackground(SystemColor.inactiveCaptionBorder);
				construireContrôleurButtonFromagesVache(scrollPane, btnNewButton_1);
				menu.add(btnNewButton_1);
		
				btnNewButton_2 = new JButton("Chèvre");
				btnNewButton_2.setIcon(new ImageIcon(LeFromi.class.getResource("/modèlePourEtudiants/icons/chevre (1).png")));
				btnNewButton_2.setBackground(SystemColor.inactiveCaptionBorder);
				construireContrôleurButtonFromagesChevre(scrollPane, btnNewButton_2);
				menu.add(btnNewButton_2);
		
				btnNewButton_3 = new JButton("Brebis");
				btnNewButton_3.setIcon(new ImageIcon(LeFromi.class.getResource("/modèlePourEtudiants/icons/brebis (1).png")));
				btnNewButton_3.setBackground(SystemColor.inactiveCaptionBorder);
				construireContrôleurButtonFromagesBrebis(scrollPane, btnNewButton_3);
				menu.add(btnNewButton_3);
		
				btnNewButton_4 = new JButton("Panier");
				btnNewButton_4.setIcon(new ImageIcon(LeFromi.class.getResource("/modèlePourEtudiants/icons/panier (1).png")));
				if (!panier.isEmpty()) {
					btnNewButton_4.setIcon(new ImageIcon(LeFromi.class.getResource("/modèlePourEtudiants/icons/panierPlein_1.png")));
				}
				btnNewButton_4.setBackground(SystemColor.inactiveCaptionBorder);
				construireControleurButtonPanier(btnNewButton_4);
				menu.add(btnNewButton_4);
	
				
	}
	
	/**
	 * Action qui permet d'acceder au récapitulatif du panier quand on clique sur le bouton "Panier"
	 * @param btnNewButton_4
	 */
	private void construireControleurButtonPanier(JButton btnNewButton_4) {
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_4.setBackground(new Color(255, 165, 0));
				btnNewButton.setBackground(SystemColor.inactiveCaptionBorder);
				btnNewButton_2.setBackground(SystemColor.inactiveCaptionBorder);
				btnNewButton_3.setBackground(SystemColor.inactiveCaptionBorder);
				btnNewButton_1.setBackground(SystemColor.inactiveCaptionBorder);
				PanierFromages f = new PanierFromages(mesArticles,panier);
				f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				f.setVisible(true);
			}
		});
	}
	
	/**
	 * Action qui permet d'acceder au détail d'un fromage quand on clique dessus dans la JList
	 * @param list
	 */
	private void construireContrôleurListDetailsFromages(JList list) {
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (Fromage f : mesArticles.getLesFromages()) {
					if (f.getDésignation() == list.getSelectedValue()) {
						fromage = f;
					}
				}
				DétailsFromages f = new DétailsFromages(fromage,mesArticles,panier);
				f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				f.setVisible(true);
			}
		});
	}

	/**
	 * Action qui permet d'afficher que les fromages au lait de brebis dans la JList quand on clique sur le bouton "Brebis"
	 * @param scrollPane
	 * @param btnNewButton_3
	 */
	private void construireContrôleurButtonFromagesBrebis(JScrollPane scrollPane, JButton btnNewButton_3) {
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_3.setBackground(new Color(255, 165, 0));
				btnNewButton.setBackground(SystemColor.inactiveCaptionBorder);
				btnNewButton_2.setBackground(SystemColor.inactiveCaptionBorder);
				btnNewButton_1.setBackground(SystemColor.inactiveCaptionBorder);
				btnNewButton_4.setBackground(SystemColor.inactiveCaptionBorder);
				JList list = new JList(mesArticles.getDesignationFromageAuLaitDeOrdreAlphabétique(TypeLait.BREBIS).toArray());
				construireContrôleurListDetailsFromages(list);
				scrollPane.setViewportView(list);
			}
		});
	}
	
	/**
	 * Action qui permet d'afficher que les fromages au lait de chevre dans la JList quand on clique sur le bouton "Chevre"
	 * @param scrollPane
	 * @param btnNewButton_2
	 */
	private void construireContrôleurButtonFromagesChevre(JScrollPane scrollPane, JButton btnNewButton_2) {
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_2.setBackground(new Color(255, 165, 0));
				btnNewButton.setBackground(SystemColor.inactiveCaptionBorder);
				btnNewButton_1.setBackground(SystemColor.inactiveCaptionBorder);
				btnNewButton_3.setBackground(SystemColor.inactiveCaptionBorder);
				btnNewButton_4.setBackground(SystemColor.inactiveCaptionBorder);
				JList list = new JList(mesArticles.getDesignationFromageAuLaitDeOrdreAlphabétique(TypeLait.CHEVRE).toArray());
				construireContrôleurListDetailsFromages(list);
				scrollPane.setViewportView(list);
			}
		});
	}
	
	/**
	 * Action qui permet d'afficher que les fromages au lait de vache dans la JList quand on clique sur le bouton "Vache"
	 * @param scrollPane
	 * @param btnNewButton_1
	 */
	private void construireContrôleurButtonFromagesVache(JScrollPane scrollPane, JButton btnNewButton_1) {
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_1.setBackground(new Color(255, 165, 0));
				btnNewButton.setBackground(SystemColor.inactiveCaptionBorder);
				btnNewButton_2.setBackground(SystemColor.inactiveCaptionBorder);
				btnNewButton_3.setBackground(SystemColor.inactiveCaptionBorder);
				btnNewButton_4.setBackground(SystemColor.inactiveCaptionBorder);
				JList list = new JList(mesArticles.getDesignationFromageAuLaitDeOrdreAlphabétique(TypeLait.VACHE).toArray());
				construireContrôleurListDetailsFromages(list);
				scrollPane.setViewportView(list);
			}
		});
	}
	
	/**
	 *  Action qui permet d'afficher tous les fromages dans la JList quand on clique sur le bouton "Tous"
	 * @param scrollPane
	 * @param btnNewButton
	 */
	private void construireContrôleurButtonTousFromages(JScrollPane scrollPane, JButton btnNewButton) {
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setBackground(new Color(255, 165, 0));
				btnNewButton_1.setBackground(SystemColor.inactiveCaptionBorder);
				btnNewButton_2.setBackground(SystemColor.inactiveCaptionBorder);
				btnNewButton_3.setBackground(SystemColor.inactiveCaptionBorder);
				btnNewButton_4.setBackground(SystemColor.inactiveCaptionBorder);
				JList list = new JList(mesArticles.getDesignationLesFromagesOrdreAlphabétique().toArray());
				construireContrôleurListDetailsFromages(list);
				scrollPane.setViewportView(list);
			}
		});
	}

}
