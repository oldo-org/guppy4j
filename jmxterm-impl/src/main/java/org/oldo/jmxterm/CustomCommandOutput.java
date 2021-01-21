package org.oldo.jmxterm;

import org.cyclopsgroup.jmxterm.io.CommandOutput;

/**
 * A CommandOutput that separates result output from jmxterm messages
 * and exceptions, and makes them available for easy access
 */
public final class CustomCommandOutput extends CommandOutput {

	private final StringBuilder out = new StringBuilder();
	private final StringBuilder msg = new StringBuilder();

	private Throwable lastThrowable;

	@Override
	public void print(String output) {
		out.append(output);
	}

	@Override
	public void printError(Throwable e) {
		msg.append(e.getMessage());
		lastThrowable = e;
	}

	@Override
	public void printMessage(String message) {
		msg.append(message);
	}

	@Override
	public void close() {
		out.setLength(0);
		msg.setLength(0);
	}

	String getResult() {
		return getAndClear(out);
	}

	Throwable getLastThrowable() {
		return lastThrowable;
	}

	String getMessages() {
		return getAndClear(msg);
	}

	private static String getAndClear(StringBuilder sb) {
		final String s = sb.toString().trim();
		sb.setLength(0);
		return s;
	}
}
