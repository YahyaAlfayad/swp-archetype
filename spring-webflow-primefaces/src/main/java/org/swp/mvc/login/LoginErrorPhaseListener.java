package org.swp.mvc.login;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;

public class LoginErrorPhaseListener implements PhaseListener {
	private static Logger logger = LoggerFactory
			.getLogger(LoginErrorPhaseListener.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 9041341678769385287L;

	public void afterPhase(PhaseEvent arg0) {

	}

	public void beforePhase(PhaseEvent arg0) {
		Exception e = (Exception) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap()
				.get(WebAttributes.AUTHENTICATION_EXCEPTION);
		if (e instanceof BadCredentialsException) {
			logger.info("Login failed reason: Bad credentials");
			FacesContext cntx = FacesContext.getCurrentInstance();
			String errorMessage = cntx.getApplication()
					.getResourceBundle(cntx, "loginMsg")
					.getString("invalidMessage");
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap()
					.put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage,
							""));
		}
	}

	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}
