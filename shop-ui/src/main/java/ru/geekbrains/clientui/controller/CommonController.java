package ru.geekbrains.clientui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ru.geekbrains.clientui.service.CartService;

@Component
@RequiredArgsConstructor
public class CommonController {

    private final CartService cartService;

    public void addRequiredModelAttributes(Model model) {
        model.addAttribute("cartSize", cartService.getCartSize());
        model.addAttribute("cartSum", cartService.getRecalculatedSum());
    }
}
