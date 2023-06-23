package models;

import Exceptions.DuplicateSymbolException;
import Exceptions.MoreThanOneBotException;
import Exceptions.PlayersCountDimensionMismatchException;
import models.enumPackage.CellState;
import models.enumPackage.GameState;
import models.enumPackage.PlayerType;
import strategies.winningStrategies.ColumnWinningStrategy;
import strategies.winningStrategies.DiagonalWinningStrategy;
import strategies.winningStrategies.RowWinningStrategy;
import strategies.winningStrategies.WinningStrategy;

import java.util.*;

public class Game {
    private List<Player> playerList;
    private Board board;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextPlayerMoveIndex;

    private List<WinningStrategy> winningStrategies;

    public static Builder getBuilder(){
        return new Builder();
    }

    public Game(int dimensions,
                List<Player> playerList,
                List<WinningStrategy> winningStrategies) {
        this.playerList = playerList;
        this.board = new Board(dimensions);
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerMoveIndex = 0;
        this.winningStrategies = winningStrategies;
    }

    public static class Builder{
//        private List<Player> playerList = new ArrayList<>();
//        private List<WinningStrategy> winningStrategies = new ArrayList<>();

        private int dimension;

        private List<Player> playerList;
        private List<WinningStrategy> winningStrategies;

        private Builder() {
            this.dimension = 0;
            this.playerList = new ArrayList<>();
            this.winningStrategies = new ArrayList<>();
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayerList(List<Player> playerList) {
            this.playerList = playerList;
            return this;
        }

        public Builder addPlayer(Player player){
            this.playerList.add(player);
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder addWinningStrategy(WinningStrategy winningStrategy){
            this.winningStrategies.add(winningStrategy);
            return this;
        }
        /* Why we need addPlayer and addWinningStrategy, because we can again add
        * i) via list <= To support list of them at a time
        * ii) one by one <= To support one to one addPlayer and addWinningStrategy
        * */

        private void botCountValidation() throws MoreThanOneBotException{
            int botCount = 0;
            for(Player player: playerList){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if(botCount > 1){
                throw new MoreThanOneBotException();
            }
        }

        private void playerCountValidation() throws PlayersCountDimensionMismatchException{
            if(playerList.size() != dimension - 1){
                throw new PlayersCountDimensionMismatchException();
            }
        }

        private void uniqueSymbolValidation() throws DuplicateSymbolException{
            Map<Character, Integer> symbolCounts = new HashMap<>();
            for(Player player: playerList){
                if(!symbolCounts.containsKey(player.getSymbol().getPlayerSymbol())){
                    symbolCounts.put(player.getSymbol().getPlayerSymbol(), 0);
                }
                symbolCounts.put(player.getSymbol().getPlayerSymbol(), symbolCounts.get(player.getSymbol().getPlayerSymbol()) + 1);
                if(symbolCounts.get(player.getSymbol().getPlayerSymbol()) > 1){
                    throw new DuplicateSymbolException();
                }
            }
        }
        private void validate() throws DuplicateSymbolException, PlayersCountDimensionMismatchException, MoreThanOneBotException {
            /* We can also do it in a separate class */
//            try {
//                botCountValidation();
//                playerCountValidation();
//                uniqueSymbolValidation();
//            } catch (Exception e){
//                throw  e;
//            }
            /* We don't need "try block" as we are throwing the exception
            * directly from above methods
            *
            * also as return values of validation methods are not used,
            * we can make above methods as void
            * */
            botCountValidation();
            playerCountValidation();
            uniqueSymbolValidation();
        }

        public Game build() throws DuplicateSymbolException, PlayersCountDimensionMismatchException, MoreThanOneBotException {
            validate();
            return new Game( dimension, playerList, winningStrategies);
        }
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }

    public void setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
        this.nextPlayerMoveIndex = nextPlayerMoveIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public void printBoard(){
        board.printBoard();
    }

    private boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        if(row >= board.getSize()) return false;
        if(col >= board.getSize()) return false;

//        if(move.getCell().getCellState().equals(CellState.FILLED)) return false;
        /* Why can't we do this? */

        if(board.getBoard().get(row).get(col).getCellState().equals(CellState.FILLED)) return false;

        return true;
    }

    private boolean checkWinner(Board board, Move move){
        for(WinningStrategy winningStrategy: winningStrategies){
            if(winningStrategy.checkWinner(board, move)) {
                return true;
            }
        }
        return false;
    }

    public void makeMove() {

        // get current player to make a move
        Player currentPlayer = playerList.get(nextPlayerMoveIndex);
        System.out.println("It is "+currentPlayer.getName()+"'s turn. Please make your move");
        Move move = currentPlayer.makeMove(board);
        System.out.println(currentPlayer.getName()+" has made a move at row: "+ move.getCell().getRow()+" " +
                " column "+move.getCell().getCol()+".");

        // To Validate if current move possible
        if(!validateMove(move)){
            System.out.println("Invalid Move, Please try again!");
            return;
        }

        // If current move validation successful, do this
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell cellToChange = board.getBoard().get(row).get(col);
        cellToChange.setCellState(CellState.FILLED);
        cellToChange.setPlayer(currentPlayer);
        Move finalMove = new Move(cellToChange, currentPlayer);
        moves.add(finalMove);

        nextPlayerMoveIndex = (nextPlayerMoveIndex + 1) % playerList.size();

        // State of the game => to check the WINNER
        if(checkWinner(board, finalMove)){
            gameState = GameState.WIN;
            winner = currentPlayer;
            return;
        }
        // If we don't find the winner and all the cell(moves) are exhausted of either players!!
        // State of game => to check if DRAW
        int boardSize = this.board.getSize();
        if(moves.size() == boardSize * boardSize){
            gameState = GameState.DRAW;
        }

        /* In this part, when we run, our winner will not work because,
        * winning strategy is not yet being created
        * */
    }

}
