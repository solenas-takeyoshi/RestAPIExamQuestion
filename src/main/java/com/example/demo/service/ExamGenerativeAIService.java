package com.example.demo.service;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ExamDto;
import com.example.demo.model.ChatCompletion;
import com.example.demo.model.Serialization;

/**
 * 生成AIで問題文を作成するクラス
 */
@Service
public class ExamGenerativeAIService {

//	@Autowired
//	private ExamRepository examRepository;

	// 生成AIが使用できない場合の生成AIからの返却値（テストモード用）
//	static String jsonTest = "{\n"
//    		+ "  \"question\": \"あるクラスの生徒の身長データを調査した結果、平均身長が165 cm、標準偏差が5 cmであることがわかりました。このクラスの生徒の身長が平均から2標準偏差以上離れている場合、その生徒は異常値とみなされます。ある生徒の身長が170 cmである場合、この生徒は異常値とみなされるかどうか判定してください。\",\n"
//    		+ "  \"choice1\": \"異常値とみなされる\",\n"
//    		+ "  \"choice2\": \"異常値とみなされない\",\n"
//    		+ "  \"choice3\": \"判断できない\",\n"
//    		+ "  \"choice4\": \"情報が不足している\",\n"
//    		+ "  \"answer\": \"異常値とみなされない\",\n"
//    		+ "  \"explain\": \"生徒の身長が平均から2標準偏差以上離れている場合に異常値とみなされるため、170 cmの生徒は異常値とはみなされません。\"\n"
//    		+ "}\n"
//    		+ "";

	
	static String jsonTest = "{\n"
			+ "  \"examText\": \"あるクラスの学生のテストの平均点は70点、標準偏差は8点です。このクラスの学生の得点が78点以上である確率はどれくらいですか？（正規分布に従うと仮定します）\",\n"
			+ "\n"
			+ "  \"choices\": [\n"
			+ "    {\n"
			+ "      \"value\": \"約15.87%\",\n"
			+ "      \"correct\": \"true\"\n"
			+ "    },\n"
			+ "    {\n"
			+ "      \"value\": \"約84.13%\",\n"
			+ "      \"correct\": \"false\"\n"
			+ "    },\n"
			+ "    {\n"
			+ "      \"value\": \"約50%\",\n"
			+ "      \"correct\": \"false\"\n"
			+ "    },\n"
			+ "    {\n"
			+ "      \"value\": \"約30.85%\",\n"
			+ "      \"correct\": \"false\"\n"
			+ "    }\n"
			+ "  ],\n"
			+ "  \"explanation\": \"正規分布において、平均70点、標準偏差8点の得点が78点以上である確率を求めるには、Zスコアを計算します。Z = (78 - 70) / 8 = 1。Zスコアが1以上である確率は約15.87%です。\"\n"
			+ "}\n"
			+ "";

	
	public ExamDto Create(String token, ExamDto order) {
		ExamDto ai = order;	
		String json = null;

		if (token != "") {
			// 生成AIを利用し試験問題文を作成
			ChatCompletion chat = ChatCompletion.getInstance();
			int result = chat.run(token, ai, json);
			if (result != 0 ) {
				// TODO:エラー処理
			}
		}
		else {
			json = jsonTest;
		}

		// 試験問題文をデシリアライズ
		ai.setChat(Serialization.deserialized(json));

		// デバッグ
		{
			System.out.println("問題    :" + order.getChat().getExamText());
			System.out.println("解説    :" + order.getChat().getExplanation());
		}

		return ai;
}

//	public Response Create(ExamDto order) {
//		
//
//		// TODO:セキュリティチェック
//		
//		// TODO:依頼文の入力チェック
//		
////		// 試験問題文を分解・作成
////		UtilRegexModel model = new UtilRegexModel();
////		String[] ans = model.SplitChatCompletionResult(null);
//
//		
//		// 試験問題文作成
////		ChatCompletion chat = ChatCompletion.getInstance();
////		String json = null;
////		int result = chat.run(order, json);
////		if (result != 0 ) {
////			// TODO:エラー処理
////		}
//
//		String json = null;
//		
//		
//		// 試験問題文をデシリアライズ
//		order.setChat(Serialization.deserialized(json));
//
//		// デバッグ
//		{
//			System.out.println("問題    :" + order.getChat().getQuestion());
//			System.out.println("選択肢１:" + order.getChat().getChoice1());
//			System.out.println("選択肢２:" + order.getChat().getChoice2());
//			System.out.println("選択肢３:" + order.getChat().getChoice3());
//			System.out.println("選択肢４:" + order.getChat().getChoice4());
//			System.out.println("解答    :" + order.getChat().getAnswer());
//			System.out.println("解説    :" + order.getChat().getExplain());
//		}
//
//		
//		
//		// TODO:DB登録
//		this.service.add(order);
////		Examination exam = new Examination();
////        exam.setExamText(order.getChat().getQuestion());
////        exam.setExplanation(order.getChat().getExplain());
////        examRepository.save(exam);
//		
//		// TODO:応答作成
//		Response response = new ExamFullResponse();
//		response.setResultCode("0");
////		Response response = new Response();
////		try {
////			System.out.println("受領したリクエストの文字列=" + request.getOrder());
////			response.setResultCode("0");
////			System.out.println("レスポンスで返す処理結果コードは→" + response.getResultCode());
////			return response;
//
//		return response;
//	}

	
//	public String CreateMsg(String msg) {
//		
//		// 分解
//		String regex = "pattern";
//		Pattern pattern = Pattern.compile(regex);		
//        Matcher matcher = pattern.matcher(msg);
//        if (matcher.find()) {
//            System.out.println("正規表現にマッチしました。");
//            
//            matcher.group(0);
//            matcher.group(1);
//            matcher.group(2);
//            
//        } else {
//            System.out.println("マッチしませんでした。");
//        } 		
//
//		// 分解
//        
//        
//        return "";
//	}
}
