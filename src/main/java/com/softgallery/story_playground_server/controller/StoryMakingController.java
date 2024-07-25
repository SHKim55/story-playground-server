package com.softgallery.story_playground_server.controller;

import com.softgallery.story_playground_server.dto.story.StoryDTO;
import com.softgallery.story_playground_server.dto.story.StoryReadingDTO;
import com.softgallery.story_playground_server.service.story.StoryMakingService;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/make")
public class StoryMakingController {
    private final StoryMakingService storyMakingService;

    public StoryMakingController(final StoryMakingService storyMakingService) {
        this.storyMakingService = storyMakingService;
    }

    @PostMapping("/")
    public ResponseEntity<StoryDTO> makeStory(@RequestHeader(name = "Authorization") String userToken) {
        System.out.println("userToken " + userToken);
        StoryDTO currentStory = storyMakingService.createStory(userToken);
        return ResponseEntity.ok().body(currentStory);
    }

    @GetMapping("/{storyId}")
    public ResponseEntity<List<String>> loadStory(@RequestHeader(name = "Authorization") String userToken,
                                                               @PathVariable Long storyId) {
        return ResponseEntity.ok().body(storyMakingService.findStoryByStoryId(storyId));
    }

    @PostMapping("/{storyId}/append")
    public ResponseEntity<StoryDTO> appendContent(@RequestHeader(name = "Authorization") String userToken,
                                                  @PathVariable Long storyId,
                                                  @RequestBody Map<String, String> newStory) {
        StoryDTO updatedStory = storyMakingService.addContentToStory(userToken, storyId, newStory);
        return ResponseEntity.ok().body(updatedStory);
    }

    @PostMapping("/{storyId}/resume")
    public boolean resumeStory(@PathVariable Long storyId) {
        return storyMakingService.changeStoryStateIncomplete(storyId);
    }

    @PostMapping("/{storyId}/visible/{visibility}")
    public boolean changeVisibility(@PathVariable Long storyId,
                                    @PathVariable String visibility) {
        return storyMakingService.changeStoryVisibility(storyId, visibility);
    }

    @GetMapping("/info/{id}")
    public StoryReadingDTO getStory(@PathVariable("id") Long storyId, @RequestHeader("Authorization") String token) {
        return storyMakingService.getStory(storyId, token);
    }
}
