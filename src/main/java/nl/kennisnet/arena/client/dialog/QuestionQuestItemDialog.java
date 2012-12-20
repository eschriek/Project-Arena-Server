package nl.kennisnet.arena.client.dialog;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import nl.kennisnet.arena.client.dialog.StoryQuestItemDialog.Container;
import nl.kennisnet.arena.client.domain.QuestItemDTO;
import nl.kennisnet.arena.client.domain.QuestItemDTO.TYPE;
import nl.kennisnet.arena.client.widget.ExtendedTextBox;
import nl.kennisnet.arena.client.widget.FormTablePanel;
import nl.kennisnet.arena.model.Type;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class QuestionQuestItemDialog extends QuestItemDialog {

	private TextBox[] answersTextBoxs;
	private RadioButton[] correctAnswer;
	private RadioButton[] questionType;
	private Container container;
	private ExtendedTextBox storyTextBox;

	private static int MAX_CHARACTERS = 160;

	private List<FormTablePanel.Element> multipleChoiceElements;

	public QuestionQuestItemDialog(final QuestItemDTO itemDTO,
			Container container, boolean readOnlyDialog, boolean create) {
		super(itemDTO, readOnlyDialog, create);
		this.container = container;
		if (this.container != null) {
			fillFormFromItem(container);
		}
		setText("Vraag details");
	}

	private List<FormTablePanel.Element> createAnswerPanels() {
		multipleChoiceElements = new ArrayList<FormTablePanel.Element>();
		answersTextBoxs = new TextBox[4];
		correctAnswer = new RadioButton[4];
		for (int i = 0; i < 4; i++) {
			Panel panel = new HorizontalPanel();
			answersTextBoxs[i] = new TextBox();
			answersTextBoxs[i].setMaxLength(80);
			panel.add(answersTextBoxs[i]);
			correctAnswer[i] = new RadioButton("correctAnswer");
			panel.add(correctAnswer[i]);
			multipleChoiceElements.add(new FormTablePanel.Element("Antwoord "
					+ (i + 1), panel));
		}
		return multipleChoiceElements;
	}

	private FormTablePanel.Element createQuestionTypePanel() {

		Panel panel = new HorizontalPanel();
		questionType = new RadioButton[2];

		panel.add(createMulitpleChoiceRadioButtion(true));
		panel.add(new Label("Multiple choice"));

		Panel panel2 = new HorizontalPanel();
		panel2.add(createOpenQuestionRadioButtion(false));
		panel2.add(new Label("Open vraag"));

		Panel verPanel = new VerticalPanel();
		verPanel.add(panel);
		verPanel.add(panel2);

		return new FormTablePanel.Element("Type", verPanel);
	}

	private RadioButton createMulitpleChoiceRadioButtion(boolean visible) {
		questionType[0] = new RadioButton("questionType");
		questionType[0].setValue(visible);
		questionType[0].addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				toggleMultipleChoiceElementsVisibility(true);
			}
		});
		return questionType[0];
	}

	private RadioButton createOpenQuestionRadioButtion(boolean visible) {
		questionType[1] = new RadioButton("questionType");
		questionType[1].setValue(visible);
		questionType[1].addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				toggleMultipleChoiceElementsVisibility(false);
			}
		});
		return questionType[1];
	}

	private void toggleMultipleChoiceElementsVisibility(Boolean visible) {
		for (FormTablePanel.Element element : multipleChoiceElements) {
			element.getLabel().setVisible(visible);
			element.getField().setVisible(visible);
		}
	}

	@Override
	protected List<FormTablePanel.Element> createFormPanels() {
		List<FormTablePanel.Element> result = new ArrayList<FormTablePanel.Element>();
		// result.add(createNamePanel());
		result.add(createQuestionTypePanel());
		result.add(createStoryPanel("Vraag"));
		result.addAll(createAnswerPanels());
		// result.add(createRadiusPanel());
		// result.add(createVisibleRadiusPanel());
		return result;
	}

	protected FormTablePanel.Element createStoryPanel(String title) {
		storyTextBox = new ExtendedTextBox(80, 4, MAX_CHARACTERS);
		return new FormTablePanel.Element(title, storyTextBox);
	}

	protected void fillFormFromItem(Container container) {
		// super.fillFormFromItem(itemDTO);
		if (container.getQuestionTypeAsEnum() == TYPE.MULTIPLE_CHOICE) {
			answersTextBoxs[0].setText(container.getOption1());
			answersTextBoxs[1].setText(container.getOption2());
			answersTextBoxs[2].setText(container.getOption3());
			answersTextBoxs[3].setText(container.getOption4());
		} else if (container.getQuestionTypeAsEnum() == TYPE.OPEN_QUESTION) {
			questionType[0].setValue(false);
			questionType[1].setValue(true);
			toggleMultipleChoiceElementsVisibility(false);
		}
		if (correctAnswer != null) {
			for (int i = 0; i < correctAnswer.length; i++) {
				RadioButton correct = correctAnswer[i];
				if (container.getCorrectOption() != null
						&& container.getCorrectOption() == i + 1
						&& correct != null) {
					correct.setValue(true);
				} else {
					correct.setValue(false);
				}

			}
		}
		storyTextBox.setText(container.getDescription());
	}

	protected QuestItemDTO fillItemFromForm(QuestItemDTO itemDTO) {
		QuestItemDTO result = super.fillItemFromForm(itemDTO);
		Container container = new Container();

		if (questionType[0].getValue() == true) {
			container.setOption1(answersTextBoxs[0].getText());
			container.setOption2(answersTextBoxs[1].getText());
			container.setOption3(answersTextBoxs[2].getText());
			container.setOption4(answersTextBoxs[3].getText());
			container.setQuestionType(0);

			// itemDTO.setOption1(answersTextBoxs[0].getText());
			// itemDTO.setOption2(answersTextBoxs[1].getText());
			// itemDTO.setOption3(answersTextBoxs[2].getText());
			// itemDTO.setOption4(answersTextBoxs[3].getText());
			// itemDTO.setQuestionType(0);
		} else {
			container.setQuestionType(1);
			// itemDTO.setQuestionType(1);
		}

		if (correctAnswer != null) {
			for (int i = 0; i < correctAnswer.length; i++) {
				RadioButton correct = correctAnswer[i];
				if (correct != null && correct.getValue()) {
					container.setCorrectOption(i + 1);
					// itemDTO.setCorrectOption(i + 1);
				}
			}
		}

		container.setDescription(storyTextBox.getText());
		result.getItems().add(container);
		return result;
	}

	@Entity
	public class Container extends Type {

		private Integer questionType = 0;
		private String option1;
		private String option2;
		private String option3;
		private String option4;
		private Integer correctOption;
		private String description;

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Integer getQuestionType() {
			return questionType;
		}

		public void setQuestionType(Integer questionType) {
			this.questionType = questionType;
		}

		public String getOption1() {
			return option1;
		}

		public void setOption1(String option1) {
			this.option1 = option1;
		}

		public String getOption2() {
			return option2;
		}

		public void setOption2(String option2) {
			this.option2 = option2;
		}

		public String getOption3() {
			return option3;
		}

		public void setOption3(String option3) {
			this.option3 = option3;
		}

		public String getOption4() {
			return option4;
		}

		public void setOption4(String option4) {
			this.option4 = option4;
		}

		public Integer getCorrectOption() {
			return correctOption;
		}

		public void setCorrectOption(Integer correctOption) {
			this.correctOption = correctOption;
		}

		public TYPE getQuestionTypeAsEnum() {
			if (questionType == null) {
				return TYPE.MULTIPLE_CHOICE;
			}
			switch (questionType) {
			case 0:
				return TYPE.MULTIPLE_CHOICE;
			case 1:
				return TYPE.OPEN_QUESTION;
			default:
				return TYPE.MULTIPLE_CHOICE;
			}
		}

	}

}
