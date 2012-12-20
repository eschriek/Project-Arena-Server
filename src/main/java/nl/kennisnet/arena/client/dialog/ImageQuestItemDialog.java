package nl.kennisnet.arena.client.dialog;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import nl.kennisnet.arena.client.domain.QuestItemDTO;
import nl.kennisnet.arena.client.widget.FormTablePanel;
import nl.kennisnet.arena.model.Type;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ImageQuestItemDialog extends QuestItemDialog {

	private final static String FILEUPLOADID = "arenafileupload";

	private Image imageDisplay;
	private FileUpload upload;
	private FormPanel formPanel;
	private String link;
	private Container container;

	public ImageQuestItemDialog(QuestItemDTO itemDTO, Container container,
			boolean readOnly, boolean create) {
		super(itemDTO, readOnly, create);
		this.container = container;
		setText("Foto details");
		if (container != null) {
			refresh();
		}

	}

	@Override
	protected List<FormTablePanel.Element> createFormPanels() {
		List<FormTablePanel.Element> result = new ArrayList<FormTablePanel.Element>();
		// result.add(createNamePanel());
		result.add(fileUploadPanel());
		result.add(createImagePanel());
		// result.add(createRadiusPanel());
		// result.add(createVisibleRadiusPanel());
		return result;
	}

	protected FormTablePanel.Element createImagePanel() {
		final Panel result = new VerticalPanel();
		Panel panel = new HorizontalPanel();

		result.add(panel);
		imageDisplay = new Image("", 0, 0, 320, 200);
		result.add(imageDisplay);
		return new FormTablePanel.Element("", result);
	}

	protected FormTablePanel.Element fileUploadPanel() {
		formPanel = new FormPanel();
		Panel panel = new HorizontalPanel();
		formPanel.setWidget(panel);

		// TODO: replace with configuration URL
		String completeURL = Window.Location.getHref();
		String baseURL = completeURL.substring(0, completeURL.lastIndexOf('/'));
		formPanel.setAction(baseURL + "/fileserver/fileUpload");
		formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
		formPanel.setMethod(FormPanel.METHOD_POST);

		upload = new FileUpload();
		upload.setName("uploadFormElement");
		upload.getElement().setId(FILEUPLOADID);
		upload.unsinkEvents(Event.ONCHANGE);
		sinkEvents(Event.ONCHANGE);

		panel.add(upload);

		formPanel
				.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
					public void onSubmitComplete(SubmitCompleteEvent event) {
						// getQuestItemDTO().setObjectURL(event.getResults());
						link = event.getResults();
						Window.alert("Submit succesvol! " + event.getResults());
						refresh();
					}
				});

		return new FormTablePanel.Element("Bestand", formPanel);

	}

	public void onBrowserEvent(Event event) {
		if (event.getTarget() != null
				&& FILEUPLOADID.equals(event.getTarget().getId())) {
			switch (DOM.eventGetType(event)) {
			case Event.ONCHANGE:
				String filename = upload.getFilename();
				if (filename == null) {
					Window.alert("Kies eerst een bestand");
				} else if (!(filename.toLowerCase().endsWith(".jpg") || filename
						.toLowerCase().endsWith(".png"))) {
					Window.alert("Bestandsformaat is niet toegestaan!");
				} else {
					formPanel.submit();
					imageDisplay.setUrlAndVisibleRect("loading2.gif", 0, 0,
							320, 200);
				}
				break;
			default:
				break;
			}
		}
	}

	protected void fillFormFromItem(QuestItemDTO itemDTO) {
		super.fillFormFromItem(itemDTO);
		if (container != null) {
			if (container.getLink() != null) {
				System.out.println(container.getLink());
				imageDisplay.setUrlAndVisibleRect(container.getLink(), 0, 0,
						320, 200);
			}
		}
		// if (itemDTO.getObjectURL() != null) {
		// imageDisplay.setUrlAndVisibleRect(itemDTO.getObjectURL(), 0, 0,
		// 320, 200);
		// }
	}

	protected QuestItemDTO fillItemFromForm(QuestItemDTO itemDTO) {
		QuestItemDTO result = super.fillItemFromForm(itemDTO);
		itemDTO.getItems().add(new Container(link));
		return result;
	}

	private void refresh() {
		clear();
		setWidget(createPanel(getQuestItemDTO()));
		fillFormFromItem(getQuestItemDTO());
	}

	@Entity
	public class Container extends Type {

		private String link;

		public Container(String link) {
			this.link = link;
		}
		
		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

	}

}
