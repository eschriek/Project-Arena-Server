package nl.kennisnet.arena.web.mixare;

import java.util.HashMap;

import nl.kennisnet.arena.model.Information;
import nl.kennisnet.arena.model.Quest;
import nl.kennisnet.arena.model.Question;
import nl.kennisnet.arena.services.ParticipantService;
import nl.kennisnet.arena.services.QuestService;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	private final QuestService questService;
	private final ParticipantService participantService;
	
	private Logger log = Logger.getLogger(ItemController.class);

	@Autowired
	public ItemController(final QuestService questService, final ParticipantService participantService) {
		this.questService = questService;
		this.participantService = participantService;
	}
	
	/**
	 * This method will run if no parameters are send with the url. Mixare will first run this
	 * url to check if the page exists.
	 * @param questId
	 * @return
	 */
	@RequestMapping(value = "/show/{questId}/{questionId}/{player}", method = RequestMethod.GET) 
	public ModelAndView showQuestions(@PathVariable Long questId, @PathVariable Long questionId){
		Quest quest = questService.getQuest(questId);
		Question question = participantService.getQuestion(questionId, quest);
		
		log.debug("questId = "+ questId + ", questionId = "+ questionId + ", quest = "+ quest + ", question = " + question);
		
		if(question == null){
			return new ModelAndView(new InternalResourceView("/question.jsp"));
		}
		
		HashMap<String, String> model = new HashMap<String, String>();
		model.put("question", question.getText());
		model.put("answer1", question.getAnswer1());
		model.put("answer2", question.getAnswer2());
		model.put("answer3", question.getAnswer3());
		model.put("answer4", question.getAnswer4());
		
		return new ModelAndView(new InternalResourceView("/question.jsp"), model);
	}
	
	/**
	 * This method will run if no parameters are send with the url. Mixare will first run this
	 * url to check if the page exists.
	 * @param questId
	 * @return
	 */
	@RequestMapping(value = "/show/{informationId}", method = RequestMethod.GET) 
	public ModelAndView showInformation(@PathVariable Long informationId){
		Information information = participantService.getInformation(informationId);
		
		log.debug("informationId = "+ informationId + ", information= "+ information);
		
		if(information == null){
			return new ModelAndView(new InternalResourceView("/information.jsp"));
		}
		
		HashMap<String, String> model = new HashMap<String, String>();
		model.put("title", information.getName());
		model.put("text", information.getText());
		
		return new ModelAndView(new InternalResourceView("/information.jsp"), model);
	}
	
	/**
	 * This method will run if no parameters are send with the url. Mixare will first run this
	 * url to check if the page exists.
	 * @param questId
	 * @return
	 */
	@RequestMapping(value = "/show/{questId}/{questionId}/{player}", method = RequestMethod.POST)
	public ModelAndView submitQuestion(@PathVariable Long questId, @PathVariable Long questionId, 
			 @PathVariable String player, @RequestParam("answer") int answer){
		
		final Quest quest = questService.getQuest(questId);
		final Question question = participantService.getQuestion(questionId, quest);		
		final long participantId = participantService.getParticipantId(player);
		final long participationId = questService.participateInQuest(participantId, quest);
		
		log.debug("answering: questId = "+ questId + ", questionId = "+ questionId + "," +
				" quest = "+ quest + ", question = " + question + " answer = "+ answer);
		
		participantService.storeParticipationAnswer(participationId, question, answer);
		
		HashMap<String, String> model = new HashMap<String, String>();		
		if( question.getCorrectAnswer() == answer ){
			model.put("correct", "correct");
			return new ModelAndView(new InternalResourceView("../../../../question-result.jsp"), model);
		}
		else{
			model.put("incorrect", "incorrect");
			return new ModelAndView(new InternalResourceView("../../../../question-result.jsp"), model);
		}
	}
	
}
