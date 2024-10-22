package com.Familyship.checkkuleogi.domains.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Familyship.checkkuleogi.domains.book.domain.Book;
import com.Familyship.checkkuleogi.domains.book.domain.BookMBTI;
import com.Familyship.checkkuleogi.domains.book.domain.repository.BookRepository;
import com.Familyship.checkkuleogi.domains.book.dto.BookMBTIRequest;
import com.Familyship.checkkuleogi.domains.book.dto.BookMBTIResponse;
import com.Familyship.checkkuleogi.domains.book.dto.ChatGPTRequest;
import com.Familyship.checkkuleogi.domains.book.dto.ChatGPTResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private RestTemplate template;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookMBTIResponse createBook(BookMBTIRequest request) {
        // ChatGPT API 호출을 위한 메시지 구성
        String prompt = request.getSummary()
                        +"의 줄거리를 가진 책을 좋아하는 어린이에 대한 MBTI를 적어줘\n"
                        +"E(외향형) ~~% I(내향형) ~~%\n"
                        +"S(감각형) ~~% N(직관형) ~~%\n"
                        +"T(사고형) ~~% F(감정형) ~~%\n"
                        +"J(판단형) ~~% P(인식형) ~~%\n"
                        +"이러한 퍼센트 형식으로 알려줘.\n"
                        +"50%는 절대 주지마!";

        ChatGPTRequest chatGPTRequest = new ChatGPTRequest(model, prompt);

        // ChatGPT API 호출
        ChatGPTResponse response = template.postForObject(apiURL, chatGPTRequest, ChatGPTResponse.class);

        if (response == null || response.getChoices().isEmpty()) {
            throw new RuntimeException("ChatGPT API 응답이 없습니다.");
        }

        // API 응답에서 MBTI 성향 추출
        String mbtiContent = response.getChoices().get(0).getMessage().getContent();
        System.out.println(mbtiContent);

        // 예시: "E 30%, S 50%, T 20%, J 10%"
        String[] mbtiValues = mbtiContent.split("%");
        System.out.println(Arrays.toString(mbtiValues));
        Integer mbtiE = Integer.parseInt(mbtiValues[0].trim().split(" ")[1]);
        Integer mbtiS = Integer.parseInt(mbtiValues[2].trim().split(" ")[1]);
        Integer mbtiT = Integer.parseInt(mbtiValues[4].trim().split(" ")[1]);
        Integer mbtiJ = Integer.parseInt(mbtiValues[6].trim().split(" ")[1]);
        System.out.println(mbtiE +" "+mbtiS+" "+mbtiT+" "+mbtiJ);

        BookMBTI bookMBTI = BookMBTI.builder()
                .mbtiE(mbtiE)
                .mbtiS(mbtiS)
                .mbtiT(mbtiT)
                .mbtiJ(mbtiJ)
                .build();

        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .publisher(request.getPublisher())
                .summary(request.getSummary())
                .content(request.getContent())
                .bookMBTI(bookMBTI)
                .build();

        bookRepository.save(book);

        // MBTI 성향 응답 반환
        return BookMBTIResponse.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .publisher(request.getPublisher())
                .summary(request.getSummary())
                .mbti(calculateMBTI(mbtiE, mbtiS, mbtiT, mbtiJ))
                .build();
    }

    private String calculateMBTI(Integer mbtiE, Integer mbtiS, Integer mbtiT, Integer mbtiJ) {
        StringBuilder sb = new StringBuilder();
        sb.append(mbtiE>50 ? "E" : "I");
        sb.append(mbtiS>50 ? "S" : "N");
        sb.append(mbtiT>50 ? "T" : "F");
        sb.append(mbtiJ>50 ? "J" : "P");
        return sb.toString();
    }
}
