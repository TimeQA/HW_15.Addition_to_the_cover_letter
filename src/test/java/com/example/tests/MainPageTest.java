package com.example.tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Owner("RomanovAleksei")
@Severity(SeverityLevel.BLOCKER)
public class MainPageTest extends TestBase{

    MainPageElement mainPageElement = new MainPageElement();

    @DisplayName("Проверка drop-down menu)")
    @Test
    public void upperBarTest() {
        step("Открываем главную страницу" ,() -> {
            mainPageElement.openMainPage();
        });

        step("В основных разделах сайта наводим курсор мыши на раздел \"Selecty\"", () -> {
            mainPageElement.highBarChapterSelecty.hover();
        });
        step("Првоеряем что в раскрывающемся списке есть подраздел \"О компании\"", () -> {
            mainPageElement.dropDownMenuFromSelecty.shouldHave(Condition.text("О компании"));
        });

    }

    @DisplayName("Проверка наличия публикаций в разделе \"СМИ о нас\" ")
    @Test
    public void availabilityOfPublicationTest() {
        step("Открываем главную страницу" ,() -> {
            mainPageElement.openMainPage();
        });
        $(".t886__btn-wrapper").click();
        $(".t-feed__button-wrapper").click();
        $$(".t-feed_col").shouldHave(CollectionCondition.sizeGreaterThan(0));
    }


    static Stream<Arguments> langUpperBarTest() {
        return Stream.of(
                Arguments.of(Lang.RU, List.of("Selecty", "Услуги", "Карьера", "Контакты")),
                Arguments.of(Lang.EN, List.of("Selecty", "Services", "Career", "Contacts"))
        );
    }

    @MethodSource
    @DisplayName("Проверка заголовков основных разделов при выборе русского и анлийского языка")
    @ParameterizedTest(name = "Для {0} отображаются кнопки основых разделов на странице {1} ")
    public void langUpperBarTest(Lang lang, List<String> expectedButtons) {
        step("Открываем главную страницу" ,() -> {
            mainPageElement.openMainPage();
        });
        $(".t228__right_buttons_wrap").$(byText(lang.name())).click();
        $$("li.t228__list_item").shouldHave(CollectionCondition.texts(expectedButtons));
    }

    @DisplayName("Проверка появления окна с видео")
    @Test
    public void videoFormTest() {
        step("Открываем главную страницу" ,() -> {
            mainPageElement.openMainPage();
        });

        $("[data-elem-id=\"1649825099006\"]").click();
        Selenide.switchTo().frame("youtubeiframe424911477");
        $("#player").shouldBe(visible);
    }

    @DisplayName("Проверка появления окна с предупреждением о необходимости заполнения " +
            "обязательных полей для отправки резюме")
    @Test
    public void newResumeFormTest() throws InterruptedException {
        step("Открываем страницу \"Working in IT\"" ,() -> {
            open("https://selecty.io/career");
        });
        $("[href=\"#sendmyform\"]").click();
        $(".js-form-popup-errorbox").shouldBe(Condition.text("Please fill out all required fields"));
    }
}
