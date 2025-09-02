package com.example.backend;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * HelloService is our "backend" with exactly one responsibility:
 * expose a method that opens a native dialog saying "Hello, world!".
 *
 * Why is this "backend"?
 * - It's a standalone library (no UI window, no main method).
 * - It encapsulates the behavior the frontend can call.
 * - You can version/deploy it separately from the frontend.
 */
public final class HelloService {

    private HelloService() {
        // Prevent instantiation; this class is a static utility holder.
    }

    /**
     * Opens a simple "Hello, world!" dialog.
     *
     * Threading note:
     * - Swing components must be created/manipulated on the EDT (Event Dispatch Thread).
     * - If the caller is not on the EDT, we schedule the dialog creation on the EDT
     *   using SwingUtilities.invokeLater(...).
     *
     * Headless note:
     * - This call assumes we are NOT running headless. If you ever run in a server
     *   environment without a display, set "java.awt.headless=false" or avoid calling this.
     */
    public static void showHelloDialog() {
        // If we're already on the Swing EDT, show dialog immediately.
        if (SwingUtilities.isEventDispatchThread()) {
            JOptionPane.showMessageDialog(
                /* parentComponent = */ null,           // center on screen
                /* message         = */ "Hello, world!",
                /* title           = */ "Hello",
                /* messageType     = */ JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            // Ensure dialog is created on the EDT for thread-safety.
            SwingUtilities.invokeLater(() ->
                JOptionPane.showMessageDialog(
                    null,
                    "Hello, world!",
                    "Hello",
                    JOptionPane.INFORMATION_MESSAGE
                )
            );
        }
    }
}
