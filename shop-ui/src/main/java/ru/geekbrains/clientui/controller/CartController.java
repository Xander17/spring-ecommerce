package ru.geekbrains.clientui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.clientui.dto.ProductDto;
import ru.geekbrains.clientui.service.CartService;
import ru.geekbrains.clientui.service.ProductService;
import ru.geekbrains.clientui.service.model.OrderLineItem;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final CommonController commonController;

    @GetMapping
    public String cartPage(Model model) {
        commonController.addRequiredModelAttributes(model);
        model.addAttribute("orderItems", cartService.getCart());
        return "cart";
    }

    @PostMapping("/update")
    public String update(Integer lineItemId, Integer qty) {
        cartService.update(getNewOrderLineItem(lineItemId, qty));
        return "redirect:/cart";
    }

    @PostMapping("/add")
    public String add(ProductDto product, Integer qty, HttpServletRequest request) {
        cartService.addQuantity(
                productService.findById(product.getId()).orElseThrow(IllegalArgumentException::new), qty);

        String referer = request.getHeader("referer");
        return "redirect:" + referer;
    }

    @DeleteMapping("/delete")
    public String delete(Integer lineItemId) {
        cartService.removeProduct(getNewOrderLineItem(lineItemId, 0));
        return "redirect:/cart";
    }

    private OrderLineItem getNewOrderLineItem(Integer lineItemId, Integer qty) {
        return new OrderLineItem(
                productService.findById(lineItemId).orElseThrow(IllegalArgumentException::new), qty);
    }
}
