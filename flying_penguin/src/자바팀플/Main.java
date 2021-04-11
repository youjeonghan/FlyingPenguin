package �ڹ�����;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Main extends JFrame {
	ImageIcon bgImage;
	JLabel bgLabel;
	Clip clip; // ����� Ŭ�� �����

	public Main() {
		setTitle("�ö��� ���");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = getContentPane();
		container.setLayout(null);
		bgImage = new ImageIcon("image/main.png"); // ��׶��� �̹���
		bgLabel = new JLabel(bgImage);
		loadAudio("audio/main.wav"); // loadAudio �Լ� ȣ��
		clip.start(); // ����� Ŭ�� ����
		clip.loop(clip.LOOP_CONTINUOUSLY); // ����� Ŭ�� ���� �ݺ� : ������ ������ó������ �ٽ� �ݺ����ݴϴ�.
		Image j = Toolkit.getDefaultToolkit().getImage("image/cursor1.png"); // Ŀ����
		Image i = Toolkit.getDefaultToolkit().getImage("image/cursor.png");
		container.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(i, new Point(0, 0), ""));// �⺻Ŀ��
		JButton btn1 = new JButton();
		ImageIcon normallcon = new ImageIcon("image/menu1.png"); // �븻 �̹���
		ImageIcon rolloverIcon = new ImageIcon("image/menu2.png"); // ���콺�ö󰥶�

		btn1.setRolloverIcon(rolloverIcon);
		btn1.setIcon(normallcon);
		
		btn1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				clip.stop();
				Fish.fish = new Fish();// �ö��� ��� ���� ����
			}

			public void mouseEntered(MouseEvent e) { // ���콺�� ���οö󰥶�
				JButton button = (JButton) e.getSource();
				button.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(j, new Point(0, 0), ""));
			}

			public void mouseExited(MouseEvent e) { // ���콺�� ���ö�
				JButton button = (JButton) e.getSource();
				button.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(i, new Point(0, 0), ""));
			}
		});


		// ��ư ��ġ �� ũ�� ���� ��ư ���� ���� ��ư ���� ���� ����
		
		btn1.setBounds(315, 775, 135, 45);
		bgLabel.setBounds(0, 0, 800, 1000);// ���ȭ�� ũ�⼳��
		
		//�����̳ʿ� ��ư ���̱� 
		container.add(btn1);
		container.add(bgLabel);
		setSize(800, 1000);// �����̳�ũ�⼳��
		screenSizeLocation(); // ȭ���� �׻� ����ο��Ը���
		setVisible(true);
		JTextField jtf = new JTextField();
		JScrollPane jsp = new JScrollPane(jtf);
		add("Center", jsp);

	}

	/*
	 * loadAudio �Լ� ����� Ŭ���� ����� ��Ʈ�� ��ü�� AudioInputStream ���� ������ �� ����� Ŭ���� ����� ��Ʈ����
	 * clip.open() ���� �����Ѵ�. �� �Լ��� ��ģ �� clip �� ����� ��Ʈ�����κ��� ����� �����͸� �޾� ����� �� �ִ� ���°�
	 * �ȴ�. clip.start(); �޼��带 ȣ���ϸ� ����� ����� �����Ѵ�.
	 */
	private void loadAudio(String pathName) {
		try {
			clip = AudioSystem.getClip();
			File audioFile = new File(pathName);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			clip.open(audioStream);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void screenSizeLocation() {
		Dimension frameSize = getSize(); // ������Ʈ ũ��
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // ����� ȭ���� ũ�� ���ϱ� // (�����ȭ�� ���� - ������ȭ�� ����) /2, (�����ȭ�� ���� - ������ȭ�� ����) / 2
		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	}

	public static void main(String[] args) {
		new Main();
	}
}