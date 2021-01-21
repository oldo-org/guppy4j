package org.oldo.jmxterm;

/**
 * Allows programmatic execution of <a href="http://wiki.cyclopsgroup.org/jmxterm/">jmxterm</a> commands
 */
public interface CommandExecutor extends AutoCloseable {

	/**
	 * Executes a jmxterm command. Use the 'help' command for a list of supported commands.
	 *
	 * @return the output of the successful command
	 * @throws IllegalStateException containing any error messages, when an error occurs
	 */
	String execute(String command);

	/**
	 * Implementations won't throw any checked exceptions.
	 */
	@Override
	void close();

}
