package com.anime_tracker.utils;

import com.anime_tracker.model.Anime;
import com.anime_tracker.model.User;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class UserUtil {

    public static Map<String, Integer> getMostViewedGenres(User user){
        Map<String, Integer> viewedGenres = new HashMap<>();

        for (Anime anime : user.getWatchedAnimes()) {
            for (String genre : anime.getGenres()) {
                viewedGenres.put(genre, viewedGenres.getOrDefault(genre, 0) + 1);
            }
        }

        Map<String, Integer> mostViewedGenres = viewedGenres.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        return mostViewedGenres;
    }

    public static Map<String, Integer> getMostViewedTags(User user){
        Map<String, Integer> viewedGenres = new HashMap<>();

        for (Anime anime : user.getWatchedAnimes()) {
            for (String tag : anime.getTags()) {
                viewedGenres.put(tag, viewedGenres.getOrDefault(tag, 0) + 1);
            }
        }

        return viewedGenres.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

}
