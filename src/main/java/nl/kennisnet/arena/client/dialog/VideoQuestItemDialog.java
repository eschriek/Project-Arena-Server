package nl.kennisnet.arena.client.dialog;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import nl.kennisnet.arena.client.domain.QuestItemDTO;
import nl.kennisnet.arena.client.widget.FormTablePanel;
import nl.kennisnet.arena.model.Type;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class VideoQuestItemDialog extends QuestItemDialog {

	private TextBox videoTextBox;
	private Container container;

	public VideoQuestItemDialog(QuestItemDTO itemDTO, Container container,
			boolean readOnlyDialog, boolean create) {
		super(itemDTO, readOnlyDialog, create);
		this.container = container;
		if (container != null) {
			fillFormFromItem(container);
		}
		setText("Video details");
	}

	@Override
	protected List<FormTablePanel.Element> createFormPanels() {
		List<FormTablePanel.Element> result = new ArrayList<FormTablePanel.Element>();
		// result.add(createNamePanel());
		result.add(createVideoPanel("Video url (youtube)",
				"(in de formaat: http://www.youtube.com/watch?v=8tPnX7OPo0Q )"));
		// result.add(createRadiusPanel());
		// result.add(createVisibleRadiusPanel());
		return result;
	}

	protected FormTablePanel.Element createVideoPanel(String title, String help) {
		VerticalPanel vp = new VerticalPanel();
		videoTextBox = new TextBox();
		Label l = new Label(help);
		vp.add(videoTextBox);
		vp.add(l);
		return new FormTablePanel.Element(title, vp);
	}

	protected void fillFormFromItem(Container container) {
		//super.fillFormFromItem(itemDTO);
		videoTextBox.setText(container.getUrl());
	}

	protected QuestItemDTO fillItemFromForm(QuestItemDTO itemDTO) {
		QuestItemDTO result = super.fillItemFromForm(itemDTO);
		result.getItems().add(new Container(videoTextBox.getText()));
		// result.setObjectURL(videoTextBox.getText());
		return result;
	}

	@Entity
	public class Container extends Type {

		private String url;

		public Container(String url) {
			this.url = url;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

	}
}
