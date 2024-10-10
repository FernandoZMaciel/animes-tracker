package com.anime_tracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Anime {
    private String id;
    private String name;
    private int episodes;
    private int watchedEpisodes;
    private List<Genre> genres;
    private List<Theme> themes;
}
