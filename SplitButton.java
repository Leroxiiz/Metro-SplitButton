package metro.style.leroxiiz;

import java.awt.AlphaComposite;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;
import java.awt.Dimension;

public class SplitButton extends JFrame {

	//Feito por: LeroxDev
	//Exemplo: http://prntscr.com/d6isdq
	
	private static final long serialVersionUID = 290385328270090396L;
	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					SplitButton frame = new SplitButton();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private int arrowSize = 10;
	private Image image;

	public SplitButton() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 403);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(24,24,24));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String name = "SplitButton";
		JLabel SplitButton = new JLabel(name + "                 |  ");
		SplitButton.setHorizontalTextPosition(SwingConstants.LEADING);
		SplitButton.setOpaque(false);
		SplitButton.setBackground(new Color(204, 204, 204));
		SplitButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				SplitButton.setOpaque(false);
				SplitButton.setForeground(UIManager.getColor("Button.background"));
				SplitButton.setIcon(new ImageIcon(arrow(UIManager.getColor("Button.background"))));

			}
			public void mousePressed(MouseEvent e) {
				SplitButton.setOpaque(true);
				SplitButton.setBackground(Color.white);
				SplitButton.setForeground(new Color(0,0,0));
				SplitButton.setIcon(new ImageIcon(arrow(Color.BLACK)));

			}
			public void mouseExited(MouseEvent e) {
				SplitButton.setBackground(UIManager.getColor("Button.background")); 
				SplitButton.setOpaque(false);

			}
			public void mouseEntered(MouseEvent e) {
				SplitButton.setBackground(UIManager.getColor("Button.darkShadow"));
				SplitButton.setOpaque(true);
			}
			public void mouseClicked(MouseEvent e) {
			}
		});
		SplitButton.setForeground(UIManager.getColor("Button.background"));
		SplitButton.setIcon(new ImageIcon(arrow(UIManager.getColor("Button.background"))));
		SplitButton.setBorder(new LineBorder(UIManager.getColor("Button.background"), 2));
		SplitButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		SplitButton.setHorizontalAlignment(SwingConstants.CENTER);
		SplitButton.setBounds(10, 11, 212, 40);
		getContentPane().add(SplitButton);
		
		JPopupMenu popupMenu = new JPopupMenu(){

			private static final long serialVersionUID = 1L;
			    public void paintComponent(final Graphics g) {
			        g.setColor(new Color(23, 23, 23));
			        g.fillRect(0,0,getWidth(), getHeight());
			    }
		};
		popupMenu.setRequestFocusEnabled(false);
		SplitButton.setBorder(new LineBorder(UIManager.getColor("Button.background"), 2));
		popupMenu.setBackground(new Color(51, 51, 51));
		splitPopup(SplitButton, popupMenu);
		
		JMenuItem Item1 = new JMenuItem("Index 1");
		Item1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		Item1.setForeground(new Color(204, 204, 204));
		Item1.setSize(new Dimension(100, 100));
		Item1.setBackground(new Color(51, 51, 51));
		popupMenu.add(Item1);
		

	}

	private BufferedImage rotate(BufferedImage img, int angle) {
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage dimg = new BufferedImage(w, h, img.getType());
		Graphics2D g = dimg.createGraphics();
		g.rotate(Math.toRadians(angle), w / 2, h / 2);
		g.drawImage(img, null, 0, 0);
		return dimg;
	}

	public Image arrow(Color color) {
			Graphics2D g = null;
			BufferedImage img = new BufferedImage(arrowSize, arrowSize, BufferedImage.TYPE_INT_RGB);
			g = (Graphics2D) img.createGraphics();
			g.fillRect(0, 0, img.getWidth(), img.getHeight());

			Color old = color;
			firePropertyChange("arrowColor", old, color);
			g.setColor(color);
			g.fillPolygon(new int[]{0, 0, arrowSize / 2}, new int[]{0, arrowSize, arrowSize / 2}, 3);
			g.dispose();
			g.setColor(color);
			img = rotate(img, 90);
			BufferedImage dimg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
			g = (Graphics2D) dimg.createGraphics();
			g.setComposite(AlphaComposite.Src);
			g.drawImage(img, null, 0, 5);
			g.dispose();
			g.setColor(color);
			for (int i = 0; i < dimg.getHeight(); i++) {
				for (int j = 0; j < dimg.getWidth(); j++) {
					if (dimg.getRGB(j, i) == Color.WHITE.getRGB()) {
						dimg.setRGB(j, i, 0x8F1C1C);
					}
				}
			}
			g.setColor(color);
			image = Toolkit.getDefaultToolkit().createImage(dimg.getSource());
			return image;
	}

	private static void splitPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
					showMenu(e);
			}
			public void mouseReleased(MouseEvent e) {
					showMenu(e);
			}
			private void showMenu(MouseEvent e) {
				popup.show(component, component.getWidth() - 40*2-1, component.getHeight() + 2);
			}
		});
	}
}
