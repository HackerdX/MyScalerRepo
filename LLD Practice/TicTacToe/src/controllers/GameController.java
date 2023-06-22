package controllers;

import Exceptions.DuplicateSymbolException;
import Exceptions.MoreThanOneBotException;
import Exceptions.PlayersCountDimensionMismatchException;
import models.Game;
import models.Player;
import models.enumPackage.GameState;
import strategies.winningStrategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int dimensionsOfBoard,
                   List<Player> players,
                   List<WinningStrategy> winningStrategies) throws DuplicateSymbolException, PlayersCountDimensionMismatchException, MoreThanOneBotException {
        return Game.getBuilder()
                .setDimension(dimensionsOfBoard)
                .setPlayerList(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public void makeMove(Game game){
        game.makeMove();
    }

    void undo(Game game){

    }

    public GameState checkState(Game game){
        return game.getGameState();
    }

    void getWinner(Game game){

    }

    public void printBoard(Game game){
        game.printBoard();
    }
}
