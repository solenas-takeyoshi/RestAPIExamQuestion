package com.example.demo.entity.exam;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 問題文の選択肢のテーブル
 */
@Entity
@Table(name = "EXAM_CHOICE")
@Getter @Setter
public class ExamChoice extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "EXAMINATION_ID", nullable = false
    , foreignKey = @ForeignKey(name = "FK_EXAMINATION_TO_EXAM_CHOICE")
    , referencedColumnName = "id"
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore //このアノテーションがないと循環参照になるため大事
    private Examination examination;

    /**
     * 選択肢
     */
    @Column(name="CHOICE_TEXT", nullable = false, length = 255)
    private String value;

    /**
     * 選択肢の正解・不正解の判定フラグ
     */
    @Column(name="CORRECT", nullable = false)
    private boolean correct;
}
