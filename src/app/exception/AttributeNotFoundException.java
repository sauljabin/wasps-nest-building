/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of WaspsNestBuilding.
 * 
 * WaspsNestBuilding is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.exception;

public class AttributeNotFoundException extends Exception {

	private static final long serialVersionUID = 6137117068095547641L;

	public AttributeNotFoundException() {
		super();
	}

	public AttributeNotFoundException(String message) {
		super(message);
	}

}
