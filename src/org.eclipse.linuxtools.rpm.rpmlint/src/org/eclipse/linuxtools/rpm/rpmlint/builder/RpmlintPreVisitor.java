/*******************************************************************************
 * Copyright (c) 2007 Alphonse Van Assche.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Alphonse Van Assche - initial API and implementation
 *******************************************************************************/
package org.eclipse.linuxtools.rpm.rpmlint.builder;

import java.util.ArrayList;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.linuxtools.rpm.rpmlint.Activator;

public class RpmlintPreVisitor implements IResourceVisitor {
	
	private ArrayList<String> paths = new ArrayList<String>();

	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IResourceVisitor#visit(org.eclipse.core.resources.IResource)
	 */
	public boolean visit(IResource resource) {
		if (Activator.SPECFILE_EXTENSION.equals(resource.getFileExtension())
				|| Activator.RPMFILE_EXTENSION.equals(resource
						.getFileExtension())) {
			// we previsiting resource to be able to run rpmlint command
			// only once. That improve drasticaly the perfs.
			paths.add(resource.getLocation().toOSString());
		}
		return true;	
	}

	public ArrayList<String> getVisitedPaths() {
		return paths;
	}
}
