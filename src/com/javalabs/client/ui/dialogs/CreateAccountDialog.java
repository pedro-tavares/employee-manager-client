package com.javalabs.client.ui.dialogs;

import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.javalabs.client.service.ServiceFactory;
import com.javalabs.client.ui.ModelType;
import com.javalabs.client.ui.NameValuePanel;
import com.javalabs.shared.dto.Email;
import com.javalabs.shared.dto.Person;

/**
 * Create Person Account Dialog
 * 
 * @author PATavares
 * @since Jan 2021
 */
public class CreateAccountDialog {

	private String 
		email,
		password;
	private NameValuePanel 
		panelEmail,
		panelFirstName,
		panelSurname,
		panelPassword;
	private Button okButton;
	private DialogBox dialogBox;
	private Label messageLabel;
	
	public CreateAccountDialog(String email, String password) {
		this.email = email;
		this.password = password;
		
		messageLabel = new Label();
		messageLabel.addStyleName("errorLbl");
		
		dialogBox = createDialogBox();
	}
	
	public void show() {
		dialogBox.center();
	}
	
	private DialogBox createDialogBox() {
	    // Create a dialog box and set the caption text
	    final DialogBox dialogBox = new DialogBox();
	    dialogBox.setText("Create Account");

	    // Create a table to layout the content
	    VerticalPanel dialogContents = new VerticalPanel();
	    dialogContents.setSpacing(4);
	    dialogBox.setWidget(dialogContents);

	    panelEmail = new NameValuePanel("Email", ModelType.STRING);
	    ((TextBox)panelEmail.getWidgetValue()).setText(this.email);
	    dialogContents.add(panelEmail);
	    
	    panelFirstName = new NameValuePanel("First Name", ModelType.STRING);
	    dialogContents.add(panelFirstName);
	    
	    panelSurname = new NameValuePanel("Surname", ModelType.STRING);
	    dialogContents.add(panelSurname);
	    
	    panelPassword = new NameValuePanel("Password", ModelType.PASSWORD);
	    ((PasswordTextBox)panelPassword.getWidgetValue()).setText(this.password);
	    dialogContents.add(panelPassword);

	    NameValuePanel panelConfirmPassword = new NameValuePanel("Confirm Password", ModelType.PASSWORD);	
	    dialogContents.add(panelConfirmPassword);
	    
	    HorizontalPanel buttonPanel = new HorizontalPanel();
	    
	    okButton = new Button("OK", new ClickHandler() {
	          public void onClick(ClickEvent event) {
	        	  if (((TextBox)panelEmail.getWidgetValue()).getText().equals("")) {
	        		  messageLabel.setText("Please specify email.");
	        		  return;
	        	  }
	        	  if (((TextBox)panelFirstName.getWidgetValue()).getText().equals("")) {
	        		  messageLabel.setText("Please specify first name.");
	        		  return;
	        	  }
	        	  if (((TextBox)panelSurname.getWidgetValue()).getText().equals("")) {
	        		  messageLabel.setText("Please specify surname.");
	        		  return;
	        	  }
	        	  if (((PasswordTextBox)panelPassword.getWidgetValue()).getText().equals("")) {
	        		  messageLabel.setText("Please specify password.");
	        		  return;
	        	  }
	        	  if (!((PasswordTextBox)panelPassword.getWidgetValue()).getText().equals(((PasswordTextBox)panelConfirmPassword.getWidgetValue()).getText())) {
	        		  messageLabel.setText("Passwords do not match!");
	        		  return;
	        	  }
	        	  
	        	  callPersonExistsService();
	          }
	       }
	    );

	    Button cancelButton = new Button("Cancel", new ClickHandler() {
	          public void onClick(ClickEvent event) {
	        	  dialogBox.hide();
	          }
	       }
	    );
	    
	    buttonPanel.setSpacing(5);
	    
	    buttonPanel.add(okButton);
	    buttonPanel.add(cancelButton);
	    
	    buttonPanel.setCellHorizontalAlignment(okButton, HasHorizontalAlignment.ALIGN_RIGHT);
	    buttonPanel.setCellHorizontalAlignment(cancelButton, HasHorizontalAlignment.ALIGN_RIGHT);

	    dialogContents.add(buttonPanel);
	    dialogContents.setCellHorizontalAlignment(buttonPanel, HasHorizontalAlignment.ALIGN_RIGHT);
	    
	    dialogContents.add(messageLabel);
	    
	    return dialogBox;
	}	
	
	private void callPersonExistsService() {
		okButton.getElement().getStyle().setCursor(Cursor.WAIT);
		RootPanel.getBodyElement().getStyle().setCursor(Cursor.WAIT);
		
		ServiceFactory.PERSON_SERVICE.userExists(new Email(this.email), new MethodCallback<Person>() {

			@Override
			public void onSuccess(org.fusesource.restygwt.client.Method method, Person response) {
				okButton.getElement().getStyle().setCursor(Cursor.DEFAULT);
				RootPanel.getBodyElement().getStyle().setCursor(Cursor.DEFAULT);
				
				//Window.alert("" + response);
				//Window.alert(response ? "Person Exists!" : "Person NOT Exists!");
				
				if (response != null) {
					messageLabel.setText("Person already exists!");
					return;
				}
				
				callPersonSaveService();
			}

			@Override
			public void onFailure(org.fusesource.restygwt.client.Method method, Throwable exception) {
				okButton.getElement().getStyle().setCursor(Cursor.DEFAULT);
				RootPanel.getBodyElement().getStyle().setCursor(Cursor.DEFAULT);
				
				messageLabel.setStyleName("errorLbl");
				messageLabel.setText(exception.getMessage());
			}
			
		});
	}

	private void callPersonSaveService() {
		okButton.getElement().getStyle().setCursor(Cursor.WAIT);
		RootPanel.getBodyElement().getStyle().setCursor(Cursor.WAIT);
		
		Person user = new Person(
			((TextBox)panelFirstName.getWidgetValue()).getText(),
			((TextBox)panelSurname.getWidgetValue()).getText(),
			((TextBox)panelEmail.getWidgetValue()).getText(),
			((TextBox)panelPassword.getWidgetValue()).getText()
		);

		ServiceFactory.PERSON_SERVICE.save(user, new MethodCallback<Person>() {

			@Override
			public void onSuccess(org.fusesource.restygwt.client.Method method, Person response) {
				okButton.getElement().getStyle().setCursor(Cursor.DEFAULT);
				RootPanel.getBodyElement().getStyle().setCursor(Cursor.DEFAULT);
				
				//Window.alert("" + response);
				
				if (response != null) {
					messageLabel.setStyleName("infoLbl");
					messageLabel.setText("User saved!");
				}
			}

			@Override
			public void onFailure(org.fusesource.restygwt.client.Method method, Throwable exception) {
				okButton.getElement().getStyle().setCursor(Cursor.DEFAULT);
				RootPanel.getBodyElement().getStyle().setCursor(Cursor.DEFAULT);
				
				messageLabel.addStyleName("errorLbl");
				messageLabel.setText("Error saving User:\n" + exception.getMessage());
			}
			
		});
	}

}
