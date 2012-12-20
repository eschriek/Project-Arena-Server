package nl.kennisnet.arena.client.dialog;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import nl.kennisnet.arena.client.domain.QuestItemDTO;
import nl.kennisnet.arena.client.widget.ExtendedTextBox;
import nl.kennisnet.arena.client.widget.FormTablePanel;
import nl.kennisnet.arena.model.Type;

public class StoryQuestItemDialog extends QuestItemDialog {

	private ExtendedTextBox storyTextBox;
	private Container container;
	
	private static int MAX_CHARACTERS = 160;

	public StoryQuestItemDialog(QuestItemDTO itemDTO, Container container, boolean readOnlyDialog,
			boolean create) {
		super(itemDTO, readOnlyDialog, create);
		this.container = container;
		if(container != null) {
			fillFormFromItem(container);
		}
		setText("Verhaal details");
	}

	@Override
	protected List<FormTablePanel.Element> createFormPanels() {
		List<FormTablePanel.Element> result = new ArrayList<FormTablePanel.Element>();
		// result.add(createNamePanel());
		result.add(createStoryPanel("Verhaal"));
		// result.add(createRadiusPanel());
		// result.add(createVisibleRadiusPanel());
		return result;
	}

	protected FormTablePanel.Element createStoryPanel(String title) {
		storyTextBox = new ExtendedTextBox(80, 4, MAX_CHARACTERS);
		return new FormTablePanel.Element(title, storyTextBox);
	}

	protected void fillFormFromItem(Container container) {
		//super.fillFormFromItem(itemDTO);
		storyTextBox.setText(container.getDescription());
	}

	protected QuestItemDTO fillItemFromForm(QuestItemDTO itemDTO) {
		QuestItemDTO result = super.fillItemFromForm(itemDTO);
		result.getItems().add(new Container(storyTextBox.getText()));
		//result.setDescription(storyTextBox.getText());
		return result;
	}

	@Entity
	public class Container extends Type {

		private String description;

		public Container(String description) {
			this.description = description;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	}

}
