/*
 * © Copyright 2014, Philipp Pfeiffer
 * 
 * This file is part of MultimediaTourEditor.

    MultimediaTourEditor is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    MultimediaTourEditor is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    
    You should have received a copy of the GNU General Public License
    along with MultimediaTourEditor.  If not, see <http://www.gnu.org/licenses/>.
 */

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
