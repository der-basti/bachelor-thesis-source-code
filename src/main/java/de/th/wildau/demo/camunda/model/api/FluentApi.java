package de.th.wildau.demo.camunda.model.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

import org.camunda.bpm.engine.task.TaskQuery;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.Definitions;

public class FluentApi {

	public static void main(String[] args) throws FileNotFoundException {

		BpmnModelInstance model = create();
		Bpmn.validateModel(model);

		write(model);
		// process(model);
	}

	@SuppressWarnings("unused")
	private static void process(final BpmnModelInstance modelInstance) {
		// FIXME
		ProcessEngineRule processEngine = new ProcessEngineRule();
		// deploy process model
		processEngine.getRepositoryService().createDeployment()
				.addModelInstance("sampleProcess.bpmn", modelInstance).deploy();
		// start process model
		processEngine.getRuntimeService().startProcessInstanceByKey("sample");
		TaskQuery taskQuery = processEngine.getTaskService().createTaskQuery();
		// check and complete task
		org.junit.Assert.assertEquals(1, taskQuery.count());
		// Map<String, Object> variables = new HashMap<String, Object>();
		// variables.put("approved", true);
		processEngine.getTaskService().complete(
				taskQuery.singleResult().getId());
		// check if process instance is ended
		org.junit.Assert.assertEquals(0, processEngine.getRuntimeService()
				.createProcessInstanceQuery().count());
	}

	private static void write(final BpmnModelInstance modelInstance) {
		String xmlString = Bpmn.convertToString(modelInstance);
		Logger.getLogger(FluentApi.class.getSimpleName()).info(xmlString);

		File file = new File("src/main/resources/fluentApiProcess.bpmn");
		// OutputStream outputStream = new FileOutputStream(file);
		// Bpmn.writeModelToStream(outputStream, modelInstance);
		Bpmn.writeModelToFile(file, modelInstance);
	}

	/**
	 * The fluentApiModel has no DI schema informations.<br/>
	 * Supported BPMN elements:
	 * https://github.com/camunda/camunda-bpmn-model/tree/master/src/main
	 * /java/org/camunda/bpm/model/bpmn/instance}
	 */
	private static BpmnModelInstance create() {
		BpmnModelInstance modelInstance = Bpmn.createProcess().id("ProcessId")
				.name("ProcessName").startEvent().name("Start").userTask()
				.name("User Task").camundaAssignee("Fred").endEvent().done();

		// set targetNamespace
		Definitions definitions = modelInstance.getDefinitions();
		// modelInstance.newInstance(Definitions.class);
		definitions.setTargetNamespace("http://camunda.org/examples");
		modelInstance.setDefinitions(definitions);

		return modelInstance;
	}
}
