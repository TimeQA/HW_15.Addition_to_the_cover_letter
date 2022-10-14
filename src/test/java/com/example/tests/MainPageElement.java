package com.example.tests;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPageElement {

    public SelenideElement
            highBarChapterSelecty = $("#nav435104973").$(byText("Selecty")),
            dropDownMenuFromSelecty = $("#nav435104973 div.t-menusub__content");

    public MainPageElement openMainPage() {
        open("https://selecty.ru/");

        return this;
    }



}
