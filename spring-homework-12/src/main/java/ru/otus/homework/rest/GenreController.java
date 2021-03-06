package ru.otus.homework.rest;

import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.rest.dto.GenreDto;
import ru.otus.homework.rest.mappers.GenreMapper;
import ru.otus.homework.service.genres.GenreService;

import java.util.List;

import static ru.otus.homework.metrics.Metrics.Genres.*;

@Controller
public class GenreController {
    private final GenreService genreService;
    private final GenreMapper genreMapper;

    public GenreController(GenreService genreService, GenreMapper genreMapper) {
        this.genreService = genreService;
        this.genreMapper = genreMapper;
    }

    @Timed(GET_GENRES_REQ_TIME)
    @GetMapping(value = "/genre")
    public String getGenres(Model model){
        List<Genre> genres = genreService.getAllGenres();
        model.addAttribute("genres", genres);
        return "genre-list";
    }

    @Timed(GET_GENRE_EDIT_REQ_TIME)
    @GetMapping(value = "/genre/{id}/edit")
    public String editGenre(@PathVariable("id") long id, Model model){
        Genre genre = genreService.getGenreById(id);
        model.addAttribute("genre", genre);
        return "genre-edit";
    }

    @Timed(PATCH_GENRE_REQ_TIME)
    @PatchMapping(value = "/genre/{id}")
    public String saveGenre(@PathVariable("id") long id,
                            @ModelAttribute("genre") GenreDto genre){
        genreService.updateGenreById(id, genreMapper.toGenre(genre));
        return "redirect:/genre";
    }

    @Timed(CREATE_GENRE_REQ_TIME)
    @PostMapping(value = "/genre")
    public String addGenre(@ModelAttribute("genre") GenreDto genre, Model model){
        genreService.insertGenre(genreMapper.toGenre(genre));
        model.addAttribute("genre", genre);
        return "redirect:/genre";
    }

    @Timed(DELETE_GENRE_REQ_TIME)
    @DeleteMapping(value = "/genre/{id}")
    public String deleteGenre(@PathVariable("id") long id){
        genreService.deleteGenreById(id);
        return "redirect:/genre";
    }
}
