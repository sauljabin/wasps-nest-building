/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of WaspsNestBuilding.
 * 
 * WaspsNestBuilding is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.exception;

public class InvalidFileFormatException extends Exception {

	private static final long serialVersionUID = -4649086203802798584L;

	public InvalidFileFormatException() {
		super();
	}

	public InvalidFileFormatException(String message) {
		super(message);
	}

}
