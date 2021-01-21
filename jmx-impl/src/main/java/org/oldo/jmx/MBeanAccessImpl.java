package org.oldo.jmx;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * Auto-closeable access to MBeans via JMX connection to a constructor-provided service URL
 */
public class MBeanAccessImpl implements MBeanAccess {

	private final JMXConnector connector;

	public MBeanAccessImpl(String url) {
		final JMXServiceURL serviceUrl = getServiceUrl(url);
		connector = connect(serviceUrl);
	}

	@Override
	public <T> T getProxy(Class<T> mBeanType) {
		final ObjectName objectName = getObjectName(mBeanType);
		return getProxy(mBeanType, objectName);
	}

	@Override
	public <T> T getProxy(Class<T> mBeanType, String name) {
		final ObjectName objectName = getObjectName(name);
		return getProxy(mBeanType, objectName);
	}

	private <T> T getProxy(Class<T> mBeanType, ObjectName objectName) {
		final MBeanServerConnection server = getServerConnection();
		if (mBeanType.getSimpleName().endsWith("MXBean")) {
			return JMX.newMXBeanProxy(server, objectName, mBeanType);
		} else {
			return JMX.newMBeanProxy(server, objectName, mBeanType);
		}
	}

	@Override
	public void close() {
		try {
			connector.close();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private MBeanServerConnection getServerConnection() {
		try {
			return connector.getMBeanServerConnection();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private static JMXServiceURL getServiceUrl(String url) {
		try {
			return new JMXServiceURL(url);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private static JMXConnector connect(JMXServiceURL serviceUrl) {
		try {
			return JMXConnectorFactory.connect(serviceUrl);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private static ObjectName getObjectName(Class<?> mbeanType) {
		final String domain = mbeanType.getPackage().getName();
		final String type = mbeanType.getSimpleName();
		try {
			return ObjectName.getInstance(domain, "type", type);
		} catch (MalformedObjectNameException e) {
			throw new IllegalStateException(e);
		}
	}

	private static ObjectName getObjectName(String objectName) {
		try {
			return ObjectName.getInstance(objectName);
		} catch (MalformedObjectNameException e) {
			throw new IllegalStateException(e);
		}
	}
}
