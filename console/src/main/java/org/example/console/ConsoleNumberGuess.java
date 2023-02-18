package org.example.console;

import lombok.extern.slf4j.Slf4j;
import org.example.Game;
import org.example.MessageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Slf4j
@Component
public class ConsoleNumberGuess  {
    private final Game game;
    private final MessageGenerator messageGenerator;

    /**
     * Autowired annotation is used to inject the bean dependency.
     */
    @Autowired
    public ConsoleNumberGuess(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    /**
     * On either initializing or refreshing the ApplicationContext, Spring raises the ContextRefreshedEvent.
     * Typically, a refresh can get triggered multiple times as long as the context has not been closed.
     * <p>
     * Spring supports an annotation-driven event listener – @EventListener.
     * In particular, we can make use of this annotation to automatically register an ApplicationListener
     * based on the signature of the method. EvenListener annotation does not require any configuration.
     */
    @EventListener(ContextRefreshedEvent.class)
    public void start() { //ContextRefreshedEvent event
            log.info("start -> Container ready for use!");

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println(messageGenerator.getMainMessage());
            System.out.println(messageGenerator.getResultMessage());

            // no safety checking
            int guess;
            try {
                 guess = scanner.nextInt();
            }catch (InputMismatchException exception)
            {
                System.out.println("Invalid number!");
                scanner.nextLine();
                continue;
            }

            scanner.nextLine();
            game.setGuess(guess);
            game.check();

            if(game.isGameWon() || game.isGameLost())
            {
                System.out.println(messageGenerator.getResultMessage());
                System.out.println("Play again? y/n");

                String playAgainString = scanner.nextLine().trim();
                if(!playAgainString.equalsIgnoreCase("y")) {
                    break;
                }
                game.reset();
            }
        }
    }
}
