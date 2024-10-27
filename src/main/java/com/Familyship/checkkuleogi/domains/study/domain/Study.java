package com.Familyship.checkkuleogi.domains.study.domain;

import com.Familyship.checkkuleogi.domains.study.domain.enums.ClassRate;
import com.Familyship.checkkuleogi.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Study extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private ClassRate classRate;

    public static Study of(String name, ClassRate classRate) {
        return Study.builder()
                .name(name)
                .classRate(classRate)
                .build();
    }
}
