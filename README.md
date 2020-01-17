# compila
Projet Compilation compila.

Langage de programmation utilisé : Java.

Ce programme fait l'analyse lexicale,syntaxique et sémantique des programmes écrit en langage compila,dans chaque type d'analyse le programme compare les caracteres,mots,lignes de codes et affiche un résultat s'il reconnait les expressions.

//Exemple code source compila

**************************************
Start_Program
Int_Number : i,a,b,c ;;
Real_Number : J,K ;;
If -- i<K -- Give J : 44 ;;
Else
Start
Give i : 25 ;;
Int_Number : Mp ;;
Real_Number : Aft_78 ;;
Affect i to Aft_78 ;;
Finish
//. Commentaire compila
ShowMess : " message a afficher " ;;
ShowVal : k ;;
Affect i to J ;;
Give K : 78,77 ;;
End_Program
**************************************

Résultat analyse lexicale:
**************************************
Start_Program : Mot reserve debut du programme
Int_Number :  Mot reserve debut declaration d'un entier
: : Caractere reserve
i,a,b,c : Erreur lexicale
;; : Mot reserve fin instruction
Real_Number :  Mot reserve debut declaration d'un Real
: : Caractere reserve
J,K : Erreur lexicale
;; : Mot reserve fin instruction
if : Identificateur
-- : Mot reserve pour une condition
i<K : Erreur lexicale
-- : Mot reserve pour une condition
Give : Mot reserve
J : identificateur
: : Caractere reserve
44 : Nombre entier
;; : Mot reserve fin instruction
Else : Mot reserve pour condition SINON
Start : Debut d'un sous programme
Give : Mot reserve
i : identificateur
: : Caractere reserve
25 : Nombre entier
;; : Mot reserve fin instruction
Int_Number :  Mot reserve debut declaration d'un entier
: : Caractere reserve
Mp : Identificateur
;; : Mot reserve fin instruction
Real_Number :  Mot reserve debut declaration d'un Real
: : Caractere reserve
Aft_78 : Identificateur
;; : Mot reserve fin instruction
Affect : Mot reserve pour affectation entre variables
i : identificateur
to : Mot reserve pour affectation
Aft_78 : Identificateur
;; : Mot reserve fin instruction
Finish : Fin d'un sous programme
//. : Mot reservé pour un commentaire
Commentaire : Identificateur
compila : Identificateur
ShowMess : Mot reservé pour afficher un message
: : Caractere reserve
" : mot reserve pour guillemets
message : Identificateur
a : identificateur
afficher : Identificateur
" : mot reserve pour guillemets
;; : Mot reserve fin instruction
ShowVal : Mot reservé pour afficher une valeur
: : Caractere reserve
k : identificateur
;; : Mot reserve fin instruction
Affect : Mot reserve pour affectation entre variables
i : identificateur
to : Mot reserve pour affectation
J : identificateur
;; : Mot reserve fin instruction
Give : Mot reserve
K : identificateur
: : Caractere reserve
78,77 : Nombre reel
;; : Mot reserve fin instruction
End_Program : Mot reserve Fin du programme
**************************************

Résultat analyse Syntaxique:
**************************************
Start_Program : Début du programme
Int_Number : i,a,b,c ;; : Déclaration de 4 variables entieres
Real_Number : J,K ;; : Déclaration de 2 variables reelles
if -- i<K -- Give J : 44 ;; : erreur de syntaxe
Else : SINON
Start : Début d'un bloc
Give i : 25 ;; : affectation dune valeur :
Int_Number : Mp ;; : Déclaration d'une variable entiere
Real_Number : Aft_78 ;; : Déclaration d'une variable reel
Affect i to Aft_78 ;; : affectation de i a Aft_78
Finish : Fin d'un bloc
//. Commentaire compila : un commentaire
ShowMess : " message a afficher " ;; : Affichage d'un message à l'ecran
ShowVal : k ;; : affichage de la valeur de k
Affect i to J ;; : affectation de i a J
Give K : 78,77 ;; : affectation dune valeur reel à K
End_Program : Fin du programme
**************************************

Résultat analyse Sémantique:
**************************************
Start_Program : public static void main(String[] args) {
Int_Number : i,a,b,c ;; : int i,a,b,c;
Real_Number : J,K ;; : Float J,K;
if -- i<K -- Give J : 44 ;; : erreur sémantique
Else : else
Start : {
Give i : 25 ;; : i=25;
Int_Number : Mp ;; : int Mp;
Real_Number : Aft_78 ;; : Float Aft_78;
Affect i to Aft_78 ;; : Aft_78=i;
Finish : }
//. Commentaire compila : /*ceci est un commentaire*/
ShowMess : " message a afficher " ;; : System.out.println(message a afficher );
ShowVal : k ;; : System.out.println(k);
Affect i to J ;; : J=i;
Give K : 78,77 ;; : K=78,77;
End_Program : }
**************************************
