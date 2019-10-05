package othello;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * @author bu
 *
 */
public class SimpleOthello extends JFrame {

	private JPanel contentPane;
	static int clickedTimes = 0;
	JButton pieces[][];
	int[][] values = new int[8][8];
	int i, j;
	int vCol, vRow;
	int boardSize = 8;
	static boolean right = false;
	static boolean left = false;
	static boolean up = false;
	static boolean down = false;
	static boolean rightUp = false;
	static boolean rightDown = false;
	static boolean leftUp = false;
	static boolean leftDown = false;
	static boolean isLoginScreen = true;

	ImageIcon black = new ImageIcon(SimpleOthello.class.getResource("black.png"));
	ImageIcon white = new ImageIcon(SimpleOthello.class.getResource("white.png"));
	ImageIcon blackS = new ImageIcon(SimpleOthello.class.getResource("blackS.png"));
	ImageIcon whiteS = new ImageIcon(SimpleOthello.class.getResource("whiteS.png"));

	/**
	 * Create the frame.
	 */
	public SimpleOthello() {
		welcome();
	}

	/**
	 * Welcome Screen
	 */
	private void welcome() {
		getContentPane().removeAll();
		getContentPane().revalidate();
		
		setResizable(false);
		setMaximumSize(new Dimension(630, 770));
		setName("baseframe");
		setMinimumSize(new Dimension(630, 770));
		setTitle("Othello\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnOnePlayer = new JButton("One Player");
		btnOnePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panel, "Sorry, the AI is not available yet, I'll implement it soon.");
			}
		});
		btnOnePlayer.setBackground(new Color(250, 250, 210));
		btnOnePlayer.setFont(new Font("Dialog", Font.BOLD, 24));
		btnOnePlayer.setForeground(new Color(220, 20, 60));
		btnOnePlayer.setBounds(226, 30, 190, 40);
		panel.add(btnOnePlayer);
		
		JButton btnTwoPlayers = new JButton("Two Players");
		btnTwoPlayers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameStart();
			}
		});
		btnTwoPlayers.setBackground(new Color(250, 250, 210));
		btnTwoPlayers.setFont(new Font("Dialog", Font.BOLD, 24));
		btnTwoPlayers.setForeground(new Color(220, 20, 60));
		btnTwoPlayers.setBounds(226, 100, 190, 40);
		panel.add(btnTwoPlayers);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					Desktop.getDesktop().browse(new URL("https://en.wikipedia.org/wiki/Reversi#Rules").toURI());
				} catch (Exception e) {
				}
			}
		});
		btnHelp.setBackground(new Color(250, 250, 210));
		btnHelp.setForeground(new Color(220, 20, 60));
		btnHelp.setFont(new Font("Dialog", Font.BOLD, 24));
		btnHelp.setBounds(226, 170, 190, 40);
		panel.add(btnHelp);
		
		JLabel lblpiece1 = new JLabel("");
		lblpiece1.setIcon(new ImageIcon(SimpleOthello.class.getResource("black.png")));
		lblpiece1.setBounds(120, 10, 60, 72);
		panel.add(lblpiece1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(SimpleOthello.class.getResource("black.png")));
		label.setBounds(120, 150, 60, 72);
		panel.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(SimpleOthello.class.getResource("white.png")));
		label_1.setBounds(120, 80, 60, 72);
		panel.add(label_1);
		
		JLabel lblBottomBoard = new JLabel();
		lblBottomBoard.setOpaque(true);
		lblBottomBoard.setBackground(new Color(152, 251, 152));
		lblBottomBoard.setBounds(60, 235, 500, 500);
		panel.add(lblBottomBoard);
		
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				JLabel lblBoard;
				if (i == 4 && j == 4 || (i ==3 && j ==3)) lblBoard = new JLabel(blackS, JLabel.CENTER);
				else if (i == 4 && j == 3 || (i ==3 && j ==4)) lblBoard = new JLabel(whiteS, JLabel.CENTER);
				else lblBoard = new JLabel("");
				lblBoard.setOpaque(true);
				lblBoard.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				lblBoard.setBackground(new Color(218, 165, 32));
				lblBoard.setBounds(30 + 55 * i , 30 + 55 * j, 55, 55);
				lblBottomBoard.add(lblBoard);
			}
		}
	}

	/**
	 * the game part
	 */
	private void gameStart() {
		getContentPane().removeAll();

		setResizable(false);
		setMaximumSize(new Dimension(630, 770));
		setName("baseframe");
		setMinimumSize(new Dimension(630, 770));
		setTitle("Othello\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(124, 252, 0));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblBlack = new JLabel("Black score:");
		lblBlack.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblBlack.setForeground(new Color(51, 51, 51));
		lblBlack.setBounds(35, 22, 190, 50);
		panel.add(lblBlack);

		JLabel lblBlackScore = new JLabel("2");
		lblBlackScore.setFont(new Font("Arial Black", Font.BOLD, 28));
		lblBlackScore.setForeground(new Color(200, 20, 20));
		lblBlackScore.setBounds(233, 22, 70, 50);
		panel.add(lblBlackScore);
		
		JLabel lblWhite = new JLabel("White score:");
		lblWhite.setForeground(UIManager.getColor("Button.foreground"));
		lblWhite.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblWhite.setBounds(300, 22, 190, 50);
		panel.add(lblWhite);

		JLabel lblWhiteScore = new JLabel("2");
		lblWhiteScore.setFont(new Font("Arial Black", Font.BOLD, 28));
		lblWhiteScore.setForeground(new Color(200, 20, 20));
		lblWhiteScore.setBounds(500, 22, 70, 50);
		panel.add(lblWhiteScore);

		JLabel lblTurn = new JLabel("Turn: ");
		lblTurn.setBackground(new Color(124, 252, 0));
		lblTurn.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblTurn.setBounds(35, 70, 100, 35);
		panel.add(lblTurn);

		JButton btnReturn = new JButton("Welcome Screen");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome();
				clickedTimes = 0;
				values = new int[8][8];
			}
		});
		btnReturn.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnReturn.setBackground(new Color(255, 250, 205));
		btnReturn.setFont(new Font("Arial Black", Font.BOLD, 22));
		btnReturn.setBounds(300, 70, 280, 50);
		panel.add(btnReturn);


		JLabel lblTurnColor = new JLabel(black);
		lblTurnColor.setBackground(new Color(124, 252, 0));
		lblTurnColor.setBounds(180, 65, 70, 70);
		panel.add(lblTurnColor);

		pieces = new JButton[boardSize][boardSize];


		for(i = 0; i < boardSize; i++) {
			for(j = 0; j < boardSize; j++) {
				pieces[j][i] = new JButton(String.valueOf(i) + " " + String.valueOf(j));
				pieces[j][i].setForeground(new Color(240, 230, 140));
				pieces[j][i].setFont(new Font("Serif", Font.BOLD, 1));
				pieces[j][i].setBackground(new Color(240, 230, 140));
				pieces[j][i].setBounds(30 + i * 70, j * 70 + 140, 70, 70);
				panel.add(pieces[j][i]);
				pieces[j][i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						vCol = Integer.parseInt(e.getActionCommand().split(" ")[0]);
						vRow = Integer.parseInt(e.getActionCommand().split(" ")[1]);
						int scoreB = 0;
						int scoreW = 0;

						if (values[vRow][vCol] == 0) {
							flipValuesUp(vRow, vCol, false);
							flipValuesDown(vRow, vCol, false);
							flipValuesRight(vRow, vCol, false);
							flipValuesLeft(vRow, vCol, false);
							flipValuesLeftDown(vRow, vCol, false);
							flipValuesLeftUp(vRow, vCol, false);
							flipValuesRightDown(vRow, vCol, false);
							flipValuesRightUp(vRow, vCol, false);


							boolean isFlippable = right || down || left || up || rightUp ||
									rightDown || leftDown || leftUp;

							if (values[vRow][vCol] == 0 && isFlippable) {
								++clickedTimes;
								if (clickedTimes % 2 == 1) {
									values[vRow][vCol] = 1;
								} else {
									values[vRow][vCol] = 2;
								}
							}

							for (int m = 0; m < boardSize; m++) {
								for (int n = 0; n < boardSize; n++) {
									if (values[n][m] == 1) {
										pieces[n][m].setIcon(black);
										pieces[n][m].setIconTextGap(0);
										continue;
									} else if (values[n][m] == 2) {
										pieces[n][m].setIcon(white);
										pieces[n][m].setIconTextGap(0);
									}
								}
							}

							for (int[] items : values) {
								for (int item : items) {
									if (item == 1) scoreB++;
									else if (item == 2) scoreW++;
								}
							}

							lblBlackScore.setText(String.valueOf(scoreB));
							lblWhiteScore.setText(String.valueOf(scoreW));

						}

						if (clickedTimes % 2 == 1) lblTurnColor.setIcon(white);
						else lblTurnColor.setIcon(black);

						boolean isNoMove = true;
						boolean isNoSpace = true;

						for (int i = 0; i < boardSize; i++) {
							for (int j = 0; j < boardSize; j++) {
								if (values[j][i] == 0) {
									isNoMove = !flipValuesUp(j, i, true) &&
											!flipValuesDown(j, i, true) &&
											!flipValuesRight(j, i, true) &&
											!flipValuesLeft(j, i, true) &&
											!flipValuesLeftDown(j, i, true) &&
											!flipValuesLeftUp(j, i, true) &&
											!flipValuesRightDown(j, i, true) &&
											!flipValuesRightUp(j, i, true);
									isNoSpace = false;

									if (!isNoMove) break;
								}
							}
							if (!isNoMove) break;
						}

						if (isNoSpace) {
							if (scoreB > scoreW) {
								JOptionPane.showMessageDialog(null,
										"Black side wins!!",
										"End of game",
										JOptionPane.WARNING_MESSAGE);
							}
							else {
								JOptionPane.showMessageDialog(null,
										"White side wins!!",
										"End of game",
										JOptionPane.WARNING_MESSAGE);
							}
						}
						else if (isNoMove) {
							JOptionPane.showMessageDialog(null, "No move possible, switch to the other side.");
							clickedTimes++;
						}
					}
				});
			}
		}
		values[3][4] = values[4][3] = 1;
		values[4][4] = values[3][3] = 2;
		pieces[3][4].setIcon(black);
		pieces[3][4].setIconTextGap(0);
		pieces[4][3].setIcon(black);
		pieces[4][3].setIconTextGap(0);
		pieces[4][4].setIcon(white);
		pieces[4][4].setIconTextGap(0);
		pieces[3][3].setIcon(white);
		pieces[3][3].setIconTextGap(0);
	}

	public boolean flipValuesDown(int vR, int vC, boolean isTesting) {
		int value = ((clickedTimes + 1) % 2 == 1)? 1 : 2;
		boolean isFlippable = false;

		down = false;
		for (int i = vR + 1; i < boardSize; i++) {
			if (values[vR + 1][vC] == 0) break;
			if (i == vR + 1 && (value + values[i][vC] != 3)) break;
			if (i > vR + 1 && values[i][vC] == 0) break;
			if (values[i][vC] == value) {
				isFlippable = true;
				if (isTesting) return isFlippable;
				break;
			}
		}

		if (!isTesting) {
			int i = vR + 1;
			while (isFlippable && i < boardSize) {
				if (values[i][vC] == value) isFlippable = false;
				else {
					values[i][vC] = value;
					down = true;
				}
				i++;
			}
		}

		return isFlippable;
	}

	public boolean flipValuesUp(int vR, int vC, boolean isTesting) {
		int value = ((clickedTimes + 1) % 2 == 1)? 1 : 2;
		boolean isFlippable = false;

		up = false;
		for (int i = vR - 1; i >= 0; i--) {
			if (values[vR-1][vC] == 0) break;
			if (i == vR - 1 && (value + values[i][vC] != 3)) break;
			if (i < vR - 1 && values[i][vC] == 0) break;
			if (values[i][vC] == value) {
				isFlippable = true;
				if (isTesting) return isFlippable;
				break;
			}
		}


		if (!isTesting) {
			int i = vR - 1;
			while (isFlippable && i > 0) {
				if (values[i][vC] == value) isFlippable = false;
				else {
					values[i][vC] = value;
					up = true;
				}
				i--;
			}
		}
		return isFlippable;
	}

	public boolean flipValuesLeft(int vR, int vC, boolean isTesting) {
		boolean isFlippable = false;
		int value = ((clickedTimes + 1) % 2 == 1)? 1 : 2;

		left = false;
		for (int i = vC - 1; i >= 0; i--) {
			if (values[vR][vC - 1] == 0) break;
			if (i == vC - 1 && (value + values[vR][i] != 3)) {
				break;
			}
			if (i < vC - 1 && values[vR][i] == 0) break;
			if (values[vR][i] == value) {
				isFlippable = true;
				if (isTesting) return isFlippable;
				break;
			}
		}


		if (!isTesting) {
			int i = vC - 1;
			while (isFlippable && i >= 0) {
				if (values[vR][i] == value) isFlippable = false;
				else {
					values[vR][i] = value;
					left = true;
				}
				i--;
			}
		}

		return isFlippable;
	}

	public boolean flipValuesRight(int vR, int vC, boolean isTesting) {
		int value = ((clickedTimes + 1) % 2 == 1)? 1 : 2;
		boolean isFlippable = false;

		right = false;
		for (int i = vC + 1; i < boardSize; i++) {
			if (values[vR][vC + 1] == 0) break;
			if (i == vC + 1 && (value + values[vR][i] != 3)) break;
			if (i > vC + 1 && values[vR][i] == 0) break;
			if (values[vR][i] == value) {
				isFlippable = true;
				if (isTesting) return isFlippable;
				break;
			}
		}


		if (!isTesting) {
			int i = vC + 1;
			while (isFlippable && i < boardSize) {
				if (values[vR][i] == value) isFlippable = false;
				else {
					values[vR][i] = value;
					right = true;
				}
				i++;
			}
		}

		return isFlippable;
	}

	public boolean flipValuesRightDown(int vR, int vC, boolean isTesting) {
		int value = ((clickedTimes + 1) % 2 == 1)? 1 : 2;
		boolean isFlippable = false;
		int c = vC + 1;
		int r = vR + 1;

		rightDown = false;
		while (c < boardSize && r < boardSize) {
			if (values[vR + 1][vC + 1] == 0) break;
			if (c == vC + 1 && (value + values[r][c] != 3)) break;
			if (c > vC + 1 && values[r][c] == 0) break;
			if (values[r][c] == value) {
				isFlippable = true;
				if (isTesting) return isFlippable;
				break;
			}
			c++;
			r++;
		}


		if (!isTesting) {
			c = vC + 1;
			r = vR + 1;
			while (isFlippable && c < boardSize && r < boardSize) {
				if (values[r][c] == value) isFlippable = false;
				else {
					values[r][c] = value;
					rightDown = true;
				}
				c++;
				r++;
			}
		}

		return isFlippable;
	}

	public boolean flipValuesLeftUp(int vR, int vC, boolean isTesting) {
		int value = ((clickedTimes + 1) % 2 == 1)? 1 : 2;
		boolean isFlippable = false;
		int c = vC - 1;
		int r = vR - 1;

		leftUp = false;
		while (c >= 0 && r >= 0) {
			if (values[vR - 1][vC - 1] == 0) break;
			if (c == vC - 1 && (value + values[r][c] != 3)) break;
			if (c < vC - 1 && values[r][c] == 0) break;
			if (values[r][c] == value) {
				isFlippable = true;
				if (isTesting) return isFlippable;
				break;
			}
			c--;
			r--;
		}

		if (!isTesting) {
			c = vC - 1;
			r = vR - 1;
			while (isFlippable && c >= 0 && r >= 0) {
				if (values[r][c] == value) isFlippable = false;
				else {
					values[r][c] = value;
					leftUp = true;
				}
				c--;
				r--;
			}
		}

		return isFlippable;
	}

	public boolean flipValuesRightUp(int vR, int vC, boolean isTesting) {
		int value = ((clickedTimes + 1) % 2 == 1)? 1 : 2;
		boolean isFlippable = false;
		int c = vC + 1;
		int r = vR - 1;

		rightUp = false;
		while (c < boardSize && r >= 0) {
			if (values[vR - 1][vC + 1] == 0) break;
			if (c == vC + 1 && (value + values[r][c] != 3)) break;
			if (c > vC + 1 && values[r][c] == 0) break;
			if (values[r][c] == value) {
				isFlippable = true;
				if (isTesting) return isFlippable;
				break;
			}
			c++;
			r--;
		}

		if (!isTesting) {
			c = vC + 1;
			r = vR - 1;
			while (isFlippable && c < boardSize && r >= 0) {
				if (values[r][c] == value) isFlippable = false;
				else {
					values[r][c] = value;
					rightUp = true;
				}
				c++;
				r--;
			}
		}

		return isFlippable;
	}

	public boolean flipValuesLeftDown(int vR, int vC, boolean isTesting) {
		int value = ((clickedTimes + 1) % 2 == 1) ? 1 : 2;
		boolean isFlippable = false;
		int c = vC - 1;
		int r = vR + 1;

		leftDown = false;
		while (r < boardSize && c >= 0) {
			if (values[vR + 1][vC - 1] == 0) break;
			if (c == vC - 1 && (value + values[r][c] != 3)) break;
			if (c < vC - 1 && values[r][c] == 0) break;
			if (values[r][c] == value) {
				isFlippable = true;
				if (isTesting) return isFlippable;
				break;
			}
			c--;
			r++;
		}

		if (!isTesting) {
			c = vC - 1;
			r = vR + 1;
			while (isFlippable && r < boardSize && c >= 0) {
				if (values[r][c] == value) isFlippable = false;
				else {
					values[r][c] = value;
					leftDown = true;
				}
				c--;
				r++;
			}
		}

		return isFlippable;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimpleOthello frame = new SimpleOthello();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
