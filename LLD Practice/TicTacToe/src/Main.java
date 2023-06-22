import controllers.GameController;
import models.Bot;
import models.Game;
import models.Player;
import models.Symbol;
import models.enumPackage.BotDifficultyLevel;
import models.enumPackage.GameState;
import models.enumPackage.PlayerType;
import strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        GameController gameController = new GameController();

        try {

            int dimensionOfBoard = 3;

            List<Player> playerList = new ArrayList<>();
            playerList.add(new Player(new Symbol('X'), "Anmol", 1L, PlayerType.HUMAN));
            playerList.add(new Bot( new Symbol('O'), "GPT", 2L, BotDifficultyLevel.MEDIUM));

            List<WinningStrategy> winningStrategies = new ArrayList<>();

            Game game = new Game(dimensionOfBoard, playerList, winningStrategies);

            while(gameController.checkState(game).equals(GameState.IN_PROGRESS)){
                // 1. Print the Board
                gameController.printBoard(game);
                // 2. x's turn
                // 3. ask x to make move
                gameController.makeMove(game);

            }

        } catch (Exception e){
            System.out.println("Something went wrong");
        }
        System.out.println("GAME IS CREATED!");
//        System.out.println("WELCOME TO TIC TAC TOE GAME!");
//        GameController gameController = new GameController();
//        while(gameController.getGameStatus().equals(GameState.IN_PROGRESS)){
//            gameController.printBoard();
//            gameController.makeNextMove();
//
//        }
//        if(gameController.getGameStatus().equals(GameState.DRAW)){
//            System.out.println("DRAW!");
//        } else{
//            System.out.println("GAME HAS WON!");
//        }
    }
}