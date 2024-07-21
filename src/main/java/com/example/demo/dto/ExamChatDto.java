package com.example.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ExamChatDto {


    private Long id;
	
	/**
	 * 問題
	 */
	@JsonProperty("examText")
	private String examText;

	/**
	 * 解説
	 */
	@JsonProperty("explanation")
	private String explanation;

    /**
     * 選択肢
     */
	@JsonProperty("choices")
    private List<ExamChoiceChatDto> choices;
}
