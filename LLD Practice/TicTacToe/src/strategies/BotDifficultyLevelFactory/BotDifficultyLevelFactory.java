package strategies.BotDifficultyLevelFactory;

import models.Bot;
import models.enumPackage.BotDifficultyLevel;
import strategies.botPlayingStrategies.BotPlayingStrategy;
import strategies.botPlayingStrategies.EasyBotPlayingStrategy;

public class BotDifficultyLevelFactory {
    public static BotPlayingStrategy getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel botDifficultyLevel){
        return new EasyBotPlayingStrategy();
    }
}
