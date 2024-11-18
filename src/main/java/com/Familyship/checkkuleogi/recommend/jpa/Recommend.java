package com.Familyship.checkkuleogi.recommend.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@Table(
//        indexes = {
//                @Index(name = "recommend", columnList = "childIdx")
//        })  //추후 Recommend에 속상이 늘어날 시 인덱싱 트레이드오프 계산 필요
public class Recommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long childIdx;

    @Column
    private Long bookIdx;
}
