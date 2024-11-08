package com.noob.moviesapi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "movies")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull
    private String title;
    @Column(length = 510)
    private String synopsis;
    private String url;
    private String image;
    private Date releaseDate;
    private Float rating;
    private Short runtime;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToMany
    @JoinTable(name = "moive_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @ToString.Exclude
    private Set<Actor> actors = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "moive_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @ToString.Exclude
    private Set<Genre> genres = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id")
    private Director director;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;
        return Float.compare(rating, movie.rating) == 0 && runtime == movie.runtime && Objects.equals(id, movie.id) && Objects.equals(title, movie.title) && Objects.equals(synopsis, movie.synopsis) && Objects.equals(url, movie.url) && Objects.equals(image, movie.image) && Objects.equals(releaseDate, movie.releaseDate) && Objects.equals(createdAt, movie.createdAt) && Objects.equals(updatedAt, movie.updatedAt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(title);
        result = 31 * result + Objects.hashCode(synopsis);
        result = 31 * result + Objects.hashCode(url);
        result = 31 * result + Objects.hashCode(image);
        result = 31 * result + Objects.hashCode(releaseDate);
        result = 31 * result + Float.hashCode(rating);
        result = 31 * result + runtime;
        result = 31 * result + Objects.hashCode(createdAt);
        result = 31 * result + Objects.hashCode(updatedAt);
        return result;
    }
}