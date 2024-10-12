package com.anime_tracker.controller;

import com.anime_tracker.mapper.AnimeResponse;
import com.anime_tracker.model.Anime;
import com.anime_tracker.service.AnimeService;
import com.anime_tracker.utils.AnimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/animes")
public class AnimeController {
    @Autowired
    AnimeService animeService;

    @PostMapping
    public AnimeResponse getAnimesByGenresAndThemes(){
        return animeService.getAnimesListByGenresAndThemes();
    }


    @PostMapping({"/animelist"})
    public List<Anime> getAnimes(){
        return AnimeUtil.ResponseAnimeToAnime(animeService.getAnimesListByGenresAndThemes());
    }
}
