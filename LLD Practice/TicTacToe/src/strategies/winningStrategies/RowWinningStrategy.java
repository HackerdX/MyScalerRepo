package strategies.winningStrategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy{
    private Map<Integer, HashMap<Symbol, Integer>> symbolCountRow = new HashMap<>();
//    private List<HashMap<Symbol, Integer>> symbolCountRow;
    /* Why list, to check for each row, use Map*/

    // All board Row map
    // | 0 | -> {X: 0, O: 0}
    // | 1 | -> {X: 0, O: 0}
    // | 2 | -> {X: 0, O: 0}
    // | 3 | -> {X: 0, O: 0}
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        if(!symbolCountRow.containsKey(row)){
            symbolCountRow.put(row, new HashMap<>());
        }
        Symbol symbol = move.getPlayer().getSymbol();;
        Map<Symbol, Integer> currRowSymbol = symbolCountRow.get(row);
        if(!currRowSymbol.containsKey(symbol)){
            currRowSymbol.put(symbol, 0);
        }
        currRowSymbol.put(symbol, currRowSymbol.get(symbol) + 1);

        if(currRowSymbol.get(symbol).equals(board.getSize())){
            return true;
        }
        return false;
    }
}
