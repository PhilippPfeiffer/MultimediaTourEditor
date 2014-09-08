package pfeiffer.mte.api;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *  This class handles error and warning messages displayed to the user.
 * @author Philipp Pfeiffer
 */
public class Messages {
    
    private String locale = "EN";
    
    /**
     * Returns the locale
     * @return String of the locale
     */
    public String getLocale() {
        return this.locale;
    }
    
    /**
     * Replaces the locale with newLocale
     * @param newLocale 
     */
    public void setLocale(String newLocale) {
        this.locale = newLocale;
    }
    
    /**
     * Returns the FacesContext of the current session
     * @return FacesContext
     */
    protected FacesContext getFacesContext() {
        
        return FacesContext.getCurrentInstance();
        
    }
    
    /**
     * Adds a new message to FacesContext. The message will be displayed globally.
     * @param summary 
     */
    protected void setinfoMessage(String summary) {
        
        getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
        
    }
    
    /**
     * Returns A message fitting for the current situation and chooses what
     * message should be displayed bases on the the currently set locale.
     * @param messageType
     * @return String of a message
     */
    public String getString(String messageType) {
        if(messageType.equals("NO_PLACES_FOUND")) {
            if(locale.equals("EN")) {
                return "No places with that name were found.";
            } else if(locale.equals("DE")) {
                return "Es wurden keine Lokalitäten für diesen Ort gefunden.";
            } else {
                return "";
            }
        } else {
            return "";
        }
    }
    
}
