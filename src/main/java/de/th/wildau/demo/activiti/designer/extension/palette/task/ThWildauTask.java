package de.th.wildau.demo.activiti.designer.extension.palette.task;

import org.activiti.designer.integration.servicetask.AbstractCustomServiceTask;
import org.activiti.designer.integration.servicetask.PropertyType;
import org.activiti.designer.integration.servicetask.annotation.DatePickerProperty;
import org.activiti.designer.integration.servicetask.annotation.Help;
import org.activiti.designer.integration.servicetask.annotation.Property;
import org.activiti.designer.integration.servicetask.annotation.PropertyItems;
import org.activiti.designer.integration.servicetask.annotation.Runtime;

/**
 * Defines the Task node.
 * 
 * @author Sebastian Nemak
 */
@Runtime(javaDelegateClass = "de.th.wildau.demo.activiti.designer.extension.palette.task.ThWildauTask")
@Help(displayHelpShort = "Help short", displayHelpLong = "Help long")
public class ThWildauTask extends AbstractCustomServiceTask {

	private static final String HELP_ACCOUNT_NUMBER_LONG = "Provide a number that is suitable as an account number.";

	private static final String DISTRIBUTION_FUNCTION_EXPO_LABEL = "Expo function";
	private static final String DISTRIBUTION_FUNCTION_EXPO_VALUE = "expo";

	private static final String DISTRIBUTION_FUNCTION_GAMMA_LABEL = "Gamma function";
	private static final String DISTRIBUTION_FUNCTION_GAMMA_VALUE = "gamma";

	private static final String DISTRIBUTION_FUNCTION_ERLANG_LABEL = "Erlang function";
	private static final String DISTRIBUTION_FUNCTION_ERLANG_VALUE = "erlang";

	private static final String DISTRIBUTION_FUNCTION_BETA_LABEL = "Beta function";
	private static final String DISTRIBUTION_FUNCTION_BETA_VALUE = "beta";
	
	private static final String DISTRIBUTION_FUNCTION_NORMAL_LABEL = "Normal function";
	private static final String DISTRIBUTION_FUNCTION_NORMAL_VALUE = "normal";

	private static final String LIMIT_LOW_LABEL = "Low (100)";
	private static final String LIMIT_LOW_VALUE = "100";
	private static final String LIMIT_MEDIUM_LABEL = "Medium (1000)";
	private static final String LIMIT_MEDIUM_VALUE = "1000";
	private static final String LIMIT_HIGH_LABEL = "High (10000)";
	private static final String LIMIT_HIGH_VALUE = "10000";

	@Property(type = PropertyType.TEXT, displayName = "Account Number", required = true, defaultValue = "1234567890")
	@Help(displayHelpShort = "Provide an account number", displayHelpLong = HELP_ACCOUNT_NUMBER_LONG)
	private String accountNumber;

	@Property(type = PropertyType.MULTILINE_TEXT, displayName = "Comments", required = true, defaultValue = "Your comment")
	@Help(displayHelpShort = "Provide comments", displayHelpLong = "You can add comments to the node to provide a brief description.")
	private String comments;

	@Property(type = PropertyType.PERIOD, displayName = "Processing Time", required = true, defaultValue = "0y 0mo 2w 0d 0h 0m 0s")
	@Help(displayHelpShort = "The maximum allowed time for processing", displayHelpLong = "Processing must take no longer than the period you specify here.")
	private String maximumProcessingTime;

	@Property(type = PropertyType.BOOLEAN_CHOICE, displayName = "Boolean", defaultValue = "false")
	@Help(displayHelpShort = "Boolean short", displayHelpLong = "Boolean long")
	private String bool;

	@Property(type = PropertyType.COMBOBOX_CHOICE, displayName = "Distribution function", required = true, defaultValue = DISTRIBUTION_FUNCTION_NORMAL_VALUE)
	@Help(displayHelpShort = "The distribution function", displayHelpLong = "Choose a distribution function from the list of options")
	@PropertyItems({ DISTRIBUTION_FUNCTION_BETA_LABEL, DISTRIBUTION_FUNCTION_BETA_VALUE,
		DISTRIBUTION_FUNCTION_ERLANG_LABEL, DISTRIBUTION_FUNCTION_ERLANG_VALUE, 
		DISTRIBUTION_FUNCTION_EXPO_LABEL, DISTRIBUTION_FUNCTION_EXPO_VALUE,
		DISTRIBUTION_FUNCTION_GAMMA_LABEL, DISTRIBUTION_FUNCTION_GAMMA_VALUE, 
		DISTRIBUTION_FUNCTION_NORMAL_LABEL, DISTRIBUTION_FUNCTION_NORMAL_VALUE })
	private String accountType;

	@Property(type = PropertyType.RADIO_CHOICE, displayName = "Limit")
	@Help(displayHelpShort = "The maximum daily..", displayHelpLong = "Choose the maximum daily ...")
	@PropertyItems({ LIMIT_LOW_LABEL, LIMIT_LOW_VALUE, LIMIT_MEDIUM_LABEL,
			LIMIT_MEDIUM_VALUE, LIMIT_HIGH_LABEL, LIMIT_HIGH_VALUE })
	private String withdrawlLimit;

	@Property(type = PropertyType.DATE_PICKER, displayName = "Expiry date")
	@DatePickerProperty()
	private String expiryDate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.activiti.designer.integration.servicetask.AbstractCustomServiceTask
	 * #contributeToPaletteDrawer()
	 */
	@Override
	public String contributeToPaletteDrawer() {
		return "TH-Wildau Palette";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.activiti.designer.integration.servicetask.AbstractCustomServiceTask
	 * #getName()
	 */
	@Override
	public String getName() {
		return "TH Wildau node";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.activiti.designer.integration.servicetask.AbstractCustomServiceTask
	 * #getSmallIconPath()
	 */
	@Override
	public String getSmallIconPath() {
		return "icons/th.png";
	}

}
