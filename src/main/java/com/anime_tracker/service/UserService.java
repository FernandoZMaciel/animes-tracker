package com.anime_tracker.service;

import com.anime_tracker.model.Anime;
import com.anime_tracker.model.User;
import com.anime_tracker.repository.UserRepository;
import com.anime_tracker.utils.AnimeUtil;
import com.anime_tracker.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnimeService animeService;


    public User addUser(User user) {
        user.setId(UUID.randomUUID().toString().split("-")[0]);
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).get();
    }

    public List<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User updateUser(User requestUser) {
        User existingUser = userRepository.findById(requestUser.getId()).get();
        existingUser.setUsername(requestUser.getUsername());
        existingUser.setPassword(requestUser.getPassword());
        existingUser.setEmail(requestUser.getEmail());
        Set<Anime> watchedAnimes = new HashSet<>(requestUser.getWatchedAnimes());
        existingUser.setWatchedAnimes(new ArrayList<>(watchedAnimes));
        return userRepository.save(existingUser);
    }

    public String deleteUser(String userId) {
        userRepository.deleteById(userId);
        return "User deleted: " + userId;
    }

    public List<Anime> getRecommendation(String userId) {
        User user = getUserById(userId);
        Map<String, Integer> mostViewedGenres = UserUtil.getMostViewedGenres(user);
        Map<String, Integer> mostViewedTags = UserUtil.getMostViewedTags(user);

        int tolerance = 3;

        List<Anime> animeList = new ArrayList<>();
        while (animeList.size() < 5 && tolerance > 0) {
            String genres = UserUtil.querryToString(mostViewedGenres, tolerance);
            String tags = UserUtil.querryToString(mostViewedTags, tolerance);
            animeList = AnimeUtil.ResponseAnimeToAnime(animeService.getAnimesListByGenresAndThemes(genres, tags));
            animeList = AnimeUtil.removeRepeatedAnimes(user, animeList);
            tolerance--;
        }
        return animeList.subList(0, 5);
    }
}
