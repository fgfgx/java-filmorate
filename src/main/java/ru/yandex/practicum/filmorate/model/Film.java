package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class Film {

    private Long id;
    private Long rate;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private int duration;
    private Long mpaId;
    private MPA mpa;
    private List<Genre> genres;
}
