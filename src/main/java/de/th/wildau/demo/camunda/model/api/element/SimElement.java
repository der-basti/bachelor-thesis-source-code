package de.th.wildau.demo.camunda.model.api.element;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.impl.BpmnModelInstanceImpl;
import org.camunda.bpm.model.xml.ModelBuilder;
import org.camunda.bpm.model.xml.ModelInstance;
import org.camunda.bpm.model.xml.impl.ModelImpl;
import org.camunda.bpm.model.xml.impl.util.DomUtil;

public class SimElement {

	public static void main(String[] args) {

		// type register
		ModelBuilder modelBuilder = ModelBuilder.createInstance("modelName");
		// FIXME org.camunda.bpm.model.xml.ModelException: Element type
		// simNamespace:simType has no id attribute
		BpmnModelInstance modelInstance = new BpmnModelInstanceImpl(
				(ModelImpl) modelBuilder.build(), modelBuilder,
				DomUtil.getEmptyDocument(DocumentBuilderFactory.newInstance()));

		Simulation.registerType(modelBuilder);
		Simulation sim = create(modelInstance, "myId", "myName");
		modelInstance.setDocumentElement(sim);

		Bpmn.writeModelToFile(new File("src/main/resources/simElement.bpmn"),
				modelInstance);
	}

	public static Simulation create(final ModelInstance modelInstance,
			final String id, final String name) {
		Simulation sim = modelInstance.newInstance(Simulation.class);
		sim.setId(id);
		sim.setName(name);
		return sim;
	}
}
