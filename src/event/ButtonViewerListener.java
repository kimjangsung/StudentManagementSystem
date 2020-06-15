package event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.WindowFrame;

public class ButtonViewerListener implements ActionListener {

	WindowFrame frame;

	public ButtonViewerListener(WindowFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(frame.getSongviewer());
		frame.revalidate();
		frame.repaint();
	}

}
