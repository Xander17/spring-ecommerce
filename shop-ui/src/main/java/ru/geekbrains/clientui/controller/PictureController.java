package ru.geekbrains.clientui.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.clientui.service.PictureService;
import ru.geekbrains.shopdb.model.Picture;

import javax.servlet.http.HttpServletResponse;
import java.util.function.Consumer;

@Controller
@RequiredArgsConstructor
@RequestMapping("/picture")
public class PictureController {

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
}
