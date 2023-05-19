package ch.fhnw.elibrary.elib.data.domain;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

// author @michimel and @RahelHaeusler

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long genreID;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    // constructor
    public Genre(Long genre_id, String name) {
        this.genreID = genre_id;
        this.name = name;
    }

    // getters and setters
    public Long getGenreID() {
        return genreID;
    }

    public void setGenreID(Long id) {
        this.genreID = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // toString
    @Override
    public String toString() {
        return String.format("Genre[id=" + genreID + ", name=" + name + "]");
    }
    
}
