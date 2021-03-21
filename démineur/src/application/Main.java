package application;
	
import javax.swing.GroupLayout.Alignment;

import java.io.*;
import java.util.*;


import javax.swing.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.text.Font;



public class Main extends Application {
	
	int nbLigne;
	int nbColonne;
	int nbMine;
	 boolean test;
	
	public void start(Stage primaryStage) {
		try {
			HBox root = new HBox();
			primaryStage.setHeight(600);
			primaryStage.setWidth(600);
			HBox boutons = new HBox();	//qui va contenir les boutons de départ	
			VBox gauche=new VBox();
			String cheminproj = System.getProperty("user.dir"); //chemin du projet
			Image image = new Image(new FileInputStream(cheminproj+"/les démineurs.png/"));
		      ImageView imageView = new ImageView(image);
			Button score=new Button("Scores");
			score.setMinSize(120, 50);
			Button nouvellePartie=new Button("Nouvelle Partie");
			nouvellePartie.setMinSize(120, 50);
			gauche.getChildren().add(imageView);
			gauche.getChildren().add(boutons);
			 GridPane grid1 = new GridPane();					
			 grid1.setVgap(10);
			  test=false;
			 grid1.setPadding(new Insets(10, 10, 10, 10));
			 grid1.add(score,1,2);
			 grid1.add(nouvellePartie,1,1);
			 Label d=new Label("Prêt(e) pour jouer ?");
			d.setFont(new Font(30));
			GridPane grid3 = new GridPane();					
			 grid3.setVgap(30);
			 grid3.add(d, 1,1);
			 grid3.add(grid1, 1,2);
			boutons.getChildren().addAll(grid3);
			root.getChildren().addAll(gauche);
			score.setOnAction(e->{
				try
				{    test=true;
					boutons.getChildren().remove(grid3);
					boutons.getChildren().add(grid1);
					grid1.setHgap(50);
					grid1.setVgap(50);
					boutons.setPadding(new Insets(0, 20, 20, 0));
					Image image2 = new Image(new FileInputStream(cheminproj+"/gagnants.png/"));
			      ImageView imageView2 = new ImageView(image2);
			      imageView2.setFitHeight(200);
			      imageView2.setFitWidth(200);
			      imageView2.setFitHeight(200);
					grid1.getChildren().remove(score);					
					FileReader F = new FileReader(cheminproj+"/dossierScores/scores");
					BufferedReader in = new BufferedReader (F); // Lire le fichier
					String ligne;
					VBox labelScores = new VBox();
					Label c=new Label("Les meilleurs scores sont:");
					c.setFont(new Font(20));
					c.setUnderline(true);
					labelScores.getChildren().add(c);
				
					while((ligne=in.readLine())!=null) // Lecture ligne par ligne
					{ 
					c=new Label(ligne);
					c.setFont(new Font(15));
					labelScores.getChildren().add(c);
										
					}
					grid1.add(imageView2,1, 2);
					grid1.add(labelScores,2,2);
				}
				catch(IOException evt)
				{
					evt.getStackTrace();
				}
				
				});
			boutons.setPadding(new Insets(50, 20, 20, 200));
			GridPane grid = new GridPane();			
			 grid.setHgap(100);
			 grid.setVgap(20);
			 grid.setPadding(new Insets(10, 10, 10, 10));
			HBox box1=new HBox();
			box1.setSpacing(50);	
			Button valider=new Button("Commencer");
			TextField saisieLigne=new TextField();			
			box1.getChildren().addAll(new Label("Donnez le nombre des lignes"),saisieLigne);
			grid.add(box1,1,1);
			HBox box2=new HBox();
			box2.setSpacing(30);		
			TextField saisieColonne=new TextField();
			box2.getChildren().addAll(new Label("Donnez le nombre des colonnes"),saisieColonne);
			grid.add(box2,1,2);
			HBox box3=new HBox();
			box3.setSpacing(50);		
			TextField saisieMine=new TextField();
			box3.getChildren().addAll(new Label("Donnez le nombre des mines"),saisieMine);
			grid.add(box3, 1, 3);
			grid.add(valider,1,4);
		      Label titre1=new Label("Attention Aux Mines");
		      titre1.setPrefSize(50, 50);
			nouvellePartie.setOnAction(e->{
				if(!test)
				{boutons.getChildren().remove(grid3);
				boutons.getChildren().add(grid1);
				}
				 boutons.getChildren().remove(grid1);// parceque la fenetre va contenir que le plateau les compteur coups et mines				
				
				 gauche.getChildren().add(grid);// ajouter la grille qui contient le plateau les compteur coups et mines
				
			});
	
		     valider.setOnAction(e->{ 
		    	 try
		    	 {  
		    		 gauche.getChildren().remove(boutons);
		    		//récuper les données saisies par le joueur
		    		nbColonne=Integer.parseInt(saisieColonne.getText());
		    	  nbLigne=Integer.parseInt(saisieLigne.getText());	
		    	  nbMine=Integer.parseInt(saisieMine.getText());	
		    	  if(nbColonne>1 && nbLigne>1 && nbMine<(nbColonne*nbLigne)) //controller  les données siasies par le joueur
		    	  { gauche.getChildren().remove(grid);
		    	  GridPane grid2 = new GridPane();					
					 grid2.setHgap(50);
					 grid2.setVgap(20);
					 grid2.setPadding(new Insets(10, 10, 10, 10));
		    		  VBox plat=creerPlateau(nbMine,nbLigne,nbColonne);//creer le plateau et les compteurs avec ces données saisies
		    		grid2.add(plat,1,0);		    		
		    		  gauche.getChildren().add(grid2);
		    		
		    		
		    	  }
		    		
		    	  else
		    	  {   Alert msg=new Alert(AlertType.WARNING); 
		    	  msg.setHeaderText("Warning !!!");
		    	  msg.setTitle ("message d'erreur");
		    	  msg.setContentText("veuillez saisir un nombre de ligne et un nombre de colonne  \n"
		    		  		+ "supérieurs à 1 et un nombre de mine inférieur au totale des cases :"+(nbColonne*nbLigne));
		    	  msg.showAndWait();
		    		 
		    	  }
		    	  
		     }
		    catch(NumberFormatException evt)
		    	 {Alert msg=new Alert(AlertType.WARNING);
		    	  msg.setHeaderText("Warning !!!");
		    	  msg.setTitle ("message d'erreur");
		    	  msg.setContentText("Veuillez saisir que des nombres !");
		    	  msg.showAndWait();}
		     }
		     );
		     final FlowPane container = new FlowPane();
		     container.getChildren().add(root);
		     ScrollPane pane=new ScrollPane(container);	 //ajouter un scroll en cas ou le nombre des cases est trés élevé     
			Scene scene = new Scene(pane,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Jeu Démineur");
			
			primaryStage.show();
			}
		 catch(Exception e)
			{			
				e.printStackTrace();						      		
			}
		   	   
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	public  VBox creerPlateau(int compteurMine,int  nbligne,int nbcolonne)
	{
		GridPane grid = new GridPane();//la grille càd le plateau qui va étre remplie par des boutons
		GridPane grid2 = new GridPane();					
		 grid2.setHgap(100);
		 grid2.setVgap(20);
		 grid2.setPadding(new Insets(10, 10, 10, 10));
		 Label a=new Label("Nombre de mines:"+nbMine);
		 Label b=new Label("Nombre de coups joués:"+"0");
		 b.setFont(new Font(15));
		 a.setFont(new Font(15));
		 HBox labels=new HBox();
		 b.setPadding(new Insets(0, 0,0,50));
		  labels.getChildren().addAll(a,b); // ajouter les compteurs coups et mines
		  VBox plat=new VBox();		
		  grid2.add(labels, 1, 1);
		  grid2.add(grid, 1, 2);
		  nouvellePartie jeux=new nouvellePartie(nbligne,nbcolonne,compteurMine);// créer la matrice des cases initialement remplie par des cases blancs
		  jeux.mettreMine(compteurMine, nbligne, nbcolonne, jeux.plateau);//mettre aléatoirement les mines
		 //remplir les cases contenant des entiers
		 int x=0;
		 for(int i=0;i<nbligne;i++)
			 for(int j=0;j<nbcolonne;j++)
				 if(!jeux.plateau[i][j].contenu.equals("M"))// si le contenu de la case non découverte n'est pas une mine					
					 { 
					 x=jeux.compterMineVoisine(i,j,jeux.plateau);//compter les nbr des mines voisines
					 
					 if(x!=0) // si la case n'est pas voisine qu'à des blancs càd s'il a au moins une mine voisine
						 jeux.plateau[i][j].setContenu(Integer.toString(x)); // remplir la case avec le nbr des mines voisines
					 }

		 for(int i=0;i< nbLigne;i++)
				for(int j=0;j<nbColonne;j++)
					{
					grid.add(jeux.plateau[i][j].bouton,i,j); //creer le plateau en créant une grille des boutons
					int X=j;//numéro de colonne
					int Y=i;//numéro de ligne
					String ch=jeux.plateau[i][j].contenu;//le contenu de la case qui a été découverte
					jeux.plateau[i][j].bouton.setOnMouseClicked(event ->
			        {
			            if (event.getButton() == MouseButton.PRIMARY)//clique sur le bouton gauche de la sourie
			            {   if(((Button)event.getSource()).getText().equals(""))//bouton non découvert et à découvrir			            
			            		jeux.compteurCoups++;			            		
			            		 b.setText("Nombre de coups:"+jeux.compteurCoups);
			            		 if(ch.equals("M"))			            		
			            		 { jeux.chercher(X, Y);			            
			            		  Label perte=new Label("Vous avez perdu !!!");	
			            		  perte.setFont(new Font(20));
			            		  HBox nouv=new HBox();
			            		  nouv.getChildren().add(perte);			            		  
			            		  grid2.add(nouv,1, 0);
			            		 }
			            		
			            		 else
			            			 {
			            			 jeux.chercher(X, Y);
			            			 if(jeux.CaseDecouvertes==((nbLigne*nbColonne)-nbMine))//cas de gain
			            			 {for(int k=0;k<jeux.plateau.length;k++)
			            					for(int z=0;z<jeux.plateau[k].length;z++)
			            						if(jeux.plateau[k][z].bouton.getText().equals(""))
			            							jeux.plateau[k][z].bouton.setDisable(true);
			            			  Label gain=new Label("Vous avez gagné");
			            			  gain.setFont(new Font(20));
			            	   		  HBox nouv=new HBox();
			            	   		  nouv.getChildren().add(gain);
			            	   		  grid2.add(nouv, 1, 3);
			            	   		 Label coordGangnant=new Label("Veuiller saisir votre prénom");
			            	   		 Button Ok=new Button("Valider");
			            	   		 TextField nom=new TextField();
			            	   		 HBox nouv2=new HBox();
			            	   		 nouv2.getChildren().addAll(coordGangnant,nom,Ok);
			            	   		 grid2.add(nouv2, 1, 4);			            	   		 
			            	   		 Ok.setOnAction(
			            	   		e->{
			            	   		try
				            	   	{
			            	   			String nomSaisie=nom.getText();
			            	   			if(nomSaisie.equals(""))
			            	   				nomSaisie="Joueur quelconque";
			            	   			String score=Integer.toString(jeux.compteurCoups);
			            	   			String cheminproj = System.getProperty("user.dir"); // Chemin du projet
			            	   			FileWriter f = new FileWriter(cheminproj+"/dossierScores/scores" , true);
			            	   			f.write(nomSaisie+" "+score+"\n");			            	   	
			            	   			f.close();			            	   					            	  
			            	   		}
			            	   		catch(IOException evt)
			            	   			{evt.getStackTrace();}
			            	   		
			            	   		 });
			            			 }
			            			 }
			            		
			                
			            } else if (event.getButton() == MouseButton.SECONDARY)//clique sur le bouton gauche de la sourie càd marquer une case
			            {  
			            	if(((Button)event.getSource()).getText().equals("D"))//si le bouton est déjà marqué comme un drapeau 
			            	{
			            		((Button)event.getSource()).setText("");
			            		jeux.compteurMine++;
			            		 a.setText("Nombre de mines:"+jeux.compteurMine);
			            	}
			            	else if(((Button)event.getSource()).getText().equals(""))//si le bouton n'est pasdéjà marqué comme un drapeau 
			            	{
		            			((Button)event.getSource()).setText("D");
		            			jeux.compteurMine--;
		            			 a.setText("Nombre de mines:"+jeux.compteurMine);
			            	}
			            	
			            }
			        });
					
					
					
					}
		 plat.getChildren().add(grid2);
		 return plat;
		 }
}

class nouvellePartie 
{   int CaseDecouvertes;
	int compteurCoups;
	int compteurMine;
	Case[][] plateau;
	
	public nouvellePartie(int nbligne,int nbcolonne,int compteurMine ) 
	{   compteurCoups=0;
	    CaseDecouvertes=0;
		this.compteurMine=compteurMine;
		plateau=new Case[nbligne][nbcolonne];
		for(int i=0;i<nbligne;i++)
			for(int j=0;j<nbcolonne;j++)
				plateau[i][j]=new Case("X");//la case est par défaut un blanc couverte
		}
	
	//Methode pour mettre des mines d'une facon aléatoire
	public void mettreMine(int compteurMine,int nbligne,int nbcolonne,Case[][] plateau) {
		int nombre=0;
		Vector vecteur=new Vector(compteurMine);//vecteur d'elts qui contient déja des mines
		Random random=new Random();
		int numColonne;
		int numligne;
		int element;
		do
		{ 
			do
			{ // saisir une case aléatoirement
				numligne= random.nextInt(nbligne);
				numColonne=random.nextInt(nbcolonne);
       
			}
			while (plateau[numligne][numColonne].getContenu().equals("M"));//par défaut tout les cases contient X comme contenu et donc si la case ne contient pas déjà une mine
		
			
			nombre++;//compter le nbr de mine déja ajouté
			plateau[numligne][numColonne].setContenu("M");
		}
	 while (nombre<compteurMine);
	}
	
	//Methode pour compter le nombre de mines voisines d'une case dont ses coordonnées sont passés en paramétre
	public int compterMineVoisine(int numligne,int numcolonne,Case[][] plateau)
	{ 
		int compteur=0;	//le compteur des nombre de mine voisines
		for(int i=numligne-1;i<=numligne+1;i++)
			for(int j=numcolonne-1;j<=numcolonne+1;j++)
				{
				if(i<plateau.length && 0<=i) //si le numéro de ligne de case ne dépasse pas les indices du plateau
					if(j<plateau[i].length && 0<=j) //si le numéro de colonne de case ne dépasse pas les indices du plateau
					if(plateau[i][j].contenu.equals("M"))
						compteur++;
				}
		return compteur;
				}

								
public void chercher (int x ,int y)//y:numéro de ligne et x:numéro de colonne
{
	if(!plateau[y][x].bouton.getText().equals(""))//si le bouton est  découvert
		return;//quitter la methode si la case actuelle est découverte
	if(plateau[y][x].contenu.equals("X"))//Si le contenu de la case est un blanc la fonction est appelée récursivement
		//pour les 8 cases voisines de la case actuelle et il s'affiche sur le bouton X
	{ 
		plateau[y][x].bouton.setText("X");
		plateau[y][x].bouton.setDisable(true);
		CaseDecouvertes++;	
		//chercher tous les cases dans les 8 cases voisines à la case courante contenant des blancs
		for(int i=y-1;i<=y+1;i++)
			for(int j=x-1;j<=x+1;j++)
				{if(i<plateau.length && 0<=i && (i!=y || j!=x))
					if(j<plateau[i].length && 0<=j)
								chercher(j,i);			
				}
				
		
	}
	else 
	{ if(!plateau[y][x].contenu.equals("M")) // si la case non découverte contient un entier
		{
		plateau[y][x].bouton.setText(plateau[y][x].contenu);//afficher le contenu de la case sur le bouton
		CaseDecouvertes++;
		}
	else //si la case courante contient une mine
		{
	
		for(int i=0;i<plateau.length;i++)
			for(int j=0;j<plateau[i].length;j++)
				if(plateau[i][j].contenu.equals("M"))
					plateau[i][j].bouton.setText("M");//afficher tout les mines
				else
					{if(plateau[i][j].bouton.getText().equals(""))
					plateau[i][j].bouton.setDisable(true);
					}
							return;}}
	}
	



}	
class Case {
	
	Button bouton;
	String contenu;// M:mine ; entier ; X :blanc ; D:drapeau
	
	
	public Case (String contenu)
	{this.contenu=contenu;
	this.bouton=new Button();
	this.bouton.setMinSize(30, 30);	
	}
	
	public void setContenu(String c)
	{this.contenu=c;}

	public String getContenu()
	{return this.contenu;}
}
