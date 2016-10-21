# CryptoRSA
0- Compilation de tous les fichiers:

$javac *.java

1- Génération de la paire de clé : GenerateKey.java

Clés publiques enregistrées dans [nom].pub sous le format Clés privées enregistrées dans [nom].priv sour le format Utiliser la commande suivante pour générer des clés de 512 bits et enregistrer les clés dans jol-512.pub et jol-512.priv

$java GenerateKey 512 jol

2- Programme de chiffrement : Chiffrer.java

Pour chiffrer le contenu du fichier message.txt en mode ECB et avec les clés dans jol-512.pub, utiliser la commande suivante

$cat message.txt | java Chiffrer jol-512

3- Programme de déchiffrement : DeChiffrer.java

$cat msg-for-jol-512.chiffre | java DeChiffrer jol-512

4- Programme de signature : Signature.java

Signe le contenu de message.txt avec les clés privées dans jol-512.priv et ecrit la signature dans signature-jol-512.chiffre

$cat message.txt | java Signature jol-512

5- Vérification de signature :  Programme : VerfierSignature.java

$cat message.txt | java verifie signature-jol-512.chiffre
