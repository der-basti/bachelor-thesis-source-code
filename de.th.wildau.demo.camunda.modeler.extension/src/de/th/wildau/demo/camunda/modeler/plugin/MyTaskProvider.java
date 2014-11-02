package de.th.wildau.demo.camunda.modeler.plugin;

import org.camunda.bpm.modeler.core.features.api.container.IFeatureContainer;
import org.camunda.bpm.modeler.plugin.AbstractCustomTaskProvider;
import org.camunda.bpm.modeler.plugin.palette.IPaletteIntegration;
import org.camunda.bpm.modeler.plugin.palette.PaletteIntegration;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.views.properties.tabbed.ISection;

public class MyTaskProvider extends AbstractCustomTaskProvider {

	@Override
	public String getId() {
		return "myTaskId";
	}

	@Override
	public String getTaskName() {
		return "TaskName";
	}

	@Override
	public boolean appliesTo(EObject eObject) {
		return PluginConstants.isMyCustomTask(eObject);
	}

	@Override
	public IPaletteIntegration getPaletteIntegration() {
		return PaletteIntegration.intoCompartmentNamed("TH-Wildau");
	}

	@Override
	public ISection getTabSection() {
		return new MyTabSection();
	}

	@Override
	public IFeatureContainer getFeatureContainer() {
		return new MyTaskFeatureContainer();
	}
}
