package com.YouTubeTools.Controller;

import com.YouTubeTools.Service.ThumbnailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

@Controller
public class ThumbnailController {

    @Autowired
    private ThumbnailService service;

    @GetMapping("/thumbnail")
    public String getThumbnailPage() {
        return "thumbnails";
    }

    @PostMapping("/get-thumbnail")
    public String showThumbnail(@RequestParam("videoUrlOrId") String videoUrlOrId, Model model) {

        String videoId = service.extractVideoId(videoUrlOrId);

        if (videoId == null || videoId.isEmpty()) {
            model.addAttribute("error", "Invalid YouTube URL or ID");
            return "thumbnails";
        }

        String thumbnailUrl = "https://img.youtube.com/vi/" + videoId + "/maxresdefault.jpg";

        model.addAttribute("thumbnailUrl", thumbnailUrl);
        model.addAttribute("videoId", videoId);

        return "thumbnails";
    }


    // ⭐ Backend download — works 100%
    @GetMapping("/download-thumbnail")
    public ResponseEntity<byte[]> downloadThumbnail(
            @RequestParam("url") String url,
            @RequestParam("filename") String filename) {

        try {
            URL imageUrl = new URL(url);

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            try (InputStream input = imageUrl.openStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            }

            byte[] imageBytes = output.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentDisposition(ContentDisposition.attachment().filename(filename).build());

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
