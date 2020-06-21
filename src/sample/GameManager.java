package sample;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
public class GameManager {

    public Player player;
    public Label affichageLance;
    public int nbrPlayer = 2;
    public List<Player> listPlayer = new ArrayList<Player>();
    public int actifPlayer;
    public boolean gameStart = false;
    public Button gameButton;
    public Button Regles;
    public Button Dés;
    public boolean AI;

    GameManager(boolean _AI)
    {
        AI = _AI;
        System.out.println("La partie est lancée ! Jouez");
        CreationPlayer();
        actifPlayer=0;
        gameButton = new Button("Recommencer");
        gameButton.setTranslateX(315);
        gameButton.setTranslateY(710);
        for(int i = 0; i<nbrPlayer;i++)
        {
            listPlayer.get(i).ChangeRenduPos(39,661);
            listPlayer.get(i).directionMove = 1;
            listPlayer.get(i).etatPlayer = new int[]{0, 0, 0, 0, 0};
            listPlayer.get(i).Examen = new boolean[]{false, false};

        }
        actifPlayer = 1;
        gameStart = false;
        gameStart = true;
        gameButton.setOnAction(actionEvent -> {

            for(int i = 0; i<nbrPlayer;i++)
            {
                listPlayer.get(i).ChangeRenduPos(39,661);
                listPlayer.get(i).directionMove = 1;
                listPlayer.get(i).etatPlayer = new int[]{0, 0, 0, 0, 0};
                listPlayer.get(i).Examen = new boolean[]{false, false};
                listPlayer.get(i).button.setVisible(true);
            }
            actifPlayer = 1;
            gameStart = false;
            gameStart = true;
        });


        affichageLance = new Label("0");
        affichageLance.setTranslateX(170);
        affichageLance.setTranslateY(710);

        Regles = new Button("?");
        Regles.setTranslateX(120);
        Regles.setTranslateY(710);
        Regles.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Label regles = new Label("  Le jeu de l'Oie est un jeu où le premier à arriver à la dernière case gagne.\n" +
                        "Il se joue à plusieurs ou contre une IA. Chaque joueur possède joue en tour par tour et possède un dé à lancer afin d'avancer sur le plateau\n" +
                        "Si vous jouez contre l'IA, votre pion est en BLEU et celui de votre adversaire en MARRON.\n" +
                        "\n   Dans notre jeu, le plateau est constitué de 81 cases avec des effets spécigiques pour celles précisées ci-dessous :\n" +
                        "\n" +
                        "-> Intégration : Vous pouvez relancer le dé.\n" +
                        "-> Gueule de bois : Passez votre prochain tour.\n" +
                        "-> Notes de Bidault : Lancez un dé (avec le boutton qui apparait). Si vous faites moins de 3, vous retournez à la case des losers.\n" +
                        "-> Cours de Migniot | Marcuard : Votre prochain lancé de dé est divisé par 2.\n" +
                        "-> WEI | Voyage à l'étranger : Lancez le dé. Si vous avez 4 ou moins, avancez. Sinon reculez de 2 case si vous avez 5 ou de 3  si vous avez 6.\n" +
                        "-> Respo Geipi : Votre prochain lancé de dé est multiplié par 2.\n" +
                        "-> Partiel GEIPI | Soutenance de Stage : Passage OBLIGATOIRE. Lancez un dé. Vous devez avoir au moins 4 pour continuer, sinon, RDV à la case redoublement précédente.\n" +
                        "-> Conseils de Brachais : Diminue de 1 point les points requis pour Partiel GEIPI\n" +
                        "-> Gala : Lance le dé. Si c'est pair avance de ton chiffre, sinon, recule.\n" +
                        "-> Semaine de campagne : Lancez le dé. Si vous faites 1 ou 6, vous passez votre tour, sinon avancez du double de votre dé.\n" +
                        "-> Projet 3A : Lancez un dé. Si vous faites 6, avancez jusqu'à la case n°70. Si vous faites 1 ou 2, reculez jusqu'à la case 50, sinon rien ne se passe.\n" +
                        "-> Stage à l'étranger | Stage : Lancez un dé. Si vous faites + de 5, avancez du montant de votre dé. Sinon, avancez d'une seule case.\n" +
                        "-> Organisation du Gala : Lancez le dé. Si vous faites 3 ou +, votre prochain lancé est doublé.\n" +
                        "-> Remise de diplôme : Félicitations, vous avez gagné !\n" +
                        "\n" +
                        "   Vous savez tout, maintenant, que le meilleur (et le plus chanceux) gagne !\n" +
                        "\nby Rémi LEFAIVRE & Colin BRACQ - ESIREM - GEIPI2 - IT");
                Pane Fenetre = new Pane(regles);
                Scene aide = new Scene(Fenetre, 800, 400);
                Stage stage = new Stage();
                stage.setTitle("Aide");
                stage.setScene(aide);
                stage.show();
            }
        });

        Dés = new Button("(Dé des events)");
        Dés.setTranslateX(490);
        Dés.setTranslateY(710);
        Dés.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            }
        });

    }

    public void PassageTour()
    {
        actifPlayer++;
        if(actifPlayer>nbrPlayer)
        {
            actifPlayer = 1;
        }

    }

    private void CreationPlayer()
    {
        String numero;
        int[] buttonXPos = new int[5];
        int[] buttonYPos = new int[5];

        switch (nbrPlayer)
        {
            case 2:
                buttonXPos[1]=8;
                buttonYPos[1]=710;
                buttonXPos[2]=630;
                buttonYPos[2]=710;
                break;
            case 3 :
                // ...
                break;
            case 4 :
                // ...
                break;

        }

        for (int i = 1;i<= nbrPlayer; i++)
        {
            numero = Integer.toString(i);
            if(AI && i==2)
            {
                player = new Player(this,true, String.format("Joueur %s", numero),1,i,39,661,buttonXPos[i],buttonYPos[i]);
            }
            else
                player = new Player(this,false, String.format("Joueur %s", numero),1,i,39,661,buttonXPos[i],buttonYPos[i]);


            listPlayer.add(player);
        }
    }

    public int getDiceValue(Player player){
        int rand;
        if(player.etatPlayer[1] == 1) {
            rand = (int) (Math.random() * 3 + 1);
            player.etatPlayer[1] = 0;
        }
        else if(player.etatPlayer[2] == 1) {
            rand = (int) (Math.random()*11+2);
            player.etatPlayer[2] = 0;
        }
        else if(player.etatPlayer[4] == 1) {
            rand = 1;
            player.etatPlayer[4] = 0;
        }
        else rand = (int) (Math.random() * 6 + 1);
        return rand;
    }

    public void eventCase(Player player)
    {
        int x = player.playerXPos;
        int y = player.playerYPos;

        if (x == 351 && y == 661) {
            System.out.println("Case n°5 : Intégration !");
            System.out.println("Vous pouvez rejouer !");
            System.out.println("---------------------------------------------------------------------------");
            actifPlayer--;
        }       // Inté 5
        if (x == 429 && y == 661) {
            System.out.println("Case n°6 : Gueule de bois !");
            System.out.println("Vous avez trop bu, passez un tour !");
            System.out.println("---------------------------------------------------------------------------");
            player.etatPlayer[0] = 1;
        }       // Gueule de bois 6
        if (x == 663 && y == 661) {
            System.out.println("Case n°9 : Notes de Bidault !");
            System.out.println("Un dé est lancé, si vous avez fait 1 ou 2, direction la case des losers !\n");
            int de = (int) (Math.random() * 6 + 1);
            Dés.setText(String.valueOf(de));
            if (de < 3) {
                System.out.println(de + ", dommage.. RDV en case 3");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos -= 78 * 6;
                player.playerPosition -= 6;
                player.translatePlayer();
            } else {
                System.out.println(de + ", bien joué, c'était pas si simple !");
                System.out.println("---------------------------------------------------------------------------");
            }
        }       // Bidault 9
        if (x == 507 && y == 583) {
            System.out.println("Case n°12 : Cours de Migniot !");
            System.out.println("A votre prochain tour, votre lancé de dé sera divisé par 2");
            System.out.println("---------------------------------------------------------------------------");
            player.etatPlayer[1] = 1;
        }       // Migniot 12
        if (x == 351 && y == 583) {
            System.out.println("Case n°14 : WEI !");
            System.out.println("Les voyages BDE sont mémorables après à vous de décider comment..");
            System.out.println("Le dé est lancé, si vous avez fait 4 ou moins, avancez de ce chiffre sinon reculez !");
            System.out.println("---------------------------------------------------------------------------");
            int de = (int) (Math.random() * 6 + 1);
            Dés.setText(String.valueOf(de));
            if (de < 5) {
                System.out.println("Bien joué, vous avancez de " + de + " cases");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos-= de * 78;
                player.playerPosition += de;
                player.translatePlayer();
            }
            if (de == 5) {
                System.out.println("Dommage 5 , vous reculez de 2 cases");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos += 2 * 78;
                player.playerPosition -= 2;
                player.translatePlayer();
            }
            if (de == 6) {
                System.out.println("Dommage 6 , vous reculez de 3 cases");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos += 3 * 78;
                player.playerPosition -= 3;
                player.translatePlayer();
            }
         }       // WEI 14
        if (x == 273 && y == 583) {
            System.out.println("Case n°15 : Gueule de bois !");
            System.out.println("Vous avez trop bu, passez un tour !");
            System.out.println("---------------------------------------------------------------------------");
            player.etatPlayer[0] = 1;
        }       // Gueule de bois 15
        if (x == 39 && y == 583) {
            System.out.println("Case n°18 : Cours de Marcuard !");
            System.out.println("A votre prochain tour, votre lancé de dé sera divisé par 2");
            System.out.println("---------------------------------------------------------------------------");
            player.etatPlayer[1] = 1;
        }        // Marcuard 18
        if (x == 117 && y == 505) {
            System.out.println("Case n°20 : Responsable GEIPI !");
            System.out.println("Vous êtes le Responsable GEIPI");
            System.out.println("A votre prochain tour, votre lancé de dé sera multiplié par 2");
            System.out.println("---------------------------------------------------------------------------");
            player.etatPlayer[2] = 1;
        }       // Respo Geipi 20
        if (x == 663 && y == 505) {
            System.out.println("Case n°27 : Conseils de Brachais !");
            System.out.println("Désormais, vous aurez besoin de faire minimum 3 pour avoir votre année, bien joué !");
            System.out.println("---------------------------------------------------------------------------");
            player.etatPlayer[3] = 1;
        }       // Conseils Brachais 27
        if (x == 663 && y == 427) {
            System.out.println("Case n°28 : Gueule de bois !");
            System.out.println("Vous avez trop bu, passez un tour !");
            System.out.println("---------------------------------------------------------------------------");
            player.etatPlayer[0] = 1;
        }       // Gueule de bois 28
        if (x == 507 && y == 427){
            System.out.println("Case n°30 : Gala !");
            System.out.println("Le Gala c'est la remise des diplômes, un concert et surtout beaucoup de champagne !");
            System.out.println("Le dé est lancé, si vous faitez un chiffre pair, avancez de ce chiffre sinon reculez !");
            System.out.println("---------------------------------------------------------------------------");
            int de = (int) (Math.random() * 6 + 1);
            Dés.setText(String.valueOf(de));
            if (de % 2 == 0 ) {
                System.out.println("Bien joué, vous avancez de " + de + " cases");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos -= de * 78;
                player.playerPosition += de;
            }
            else{
                System.out.println("Dommage, vous reculez de "+ de +" cases");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos += 2 * 78;
                if(de > 2){
                    player.playerYPos += 78;
                    player.playerXPos -= 78*(de-3);
                    player.directionMove--;
                }
                player.playerPosition -= de;
            }
            player.translatePlayer();
        }        // Gala 30
        if (x == 273 && y == 427) {
            System.out.println("Case n°33 : Voyage à l'étranger !");
            System.out.println("Le voyage à l'étranger c'est l'occasion de faire rayonner l'ESIREM à l'international");
            System.out.println("Le dé est lancé, si vous avez fait 4 ou moins, avancez de ce chiffre sinon reculez !");
            System.out.println("---------------------------------------------------------------------------");
            int de = (int) (Math.random() * 6 + 1);
            Dés.setText(String.valueOf(de));
            if (de < 4) {
                System.out.println("Bien joué, vous avancez de " + de + " cases");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos -= de * 78;
                player.playerPosition += de;
                player.translatePlayer();
            }
            if(de==4){
                System.out.println("Bien joué, vous avancez de " + de + " cases");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos -= (de-1) * 78;
                player.playerPosition += de-1;
                player.translatePlayer();
            }
            if (de == 5) {
                System.out.println("Dommage 5, vous reculez de 2 cases");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos += 2 * 78;
                player.playerPosition -= 2;
                player.translatePlayer();
            }
            if (de == 6) {
                System.out.println("Dommage 6, vous reculez de 3 cases");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos += 3 * 78;
                player.playerPosition -= 3;
                player.translatePlayer();
            }
        }       // Voyage étranger 33
        if (x == 39 && y == 427) {
            int points = 4-player.etatPlayer[3];
            System.out.println("Case n°36 : Partiel GEIPI !");
            System.out.println("Ca y est, le moment est enfin venu de faire vos preuves");
            System.out.println("Un dé est lancé, si vous avez fait " + points +" ou plus, vous êtes en 3A !\n");
            System.out.println("Sinon, c'est le redoublement.. RDV en case 24");
            int de = (int) (Math.random() * 6 + 1);
            Dés.setText(String.valueOf(de));
            if (de < points) {
                System.out.println(de + ", dommage.. c'est le redoublement (aïe)");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos = 429;
                player.playerYPos = 505;
                player.directionMove--;
                player.playerPosition= 24;
                player.translatePlayer();
            } else {
                System.out.println(de + ", bienvenu(e) en 3A");
                System.out.println("---------------------------------------------------------------------------");
                player.Examen[0] = true;
                player.etatPlayer[3]=0;
                player.playerPosition = 36;
            }
        }        // Partiel Geipi 36
        if (x == 117 && y == 349) {
            System.out.println("Case n°38 : Intégration !");
            System.out.println("Vous pouvez rejouer !");
            System.out.println("---------------------------------------------------------------------------");
            actifPlayer --;
        }       // Inté 38
        if (x == 429 && y == 349) {
            System.out.println("Case n°42 : WEI !");
            System.out.println("Les voyages BDE sont mémorables après à vous de décider comment..");
            System.out.println("Le dé est lancé, si vous avez fait 4 ou moins, avancez de ce chiffre sinon reculez !");
            System.out.println("---------------------------------------------------------------------------");
            int de = (int) (Math.random() * 6 + 1);
            Dés.setText(String.valueOf(de));
            if (de < 4) {
                System.out.println("Bien joué, vous avancez de " + de + " cases");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos += de * 78;
                player.playerPosition += de;
                player.translatePlayer();
            }
            if (de == 4) {
                System.out.println("Bien joué, vous avancez de " + de + " cases");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos += (de-1) * 78;
                player.playerYPos -= 78;
                player.directionMove++;
                player.playerPosition+= de;
                player.translatePlayer();
            }
            if (de == 5) {
                System.out.println("Dommage 5, vous reculez de 2 cases");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos -= 2 * 78;
                player.playerPosition -= 2;
                player.translatePlayer();
            }
            if (de == 6) {
                System.out.println("Dommage 6, vous reculez de 3 cases");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos -= 3 * 78;
                player.playerPosition -= 3;
                player.translatePlayer();
            }
        }       // WEI 42
        if (x == 507 && y == 349) {
            System.out.println("Case n°43 : Gueule de bois !");
            System.out.println("Vous avez trop bu, passez un tour !");
            System.out.println("---------------------------------------------------------------------------");
            player.etatPlayer[0] = 1;
        }       // Gueule de bois 43
        if (x == 663 && y == 271) {
            System.out.println("Case n°46 : Voyage à l'étranger !");
            System.out.println("Le voyage à l'étranger c'est l'occasion de faire rayonner l'ESIREM à l'international");
            System.out.println("Le dé est lancé, si vous avez fait 4 ou moins, avancez de ce chiffre sinon reculez !");
            System.out.println("---------------------------------------------------------------------------");
            int de = (int) (Math.random() * 6 + 1);
            Dés.setText(String.valueOf(de));
            if (de < 5) {
                System.out.println("Bien joué, vous avancez de " + de + " cases");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos -= de * 78;
                player.playerPosition += de;
                player.translatePlayer();
            }
            if (de == 5) {
                System.out.println("Dommage 5 , vous reculez de 2 cases");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos -=  78;
                player.playerYPos +=  78;
                player.directionMove--;
                player.playerPosition -= 2;
                player.translatePlayer();
            }
            if (de == 6) {
                System.out.println("Dommage 6, vous reculez de 3 cases");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos -= 2 * 78;
                player.playerYPos +=  78;
                player.directionMove--;
                player.playerPosition-= 3;
                player.translatePlayer();
            }
        }       // Voyage étranger 46
        if (x == 195 && y == 271){
            System.out.println("Case n°52 : Semaine de Campagne !");
            System.out.println("La fin du mandat arrive.. Un nouveau BDE doit être élu");
            System.out.println("Le dé est lancé, si vous avez fait 1 ou 6, vous passez votre tour. Sinon avancez du double de votre dé !");
            System.out.println("---------------------------------------------------------------------------");
            int de = (int) (Math.random() * 6 + 1);
            Dés.setText(String.valueOf(de));
            if(de == 1 || de ==6){
                System.out.println(de + ", vraiment pas de chance.. Vous passez votre tour");
                System.out.println("---------------------------------------------------------------------------");
                player.etatPlayer[0] = 1;
            }
            else{
                int avance = 2*de;
                System.out.println("Wouah " + de + " ! Vous avancez donc de " + avance + " cases");
                player.playerXPos -= 2 * 78;
                if(avance>2 && avance <11){
                    player.playerYPos -= 78;
                    player.playerXPos += 78*(avance-3);
                    player.directionMove++;
                    player.playerPosition += avance;
                    player.translatePlayer();
                }
            }
        }        // Semaine de campagne 52
        if (x == 117 && y == 271) {
            System.out.println("Case n°53 : Gueule de bois !");
            System.out.println("Vous avez trop bu, passez un tour !");
            System.out.println("---------------------------------------------------------------------------");
            player.etatPlayer[0] = 1;
        }       // Gueule de bois 53
        if (x == 117 && y == 193){
            System.out.println("Case n°56 : Projet 3A !");
            System.out.println("Beaucoup s'y sont cassé les dents, à vous de survivre !");
            System.out.println("Le dé est lancé, si vous avez fait 6, avancez jusqu'à la case 70.");
            System.out.println("Si vous avez fait 1 ou 2, reculez jusqu'à la case 50. Sinon rien ne se passe !");
            System.out.println("---------------------------------------------------------------------------");
            int de = (int) (Math.random() * 6 + 1);
            Dés.setText(String.valueOf(de));
            if(de == 6){
                System.out.println(de + ", la chaaance.. Bien joué chef");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos = 195;
                player.playerYPos = 115;
                player.directionMove++;
                player.playerPosition = 70;
                player.translatePlayer();
            }
            if(de == 1 || de == 2){
                System.out.println(de + ", oopsi, ça fait mal ça.. Allez.. En case 50 !");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos = 351;
                player.playerYPos = 271;
                player.directionMove--;
                player.playerPosition = 50;
                player.translatePlayer();
            }
            else{
                System.out.println(de + ", pas mal tu fais rien du coup !");
            }
        }        // Projet 3A 56
        if (x == 429 && y == 193){
            System.out.println("Case n°60 : Gala !");
            System.out.println("Le Gala c'est la remise des diplômes, un concert et surtout beaucoup de champagne !");
            System.out.println("Le dé est lancé, si vous faites un chiffre pair, avancez de ce chiffre sinon reculez !");
            System.out.println("---------------------------------------------------------------------------");
            int de = (int) (Math.random() * 6 + 1);
            Dés.setText(String.valueOf(de));
            if (de % 2 == 0 ) {
                System.out.println("Bien joué, vous avancez de " + de + " cases");
                System.out.println("---------------------------------------------------------------------------");
                if(de<3){
                    player.playerXPos += de * 78;
                    player.playerPosition += de;
                }
                if(de>3){
                    player.playerXPos = 663 - de*(de-4);
                    player.playerYPos -= 78;
                    player.playerPosition += de;
                    player.directionMove++;
                }
            }
            else{
                System.out.println("Dommage, vous reculez de "+ de +" cases");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos -= de * 78;
                player.playerPosition -= de;
            }
            player.translatePlayer();
        }        // Gala 60
        if (x == 507 && y == 193) {
            System.out.println("Case n°561 : Gueule de bois !");
            System.out.println("Vous avez trop bu, passez un tour !");
            System.out.println("---------------------------------------------------------------------------");
            player.etatPlayer[0] = 1;
        }       // Gueule de bois 61
        if (x == 507 && y == 115) {
            System.out.println("Case n°66 : Stage à l'étranger !");
            System.out.println("Vous faites vos premiers pas dans le monde professionnel, quelle aubène !");
            System.out.println("Le dé est lancé, si vous faites 6, avancez de ce chiffre !");
            System.out.println("---------------------------------------------------------------------------");
            int de = (int) (Math.random() * 6 + 1);
            Dés.setText(String.valueOf(de));
            if (de == 6) {
                System.out.println(de + ".. Juste trop fort");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos -= de * 78;
                player.playerPosition += de;
                player.translatePlayer();
                player.etatPlayer[4]=0;
            }
            else{
                System.out.println(de + ".. Dommage, tu auras éssayé !");
                System.out.println("---------------------------------------------------------------------------");
                player.etatPlayer[4] = 0;
            }
        }       // Stage à l'étranger 66
        if (x == 585 && y == 115) {
            System.out.println("Case n°65 : Stage à l'étranger !");
            System.out.println("Vous faites vos premiers pas dans le monde professionnel, quelle aubène !");
            System.out.println("Le dé est lancé, si vous faites 6, avancez de ce chiffre sinon vous avancerez de 1 au prochain tour !");
            System.out.println("---------------------------------------------------------------------------");
            int de = (int) (Math.random() * 6 + 1);
            Dés.setText(String.valueOf(de));
            if (de == 6) {
                System.out.println(de + ".. Juste trop fort");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos -= de * 78;
                player.playerPosition += de;
                player.translatePlayer();
                player.etatPlayer[4] = 0;
            }
            else{
                System.out.println(de + ".. Dommage, réessaye au prochain tour !");
                System.out.println("---------------------------------------------------------------------------");
                player.etatPlayer[4] = 1;
            }
        }       // Stage à l'étranger 65
        if (x == 663 && y == 115) {
            System.out.println("Case n°64 : Stage à l'étranger !");
            System.out.println("Vous faites vos premiers pas dans le monde professionnel, quelle aubène !");
            System.out.println("Le dé est lancé, si vous faites 6, avancez de ce chiffre sinon vous avancerez de 1 au prochain tour !");
            System.out.println("---------------------------------------------------------------------------");
            int de = (int) (Math.random() * 6 + 1);
            Dés.setText(String.valueOf(de));
            if (de == 6) {
                System.out.println(de + ".. Juste trop fort");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos -= de * 78;
                player.playerPosition+= de;
                player.translatePlayer();
                player.etatPlayer[4] = 0;
            }
            else{
                System.out.println(de + ".. Dommage, réessaye au prochain tour !");
                System.out.println("---------------------------------------------------------------------------");
                player.etatPlayer[4] = 1;
            }
        }       // Stage à l'étranger 64
        if (x == 663 && y == 193) {
            System.out.println("Case n°63 : Stage à l'étranger !");
            System.out.println("Vous faites vos premiers pas dans le monde professionnel, quelle aubène !");
            System.out.println("Le dé est lancé, si vous faites 6, avancez de ce chiffre sinon vous avancerez de 1 au prochain tour !");
            System.out.println("---------------------------------------------------------------------------");
            int de = (int) (Math.random() * 6 + 1);
            Dés.setText(String.valueOf(de));
            if (de == 6) {
                System.out.println(de + ".. Juste trop fort");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos -= (de-1) * 78;
                player.playerYPos -= de * 78;
                player.playerPosition += de;
                player.translatePlayer();
                player.etatPlayer[4] = 0;
            }
            else{
                System.out.println(de + ".. Dommage, réessaye au prochain tour !");
                System.out.println("---------------------------------------------------------------------------");
                player.etatPlayer[4] = 1;
            }
        }       // Stage à l'étranger 63
        if (x == 117 && y == 115) {
            System.out.println("Case n°71 : Organisation du Gala !");
            System.out.println("Vous êtes l'organisateur du spectacle où le chammpagne coule à flot !");
            System.out.println("Le dé est lancé, si vous faites 3 ou plus, votre prochain lancé est doublé !");
            System.out.println("---------------------------------------------------------------------------");
            int de = (int) (Math.random() * 6 + 1);
            Dés.setText(String.valueOf(de));
            if (de > 2) {
                System.out.println("CHAMPIOOOOOON ! Tu iras 2 fois plus loin au prochain tour !");
                System.out.println("---------------------------------------------------------------------------");
                player.etatPlayer[2] = 1;
            }
            else{
                System.out.println(de + ".. T'avais des responsabilités t'as pas assumé.. Tant pis pour toi !");
            }
        }       // Organisation Gala 71
        if (x == 273 && y == 37) {
            System.out.println("Case n°76 : Stage !");
            System.out.println("Vous faites vos premiers pas dans le monde professionnel, quelle aubène !");
            System.out.println("Le dé est lancé, si vous faites 6, avancez de ce chiffre !");
            System.out.println("---------------------------------------------------------------------------");
            int de = (int) (Math.random() * 6 + 1);
            Dés.setText(String.valueOf(de));
            if (de == 6) {
                System.out.println(de + ".. Juste trop fort");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos += (de-4) * 78;
                player.playerPosition += de-4;
                player.translatePlayer();
                player.etatPlayer[4]=0;
            }
            else{
                System.out.println(de + ".. Dommage, tu auras éssayé !");
                System.out.println("---------------------------------------------------------------------------");
                player.etatPlayer[4] = 0;
            }
        }        // Stage 76
        if (x == 195 && y == 37) {
            System.out.println("Case n°75 : Stage !");
            System.out.println("Vous faites vos premiers pas dans le monde professionnel, quelle aubène !");
            System.out.println("Le dé est lancé, si vous faites 6, avancez de ce chiffre sinon vous avancerez de 1 au prochain tour !");
            System.out.println("---------------------------------------------------------------------------");
            int de = (int) (Math.random() * 6 + 1);
            Dés.setText(String.valueOf(de));
            if (de == 6) {
                System.out.println(de + ".. Juste trop fort");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos += (de-3) * 78;
                player.playerPosition+= de-3;
                player.translatePlayer();
                player.etatPlayer[4] = 0;
            }
            else{
                System.out.println(de + ".. Dommage, réessaye au prochain tour !");
                System.out.println("---------------------------------------------------------------------------");
                player.etatPlayer[4] = 1;
            }
        }        // Stage 75
        if (x == 117 && y == 37) {
            System.out.println("Case n°74 : Stage !");
            System.out.println("Vous faites vos premiers pas dans le monde professionnel, quelle aubène !");
            System.out.println("Le dé est lancé, si vous faites 6, avancez de ce chiffre sinon vous avancerez de 1 au prochain tour !");
            System.out.println("---------------------------------------------------------------------------");
            int de = (int) (Math.random() * 6 + 1);
            Dés.setText(String.valueOf(de));
            if (de == 6) {
                System.out.println(de + ".. Juste trop fort");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos += (de-2) * 78;
                player.playerPosition += de-2;
                player.translatePlayer();
                player.etatPlayer[4] = 0;
            }
            else{
                System.out.println(de + ".. Dommage, réessaye au prochain tour !");
                System.out.println("---------------------------------------------------------------------------");
                player.etatPlayer[4] = 1;
            }
        }        // Stage 74
        if (x == 39 && y == 37)  {
            System.out.println("Case n°73 : Stage !");
            System.out.println("Vous faites vos premiers pas dans le monde professionnel, quelle aubène !");
            System.out.println("Le dé est lancé, si vous faites 6, avancez de ce chiffre sinon vous avancerez de 1 au prochain tour !");
            System.out.println("---------------------------------------------------------------------------");
            int de = (int) (Math.random() * 6 + 1);
            Dés.setText(String.valueOf(de));
            if (de == 6) {
                System.out.println(de + ".. Juste trop fort");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos += (de-1) * 78;
                player.playerPosition += de-1;
                player.translatePlayer();
                player.etatPlayer[4] = 0;
            }
            else{
                System.out.println(de + ".. Dommage, réessaye au prochain tour !");
                System.out.println("---------------------------------------------------------------------------");
                player.etatPlayer[4] = 1;
            }
        }        // Stage 73
        if (x == 429 && y == 37) {
            System.out.println("Case n°78 : Soutenance de Stage !");
            System.out.println("Vous y êtes presque, il y a plus qu'à montrer que vous avez bien travaillé :)");
            System.out.println("Un dé est lancé, si vous avez fait 4 ou plus vous obtiendez votre diplôme !\n");
            System.out.println("Sinon, c'est le redoublement.. RDV en case 69");
            int de = (int) (Math.random() * 6 + 1);
            Dés.setText(String.valueOf(de));
            if (de < 4) {
                System.out.println(de + ", dommage.. c'est le redoublement (aïe)");
                System.out.println("---------------------------------------------------------------------------");
                player.playerXPos = 273;
                player.playerYPos = 115;
                player.directionMove--;
                player.playerPosition = 69;
                player.translatePlayer();
            } else {
                System.out.println(de + ", Félicitations ! Allez jusqu'au bout maintenant.");
                System.out.println("---------------------------------------------------------------------------");
                player.Examen[1] = true;
                player.playerPosition = 78;
            }
        }        // Soutenance Stage 78
    }
}
