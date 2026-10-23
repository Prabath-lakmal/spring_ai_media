package com.spring_AI.spring_ai_media.controller;

import com.spring_AI.spring_ai_media.service.AudioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/audio")

public class AudioController {

    private final AudioService audioService;

    public AudioController(AudioService audioService){
        this.audioService = audioService;
    }


    public ResponseEntity<String> speechToText(@RequestParam("audioFile")MultipartFile audioFile){
//        spring ai --->
        String responseText = audioService.convertAudioToText(audioFile.getResource());
        return ResponseEntity.ok(responseText);
    }

    //handler method:
    //resource folder
    @PostMapping("/transcript")
    public ResponseEntity<String> transcriptAudio(@Value("${classpath:sample.mp4}")Resource inputAudio){
//        spring ai --->
        String responseText = audioService.convertAudioToText(inputAudio);
        return ResponseEntity.ok(responseText);
    }

    @PostMapping("/transcript-with-option")
    public ResponseEntity<String> transcriptAudioWithOption(@Value("${classpath:sample.m4a}") Resource inputAudio){
//        spring ai --->
        String responseText = audioService.convertAudioToTextWithOption(inputAudio);
        return ResponseEntity.ok(responseText);
    }
}
