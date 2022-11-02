package com.mycompany.whiteboard;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author juan
 */
public class AccessDataViewModel {
    private final StringProperty username = new SimpleStringProperty();
	private final StringProperty password = new SimpleStringProperty();
        private final StringProperty name = new SimpleStringProperty();
	private final ReadOnlyBooleanWrapper writePossible = new ReadOnlyBooleanWrapper();

	public AccessDataViewModel() {
		writePossible.bind(username.isNotEmpty().and(password.isNotEmpty().and(name.isNotEmpty())));
	}

	public StringProperty usernameProperty() {
		return username;
	}

	public StringProperty passwordProperty() {
		return password;
	}
        
        public StringProperty nameProperty() {
                return name;
        }

	public ReadOnlyBooleanProperty isWritePossibleProperty() {
		return writePossible.getReadOnlyProperty();
	}
}
