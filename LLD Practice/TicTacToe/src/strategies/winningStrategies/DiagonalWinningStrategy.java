package strategies.winningStrategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.List;

public class DiagonalWinningStrategy implements WinningStrategy{
    private List<HashMap<Symbol, Integer>> symbolCountDiagonal;
    /* Why list, to check for each row */
    @Override
    public boolean checkWinner(Board board, Move move) {
        return false;
    }
}
