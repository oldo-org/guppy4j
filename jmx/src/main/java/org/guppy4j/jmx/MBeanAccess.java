package org.guppy4j.jmx;

/**
 * Provides access to mbeans, supports try-with-resources
 */
public interface MBeanAccess extends AutoCloseable {

	/**
	 * Looks up an mbean using a default naming convention where domain = package and type = simple class name.
	 *
	 * @param mBeanType The runtime type of the requested mbean (typically the mbean interface type)
	 * @param <T> Formal type of the requested mbean
	 * @return A proxy that implements all methods of the requested mbean interface
	 */
	<T> T getProxy(Class<T> mBeanType);

	/**
	 * Looks up an mbean using the specified object name.
	 *
	 * @param mBeanType The runtime type of the requested mbean (typically the mbean interface type)
	 * @param objectName The specific object name to use for the mbean lookup
	 * @param <T> Formal type of the requested mbean
	 * @return A proxy that implements all methods of the requested mbean interface
	 */
	<T> T getProxy(Class<T> mBeanType, String objectName);

	/**
	 * This type is auto-closeable and won't throw any checked exceptions on close()
	 */
	@Override
	void close();

}
