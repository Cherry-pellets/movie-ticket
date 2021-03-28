package ncu.jinxiu.entity.Vo;

import ncu.jinxiu.entity.AdminRole;
import ncu.jinxiu.entity.Cinema;
import ncu.jinxiu.entity.HallType;
import ncu.jinxiu.entity.Movie;

import java.util.List;

public class AdminOptions {
    private List<HallType> hallTypes;
    private List<Cinema> cinemas;
    private List<Movie> movies;
    private List<AdminRole> roles;

    public List<AdminRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AdminRole> roles) {
        this.roles = roles;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<HallType> getHallTypes() {
        return hallTypes;
    }

    public void setHallTypes(List<HallType> hallTypes) {
        this.hallTypes = hallTypes;
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(List<Cinema> cinemas) {
        this.cinemas = cinemas;
    }
}
