package com.ly.view.page;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class DemoGui {
    private JPanel panel;
    private JPanel inputPanel;
    private JTextArea inputTextArea;
    private JButton confirmButton;
    private JButton cancelButton;
    private JTextArea textArea1;

    public Container getPanel() {
        return panel;
    }
}
