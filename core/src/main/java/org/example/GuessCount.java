package org.example;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// context where an annotation type is applicable: fields, parameters and methods
@Target({ElementType.FIELD,ElementType.PARAMETER, ElementType.METHOD})
// used at runtime by the JVM
@Retention(RetentionPolicy.RUNTIME)
// spring annotation used to qualify other annotations
@Qualifier
// @interface is a custom annotation
public @interface GuessCount {
}
