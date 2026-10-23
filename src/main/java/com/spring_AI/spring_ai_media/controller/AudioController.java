package com.spring_AI.spring_ai_media.controller;

import com.spring_AI.spring_ai_media.service.AudioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/audio")

public class AudioController {

    private final AudioService audioService;

    public AudioController(AudioService audioService){
        this.audioService = audioService;
    }

    //handler method:

    //resource folder

    @PostMapping("/transcript")
    public ResponseEntity<String> transcriptAudio(@Value("${classpath:sample.mp4}")Resource inputAudio){

    }

}
