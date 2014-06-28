package de.th.wildau.demo.activiti.crystalball;

import org.activiti.engine.ProcessEngines;
import org.activiti.engine.impl.test.ResourceActivitiTestCase;
import org.activiti.engine.test.Deployment;

public class SimpleSimulationRunTest extends ResourceActivitiTestCase {

	public SimpleSimulationRunTest() {
		super(
				"de/th/wildau/demo/activiti/crystalball/SimpleSimulationRunTest.cfg.xml");
	}

	@Deployment
	public void testSimulationRun() {
		super.runtimeService.startProcessInstanceByKey("basicSimulationRun");
		// all simulationManager executions are finished
		assertEquals(0, super.runtimeService.createExecutionQuery().count());
	}

	@Override
	protected void closeDownProcessEngine() {
		super.closeDownProcessEngine();
		ProcessEngines.destroy();
	}

}
