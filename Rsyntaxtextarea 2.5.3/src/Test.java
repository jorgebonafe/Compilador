import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;


public class Test{
	private JFrame window;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new Test();
			}
		});
	}

	public Test()
	{
		createAndShowGUI();
	}

	private void createAndShowGUI()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		window = new JFrame("Teste da classe PNCABaseDevice na rede");

		window.setMinimumSize(new Dimension(400,250));
		window.setLocation(50,50);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addComponentsToPane(window.getContentPane());
		window.pack();

		window.setVisible(true);
	}

	private void addComponentsToPane(Container pane)
	{
		pane.setLayout(new BorderLayout(0,0));

		JPanel upperPanel  = new JPanel();
		JPanel lowerPanel  = new JPanel();
		JPanel inputPanel  = new JPanel();
		JPanel buttonPanel = new JPanel();

		RSyntaxTextArea textArea = new RSyntaxTextArea();
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LEGAL_ALPHA_2012);
		//textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
		textArea.setTabSize(3);
    
    RTextScrollPane sp = new RTextScrollPane(textArea);
    sp.setIconRowHeaderEnabled(true);

    
    JScrollPane messages2 = new JScrollPane(sp,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    pane.add(upperPanel,BorderLayout.CENTER);
    upperPanel.setBorder(BorderFactory.createLineBorder (Color.black, 1));
    pane.add(lowerPanel,BorderLayout.PAGE_END);
    upperPanel.setLayout(new GridLayout(1,1));
    lowerPanel.setBorder(BorderFactory.createLineBorder (Color.black, 1));
    lowerPanel.setLayout(new BorderLayout());
    inputPanel.setLayout(new BorderLayout());
    buttonPanel.setLayout(new GridLayout(1,3,1,1));
    buttonPanel.setBorder(BorderFactory.createLineBorder (Color.black, 1));

  	upperPanel.add(messages2);
  	lowerPanel.add(inputPanel,BorderLayout.PAGE_START);
  	lowerPanel.add(buttonPanel,BorderLayout.PAGE_END);
	}
}