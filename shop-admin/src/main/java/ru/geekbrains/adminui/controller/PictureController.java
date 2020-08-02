package ru.geekbrains.adminui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.adminui.services.BrandPictureService;
import ru.geekbrains.adminui.services.PictureService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/picture")
public class PictureController {

    private final PictureService pictureService;

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, HttpServletRequest request) {
        pictureService.delete(id);
        String referer = request.getHeader("referer");

        return "redirect:" + referer;
    }
}
