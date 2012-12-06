package nl.kennisnet.arena.repository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import geodb.GeoDB;

import javax.sql.DataSource;

import nl.kennisnet.arena.model.Location;
import nl.kennisnet.arena.model.ParticipantAnswer;
import nl.kennisnet.arena.model.Participant;
import nl.kennisnet.arena.model.ParticipantAnswer.Result;
import nl.kennisnet.arena.model.Participation;
import nl.kennisnet.arena.model.Positionable;
import nl.kennisnet.arena.model.Quest;
import nl.kennisnet.arena.model.Question;
import nl.kennisnet.arena.model.Round;

import org.h2.engine.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/configuration.xml",
		"classpath:/integration.xml", "classpath:/integration-test.xml",
		"classpath:/services.xml", "classpath:arena-servlet-test.xml" })
@Transactional
public class ParticipantAnswerRepositoryTest {

	@Autowired
	DataSource dataSource;

	@Autowired
	ParticipantAnswerRepository repository;
	
	@Autowired
	RoundRepository roundRepository;
	
	@Autowired
	ParticipationRepository participationRepository;
	
	@Autowired
	QuestRepository questRepository;
	
	@Autowired
	ParticipantRepository participantRepository;
	
	@Autowired
	PositionableRepository positionableRepository;
	
	@Autowired
	LocationRepository locationRepository;
	
	@Before
	public void setUp() throws Exception {
		GeoDB.InitGeoDB(dataSource.getConnection());
	}

	private ParticipantAnswer createObject() {
		ParticipantAnswer participantAnswer = new ParticipantAnswer();
		participantAnswer.setTextAnswer("LOL");
		
		Round round = new Round();
		round = roundRepository.merge(round);
		
		Participation participation = new Participation();
		Quest quest = new Quest();
		quest.setEmailOwner("testmail@test.nl");
		quest = questRepository.merge(quest);
		Participant participant = new Participant("testname");
		participant = participantRepository.merge(participant);
		participation.setParticipant(participant);
		participation.setQuest(quest);
		participation.setRound(round);
		participation = participationRepository.merge(participation);
		
		Question positionable = new Question();
		Location location = new Location();
		location = locationRepository.merge(location);
		positionable.setLocation(location);
		positionable.setQuest(quest);
		positionable = (Question) positionableRepository.merge(positionable);
		
		participantAnswer.setRound(round);
		participantAnswer.setQuestion(positionable);
		participantAnswer.setParticipation(participation);
		participantAnswer = repository.merge(participantAnswer);
		return participantAnswer;
	}
	
	@Test
	public void testCreate() {
		ParticipantAnswer participantAnswer = createObject();
		assertThat(participantAnswer, is(not(nullValue())));
	}
	
	@Test
	public void testGet() {
		ParticipantAnswer participantAnswer = createObject();
		ParticipantAnswer receivedAnswer = repository.get(participantAnswer.getParticipationAnswerPrimaryKey());		
		assertThat(receivedAnswer, is(not(nullValue())));
		System.out.println(receivedAnswer.getTextAnswer());
	}
	
	@Test
	public void testGetAll() {
		List<ParticipantAnswer> listOfCreatedParticipantAnswers = new ArrayList<ParticipantAnswer>();
		int count;
		int amount = 10;
		for (count=0; count<amount; count++) {
			ParticipantAnswer participantAnswer = createObject();
			listOfCreatedParticipantAnswers.add(participantAnswer);
		}
		assertThat(repository.getAll(), is(listOfCreatedParticipantAnswers));
	}
	
	@Test
	public void testDelete() {
		List<ParticipantAnswer> listToDelete = new ArrayList<ParticipantAnswer>();
		ParticipantAnswer pa1 = createObject();
		ParticipantAnswer pa2 = createObject();
		ParticipantAnswer pa3 = createObject();
		listToDelete.add(pa1);
		listToDelete.add(pa3);
		repository.delete(listToDelete);
		assertThat(repository.get(pa1.getParticipationAnswerPrimaryKey()), is(nullValue()));
		assertThat(repository.get(pa2.getParticipationAnswerPrimaryKey()), is(not(nullValue())));
		assertThat(repository.get(pa3.getParticipationAnswerPrimaryKey()), is(nullValue()));
	}
	
	@Test
	public void testSaveAnswerApprove() {
		//SETUP
		Participant blauw = new Participant();
		blauw.setName("Blauw");
		blauw = participantRepository.merge(blauw);
		
		Quest quest = new Quest();
		quest.setEmailOwner("email@email.nl");
		quest = questRepository.merge(quest);
		
		Round round = new Round();
		round.setName("Round");
		round.setQuest(quest);
		round = roundRepository.merge(round);
		
		quest.setActiveRound(round);
		quest.getRounds().add(round);
		quest = questRepository.merge(quest);
		
		Participation part1 = new Participation();
		part1.setParticipant(blauw);
		part1.setRound(round);
		part1.setQuest(quest);
		part1 = participationRepository.merge(part1);
		
		blauw.getParticipations().add(part1);
		blauw = participantRepository.merge(blauw);
		
		//ADD QUESTION & ANSWER
		Location location = new Location();
		location = locationRepository.merge(location);
		
		Question question = new Question();
		question.setName("The Question?");
		question.setQuest(quest);
		question.setQuestionType(1);
		question.setLocation(location);
		question = (Question)positionableRepository.merge(question);
		
		quest.getPositionables().add(question);
		quest = questRepository.merge(quest);
		
		location.getPositionables().add(question);
		location = locationRepository.merge(location);
		
		ParticipantAnswer answer1 = new ParticipantAnswer();
		answer1.setTextAnswer("The Answer");
		answer1.setQuestion(question);
		answer1.setResult(Result.ANSWERED.name());
		answer1.setParticipation(part1);
		answer1.setRound(round);
		answer1 = repository.merge(answer1);
		
		round.getParticipantAnswers().add(answer1);
		round = roundRepository.merge(round);
		
		List<ParticipantAnswer> answer = new ArrayList<ParticipantAnswer>();
		answer.add(answer1);
		question.setParticipantAnswers(answer);
		question = (Question)positionableRepository.merge(question);
		positionableRepository.getAll();
		repository.getSession().clear();
		
		//CHECK ANSWER 
		ParticipantAnswer participantAnswer = getParticipationAnswer(answer1.getParticipationId(), 
				getQuestion(answer1.getQuestion().getId(), quest));
		participantAnswer.setResult(Result.INCORRECT.name());
		participantAnswer = repository.merge(participantAnswer);
		repository.getAll();
	}
	
	public ParticipantAnswer getParticipationAnswer(long participationId,
			Question question) {
		List<ParticipantAnswer> participants = question.getParticipantAnswers();
		for (ParticipantAnswer p : participants){
			if(p.getParticipationId() == (participationId) && p.getQuestion().equals(question)){
				repository.evict(participants.get(0));
				return p;
			}			
		}		
		return null;
	}	
	
	
	public Question getQuestion(Long id, Quest quest) {
		if (quest != null && quest.getPositionables() != null) {
			for (Positionable positionable : quest.getPositionables()) {
				if (positionable.getId().equals(id)
						&& positionable instanceof Question) {
					return (Question) positionable;
				}
			}
		}
		return null;
	}

}
