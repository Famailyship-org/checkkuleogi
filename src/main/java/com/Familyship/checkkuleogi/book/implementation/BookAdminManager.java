package com.Familyship.checkkuleogi.book.implementation;

import com.Familyship.checkkuleogi.book.jpa.repository.BookRepository;
import com.Familyship.checkkuleogi.book.jpa.Book;
import com.Familyship.checkkuleogi.book.jpa.BookMBTI;
import com.Familyship.checkkuleogi.book.dto.request.BookMBTIRequest;
import com.Familyship.checkkuleogi.book.dto.request.BookUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookAdminManager {

    private final GPTManager gpt;
    private final BookRepository bookRepository;
    private final BookCacheManager bookCacheManager;

    public Book createBook(BookMBTIRequest req) {
        Integer[] mbti = gpt.getMbtiFromLLM(req);

        // BookMBTI 저장
        BookMBTI bookMBTI = BookMBTI.builder()
                .mbtiE(mbti[0])
                .mbtiS(mbti[1])
                .mbtiT(mbti[2])
                .mbtiJ(mbti[3])
                .build();

        // Book 저장
        Book book = Book.builder()
                .title(req.getTitle())
                .author(req.getAuthor())
                .publisher(req.getPublisher())
                .summary(req.getSummary())
                .content(req.getContent())
                .mbti(gpt.calculateMBTI(mbti[0], mbti[1], mbti[2], mbti[3]))
                .bookMBTI(bookMBTI)
                .build();

        return bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        //캐시에 삭제 반영
        bookCacheManager.deleteBookInCache(bookId);
        bookRepository.deleteById(bookId);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book updateBook(Long id, BookUpdateRequest request) {
        // 기존 책 조회
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("책을 찾을 수 없습니다."));

        // 수정된 책 객체 생성
        Book updatedBook = Book.builder()
                .idx(existingBook.getIdx()) // 기존 ID 유지
                .title(request.title())
                .author(request.author())
                .publisher(request.publisher())
                .summary(request.summary())
                .content(existingBook.getContent()) // 기존 content 유지
                .mbti(existingBook.getMbti()) // 기존 MBTI 유지
                .bookMBTI(existingBook.getBookMBTI()) // 기존 BookMBTI 유지
                .build();

        // DB에 수정된 책 저장
        Book savedBook = bookRepository.save(updatedBook);

        // 캐시에 업데이트 반영
        bookCacheManager.updateBookInCache(savedBook);

        return savedBook;
    }
}
