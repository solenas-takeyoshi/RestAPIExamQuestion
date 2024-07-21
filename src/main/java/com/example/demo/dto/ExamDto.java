package com.example.demo.dto;

import lombok.Data;

/**
 * 問題文作成のリクエストをまとめたクラス
 */
@Data
public class ExamDto {

	// セキュリティキー
	// ユーザID
	// 依頼日時
	
	
	// 依頼文
	private String order;

	// 生成AI
	private ExamChatDto chat;
}
