package com.example.demo.entity.exam;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
//import jp.co.solenas.snap.entity.master.MstUser;
import lombok.Getter;
import lombok.Setter;

/**
 * テスト問題を表すエンティティクラス
 */
@Entity
@Table(name = "EXAMINATION")
@Getter @Setter
public class Examination extends BaseEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /**
    * 試験の問題文
    * */
    @Column(name="EXAM_TEXT", nullable = false, length = 1024)
    private String examText;

    /**
     * 問題文にに対する解説
     */
    @Column(name="EXPLANATION", nullable = false, length = 1024)
    private String explanation;

    /**
     * 問題の回答の選択肢
     */
    /*
     * orphanRemoval=true:親キーで子をまとめて削除する場合、永続的に削除を行う際に必要（これがないとメモリ上は削除されててもDB上にはのこってしまう）
     * mappedBy = "exam":子側で保持する親のプロパティ名
     */
    @OneToMany(targetEntity = ExamChoice.class,
    cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=true, mappedBy = "examination")
    private List<ExamChoice> choices = new ArrayList<>();

    public void addChoice(ExamChoice c) {
        this.choices.add(c);
    }

//    /**
//     * テスト問題のキーワード。Java Silever試験の場合は”java”,"silver"などが該当する。
//     * 検索時に使用する。
//     */
//    @OneToMany(targetEntity = ExamKeyword.class,
//    cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "examination")
//    private List<ExamKeyword> keywords = new ArrayList<>();
//
//    public void addKeyword(ExamKeyword k){ this.keywords.add(k); }
//
//    /**
//     * テスト問題のオーナーユーザー
//     */
//    @ManyToOne(targetEntity = MstUser.class)
//    @JoinColumn(name = "USER_ID", nullable = false
//            , foreignKey = @ForeignKey(name = "FK_MST_USER_TO_EXAMINATION")
//            , referencedColumnName = "id")
//    private MstUser owner;


}
