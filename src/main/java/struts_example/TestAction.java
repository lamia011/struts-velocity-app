package struts_example;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {

	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEscapeMe() {
		return "\"; alert('I was not escaped !!'); var a = \"";
	}

	@Override
	public String execute() throws Exception {
		name = "TEST";
        ActionContext.getContext().put("name", "Hello from context!");      
		return SUCCESS;
	}
}
