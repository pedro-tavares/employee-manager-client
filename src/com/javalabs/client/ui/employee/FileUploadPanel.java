package com.javalabs.client.ui.employee;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * File Upload Panel
 * 
 * @author PATavares
 * @since Jan 2021
 */
public class FileUploadPanel extends HorizontalPanel {

	private Boolean autoSubmit;
	private String name;
	private FileUpload fileUpload = new FileUpload();
	private FormPanel form = new FormPanel();

	public FileUploadPanel(String name) {
		this(name, false);
	}
	
	public FileUploadPanel(String name, Boolean autoSubmit) {
		this.name = name;
		this.autoSubmit = autoSubmit;

		init();
	}

	private void init() {
		Label lblName = new Label(name);
		lblName.setStyleName("fileUploadLabel");
		this.add(lblName);

		// choose file and setup upload
		//form.setAction(GWT.getModuleBaseURL() + "fileupload");
		form.setAction("http://localhost:8081/employee/fileupload");
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);
		
		fileUpload.setName("file");
		fileUpload.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				//Window.alert(fileUpload.getFilename());
				if (autoSubmit) {
					form.submit();
				}
			}
		});
		form.setWidget(fileUpload);			
		this.add(form);

		// separate submit
		/*
		 panel.add(new Button("Submit", new ClickListener() { 
		 	public void onClick(Widget sender) { 
		 		//Window.alert("Submit"); 
		 		form.submit(); } 
		 	}));
		 */

		form.addSubmitHandler(new FormPanel.SubmitHandler() {
			public void onSubmit(SubmitEvent event) {
				// This event is fired just before the form is submitted. 
				// We can take this opportunity to perform validation.
/*
				if (tb.getText().length() == 0) {
					Window.alert("The text box must not be empty");
					event.cancel();
				}
*/
			}
		});
		form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			public void onSubmitComplete(SubmitCompleteEvent event) {
				// When the form submission is successfully completed, this event is fired. 
				// Assuming the service returned a response of type text/html, we can get the result text here 
				// (see the FormPanel documentation for further explanation).
				//Window.alert("SUBMITTED:" + event.getResults());
			}
		});
	}

	public FileUpload getFileUpload() {
		return fileUpload;
	}

	public void upload() {
		if (!fileUpload.getFilename().equals("")) {
			form.submit();
		}
	}
}
