package ru.geekbrains.adminui.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.adminui.services.PictureService;
import ru.geekbrains.shopdb.model.Picture;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Consumer;

@Controller
@RequiredArgsConstructor
@RequestMapping("/picture")
public class PictureConroller {

    private final PictureService pictureService;

    @GetMapping("/{id}")
    public void loadPicture(@PathVariable("id") Integer id, HttpServletResponse response) {
        pictureService.findById(id).ifPresent(new Consumer<Picture>() {
            @SneakyThrows
            @Override
            public void accept(Picture picture) {
                response.setContentType(picture.getContentType());
                response.getOutputStream().write(picture.getPictureData().getData());
            }
        });

    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, HttpServletRequest request) {
        pictureService.delete(id);
        String referer = request.getHeader("referer");

        return "redirect:" + referer;
    }
}
