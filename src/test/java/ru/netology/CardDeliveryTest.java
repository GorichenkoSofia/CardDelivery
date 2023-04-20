package ru.netology;


import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    private String dataGenerate() {
        return LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void shouldBeSuccessfullComplited() {
        open("http://localhost:9999/");
        $x("//span[@data-test-id='city']//input").setValue("Санкт-Петербург");
        String currentDate;
        currentDate = dataGenerate();
        $x("//span[@data-test-id='date']//input").sendKeys(Keys.chord(Keys.CONTROL, Keys.SHIFT, Keys.ARROW_LEFT), Keys.DELETE);
        $x("//span[@data-test-id='date']//input").sendKeys(currentDate);
        $x("//span[@data-test-id='name']//input").setValue("Гориченко София");
        $x("//span[@data-test-id='phone']//input").setValue("+79505005050");
        $x("//label[@data-test-id='agreement']").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(25))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + currentDate));

    }
}
