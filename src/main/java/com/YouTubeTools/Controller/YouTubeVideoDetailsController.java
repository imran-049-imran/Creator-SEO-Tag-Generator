package com.YouTubeTools.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // ✅ Correct import
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.YouTubeTools.Model.Video;
import com.YouTubeTools.Service.ThumbnailService;
import com.YouTubeTools.Service.YouTubeService;

@Controller
@RequestMapping("/youtube")
public class YouTubeVideoDetailsController {

    @Autowired
    private YouTubeService youTubeService;

    @Autowired
    private ThumbnailService thumbnailService;

    @PostMapping("/video-details")
    public String getVideoDetails(@RequestParam("videoUrlOrId") String videoUrlOrId, Model model) {
        String videoId = thumbnailService.extractVideoId(videoUrlOrId);

        if (videoId == null) {
            model.addAttribute("error", "Invalid YouTube URL or ID");
            return "video-details";
        }

        try {
            Video videoDetails = youTubeService.fetchVideoDetails(videoId);
            model.addAttribute("videoDetails", videoDetails);
            model.addAttribute("videoId", videoId);
            model.addAttribute("videoUrlOrId", videoUrlOrId);
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching video details: " + e.getMessage());
        }

        return "video-details"; 
    }
}
