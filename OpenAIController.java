package com.tuespotsolutions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuespotsolutions.models.AIReauest;
import com.tuespotsolutions.models.AIReauestForCrossQuestion;
import com.tuespotsolutions.models.AIResponse;
import com.tuespotsolutions.service.OpenAIService;

@RestController
@CrossOrigin("*")
@RequestMapping("/openai")
public class OpenAIController {
	
	@Autowired
	private OpenAIService openAIService;
	
	@PostMapping("/")
	public List<AIResponse> getQustionWithAudioFromAI(@RequestBody AIReauest aiReauest) {
		return openAIService.getAudioFromOpenAI(aiReauest);
	}

	@PostMapping("/crossquestion")
	public List<AIResponse> getCrossQuestionFromOpenAIWithAudio(@RequestBody AIReauestForCrossQuestion aiReauest){
		return openAIService.getCrossQuestionsAudioFromOpenAI(aiReauest);
	}
	
}
