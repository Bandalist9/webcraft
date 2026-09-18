package com.gameexpert.chat.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gameexpert.chat.dto.ChatMessageResponse;
import com.gameexpert.chat.service.RecentChatQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WorldChatController {

    private final RecentChatQueryService chatService;

    // TODO Lv 6: API 명세에 맞는 요청 매핑과 응답을 구현합니다.
    @GetMapping("/worlds/{worldId}/chats")
    public ResponseEntity<List<ChatMessageResponse>> chats(@PathVariable("worldId") Long worldId,@RequestParam(name = "limit", defaultValue = "50") int limit) {
        return ResponseEntity.ok(chatService.getRecentMessages(worldId, limit));
    }
}
