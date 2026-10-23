package com.spring_AI.spring_ai_media.service;

import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.ai.google.genai.GoogleGenAiAudioTranscriptionOptions;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class AudioService {

    private final TranscriptionModel transcriptionModel;

    public AudioService(TranscriptionModel transcriptionModel){
        this.transcriptionModel = transcriptionModel;
    }

    public String convertAudioToText(Resource inputAudio) {
//        speech to text
        return transcriptionModel.transcribe(inputAudio);
    }

    public String convertAudioToTextWithOption(Resource inputAudio) {
        return transcriptionModel.transcribe(inputAudio, GoogleGenAiAudioTranscriptionOptions.builder()
                .language("en")
                .temperature(0.7f)
                .prompt("Spring boot")
                .build());
    }
}
