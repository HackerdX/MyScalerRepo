package strategies.botPlayingStrategies;

import models.Board;
import models.Bot;
import models.Move;

public interface BotPlayingStrategy {
//    public Move makeMoveBot(Board board, Bot bot);
    // IS BOT parameter needed as bot can do a move in board, BOT is irrelavant..!

    public Move makeMoveBot(Board board);
}
