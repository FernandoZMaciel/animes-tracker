package com.anime_tracker.utils;

import com.anime_tracker.mapper.AnimeResponse;
import com.anime_tracker.model.Anime;
import com.anime_tracker.model.Status;
import com.anime_tracker.model.User;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Log4j2
public class AnimeUtil {

    public static List<Anime> ResponseAnimeToAnime(AnimeResponse animeResponses) {
        List<Anime> animeList = new ArrayList<>();

        for (AnimeResponse.Media animeResponse : animeResponses.getData().getPage().getMedia()) {
            Anime anime = Anime.builder()
                    .id(animeResponse.getId())
                    .titleEnglish(animeResponse.getTitle().getEnglish())
                    .titleRomaji(animeResponse.getTitle().getRomaji())
                    .descriptionEnglish(animeResponse.getDescription())
                    .coverImage(animeResponse.getCoverImage().getLarge())
                    .studios(animeResponse.getStudios().getStudioEdge().get(0).getNode().getName())
                    .source(animeResponse.getSource())
                    .year(animeResponse.getSeasonYear())
                    .averageScore(animeResponse.getAverageScore())
                    .numberOfEpisodes(animeResponse.getEpisodes())
                    .episodeCount(0)
                    .status(Status.PLAN_TO_WATCH)
                    .build();

            List<String> responseGenres = animeResponse.getGenres();
            List<String> animeGenres = new ArrayList<>(responseGenres.subList(0, Math.min(responseGenres.size(), 4)));
            anime.setGenres(animeGenres);

            List<String> responseTags = new ArrayList<>();
            List<AnimeResponse.Tag> tags = animeResponse.getTags().subList(0, Math.min(animeResponse.getTags().size(), 5));
            for (AnimeResponse.Tag tag : tags) {
                responseTags.add(tag.getName());
            }
            anime.setTags(responseTags);

            List<AnimeResponse.Edge> staffEdges = animeResponse.getStaff().getEdges();
            for (AnimeResponse.Edge staffEdge : staffEdges) {
                if (staffEdge.getRole().equals("Original Creator")) {
                    anime.setAuthor(staffEdge.getNode().getName().getFull());
                }
                if (staffEdge.getRole().equals("Director")) {
                    anime.setMainDirector(staffEdge.getNode().getName().getFull());
                }
            }
            animeList.add(anime);
        }
        List<Anime> filteredAnimes = removeSequencesAndBadAnime(animeList);
        return filteredAnimes;
    }

    public static List<Anime> removeSequencesAndBadAnime(List<Anime> animeList) {
        String[] exclusionTerms = {"Arc", "Second Season", "Third Season", "Final Season", "Part", "Final Act", "OVA", "Special", "Season 2", "Season 3", "S2", "S3"};
        int cutOffScore = 65;
        List<Anime> filteredAnimes = new ArrayList<>();
        for (Anime anime : animeList) {
            boolean exclude = false;
            if (anime.getAverageScore() <= cutOffScore) {
                exclude = true;
            } else {
                for (String term : exclusionTerms) {
                    if (anime.getTitleEnglish().contains(term)) {
                        exclude = true;
                        break;
                    }
                }
            }
            if (!exclude) {
                filteredAnimes.add(anime);
            }
        }
        return filteredAnimes;
    }

    public static List<Anime> removeRepeatedAnimes(User user, List<Anime> animeList) {
        animeList.removeIf(anime -> user.getWatchedAnimes().contains(anime));
        return animeList;
    }
}
