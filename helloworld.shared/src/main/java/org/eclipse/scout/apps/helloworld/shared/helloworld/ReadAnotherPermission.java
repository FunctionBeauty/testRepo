package org.eclipse.scout.apps.helloworld.shared.helloworld;

import java.security.BasicPermission;

public class ReadAnotherPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public ReadAnotherPermission() {
		super(ReadAnotherPermission.class.getSimpleName());
	}
}
