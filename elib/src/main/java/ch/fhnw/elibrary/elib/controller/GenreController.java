package ch.fhnw.elibrary.elib.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ch.fhnw.elibrary.elib.business.service.GenreService;
import ch.fhnw.elibrary.elib.data.domain.Genre;


// GenreController class author @michimel

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    @Autowired
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @PostMapping("/newGenre")
    public Genre createGenre(@RequestBody Genre genre) {
        return genreService.createGenre(genre);
    }

    @PutMapping("/updateGenre/{genreId}")
    public Genre updateGenre(@PathVariable Long genreId, @RequestBody Genre genre) {
        return genreService.updateGenre(genreId, genre);
    }

    /* the following mappings are not used in the application, 
    as budibase provides the functionality to search via the filter function,
    but for completeness we provide the mappings below */
    
    @GetMapping("/findByName/{name}")
    public List<Genre> getGenresByName(@PathVariable String name) {
        return genreService.getGenresByName(name);
    }
}

