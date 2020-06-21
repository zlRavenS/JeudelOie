package sample;

import javafx.animation.TranslateTransition;
        import javafx.scene.control.Button;
        import javafx.scene.paint.Color;
        import javafx.scene.shape.Circle;
        import javafx.util.Duration;


public class Player {

    public String Id;

    public int lance;

    public Circle rendu = new Circle(30);
    public int playerPosition;
    public int playerNbr;
    public int playerXPos;
    public int playerYPos;
    public int directionMove;
    public int[] etatPlayer = { 0, 0, 0, 0, 0}; // bourre, fatigue, responsable geipi, point bonus, stage
    public boolean[] Examen = {false,false}; // Partiel GEIPI, Soutenance Stage
    private final GameManager gameManager;

    public boolean AI = false;
    public Button button;

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

    public void ActionButton()
    {
        if (gameManager.gameStart) {
            if (playerNbr == gameManager.actifPlayer) {

                lance = gameManager.getDiceValue(this);
                gameManager.affichageLance.setText(String.valueOf(lance));
                playerPosition += lance;
                movePlayer();
                System.out.println("---------------------------------------------------------------------------");
                System.out.println(Id+" a joué\n");
                gameManager.eventCase(this);

                gameManager.PassageTour();

                if (gameManager.listPlayer.get(gameManager.actifPlayer - 1).etatPlayer[0] == 1) {
                    gameManager.listPlayer.get(gameManager.actifPlayer - 1).etatPlayer[0] = 0;
                    gameManager.PassageTour();
                }
                if (gameManager.listPlayer.get(gameManager.actifPlayer - 1).AI)
                {
                    gameManager.listPlayer.get(gameManager.actifPlayer - 1).ActionButton();
                }
            }
        }
    }

    public void ChangeRenduPos(int _playerXPos,int _playerYPos)
    {
        playerXPos = _playerXPos;
        playerYPos = _playerYPos;
        rendu.setTranslateX(playerXPos);
        rendu.setTranslateY(playerYPos);
    }

    public void translatePlayer(){
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000),rendu);
        animate.setToX(playerXPos);
        animate.setToY(playerYPos);
        animate.setAutoReverse(false);
        animate.play();
    }

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

            if(directionMove % 2 == 1){
                playerXPos += 78;
                translatePlayer();
            }

            if(directionMove % 2 == 0){
                playerXPos -= 78;
                translatePlayer();
            }

            if(playerXPos > 664 && playerYPos > 37){
                playerXPos -= 78;
                playerYPos -= 78;
                directionMove++;
                translatePlayer();
            }

            if(playerXPos < 38 && playerYPos > 37) {
                playerXPos += 78;
                playerYPos -= 78;
                directionMove++;
                translatePlayer();
            }

            if((playerXPos == 39 && playerYPos == 427) && !Examen[0]) {
                lance = 0;
                playerPosition = 36;
            }

            if((playerXPos == 429 && playerYPos == 37) && !Examen[1]){
                lance =0;
                playerPosition = 78;
            }
        }
    }
}
