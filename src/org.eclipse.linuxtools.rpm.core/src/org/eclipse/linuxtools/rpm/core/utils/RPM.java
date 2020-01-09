/*******************************************************************************
 * Copyright (c) 2005, 2010 Red Hat, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat - initial API and implementation
 *******************************************************************************/
package org.eclipse.linuxtools.rpm.core.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.linuxtools.rpm.core.IRPMConstants;
import org.eclipse.linuxtools.rpm.core.RPMConfiguration;
import org.eclipse.linuxtools.rpm.core.RPMCorePlugin;

/**
 * A utility class for executing RPM commands.
 * 
 */
public class RPM {

	private static final String DEFINE = "--define"; //$NON-NLS-1$
	private String[] macroDefines;
	private String rpmCmd;

	/**
	 * Constructs a new RPM object.
	 * 
	 * @param config
	 *            the RPM configuration to use
	 */
	public RPM(RPMConfiguration config) {
		IEclipsePreferences node = new DefaultScope().getNode(RPMCorePlugin.ID);
		rpmCmd = node.get(IRPMConstants.RPM_CMD, ""); //$NON-NLS-1$
		String[] tmpMacroDefines = {
				rpmCmd,
				"-v", //$NON-NLS-1$
				DEFINE, "_sourcedir " //$NON-NLS-1$
						+ config.getSourcesFolder().getLocation().toOSString(),
				DEFINE, "_srcrpmdir " + //$NON-NLS-1$
						config.getSrpmsFolder().getLocation().toOSString(),
				DEFINE, "_builddir " + //$NON-NLS-1$
						config.getBuildFolder().getLocation().toOSString(),
				DEFINE, "_rpmdir " + //$NON-NLS-1$
						config.getRpmsFolder().getLocation().toOSString(),
				DEFINE, "_specdir " + //$NON-NLS-1$
						config.getSpecsFolder().getLocation().toOSString() };
		this.macroDefines = tmpMacroDefines;
	}

	/**
	 * Installs a given source RPM
	 * 
	 * @param sourceRPM The src.rpm file to install.
	 * @return The output of the install command.
	 * @throws CoreException If something fails.
	 */
	public String install(IFile sourceRPM) throws CoreException {
		List<String> command = new ArrayList<String>();
		command.addAll(Arrays.asList(macroDefines));
		command.add("-i"); //$NON-NLS-1$
		command.add(sourceRPM.getLocation().toOSString());
		try {
			return Utils.runCommandToString(command
					.toArray(new String[command.size()]));
		} catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR, RPMCorePlugin.ID,
					e.getMessage(), e));
		}
	}
}
