package com.javalabs.client.ui.employee;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Upload Your Photos Panel
 * 
 * @author PATavares
 * @since Jan 2021
 */
public class UploadYourPhotosPanel extends VerticalPanel {

	private Map<Integer, FileUploadPanel> mapOfFileUploadPanel = new HashMap<Integer, FileUploadPanel>();
	
	public UploadYourPhotosPanel() {
		this.setSpacing(10);
		
		init();
	}
	
	private void init() {
		Label lblSubSection = new Label("Upload your photos");
		lblSubSection.setStyleName("subTitledPanelLabel");
		this.add(lblSubSection);
		
		HTML htmlInfo = new HTML(
			"Please upload a minimum of 4 photos of yourself<br>" + 
			"Photo guidelines:<br>" + 
			"Full color, unretouched images<br>" + 
			"Recently photographed within the last 3 months<br>" +
			"No other subjects should appear in photos (includes people or animals)<br>" +
			"File format in .jpg<br>" + 
			"File size limit(per image): 10 megabyte or less"
		);
		this.add(htmlInfo);
		
		for (int i=1; i<11; i++) {
			FileUploadPanel fileUploadPanel = new FileUploadPanel("Photo " + i);
			mapOfFileUploadPanel.put(i, fileUploadPanel);
			this.add(fileUploadPanel);
		}
	}
	
	public Map<Integer, FileUploadPanel> getMapOfFileUploadPanel() {
		return mapOfFileUploadPanel;
	}
	
	public void upload() {
		for (Integer key: mapOfFileUploadPanel.keySet()) {
			mapOfFileUploadPanel.get(key).upload();
		}
	}
}
