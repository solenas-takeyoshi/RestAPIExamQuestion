package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ExamChoiceChatDto {

    private Long id;
	/**
	 * 選択肢の内容
	 */
	@JsonProperty("value")
	private String value;

	/**
	 * 選択肢の正解・不正解の判定フラグ
	 * (ture:正解、false:不正解)
	 */
	@JsonProperty("correct")
	private boolean correct;

}
