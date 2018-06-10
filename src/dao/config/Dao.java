package dao.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import modele.Joueur;
import modele.domino.JoueurDomino;
import modele.triomino.JoueurTriomino;

public class Dao {
	private String fileName;
	
	public Dao(String fileName) {
		this.fileName = fileName;
		
	}

	public boolean baseExistante() {
		return new File(fileName).exists();
	}

	public void creerBase() {
		if (!baseExistante()) {
			File file=new File(fileName);
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Document document = new Document();
			document.setRootElement(new Element("Historique"));
			XMLOutputter write=new XMLOutputter(Format.getPrettyFormat());
			try {
				write.output(document, new FileOutputStream(fileName));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void viderBase(){
		File file=new File(fileName);
		if(file.exists()){
		file.delete();
		this.creerBase();
		}
	}
	public void ajouterPartieDomino(ArrayList<JoueurDomino> joueurs,int indexDuGagnant){
		this.creerBase(); // si la base n'existe pas
		Document document = null ;
		try {
			document=new SAXBuilder().build(new File(fileName));
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element partie=new Element("Partie");
		Element elementJeu=new Element("Jeu");
		elementJeu.addContent("Domino");
		partie.addContent(elementJeu);
		Element elementJoueurs=new Element("Joueurs");
		Element joueur=new Element("Gagnant");
		joueur.addContent(new Element("Id").addContent(String.valueOf(indexDuGagnant)));
		joueur.addContent(new Element("Nom").addContent(joueurs.get(indexDuGagnant).getNom()));

		joueur.addContent(new Element("Cpu").addContent(String.valueOf(joueurs.get(indexDuGagnant).isCpu())));
	
		elementJoueurs.addContent(joueur);
		for( int i=0;i<joueurs.size();i++){
			if(i==indexDuGagnant)
				continue;
			joueur=new Element("Joueur");
			joueur.addContent(new Element("Id").addContent(String.valueOf(i)));
			joueur.addContent(new Element("Nom").addContent(joueurs.get(i).getNom()));
			
			joueur.addContent(new Element("Cpu").addContent(String.valueOf(joueurs.get(i).isCpu())));
		
			elementJoueurs.addContent(joueur);
		}
		partie.addContent(elementJoueurs);
		document.getRootElement().addContent(partie);
		XMLOutputter output=new XMLOutputter();
		try {
			output.output(document, new FileOutputStream(new File(fileName)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void ajouterPartieTriomino(ArrayList<JoueurTriomino> joueurs,int indexDuGagnant){
		this.creerBase(); // si la base n'existe pas
		Document document = null ;
		try {
			document=new SAXBuilder().build(new File(fileName));
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element partie=new Element("Partie");
		Element elementJeu=new Element("Jeu");
		elementJeu.addContent("Triomino");
		partie.addContent(elementJeu);
		Element elementJoueurs=new Element("Joueurs");
		Element joueur=new Element("Gagnant");
		joueur.addContent(new Element("Id").addContent(String.valueOf(indexDuGagnant)));
		joueur.addContent(new Element("Nom").addContent(joueurs.get(indexDuGagnant).getNom()));
		
			joueur.addContent(new Element("Score").addContent(String.valueOf(joueurs.get(indexDuGagnant).getScore())));
		
		joueur.addContent(new Element("Cpu").addContent(String.valueOf(joueurs.get(indexDuGagnant).isCpu())));
	
		elementJoueurs.addContent(joueur);
		for( int i=0;i<joueurs.size();i++){
			if(i==indexDuGagnant)
				continue;
			joueur=new Element("Joueur");
			joueur.addContent(new Element("Id").addContent(String.valueOf(i)));
			joueur.addContent(new Element("Nom").addContent(joueurs.get(i).getNom()));
				joueur.addContent(new Element("Score").addContent(String.valueOf(joueurs.get(i).getScore())));
			
			joueur.addContent(new Element("Cpu").addContent(String.valueOf(joueurs.get(i).isCpu())));
		
			elementJoueurs.addContent(joueur);
		}
		partie.addContent(elementJoueurs);
		document.getRootElement().addContent(partie);
		XMLOutputter output=new XMLOutputter();
		try {
			output.output(document, new FileOutputStream(new File(fileName)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
		Dao dao=new Dao("sss.xml");
		System.out.println(dao.baseExistante());
		dao.creerBase();
		System.out.println(dao.baseExistante());
		ArrayList<Joueur>joueurs=new ArrayList<Joueur>();
		joueurs.add(new Joueur("j1", 100, false));

		joueurs.add(new Joueur("j2", 100, false));

		joueurs.add(new Joueur("j3", 100, false));
		
		
	}
}
