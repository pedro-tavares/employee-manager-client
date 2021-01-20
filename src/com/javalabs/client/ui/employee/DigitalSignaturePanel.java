package com.javalabs.client.ui.employee;

import java.util.Date;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.javalabs.client.ui.ModelType;
import com.javalabs.client.ui.NameValuePanel;

/**
 * Panel Config Digital Signature
 * 
 * @author PATavares
 * @since Jan 2021
 */
public class DigitalSignaturePanel extends VerticalPanel {

	private NameValuePanel panelIAgree;
	private NameValuePanel panelSignature;
	private NameValuePanel panelDate;
	
	public DigitalSignaturePanel() {
		this.setSpacing(10);
		init();
	}
	
	private void init() {
		Label lblSubSection = new Label("Digital Signature");
		lblSubSection.setStyleName("subTitledPanelLabel");
		this.add(lblSubSection);
		
		HTML htmlInfo = new HTML(
			"By checking this box, I agree that I am at least 18 years of age or the age of majority where I live,<br>" + 
			"That I am submitting my information for casting throughout the organization<br>" + 
			"And their affiliated network of licensees/partners.<br>"
		);
		this.add(htmlInfo);

		panelIAgree = new NameValuePanel("I agree with the terms listed above", ModelType.BOOLEAN, true);
		this.add(panelIAgree);		
		
		panelSignature = new NameValuePanel("Signature", ModelType.STRING);
		((TextBox)panelSignature.getWidgetValue()).setText("");
		this.add(panelSignature);
		
		panelDate = new NameValuePanel("Date", ModelType.DATE);
		((DateBox)panelDate.getWidgetValue()).setValue(new Date());
		this.add(panelDate);		
	}
	
	public NameValuePanel getPanelIAgree() {
		return panelIAgree;
	}

	public NameValuePanel getPanelSignature() {
		return panelSignature;
	}

	public NameValuePanel getPanelDate() {
		return panelDate;
	}

	
}
