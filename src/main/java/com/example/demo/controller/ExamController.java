package com.example.demo.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.example.demo.model.UtilRegexModel;
//import com.theokanning.openai.completion.chat.ChatCompletionChoice;
//import com.theokanning.openai.completion.chat.ChatCompletionRequest;
//import com.theokanning.openai.completion.chat.ChatMessage;
//import com.theokanning.openai.service.OpenAiService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ExamChatDto;
import com.example.demo.dto.ExamDto;
import com.example.demo.entity.exam.ExamChoice;
import com.example.demo.entity.exam.Examination;
import com.example.demo.service.ExamDBService;
import com.example.demo.service.ExamGenerativeAIService;


@Controller
@RestController
@RequestMapping("/host")
public class ExamController {
	//DB検索テスト
    @Autowired
    private ExamDBService dbService;
	
    @Value("${openai.token}")
    private String token;

    @Autowired
    private ModelMapper modelMapper;    
    
    @PostMapping("/add")
	@ResponseBody
	public ExamDto createExamination(@RequestBody ExamDto request) {
//--DB接続テスト---------------------------------------------
//		// 検索
//		List<Examination> list = this.service.getAll();
//-----------------------------------------------------------
		
		// 試験問題作成
		ExamGenerativeAIService aiService = new ExamGenerativeAIService();
		ExamDto dto = aiService.Create(token, request);
		
		// 試験問題登録
		Examination entity = modelMapper.map(dto.getChat(), Examination.class);
		List<ExamChoice> list = entity.getChoices();
		for(ExamChoice choice : list)
		{
			choice.setExamination(entity);
		}
		dbService.insert(entity);

		// 返却
		dto.setChat(modelMapper.map(entity, ExamChatDto.class));
		return dto;
	}
}
