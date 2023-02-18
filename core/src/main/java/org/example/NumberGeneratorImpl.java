package org.example;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

// not in the interface because interface should be decoupled
@Component
@Getter
public class NumberGeneratorImpl implements NumberGenerator {
    @Getter(AccessLevel.NONE)
    private final Random random = new Random();
    private final int maxNumber;
    private final int minNumber;
    // Annotations for minNumber and maxNumber can be used as the field,
    // but it is better to use them as the constructor parameters
    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber,@MinNumber int minNumber) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }

    // == public methods ==
    @Override
    public int next() {
        return random.nextInt(minNumber,maxNumber);
    }
}
