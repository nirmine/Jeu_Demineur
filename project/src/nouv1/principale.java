package nouv1;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.*;


public class Principale extends JFrame {
     
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
 public Principale() {
	//JFrame fenetre=new JFrame("Jeu Démineur");
	 this.setLocation(350, 50);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 this.setSize(new Dimension(600, 600));
		 JPanel p1=new JPanel();
		 JTextField nombreLigne=new JTextField();
		 JLabel l1=new JLabel("Donner le nombre des lignes");
		 JTextField nombreColonne=new JTextField();
		 JLabel l2=new JLabel("Donner le nombre des colonnes");
		 JButton valider=new JButton("OK");
		 int nbLigne=50;
		 int nbColonne=50;
		 valider.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent e) {
		       		{ 
		       			
		       			
		       		}}});
		   JButton nouvellePartie=new JButton("Nouvelle Partie");
		   nouvellePartie.addActionListener(new ActionListener(){
			      public void actionPerformed(ActionEvent e) {
	       		{
	       			p1.add(l1);
	       			p1.add(nombreLigne);
	       			p1.add(l2);
	       			p1.add(nombreColonne);
	       			p1.add(valider);
	       			
	       		}}});
		   JButton scores=new JButton("Score");
		   scores.addActionListener(new ActionListener(){
			      public void actionPerformed(ActionEvent e) {
	       		{
	       			
	       			
	       			
	       		}}});
		   this.getContentPane().setLayout(new GridLayout(1,2));
			
			p1.add(nouvellePartie);
			p1.add(scores);
			int compteurmine=0;//
			nouvellePartie leJeu=jeu(compteurmine);
			   this.setVisible(true);
 }
 
 public nouvellePartie jeu(int compteurMine)
 {int nbligne=5;
 int nbcolonne=5;
	 nouvellePartie jeux=new nouvellePartie(compteurMine);
	 jeux.mettreMine(compteurMine, nbligne, nbcolonne, jeux.plateau);
	 //remplir les case contenant des entiers
	 int x=0;
	 for(int i=0;i<nbligne;i++)
			for(int j=0;j<nbcolonne;j++)
				{if(((i>=0)||(i<=nbligne-2))&&((j>=0)||(j<=nbcolonne-2)))
				{  x=jeux.compterMineVoisine(i,j,jeux.plateau);
				if(x!=0)
					jeux.plateau[i][j].setContenu(Integer.toString(x));
				}
				else 
				{ if (i==0 && j==0)
					{
					if(jeux.plateau[i][1].contenu.equals("M"))
						x++;
					if(jeux.plateau[i+1][1].contenu.equals("M"))
						x++;
					if(jeux.plateau[i+1][2].contenu.equals("M"))
						x++;
					if(x!=0)
						jeux.plateau[1][1].setContenu(Integer.toString(x));
					
					}
				else
				{if (i==0 && j==nbcolonne-1)
				{
				if(jeux.plateau[i][j-1].contenu.equals("M"))
					x++;
				if(jeux.plateau[i+1][j-1].contenu.equals("M"))
					x++;
				if(jeux.plateau[i+1][j].contenu.equals("M"))
					x++;
				if(x!=0)
					jeux.plateau[i][j].setContenu(Integer.toString(x));
				
				}
				else
				{if (i==nbligne-1 && j==nbcolonne-1)
				{
				if(jeux.plateau[i][j-1].contenu.equals("M"))
					x++;
				if(jeux.plateau[i-1][j-1].contenu.equals("M"))
					x++;
				if(jeux.plateau[i-1][j].contenu.equals("M"))
					x++;
				if(x!=0)
					jeux.plateau[i][j].setContenu(Integer.toString(x));
				
				}
					
				
				else
				{if (i==nbligne-1 && j==0)
				{
				if(jeux.plateau[i][j+1].contenu.equals("M"))
					x++;
				if(jeux.plateau[i-1][j+1].contenu.equals("M"))
					x++;
				if(jeux.plateau[i-1][j].contenu.equals("M"))
					x++;
				if(x!=0)
					jeux.plateau[i][j].setContenu(Integer.toString(x));
				
				}
				else
				{
					x=jeux.comparerFrontieres(i, j, jeux.plateau, nbligne,nbcolonne);
					if(x!=0)
						jeux.plateau[i][j].setContenu(Integer.toString(x));								
				}
				}
					
				}
				}
				
				}
 }
	 return jeux;
}


	 }
class nouvellePartie extends JPanel
{
	int compteurCoups;
	int compteurMine;
	Case[][] plateau;
	
	public nouvellePartie(int nbligne,int nbcolonne,int compteurMine ) 
	{   compteurCoups=0;
		this.compteurMine=compteurMine;
		for(int i=0;i<nbligne;i++)
			for(int j=0;j<nbcolonne;j++)
				plateau[i][j]=new Case(0,"X");//la case est par défaut un blanc couverte
		        mettreMine(compteurMine,nbligne,nbcolonne,plateau);
		}
	
	public void mettreMine(int compteurMine,int nbligne,int nbcolonne,Case[][] plateau) {
		int nombre=0;
		Vector vecteur=new Vector(compteurMine);//vecteur d'elts qui contient déja des mines
		Random random=new Random();
		do
		{int numligne=random.nextInt(nbligne-1);//entier aléatoire entre 0 et nbligne
		 int numColonne=random.nextInt(nbcolonne);
		 int element=numligne*10+numColonne;//pour mettre sous la forme ij
		do
		{  numligne= random.nextInt(nbligne);
           numColonne=random.nextInt(nbcolonne);
          element=numligne*10+numColonne;
        }
		 while (vecteur.contains(element));
		vecteur.add(element);
	 nombre++;//compter le nbr de mine déja ajouter
	    plateau[numligne][numColonne].setContenu("M");
		}
	 while (nombre<compteurMine);
	}
	public int compterMineVoisine(int numligne,int numcolonne,Case[][] plateau)
	{ int compteur=0;
		for(int i=numligne-1;i<=numligne+1;i++)
			for(int j=numcolonne-1;j<=numcolonne+1;j++)
				{if(i!=numligne||j!=numcolonne)
					if(plateau[i][j].contenu.equals("M"))
						compteur++;
				}
		return compteur;
				}
					
				
public nouvellePartie(int compteurMine ) 
{   compteurCoups=0;
	this.compteurMine=compteurMine;
	int nbligne =50;
	int nbcolonne=50;
	for(int i=0;i<nbligne;i++)
		for(int j=0;j<nbcolonne;j++)
			plateau[i][j]=new Case(0,"X");
	}
public int comparerFrontieres(int numL,int numC,Case[][] tab,int nbL,int nbC)
{ int x=0;
if(numL==nbL)
	{for(int i=nbL-2;i<=nbL-1;i++)
	for(int j=numC-1;j<=numC+1;j++)
		if((tab[i][j].contenu.equals("M"))&&(i!=numL||j!=numC))
			x++;}
else
{if(numL==0)
{for(int i=0;i<=1;i++)
	for(int j=numC-1;j<=numC+1;j++)
		if((tab[i][j].contenu.equals("M"))&&(i!=numL||j!=numC))
			x++;}
else
{if(numC==nbL-1)
{for(int i=numL-1;i<=numL+1;i++)
	for(int j=numC-1;j<=numC;j++)
		if((tab[i][j].contenu.equals("M"))&&(i!=numL||j!=numC))
			x++;}
else
{if(numC==0)
{for(int i=numL-1;i<=numL+1;i++)
	for(int j=numC;j<=numC+1;j++)
		if((tab[i][j].contenu.equals("M"))&&(i!=numL||j!=numC))
			x++;}
}
}}
	return x;
	
}
/*public int comparerDerniereColonne(int numC,Case[][] tab,int nbL)
{ int x=0;
	for(int i=nbL-2;i<=nbL-1;i++)
	for(int j=numC-1;j<=numC+1;j++)
		if(tab[i][j].contenu.equals("M"))
			x++;
	return x;
	
}*/

}
		//creer un plateau vide}
class Case {
	JButton bouton;
	int etat;//0 si couverte;1 si découverte ;-1 si un flag
	String contenu;// M:mine ; entier ; X :blanc ; D:drapeau
	public Case (int etat,String contenu)
	{this.contenu=contenu;
	this.etat=etat;
	this.bouton=new JButton(contenu);
		
	}
	public void setContenu(String c)
	{this.contenu=c;}
	public void setEtat(int y)
	{this.etat=y;}
	
}