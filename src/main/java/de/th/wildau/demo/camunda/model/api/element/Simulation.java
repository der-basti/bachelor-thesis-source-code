package de.th.wildau.demo.camunda.model.api.element;

import org.camunda.bpm.model.xml.ModelBuilder;
import org.camunda.bpm.model.xml.impl.instance.ModelElementInstanceImpl;
import org.camunda.bpm.model.xml.impl.instance.ModelTypeInstanceContext;
import org.camunda.bpm.model.xml.type.ModelElementTypeBuilder;
import org.camunda.bpm.model.xml.type.attribute.Attribute;
import org.camunda.bpm.model.xml.type.reference.AttributeReference;

public class Simulation extends ModelElementInstanceImpl {

	public static String MODEL_NAMESPACE = "simNamespace";
	public static String TYPE_NAME = "simType";
	public static String ATTRIBUTE_NAME_ID = "simId";
	public static String ATTRIBUTE_NAME_NAME = "simName";
	public static String ATTRIBUTE_NAME_REF = "simRef";

	protected static Attribute<String> idAttr;
	protected static Attribute<String> nameAttr;
	protected static AttributeReference<Simulation> refAttr;

	public Simulation(ModelTypeInstanceContext instanceContext) {
		super(instanceContext);
		// TODO Auto-generated constructor stub
	}

	public static void registerType(ModelBuilder modelBuilder) {

		ModelElementTypeBuilder typeBuilder = modelBuilder
				.defineType(Simulation.class, TYPE_NAME)
				.namespaceUri(MODEL_NAMESPACE).abstractType();

		idAttr = typeBuilder.stringAttribute(ATTRIBUTE_NAME_ID).idAttribute()
				.build();
		nameAttr = typeBuilder.stringAttribute(ATTRIBUTE_NAME_NAME).build();
		refAttr = typeBuilder.stringAttribute(ATTRIBUTE_NAME_REF)
				.qNameAttributeReference(Simulation.class).build();

		typeBuilder.build();
	}
	
	public String getId() {
	    return idAttr.getValue(this);
	  }

	  public void setId(String id) {
	    idAttr.setValue(this, id);
	  }

	  public String getName() {
	    return nameAttr.getValue(this);
	  }

	  public void setName(String name) {
	    nameAttr.setValue(this, name);
	  }

	  public Simulation getFather() {
	    return refAttr.getReferenceTargetElement(this);
	  }

	  public void setFather(Simulation sim) {
		refAttr.setReferenceTargetElement(this, sim);
	}

}
