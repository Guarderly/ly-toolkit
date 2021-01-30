package com.ly.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Desktop;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ly.model.XlsToolModel;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class XlsTool {
	static final Logger logger = LogManager.getLogger(XlsTool.class);

	private JFrame frame;
	private JTextField textFieldInputXls;
	private JTextField textFieldKeyIndex;
	private JTextField textFieldOutPath;
	private JTextArea textAreaOutput = new JTextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		logger.info("XlsTool start");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XlsTool window = new XlsTool();
					window.frame.setVisible(true);
					logger.info("XlsTool start");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public XlsTool() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_input = new JPanel();
		panel_input.setBorder(new TitledBorder(null, "\u8F93\u5165", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_input, BorderLayout.NORTH);
		panel_input.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_inputField = new JPanel();
		panel_input.add(panel_inputField, BorderLayout.NORTH);
		panel_inputField.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_inputField.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblxls = new JLabel("文件路径：");
		panel_1.add(lblxls);
		
		textFieldInputXls = new JTextField();
		textFieldInputXls.setText("/Users/guarder/eclipse-workspace/ly-toolkit/data/test.xls");
		textFieldInputXls.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser jfc=new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY );
				ExcelFileFilter excelFilter = new ExcelFileFilter(); //excel过滤器  
			    jfc.addChoosableFileFilter(excelFilter);
			    jfc.setFileFilter(excelFilter);
				jfc.showDialog(new JLabel(), "选择");
				File file=jfc.getSelectedFile();
				//未选择则直接退出
				if(file==null) {
					return;
				}
				textFieldInputXls.setText(file.getAbsolutePath());
				String outPath = file.getPath();
				int lastIndex = outPath.lastIndexOf(".");
				outPath = outPath.substring(0, lastIndex);
				textFieldOutPath.setText(outPath);
				//日志输出
				textAreaOutput.append("\n文件已选择："+file.getAbsolutePath());
				textAreaOutput.append("\n自动生成输出路径："+outPath);
			}
		});
		panel_1.add(textFieldInputXls);
		textFieldInputXls.setColumns(20);
		
		JPanel panel_2 = new JPanel();
		panel_inputField.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label = new JLabel("主拆分列：");
		panel_2.add(label);
		
		textFieldKeyIndex = new JTextField();
		textFieldKeyIndex.setText("0");
		panel_2.add(textFieldKeyIndex);
		textFieldKeyIndex.setColumns(20);
		
		JPanel panel_3 = new JPanel();
		panel_inputField.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label_1 = new JLabel("存放路径：");
		panel_3.add(label_1);
		
		textFieldOutPath = new JTextField();
		textFieldOutPath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String outPath = textFieldOutPath.getText();
				if(outPath==null||"".equals(outPath)) {
					return;
				}
				log("尝试打开："+outPath);
				try {
					File outputFile = new File(outPath);
					Desktop.getDesktop().open(outputFile);
				} catch (IOException e1) {
					log("处理异常："+e1.toString());
					return;
				}
			}
		});
		textFieldOutPath.setText("/Users/guarder/eclipse-workspace/ly-toolkit/data/test");
		panel_3.add(textFieldOutPath);
		textFieldOutPath.setColumns(20);
		
		JPanel panel_button = new JPanel();
		panel_input.add(panel_button);
		
		JButton btnDeal = new JButton("开始处理");
		btnDeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log("开始处理：");
				try {
					String inFilePath = textFieldInputXls.getText();
					int colIndex = Integer.parseInt(textFieldKeyIndex.getText());
					String outPath = textFieldOutPath.getText();
					XlsToolModel.splitByCol(inFilePath, colIndex, outPath);
					File outputFile = new File(outPath);
					Desktop.getDesktop().open(outputFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					log("处理异常："+e1.toString());
					return;
				}
				log("处理成功！");
			}
		});
		panel_button.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panel_button.add(btnDeal);
		
		JPanel panel_out = new JPanel();
		panel_out.setBorder(new TitledBorder(null, "\u8F93\u51FA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_out, BorderLayout.CENTER);
		panel_out.setLayout(new BorderLayout(0, 0));
		
		
		JScrollPane scrollPanel_output = new JScrollPane();
		scrollPanel_output.setAutoscrolls(true);
		panel_out.add(scrollPanel_output);
		
//		scrollPanel_output.setAutoscrolls(true);
//		scrollPanel_output.setBorder(new TitledBorder(null, "\u8F93\u51FA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		scrollPanel_output.setViewportView(textAreaOutput);
	}
	
	private void log(String info) {
		textAreaOutput.append(info+"\n");
	}
	
	class ExcelFileFilter extends FileFilter {  
	    public String getDescription() {  
	        return "*.xls";  
	    }  
	  
	    public boolean accept(File file) {  
	        String name = file.getName();  
	        return file.isDirectory() || name.toLowerCase().endsWith(".xls") ;  // 仅显示目录和xls、xlsx文件
	    }  
	}

}
