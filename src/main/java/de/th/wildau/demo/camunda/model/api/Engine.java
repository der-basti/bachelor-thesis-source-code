package de.th.wildau.demo.camunda.model.api;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;

public class Engine {

	public static void main(String[] args) {
		ProcessEngine processEngine = ProcessEngineConfiguration
				.createStandaloneProcessEngineConfiguration()
				.setDatabaseSchemaUpdate(
						ProcessEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP)
				.setJdbcDriver("jdbc:h2:mem:my-db;DB_CLOSE_DELAY=1000")
				.setJobExecutorActivate(true).buildProcessEngine();

		processEngine.getRepositoryService().createDeployment()
				.addClasspathResource("sampleProcess.bpmn").deploy();

		processEngine.getRuntimeService().startProcessInstanceByKey(
				"ProcessId");
	}
}
