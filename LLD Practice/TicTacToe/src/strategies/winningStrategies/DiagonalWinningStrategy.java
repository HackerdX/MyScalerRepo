package strategies.winningStrategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy{
    private final Map<Symbol, Integer> symbolCountLeftDiagonal = new HashMap<>();
    private final Map<Symbol, Integer> symbolCountRightDiagonal = new HashMap<>();
    /* Why list, to check for each row, use map */


    // | (0, 0)  |         | (0, 2) |
    // |         | (1, 1)  |        |
    // | (2, 0)  |         | (2, 2) |
    @Override
    public boolean checkWinner(Board board, Move move) {
        Symbol symbol = move.getPlayer().getSymbol();
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        if(row == col){
            if(!symbolCountLeftDiagonal.containsKey(symbol)){
                symbolCountLeftDiagonal.put(symbol, 0);
            }
            symbolCountLeftDiagonal.put(symbol, symbolCountLeftDiagonal.get(symbol) + 1);
            if(symbolCountLeftDiagonal.get(symbol).equals(board.getSize())){
                return true;
            }
        }

        if(row + col == board.getSize() - 1){
            if(!symbolCountRightDiagonal.containsKey(symbol)){
                symbolCountRightDiagonal.put(symbol, 0);
            }
            symbolCountRightDiagonal.put(symbol, symbolCountRightDiagonal.get(symbol) + 1);
            return symbolCountRightDiagonal.get(symbol).equals(board.getSize());
        }
        return false;
    }
}
