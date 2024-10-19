package com.anime_tracker.controller;

import com.anime_tracker.model.Anime;
import com.anime_tracker.model.User;
import com.anime_tracker.service.AnimeService;
import com.anime_tracker.service.UserService;
import com.anime_tracker.utils.AnimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://anime-tracker-vert.vercel.app/")

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AnimeService animeService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@RequestBody User requestUser) {
        return userService.updateUser(requestUser);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteUserById(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/animes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Anime> getRecommendedAnimes(@PathVariable String id) {
        User user = userService.getUserById(id);
        List<Anime> animeList = AnimeUtil.ResponseAnimeToAnime(animeService.getAnimesListByGenresAndThemes());
        return AnimeUtil.removeRepeatedAnimes(user, animeList);
    }

    @GetMapping("/recommendaion/{id}")
    public List<Anime> getRecommendationByUserId(@PathVariable String id) {
        return userService.getRecommendation(id);
    }

}
