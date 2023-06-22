package strategies.botPlayingStrategies;

import models.Board;
import models.Cell;
import models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMoveBot(Board board) {
        for(List<Cell> cellRow: board.getBoard()){
            for(Cell cell: cellRow){
                if(cell.getPlayer() == null){
                    return new Move(cell, null);
                }
            }
        }
        return null;
    }
}
