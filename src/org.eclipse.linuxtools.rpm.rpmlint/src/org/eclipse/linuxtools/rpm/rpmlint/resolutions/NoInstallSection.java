/*******************************************************************************
 * Copyright (c) 2008 Alexander Kurtakov.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Alexander Kurtakov - initial API and implementation
 *******************************************************************************/
package org.eclipse.linuxtools.rpm.rpmlint.resolutions;

import java.util.List;

import org.eclipse.linuxtools.rpm.ui.editor.SpecfileEditor;
import org.eclipse.linuxtools.rpm.ui.editor.parser.SpecfileSection;

public class NoInstallSection extends AInsertLineResolution {
	public static final String ID = "no-%install-section"; //$NON-NLS-1$

	public String getDescription() {
		return Messages.NoInstallSection_0;
	}

	public String getLabel() {
		return ID;
	}

	@Override
	public String getLineToInsert() {
		return "%install\n\n"; //$NON-NLS-1$
	}

	@Override
	public int getLineNumberForInsert(SpecfileEditor editor) {
		List<SpecfileSection> sections = editor.getSpecfile().getSections();
		for (SpecfileSection section : sections) {
			if (section.getName().equals("clean")) { //$NON-NLS-1$
				return section.getLineNumber();
			}
		}
		return 0;
	}
}
