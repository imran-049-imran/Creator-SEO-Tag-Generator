package com.YouTubeTools.Service;

import com.YouTubeTools.Model.SearchVideo;
import com.YouTubeTools.Model.Video;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class YouTubeService {

    @Value("${youtube.api.key}")
    private String apiKey;

    @Value("${youtube.api.search-url}")
    private String searchUrl;

    @Value("${youtube.api.videos-url}")
    private String videosUrl;

    @Value("${youtube.api.max-related-videos}")
    private int maxRelatedVideos;

    /**
     * Searches YouTube for videos matching the given title and returns primary + related video metadata.
     */
    public SearchVideo searchVideos(String videoTitle) {
        RestTemplate restTemplate = new RestTemplate();

        String searchQuery = UriComponentsBuilder.fromHttpUrl(searchUrl)
                .queryParam("part", "snippet")
                .queryParam("q", videoTitle)
                .queryParam("type", "video")
                .queryParam("maxResults", maxRelatedVideos + 1)
                .queryParam("key", apiKey)
                .toUriString();

        Map<String, Object> searchResponse = restTemplate.getForObject(searchQuery, Map.class);
        List<Map<String, Object>> items = (List<Map<String, Object>>) searchResponse.get("items");

        if (items == null || items.isEmpty()) {
            throw new RuntimeException("No videos found for: " + videoTitle);
        }

        List<String> videoIds = new ArrayList<>();
        for (Map<String, Object> item : items) {
            Map<String, Object> idMap = (Map<String, Object>) item.get("id");
            if (idMap != null && idMap.get("videoId") != null) {
                videoIds.add((String) idMap.get("videoId"));
            }
        }

        if (videoIds.isEmpty()) {
            throw new RuntimeException("No valid video IDs found for: " + videoTitle);
        }

        return fetchVideoDetails(videoIds);
    }

    /**
     * Fetches detailed metadata for a list of video IDs.
     */
    private SearchVideo fetchVideoDetails(List<String> videoIds) {
        RestTemplate restTemplate = new RestTemplate();

        String videoDetailsQuery = UriComponentsBuilder.fromHttpUrl(videosUrl)
                .queryParam("part", "snippet")
                .queryParam("id", String.join(",", videoIds))
                .queryParam("key", apiKey)
                .toUriString();

        Map<String, Object> detailsResponse = restTemplate.getForObject(videoDetailsQuery, Map.class);
        List<Map<String, Object>> detailItems = (List<Map<String, Object>>) detailsResponse.get("items");

        if (detailItems == null || detailItems.isEmpty()) {
            throw new RuntimeException("No video details found for IDs: " + videoIds);
        }

        List<Video> videos = new ArrayList<>();
        for (Map<String, Object> item : detailItems) {
            Map<String, Object> snippet = (Map<String, Object>) item.get("snippet");
            String id = (String) item.get("id");

            if (snippet == null || id == null) continue;

            Video video = Video.builder()
                    .id(id)
                    .title((String) snippet.getOrDefault("title", "Untitled"))
                    .channelTitle((String) snippet.getOrDefault("channelTitle", "Unknown Channel"))
                    .description((String) snippet.getOrDefault("description", ""))
                    .publishedAt((String) snippet.getOrDefault("publishedAt", ""))
                    .thumbnailUrl("https://img.youtube.com/vi/" + id + "/maxresdefault.jpg")
                    .tags((List<String>) snippet.getOrDefault("tags", new ArrayList<>()))
                    .build();

            videos.add(video);
        }

        if (videos.isEmpty()) {
            throw new RuntimeException("No valid video metadata found.");
        }

        Video primaryVideo = videos.get(0);
        List<Video> relatedVideos = videos.size() > 1
                ? videos.subList(1, Math.min(videos.size(), maxRelatedVideos + 1))
                : Collections.emptyList();

        return SearchVideo.builder()
                .primaryVideo(primaryVideo)
                .relatedVideos(relatedVideos)
                .build();
    }

    /**
     * Fetches metadata for a single video ID.
     */
    public Video fetchVideoDetails(String videoId) {
        SearchVideo result = fetchVideoDetails(Collections.singletonList(videoId));
        return result.getPrimaryVideo();
    }
}
