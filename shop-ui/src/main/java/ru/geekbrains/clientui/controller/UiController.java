package ru.geekbrains.clientui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UiController {

    private final CommonController commonController;

    @GetMapping
    public String indexPage(Model model) {
        commonController.addRequiredModelAttributes(model);
        return "index";
    }
}
