package com.ly.view;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class LogGui {
    private JScrollPane logPane;
    private JTextArea textArea;

    public Container getPanel() {
        return logPane;
    }
}
