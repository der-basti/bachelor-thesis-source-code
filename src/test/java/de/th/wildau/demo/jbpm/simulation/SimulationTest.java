package de.th.wildau.demo.jbpm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.drools.core.time.SessionPseudoClock;
import org.jbpm.simulation.PathFinder;
import org.jbpm.simulation.PathFinderFactory;
import org.jbpm.simulation.SimulationContext;
import org.jbpm.simulation.SimulationContextFactory;
import org.jbpm.simulation.SimulationRepository;
import org.jbpm.simulation.SimulationRunner;
import org.jbpm.simulation.converter.SimulationFilterPathFormatConverter;
import org.jbpm.simulation.impl.SimulationPath;
import org.junit.Test;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.th.wildau.demo.jbpm.simulation.util.HardCodedSimulationDataProvider;
import de.th.wildau.demo.jbpm.simulation.util.TestUtils;

public class SimulationTest {

	private static final Logger log = LoggerFactory
			.getLogger(SimulationTest.class);

	@Test
	public void testUserTaskProcessSimulation() {
		log.info("start testUserTaskProcessSimulation");
		PathFinder finder = PathFinderFactory.getInstance(this.getClass()
				.getResourceAsStream("/BPMN2-UserTask.bpmn2"));

		List<SimulationPath> paths = finder
				.findPaths(new SimulationFilterPathFormatConverter());
		SimulationContext context = SimulationContextFactory
				.newContext(new HardCodedSimulationDataProvider());

		for (SimulationPath path : paths) {

			context.setCurrentPath(path);
			StatefulKnowledgeSession session = TestUtils
					.createSession("BPMN2-UserTask.bpmn2");

			context.setClock((SessionPseudoClock) session.getSessionClock());
			// set start date to current time
			context.getClock().advanceTime(System.currentTimeMillis(),
					TimeUnit.MILLISECONDS);

			session.startProcess("UserTask");
			log.info("done");
		}
	}

	@Test
	public void testExclusiveGatewayProcessSimulation() {
		log.info("start testExclusiveGatewayProcessSimulation");
		PathFinder finder = PathFinderFactory.getInstance(this.getClass()
				.getResourceAsStream("/BPMN2-ExclusiveSplit.bpmn2"));

		List<SimulationPath> paths = finder
				.findPaths(new SimulationFilterPathFormatConverter());
		SimulationContext context = SimulationContextFactory
				.newContext(new HardCodedSimulationDataProvider());

		for (SimulationPath path : paths) {
			context.setCurrentPath(path);
			StatefulKnowledgeSession session = TestUtils
					.createSession("BPMN2-ExclusiveSplit.bpmn2");

			context.setClock((SessionPseudoClock) session.getSessionClock());
			// set start date to current time
			context.getClock().advanceTime(System.currentTimeMillis(),
					TimeUnit.MILLISECONDS);

			session.startProcess("com.sample.test");
			log.info("done");
		}
	}

	@Test
	public void testSimulationRun() throws IOException {
		log.info("start testSimulationRun");
		// enable logging
		System.setProperty("jbpm.simulation.log.enabled", "true");
		// read process
		String out = preset("/BPMN2-BankLoanProcess.bpmn2");
		// run simulation
		SimulationRepository repo = SimulationRunner.runSimulation(
				"defaultPackage.banl-loan", out, 10, 100, true,
				"onevent.simulation.rules.drl");
		log.debug(repo.getSimulationInfo().toString());
		log.info("done");
	}

	private String preset(final String process) throws IOException {
		InputStreamReader in = new InputStreamReader(this.getClass()
				.getResourceAsStream(process));
		log.debug(in.toString());
		String out = new String();
		BufferedReader br = new BufferedReader(in);
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			out += line;
		}
		log.debug(in.toString());
		return out;
	}
}
