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
package org.eclipse.linuxtools.rpm.rpmlint.resolutions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator2;

public class RpmlintMarkerResolutionGenerator implements IMarkerResolutionGenerator2 {

	// rpmlint error id atribut name
	public static final String RPMLINT_ERROR_ID = "rpmlintErrorId"; //$NON-NLS-1$
	
	// rpmlint refered  text
	public static final String RPMLINT_REFFERED_CONTENT = "rpmlintrefferedContent"; //$NON-NLS-1$
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IMarkerResolutionGenerator2#hasResolutions(org.eclipse.core.resources.IMarker)
	 */
	public boolean hasResolutions(IMarker marker) {
		String rpmlintErrorId = getRpmlintErrorId(marker);
		if (rpmlintErrorId.equals(SetupNotQuiet.ID)) {
            return true;
		} else if (rpmlintErrorId.equals(PatchNotApplied.ID)) {
			return true;
		}  else if (rpmlintErrorId.equals(NoBuildrootTag.ID)) {
			return true;
		} else if (rpmlintErrorId.equals(NoCleaningOfBuildroot.ID)){
			return true;
		} else if (rpmlintErrorId.equals(NoBuildSection.ID)){
			return true;
		} else if (rpmlintErrorId.equals(MacroInChangelog.ID)){
			return true;
		} else if (rpmlintErrorId.equals(RpmBuildrootUsage.ID)){
			return true;
		} else if (rpmlintErrorId.equals(HardcodedPrefixTag.ID)){
			return true;
		} else if (rpmlintErrorId.equals(HardcodedPackagerTag.ID)){
			return true;
		} else if (rpmlintErrorId.equals(NoPrepSection.ID)){
			return true;
		} else if (rpmlintErrorId.equals(NoInstallSection.ID)){
			return true;
		} else if (rpmlintErrorId.equals(NoCleanSection.ID)){
			return true;
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IMarkerResolutionGenerator#getResolutions(org.eclipse.core.resources.IMarker)
	 */
	public IMarkerResolution[] getResolutions(IMarker marker) {
		List<IMarkerResolution> resolutions = new ArrayList<IMarkerResolution>();
		String rpmlintErrorId = getRpmlintErrorId(marker);
		if (rpmlintErrorId.equals(SetupNotQuiet.ID)) {
			resolutions.add(new SetupNotQuiet());
		} else if (rpmlintErrorId.equals(PatchNotApplied.ID)) {
			resolutions.add(new PatchNotApplied());
		} else if (rpmlintErrorId.equals(NoBuildrootTag.ID)) {
			resolutions.add(new NoBuildrootTag());
		} else if (rpmlintErrorId.equals(NoCleaningOfBuildroot.ID)){
			resolutions.add(new NoCleaningOfBuildroot());
		} else if (rpmlintErrorId.equals(NoBuildSection.ID)){
			resolutions.add(new NoBuildSection());
		} else if (rpmlintErrorId.equals(MacroInChangelog.ID)){
			resolutions.add(new MacroInChangelog());
		} else if (rpmlintErrorId.equals(RpmBuildrootUsage.ID)){
			resolutions.add(new RpmBuildrootUsage());
		} else if (rpmlintErrorId.equals(HardcodedPrefixTag.ID)){
			resolutions.add(new HardcodedPrefixTag());
		} else if (rpmlintErrorId.equals(HardcodedPackagerTag.ID)){
			resolutions.add(new HardcodedPackagerTag());
		} else if (rpmlintErrorId.equals(NoPrepSection.ID)){
			resolutions.add(new NoPrepSection());
		} else if (rpmlintErrorId.equals(NoInstallSection.ID)){
			resolutions.add(new NoInstallSection());
		} else if (rpmlintErrorId.equals(NoCleanSection.ID)){
			resolutions.add(new NoCleanSection());
		}

		return resolutions.toArray(new IMarkerResolution[resolutions.size()]);
	}
	
	/**
	 * Return the rpmlint error id attribute for the specified marker
	 * 
	 * @param marker the marker to check
	 * @return the rpmlint error id or <code>""</code> if none
	 */
	private String getRpmlintErrorId(IMarker marker) {
		return marker.getAttribute(RPMLINT_ERROR_ID, ""); //$NON-NLS-1$
	}

}
