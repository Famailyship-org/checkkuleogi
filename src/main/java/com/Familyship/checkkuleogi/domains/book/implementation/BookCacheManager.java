package com.Familyship.checkkuleogi.domains.book.implementation;

import com.Familyship.checkkuleogi.domains.book.dto.BookCachingItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookCacheManager {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper; // Jackson ObjectMapper

    public Optional<List<BookCachingItem>> findBookListBy(Long childIdx) {
        String listKey = "child:" + childIdx + ":recently_viewed_books";
        List<String> cachedItems = redisTemplate.opsForList().range(listKey, 0, -1);

        if (cachedItems == null || cachedItems.isEmpty()) {
            return Optional.empty(); // 리스트가 비어있으면 빈 옵셔널 반환, 휴먼 유저임
        }

        List<BookCachingItem> books = cachedItems.stream()
                .map(this::deserializeBookCachingItem)
                .collect(Collectors.toList());

        return Optional.of(books);
    }

    public void cacheRecentlyViewedBook(BookCachingItem bookCachingItem, Long childIdx) {
        String listKey = "child:" + childIdx + ":recently_viewed_books";
        String newBookJson;
        // DTO를 JSON으로 직렬화하여 리스트에 저장
        try {
            // DTO를 JSON으로 직렬화
            newBookJson = objectMapper.writeValueAsString(bookCachingItem);
            // 리스트에 이미 해당 책이 존재하는지 확인
            List<String> existingBooks = redisTemplate.opsForList().range(listKey, 0, -1);
            if (existingBooks != null && existingBooks.contains(newBookJson)) {
                return;  // 중복인 경우 추가하지 않음
            }

            String json = objectMapper.writeValueAsString(bookCachingItem);
            redisTemplate.opsForList().leftPush(listKey, json); //FIFO
            redisTemplate.opsForList().trim(listKey, 0, 9);  // 최대 10개 유지
            redisTemplate.expire(listKey, 30, TimeUnit.DAYS);  // TTL 설정
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing BookCachingItem", e);
        }
    }

    private BookCachingItem deserializeBookCachingItem(String json) {
        try {
            return objectMapper.readValue(json, BookCachingItem.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error deserializing BookCachingItem", e);
        }
    }
}
