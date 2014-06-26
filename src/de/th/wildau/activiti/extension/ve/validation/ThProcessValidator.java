package de.th.wildau.activiti.extension.ve.validation;

import org.activiti.bpmn.model.StartEvent;
import org.activiti.designer.eclipse.extension.validation.AbstractProcessValidator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.Diagram;

public class ThProcessValidator extends AbstractProcessValidator {

	@Override
	public String getFormatName() {
		// TODO Auto-generated method stub
		return "FormatName";
	}

	@Override
	public String getValidatorId() {
		// TODO Auto-generated method stub
		return "ValidatorId";
	}

	@Override
	public String getValidatorName() {
		// TODO Auto-generated method stub
		return "ValidatorName";
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean validateDiagram(Diagram diagram, IProgressMonitor arg1) {
		// TODO Auto-generated method stub

		// Demonstrates progress using the monitor
		for (int i = 0; i < 5; i++) {
			try {
				Thread.currentThread().sleep(2000l);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getDiagramWorkerContext().getProgressMonitor().worked(5);
		}
		getDiagramWorkerContext().getProgressMonitor().done();

		// Clear problems for this diagram first
		// clearMarkers(getDiagramResource());

		final EList<EObject> contents = diagram.getContainer().eContents();
		// final EList<EObject> contents =
		// getResourceForDiagram(diagram).getContents();
		for (final EObject object : contents) {
			if (object instanceof StartEvent) {
				// Perform some validations for StartEvents
				addProblemToDiagram("message", ((StartEvent) object).getId());
			}
			// Other node types and validations
		}

		addInfoToDiagram("markerMessage", "marker.getNodeId()");

		return false;
	}

}
