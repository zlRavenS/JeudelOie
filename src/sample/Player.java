package sample;

import javafx.animation.TranslateTransition;
        import javafx.scene.control.Button;
        import javafx.scene.paint.Color;
        import javafx.scene.shape.Circle;
        import javafx.util.Duration;


public class Player {

    public String Id;                                       //Nom du joueur

    public int lance;                                      //Valeur du lancé de dé

    public Circle rendu = new Circle(30);               //Visuel du joueur

    public int playerPosition;                             //Case sur laquelle est le joueur
    public int playerNbr;                                  //Numéro du joueur
    public int playerXPos;                                 //Position en X du joueur
    public int playerYPos;                                 //Position en X du joueur

    public int directionMove;                              //Direction de movement en X

    public int[] etatPlayer = { 0, 0, 0, 0, 0};            //état possible du joueur : bourre, fatigue, responsable geipi, point bonus, stage
    public boolean[] Examen = {false,false};               //Si le joueur a passer l'examen : Partiel GEIPI, Soutenance Stage
    private final GameManager gameManager;

    public boolean AI = false; // Si le Player est l'AI
    public Button button; // Bouton relié au joueur

    //Constructeur du Player
    public Player(GameManager _gameManager,boolean _AI,String _Id, int _playerPosition, int _playerNbr, int _playerXPos, int _playerYPos,int buttonXPos, int buttonYPos)
    {
        gameManager=_gameManager;
        Id = _Id;
        playerPosition = _playerPosition;
        playerNbr=_playerNbr;
        playerXPos=_playerXPos;
        playerYPos=_playerYPos;
        directionMove = 1;
        AI =_AI;
        rendu.setId(_Id);
        //Choix couleur pion
        switch (_playerNbr)
        {
            case 1:
                rendu.setFill(Color.AQUA);
                break;
            case 2 :
                rendu.setFill(Color.CHOCOLATE);
                break;
        }
        rendu.getStyleClass().add("sample/style.css");
        rendu.setTranslateX(playerXPos);
        rendu.setTranslateY(playerYPos);

        // Ajout ou non du button si pas AI
        if (!_AI){
            button = new Button(_Id);
            button.setTranslateX(buttonXPos);
            button.setTranslateY(buttonYPos);
            button.setOnAction(actionEvent ->
            {
                ActionButton();
            });
         }
    }

    // Action du button du joueur
    public void ActionButton()
    {
        if (gameManager.gameStart) {
            if (playerNbr == gameManager.actifPlayer) {

                lance = gameManager.getDiceValue(this); // Lancement du dé
                gameManager.affichageLance.setText(String.valueOf(lance)); //Affichage
                playerPosition += lance; // Déplacement théorique
                movePlayer(); // Déplacement réel
                System.out.println("---------------------------------------------------------------------------");
                System.out.println(Id+" a joué\n");
                gameManager.eventCase(this); //Gestion arrivé une case

                gameManager.PassageTour(); // Passage au joueur suivant

                // Vérification si la prochain joueur est bourré, si oui passer son tour
                if (gameManager.listPlayer.get(gameManager.actifPlayer - 1).etatPlayer[0] == 1) {
                    gameManager.listPlayer.get(gameManager.actifPlayer - 1).etatPlayer[0] = 0;
                    gameManager.PassageTour();
                }
                // Vérification si la prochain joueur est une AI, si oui lancer son tour
                if (gameManager.listPlayer.get(gameManager.actifPlayer - 1).AI)
                {
                    gameManager.listPlayer.get(gameManager.actifPlayer - 1).ActionButton();
                }
            }
        }
    }

    // Déplacement du rendu en x et y
    public void ChangeRenduPos(int _playerXPos,int _playerYPos)
    {
        playerXPos = _playerXPos;
        playerYPos = _playerYPos;
        rendu.setTranslateX(playerXPos);
        rendu.setTranslateY(playerYPos);
    }

    // Création animation de déplacement
    public void translatePlayer(){
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000),rendu);
        animate.setToX(playerXPos);
        animate.setToY(playerYPos);
        animate.setAutoReverse(false);
        animate.play();
    }

    // Gestion movement du joueur
    private void movePlayer(){
        for(int i = 0; i < lance; i++){
            if(playerXPos == 585 && playerYPos == 37) {
                lance = 0;
                gameManager.affichageLance.setText("Le "+ Id +" a gagné !");
                gameManager.gameButton.setText("Rejouer");
                gameManager.actifPlayer=0;
                System.out.println("_____________________________________________________");
                System.out.println("|   Félicitations, le "+ Id +" a gagné la partie !   |");
                System.out.println("_____________________________________________________");
                for(int j = 0; j<gameManager.nbrPlayer;j++) {
                    gameManager.listPlayer.get(j).button.setVisible(false);
                }
            }
            // Mouvement en X vers la droite
            if(directionMove % 2 == 1){
                playerXPos += 78;
                translatePlayer();
            }
            // Mouvement en X vers la gauche
            if(directionMove % 2 == 0){
                playerXPos -= 78;
                translatePlayer();
            }
            // Mouvement en Y en fin de ligne à droite
            if(playerXPos > 664 && playerYPos > 37){
                playerXPos -= 78;
                playerYPos -= 78;
                directionMove++;
                translatePlayer();
            }
            // Mouvement en Y en fin de ligne à gauche
            if(playerXPos < 38 && playerYPos > 37) {
                playerXPos += 78;
                playerYPos -= 78;
                directionMove++;
                translatePlayer();
            }
            //Arrêt Partiel Geipi
            if((playerXPos == 39 && playerYPos == 427) && !Examen[0]) {
                lance = 0;
                playerPosition = 36;
            }
            // Arrêt Soutenances
            if((playerXPos == 429 && playerYPos == 37) && !Examen[1]){
                lance =0;
                playerPosition = 78;
            }
        }
    }
}
