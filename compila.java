package compila;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class compila {
	private JFrame frame;
        private String commentaire,var[];
        private JLabel sortie;
        private JPanel panel,scroll;
	private JTextArea textArea;
        private JButton btnlex,btnsyn,btnsem,charger;
	static JFileChooser file_chooser = new JFileChooser("C:\\Users");
	static ArrayList<String> mots = new ArrayList<String>();
        static ArrayList<String> comm = new ArrayList<String>();
	static ArrayList<String> lignes = new ArrayList<String>();
	static ArrayList<String> affiche_lex = new ArrayList<String>();
	static String[] mot;

//démarrage de l'application        
public static void main(String[] args) {
                                new compila(); 
	}

//création du Jframe        
public compila() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Analyseur COMPILA");
		frame.getContentPane().setBackground(Color.darkGray);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
                frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
                panel.setLayout(null);
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(10, 38, 258, 395);
		frame.getContentPane().add(panel);
		charger = new JButton("Charger");
		charger.setBounds(10, 50, 236, 59);
		charger.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                try {
			textArea.setText("");
			charger();
			int i = 0;
			while (i < lignes.size()) {
			textArea.setText(textArea.getText()+lignes.get(i)+"\n");
			i++;}
}       
                catch (FileNotFoundException e1) {
							e1.printStackTrace();
		}}});
		charger.setForeground(Color.BLACK);
		charger.setFont(new Font("Roboto", Font.BOLD, 17));
                charger.setBorderPainted(true);
                charger.setFocusPainted(false);
                charger.setContentAreaFilled(false);
		btnlex = new JButton("Analyse Lexicale");
		btnlex.setBounds(10, 140, 236, 59);
		btnlex.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		textArea.setText("");
		lexicale(mots);
		int i = 0;
		while (i < mots.size()) {
		textArea.setText(textArea.getText()+mots.get(i) + " : " + affiche_lex.get(i)+"\n");
		i++;}
		}
});
                btnlex.setForeground(Color.BLACK);
		btnlex.setFont(new Font("Roboto", Font.BOLD, 17));
		btnsyn = new JButton("Analyse Syntaxique");
		btnsyn.setBounds(10, 220, 236, 59);
		btnsyn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		textArea.setText("");
		int i = 0;
		while (i < lignes.size()) {
		textArea.setText(textArea.getText()+lignes.get(i) + " : " +syntax(lignes.get(i))+"\n");
		i++;}
		}
				});
		btnsyn.setForeground(Color.BLACK);
		btnsyn.setFont(new Font("Roboto", Font.BOLD, 17));
		btnsem = new JButton("Analyse Sémantique");
		btnsem.setBounds(10, 300, 236, 59);
		btnsem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		textArea.setText("");
		int i = 0;
		while (i < lignes.size()) {
		textArea.setText(textArea.getText()+lignes.get(i) + " : " +semantique(lignes.get(i))+"\n");
		i++;}
		i=0;
        }
				});
		btnsem.setForeground(Color.BLACK);
		btnsem.setFont(new Font("Roboto", Font.BOLD, 17));
		sortie = new JLabel("<html><font color='white'>Sortie</font></html>");
		sortie.setFont(new Font("Robota", Font.BOLD, 20));
		sortie.setBounds(413, 0, 93, 48);
		frame.getContentPane().add(sortie);
		scroll = new JPanel();
		scroll.setBackground(Color.WHITE);
		scroll.setBounds(273, 38, 350, 395);
		frame.getContentPane().add(scroll);
		scroll.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 330, 375);
		scroll.add(scrollPane);
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setForeground(Color.BLACK);
		textArea.setBackground(Color.WHITE);
		textArea.setFont(new Font("Roboto", Font.BOLD, 12));
                panel.add(charger);
                panel.add(btnlex);
                panel.add(btnsyn);
                panel.add(btnsem);
                frame.setVisible(true);
}
//chargement du fichier .compila
public static void charger() throws FileNotFoundException {
		file_chooser.addChoosableFileFilter(new FileNameExtensionFilter(".compila", "compila"));
		if(file_chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
			File file = file_chooser.getSelectedFile();
			Scanner sc_lignes = new Scanner(file);
			Scanner sc_mots = new Scanner(file);
			mots.clear();
			lignes.clear();
				while(sc_lignes.hasNextLine()){
					lignes.add(sc_lignes.nextLine());
				}
				while(sc_mots.hasNext()){
					mots.add(sc_mots.next());
					}
			sc_mots.close();
			sc_lignes.close();
			}
	}
	public boolean isNumber(String chaine, int i) {
		char[] nombre = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
		int j = 0;
		while (j < nombre.length) {
			if (chaine.charAt(i) == nombre[j]) {
				return true;
			}
			j++;
		}
		return false;
	}
	public String num(String chaine) {
		int i = 0;
		int token_pos = 0;
		boolean point_unique = true;
		while (i < chaine.length()) {
			if (isNumber(chaine, i)) token_pos++;
			else if(point_unique & chaine.charAt(token_pos)==',') {
				token_pos++;
				point_unique = false;
			}
			i++;
		}

		if (token_pos == chaine.length() && !chaine.contains(",")) return "Nombre entier";
		else if (token_pos == chaine.length() && !point_unique) return "Nombre reel";
		return null;
	}
	public boolean isChar(String chaine, int i) {
		char[] alphabet = { 'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g', 'H', 'h', 'I', 'i',
				'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T',
				't', 'U', 'u', 'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y', 'Z', 'z' };
		int k = 0;
		while (k < alphabet.length) {
			if (chaine.charAt(i) == alphabet[k]) {
				return true;
			}
			k++;
		}
		return false;
	}
	public String ident(String chaine) {
		boolean verifier_Premier = false;
		boolean tiret_unique = true;
		int token_pos = 0;
		int i = 0;
		if (isChar(chaine, 0)) {
			token_pos++;
			verifier_Premier = true;
		}
		if (verifier_Premier == true && chaine.length() == 1)
			return "identificateur";
		else if (chaine.length() > 1) {
			i = 1;
			while (i < chaine.length()) {

				if (isChar(chaine, i))
					{token_pos++;
					tiret_unique=true;
					}
				else if (isNumber(chaine, i))
					{token_pos++;
					tiret_unique=true;
					}
				else if (chaine.charAt(i) == '_' && tiret_unique) {
					tiret_unique=true;
					token_pos++;
				}
				i++;
			}
			if (token_pos == chaine.length())
				return "Identificateur";
		}
		return null;
	}
        //Analyse syntaxique
	public String syntax(String chaine){	
		if(chaine.equals("Start_Program")) return "Début du programme";
		else if(chaine.equals("Else")) return "SINON";
		else if(chaine.startsWith("//.")) return "un commentaire";
		else if(chaine.equals("End_Program")) return "Fin du programme";
		else if(chaine.startsWith("ShowMess : \" ") && chaine.endsWith(" \" ;;")) return "Affichage d'un message à l'ecran";
		else if(chaine.startsWith("ShowVal : \" ") && chaine.endsWith(" \" ;;")) return "Affichage d'une valeur";
                else if (chaine.startsWith("Start")) return "Début d'un bloc";
                else if (chaine.startsWith("Finish")) return "Fin d'un bloc";    
                else if(chaine.contains(" ")) {
			mot = chaine.split(" ");
			int i=0, k=1;
				if(mot[i].equals("Int_Number")){
					i++;
                                            if(mot[i].equals(":")) i++;
                                               if(mot[i].contains(",") && !(mot[i].startsWith(",")) && !(mot[i].endsWith(","))) {
							var=mot[i].split(",");i++;
							if(mot[i].equals(";;")) return "Déclaration de "+var.length+" variables entieres";var=null;}
                                                  else if( !(mot[i].startsWith(",")) && !(mot[i].endsWith(","))){
                                                            i++;
                                                            if(mot[i].equals(";;")) return "Déclaration d'une variable entiere";var=null;
                                                                }
                                }
				else if(mot[i].equals("Give")){
					i++;
					if(ident(mot[i]) != null){
						i++;
					if(mot[i].equals(":"))i++;
                                        if(num(mot[i]) == "Nombre entier") {
							i++;
							if(mot[i].equals(";;")) return "affectation dune valeur "+mot[i-2];
						}
						else if(num(mot[i]) == "Nombre reel"){
							i++;
							if(mot[i].equals(";;")) return "affectation dune valeur reel à "+mot[i-3];
						}}}				
				else if(mot[i].equals("Real_Number")){
					i++;
                                            if(mot[i].equals(":")) i++;
                                               if(mot[i].contains(",") && !(mot[i].startsWith(",")) && !(mot[i].endsWith(","))) {
							var=mot[i].split(",");i++;
							if(mot[i].equals(";;")) return "Déclaration de "+var.length+" variables reelles";var=null;}
                                                  else if( !(mot[i].startsWith(",")) && !(mot[i].endsWith(","))){
                                                            i++;
                                                            if(mot[i].equals(";;")) return "Déclaration d'une variable reel";var=null;
                                                                }
                                }
				else if(mot[i].equals("If")){
					i++;
					if(mot[i].equals("--")){
						i++;
					if(ident(mot[i]) != null){
						i++;
						if(mot[i].equals("<") || mot[i].equals(">") || mot[i].equals("==")){
						i++;
						if(ident(mot[i]) != null){
							i++;
						if(mot[i].equals("--")) return "condition"; 
							}}}}
				}
                        else if(mot[i].equals("Affect")){
					i++;
					if(ident(mot[i]) != null){
						i++;
					if(mot[i].equals("to")){
						i++;
						if(ident(mot[i]) != null) {
							i++;
							if(mot[i].equals(";;")) return "affectation de "+mot[i-3]+" a "+mot[i-1];
						}
					}
				}
				}
				else if(mot[i].equals("ShowVal")){
					i++;
						if(ident(mot[i]) != null)i++;
                                                    if(mot[i].equals(":"))i++;
                                                          if(ident(mot[i]) != null) {i++;
							if(mot[i].equals(";;")) return "affichage de la valeur de "+mot[i-1];
						}}
										}
		return "erreur de syntaxe";
	}
        //Vérification mot reservé
        public String reserve_lex(String chaine) {		
		String[] mot_reserve = {"If","--",":","\"", "<", ">", ",", "Start_Program", "Int_Number", ";;", "Affect", "Real_Number", "If", "--", "Else",
				"Start", "Give","to", "Finish", "ShowMess","ShowVal", "//.", "End_Program" };
		String[] Affichage = {"Mot reserve pour une condition","Mot reserve pour une condition","Caractere reserve","mot reserve pour guillemets","symbol inferieur", "symbol superieur", "caractere reservé virgule",
				"Mot reserve debut du programme", " Mot reserve debut declaration d'un entier",
				"Mot reserve fin instruction", "Mot reserve pour affectation entre variables", " Mot reserve debut declaration d'un Real",
				" Mot reserve pour condition SI", "Mot reserve pour condition", "Mot reserve pour condition SINON", "Debut d'un sous programme",
				"Mot reserve", "Mot reserve pour affectation", "Fin d'un sous programme",
				"Mot reservé pour afficher un message","Mot reservé pour afficher une valeur" ,"Mot reservé pour un commentaire", "Mot reserve Fin du programme"};
		int i = 0;
		while (i < mot_reserve.length) {
			if (chaine.equals(mot_reserve[i])) {
				return Affichage[i];
			}
			i++;
		}
		return null;
	}
        //Analyse lexicale
	public void lexicale(List<String> liste) {
		int i = 0;
		while (i < mots.size()) {
			if (reserve_lex(mots.get(i)) != null) {
				affiche_lex.add(reserve_lex(mots.get(i)));
			} else if (ident(mots.get(i)) != null) {
				affiche_lex.add(ident(mots.get(i)));
			} else if (num(mots.get(i)) != null) {
				affiche_lex.add(num(mots.get(i)));
			}
			else affiche_lex.add("Erreur lexicale");
			i++;
		}
	}	
        //Analyse sémantique
	public String semantique(String chaine){
		if(chaine.equals("Start_Program")) return "public static void main(String[] args) {";
		else if(chaine.equals("Else")) return "else";
		else if(chaine.equals("Start")) return "{";
		else if(chaine.equals("Finish")) return "}";
		else if(chaine.startsWith("//.")) return "/*ceci est un commentaire*/";
		else if(chaine.equals("End_Program")) return "}";
		else if(chaine.startsWith("ShowMess \" ") && chaine.endsWith(" \" ;;")) return "System.out.println(\"Affichage d'un message à l'ecran\");";
		else if(chaine.contains(" ")) {
			mot = chaine.split(" ");
			int i=0;
				if(mot[i].equals("Int_Number")){
					i++;
                                            if(mot[i].equals(":")) i++;
                                               if(mot[i].contains(",") && !(mot[i].startsWith(",")) && !(mot[i].endsWith(","))) {
							var=mot[i].split(",");i++;
                                                        commentaire=var[0];
                                                        for(int l=1;l<(var.length);l++){commentaire=commentaire.concat(","+var[l]);}
							if(mot[i].equals(";;")) return "int "+commentaire+";";var=null;}
                                                  else if( !(mot[i].startsWith(",")) && !(mot[i].endsWith(","))){
                                                            i++;
                                                            if(mot[i].equals(";;")) return "int "+mot[i-1]+";";var=null;
                                                                }
                                }
				else if(mot[i].equals("Give")){
					i++;
					if(ident(mot[i]) != null){
						i++;
                                            if(mot[i].equals(":"))i++;
						if(num(mot[i]) == "Nombre entier") {
							i++;
							if(mot[i].equals(";;")) return mot[i-3]+"="+mot[i-1]+";";
						}
						else if(num(mot[i]) == "Nombre reel"){
							i++;
							if(mot[i].equals(";;")) return mot[i-3]+"="+mot[i-1]+";";
						}
				}
				}
                                else if(mot[i].equals("Real_Number")){
					i++;
                                            if(mot[i].equals(":")) i++;
                                               if(mot[i].contains(",") && !(mot[i].startsWith(",")) && !(mot[i].endsWith(","))) {
							var=mot[i].split(",");i++;
                                                        commentaire=var[0];
                                                        for(int l=1;l<(var.length);l++){commentaire=commentaire.concat(","+var[l]);}
							if(mot[i].equals(";;")) return "Float "+commentaire+";";var=null;}
                                                  else if( !(mot[i].startsWith(",")) && !(mot[i].endsWith(","))){
                                                            i++;
                                                            if(mot[i].equals(";;")) return "Float "+mot[i-1]+";";var=null;
                                                                }
                                }							
				else if(mot[i].equals("If")){
					i++;
					if(mot[i].equals("--")){
						i++;
					if(ident(mot[i]) != null){
						i++;
						if(mot[i].equals("<") || mot[i].equals(">") || mot[i].equals("==")){
						i++;
						if(ident(mot[i]) != null){
							i++;
						if(mot[i].equals("--")) return "if"+"("+mot[i-3]+mot[i-2]+mot[i-1]+")"; 
							}}}}
				}												
				else if(mot[i].equals("Affect")){
					i++;
					if(ident(mot[i]) != null){
						i++;
					if(mot[i].equals("to")){
						i++;
						if(ident(mot[i]) != null) {
							i++;
							if(mot[i].equals(";;")) return  mot[i-1]+"="+mot[i-3]+";";
						}
					}
				}
				}
				else if(mot[i].equals("ShowVal")){
					i++;
                                          if(mot[i].equals(":"))i++;
						if(ident(mot[i]) != null)i++;
                                                   if(mot[i].equals("\"")) i++;
							if(mot[i].equals(";;")) return "System.out.println("+mot[i-1]+");";
						}
                                else if(mot[i].equals("ShowMess")){
                                    commentaire="";
					i++;
                                          if(mot[i].equals(":"))i++;
                                                if(mot[i].equals("\""))i++;  
						if(ident(mot[i]) != null)i++;comm.add(mot[i-1]+" ");
                                                  while(ident(mot[i]) != null){
                                                  comm.add(mot[i]+" ");
                                                  i++;
                                                  }
                                                   if(mot[i].equals("\"")) i++;
							if(mot[i].equals(";;")){ 
                                                        for(String s : comm) { commentaire=commentaire.concat(s);}
                                                            return "System.out.println("+commentaire+");";
                                                        }}
								}
		return "erreur sémantique";
	}
}