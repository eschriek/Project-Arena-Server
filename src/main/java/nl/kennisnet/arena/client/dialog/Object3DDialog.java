package nl.kennisnet.arena.client.dialog;

import java.util.ArrayList;
import java.util.List;

import nl.kennisnet.arena.client.domain.PoiDTO;
import nl.kennisnet.arena.client.widget.FormTablePanel;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;

public class Object3DDialog extends QuestItemDialog {

//	private final static String FILEUPLOADID = "arenafileupload";
//
//	private FileUpload upload;
//	private FormPanel formPanel;
//	private TextBox[] settings;
//	private TextBox[] rotation;
//	private RadioButton blended;
//
	public Object3DDialog(PoiDTO itemDTO, boolean readOnlyDialog,
			boolean create) {
		super(itemDTO, readOnlyDialog, create);
		setText("3D Object details");
	}
//
//	@Override
//	protected List<FormTablePanel.Element> createFormPanels() {
//		List<FormTablePanel.Element> result = new ArrayList<FormTablePanel.Element>();
//		result.add(createNamePanel());
//		result.add(fileUploadPanel());
//		result.addAll(createSettingsPanel());
//		result.addAll(createRotationPanel());
//		result.add(createRadiusPanel());
//		result.add(createVisibleRadiusPanel());
//		return result;
//	}
//
//	protected List<FormTablePanel.Element> createSettingsPanel() {
//		List<FormTablePanel.Element> items = new ArrayList<FormTablePanel.Element>();
//
//		blended = new RadioButton("blended");
//
//		settings = new TextBox[2];
//		settings[0] = new TextBox();
//		settings[1] = new TextBox();
//
//		settings[0].setName("Schaal");
//		settings[1].setName("Altitude");
//
//		for (TextBox t : settings) {
//			items.add(new FormTablePanel.Element(t.getName(), t));
//		}
//
//		items.add(new FormTablePanel.Element("Geblendeerd", blended));
//
//		return items;
//	}
//
//	protected List<FormTablePanel.Element> createRotationPanel() {
//		List<FormTablePanel.Element> items = new ArrayList<FormTablePanel.Element>();
//		rotation = new TextBox[3];
//		rotation[0] = new TextBox();
//		rotation[1] = new TextBox();
//		rotation[2] = new TextBox();
//
//		rotation[0].setName("X : ");
//		rotation[1].setName("Y : ");
//		rotation[2].setName("Z : ");
//
//		for (TextBox t : rotation) {
//			items.add(new FormTablePanel.Element(t.getName(), t));
//		}
//
//		return items;
//	}
//
//	protected FormTablePanel.Element fileUploadPanel() {
//		formPanel = new FormPanel();
//		Panel panel = new HorizontalPanel();
//		formPanel.setWidget(panel);
//
//		// TODO: replace with configuration URL
//		String completeURL = Window.Location.getHref();
//		String baseURL = completeURL.substring(0, completeURL.lastIndexOf('/'));
//		formPanel.setAction(baseURL + "/fileserver/fileUpload");
//		formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
//		formPanel.setMethod(FormPanel.METHOD_POST);
//
//		upload = new FileUpload();
//		upload.setName("uploadFormElement");
//		upload.getElement().setId(FILEUPLOADID);
//		upload.unsinkEvents(Event.ONCHANGE);
//		sinkEvents(Event.ONCHANGE);
//
//		panel.add(upload);
//
//		formPanel
//				.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
//					public void onSubmitComplete(SubmitCompleteEvent event) {
//						getPoiDTO().setObjectURL(event.getResults());
//					}
//				});
//
//		return new FormTablePanel.Element("Bestand", formPanel);
//
//	}
//
//	public void onBrowserEvent(Event event) {
//		if (event.getTarget() != null
//				&& FILEUPLOADID.equals(event.getTarget().getId())) {
//			switch (DOM.eventGetType(event)) {
//			case Event.ONCHANGE:
//				String filename = upload.getFilename();
//				if (filename == null) {
//					Window.alert("Kies eerst een bestand");
//				} else if (!(filename.toLowerCase().endsWith(".obj") || filename
//						.toLowerCase().endsWith(".txt"))) {
//					Window.alert("Bestandsformaat is niet toegestaan!");
//				} else {
//					formPanel.submit();
//				}
//				break;
//			default:
//				break;
//			}
//		}
//	}
//
//	protected void fillFormFromItem(PoiDTO itemDTO) {
//		super.fillFormFromItem(itemDTO);
//		if (itemDTO.getObjectURL() != null) {
//			System.out.println(itemDTO.getObjectURL());
//		}
//
//		if (itemDTO.getBlended() != null) {
//			blended.setValue((itemDTO.getBlended() == 0) ? false : true);
//		}
//
//		if (itemDTO.getSchaal() != null) {
//			settings[0].setText("" + itemDTO.getSchaal());
//		} else if (itemDTO.getAlt() != null) {
//			settings[1].setText("" + itemDTO.getAlt());
//		}
//
//		if (itemDTO.getRotation() != null) {
//			rotation[0].setText("" + (itemDTO.getRotation())[0]);
//			rotation[1].setText("" + (itemDTO.getRotation())[1]);
//			rotation[2].setText("" + (itemDTO.getRotation())[2]);
//		}
//
//	}
//
//	protected PoiDTO fillItemFromForm(PoiDTO itemDTO) {
//		PoiDTO result = super.fillItemFromForm(itemDTO);
//		if (blended.getValue() == true) {
//			result.setBlended(1);
//		} else {
//			result.setBlended(0);
//		}
//
//		try {
//			if (!settings[0].getText().isEmpty()
//					|| !settings[1].getText().isEmpty()) {
//				result.setSchaal(Float.valueOf(settings[0].getText()));
//				result.setAlt(Double.valueOf(settings[1].getText()));
//
//				Float[] rotatie = new Float[3];
//				rotatie[0] = Float.valueOf(rotation[0].getText());
//				rotatie[1] = Float.valueOf(rotation[1].getText());
//				rotatie[2] = Float.valueOf(rotation[2].getText());
//
//				result.setRotation(rotatie);
//			}
//		} catch (NumberFormatException e) {
//			Window.alert("Foute waardes");
//		}
//
//		return result;
//	}
//
//	protected void refresh() {
//		clear();
//		setWidget(createPanel(getPoiDTO()));
//		fillFormFromItem(getPoiDTO());
//	}
}
