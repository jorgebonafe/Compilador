package testJJ;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

public class IDE {
	private PFrame window;
	private RSyntaxTextArea editor;
	private JTextArea output;
	private JTextArea messages;
	private JScrollPane scrollpane1;
	private JScrollPane scrollpane2;
	private JSplitPane splitPane;

	public static void main(String[] args) throws Exception {
		if (args.length <= 0) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new IDE();
				}
			});
		}
		else {
			File f = new File(args[0]);
			System.out.println(f);

			JASON parser = new JASON(new FileInputStream(f));
			String result = parser.testParser(System.in);
			if (parser.getErrorCode() > 0) {
				System.out.println(parser.getErrorMessage());
			}
			else {
				System.out.println(result);
			}
		}
	}

	public IDE() {
		createAndShowGUI();
	}

	private void createAndShowGUI() {
		PFrame.setDefaultLookAndFeelDecorated(true);
		window = new PFrame("Teste JASON");

		window.setMinimumSize(new Dimension(640,480));
		window.setSize(new Dimension(640,480));
		window.setPreferredSize(new Dimension(640,480));
		window.setDefaultCloseOperation(3);


		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocation(ge.getMaximumWindowBounds().width/2-320,
				ge.getMaximumWindowBounds().height/2-240);


		addComponentsToPane(this.window.getContentPane());
		this.window.pack();

		this.window.setVisible(true);
	}

	private void addComponentsToPane(Container c) {
		c.setLayout(new GridBagLayout());
		editor = new RSyntaxTextArea();
		editor.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JASON);
		
		editor.setTabSize(3);
		editor.setCurrentLineHighlightColor(new Color(203,255,203));	
		
		editor.setFont(editor.getFont().deriveFont(18f));
		output = new JTextArea();
		output.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));

		output.setEditable(false);
		output.setBackground(Color.lightGray);

		scrollpane1 = new JScrollPane(editor, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane2 = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scrollpane1, scrollpane2);
		splitPane.setBackground(new Color( 0, 0, 0));
		splitPane.setOneTouchExpandable(false);
		splitPane.setDividerLocation(240);
		
		window.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent a) {
				splitPane.setDividerLocation(window.getSize().width/2);
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
						window.setLocation(ge.getMaximumWindowBounds().width/2-window.getSize().width/2,
								ge.getMaximumWindowBounds().height/2-window.getSize().height/2);
					}
				});
			}
		});
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1;
		gbc.weightx = 1;
		gbc.ipadx = 10;
		
		c.add(splitPane,gbc);
		
		JButton b = new JButton("Compilar");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InputStream is = new ByteArrayInputStream( editor.getText().getBytes() );
				output.setText("");
				messages.setText("");

				JASON parser = new JASON(is);
				String result = parser.testParser(is);
				if (parser.getErrorCode() > 0) {
					messages.setForeground(Color.red);
					messages.setText(parser.getErrorMessage());
				}
				else {
					output.setText(result);
				}
			}
		});

		messages = new JTextArea();
		messages.setMaximumSize(new Dimension(10000,50));
		messages.setPreferredSize(new Dimension(0,50));
		messages.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		messages.setFont(messages.getFont().deriveFont(18f));
		messages.setEditable(false);
		messages.setLineWrap(true);
		messages.setWrapStyleWord(true);
		
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 0;
		gbc.insets = new Insets(5,2,0,2);
		
		c.add(messages,gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weighty = 0;
		gbc.insets = new Insets(10,0,10,0);
		
		c.add(b,gbc);
	}
}