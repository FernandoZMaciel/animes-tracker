package com.anime_tracker.mapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class AnimeResponse {
    private Data data;

    @Getter
    @Setter
    public static class Data {
        @JsonProperty("Page")
        private Page page;
    }

    @Getter
    @Setter
    public static class Page {
        private List<Media> media;
    }

    @Setter
    @Getter
    public static class Media {
        private int id;
        private Title title;
        private String description;
        private CoverImage coverImage;
        private List<String> genres;
        @JsonProperty("tags")
        private List<Tag> tags;
        @JsonProperty("staff")
        private StaffData staff;
        private Studio studios;
        private String source;
        private int averageScore;
    }

    @Setter
    @Getter
    public static class StaffData {
        @JsonProperty("edges")
        private List<Edge> edges;
    }

    @Setter
    @Getter
    public static class Edge {
        @JsonProperty("node")
        private StaffNode node;
        private String role;

    }

    @Setter
    @Getter
    public static class StaffNode {
        private Name name;
    }

    @Setter
    @Getter
    public static class Name {
        private String full;
    }

    @Setter
    @Getter
    public static class Tag {
        private String name;
    }

    @Setter
    @Getter
    public static class Title {
        private String romaji;
        private String english;
        @JsonProperty("native")
        private String nativeTitle;
    }

    @Setter
    @Getter
    public static class CoverImage {
        private String large;
    }

    @Setter
    @Getter
    public static class Studio{
        @JsonProperty("edges")
        private List<StudioEdge> studioEdge;
    }

    @Setter
    @Getter
    public static class StudioEdge {
        @JsonProperty("isMain")
        private boolean isMain;
        private StudioNode node;
    }

    @Setter
    @Getter
    public static class StudioNode {
        private String name;
    }
}