package de.th.wildau.demo.eclipse.extension.preferences;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jface.action.Separator;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.th.wildau.demo.eclipse.extension.Activator;

public class THWildauPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public THWildauPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("TH-Wildau Preferences Description");
	}

	@Override
	protected void initialize() {
		super.initialize();
	}

	@Override
	public boolean performOk() {
		return super.performOk();
	}

	// private void addSeparator(Composite parent) {
	// Composite c = new Composite(parent, style)
	// Label separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
	// GridData gridData = new GridData();
	// gridData.horizontalAlignment = GridData.FILL;
	// gridData.grabExcessHorizontalSpace = true;
	// separator.setLayoutData(gridData);
	// }

	@Override
	public void createFieldEditors() {
		addField(new BooleanFieldEditor(PreferenceConstants.DEMO_BOOLEAN,
				"&Boolean field", getFieldEditorParent()));

		addField(new StringFieldEditor(PreferenceConstants.DEMO_TEXT,
				"&String field", getFieldEditorParent()));

		addField(new ComboFieldEditor(PreferenceConstants.DEMO_COMBO,
				"&Combo field", getLogLevelValues(), getFieldEditorParent()));

		addField(new FileFieldEditor(PreferenceConstants.DEMO_FILE,
				"&File field", getFieldEditorParent()));
	}

	public void init(IWorkbench workbench) {
		// nothing to do
	}

	private String[][] generateEntryNameValue(Collection<String> elements) {
		String[][] results = new String[elements.size()][2];
		Iterator<String> ite = elements.iterator();
		int i = 0;

		while (ite.hasNext()) {
			String next = ite.next();
			results[i][0] = next;
			results[i][1] = next;
			i++;
		}
		return results;
	}

	private String[][] getLogLevelValues() {
		return generateEntryNameValue(Arrays.asList(new String[] { "off",
				"debug", "info", "warn", "error", "fatal" }));
	}
}