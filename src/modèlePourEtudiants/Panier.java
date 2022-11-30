package modèlePourEtudiants;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Panier {
    
	/**
	 * Liste contenant les Articles contenus dans le panier
	 */
    private List<Article> listeArticle;
    /**
     * prix des différents services de livraison 
     */
    public static final float COLIZION = 4.90f;
    public static final float CROUTISSIMO = 4.90f;
    public static final float CHRONOFROMAGE = 9.90f;

    /**
     * Crée un Panier nul
     */
    public Panier() {
    	/**
    	 * Crée la liste d'Article
    	 */
        this.listeArticle = new LinkedList<Article>();
    }
    
    /**
     * vérifie qu'un Panier est vide
     * @return true si le Panier est vide ou false si il ne l'est pas
     */
    public boolean isEmpty() {
    	return this.getSizePanier() == 0;
    }
    
    /**
     * Accéder au nombre d'article que contient le panier
     * @return un entier 
     */
    public int getSizePanier() {
    	return this.listeArticle.size();
    }
    
    /**
     * Accéder à tous les articles du panier
     * @return une liste d'Article
     */
    public List<Article> getPanier() {
    	return this.listeArticle;
    }
    
    /**
     * Accéder à un Article précis du Panier
     * @param index où se trouve l'article dans la liste
     * @return unn Article
     */
    public Article getArticle (int index) {
    	return this.getPanier().get(index);
    }
    
    /**
     * Accéder a la quantite d'un Article précis du Panier 
     * @param index où se trouve l'article dans la liste
     * @return un entier
     */
    public int getQuantiteArticle(int index) {
		return this.getArticle(index).getQuantitéEnStock();
	}
	
    /**
     * Permet d'initialiser la quantité d'un Article précis du Panier
     * @param index où se trouve l'article dans la liste
     * @param quantite qu'on veut ajouter à notre Article
     */
	public void setQuantiteArticle(int index, int quantite) {
		this.getArticle(index).setQuantitéEnStock(this.getQuantiteArticle(index) + quantite);
	}
    
	/**
	 * Permet d'ajouter un Article au Panier
	 * @param a : l'Article qu'on veut ajouter
	 * @param quantite qu'on veut ajouter à notre Article
	 */
    public void addPanier(Article a, int quantite) {
    	boolean dejaExistant = false;
    	for (int i = 0; i<this.getSizePanier(); i++) {
    		if (this.getArticle(i).equals(a)) {
    			this.setQuantiteArticle(i,quantite);
				dejaExistant = true;
    		}
    	}
    	if (!dejaExistant) {
    		this.listeArticle.add(new Article(a.getFromage(),a.getClé(),a.getPrixTTC()));
    		for (int i = 0; i<this.getSizePanier(); i++) {
        		if (this.getArticle(i).equals(a)) { 
        			this.setQuantiteArticle(i,0);
        			this.setQuantiteArticle(i,quantite);
        		}
    		}
    	}   
    }
    
    /**
     * Permet de supprimer tous les Articles du Panier
     * @param mesArticles : tous les Articles disponible sur l'application
     */
    public void razPanier(Articles mesArticles) {
    	for (int i = 0; i<this.getSizePanier(); i++) {
    		for (Fromage f : mesArticles.getLesFromages()) {
    			for (Article a : f.getArticles() ) {
    				if (a.equals(this.getArticle(i))) {
    					a.rendreQuantité(this.getQuantiteArticle(i));
    				}
    			}
    		}
    	}
        this.listeArticle.removeAll(listeArticle);
    }
    
    /**
     * Calcule le prix d'un Article en fonction de sa quantité
     * @param index où se trouve l'article dans la liste
     * @return un flottant
     */
    public float getTotalArticle(int index) {
    	return this.getArticle(index).getPrixTTC() * this.getQuantiteArticle(index);
    }
    
    /**
     * Calcul le prix du Panier
     * @return un flottant
     */
    public float getSousTotalTTC() {
        float total = 0;
        for (int i = 0; i < this.listeArticle.size(); i++) {
        	total += this.getTotalArticle(i);
        }
        return total;
    }
    
    /**
     * Calcul le prix du Panier avec les frais de livraison si le prix du panier est inférieur à 80€ sinon les frais de livraison sont gratuit
     * @param livraison choisi
     * @return un flottant
     */
    public float getTotalTTC(int livraison) {
    	return this.getSousTotalTTC() + this.getLivraison(livraison);
    }
    
    /**
     * Accède à la livraison choisi par l'utilisateur
     * @param livraison choisi
     * @return un flottant
     */
    public float getLivraison(int livraison) {
    	if (this.getSousTotalTTC() < 80.0f) {
    		switch(livraison) {
            case 1:
                return COLIZION;
    		case 2:
                return CROUTISSIMO;
    		case 3:
                return CHRONOFROMAGE;
    		default:
                throw new IllegalArgumentException ("livraison inconnue");
            }
    	} else {
    		return 0;
        }    
    }
    
    
    /**
     * Valeur unicode d'un Panier
     * @param livraison choisi
     * @return une chaîne de caractère
     */
    public String toString(int livraison) {
    	String chaine = "";
    	chaine = chaine + String.format("%30s %70s %40s %20s", "Produit", "Prix", "Quantité", "Total") +"\n" +"\n";
    	for (int i = 0; i<this.getSizePanier(); i++) {
    		chaine = chaine + String.format("%30s %60.2f€ %30s %25.2f€", this.getArticle(i).toStringIHM(),this.getArticle(i).getPrixTTC() , this.getQuantiteArticle(i), this.getTotalArticle(i)) + "\n";
    	}
    	chaine += "\n" + String.format("%20s %20.2f€", "SOUS TOTAL TTC :", this.getSousTotalTTC());
    	chaine += "\n" + String.format("%21s %20.2f€", "FRAIS DE PORTS :", this.getLivraison(livraison));
    	chaine += "\n" + String.format("%25s %20.2f€", "TOTAL TTC :", this.getTotalTTC(livraison));
    	return chaine;
    }
 
}