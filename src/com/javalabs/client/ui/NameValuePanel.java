package com.javalabs.client.ui;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

/**
 * A auto-configuring Name Value Panel
 * 
 * @author PATavares
 * @since Jan 2021
 */
public class NameValuePanel extends VerticalPanel {

	private ModelType modelType;
	private RefDataModelType refDataModelType;
	private Boolean reverseLabel;
	private Widget widgetValue; 

	public NameValuePanel(String name, ModelType type) {
		this(name, type, null, false);
	}

	public NameValuePanel(String name, ModelType type, Boolean reverseLabel) {
		this(name, type, null, reverseLabel);
	}
	
	public NameValuePanel(String name, ModelType modelType, RefDataModelType refDataModelType, Boolean reverseLabel) {
		this.modelType = modelType;
		this.refDataModelType = refDataModelType;
		this.reverseLabel = reverseLabel;
		
		Label labelName = new Label(name);
		if (!reverseLabel) {
			this.add(labelName);
		}
		
		switch(modelType) {
			case STRING:
				widgetValue = new TextBox();
				widgetValue.setWidth("300px");
				break;
			case INTEGER:
				break;
			case BOOLEAN:
				widgetValue = new CheckBox();
				break;
			case PASSWORD:
				widgetValue = new PasswordTextBox();
				widgetValue.setWidth("300px");
				break;
			case DATE:
				DateTimeFormat dateFormat = DateTimeFormat.getLongDateTimeFormat();
			    DateBox dateBox = new DateBox();
			    dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
			    dateBox.getDatePicker().setYearArrowsVisible(true);
			    widgetValue = dateBox;
			    break;
			case MULTI_CHOICE:
				widgetValue = new ListBox();
				break;
			default:
				widgetValue = this.constructorForRefData(name, refDataModelType);
		}
			
		if (reverseLabel) {
			HorizontalPanel panelReverseLabel = new HorizontalPanel();
			panelReverseLabel.add(widgetValue);
			panelReverseLabel.setCellVerticalAlignment(widgetValue, HasVerticalAlignment.ALIGN_MIDDLE);
			panelReverseLabel.add(labelName);
			panelReverseLabel.setCellVerticalAlignment(labelName, HasVerticalAlignment.ALIGN_MIDDLE);			
			this.add(panelReverseLabel);
		} else {
			this.add(widgetValue);
		}
	}

	@SuppressWarnings("incomplete-switch")
	private Widget constructorForRefData(String name, RefDataModelType type) {
		this.modelType = ModelType.REF_DATA;
		
		// Define the oracle that finds suggestions
	    MultiWordSuggestOracle oracle = new MultiWordSuggestOracle(); // NOT Larry

	    switch(type) {
	    }

	    return new SuggestBox(oracle);
	}
	
	public Widget getWidgetValue() {
		return widgetValue;
	}

}
