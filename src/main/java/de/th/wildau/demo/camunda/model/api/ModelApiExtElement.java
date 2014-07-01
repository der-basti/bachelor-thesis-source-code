package de.th.wildau.demo.camunda.model.api;

import java.io.File;
import java.util.Map.Entry;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.xml.impl.util.XmlQName;

import com.sun.istack.internal.logging.Logger;

/**
 * Use the camunda model api to extends the elements with BPsim parameter.
 */
public class ModelApiExtElement {

	public static void main(String[] args) {
		BpmnModelInstance modelInstance = read();

		modelInstance.getDocument().registerNamespace("bpsim",
				"http://www.bpsim.org/schemas/1.0");

		// XmlQName xmlName = new XmlQName(modelInstance.getDocument(), "", "");
		// modelInstance.getDocument().getRootElement().getChildElements().add(new
		// SimElement());

		Bpmn.writeModelToFile(new File(
				"src/main/resources/modelApiExtElementProcess.bpmn"),
				modelInstance);
	}

	private static BpmnModelInstance read() {
		BpmnModelInstance modelInstance = Bpmn.readModelFromFile(new File(
				"src/main/resources/sampleProcess.bpmn"));

		// print infos
		System.out.println(modelInstance.getDocument().getRootElement()
				.getPrefix());
		for (Entry<String, String> e : XmlQName.KNOWN_PREFIXES.entrySet()) {
			Logger.getLogger(ModelApiExtElement.class).info(
					e.getKey() + " : " + e.getValue());
		}

		return modelInstance;
	}

}
