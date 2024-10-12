package com.anime_tracker.service;


import com.anime_tracker.mapper.AnimeResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AnimeService {

    private OkHttpClient client = new OkHttpClient();

    public AnimeResponse getAnimesListByGenresAndThemes(){

        String url = "https://graphql.anilist.co/";
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        String query = "{\n" +
                "\"query\": \"query {\\n" +
                "  Page(page: 1, perPage: 20) {\\n" +
                "    media(type: ANIME, sort: POPULARITY_DESC, genre_in: [\\\"Action\\\", \\\"Adventure\\\", \\\"Fantasy\\\"], tag_in: [\\\"Shounen\\\", \\\"Super Power\\\"]) {\\n" +
                "      id\\n" +
                "      title {\\n" +
                "        romaji\\n" +
                "        english\\n" +
                "        native\\n" +
                "      }\\n" +
                "      description\\n" +
                "      coverImage {\\n" +
                "        large\\n" +
                "      }\\n" +
                "      genres\\n" +
                "      tags {\\n" +
                "        name\\n" +
                "      }\\n" +
                "      staff {\\n" +
                "        edges {\\n" +
                "          role\\n" +
                "          node {\\n" +
                "            name {\\n" +
                "              full\\n" +
                "            }\\n" +
                "          }\\n" +
                "        }\\n" +
                "      }\\n" +
                "      studios {\\n" +
                "        edges {\\n" +
                "          isMain\\n" +
                "          node {\\n" +
                "            name\\n" +
                "          }\\n" +
                "        }\\n" +
                "      }\\n" +
                "      source\\n" +
                "      averageScore\\n" +
                "      episodes\\n" +
                "      seasonYear\\n" +
                "    }\\n" +
                "  }\\n" +
                "}\",\n" +
                "\"variables\": {}\n" +
                "}";
        RequestBody requestBody = RequestBody.create(query, mediaType);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try  {
            Response response = client.newCall(request).execute();
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = response.body().string();
            return objectMapper.readValue(jsonData, AnimeResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
