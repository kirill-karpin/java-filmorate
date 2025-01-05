package ru.yandex.practicum.filmorate.annotaion;

import jakarta.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinimumDurationValidator.class)
public @interface MinimumDuration {
    String message() default "Duration must be greater {value}";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};

    int value() default 0;
}
