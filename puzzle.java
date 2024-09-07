package Exercise;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class puzzle extends Frame implements Runnable{
	int width = 1000;   // ウィンドウの幅
	int height = 800;   // ウィンドウの高さ
	Image  waku, mihon, im0, im1, im2, im3,   // easy
			waku2, mihon2, h0, h1, h2, h3, h4, h5, h6, h7, h8, // normal
			waku3, mihon3, t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15;  // diff
	Button Button0, Button1, Button2, Button3, Button4, Button5, Button6, Button7, Button8, 
			Button9, Button10, Button11, Button12, Button13, Button14, Button15,
			EasyButton, NormalButton, HardButton, resetButton, OKButton;
	int[] x = {650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650};  // ピースの初期位置
	int[] y = {405, 405, 405, 405, 405, 405, 405, 405, 405, 405, 405, 405, 405, 405, 405, 405};  // ピースの初期位置
	int piece, level;
	Choice c;
	String[] Lebel = {"Easy", "Normal", "Hard"};
	
	public static void main(String[] args) throws IOException {
		new puzzle();
	}
	
	public puzzle() throws IOException {
		super("パズル");                                  // ウィンドウのタイトル
		addWindowListener(new SampleWindowListener());    // イベントリスナーの登録
		Toolkit tk = getToolkit();
		waku = tk.getImage("usagi_waku.png");     // 枠（グレー）
		waku2 = tk.getImage("hitsuji_waku.png");
		waku3 = tk.getImage("tora_waku.png");
		// easy //
		mihon = tk.getImage("usagi_mihon.png");  // 見本
		im0 = tk.getImage("usagi0.png");  // 左上のピース
		im1 = tk.getImage("usagi1.png");  // 右上のピース
		im2 = tk.getImage("usagi2.png");  // 左下のピース
		im3 = tk.getImage("usagi3.png");  // 右下のピース
		// normal //
		mihon2 = tk.getImage("hitsuji_mihon.png");  // 見本
		h0 = tk.getImage("hitsuji0.png");  
		h1 = tk.getImage("hitsuji1.png");  
		h2 = tk.getImage("hitsuji2.png");   
		h3 = tk.getImage("hitsuji3.png");   
		h4 = tk.getImage("hitsuji4.png");
		h5 = tk.getImage("hitsuji5.png");
		h6 = tk.getImage("hitsuji6.png");
		h7 = tk.getImage("hitsuji7.png");
		h8 = tk.getImage("hitsuji8.png");
		// hard //
		mihon3 = tk.getImage("tora_mihon.png");  // 見本
		t0 = tk.getImage("tora0.png");  // ピース
		t1 = tk.getImage("tora1.png");  // ピース
		t2 = tk.getImage("tora2.png");  // ピース
		t3 = tk.getImage("tora3.png");  // ピース
		t4 = tk.getImage("tora4.png");  // ピース
		t5 = tk.getImage("tora5.png");  // ピース
		t6 = tk.getImage("tora6.png");  // ピース
		t7 = tk.getImage("tora7.png");  // ピース
		t8 = tk.getImage("tora8.png");  // ピース
		t9 = tk.getImage("tora9.png");  // ピース
		t10 = tk.getImage("tora10.png");  // ピース
		t11 = tk.getImage("tora11.png");  // ピース
		t12 = tk.getImage("tora12.png");  // ピース
		t13 = tk.getImage("tora13.png");  // ピース
		t14 = tk.getImage("tora14.png");  // ピース
		t15 = tk.getImage("tora15.png");  // ピース
		
		// リセットボタン //
		resetButton = new Button("Reset");
		resetButton.setForeground(Color.red);
		resetButton.setFont(new Font("Dialog", Font.BOLD, 15));
		resetButton.setBounds(50, 40, 70, 30);
		resetButton.addActionListener(new ActionListener() {       // イベントリスナーの登録
			// ボタンクリック
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 16; i++) {
					x[i] = 650;
					y[i] = 405;
				}
				addMouseListener(new SampleMouseAdapter0());
			}
		});		

		// 難易度ボタン //
		EasyButton = new Button("Easy");
		EasyButton.setForeground(Color.blue);
		EasyButton.setFont(new Font("Dialog", Font.BOLD, 15));
		EasyButton.setBounds(140, 40, 70, 30);
		EasyButton.addActionListener(new ActionListener() {       // イベントリスナーの登録
			// ボタンクリック
			@Override
			public void actionPerformed(ActionEvent e) {				
				level = 0;
				addMouseListener(new SampleMouseAdapter0());
			}
		});		
		NormalButton = new Button("Normal");
		NormalButton.setForeground(Color.blue);
		NormalButton.setFont(new Font("Dialog", Font.BOLD, 15));
		NormalButton.setBounds(210, 40, 70, 30);
		NormalButton.addActionListener(new ActionListener() {       // イベントリスナーの登録
			// ボタンクリック
			@Override
			public void actionPerformed(ActionEvent e) {				
				level = 1;
				addMouseListener(new SampleMouseAdapter0());
			}
		});
		HardButton = new Button("Hard");
		HardButton.setForeground(Color.blue);
		HardButton.setFont(new Font("Dialog", Font.BOLD, 15));
		HardButton.setBounds(280, 40, 70, 30);
		HardButton.addActionListener(new ActionListener() {       // イベントリスナーの登録
			// ボタンクリック
			@Override
			public void actionPerformed(ActionEvent e) {				
				level = 2;
				addMouseListener(new SampleMouseAdapter0());
			}
		});
		
		OKButton = new Button("OK");
		OKButton.setForeground(Color.blue);
		OKButton.setFont(new Font("Dialog", Font.BOLD, 15));
		OKButton.setBounds(350, 40, 70, 30);
		OKButton.addActionListener(new ActionListener() {       // イベントリスナーの登録
			// ボタンクリック
			@Override
			public void actionPerformed(ActionEvent e) {
				addMouseListener(new SampleMouseAdapter0());
			}
		});		
		
		// ピース操作ボタン // 
		Button0 = new Button("0");
		Button0.setFont(new Font("Dialog", Font.BOLD, 15));
		Button0.setBounds(50, 70, 50, 40);
		Button0.addActionListener(new ActionListener() {       // イベントリスナーの登録
			// ボタンクリック
			@Override
			public void actionPerformed(ActionEvent e) {
				piece = 0;
				start();
			}
		});		 
		Button1 = new Button("1");
		Button1.setFont(new Font("Dialog", Font.BOLD, 15));
		Button1.setBounds(100, 70, 50, 40);
		Button1.addActionListener(new ActionListener() {       // イベントリスナーの登録
			// ボタンクリック
			@Override
			public void actionPerformed(ActionEvent e) {
				piece = 1;
				start();
			}
		});
		Button2 = new Button("2");
		Button2.setFont(new Font("Dialog", Font.BOLD, 15));
		Button2.setBounds(150, 70, 50, 40);
		Button2.addActionListener(new ActionListener() {       // イベントリスナーの登録
			// ボタンクリック
			@Override
			public void actionPerformed(ActionEvent e) {
				piece = 2;
				start();
			}
		});		
		Button3 = new Button("3");
		Button3.setFont(new Font("Dialog", Font.BOLD, 15));
		Button3.setBounds(200, 70, 50, 40);
		Button3.addActionListener(new ActionListener() {       // イベントリスナーの登録
			// ボタンクリック
			@Override
			public void actionPerformed(ActionEvent e) {
				piece = 3;
				start();
			}
		});		
		Button4 = new Button("4");
		Button4.setFont(new Font("Dialog", Font.BOLD, 15));
		Button4.setBounds(250, 70, 50, 40);
		Button4.addActionListener(new ActionListener() {       // イベントリスナーの登録
			// ボタンクリック
			@Override
			public void actionPerformed(ActionEvent e) {
				piece = 4;
				start();
			}
		});		
		Button5 = new Button("5");
		Button5.setFont(new Font("Dialog", Font.BOLD, 15));
		Button5.setBounds(300, 70, 50, 40);
		Button5.addActionListener(new ActionListener() {       // イベントリスナーの登録
			// ボタンクリック
			@Override
			public void actionPerformed(ActionEvent e) {
				piece = 5;
				start();
			}
		});		
		Button6 = new Button("6");
		Button6.setFont(new Font("Dialog", Font.BOLD, 15));
		Button6.setBounds(350, 70, 50, 40);
		Button6.addActionListener(new ActionListener() {       // イベントリスナーの登録
			// ボタンクリック
			@Override
			public void actionPerformed(ActionEvent e) {
				piece = 6;
				start();
			}
		});		
		Button7 = new Button("7");
		Button7.setFont(new Font("Dialog", Font.BOLD, 15));
		Button7.setBounds(400, 70, 50, 40);
		Button7.addActionListener(new ActionListener() {       // イベントリスナーの登録
			// ボタンクリック
			@Override
			public void actionPerformed(ActionEvent e) {
				piece = 7;
				start();
			}
		});		
		Button8 = new Button("8");
		Button8.setFont(new Font("Dialog", Font.BOLD, 15));
		Button8.setBounds(450, 70, 50, 40);
		Button8.addActionListener(new ActionListener() {       // イベントリスナーの登録
			// ボタンクリック
			@Override
			public void actionPerformed(ActionEvent e) {
				piece = 8;
				start();
			}
		});		
		Button9 = new Button("9");
		Button9.setFont(new Font("Dialog", Font.BOLD, 15));
		Button9.setBounds(500, 70, 50, 40);
		Button9.addActionListener(new ActionListener() {       // イベントリスナーの登録
			// ボタンクリック
			@Override
			public void actionPerformed(ActionEvent e) {
				piece = 9;
				start();
			}
		});
		Button10 = new Button("10");
		Button10.setFont(new Font("Dialog", Font.BOLD, 15));
		Button10.setBounds(550, 70, 50, 40);
		Button10.addActionListener(new ActionListener() {       // イベントリスナーの登録
			// ボタンクリック
			@Override
			public void actionPerformed(ActionEvent e) {				
				piece = 10;
				start();
			}
		});		 
		Button11 = new Button("11");
		Button11.setFont(new Font("Dialog", Font.BOLD, 15));
		Button11.setBounds(600, 70, 50, 40);
		Button11.addActionListener(new ActionListener() {       // イベントリスナーの登録
			// ボタンクリック
			@Override
			public void actionPerformed(ActionEvent e) {
				piece = 11;
				start();
			}
		});
		Button12 = new Button("12");
		Button12.setFont(new Font("Dialog", Font.BOLD, 15));
		Button12.setBounds(650, 70, 50, 40);
		Button12.addActionListener(new ActionListener() {       // イベントリスナーの登録
			// ボタンクリック
			@Override
			public void actionPerformed(ActionEvent e) {
				piece = 12;
				start();
			}
		});		
		Button13 = new Button("13");
		Button13.setFont(new Font("Dialog", Font.BOLD, 15));
		Button13.setBounds(700, 70, 50, 40);
		Button13.addActionListener(new ActionListener() {       // イベントリスナーの登録
			// ボタンクリック
			@Override
			public void actionPerformed(ActionEvent e) {
				piece = 13;
				start();
			}
		});		
		Button14 = new Button("14");
		Button14.setFont(new Font("Dialog", Font.BOLD, 15));
		Button14.setBounds(750, 70, 50, 40);
		Button14.addActionListener(new ActionListener() {       // イベントリスナーの登録
			// ボタンクリック
			@Override
			public void actionPerformed(ActionEvent e) {
				piece = 14;
				start();
			}
		});		
		Button15 = new Button("15");
		Button15.setFont(new Font("Dialog", Font.BOLD, 15));
		Button15.setBounds(800, 70, 50, 40);
		Button15.addActionListener(new ActionListener() {       // イベントリスナーの登録
			// ボタンクリック
			@Override
			public void actionPerformed(ActionEvent e) {
				piece = 15;
				start();
			}
		});		
		
		c = new Choice();
		c.setFont(new Font("Dialog", Font.BOLD, 15));
		c.setBounds(50, 40, 90, 40);
		c.add(Lebel[0]); // 初級
		c.add(Lebel[1]); // 中級
		c.add(Lebel[2]); // 上級
		
		c.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e){
				System.out.println(c.getItem(0));
				//if(c.getItem(0).equals("0")) {
				//p = 0;
				//}
				//start();
			}
		});
		 
		// ボタンの追加 //
		this.add(resetButton);
		this.add(EasyButton);
		this.add(NormalButton);
		this.add(HardButton);
		this.add(OKButton);
		this.add(Button0);
		this.add(Button1);
		this.add(Button2);
		this.add(Button3);
		this.add(Button4);
		this.add(Button5);
		this.add(Button6);
		this.add(Button7);
		this.add(Button8);
		this.add(Button9);
		this.add(Button10);
		this.add(Button11);
		this.add(Button12);
		this.add(Button13);
		this.add(Button14);
		this.add(Button15);
		
		// フレームの設定 //		
		setSize(width, height);
		setLayout(null);    // フレームのレイアウトマネージャを無効にする
		setVisible(true);	
	}	
		
	
	// スレッドの開始
	public void start() {
		Thread th = new Thread(this);
		th.start();
	}

	@Override
	public void run() {
		try {
			addMouseListener(new SampleMouseAdapter());
			Thread.sleep(100);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g){
		String str = "Next Piece >>";
		g.setFont(new Font("Dialog", Font.BOLD, 20));
		g.drawString(str, 650, 390);

		String str2 = "Complete!";
		g.setFont(new Font("Dialog", Font.BOLD, 30));
		g.setColor(Color.red);
		g.drawString(str2, 650, 540);

	
		
		// easy //
		if(level == 0) {
			g.drawImage(waku,  50, 120,  this);   // 枠（グレー）
			g.drawImage(mihon,  650, 120,  this);   // 見本			
			g.drawImage(im3, x[3], y[3],  this);   // 右下のピース(3)			
			g.drawImage(im2, x[2], y[2],  this);   // 左下のピース(1)
			g.drawImage(im1, x[1], y[1],  this);   // 右上のピース(2)
			g.drawImage(im0, x[0], y[0],  this);   // 左上のピース(0)
		} 
		// normal // 
		else if(level == 1) {
			g.drawImage(waku2,  50, 120,  this);   // 枠（グレー）
			g.drawImage(mihon2,  650, 120,  this);   // 見本	
			g.drawImage(h8, x[8], y[8],  this);   // ピース(8)	
			g.drawImage(h7, x[7], y[7],  this);   // ピース(7)	
			g.drawImage(h6, x[6], y[6],  this);   // ピース(6)
			g.drawImage(h5, x[5], y[5],  this);   // ピース(5)	
			g.drawImage(h4, x[4], y[4],  this);   // ピース(4)	
			g.drawImage(h3, x[3], y[3],  this);   // ピース(3)	
			g.drawImage(h2, x[2], y[2],  this);   // ピース(2)	
			g.drawImage(h1, x[1], y[1],  this);   // ピース(1)
			g.drawImage(h0, x[0], y[0],  this);   // ピース(0)			
		} 
		// hard //
		else if(level == 2) {
			g.drawImage(waku3,  50, 120,  this);   // 枠（グレー）
			g.drawImage(mihon3,  650, 120,  this);   // 見本			
			g.drawImage(t15, x[15], y[15],  this);   // ピース(15)	
			g.drawImage(t14, x[14], y[14],  this);   // ピース(14)	
			g.drawImage(t13, x[13], y[13],  this);   // ピース(13)	
			g.drawImage(t12, x[12], y[12],  this);   // ピース(12)	
			g.drawImage(t11, x[11], y[11],  this);   // ピース(11)
			g.drawImage(t10, x[10], y[10],  this);   // ピース(10)
			g.drawImage(t9, x[9], y[9],  this);      // ピース(9)	
			g.drawImage(t8, x[8], y[8],  this);      // ピース(8)	
			g.drawImage(t7, x[7], y[7],  this);      // ピース(7)	
			g.drawImage(t6, x[6], y[6],  this);      // ピース(6)
			g.drawImage(t5, x[5], y[5],  this);      // ピース(5)	
			g.drawImage(t4, x[4], y[4],  this);      // ピース(4)	
			g.drawImage(t3, x[3], y[3],  this);      // ピース(3)	
			g.drawImage(t2, x[2], y[2],  this);      // ピース(2)	
			g.drawImage(t1, x[1], y[1],  this);      // ピース(1)
			g.drawImage(t0, x[0], y[0],  this);      // ピース(0)
		}
	}
		
	class SampleMouseAdapter extends MouseAdapter{
		public void mousePressed(MouseEvent e){			
			int i = 0;
			while(i < 16) {
				if(piece == i) {
					x[i] = e.getX();
					y[i] = e.getY();
				}
				i++;
			}
			repaint();
		}
	}
	
	class SampleMouseAdapter0 extends MouseAdapter{
		public void mousePressed(MouseEvent e){			
			repaint();
		}
	}

	
	class SampleWindowListener extends WindowAdapter{
		// ウィンドウを閉じる
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

}




