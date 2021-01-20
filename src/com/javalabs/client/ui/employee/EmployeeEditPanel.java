package com.javalabs.client.ui.employee;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.javalabs.client.service.ServiceFactory;
import com.javalabs.client.ui.ModelType;
import com.javalabs.client.ui.NameValuePanel;
import com.javalabs.client.ui.TitledPanel;
import com.javalabs.shared.dto.Employee;

/**
 * Employee Edit Panel
 * 
 * @author PATavares
 * @since Jan 2021
 */
public class EmployeeEditPanel extends TitledPanel {

	private boolean isNew;
	
	private Employee employee;
	private EmployeeListPanel parent;
	
	private NameValuePanel panelName;
	private NameValuePanel panelSurname;
	private NameValuePanel panelDOB;
	private NameValuePanel panelCity;
	private NameValuePanel panelState;
	private NameValuePanel panelCountry;
	private NameValuePanel panelUSCitizen;
	private NameValuePanel panelPhone;
	private NameValuePanel panelEmail;
	private NameValuePanel panelPreferredContact;
	private NameValuePanel panelOccupation;
	private NameValuePanel panelWebsite;
	private NameValuePanel panelInstagram;

	private UploadYourPhotosPanel panelUploadYourPhotos = new UploadYourPhotosPanel();
	private GovernmentIssuedIdPanel panelGovernmentIssuedId = new GovernmentIssuedIdPanel();
	private DigitalSignaturePanel panelDigitalSignature = new DigitalSignaturePanel();

	private Button submitApplicationButton = new Button("Submit application");
	private Label msgLabel = new Label("");
	
	public EmployeeEditPanel(EmployeeListPanel parent, Employee employee) {
		super(employee == null ? "Employee submission form" : "Edit Employee");

		this.parent = parent;
		this.employee = employee;
	
		this.setSpacing(10);
		
		init();
	}
	
	private void init() {
		if (employee == null) { // create new
			isNew = true;
			employee = new Employee();
		}

		VerticalPanel panelEmployeeSection = new VerticalPanel();
		panelEmployeeSection.setSpacing(10);
		
		panelName = new NameValuePanel("First Name", ModelType.STRING);
		((TextBox)panelName.getWidgetValue()).setText(employee.getName());
		panelEmployeeSection.add(panelName);

		panelSurname = new NameValuePanel("Last Name", ModelType.STRING);
		((TextBox)panelSurname.getWidgetValue()).setText(employee.getSurname());
		panelEmployeeSection.add(panelSurname);

		panelDOB = new NameValuePanel("Date of Birth", ModelType.DATE);
		((DateBox)panelDOB.getWidgetValue()).setValue(employee.getDob() != null ? employee.getDob() : null);
		panelEmployeeSection.add(panelDOB);

		panelCity = new NameValuePanel("Current City", ModelType.STRING);
		((TextBox)panelCity.getWidgetValue()).setText(employee.getCity());
		panelEmployeeSection.add(panelCity);

		panelState = new NameValuePanel("State/Province", ModelType.STRING);
		((TextBox)panelState.getWidgetValue()).setText(employee.getState());
		panelEmployeeSection.add(panelState);

		panelCountry = new NameValuePanel("Country", ModelType.STRING);
		((TextBox)panelCountry.getWidgetValue()).setText(employee.getCountry());
		panelEmployeeSection.add(panelCountry);

		panelUSCitizen = new NameValuePanel("US Citizen", ModelType.MULTI_CHOICE);
		((ListBox)panelUSCitizen.getWidgetValue()).addItem("Yes");
		((ListBox)panelUSCitizen.getWidgetValue()).addItem("No");		
		panelEmployeeSection.add(panelUSCitizen);

		panelPhone = new NameValuePanel("Phone", ModelType.STRING);
		((TextBox)panelPhone.getWidgetValue()).setText(employee.getPhone());
		panelEmployeeSection.add(panelPhone);

		panelEmail = new NameValuePanel("Email", ModelType.STRING);
		((TextBox)panelEmail.getWidgetValue()).setText(employee.getEmail());
		panelEmployeeSection.add(panelEmail);
		
		panelPreferredContact = new NameValuePanel("Preferred Contact", ModelType.MULTI_CHOICE);
		((ListBox)panelPreferredContact.getWidgetValue()).addItem("Phone");
		((ListBox)panelPreferredContact.getWidgetValue()).addItem("Email");
		panelEmployeeSection.add(panelPreferredContact);
		
		Label lblSubSection = new Label("Additional Information");
		lblSubSection.setStyleName("subTitledPanelLabel");
		panelEmployeeSection.add(lblSubSection);

		panelOccupation = new NameValuePanel("Occupation", ModelType.STRING);
		((TextBox)panelOccupation.getWidgetValue()).setText(employee.getOccupation());
		panelEmployeeSection.add(panelOccupation);

		panelWebsite = new NameValuePanel("Website", ModelType.STRING);
		((TextBox)panelWebsite.getWidgetValue()).setText(employee.getWebsite());
		panelEmployeeSection.add(panelWebsite);

		panelInstagram = new NameValuePanel("Instagram", ModelType.STRING);
		((TextBox)panelInstagram.getWidgetValue()).setText(employee.getInstagram());
		panelEmployeeSection.add(panelInstagram);
		
		panelEmployeeSection.add(panelUploadYourPhotos);
		panelEmployeeSection.add(panelGovernmentIssuedId);
		panelEmployeeSection.add(panelDigitalSignature);
		
		if (isNew) { // extra logic on new 
		}
		
		this.add(panelEmployeeSection);

		submitApplicationButton.addClickHandler(event -> {
			if (this.isNew && parent.employeeExists(((TextBox)panelName.getWidgetValue()).getText())) {
				this.msgLabel.setText("Employee already exists!");
				return;
			}
			
			employee.setName(((TextBox)panelName.getWidgetValue()).getText());
			employee.setSurname(((TextBox)panelSurname.getWidgetValue()).getText());
			employee.setDob(((DateBox)panelDOB.getWidgetValue()).getValue());
			employee.setCity(((TextBox)panelCity.getWidgetValue()).getText());
			employee.setState(((TextBox)panelState.getWidgetValue()).getText());
			employee.setCountry(((TextBox)panelCountry.getWidgetValue()).getText());
			employee.setUsCitizen(((ListBox)panelUSCitizen.getWidgetValue()).getSelectedItemText().equals("Yes"));
			employee.setPhone(((TextBox)panelPhone.getWidgetValue()).getText());
			employee.setEmail(((TextBox)panelEmail.getWidgetValue()).getText());
			employee.setPreferredContact(((ListBox)panelPreferredContact.getWidgetValue()).getSelectedItemText());
			employee.setOccupation(((TextBox)panelOccupation.getWidgetValue()).getText());
			employee.setWebsite(((TextBox)panelWebsite.getWidgetValue()).getText());
			employee.setInstagram(((TextBox)panelInstagram.getWidgetValue()).getText());

			String fakePath = "C:\\fakepath\\"; // path used for tmp upload
			employee.setPhoto1(panelUploadYourPhotos.getMapOfFileUploadPanel().get(1).getFileUpload().getFilename().replace(fakePath, ""));
			employee.setPhoto2(panelUploadYourPhotos.getMapOfFileUploadPanel().get(2).getFileUpload().getFilename().replace(fakePath, ""));
			employee.setPhoto3(panelUploadYourPhotos.getMapOfFileUploadPanel().get(3).getFileUpload().getFilename().replace(fakePath, ""));
			employee.setPhoto4(panelUploadYourPhotos.getMapOfFileUploadPanel().get(4).getFileUpload().getFilename().replace(fakePath, ""));
			employee.setPhoto5(panelUploadYourPhotos.getMapOfFileUploadPanel().get(5).getFileUpload().getFilename().replace(fakePath, ""));
			employee.setPhoto6(panelUploadYourPhotos.getMapOfFileUploadPanel().get(6).getFileUpload().getFilename().replace(fakePath, ""));
			employee.setPhoto7(panelUploadYourPhotos.getMapOfFileUploadPanel().get(7).getFileUpload().getFilename().replace(fakePath, ""));
			employee.setPhoto8(panelUploadYourPhotos.getMapOfFileUploadPanel().get(8).getFileUpload().getFilename().replace(fakePath, ""));
			employee.setPhoto9(panelUploadYourPhotos.getMapOfFileUploadPanel().get(9).getFileUpload().getFilename().replace(fakePath, ""));
			employee.setPhoto10(panelUploadYourPhotos.getMapOfFileUploadPanel().get(10).getFileUpload().getFilename().replace(fakePath, ""));
			
			employee.setGovernmentIssuedIdType(((ListBox)panelGovernmentIssuedId.getPanelIdentificationType().getWidgetValue()).getSelectedItemText());
			employee.setFrontOfPhotoId(panelGovernmentIssuedId.getFrontOfPhotoIdUploadPanel().getFileUpload().getFilename().replace(fakePath, ""));
			employee.setDigitalSignature(((TextBox)panelDigitalSignature.getPanelSignature().getWidgetValue()).getText());
			employee.setDigitalSignatureDate(((DateBox)panelDigitalSignature.getPanelDate().getWidgetValue()).getValue());
			
			callEmployeeSaveService();
			
		});
		this.add(submitApplicationButton);

		msgLabel.setStyleName("errorLbl");
		this.add(msgLabel);
	}
		
	private boolean validate() {
		/*
		if (((TextBox)panelName.getWidgetValue()).getText().equals("")) {
			this.msgLabel.setText("First Name cannot be empty!");
			return false;
		}
		*/
		if (((TextBox)panelEmail.getWidgetValue()).getText().equals("")) {
			msgLabel.setStyleName("errorLbl");
			this.msgLabel.setText("Email cannot be empty");
			return false;
		}
		if (((CheckBox)panelDigitalSignature.getPanelIAgree().getWidgetValue()).getValue().equals(false)) {
			msgLabel.setStyleName("errorLbl");
			this.msgLabel.setText("Please agree with the terms");
			return false;
		}
		if (((TextBox)panelDigitalSignature.getPanelSignature().getWidgetValue()).getText().equals("")) {
			msgLabel.setStyleName("errorLbl");
			this.msgLabel.setText("Please complete Signature");
			return false;
		}
		this.msgLabel.setText("");
		
		return true;
	}

	public void showCursorWAIT() {
		submitApplicationButton.getElement().getStyle().setCursor(Cursor.WAIT);
		RootPanel.getBodyElement().getStyle().setCursor(Cursor.WAIT);	
	}

	public void showCursorDEFAULT() {
		submitApplicationButton.getElement().getStyle().setCursor(Cursor.DEFAULT);
		RootPanel.getBodyElement().getStyle().setCursor(Cursor.DEFAULT);	
	}
	
	private void callEmployeeSaveService() {
		showCursorWAIT();
		
		if (!validate()) {
			showCursorDEFAULT();
			return;
		}
		
		GWT.log("callEmployeeSaveService()");
		GWT.log("employee email:" + employee.getEmail());
		GWT.log("employee photo  1:" + employee.getPhoto1());
		GWT.log("employee photo  2:" + employee.getPhoto2());
		GWT.log("employee photo  3:" + employee.getPhoto3());
		GWT.log("employee photo  4:" + employee.getPhoto4());
		GWT.log("employee photo  5:" + employee.getPhoto5());
		GWT.log("employee photo  6:" + employee.getPhoto6());
		GWT.log("employee photo  7:" + employee.getPhoto7());
		GWT.log("employee photo  8:" + employee.getPhoto8());
		GWT.log("employee photo  9:" + employee.getPhoto9());
		GWT.log("employee photo 10:" + employee.getPhoto10());
		//GWT.log("employee front of photo id:" + employee.getFrontOfPhotoId());
		
		ServiceFactory.EMPLOYEE_SERVICE.save(employee, new MethodCallback<Employee>() {

			@Override
			public void onSuccess(Method method, Employee response) {
				showCursorDEFAULT();
				
				GWT.log("callEmployeeSaveService() onSuccess:\n" + response.toString());
				//Window.alert("Employee " + employee.getEmail() + " saved!");
				
				parent.refresh();
				
				msgLabel.setStyleName("infoLbl");
				msgLabel.setText("Employee Saved! with id:" + response.getId());

				//We have saved the Employee, do the file uploads
				panelUploadYourPhotos.upload();
				panelGovernmentIssuedId.upload();
			}

			@Override
			public void onFailure(Method method, Throwable e) {
				showCursorDEFAULT();

				GWT.log("callEmployeeSaveService() onFailure:\n" + e.toString());			
			}
		});
	}
	
}
