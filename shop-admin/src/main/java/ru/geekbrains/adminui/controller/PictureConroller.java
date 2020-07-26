package ru.geekbrains.adminui.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.adminui.services.BrandPictureService;
import ru.geekbrains.adminui.services.PictureService;
import ru.geekbrains.shopdb.model.AbstractPicture;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Consumer;

@Controller
@RequiredArgsConstructor
@RequestMapping("/picture")
public class PictureConroller {

    private final PictureService pictureService;
    private final BrandPictureService brandPictureService;

    @GetMapping("/{id}")
    public void loadPicture(@PathVariable("id") Integer id, HttpServletResponse response) {
        pictureService.findById(id).ifPresent(new PictureConsumer<>(response));
    }

    @GetMapping("/brand/{id}")
    public void loadBrandPicture(@PathVariable("id") Integer id, HttpServletResponse response) {
        brandPictureService.findById(id).ifPresent(new PictureConsumer<>(response));
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, HttpServletRequest request) {
        pictureService.delete(id);
        String referer = request.getHeader("referer");

        return "redirect:" + referer;
    }

    @AllArgsConstructor
    private static class PictureConsumer<T extends AbstractPicture> implements Consumer<T> {

        private final HttpServletResponse response;

        @SneakyThrows
        @Override
        public void accept(T picture) {
            response.setContentType(picture.getContentType());
            response.getOutputStream().write(picture.getPictureData().getData());
        }
    }
}
