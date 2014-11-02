package de.th.wildau.demo.activiti.designer.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;

public class ProcessTestActivitiReview {

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();

	@Test
	public void startProcess() throws Exception {
		File file = new File("src/main/resources/diagrams/MyProcess.bpmn");

		RepositoryService repositoryService = activitiRule
				.getRepositoryService();
		Deployment deploy = repositoryService
				.createDeployment()
				.addInputStream("activitiReview.bpmn20.xml",
						new FileInputStream(file.getAbsolutePath())).deploy();
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("name", "Activiti");

		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("my-process", variableMap);
		assertNotNull(processInstance.getId());

		System.out.println("ProcessInstance id=" + processInstance.getId()
				+ " / " + processInstance.getProcessDefinitionId() + " date="
				+ deploy.getDeploymentTime());

		// repositoryService.activateProcessDefinitionById(processInstance.getProcessDefinitionId());
		// // already is state 'active'

		TaskService taskService = activitiRule.getTaskService();
		Task task = taskService.createTaskQuery().singleResult();

		assertEquals("Demo Task", task.getName());
		assertEquals("usertask1", processInstance.getActivityId());

		// change owner
		task.setOwner("flenders");
		taskService.saveTask(task);
		task = taskService.createTaskQuery().singleResult();
		assertEquals("flenders", task.getOwner());

		taskService.complete(task.getId());
		// http://forums.activiti.org/content/processinstanceisended-returns-false
		// assertTrue(processInstance.isEnded());
		// fetch second task
		task = taskService.createTaskQuery().singleResult();
		assertNull(task);
	}
}
