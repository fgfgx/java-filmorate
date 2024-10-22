package ru.yandex.practicum.filmorate.model;


import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"email"})
public class User {
    private Long id;
    private String email;
    private String login;
    private String name;
    private LocalDate birthday;
}

