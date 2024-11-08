package com.noob.moviesapi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "actors")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class Actor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    private String briefly;
    private String image;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToMany(mappedBy = "actors")
    @ToString.Exclude
    private Set<Movie> movies = new HashSet<>();

    public void addMovie(Movie movie){
        this.movies.add(movie);
        movie.getActors().add(this);
    }

    public void removeMovie(Movie movie){
        this.movies.remove(movie);
        movie.getActors().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;
        return Objects.equals(id, actor.id) && Objects.equals(name, actor.name) && Objects.equals(briefly, actor.briefly) && Objects.equals(image, actor.image) && Objects.equals(createdAt, actor.createdAt) && Objects.equals(updatedAt, actor.updatedAt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(briefly);
        result = 31 * result + Objects.hashCode(image);
        result = 31 * result + Objects.hashCode(createdAt);
        result = 31 * result + Objects.hashCode(updatedAt);
        return result;
    }
}