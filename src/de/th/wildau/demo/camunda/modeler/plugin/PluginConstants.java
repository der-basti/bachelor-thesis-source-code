package de.th.wildau.demo.camunda.modeler.plugin;

import org.camunda.bpm.modeler.runtime.engine.model.ModelPackage;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class PluginConstants {

	public static final EStructuralFeature CLASS_STRUCTURAL_FEATURE = ModelPackage.eINSTANCE
			.getDocumentRoot_Class();

	public static final String CLASS_VALUE = "de.th.wildau.demo.camunda.modeler.plugin";

	public static boolean isMyCustomTask(EObject eObject) {
		return eObject instanceof ServiceTask
				&& CLASS_VALUE.equals(eObject.eGet(CLASS_STRUCTURAL_FEATURE));
	}

}
