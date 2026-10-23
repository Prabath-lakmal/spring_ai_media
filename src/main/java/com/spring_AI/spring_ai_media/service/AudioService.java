package com.spring_AI.spring_ai_media.service;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaTypeFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;

import java.util.List;

@Service
public class AudioService {

    private static final String DEFAULT_INSTRUCTIONS = "Transcribe the attached audio.\nReturn only the transcription text, without any additional commentary.\n";

    private final ChatModel chatModel;

    public AudioService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String convertAudioToText(Resource inputAudio) {
        return transcribe(inputAudio, DEFAULT_INSTRUCTIONS);
    }

    public String convertAudioToTextWithOption(Resource inputAudio) {
        String instructions = "Transcribe the attached audio in English.\nUse \"Spring Boot\" as contextual vocabulary.\nReturn only the transcription text, without any additional commentary.\n";
        return transcribe(inputAudio, instructions);
    }

    private String transcribe(Resource audioResource, String instructions) {
        MimeType mimeType = MediaTypeFactory.getMediaType(audioResource)
                .orElseThrow(() -> new IllegalArgumentException("Could not determine media type for " + audioResource.getFilename()));

        Media media = Media.builder()
                .mimeType(mimeType)
                .data(audioResource)
                .build();

        UserMessage userMessage = UserMessage.builder()
                .text(instructions)
                .media(List.of(media))
                .build();

        ChatResponse response = chatModel.call(new Prompt(userMessage));
        return response.getResult().getOutput().getText();
    }
}
