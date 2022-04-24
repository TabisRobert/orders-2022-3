package pl.edu.wszib.orders;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExampleTest {

    @Test
//    @DisplayName("should print test") - already covered by configuring:
//    junit.jupiter.displayname.generator.default=org.junit.jupiter.api.DisplayNameGenerator$ReplaceUnderscores
//    In Kotlin fun `should be able to create order when passes correct data`
//    public void shouldBeAbleToCreateOrderWhenPassesCorrectData - less readable in my opinion
    public void should_print_test() {
        System.out.println("Test");
    }
}
