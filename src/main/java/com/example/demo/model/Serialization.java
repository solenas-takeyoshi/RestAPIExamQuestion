package com.example.demo.model;

import com.example.demo.dto.ExamChatDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Serialization {

    /**
     * 生成AIからの返答(json形式)をオブジェクトに変換
     * @param 生成した問題文(json形式)
     * @return 生成した問題文のオブジェクト
     */
    public static ExamChatDto deserialized(String json) {
    	ObjectMapper om = new ObjectMapper();
    	ExamChatDto deserialized = null ;
       	
    	// デシリアライズ（JSON → オブジェクト）
    	try {
        	deserialized = om.readValue(json, ExamChatDto.class);
		} catch (JsonProcessingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    	
    	return deserialized;
    }
}
