package struts_example;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.views.velocity.VelocityManager;
import org.apache.struts2.views.velocity.result.VelocityResult;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.ToolContext;
import org.apache.velocity.tools.ToolManager;
import org.apache.velocity.tools.view.ViewToolContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.apache.struts2.views.velocity.VelocityManager.KEY_VELOCITY_STRUTS_CONTEXT;

public class CustomVelocityResult extends VelocityResult {

    @Override
    protected Context createContext(VelocityManager velocityManager, ValueStack stack, HttpServletRequest request, HttpServletResponse response, String location) {
        Context context = createCustomToolContext(velocityManager, request, response);
        if (context == null) {
            context = velocityManager.createContext(stack, request, response);
        }
        return context;
    }

    protected Context createCustomToolContext(VelocityManager velocityManager, HttpServletRequest request, HttpServletResponse response) {
        ToolManager toolboxManager = velocityManager.getToolboxManager();

        if (toolboxManager == null) {
            return null;
        }
        ServletContext servletCtx;
        try {
            servletCtx = ServletActionContext.getServletContext();
        } catch (NullPointerException e) {
            return null;
        }
        if (servletCtx == null) {
            return null;
        }
        ToolContext toolContext = new ViewToolContext(velocityManager.getVelocityEngine(), request, response, servletCtx);
        toolContext.addToolbox(toolboxManager.getApplicationToolbox());
        toolContext.addToolbox(toolboxManager.getRequestToolbox());
        request.setAttribute(KEY_VELOCITY_STRUTS_CONTEXT, toolContext);
        return toolContext;
    }

}
