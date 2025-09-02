package com.example.frontend;

import com.example.backend.HelloService;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

/**
 * App is the entry point of the desktop application.
 *
 * What it does:
 * - Creates a large window (800x600).
 * - Places a single big button in the center.
 * - When you click the button, it calls HelloService.showHelloDialog()
 *   from the "backend" library, which pops a "Hello, world!" dialog.
 *
 * Why Swing:
 * - Swing is included with the JDK.
 * - Perfect for a minimal demo (no extra libraries).
 *
 * Threading:
 * - We start the UI on the Swing Event Dispatch Thread (EDT) using SwingUtilities.invokeLater.
 * - This is the correct & safe way to create Swing UIs.
 */
public final class App {

    public static void main(String[] args) {
        // Always start Swing UIs on the Event Dispatch Thread (EDT).
        SwingUtilities.invokeLater(App::createAndShowUI);
    }

    /**
     * Builds and shows the main window.
     */
    private static void createAndShowUI() {
        // Create the main window frame
        JFrame frame = new JFrame("Tiny Frontend - Hello Demo");

        // Ensure the app exits when the window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // We want a big window
        frame.setPreferredSize(new Dimension(800, 600));

        // Use a simple BorderLayout and put our button panel in the CENTER
        frame.setLayout(new BorderLayout());

        // Create a panel that will hold the button (centered by BorderLayout)
        JPanel centerPanel = new JPanel(); // default FlowLayout centers the component
        JButton helloButton = new JButton("Say Hello");

        // Make the button clearly visible (bigger font and fixed size)
        helloButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        helloButton.setPreferredSize(new Dimension(240, 80));

        // Wire the button to the backend function.
        // When clicked, we delegate to the backend to show the dialog.
        helloButton.addActionListener(e -> {
            // Call into the backend library; this will pop the dialog.
            HelloService.showHelloDialog();
        });

        // Add the button to the panel and the panel to the frame's center
        centerPanel.add(helloButton);
        frame.add(centerPanel, BorderLayout.CENTER);

        // Pack components to their preferred sizes and display the window
        frame.pack();
        frame.setLocationRelativeTo(null); // center on screen
        frame.setVisible(true);
    }
}
