package com.gameexpert.chat.service;

import java.time.Duration;
import java.util.List;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRateLimitService {

    private final StringRedisTemplate redisTemplate;

    public boolean allow(Long playerId) {
        // TODO Lv 19: 횟수 확인부터 최초 만료 설정까지 원자적으로 실행합니다.
        String key = "chat:limit:" + playerId;

        String lua = """
            local value = redis.call('GET', KEYS[1])
            local count = value == false and 0 or tonumber(value)

            if count >= 5 then
                return 0
            end

            local updated = redis.call('INCR', KEYS[1])

            if updated == 1 then
                redis.call('EXPIRE', KEYS[1], 10)
            end

            return 1
            """;

        RedisScript<Long> script = RedisScript.of(lua, Long.class);

        Long result = redisTemplate.execute(
                script,
                List.of(key)
        );

        return result == 1L;
    }
}
