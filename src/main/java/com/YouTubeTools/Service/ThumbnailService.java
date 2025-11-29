package com.YouTubeTools.Service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ThumbnailService {

    // Precompiled regex patterns for performance
    private static final Pattern[] YOUTUBE_PATTERNS = new Pattern[]{
        Pattern.compile("(?:https?:\\/\\/)?(?:www\\.)?youtube\\.com\\/watch\\?v=([a-zA-Z0-9_-]{11})"),
        Pattern.compile("(?:https?:\\/\\/)?(?:www\\.)?youtu\\.be\\/([a-zA-Z0-9_-]{11})"),
        Pattern.compile("(?:https?:\\/\\/)?(?:www\\.)?youtube\\.com\\/embed\\/([a-zA-Z0-9_-]{11})")
    };

    /**
     * Extracts the YouTube video ID from a full URL or raw ID.
     * Supports watch?v=, youtu.be/, and embed/ formats.
     *
     * @param input YouTube video URL or ID
     * @return 11-character video ID or null if invalid
     */
    public String extractVideoId(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }

        input = input.trim();

        // Case 1: Direct video ID
        if (input.matches("^[a-zA-Z0-9_-]{11}$")) {
            return input;
        }

        // Case 2: Match known URL formats
        for (Pattern pattern : YOUTUBE_PATTERNS) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }

        // Case 3: No match
        return null;
    }
}
