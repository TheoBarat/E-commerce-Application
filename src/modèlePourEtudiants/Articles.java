package modèlePourEtudiants;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class Articles {
	
	private List<Fromage> lesFromages;
	
	public Articles() {
		this.lesFromages = new LinkedList<Fromage>();
	}
	
	public void addFromages(List<Fromage> fromages) {
		this.lesFromages.addAll(fromages);
	}
	
	public String toStringFromagesEtArticles() {
		StringBuffer enForme = new StringBuffer();
		for (Fromage f : this.lesFromages) {
			enForme.append(f.toString() + '\n');
			if (f.nombreArticles() > 0) {
				for (Article article : f.getArticles()) {
					enForme.append(article.toString() + '\n');
				}
			}
		}
		return enForme.toString();
	}
	
	public String toStringArticlesEtStock() {
		StringBuffer enForme = new StringBuffer();
		for (Fromage f : this.lesFromages) {
			if (f.nombreArticles() > 0) {
				for (Article article : f.getArticles()) {
					enForme.append(article.toStringAvecStock() + '\n');
				}
			}
		}
		return enForme.toString();
	}
	
	/**
	 * Accéder uniquement aux fromages d'un type de lait précis
	 * @param lait de TypeLait
	 * @return une liste de fromages
	 */
	public List<Fromage> fromageAuLaitDe(TypeLait lait) {
		List<Fromage> lesFromagesAuLaitDe = new LinkedList<Fromage>();
		switch(lait) {
		case VACHE :
			for (Fromage f : this.lesFromages) {
				if (f.getTypeFromage() == TypeLait.VACHE) {
					lesFromagesAuLaitDe.add(f);
				}
			}
			break;
		case CHEVRE :
			for (Fromage f : this.lesFromages) {
				if (f.getTypeFromage() == TypeLait.CHEVRE) {
					lesFromagesAuLaitDe.add(f);
				}
			}
			break;
		case BREBIS :
			for (Fromage f : this.lesFromages) {
				if (f.getTypeFromage() == TypeLait.BREBIS) {
					lesFromagesAuLaitDe.add(f);
				}
			}
			break;
		}
		return lesFromagesAuLaitDe;
	}
	
	/**
	 * Accéder à tous les fromages
	 * @return une liste de fromages
	 */
	public List<Fromage> getLesFromages() {
		return this.lesFromages;
	}
	
	/**
	 * Permet d'acceder à toute les désignations de fromages trié par ordre alphabétique
	 * @return une TreeSet de String
	 */
	public TreeSet<String> getDesignationLesFromagesOrdreAlphabétique() {
		TreeSet<String> treeSet =  new  TreeSet<String>();
		for (Fromage f : lesFromages) {
			treeSet.add(f.getDésignation());			
		}
		return treeSet;
	}
	
	/**
	 * Permet d'acceder uniquement aux désignations de fromages d'un type de lait précis
	 * @param lait de TypeLait
	 * @return une TreeSet de String
	 */
	public TreeSet<String> getDesignationFromageAuLaitDeOrdreAlphabétique(TypeLait lait) {
		TreeSet<String> treeSet =  new  TreeSet<String>();
		for (Fromage f : fromageAuLaitDe(lait)) {
			treeSet.add(f.getDésignation());			
		}
		return treeSet;
	}
	
	public Article getArticle (String désignation, String clé) {
        return this.getArticle(désignation, clé);
    }
	
	public int compteFromages(TypeLait lait) {
		int compteur = 0;
		for (Fromage f : this.fromageAuLaitDe(lait)) {
			compteur += 1;
		}
		return compteur;	
	}
	
	public void regénérationDuStock() {
		for (Fromage f : this.lesFromages) {
			if (f.nombreArticles() > 0) {
				for (Article article : f.getArticles()) {
					article.setQuantitéEnStock((int) Math.round(Math.random()*100));
				}
			}
		}
	}
	
	public String vérificationSaisie( ) {
		StringBuffer enForme = new StringBuffer();
		for (Fromage f : this.lesFromages) {
			if (f.nombreArticles() == 0) {
				enForme.append("Pas d'articles pour " + f.toString() + '\n');
			}
		}
		return enForme.toString();
	}
	
}
