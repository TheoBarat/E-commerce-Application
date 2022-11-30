package modèlePourEtudiants;

public class Facture {
	
	private Client client;
	private Panier panier;
	
	/**
	 * 
	 * @param client à qui est adressé  la Facture
	 * @param panier qui fait l'objet de la Facture
	 */
	public Facture(Client client, Panier panier) {
		this.client = client;
		this.panier = panier;
	}
	
	/**
	 * valeur unicode d'une Facture
	 * @param livraison choisi par l'utilisateur
	 * @return chaine de caractère
	 */
	public String toString(int livraison) {
		String chaine = "";
		chaine += "RECAPITULATIF DE VOTRE COMMANDE :" +"\n";
		
		chaine += "\n" + this.panier.toString(livraison) +"\n" +"\n";
		
		chaine += "La fromagerie LeFromi vous remercie pour votre commande," +"\n";
		chaine += "En espérant vous revoir au plus vite !" +"\n";

		return chaine;
	}
}
