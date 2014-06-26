package de.th.wildau.demo.activiti.designer.extension.palette;

import java.util.ArrayList;
import java.util.List;

import org.activiti.designer.integration.palette.AbstractDefaultPaletteCustomizer;
import org.activiti.designer.integration.palette.PaletteEntry;

/**
 * Customizes the de.th.wildau.demo.activiti.designer.extension.palette.
 * 
 * @author Sebastian Nemak
 */
public class PaletteCustomizer extends AbstractDefaultPaletteCustomizer {

	@Override
	public List<PaletteEntry> disablePaletteEntries() {
		List<PaletteEntry> result = new ArrayList<PaletteEntry>();
		// disable the ...
		//result.add(PaletteEntry.ALL);
		return result;
	}

}
