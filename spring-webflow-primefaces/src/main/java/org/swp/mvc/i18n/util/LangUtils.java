package org.swp.mvc.i18n.util;

import java.util.Locale;

import javax.faces.context.FacesContext;

import org.springframework.context.i18n.LocaleContextHolder;

public class LangUtils {

	public Locale getClientLocale() {
		return LocaleContextHolder.getLocale();
		// return FacesContext.getCurrentInstance().getExternalContext()
		// .getRequestLocale();
	}

	public String getLangCode() {
		return getClientLocale().getLanguage();
	}

	/**
	 * the language direction
	 * 
	 * @return RTL or LTR.
	 */
	public String getLangDir() {
		return LangDirectionResolverSingleton.resolveDirection(
				getClientLocale()).name();
	}

	/**
	 * Current client's language justification.
	 * 
	 * @return <code>"right"</code> or <code>"left"</code> depending on client's
	 *         locale.
	 */
	public String getLangJustify() {
		String langDir = getLangDir();
		if (langDir.equals("RTL")) {
			return "right";
		} else {
			return "left";
		}
	}
}
