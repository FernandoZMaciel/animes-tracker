package com.anime_tracker.utils;

import com.anime_tracker.model.Anime;
import com.anime_tracker.model.User;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class UserUtil {

    public static Map<String, Integer> getMostViewedGenres(User user) {
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

    public static Map<String, Integer> getMostViewedTags(User user) {
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

    public static String querryToString(Map<String, Integer> querryField, int tolerance) {
        // Limita a quantidade de elementos a ser retornada
        Map<String, Integer> limitedQueryField = querryField.entrySet()
                .stream()
                .limit(tolerance)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        // Constrói a string a partir do mapa limitado
        StringBuilder toString = new StringBuilder("[");
        for (String name : limitedQueryField.keySet()) {
            toString.append("\\\"").append(name).append("\\\", ");
        }

        // Remove a última vírgula e espaço, se necessário, e fecha o array
        if (limitedQueryField.size() > 0) {
            toString.setLength(toString.length() - 2); // remove a última vírgula e espaço
        }
        toString.append("]");

        return toString.toString();
    }

}
