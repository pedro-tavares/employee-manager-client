package com.javalabs.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.javalabs.client.EmployeeManager;
import com.javalabs.client.ui.employee.EmployeeListPanel;
import com.javalabs.client.ui.person.PersonListPanel;

/**
 * Menu Panel
 * 
 * @author PATavares
 * @since Jan 2021
 */
public class MenuPanel extends HorizontalPanel {

	private Button 
		accountsButton,
		employeesButton;
	private PersonListPanel personPanel;
	private EmployeeListPanel employeePanel;
	
	public MenuPanel() {
		super();
		
		this.setStyleName("menuPanel");
		this.setSpacing(20);		
		this.init();
	}
	
	private void init() {
		accountsButton = new Button("Accounts");
		accountsButton.addClickHandler(event -> {
			if (personPanel == null) {
				personPanel = new PersonListPanel();
			}
			EmployeeManager.GET().showView(personPanel);
		});
		this.add(accountsButton);

		employeesButton = new Button("Employees");
		employeesButton.addClickHandler(event -> {
			if (employeePanel == null) {
				employeePanel = new EmployeeListPanel();
			}
			EmployeeManager.GET().showView(employeePanel);
		});
		this.add(employeesButton);		
	}
}
