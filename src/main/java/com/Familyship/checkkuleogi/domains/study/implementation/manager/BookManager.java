package com.Familyship.checkkuleogi.domains.study.implementation.manager;

import com.Familyship.checkkuleogi.domains.child.domain.Child;
import com.Familyship.checkkuleogi.domains.like.domain.BookLike;
import com.Familyship.checkkuleogi.domains.like.domain.repository.BookLikeRepository;
import com.Familyship.checkkuleogi.domains.study.domain.BookT;
import com.Familyship.checkkuleogi.domains.study.dto.request.FeedbackOnBookReq;
import com.Familyship.checkkuleogi.domains.study.dto.request.ReadBookReq;
import com.Familyship.checkkuleogi.domains.study.dto.request.RegisterBookReq;
import com.Familyship.checkkuleogi.domains.study.dto.request.UpdateBookReq;
import com.Familyship.checkkuleogi.domains.study.exception.BookException;
import com.Familyship.checkkuleogi.domains.study.exception.BookExceptionType;
import com.Familyship.checkkuleogi.domains.study.repository.BookRepositoryT;
import com.Familyship.checkkuleogi.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookManager { //Implement Layer 계층

    private final BookRepositoryT bookRepositoryT;
    private final BookLikeRepository bookLikeRepository;

    //Implement Layer 계층에 존재하는 다른 협업 클래스들과 의존 가능
    private final ChildManager childManager;
    private final JwtProvider jwtProvider;

    public BookT registerBook(RegisterBookReq req) {
        BookT bookT = BookT.builder()
                .title(req.title())
                .author(req.author())
                .publisher(req.publisher())
                .content(req.content())
                .state(req.state())
                .build();
        return bookRepositoryT.save(bookT);
    }

    public void updateBook(Long bookIdx, UpdateBookReq req) {
        BookT bookT = this.getBook(bookIdx);
        bookT.update(req.title(), req.author(), req.publisher(), req.content(), req.state());
    }

    public void deleteBook(Long bookIdx) {
        BookT bookT = this.getBook(bookIdx);
        bookRepositoryT.delete(bookT);
    }

    public String readBook(ReadBookReq req) {
        return this.pagingBook(req.bookIdx(), req.startPage(), req.endPage());
    }

    public String pagingBook(Long bookIdx, int startPage, int endPage) {
        BookT bookT = this.getBook(bookIdx);
        return bookT.getContent().substring(startPage, endPage);
    }

    public BookT getBook(Long id) {
        Optional<BookT> book = bookRepositoryT.findById(id);
        return book
                .orElseThrow(() -> new BookException(BookExceptionType.BOOK_NOT_FOUNT_EXCEPTION));
    }

    public BookLike feedbackOnBook(String token, FeedbackOnBookReq req) {
        Long childIdx = Long.valueOf(jwtProvider.getUserIdFromToken(token));

        Child child = childManager.getChild(childIdx);
        BookT bookT = this.getBook(req.bookIdx());
        boolean likeDislike = req.isLike();

        BookLike bookLike = BookLike.builder()
                .child(child)
                //.bookT(bookT)  //기존 bookT 클래스 타입과 충돌
                .likedislike(likeDislike)
                .build();
        return bookLikeRepository.save(bookLike);
    }
}
