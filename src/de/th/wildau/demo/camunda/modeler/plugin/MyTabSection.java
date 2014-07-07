package de.th.wildau.demo.camunda.modeler.plugin;

import org.camunda.bpm.modeler.core.property.AbstractTabSection;
import org.camunda.bpm.modeler.ui.property.tabs.AbstractTabCompositeFactory;
import org.camunda.bpm.modeler.ui.property.tabs.util.FieldInjectionUtil;
import org.camunda.bpm.modeler.ui.property.tabs.util.PropertyUtil;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class MyTabSection extends AbstractTabSection {

	@Override
	protected Composite createCompositeForObject(Composite parent,
			EObject businessObject) {
		return new MyCustomTaskTabSectionFactory(this, parent)
				.createCompositeForBusinessObject((BaseElement) businessObject);
	}

	private static class MyCustomTaskTabSectionFactory extends
			AbstractTabCompositeFactory<BaseElement> {

		public MyCustomTaskTabSectionFactory(GFPropertySection section,
				Composite parent) {
			super(section, parent);
		}

		@Override
		public Composite createCompositeForBusinessObject(
				BaseElement baseElement) {

			Text endpointText = FieldInjectionUtil.createLongStringText(
					section, parent, "Label", "fieldName", baseElement);

			PropertyUtil
					.attachNoteWithLink(
							section,
							endpointText,
							"For more information look <a href=\"http://docs.camunda.org/latest/real-life/how-to/#modeler\">here</a>");

			return parent;
		}
	}
}
