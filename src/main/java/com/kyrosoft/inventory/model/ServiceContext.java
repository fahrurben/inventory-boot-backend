package com.kyrosoft.inventory.model;

/**
 * <p>
 * ServiceContext provides service implementations access to the context data associated with thread in which
 * current service method call is being executed. This class uses ThreadLocal to store context data associated with
 * the running thread.
 * </p>
 * <p>
 * <strong>Thread Safety:</strong> This class is thread safe, since it uses a ThreadLocal to wrap the context data
 * and it only provides static methods.
 * </p>
 *
 * @author fahrur
 * @version 1.0
 */
public final class ServiceContext {

    /**
     * Represents the ThreadLocal object that provides access to thread local User object.
     *
     * It will be initialized to a new ThreadLocal<User> object and won't change afterwards.
     */
    private static final ThreadLocal<String> USER_THREAD_LOCAL = new ThreadLocal<String>();

    /**
     * Empty constructor to prevent instantiation.
     */
    private ServiceContext() {}

    /**
     * This method is used to access the user associated with current running thread.
     *
     * @return the user, null will be returned if no user is associated with current running thread
     */
    public static String getCurrentUser() {
        return USER_THREAD_LOCAL.get();
    }

    /**
     * This method is used to set the user associated with current running thread.
     *
     * @param user
     *            the user to set, null indicates to remove the user.
     */
    public static void setCurrentUser(String user) {
        if (user == null) {
            USER_THREAD_LOCAL.remove();
        } else {
            USER_THREAD_LOCAL.set(user);
        }
    }

}
