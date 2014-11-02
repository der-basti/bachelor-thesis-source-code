package de.th.wildau.demo.bpmn2.modeler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.impl.SequenceFlowImpl;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.pmw.tinylog.Logger;

public class ModelReader {

	public static void main(String[] args) {
		printProcess("src/main/resources/Sample.bpmn2");
	}

	/**
	 * Please check out alternative exports methods. E.g. direct XML processor
	 * or something else. Take a look in the org.eclipse.bpmn2 or org.jbpm.bpmn2
	 * package.
	 * 
	 * @param processRessource
	 */
	private static void printProcess(String processRessource) {
		Logger.debug("load sample process");
		URI uri = URI.createURI(processRessource);
		Bpmn2ResourceFactoryImpl resFactory = new Bpmn2ResourceFactoryImpl();
		Resource resource = resFactory.createResource(uri);

		Logger.debug("object references in the file are 'by ID' instead of the document reference 'URI#fragment' form.");
		HashMap<Object, Object> options = new HashMap<Object, Object>();
		options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, true);

		Logger.debug("load the resource");
		try {
			resource.load(options);
		} catch (final IOException ex) {
			Logger.error(ex);
		}

		Logger.debug("root element of the XML document");
		Definitions d = (Definitions) resource.getContents().get(0).eContents()
				.get(0);

		Logger.debug("print all elements contained in all Processes found");
		StringBuilder sb = new StringBuilder();
		List<RootElement> rootElements = d.getRootElements();
		for (RootElement re : rootElements) {
			if (re instanceof Process) {
				Process process = (Process) re;
				Logger.debug("Process: name=" + process.getName() + " ID="
						+ process.getId());
				for (FlowElement fe : process.getFlowElements()) {
					sb.setLength(0);
					if (fe instanceof SequenceFlowImpl) {
						SequenceFlow sf = ((SequenceFlow) fe);
						Logger.debug("null check for unattached elements");
						if (sf != null) {
							sb.append("Sequence Flow: ");
							if (sf.getSourceRef() != null) {
								sb.append(sf.getSourceRef().getId());
							}
							sb.append(" -> ");
							if (sf.getTargetRef() != null) {
								sb.append(sf.getTargetRef().getId());
							}
						}
					} else {
						sb.append("Name=").append(fe.getName()).append(" ID=")
								.append(fe.getId());
					}
					Logger.info(sb.toString());
				}
			}
		}
	}

}
