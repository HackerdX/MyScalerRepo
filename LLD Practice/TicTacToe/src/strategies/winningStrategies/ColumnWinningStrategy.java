package strategies.winningStrategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy{
    private Map<Integer, HashMap<Symbol, Integer>> symbolCountColumn = new HashMap<>();
//    private List<HashMap<Symbol, Integer>> symbolCountColumn;
    /* Why list, to check for each column, needs to be a Map*/

    // All board Column map
    // | 0 | -> {X: 0, O: 0}
    // | 1 | -> {X: 0, O: 0}
    // | 2 | -> {X: 0, O: 0}
    // | 3 | -> {X: 0, O: 0}
    @Override
    public boolean checkWinner(Board board, Move move) {
       int col = move.getCell().getCol();
       if(!symbolCountColumn.containsKey(col)){
           symbolCountColumn.put(col, new HashMap<>());
       }
       Symbol symbol = move.getPlayer().getSymbol();;
       Map<Symbol, Integer> currColSymbol = symbolCountColumn.get(col);
        if(!currColSymbol.containsKey(symbol)){
           currColSymbol.put(symbol, 0);
        }
        currColSymbol.put(symbol, currColSymbol.get(symbol) + 1);

        if(currColSymbol.get(symbol).equals(board.getSize())){
            return true;
        }
        return false;
    }
}
