package com.javalabs.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.javalabs.client.ui.CenterPanel;
import com.javalabs.client.ui.MenuPanel;
import com.javalabs.client.ui.employee.EmployeeEditPanel;
import com.javalabs.client.ui.employee.EmployeeListPanel;
import com.javalabs.client.ui.person.LoggedinPanel;
import com.javalabs.client.ui.person.LoginPanel;
import com.javalabs.shared.dto.Person;

/**
 * Employee Manager
 * The application entry point
 * 
 * @author PATavares
 * @since Jan 2021
 */
public class EmployeeManager implements EntryPoint {

	private static EmployeeManager INSTANCE;
	private static String HOST;
	private static Person USER;
	
	private static CenterPanel centerPanel = new CenterPanel();
	private static LoginPanel loginPanel = new LoginPanel();
	private static Image 
		centerImg,
		centerImgLoggedIn;
	//private static Image loginImg;
	private static HorizontalPanel topPanel;
	private static LoggedinPanel loggedinPanel;
	
	private static MenuPanel menuPanel = new MenuPanel();
	
	private static Panel lastViewPanel;
	
	@Override
	public void onModuleLoad() {
		INSTANCE = this;

		detectHOST_IP();
		
		//createUI();
		//loginPanel.autoLogin();

		doLaunch();
		
		doSTUFF();
	}

	public static EmployeeManager GET() {
		return INSTANCE;
	}

	public static Person GET_USER() {
		return USER;
	}
	
	public static String GET_HOST() {
		return HOST;
	}
	
	private void detectHOST_IP() {
		HOST = Window.Location.getHost();
		//Window.alert(HOST);
	}
	
	private void createUI() {
		//centerImg = new Image("images/javalabs_background.jpg");
		centerImg = new Image("images/employee_manager_background.jpg");
		centerImg.setStyleName("centerImg");
		RootPanel.get().add(centerImg, 0, 0);
		
		topPanel = new HorizontalPanel();
		topPanel.setStyleName("topPanel");
		topPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		topPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		Image logoImg = new Image("images/javalabs_employee_manager.jpg");
		logoImg.setStyleName("logoImg");
		logoImg.setPixelSize(400, 75);

		RootPanel.get().add(topPanel, 0, 0);
		
		topPanel.add(logoImg);
		topPanel.setCellHorizontalAlignment(logoImg, HasHorizontalAlignment.ALIGN_LEFT);
		
		loginPanel.setStyleName("loginPanel");
		topPanel.add(loginPanel);
		
		resize();
		
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				resize();				
			}
		});
		
		//createCenterPanel();
	}
	
	private void createCenterPanel() {
		RootPanel.get().add(centerPanel, 0, 150);
/*		
		//String userEmail = getCookie();
		String userEmail = "The Developer Advocate";
		
		if (userEmail != null) {
			USER = new User();
			USER.setEmail(userEmail);
		
			letsGo(USER.getEmail());
			
		} else {
			topPanel.add(loginPanel);
		}
*/		
	}
	
	private static void resize() {
	}
	
	public static void letsGo(String email) {
		
		USER = new Person(email);
		//Window.alert("USER: " + USER.toString());
		setCookie();
		
		//AuditFactory.log(AuditEvent.LOGIN_SUCCESS);
		
		loginPanel.removeFromParent();

		centerImg.removeFromParent();
		//centerImgLoggedIn = new Image("images/javalabs_background.jpg");
		centerImgLoggedIn = new Image("images/employee_manager_background.jpg");
		centerImgLoggedIn.setStyleName("centerImgLoggedin");
		RootPanel.get().add(centerImgLoggedIn, 0, 0);

		topPanel.removeFromParent();
		topPanel.setStyleName("topPanel");
		topPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		topPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		//RootPanel.get().add(topPanel, 0, 0);
		
		loggedinPanel = new LoggedinPanel(USER);
		loggedinPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		topPanel.add(loggedinPanel);
		
		doMenu();
	}
	
	public static void logOut() {
		//deleteCookie();
		
		centerImgLoggedIn.removeFromParent();
		//centerImg = new Image("images/javalabs_background.jpg");
		centerImg = new Image("images/employee_manager_background.jpg");
		centerImg.setStyleName("centerImg");
		RootPanel.get().add(centerImg, 0, 0);
		
		menuPanel.removeFromParent();
		if (lastViewPanel != null) {
			lastViewPanel.removeFromParent();
		}
		
		//loginPanel.clear();
		topPanel.remove(loggedinPanel);
		topPanel.add(loginPanel);
		
		//AuditFactory.log(AuditEvent.LOGOUT);
	}
	
	private static void setCookie() {
		if (Cookies.isCookieEnabled()) {
			final long DURATION = 1000 * 60 * 60 * 24 * 7; //duration remembering login - 1 week
			Date expires = new Date(System.currentTimeMillis() + DURATION);
	
			Cookies.setCookie("MALIBUITLABS", USER.getEmail(), expires, null, "/", false);
		}
	}
	
	private static String getCookie() {
		if (Cookies.isCookieEnabled()) {
			return Cookies.getCookie("JAVALABS_EMPLOYEE_MANAGER");
		}
		return null;
	}
	
	private static void deleteCookie() {
		if (Cookies.isCookieEnabled()) {
			Cookies.removeCookie("JAVALABS_EMPLOYEE_MANAGER");
		}
	}
	
	private static void doMenu() {
		RootPanel.get().add(menuPanel, 400, 12);
//		 resize();
	}

	private static EmployeeEditPanel employeeEditPanel;
	
	private static void doLaunch() {
		Label titleLbl = new Label("This is employment nework");
		RootPanel.get().add(titleLbl, 10, 10);
		
		Hyperlink submissionFormLink = new Hyperlink("Please use employee submission form", "employee_submission_form");
		submissionFormLink.addClickHandler(event -> {
			titleLbl.removeFromParent();
			submissionFormLink.removeFromParent();
			
			if (employeeEditPanel == null) {
				employeeEditPanel = new EmployeeEditPanel(new EmployeeListPanel(), null);
			}
			EmployeeManager.GET().showView(employeeEditPanel);
		});
		RootPanel.get().add(submissionFormLink, 10, 30);
		
	}
	
	public void showView(Panel viewPanel) {
		if (centerImgLoggedIn != null) {
			centerImgLoggedIn.removeFromParent();
		}
		if (lastViewPanel != null) {
			lastViewPanel.removeFromParent();
		}
		//Window.alert("showView:\n" + viewPanel.toString());
		lastViewPanel = viewPanel;
		//viewPanel.setPixelSize(Window.getClientWidth()-200, Window.getClientHeight()-75);
		if (topPanel != null) {
			RootPanel.get().add(viewPanel, 0, 76);
		} else {
			RootPanel.get().add(viewPanel, 0, 0);
		}
	}

	// EXPERIMENTS *************************************************************************************************
	private void doSTUFF() {
		
	}
}
