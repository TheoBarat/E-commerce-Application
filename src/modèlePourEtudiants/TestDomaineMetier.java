package modèlePourEtudiants;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class TestDomaineMetier {
	
	private Articles mesArticles;
	private Fromage fromage;
	private Article a1;
	private Article a2;
	private Panier panier;
	
	@Before
	public void setUp() {
		this.mesArticles = GenerationFromages.générationBaseFromages();
		this.fromage = new Fromage("Brebis au Bleu");
		this.a1 = new Article(fromage, "250 g", 8.2F);
		this.a1.setQuantitéEnStock(5);
		this.a2 = new Article(fromage, "500 g", 16.4F);
		this.fromage.addArticle("250 g", 8.2F);
		this.fromage.addArticle("500 g", 16.4F);
		this.panier = new Panier();
	}
	
	@After
	public void tearDown() {
		this.mesArticles = null;
		this.fromage = null;
		this.a1 = null;
		this.a2 = null;
		this.panier = null;
	}
	
	
	/**
	 * Vérification du filtre par type de fromage
	 */
	@Test
	public void testFilterParChevre() {
		assertEquals(22,this.mesArticles.compteFromages(TypeLait.CHEVRE));
	}
	
	@Test
	public void testFilterParVache() {
		assertEquals(59,this.mesArticles.compteFromages(TypeLait.VACHE));
	}
	
	@Test
	public void testFilterParBrebis() {
		assertEquals(16,this.mesArticles.compteFromages(TypeLait.BREBIS));
	}
	
	
	/**
	 * Vérification de la récupération des articles pour un fromage
	 */
	@Test
	public void testGetArticles() {
		List<Article> articles = new LinkedList<Article>();
		articles.add(this.a1);
		articles.add(a2);
		assertEquals(articles, this.fromage.getArticles());
	}
	
	@Test
	public void testNombreArticles() {
		assertEquals(2, this.fromage.nombreArticles());
	}
	
	
	/**
	 * Vérification de la gestion du panier (ajout/modification/suppression)
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(this.panier.isEmpty());
	}
	
	@Test
	public void testGetSizePanier() {
		panier.addPanier(this.a1, 3);
		assertEquals(this.panier.getSizePanier(),1);
	}
	
	@Test
	public void testGetPanier() {
		panier.addPanier(this.a1, 3);
		panier.addPanier(this.a2, 2);
		List<Article> articles = new LinkedList<Article>();
		articles.add(this.a1);
		articles.add(this.a2);
		assertEquals(this.panier.getPanier(),articles);
		
	}

	@Test
	public void testGetArticle() {
		panier.addPanier(this.a1, 3);
		assertEquals(this.panier.getArticle(0), this.a1);
	}
	
	@Test
	public void testGetQuantiteArticle() { // A partir de l'index de l'article 
		panier.addPanier(this.a1, 2);
		assertEquals(this.panier.getQuantiteArticle(0), 2);
	}
	
	@Test
	public void testSetQuantiteArticle() { // A partir de l'index de l'article
		panier.addPanier(this.a1, 1);
		panier.setQuantiteArticle(0, 2);
		assertEquals(this.panier.getQuantiteArticle(0),3);
	}
	
	@Test
	public void testAddPanier() {
		panier.addPanier(this.a1, 3);
		List<Article> articles = new LinkedList<Article>();
		articles.add(this.a1);
		assertEquals(panier.getPanier(), articles);
		
	}
	
	@Test
	public void testAddPanierArticleExistant() { //Ajout de 2 fois le même article
		panier.addPanier(this.a1, 3);
		panier.addPanier(this.a1, 2);
		assertEquals(panier.getQuantiteArticle(0), 5);
		assertEquals(this.panier.getSizePanier(),1);
	}
	
	@Test
	public void razPanierTest() {
		panier.addPanier(this.a1, 3);
		panier.addPanier(this.a2, 5);
		panier.razPanier(mesArticles);
		assertEquals(panier.getSizePanier(),0);
	}
	
	
	/**
	 * Vérification des calculs sur la facture
	 */
	@Test
	public void testGetTotalArticle() {
		NumberFormat nf = new DecimalFormat("0.#");
		panier.addPanier(this.a1, 3);
		assertEquals(nf.format(24.6), nf.format(panier.getTotalArticle(0)));
	}
	
	@Test
	public void testGetSousTotalTTC() {
		NumberFormat nf = new DecimalFormat("0.#");
		panier.addPanier(this.a1, 3);
		panier.addPanier(this.a2, 1);
		assertEquals(nf.format(this.a1.getPrixTTC() * 3 + this.a2.getPrixTTC()), nf.format(panier.getSousTotalTTC()));
	}
	
	@Test
	public void testGetTotalTTC() {
		NumberFormat nf = new DecimalFormat("0.#");
		panier.addPanier(this.a1, 3);
		panier.addPanier(this.a2, 1);
		assertEquals(nf.format(this.a1.getPrixTTC() * 3 + this.a2.getPrixTTC() + panier.getLivraison(1)) , nf.format(panier.getTotalTTC(1)));
	}
	
	@Test
	public void testGetTotalTTCAvecLivraisonGratuite() {
		NumberFormat nf = new DecimalFormat("0.#");
		panier.addPanier(this.a1, 10);
		panier.addPanier(this.a2, 3);
		assertEquals(nf.format(this.a1.getPrixTTC() * 10 + this.a2.getPrixTTC() * 3 + panier.getLivraison(1)) , nf.format(panier.getTotalTTC(1)));
	}
	
	@Test
	public void testGetLivraiso() {
		NumberFormat nf = new DecimalFormat("0.##");
		assertEquals(nf.format(panier.getLivraison(1)), nf.format(Panier.COLIZION));
		assertEquals(nf.format(panier.getLivraison(2)), nf.format(Panier.CROUTISSIMO));
		assertEquals(nf.format(panier.getLivraison(3)), nf.format(Panier.CHRONOFROMAGE));
	}
	
	
	/**
	 * Vérification de la mise à jour des stocks
	 */
	@Test
	public void testQuantitéEnStock() {
		assertEquals(5,this.a1.getQuantitéEnStock());
	}
	
	@Test
	public void testPréempterQuantité() {
		this.a1.préempterQuantité(1);
		assertEquals(4, this.a1.getQuantitéEnStock()); 
	}
	
	@Test
	public void testRendreQuantité() {
		this.a1.préempterQuantité(1);
		this.a1.rendreQuantité(1);
		assertEquals(5, this.a1.getQuantitéEnStock());
	}
	

}
