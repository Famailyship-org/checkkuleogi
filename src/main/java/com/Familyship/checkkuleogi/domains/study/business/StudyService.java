package com.Familyship.checkkuleogi.domains.study.business;

import com.Familyship.checkkuleogi.domains.study.domain.Study;
import com.Familyship.checkkuleogi.domains.study.domain.repository.StudyRepository;
import com.Familyship.checkkuleogi.domains.study.dto.StudyRequestDTO;
import com.Familyship.checkkuleogi.domains.study.dto.StudyResponseDTO;
import com.Familyship.checkkuleogi.security.jwt.JwtProvider;
import org.springframework.stereotype.Service;

@Service
public class StudyService implements StudyUseCase {
    private final StudyRepository studyRepository; //얘가 왜 있냐 얘는 두단계 더 낮은 계층인데 매니저가 가지고 있어야지
    private final JwtProvider jwtProvider;
    // private final StudyManager
    // private final StudyDtoMapper   (Dto 생성해주는 애 dto 생성하는 로직이 서비스단에 있으면 너무 번잡해) 근데 레코드로 만들면 굿 ~

    /*
    private final StudyManager studyManager;    // 얘는 짬통 비즈니스 보다 더 낮은 계층. 얘가 DAO랑 소통함. 이렇게 함으로써 서비스 계층은 DB에 직접 접근할 일이 없도록
    아예 StudyUseCase는 가장 높은 추상화 단계 딱딱 해당 도메인이 가지는 굵직한 기능을 표현 StudyService는 각 굵직한 기능을 좀더 자세하게 적어놈. 대신, 이떄 PL 알고리즘이 거의 쓰면안됨.
    거의 자연어를 읽듯이, 메서드 시그니처를 통해 흐름을 쉽게 추상적으로 파악할 수 있어야 한다.
    그럼 이제 상세 알고리즘은 어디에? Manager에 다 짬때려져 있다. 얘는 진짜 짬통 그리고 얘가 DAO랑 소통하면서 DB에 데이터 들고 와진거 계산하고 이런 느낌쓰
    딱 책 등록이라는 서비스 기능이 있어 ! 근데 그 책이 유효한지 뭐 이딴 그냥 짜바리 작업 ~은 매니저가 하는거 근데 내가 기준을 세우는거지
    과연 이 로직은 추상화된, 즉 메서드명이 나타내는 책임에 부합하는가? 아니면 책임을 분리해주어야 하는가
    걍 서비스에는 절대 레포지토리를 호출 하지마 ~ https://geminikims.medium.com/%EC%A7%80%EC%86%8D-%EC%84%B1%EC%9E%A5-%EA%B0%80%EB%8A%A5%ED%95%9C-%EC%86%8C%ED%94%84%ED%8A%B8%EC%9B%A8%EC%96%B4%EB%A5%BC-%EB%A7%8C%EB%93%A4%EC%96%B4%EA%B0%80%EB%8A%94-%EB%B0%A9%EB%B2%95-97844c5dab63

     */

    public StudyService(StudyRepository studyRepository, JwtProvider jwtProvider) {
        this.studyRepository = studyRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public StudyResponseDTO registerStudy(String token, StudyRequestDTO dto) {
        Long user_idx = Long.valueOf(jwtProvider.getUserIdFromToken(token));

        //여기서 user_idx 쓸일이 있겠지...

        //지금은 여기서 했는데 서비스는 절대 레포지토리 호출하지마 ! 그냥 매니저가 하게 해, 뭐 뭘 계산하는거든 알고리즘 엔티티 만들기 다 이런거 다
        Study study = Study.builder()
                .id(dto.getStudyId())
                .name(dto.getName())
                .classRate(dto.getClassRate())
                .build();
        studyRepository.save(study); //이게 걍 매니저 리턴값임

        /*
        현재 StudyService에서 Study 객체를 직접 생성하고 있는데, 이는 Study 도메인 객체의 책임을 서비스 계층에서 가져가는 상황입니다.
        이를 개선하기 위해 Study 자체가 자신의 생성 메서드를 제공하면 객체 생성 책임을 도메인으로 넘길 수 있습니다.
         */

         //Study study1 = Study.of(dto.getName(), dto.getClassRate()); 정적 펙토리 메서드

        // 매니저가 save하면서 반환해 걍 studyRepository.save(study); 이걸 리턴함
        // 그럼 여기서 받아 그걸 Study study = studyManager.registerStudy(dto)
        // 그럼 최동으로 이 서비스 계층의 registerStudy 메서드의 리턴은 무엇이냐?
        // return studyDtoMapper.toRegisterStudyResp(study);  !!! Resp는 리스펀스 줄임말

        return new StudyResponseDTO(   // 딱 이작업은 어디로 가? DAO작업이 다 몰려있는 DtoMapper로 가. 이건 근데 그냥 서비스에는 서비스 로직만 있으면 좋으니까 깔끔하니까 ~ 그렇게 하는거다
                study.getId(),
                study.getName(),
                study.getClassRate()
        );
    }

    @Override
    public StudyResponseDTO updateStudy(StudyRequestDTO dto) {
        return null;
    }
}