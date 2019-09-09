package cl.go.sport.api.controllers.forms;

public interface RepeatPasswordForm {
	String getPassword();
	String getRepeatPassword();
	void setPassword(String password);
	void setRepeatPassword(String repeatPassword);
}
