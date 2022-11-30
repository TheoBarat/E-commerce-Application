package modèlePourEtudiants;

public class Client {
    
    private String prenom;
    private String nom;
    private String mail;
    private String téléphone;
    private String adresse;
    private String adresse2;
    private String codePostal;
    private String ville;

    /**
     * Crée un Client avec ses informations
     * @param nom du Client
     * @param prenom du Client
     * @param adresse du Client
     * @param adresse2 du Client
     * @param codePostal du Client
     * @param ville du Client
     * @param téléphone du Client
     * @param mail du Client
     */
    public Client(String nom, String prenom, String adresse, String adresse2, String codePostal, String ville, String téléphone, String mail) {
        this.prenom = prenom;
        this.nom = nom;
        this.mail = mail;
        this.téléphone = téléphone;
        this.adresse = adresse;
        this.adresse2 = adresse2;
        this.codePostal = codePostal;
        this.ville = ville ;
    }
    
    /**
     * Accéder au prénom du Client
     * @return une chaîne de caractère
     */
    public String getPrenom() {
        return this.prenom;
    }
    
    /**
     * Accéder au nom du Client
     * @return une chaîne de caractère
     */
    public String getNom() {
        return this.nom;
    }
    
    /**
     * Accéder au mail du Client
     * @return une chaîne de caractère
     */
    public String getMail() {
    	return this.mail;
    }
    
    /**
     * Accéder au téléphone du Client
     * @return une chaîne de caractère
     */
    public String getTéléphone() {
        return  this.téléphone ;
    }
    
    /**
     * Accéder à l'adresse du Client
     * @return une chaîne de caractère
     */
    public String getAdresse() {
        return this.adresse;
    }
    
    /**
     * Accéder à la seconde adresse du Client
     * @return une chaîne de caractère
     */
    public String getAdresse2() {
        return this.adresse2;
    }
    
    /**
     * Accéder au code postal du Client
     * @return une chaîne de caractère
     */
    public String getCodePostal() {
        return this.codePostal;
    }
    
    /**
     * Accéder à la ville du Client
     * @return une chaîne de caractère
     */
    public String getVille() {
        return  this.ville ;
    }
    
    /**
     * Verifier que toute les informations ont été remplies par le client
     * @return true ou false
     */
    public boolean informationsRemplies() {
    	int rempli = 0;
    	if (!(this.getNom().isBlank())) {
    		rempli ++;
    	}
    	if (!(this.getPrenom().isBlank())) {
    		rempli ++;
    	}
    	if (!(this.getAdresse().isBlank())) {
    		rempli ++;
    	}
    	if (!(this.getCodePostal().isBlank())) {
    		rempli ++;
    	}
    	if (!(this.getVille().isBlank())) {
    		rempli ++;
    	}
    	if (!(this.getTéléphone().isBlank())) {
    		rempli ++;
    	}
    	if (!(this.getMail().isBlank())) {
    		rempli ++;
    	}
    	return (rempli == 7);	
    }
    
    /**
     * Valeur unicode d'un Cient
     */
    @Override
    public String toString() {
 
        String chaine = "";
        chaine += "\n" + "Informations du Client :";
        chaine += "\n" + "Nom :  " + this.getNom();
        chaine += "\n" + "Prénom :  " + this.getPrenom();
        chaine += "\n" + "Adresse :  " + this.getAdresse();
        chaine += "\n" + "Adresse 2 :  " + this.getAdresse2();
        chaine += "\n" + "Code Postal :  " + this.getCodePostal();
        chaine += "\n" + "Ville  :  " + this.getVille();
        chaine += "\n" + "Téléphone :  " + this.getTéléphone();
        chaine += "\n" + "Mail  :  " + this.getMail();
        return chaine;
    }
}
