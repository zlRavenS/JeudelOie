package sample;

        import javafx.application.Application;
        import javafx.scene.Group;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.scene.layout.Pane;
        import javafx.stage.Stage;

public class Jeudeloie extends Application {



    public int[][] cirPos = new int [9][9];

    public static final int Tile_Size = 70;
    public static final int width = 9;
    public static final int height = 9;

    public boolean AI = false;
    public GameManager gameManager;


    private final Group tileGroup = new Group();

    private Parent menu(Stage stage){
    Pane window = new Pane();
    window.setPrefSize(300,200);
    Label titre = new Label("        Le Jeu de l'Oie Bourrée\n" + "                Version ESIREM\n" + "Par Rémi LEFAIVRE & Colin BRACQ\n" + "\n Tous droits réservés (pas touche)\n" + "\n\nChoisissez votre mode de jeu :");
    titre.setTranslateX(70);
    titre.setTranslateY(20);
    Button AiButton = new Button("Contre AI");
    AiButton.setTranslateX(170);
    AiButton.setTranslateY(170);
    AiButton.setOnAction(actionEvent -> {
        AI = true;
        Game();
        stage.close();
    });
    Button players = new Button("2 Joueurs");
    players.setTranslateX(80);
    players.setTranslateY(170);
    players.setOnAction(actionEvent ->
    {
        Game();
        stage.close();
    });

    window.getChildren().addAll(players,titre, AiButton);

    return window;
    }

    private void Game()
    {
        Stage stageMenu = new Stage();
        Scene scene = new Scene(createContent());
        scene.getStylesheets().add("sample/style.css");
        stageMenu.setTitle("Jeu de l'Oie");
        stageMenu.setScene(scene);
        stageMenu.show();

    }

    private Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize((width*Tile_Size) + 70, (height*Tile_Size) + 120);
        root.getChildren().addAll(tileGroup);

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                Tile tile = new Tile(Tile_Size, Tile_Size);
                tile.setTranslateX(j*Tile_Size);
                tile.setTranslateY(i*Tile_Size);
                tileGroup.getChildren().add(tile);

                cirPos[i][j] = j*(Tile_Size - 40);
            }
        }

        gameManager = new GameManager(AI);

        Image img = new Image("sample/plateau.png");
        ImageView bgImage = new ImageView();
        bgImage.setImage(img);
        bgImage.setFitHeight(700);
        bgImage.setFitWidth(700);

        tileGroup.getChildren().add(bgImage);
        tileGroup.getChildren().add(gameManager.gameButton);
        tileGroup.getChildren().add(gameManager.affichageLance);
        tileGroup.getChildren().add(gameManager.Regles);
        tileGroup.getChildren().add(gameManager.Dés);
        for(int i = 0;i<gameManager.nbrPlayer;i++)
        {
            tileGroup.getChildren().add(gameManager.listPlayer.get(i).rendu);
            if (!gameManager.listPlayer.get(i).AI)
                tileGroup.getChildren().add(gameManager.listPlayer.get(i).button);
        }


        return root;
    }



    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(menu(primaryStage));
        scene.getStylesheets().add("sample/style.css");
        primaryStage.setTitle("Jeu de l'Oie");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) { launch(args);
    }
}