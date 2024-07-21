package com.example.demo.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ExamDto;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

@Component
public class ChatCompletion {

	private static ChatCompletion chat = new ChatCompletion();

	private ChatCompletion(){
	}

	@Autowired
	@Value("${openai.token}")
    private String token;

	
	static String jsonTest = "{\n"
    		+ "  \"question\": \"あるクラスの生徒の身長データを調査した結果、平均身長が165 cm、標準偏差が5 cmであることがわかりました。このクラスの生徒の身長が平均から2標準偏差以上離れている場合、その生徒は異常値とみなされます。ある生徒の身長が170 cmである場合、この生徒は異常値とみなされるかどうか判定してください。\",\n"
    		+ "  \"choice1\": \"異常値とみなされる\",\n"
    		+ "  \"choice2\": \"異常値とみなされない\",\n"
    		+ "  \"choice3\": \"判断できない\",\n"
    		+ "  \"choice4\": \"情報が不足している\",\n"
    		+ "  \"answer\": \"異常値とみなされない\",\n"
    		+ "  \"explain\": \"生徒の身長が平均から2標準偏差以上離れている場合に異常値とみなされるため、170 cmの生徒は異常値とはみなされません。\"\n"
    		+ "}\n"
    		+ "";
	
	public static ChatCompletion getInstance() {
		return chat;
	}

	/**
	 * @param request
	 * @return エラーコード(0：エラーなし、0以外：エラー)
	 */
	public int run(String token, ExamDto request, String msg) {
		StringBuilder val = new StringBuilder();
		
		final var service = new OpenAiService(token, Duration.ofSeconds(60));
		final var message = request.getOrder();
        final var prompt = "あなたは大学入試問題を作成する問題作成委員の一人です。";

		final List <ChatMessage> messages = new ArrayList <>();
		final var promptMessage = new ChatMessage();
		promptMessage.setRole("system");
		promptMessage.setContent(prompt);
		messages.add(promptMessage);
		final var userMessage = new ChatMessage();
		userMessage.setRole("user");
		userMessage.setContent(message);
		messages.add(userMessage);
		
		final var req = ChatCompletionRequest.builder()
				// .model("gpt-4")
		        .model("gpt-3.5-turbo")
		            .messages(messages)
		            .maxTokens(2048)
		            .build();
		
		final var completionResult = service.createChatCompletion(req);
		
		final var choiceList = completionResult.getChoices();
		
		if (choiceList.isEmpty()) {
		        System.out.println("エラー");
		        return -1;
		}
		
		for (final ChatCompletionChoice choice : choiceList) {
		    System.out.println(choice);
		    val.append(choice.toString());
		}

		// ChatGPTからの応答をStringに変換して返却
		msg = new String(val);
		return 0;
	}
	

//	/**
//	 * 応答メッセージ分割
//	 * ChatGPTからの応答をフォーマットを元に分割する。
//	 * フォーマット
//	 *     [問題]****[問題の選択肢]****[回答]****[解説]****
//	 * @param completionMsg ChatGPTが補完したメッセージ
//	 * @return
//	 */
//	private String[] SplitChatCompletionResult(String completionMsg) {
//		String[] msg=null;
//		// 指定通りのフォーマットになっているかチェック
//		
//		UtilRegexModel model = new UtilRegexModel("\\[問題\\](.*)\\[問題の選択肢\\](.*)\\[回答\\](.*)\\[解説\\](.*)");
//		if(!model.IsMatch(completionMsg)) {
//			// TODO:指定したフォーマットになっていない場合、他の方法で分解を試みる
//					
//			return null;
//		}
//
//		// 分割
//		// [問題]
//		
//		// [問題の選択肢]
//		// [回答]
//		// [解説]
//		
//		
//		
//		
//		return msg;
//
//	}
//	
//	/**
//	 * メッセージが指定したフォーマットになっているかを確認する。
//	 * フォーマット
//	 *     [問題]****[問題の選択肢]****[回答]****[解説]****
//	 * @param completionMsg メッセージ
//	 * @param msg 分割したメッセージ
//	 * @return true:指定したフォーマットである、false:指定したフォーマットではない
//	 */
//	private boolean	 IsAssignCompletionMsg(String completionMsg, String[] msg){
//
//		String regex = "\\[問題\\](.*)\\[問題の選択肢\\](.*)\\[回答\\](.*)\\[解説\\](.*)";
//		Pattern pattern = Pattern.compile(regex);		
//	    Matcher matcher = pattern.matcher(completionMsg);
//	    if (!matcher.find()) {
//	        System.out.println("正規表現にマッチしませんでした。");
//			return false;
//
//	    } 		
//        System.out.println("正規表現にマッチしました。");
//
//        msg = new String[4];
//
//        msg[0] = matcher.group(2);			// [問題]の本文
//        msg[1] = matcher.group(4);			// [問題の選択肢]の本文
//        msg[2] = matcher.group(6);			// [回答]の本文
//        msg[3] = matcher.group(8);			// [解説]の本文
//
//		return true;
//	}



}
