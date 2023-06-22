package models;

import models.enumPackage.BotDifficultyLevel;
import models.enumPackage.PlayerType;
import strategies.BotDifficultyLevelFactory.BotDifficultyLevelFactory;
import strategies.botPlayingStrategies.BotPlayingStrategy;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;

    private BotPlayingStrategy botPlayingStrategy;

    public Bot(Symbol symbol, String name, Long id, BotDifficultyLevel botDifficultyLevel){
        super(symbol, name, id, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotDifficultyLevelFactory.getBotPlayingStrategyForDifficultyLevel(botDifficultyLevel);
    }
    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move makeMove(Board board) {
        Move move = botPlayingStrategy.makeMoveBot(board);
        move.setPlayer(this);
        return move;
    }
}
