package strategies.winningStrategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.List;

public class ColumnWinningStrategy implements WinningStrategy{
    private List<HashMap<Symbol, Integer>> symbolCountColumn;
    /* Why list, to check for each column */
    @Override
    public boolean checkWinner(Board board, Move move) {
        return false;
    }
}
