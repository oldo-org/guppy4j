package org.oldo.jmxterm;

import java.io.IOException;

import javax.management.remote.JMXServiceURL;

import org.cyclopsgroup.jmxterm.JavaProcess;
import org.cyclopsgroup.jmxterm.SyntaxUtils;
import org.cyclopsgroup.jmxterm.cc.CommandCenter;
import org.cyclopsgroup.jmxterm.io.CommandOutput;
import org.cyclopsgroup.jmxterm.io.UnimplementedCommandInput;
import org.cyclopsgroup.jmxterm.io.VerboseLevel;

/**
 * A simple client for programmatic execution of jmxterm commands.
 * <p>
 * For now, only connections to local JMX service processes are supported.
 * </p><p>
 * Please note that this class implements AutoCloseable and should be
 * instantiated using try-with-resources. On close(), an implicit 'quit'
 * command will be executed to close the session and disconnect.
 * </p>
 */
public final class CommandExecutorImpl implements CommandExecutor {

	private final CommandCenter commandCenter;
	private final CustomCommandOutput commandOutput = new CustomCommandOutput();

	public CommandExecutorImpl(String serviceIdentifier) {
		commandCenter = newCommandCenter(commandOutput);
		commandCenter.setVerboseLevel(VerboseLevel.VERBOSE);
		connect(serviceIdentifier);
	}

	@Override
	public String execute(String command) {
		if (commandCenter.execute(command)) {
			return commandOutput.getResult();
		} else {
			throw getRuntimeException(commandOutput.getLastThrowable());
		}
	}

	@Override
	public void close() {
		try {
			execute("quit");
		} finally {
			commandOutput.close();
		}
	}

	private void connect(String serviceIdentifier) {
		final JavaProcess process = findProcess(serviceIdentifier);
		final JMXServiceURL jmxUrl = getJmxUrl(process.getProcessId());
		connect(jmxUrl);
	}

	private void connect(JMXServiceURL jmxUrl) {
		try {
			commandCenter.connect(jmxUrl, null);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private JMXServiceURL getJmxUrl(int processId) {
		try {
			return SyntaxUtils.getUrl(String.valueOf(processId), commandCenter.getProcessManager());
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private JavaProcess findProcess(String identifier) {
		for (JavaProcess process : commandCenter.getProcessManager().list()) {
			final String name = process.getDisplayName();
			if (name != null && name.contains(identifier)) {
				return process;
			}
		}
		throw new IllegalStateException(String.format("Cannot find java process matching '%s'", identifier));
	}

	private static CommandCenter newCommandCenter(CommandOutput output) {
		try {
			// input would only be required by interactive commands that prompt for credentials
			return new CommandCenter(output, new UnimplementedCommandInput());
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private static RuntimeException getRuntimeException(Throwable t) {
		return (t instanceof RuntimeException) ? (RuntimeException) t : new RuntimeException(t);
	}
}
