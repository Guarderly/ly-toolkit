package com.ly.view;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class ToolMainGui {

    private JTabbedPane jtabbedPane;

    public Container getPanel() {
        return jtabbedPane;
    }
}
