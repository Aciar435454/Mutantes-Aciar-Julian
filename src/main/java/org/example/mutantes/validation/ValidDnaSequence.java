package org.example.mutantes.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidDnaSequenceValidator.class)
public @interface ValidDnaSequence {

    String message() default "Secuencia de ADN inválida (solo A,T,C,G y matriz NxN)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
