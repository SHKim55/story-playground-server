package com.softgallery.story_playground_server.dto.story;

import com.softgallery.story_playground_server.service.story.Visibility;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

@Repository
@Getter
public class StoryReadingDTO {
    private Long storyId;
    private String title = "No Title";
    private String username;
    private String topic;
    private Boolean isCompleted;
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private LocalDateTime modifiedDate;
    private Visibility visibility;
    private Long likeNum;
    private Long dislikeNum;
    private Boolean isMine;
    private Boolean isEvaluated;

    public StoryReadingDTO() {};

    public StoryReadingDTO(Long storyId, String title, String username, String topic, Boolean isCompleted, LocalDateTime modifiedDate,
                           Visibility visibility, Long likeNum, Long dislikeNum, Boolean isMine, Boolean isEvaluated) {
        this.storyId = storyId;
        this.title = title;
        this.username = username;
        this.topic = topic;
        this.isCompleted = isCompleted;
        this.modifiedDate = modifiedDate;
        this.visibility = visibility;
        this.likeNum = likeNum;
        this.dislikeNum = dislikeNum;
        this.isMine = isMine;
        this.isEvaluated = isEvaluated;
    }
}
