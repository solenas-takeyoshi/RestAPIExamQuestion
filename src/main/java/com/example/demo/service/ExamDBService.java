package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.exam.Examination;
import com.example.demo.repository.exam.ExamRepository;

@Service
public class ExamDBService {

    @Autowired
    private ExamRepository examRepository;

//    @Autowired
//    private ExamChoiceRepository choiceRepository;

//    /**
//     * テスト問題用キーワードテーブルサービスクラス
//     */
//    @Autowired
//    private ExamKeywordService examKeywordService;
//
//    /**
//     * キーワードマスタ(MST_KEYWORD)サービス
//     */
//    @Autowired
//    private MstKeywordService mstKeywordService;
//
	/**
	 * 問題一覧取得
	 * @return
	 */
	public List<Examination> getAll(){
	    return this.examRepository.findAll();
	}

//	@Transactional
	public Examination insert(Examination exam){
      return this.examRepository.save(exam);
  }

    
    
//    /**
//     * Pager版問題一覧取得
//     * @param page
//     * @return
//     */
//    public Page<Examination> getAll(Pageable page){
//        return this.examRepository.findAll(page);
//    }
//
//    public Examination findById(Long id){
//        Optional<Examination> exam = this.examRepository.findById(id);
//        return exam.orElseThrow();
//    }
//    public Examination add(ExamDto dto, MstUser user){
//        Examination exam = buildForExaminationAdd(dto, user);
////        keywordBuilder(exam, dto.getKeywordList());
//        return exam;
//    }
//
//    @Transactional
//    private Examination buildForExaminationAdd(ExamDto dto, MstUser user){
//        //Dtoからサービス用のEntityクラスにデータを移し替え
//        Examination exam = new Examination();
//        exam.setExamText(dto.getExamText());
//        exam.setExplanation(dto.getExplanation());
//        //選択肢
//        List<ExamChoiceDto> list = dto.getChoiceList();
//        if(list == null){
//            dto.setChoiceList(new ArrayList<ExamChoiceDto>());
//        }
//        exam.getChoices().clear();
//        for(ExamChoiceDto cDto : list){
//            String choiceText = cDto.getChoiceText();
//            if(choiceText == null){
//                continue;
//            }
//            ExamChoice choice = new ExamChoice();
//            choice.setExamination(exam);
//            choice.setValue(choiceText);
//            choice.setCorrect(cDto.isCorrect());
//            exam.getChoices().add(choice);
//        }
//        //オーナー
//        exam.setOwner(user);
//        return this.examRepository.save(exam);
//    }

//    /**
//     * 問題登録時に送信されたキーワード（tag）が、キーワードマスタに存在する場合はランクアップし、
//     * キーワードマスタに存在しない場合はキーワードマスタに登録を行う。
//     * また、テスト問題キーワードテーブルに登録を行う。
//     * キーワードマスタはキーワード自体がユニーク制約があるため、
//     * 問題の登録とは別トランザクションで行う。
//     * @param keywords
//     * @return
//     */
//    @Transactional
//    private void keywordBuilder(Examination exam, List<ExamKeywordDto> keywords ){
//
//        //キーワード
//        List<MstKeyword> candidateKeywordList = new ArrayList<>();  //
//        for(ExamKeywordDto keyDto : keywords) {
//            //送信されたキーワードがキーワードマスタに完全一致するか？
//            List<MstKeyword> mstKeywords = this.mstKeywordService.findByKeyword(keyDto.getKeywordText());
//            //一致した場合はランクアップ
//            if (mstKeywords.size() > 0) {
//                mstKeywords.forEach(row -> {
//                    candidateKeywordList.add(row);
//                    this.mstKeywordService.rankUp(row.getId());
//                });
//            }
//            //一致しなかった場合は、キーワードマスタに新規追加
//            else {
//                MstKeyword newKeyword = this.mstKeywordService.add(keyDto.getKeywordText());
//                candidateKeywordList.add(newKeyword);
//            }
//        }
//
//        for(MstKeyword mst: candidateKeywordList) {
//            ExamKeyword keyword = new ExamKeyword();
//            keyword.setExamination(exam);
//            keyword.setKeyword(mst);
//            this.examKeywordService.add(keyword);
//        }
//    }
//
//    @Transactional
//    public Examination update(Long examId, Examination examRequest){
//        return examRepository.findById(examId).map(exam -> {
//            exam.setExamText(examRequest.getExamText());    //問題文を更新
//            if(examRequest.getChoices() != null){
//                //選択肢がある場合は、親から一度、clearして保存で削除
//                exam.getChoices().clear();
//                examRepository.save(exam);
//                //新規に選択肢を追加するため
//                for (ExamChoice choice : examRequest.getChoices()){
//                    choice.setExamination(exam);
//                }
//                exam.getChoices().addAll(examRequest.getChoices());
//            }
//            return examRepository.save(exam);
//        }).orElseThrow(() -> new ResourceNotFoundException("examId " + examId + " not found"));
//    }
//
//    public ResponseEntity<?> delete(Long examId){
//        return examRepository.findById(examId).map(exam -> {
//            examRepository.delete(exam);
//            return ResponseEntity.ok().build();
//        }).orElseThrow(() -> new ResourceNotFoundException("examId " + examId + " not found"));
//    }
}
