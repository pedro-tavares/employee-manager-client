package com.javalabs.client.ui.employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.javalabs.client.service.ServiceFactory;
import com.javalabs.client.ui.TitledPanel;
import com.javalabs.shared.dto.Employee;

/**
 * Employee List Service
 * 
 * @author PATavares
 * @since Jan 2021
 */
public class EmployeeListPanel extends TitledPanel {

	private List<Employee> EMPLOYEE_DATA;
	private Employee selectedEmployee;
	
	private HorizontalPanel panelTable = new HorizontalPanel();
	private CellTable<Employee> table;
	private EmployeeEditPanel panelEmployeeEdit;
	private Button 
		buttonNewEmployee,
		buttonDeleteEmployee;

	private Label labelFectchInfo = new Label("Fetching...");
	private Date startDate;
	private Date endDate;
	
	public EmployeeListPanel() {
		super("Employees");

		this.setSpacing(20);		
		this.init();

		callGetEmployeesService();
	}
	
	private void init() {

		  table = new CellTable<Employee>();
		  table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
		  TextColumn<Employee> idColumn = new TextColumn<Employee>() {
		     @Override
		     public String getValue(Employee object) {
		        return object.getId() != null ? object.getId().toString() : "";
		     }
		  };
		  table.addColumn(idColumn, "ID");

		  TextColumn<Employee> nameColumn = new TextColumn<Employee>() {
		     @Override
		     public String getValue(Employee object) {
		        return object.getName();
		     }
		  };
		  table.addColumn(nameColumn, "First Name");

		  TextColumn<Employee> surnameColumn = new TextColumn<Employee>() {
		     @Override
		     public String getValue(Employee object) {
		        return object.getSurname();
		     }
		  };
		  table.addColumn(surnameColumn, "Last Name");
		  
		  TextColumn<Employee> dobColumn = new TextColumn<Employee>() {
		     @Override
		     public String getValue(Employee object) {
		        return object.getDob() != null ? object.getDob().toLocaleString() : "";
		     }
		  };
		  table.addColumn(dobColumn, "Date of Birth");
		  
		  TextColumn<Employee> cityColumn = new TextColumn<Employee>() {
		     @Override
		     public String getValue(Employee object) {
		        return object.getCity();
		     }
		  };
		  table.addColumn(cityColumn, "Current City");
		  		  
		  /*
		  SimplePager.Resources resources = GWT.create(SimplePager.Resources.class); 
		  SimplePager simplePager = new SimplePager(TextLocation.CENTER, resources , false, 0, true);
		  simplePager.setDisplay(table);
		  simplePager.setPageSize(5);
		  this.add(simplePager);
		  */

		  // Add a selection model to handle Employee selection.
		  final SingleSelectionModel<Employee> selectionModel = new SingleSelectionModel<Employee>();
		  table.setSelectionModel(selectionModel);
		  selectionModel.addSelectionChangeHandler(
			  new SelectionChangeEvent.Handler() {
			     public void onSelectionChange(SelectionChangeEvent event) {
			    	Employee selected = selectionModel.getSelectedObject();
			        if (selected != null) {
			        	//Window.alert("You selected: " + selected.getId());
			        	selectedEmployee = selected;
			        	initModelPanel(selected);
			        	//buttonDeleteSite.setEnabled(true);
			        }
			     }
			  }
		  );
		  
		  //panel.setBorderWidth(1);
		  panelTable.setSpacing(10);
		  panelTable.add(table);

		  //this.add(labelFectchInfo);

		  // Buttons
		  buttonNewEmployee = new Button("New Employee");
		  buttonNewEmployee.addClickHandler(event -> {
			  initModelPanelNewEmployee();
		  });

		  buttonDeleteEmployee = new Button("Delete Employee");
		  buttonDeleteEmployee.addClickHandler(event -> {
			  callDeleteEmployeeService();
		  });
		  
		  HorizontalPanel panelButtons = new HorizontalPanel();
		  panelButtons.setSpacing(10);
		  panelButtons.add(buttonNewEmployee);
//		  panelButtons.add(buttonDeleteUser);
		  
		  this.add(panelButtons);

		  this.add(panelTable);	
	}		

	private void initModelPanelNewEmployee() {
		if (panelEmployeeEdit != null) {
			panelEmployeeEdit.removeFromParent();
		}
		
		panelEmployeeEdit = new EmployeeEditPanel(this, null);
		panelTable.add(panelEmployeeEdit);
	}
	
	private void initModelPanel(Employee employee) {
		if (panelEmployeeEdit != null) {
			panelEmployeeEdit.removeFromParent();
		}
		
		panelEmployeeEdit = new EmployeeEditPanel(this, this.selectedEmployee);
		panelTable.add(panelEmployeeEdit);
	}
	
	public void setModel(List<Employee> model) {
		EMPLOYEE_DATA = model;
		
		GWT.log("SITE_DATA.size: " + EMPLOYEE_DATA.size());
		
		table.setPageSize(5000);
		table.setRowData(0, EMPLOYEE_DATA);
		table.setRowCount(EMPLOYEE_DATA.size(), true);
	}
	
	public boolean employeeExists(String email) {
		for (Employee employeeData: EMPLOYEE_DATA) {
			if (employeeData.getEmail().equals(email)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void refresh() {
		callGetEmployeesService();
	}

	private void callGetEmployeesService() {
//		RootPanel.getBodyElement().getStyle().setCursor(Cursor.WAIT);
		
		List<Employee> employeeList = new ArrayList<Employee>();
		Employee employee1 = new Employee("Employee 1 Name", "Employee 1 Surname", "employee1@gmail.com");
		employee1.setId(1L);
		Employee employee2 = new Employee("Employee 2 Name", "Employee 2 Surname", "employee2@gmail.com");
		employee2.setId(2L);
		Employee employee3 = new Employee("Employee 3 Name", "Employee 3 Surname", "employee3@gmail.com");
		employee3.setId(3L);
		
		employeeList.add(employee1);
		employeeList.add(employee2);
		employeeList.add(employee3);
		
		setModel(employeeList);
		return;
/*				
		startDate = new Date();
		
		ServiceFactory.EMPLOYEE_SERVICE.flux(new MethodCallback<List<Employee>>() {

			@Override
			public void onSuccess(Method method, List<Employee> response) {
				RootPanel.getBodyElement().getStyle().setCursor(Cursor.DEFAULT);
				
				//Window.alert("callGetEmployeesService - SUCCESS\n");
				//GWT.log(response.toString());
				setModel(response);
				
				endDate = new Date();
				labelFectchInfo.setText("Fetched " + response.size() + " Sites in " + 
						(endDate.getTime()-startDate.getTime()) + "ms"
				);
			}

			@Override
			public void onFailure(Method method, Throwable exception) {
				RootPanel.getBodyElement().getStyle().setCursor(Cursor.DEFAULT);
				
		        Window.alert("callGetEmployeesService - FAILURE:\n"  + method.getResponse().getText());
			}
		});		
*/	
	}

	private void callDeleteEmployeeService() {
		RootPanel.getBodyElement().getStyle().setCursor(Cursor.WAIT);
		
		ServiceFactory.EMPLOYEE_SERVICE.delete(selectedEmployee, new MethodCallback<List<Employee>>() {

			@Override
			public void onSuccess(Method method, List<Employee> response) {
				RootPanel.getBodyElement().getStyle().setCursor(Cursor.DEFAULT);
				
				//Window.alert("callDeleteEmployeeService - SUCCESS\n");
				
				setModel(response);
			}

			@Override
			public void onFailure(Method method, Throwable exception) {
				RootPanel.getBodyElement().getStyle().setCursor(Cursor.DEFAULT);
				
		        Window.alert("callDeleteEmployeeService - FAILURE:\n"  + method.getResponse().getText());
			}
		});		
	}
	
}
