package com.anime_tracker.controller;

import com.anime_tracker.mapper.AnimeResponse;
import com.anime_tracker.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animes")
public class AnimeController {
    @Autowired
    AnimeService animeService;

    @PostMapping
    public AnimeResponse getAnimesByGenresAndThemes(){
        return animeService.getAnimesListByGenresAndThemes();
    }
}
