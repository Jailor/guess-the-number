package org.example;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}

// OLD CODE
// create context (container)
//   ConfigurableApplicationContext context
//                = new AnnotationConfigApplicationContext(GameConfig.class);
//        // asking the container to generate a random number generator instance (bean) (singleton instance)
//        NumberGenerator numberGenerator
//                = context.getBean(NumberGenerator.class);
//
//        // call method next() to get a random number
//        int number = numberGenerator.next();
//
//        // log generated number, will automatically replace the parenthesis
//        log.info("Random number = {}", number);
//
//        // get game bean from context
//        // Game game = context.getBean(Game.class);
//
//        MessageGenerator messageGenerator =
//                context.getBean(MessageGenerator.class);
//        log.info("getMainMessage= {}", messageGenerator.getMainMessage());
//        log.info("getResultMessage= {}", messageGenerator.getResultMessage());
//        // close context (container), prevent any memory leaks
// context.close();