package ru.yandex.practicum.filmorate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.IncorrectParameterException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;


import java.util.*;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Film> getFilms() {
        return filmService.getFilms();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Film getFilm(@PathVariable(name = "id", required = false) final Long filmId) {
        if (filmId == null) throw new IncorrectParameterException("Id должен быть указан");
        return filmService.getFilm(filmId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Film createFilm(@RequestBody Film film) {
        return filmService.addFilm(film);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Film updateFilm(@RequestBody Film newFilm) {
        if (newFilm.getId() == null) throw new IncorrectParameterException("Id должен быть указан");
        return filmService.updateFilm(newFilm);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFilm(@PathVariable(name = "id", required = false) final Long filmId) {
        if (filmId == null) throw new IncorrectParameterException("Id должен быть указан");
        filmService.deleteFilm(filmId);
    }

    // добавить лайк
    @PutMapping("/{id}/like/{userId}")
    public void addLike(@PathVariable(name = "id", required = false) final Long filmId, @PathVariable(required = false) final Long userId) {
        // добавьте необходимые проверки
        if (filmId == null) throw new IncorrectParameterException("Необходимо установить параметр filmId");
        if (userId == null) throw new IncorrectParameterException("Необходимо установить параметр userId");
        filmService.addLike(filmId, userId);
    }

    // удалить лайк
    @DeleteMapping("/{id}/like/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLike(@PathVariable(name = "id", required = false) final Long filmId, @PathVariable(required = false) final Long userId) {
        // добавьте необходимые проверки
        if (filmId == null) throw new IncorrectParameterException("Необходимо установить параметр filmId");
        if (userId == null) throw new IncorrectParameterException("Необходимо установить параметр userId");
        filmService.deleteLike(filmId, userId);
    }

    @GetMapping("/popular")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Film> getMostPopularFilms(@RequestParam(name = "count", defaultValue = "10", required = false) Integer count) {
        String sort = "desc"; //asc
        return filmService.findMostPopular(count, 0, sort);
    }


}
