package com.gameexpert.player.controller;

import com.gameexpert.player.dto.CreatePlayerRequest;
import com.gameexpert.player.entity.Player;
import com.gameexpert.player.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    // TODO Lv 3: API 명세에 맞게 요청을 매핑하고, 검증한 요청으로 등록 서비스를 호출한 뒤 성공 응답을 반환합니다.
    @PostMapping("/players")
    public ResponseEntity<Void> create(@Valid @RequestBody CreatePlayerRequest request) {
        playerService.createPlayer(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // player 확인용 임시 메서드
    @GetMapping("/players")
    public ResponseEntity<List<CreatePlayerRequest>> getAllPlayers() {
        List<CreatePlayerRequest> players = playerService.findAll();
        return ResponseEntity.ok(players);
    }
}