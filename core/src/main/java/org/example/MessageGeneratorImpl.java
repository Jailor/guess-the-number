package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator{
    private static final String MAIN_MESSAGE = "game.main.message";
    private static final String WIN = "game.win";
    private static final String LOSE = "game.lose";
    private static final String INVALID_RANGE = "game.invalid.range";
    private static final String FIRST_GUESS = "game.first.guess";
    private static final String HIGHER = "game.higher";
    private static final String LOWER = "game.lower";
    private static final String REMAINING = "game.remaining";
    private final Game game;
    // defines a method called getMessage() that we can use to get the message by key
    // from the application properties file.
    private final MessageSource messageSource;
    @Autowired
    public MessageGeneratorImpl(Game game, MessageSource messageSource) {
        this.game = game;
        this.messageSource = messageSource;
    }

    @PostConstruct
    public void init()
    {
        log.info("game = {}", game);
    }

    /**
     * Method will build the string that will be displayed to the user
     * by passing 2 arguments to the getMessage() method that will replace
     * {0} and {1} in the message property
     * @return the message to be displayed to the user
     */
    @Override
    public String getMainMessage() {
        return getMessage(MAIN_MESSAGE, game.getSmallest(), game.getBiggest());
    }

    /**
     * For each case, we send a message according to the state of the game.
     * If none of win, lose, valid number or remaining guesses are true, then
     * we know that the user has made a guess, and we need to tell them if it
     * was higher or lower than the number. Accordingly, the remaining message
     * will be displayed, with a different direction depending on the guess.
     * @return the result message to be displayed to the user after each guess
     */
    @Override
    public String getResultMessage() {
        if(game.isGameWon()) {
            return getMessage(WIN, game.getNumber());
        } else if(game.isGameLost()) {
            return getMessage(LOSE, game.getNumber());
        } else if(!game.isValidNumberRange()) {
            return getMessage(INVALID_RANGE);
        } else if(game.getRemainingGuesses() == game.getGuessCount()) {
            return getMessage(FIRST_GUESS);
        } else {
            String direction = getMessage(LOWER);

            if(game.getGuess() < game.getNumber()) {
                direction = getMessage(HIGHER);
            }

            return getMessage(REMAINING, direction, game.getRemainingGuesses());
        }

    }

    /**
     * LocaleContextHolder.getLocale() will return the locale that is set in the application
     * @param code the property key to lookup up, such as 'game.home'
     * @param args Array of arguments to pass to in order to resolve the message, can be
     *             as many as we need
     * @return the resolved message
     */
    private String getMessage(String code, Object... args)
    {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}

/*old code for get result message
          if(game.isGameWon()) {
            return "You guessed the number! It was " + game.getNumber();
        } else if (game.isGameLost()) {
            return "You lost. The number was " + game.getNumber();
        } else if (!game.isValidNumberRange()) {
            return "Invalid number range!" +
                    "\nYou have " + game.getRemainingGuesses() + " guesses left";
        } else if (game.getRemainingGuesses() == game.getGuessCount()) {
            return "What is your first guess?";
        }
        else
        {
            String direction = "Lower";
            if(game.getGuess() < game.getNumber()){
                direction = "Higher";
            }
            if(game.getRemainingGuesses() != 1) return direction +"! You have " + game.getRemainingGuesses() + " guesses left";
            else  return direction +"! You have " + game.getRemainingGuesses() + " guess left";
        }*/
