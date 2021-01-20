package com.javalabs.client.ui.employee;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.javalabs.client.ui.ModelType;
import com.javalabs.client.ui.NameValuePanel;

/**
 * Government Issued ID Panel
 * 
 * @author PATavares
 * @since Jan 2021
 */
public class GovernmentIssuedIdPanel extends VerticalPanel {

	private NameValuePanel panelIdentificationType;
	private FileUploadPanel frontOfPhotoIdUploadPanel;
	
	public GovernmentIssuedIdPanel() {
		this.setSpacing(10);		
		init();
	}
	
	private void init() {
		Label lblSubSection = new Label("Government issued id");
		lblSubSection.setStyleName("subTitledPanelLabel");
		this.add(lblSubSection);
		
		HTML htmlInfo = new HTML(
			"Your government issued ID must meet the following guidelines:<br>" + 
			"Photo of your ID must include date of birth and expiration date.<br>" + 
			"Acceptable forms of ID include: driver's license, government issued state ID or Passport<br>" + 
			"This form will not be accepted without a copy of personal identification to prove your age to be 18 or over.(19 in Alabama, Delaware and Nebraska, 21 in Mississippi and Puerto rico)."
		);
		this.add(htmlInfo);
		
		panelIdentificationType = new NameValuePanel("Identification type", ModelType.MULTI_CHOICE);
		((ListBox)panelIdentificationType.getWidgetValue()).addItem("Drivers License");
		((ListBox)panelIdentificationType.getWidgetValue()).addItem("Passport");
		((ListBox)panelIdentificationType.getWidgetValue()).addItem("Government Issued ID");
		this.add(panelIdentificationType);

		frontOfPhotoIdUploadPanel = new FileUploadPanel("Front of photo ID");
		this.add(frontOfPhotoIdUploadPanel);
	}

	public NameValuePanel getPanelIdentificationType() {
		return panelIdentificationType;
	}

	public FileUploadPanel getFrontOfPhotoIdUploadPanel() {
		return frontOfPhotoIdUploadPanel;
	}

	public void upload() {
		frontOfPhotoIdUploadPanel.upload();
	}
}
