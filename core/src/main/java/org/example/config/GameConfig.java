package org.example.config;

import org.example.GuessCount;
import org.example.MaxNumber;
import org.example.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "org.example")
// path for the properties file, on the classpath
@PropertySource("classpath:config/game.properties")
public class GameConfig {
    /**
     * The @Value annotation allows us to inject values from the properties file into the bean.
     * Colon gives default values if the property is not found in the properties file.
     * Spring will automatically convert the string values to the correct type.
     */
    @Value("${game.maxNumber:200}")
    private int maxNumber;

    @Value("${game.minNumber:0}")
    private int minNumber;
    @Value("${game.guessCount:5}")
    private int guessCount;

    /**
     * In order to autowire int values and allow for changing the names of the
     * fields without breaking the bean mechanism, we need to create a custom
     * annotation. This is done by creating an interface with the @Qualifier annotation, instead
     * of using strings and trying to remember bean names. In order to use the qualifiers,
     * we annotate the beans with the custom annotation.
     */
    @Bean
    @MaxNumber
    public int maxNumber(){
        return maxNumber;
    }

    @Bean
    @MinNumber
    public int minNumber(){
        return minNumber;
    }

    @Bean
    @GuessCount
    public int guessCount(){return guessCount;}
}
