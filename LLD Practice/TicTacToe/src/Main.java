import controllers.GameController;
import models.Bot;
import models.Game;
import models.Player;
import models.Symbol;
import models.enumPackage.BotDifficultyLevel;
import models.enumPackage.GameState;
import models.enumPackage.PlayerType;
import strategies.winningStrategies.ColumnWinningStrategy;
import strategies.winningStrategies.DiagonalWinningStrategy;
import strategies.winningStrategies.RowWinningStrategy;
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

            List<WinningStrategy> winningStrategies = List.of(
                    new RowWinningStrategy(),
                    new ColumnWinningStrategy(),
                    new DiagonalWinningStrategy()
            );

            Game game = new Game(dimensionOfBoard, playerList, winningStrategies);

            while(gameController.checkState(game).equals(GameState.IN_PROGRESS)){
                // 1. Print the Board
                gameController.printBoard(game);
                // 2. x's turn
                // 3. ask x to make move
                gameController.makeMove(game);

            }

            System.out.println("GAME IS FINISHED!");
            GameState state = gameController.checkState(game);

            if(state.equals(GameState.DRAW)){
                System.out.println("Game is a DRAW!");
            } else if(state.equals(GameState.WIN)){
                System.out.println("Winner is: "+gameController.getWinner(game).getName());
            }
        } catch (Exception e){
            System.out.println("Something went wrong");
        }

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