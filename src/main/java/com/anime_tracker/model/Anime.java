package com.anime_tracker.model;


import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Anime {
    @EqualsAndHashCode.Include
    private int id;
    private String titleEnglish;
    private String titleRomaji;
    private String descriptionEnglish;
    private String coverImage;
    private List<String> genres;
    private List<String> tags;
    private String studios;
    private String source;
    private int year;
    private int averageScore;
    private String author;
    private String mainDirector;
    private int numberOfEpisodes;
    private int episodeCount;
    private Status status;
}
