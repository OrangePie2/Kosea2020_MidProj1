package Pocketmon;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PocketmonAWT {
	
	static String addquary2;
	static boolean imageChanged = false;
	static boolean addimageChanged = false;
	
	static JFrame f;
	static JLabel backgroundlabel;

	static TextField NoT1;
	static TextField NameT1;
	static TextField Type1T1;
	static TextField Type2T1;
	static TextField ClassT1;

	// 메인 결과창
	static JLabel NoT;
	static JLabel NameT;
	static JLabel Type1T;
	static JLabel Type2T;
	static JLabel ClassT;
	static JLabel Specificity1T;
	static JLabel Specificity2T;
	static JLabel DescriptionT;

	static String No;
	static String Name;
	static String Type1;
	static String Type2;
	static String Class;
	static String Specificity1;
	static String Specificity2;
	static String Description;

	// 이미지 구현
	static byte[] bytes;
	static JLabel Mainpic;

	static JTable table;
	static DefaultTableModel model;
	static JLabel jlabel;

	// 이미지 삽입 라벨, 이미지주소 입력
	static JLabel addjl;
	static String picsave;

	// addbox 및 데이터 삽입 부분 텍스트 필드
	static JTextField addnoT;
	static JTextField addnameT;
	static JTextField addtype1T;
	static JTextField addtype2T;
	static JTextField addclassT;
	static JTextField addspecificity1T;
	static JTextField addspecificity2T;
	static JTextArea adddescriptionT;

	// rewrite
	static JTextField renoT;
	static JTextField renaT;
	static JTextField ret1T;
	static JTextField ret2T;
	static JTextField reclT;
	static JTextField res1T;
	static JTextField res2T;
	static JTextArea redeT;
	static JScrollPane rejsp;

	// 수정 그림 불러오기
	static String piccome;

	static String a;
	static String b;
	static int d;

	// 체크
	static JFrame rechick;
	// 삭제
	static JFrame redelete;

	static JButton rewritebutton;
	static JButton button5;
	static JButton button6;

	public static class PocketmonExitClass extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}

	}

	// 검색창에서 목록 구현
	public static class listup implements ActionListener {
		JTable table1;

		public listup(JTable table) {
			this.table1 = table;
		}

		public void actionPerformed(ActionEvent e) {
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			model.setNumRows(0);
			try {
				table.removeAll();
				String quary = "SELECT * FROM POCKETMON where No like '%" + NoT1.getText() + "%' and name like '%"
						+ NameT1.getText() + "%' and type1 like '%" + Type1T1.getText() + "%' and type2 like '%"
						+ Type2T1.getText() + "%' and class like '%" + ClassT1.getText() + "%'" + " order by " + " No";
				System.out.println(quary);

				conn = PocketmonDBconnection.getConnection();
				pstm = conn.prepareStatement(quary);
				rs = pstm.executeQuery();
				while (rs.next()) {
					String arr[] = new String[5];
					arr[0] = rs.getString(1);
					arr[1] = rs.getString(2);
					arr[2] = rs.getString(3);
					arr[3] = rs.getString(4);
					arr[4] = rs.getString(5);
					model = (DefaultTableModel) table1.getModel();
					model.addRow(arr);
				}
			} catch (SQLException sqle) {
				System.out.println("SELECT문에서 예외 발생");
				sqle.printStackTrace();
			} // 연결 종류
			
		}

	}

	// 추가 창에서 그림 클릭시 구현
	public static class selectpicture implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame picJF = new JFrame();
			FileDialog pic = new FileDialog(picJF, "File Open", FileDialog.LOAD);
			pic.setDirectory("C:\\Windows");
			pic.setVisible(true);
			picsave = pic.getDirectory() + pic.getFile();
			System.out.println("133lines : " + picsave);
			
			//이미지크기 맞추기
			ImageIcon ii=new ImageIcon(picsave);
			Dimension d= addjl.getSize();
			ii = imageSetsize(ii, d.width, d.height);
			
			// add JLabel에 이미지 넣기
			addjl.setIcon(ii);
			addimageChanged = true;
		}
	}
	//Imgae변환 (이미지 아이콘 품질 깨지지 않고 변환하기 위해)
	
	public static ImageIcon imageSetsize(ImageIcon icon, int x, int y) {
		Image a = icon.getImage(); // ImageIcon Imgae변환 (이미지 아이콘 품질 깨지지 않고 변환하기 위해)
		Image b = a.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH); // (image함수 getScaledInstance로 품질 유지한 채// 사이즈
																			// 변경)
		ImageIcon c = new ImageIcon(b);
		return c;
	}
	

	public static class adddata implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			FileInputStream imageInputStream = null;

			try {
				String quary1 = "select max(no) from pocketmon";
				conn = PocketmonDBconnection.getConnection();
				pstm = conn.prepareStatement(quary1);
				rs = pstm.executeQuery();

				while (rs.next()) {
					a = rs.getString(1);
					int c = Integer.parseInt(a);
					d = c + 1;
					b = Integer.toString(d);
					addnoT.setText(b);
				}
			} catch (SQLException sqle) {
				System.out.println("SELECT2문에서 예외 발생");
				sqle.printStackTrace();
			} // 연결 종류

			try {
				
				if (addimageChanged == true) {
					addquary2 = "INSERT INTO POCKETMON(NO, NAME,TYPE1,TYPE2,CLASS,SPECIFICITY1,SPECIFICITY2,description, IMAGE) "
							+ "values" + "('" + b + "'," + "'" + addnameT.getText() + "'," + "'" + addtype1T.getText()
							+ "'," + "'" + addtype2T.getText() + "'," + "'" + addclassT.getText() + "'," + "'"
							+ addspecificity1T.getText() + "'," + "'" + addspecificity2T.getText() + "'," + "'"
							+ adddescriptionT.getText() + "'," + "?)";
				} else {
					addquary2 = "INSERT INTO POCKETMON(NO, NAME,TYPE1,TYPE2,CLASS,SPECIFICITY1,SPECIFICITY2,description) "
							+ "values" + "('" + b + "'," + "'" + addnameT.getText() + "'," + "'" + addtype1T.getText()
							+ "'," + "'" + addtype2T.getText() + "'," + "'" + addclassT.getText() + "'," + "'"
							+ addspecificity1T.getText() + "'," + "'" + addspecificity2T.getText() + "'," + "'"
							+ adddescriptionT.getText() + "')";
				}
				
				String quary=addquary2;
				System.out.println("Line 223: "+quary);
				conn = PocketmonDBconnection.getConnection();
				pstm = conn.prepareStatement(quary);
				
				if (addimageChanged == true) {
				try {
					imageInputStream = new FileInputStream(new File(picsave));
				} catch (FileNotFoundException e1) {
					System.out.println(e1);
					e1.printStackTrace();}
				pstm.setBinaryStream(1, imageInputStream);
				}
				
				pstm.execute();
			
				
			} catch (SQLException sqle) {
				System.out.println("SELECT2문에서 예외 발생: "+sqle);
			} // 연결 종류
		}
	}

	// 추가 버튼 클릭시 나오는 새창
	public static class addbox implements ActionListener {
		JTable table2;

		public addbox(JTable table2) {
			this.table2 = table2;
		}

		public void actionPerformed(ActionEvent e) {
			JFrame addJF = new JFrame("데이터 추가 입력");
			selectpicture SA3 = new selectpicture();
			adddata adddata = new adddata();
			JLabel addstatuslabel = new JLabel();
			
			
			System.out.println("197lines : ");

			// add 파트 텍스트필드 구현 및 위치 크기
			addnoT = new JTextField(b);
			addnoT.setEditable(false);
			addnameT = new JTextField("이름");
			addtype1T = new JTextField("타입1");
			addtype2T = new JTextField("타입2");
			addclassT = new JTextField("클래스");
			addspecificity1T = new JTextField("S1");
			addspecificity2T = new JTextField("S2");
			adddescriptionT = new JTextArea("설명");
			JScrollPane jsp = new JScrollPane(adddescriptionT);

			addnoT.setBounds(499, 41, 40, 21);
			addnameT.setBounds(646, 41, 113, 21);
			addtype1T.setBounds(528, 78, 47, 21);
			addtype2T.setBounds(711, 78, 47, 21);
			addclassT.setBounds(455, 195, 302, 20);
			addspecificity1T.setBounds(454, 133, 117, 20);
			addspecificity2T.setBounds(640, 133, 117, 20);
			jsp.setBounds(445, 260, 321, 98);
			
			addJF.getContentPane().add(addnoT);
			addJF.getContentPane().add(addnameT);
			addJF.getContentPane().add(addtype1T);
			addJF.getContentPane().add(addtype2T);
			addJF.getContentPane().add(addclassT);
			addJF.getContentPane().add(addspecificity1T);
			addJF.getContentPane().add(addspecificity2T);
			addJF.getContentPane().add(jsp);

			// 그림 불러오기 버튼
			JButton addpicbut = new JButton();
			addpicbut.setBounds(445, 370, 70, 20);
			addpicbut.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\design\\imagebutton89x30.png"));
			addJF.getContentPane().add(addpicbut);
			addpicbut.addActionListener(SA3);

			// 입력값 저장 버튼
			JButton addsavebut = new JButton();
			addsavebut.setBounds(695, 370, 70, 20);
			addsavebut.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\design\\confirmbutton89x30.jpg"));
			addJF.getContentPane().add(addsavebut);
			addsavebut.addActionListener(adddata);

			// add JLabel 구현
			addjl = new JLabel();
			addjl.setBounds(39, 40, 352, 352);
			// String pic2=pic(picsave);
			addjl.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\pocket\\lugia.jpg"));
			addJF.getContentPane().add(addjl);

			// add backLabel 구현
			JLabel addBackLabel = new JLabel();
			addBackLabel.setBounds(0, 0, 417, 432);
			addBackLabel.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\design\\popimage_417x432.png"));
			addJF.getContentPane().add(addBackLabel);

			// add프레임 구현
			addJF.add(addstatuslabel);
			addstatuslabel.setBounds(417, 0, 415, 432);
			addstatuslabel.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\design\\popsearch415x432.png"));
			addJF.getContentPane().setLayout(null);
			addJF.setBounds(250, 250, 848, 470);
			addJF.setVisible(true);
		}
	}

	// 마우스 클릭시 데이터 메인 모니터에 구현
	public static class MyMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			try {
				int row = table.getSelectedRow();// 순서 값 불러오기
				String no = (String) table.getValueAt(row, 0);// 순서값을 이용하여 포켓몬 넘버 불러오기

				System.out.println("291 lines : -------------------------");
				String quary = "SELECT * FROM POCKETMON where no like '%" + no + "%'";
				System.out.println(quary);

				conn = PocketmonDBconnection.getConnection();
				pstm = conn.prepareStatement(quary);
				rs = pstm.executeQuery();
				bytes = null;// DB이미지 구현
				while (rs.next()) {
					No = rs.getString(1);
					Name = rs.getString(2);
					Type1 = rs.getString(3);
					Type2 = rs.getString(4);
					Class = rs.getString(5);
					Specificity1 = rs.getString(6);
					Specificity2 = rs.getString(7);
					Description = rs.getString(8);
					bytes = rs.getBytes(9);

					NoT.setText(No);
					NameT.setText(Name);
					Type1T.setText(Type1);
					Type2T.setText(Type2);
					ClassT.setText(Class);
					Specificity1T.setText(Specificity1);
					Specificity2T.setText(Specificity2);
					DescriptionT.setText(Description);

					// 이미지 불러오기
					Image image = Mainpic.getToolkit().createImage(bytes);
					// 이미지 크기맞추기
					ImageIcon ii=new ImageIcon(image);
					Dimension d= Mainpic.getSize();
					ii = imageSetsize(ii, d.width, d.height);
					
					Mainpic.setIcon(ii);
				}
			} catch (SQLException sqle) {
				System.out.println("SELECT문에서 예외 발생");
				sqle.printStackTrace();
			} // 연결 종류
			rewritebutton.setEnabled(true);
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

	}

	// 삭제 기능
	public static class deletebutton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("delete");

			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			try {
				int row = table.getSelectedRow();// 순서 값 불러오기
				String no = (String) table.getValueAt(row, 0);// 순서값을 이용하여 포켓몬 넘버 불러오기

				System.out.println("373 lines : -------------------------");
				String quary = "DELETE FROM POCKETMON where NO = '" + no + "'";
				System.out.println(quary);

				conn = PocketmonDBconnection.getConnection();
				pstm = conn.prepareStatement(quary);
				rs = pstm.executeQuery();
			} catch (SQLException sqle) {
				System.out.println("SELECT문에서 예외 발생");
				sqle.printStackTrace();
			} // 연결 종류

			redelete = new JFrame();
			JLabel red= new JLabel();
			redelete.setUndecorated(true);
			
			JButton button8 = new JButton();
			button8.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\design\\confirmbutton89x30.jpg"));
			button8.addActionListener(new listup(table));
			button8.addActionListener(new Redelete());
			button8.setBounds(160, 85, 89, 30);
			
			red.add(button8);
			red.setBounds(0, 0, 400, 200);
			red.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\design\\smallpop.jpg"));
			
			
			redelete.add(red);
			redelete.setLayout(null);
			redelete.setBounds(340, 200, 400, 200);
			redelete.setVisible(true);

		}

	}

	public static class Redelete implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Redelete");
			redelete.dispose();
		}
	}

	// 수정 기능
	public static class rewritebutton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("rewrite");

			renoT = new JTextField(No);
			renaT = new JTextField(Name);
			ret1T = new JTextField(Type1);
			ret2T = new JTextField(Type2);
			reclT = new JTextField(Class);
			res1T = new JTextField(Specificity1);
			res2T = new JTextField(Specificity2);
			redeT = new JTextArea(Description);
			rejsp = new JScrollPane(redeT);

			// 라벨에 텍스값 넣기
			NoT.add(renoT);
			NameT.add(renaT);
			Type1T.add(ret1T);
			Type2T.add(ret2T);
			ClassT.add(reclT);
			Specificity1T.add(res1T);
			Specificity2T.add(res2T);
			DescriptionT.add(rejsp);

			// 택스트 상자 사이즈
			renoT.setSize(90, 20);
			renaT.setSize(90, 20);
			ret1T.setSize(90, 20);
			ret2T.setSize(90, 20);
			reclT.setSize(90, 20);
			res1T.setSize(90, 20);
			res2T.setSize(90, 20);
			redeT.setSize(680, 90);
			rejsp.setSize(680, 90);
			// (0, 0, 725, 431);
			button5 = new JButton();
			button5.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\design\\confirmbutton89x30.jpg"));
			backgroundlabel.add(button5);
			button5.setBounds(598, 357, 70, 20);
			button5.addActionListener(new rewrite2button());

			button6 = new JButton();
			backgroundlabel.add(button6);
			button6.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\design\\imagebutton89x30.png"));
			button6.setBounds(598, 335, 70, 20);
			button6.addActionListener(new repicture2button());

		}

	}

	// 수정2 기능
	public static class rewrite2button implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("rewrite2");

			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			FileInputStream imageInputStream = null;

			int row = table.getSelectedRow();// 순서 값 불러오기
			String no = (String) table.getValueAt(row, 0);// 순서값을 이용하여 포켓몬 넘버 불러오기

			try {
				conn = PocketmonDBconnection.getConnection();

				String query = "UPDATE  POCKETMON SET " + " NO=" + "'" + renoT.getText() + "'," + " NAME=" + "'"
						+ renaT.getText() + "'," + " TYPE1=" + "'" + ret1T.getText() + "'," + " TYPE2=" + "'"
						+ ret2T.getText() + "'," + " CLASS=" + "'" + reclT.getText() + "'," + " SPECIFICITY1=" + "'"
						+ res1T.getText() + "'," + " SPECIFICITY2=" + "'" + res2T.getText() + "'," + " DESCRIPTION="
						+ "'" + redeT.getText();// + " IMAGE=?" + " WHERE NO =" + "'" + no + "'";

				if (imageChanged == true) {
					query += "', IMAGE=?" + " WHERE NO =" + "'" + no + "'";
					try {
						imageInputStream = new FileInputStream(new File(piccome));
						System.out.println("Line495" + piccome);

					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
						System.out.println(e1);
					}

				} else {
					query += "' WHERE NO =" + "" + no + "";
				}
				System.out.println("538 lines : ---------------------------------");
				System.out.println("539 lines : " + query);
				pstm = conn.prepareStatement(query);
				if (imageChanged == true) {
					pstm.setBinaryStream(1, imageInputStream);
				}


				pstm.execute();
			} catch (SQLException sqle) {
				System.out.println("Line 553: 수정 부분에서 예외 발생");
				sqle.printStackTrace();

			} // 연결 종류

			rechick = new JFrame();
			rechick.setUndecorated(true);
			JLabel rcl= new JLabel();
			
			JButton button7 = new JButton();
			button7.addActionListener(new listup(table));
			button7.addActionListener(new Recheck());
			button7.setBounds(160, 85, 89, 30);
			button7.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\design\\confirmbutton89x30.jpg"));
			
			rcl.setBounds(0,0, 400, 200);
			rcl.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\design\\smallpop.jpg"));
			rcl.add(button7);
			
			rechick.add(rcl);
			rechick.setLayout(null);
			rechick.setBounds(348, 200, 400, 200);
			rechick.setVisible(true);
			rewritebutton.setEnabled(false);
			button5.setVisible(false);
			button6.setVisible(false);

		}

	}

	public static class Recheck implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			try {
				System.out.println("291 lines : -------------------------");
				String quary = "SELECT * FROM POCKETMON where no =" + NoT.getText();
				System.out.println(quary);

				conn = PocketmonDBconnection.getConnection();
				pstm = conn.prepareStatement(quary);
				rs = pstm.executeQuery();
				bytes = null;// DB이미지 구현
				while (rs.next()) {
					No = rs.getString(1);
					Name = rs.getString(2);
					Type1 = rs.getString(3);
					Type2 = rs.getString(4);
					Class = rs.getString(5);
					Specificity1 = rs.getString(6);
					Specificity2 = rs.getString(7);
					Description = rs.getString(8);
					bytes = rs.getBytes(9);

					NoT.setText(No);
					NameT.setText(Name);
					Type1T.setText(Type1);
					Type2T.setText(Type2);
					ClassT.setText(Class);
					Specificity1T.setText(Specificity1);
					Specificity2T.setText(Specificity2);
					DescriptionT.setText(Description);

					// 이미지 불러오기
					Image image = Mainpic.getToolkit().createImage(bytes);
					
					// 이미지 크기맞추기
					ImageIcon ii=new ImageIcon(image);
					Dimension d= Mainpic.getSize();
					ii = imageSetsize(ii, d.width, d.height);
					
					Mainpic.setIcon(ii);
				}
			} catch (SQLException sqle) {
				System.out.println("SELECT문에서 예외 발생");
				sqle.printStackTrace();
			} // 연결 종류

			System.out.println("Recheck");
			renoT.setVisible(false);
			renaT.setVisible(false);
			ret1T.setVisible(false);
			ret2T.setVisible(false);
			reclT.setVisible(false);
			res1T.setVisible(false);
			res2T.setVisible(false);
			redeT.setVisible(false);
			rejsp.setVisible(false);

			button5.setVisible(false);
			button6.setVisible(false);
			rechick.dispose();
		}
	}

	// 수정 그림불러오기
	public static class repicture2button implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("repicture2button ");
			f = new JFrame();
			FileDialog pico = new FileDialog(f, "File Open", FileDialog.LOAD);
			pico.setDirectory("C:\\Windows");
			pico.setVisible(true);
			piccome = pico.getDirectory() + pico.getFile();
			System.out.println("133lines : " + piccome);
			
			//이미지크기 맞추기
			ImageIcon ii=new ImageIcon(piccome);
			Dimension d= Mainpic.getSize();
			ii = imageSetsize(ii, d.width, d.height);
			
			// add JLabel에 이미지 넣기
			Mainpic.setIcon(ii);
			imageChanged = true;
		}
	}
	
	public static class Rdbutton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			try {
				int rd= (int) ((Math.random()*150)+1);
				String no = Integer.toString(rd); 
				String quary = "SELECT * FROM POCKETMON where no like '%" + no + "%'";
				

				conn = PocketmonDBconnection.getConnection();
				pstm = conn.prepareStatement(quary);
				rs = pstm.executeQuery();
				bytes = null;// DB이미지 구현
				while (rs.next()) {
					No = rs.getString(1);
					Name = rs.getString(2);
					Type1 = rs.getString(3);
					Type2 = rs.getString(4);
					Class = rs.getString(5);
					Specificity1 = rs.getString(6);
					Specificity2 = rs.getString(7);
					Description = rs.getString(8);
					bytes = rs.getBytes(9);

					NoT.setText(No);
					NameT.setText(Name);
					Type1T.setText(Type1);
					Type2T.setText(Type2);
					ClassT.setText(Class);
					Specificity1T.setText(Specificity1);
					Specificity2T.setText(Specificity2);
					DescriptionT.setText(Description);

					// 이미지 불러오기
					Image image = Mainpic.getToolkit().createImage(bytes);
					// 이미지 크기맞추기
					ImageIcon ii=new ImageIcon(image);
					Dimension d= Mainpic.getSize();
					ii = imageSetsize(ii, d.width, d.height);
					
					Mainpic.setIcon(ii);
				}
			} catch (SQLException sqle) {
				System.out.println("SELECT문에서 예외 발생");
				sqle.printStackTrace();
			} // 연결 종류
			
		}
	}
	

	public static void main(String[] args) {
		f = new JFrame("Pocketmon Test");
		PocketmonExitClass ec = new PocketmonExitClass();
		f.getContentPane().setLayout(null);

		//Scroll panel
		JPanel jp5 = new JPanel();
		jp5.setBounds(733, 248, 290, 235);
		f.getContentPane().add(jp5);
		jp5.setLayout(null);


		// 라벨 텍스트 시작
		NoT = new JLabel("넘버");
		NameT = new JLabel("이름");
		Type1T = new JLabel("타입1");
		Type2T = new JLabel("타입2");
		ClassT = new JLabel("쿨래스");
		Specificity1T = new JLabel("S1");
		Specificity2T = new JLabel("S2");

		// 라벨 텍스트 위치
		NoT.setBounds(80, 25, 90, 20);
		NameT.setBounds(295, 15, 90, 20);
		Type1T.setBounds(110, 23, 90, 20);
		Type2T.setBounds(110, 50, 90, 20);
		ClassT.setBounds(30, 95, 90, 20);
		Specificity1T.setBounds(30, 160, 90, 20);
		Specificity2T.setBounds(30, 205, 90, 20);

		// 목로값 불러오기 swing Table 설정 방법
		// 테이블을 생성해서 content pane에 추가합니다(내가 원하는 기술 하나선택시 행이 전체선택되는 부분)
		String colname[] = { "No.", "Name", "Type1", "Type2", "Class" };
		model = new DefaultTableModel(colname, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		table = new JTable(model);
		JScrollPane js = new JScrollPane(table);
		js.setBounds(0, 0, 290, 235);
		jp5.add(js);
		table.addMouseListener(new MyMouseListener());
		f.getContentPane().setLayout(null);
		// setBounds(x, y, width, height);

		// 가운데 이미지
		Mainpic = new JLabel();
		Mainpic.setBounds(300, 127, 340, 250);
		// f.getContentPane().add(lblNewLabel_1);
		f.add(Mainpic);

		// 오른쪽 위 라벨
		JLabel classlabel = new JLabel();
		classlabel.setIcon(
				new ImageIcon("C:\\Users\\user\\Desktop\\design\\status209x250.png"));
		classlabel.setBounds(40, 128, 209, 250);
		f.getContentPane().add(classlabel);
		classlabel.add(ClassT);
		classlabel.add(Specificity1T);
		classlabel.add(Specificity2T);
		classlabel.add(Type1T);
		classlabel.add(Type2T);
		
		

		// 왼쪽 라벨
		JLabel nonamelabel = new JLabel();
		nonamelabel.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\design\\NoName543x59.png"));
		nonamelabel.setBounds(80, 70, 543,59);
		f.getContentPane().add(nonamelabel);
		nonamelabel.add(NoT);
		nonamelabel.add(NameT);
		

		// 라벨, 텍스트상자, 버튼을 생성해서 테이블 아래쪽에 추가합니다
		JButton searchbutton = new JButton();
		searchbutton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\design\\searchbutton.png"));
		f.getContentPane().add(searchbutton);
		searchbutton.setBounds(961, 204, 38, 38);
		// 추가,삭제 버튼에 대한 리스너를 등록
		searchbutton.addActionListener(new listup(table));

		// 삭제테스트 버튼
		JButton deletebutton = new JButton();
		f.add(deletebutton);
		deletebutton.setBounds(824, 204, 39, 38);
		deletebutton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\design\\deletebutton.png"));
		deletebutton.addActionListener(new deletebutton());

		// 수정테스트 버튼
		rewritebutton = new JButton();
		rewritebutton.setEnabled(false);
		f.add(rewritebutton);
		rewritebutton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\design\\rewritebutton.png"));
		rewritebutton.setBounds(892, 204, 40, 38);
		rewritebutton.addActionListener(new rewritebutton());
		

		// Search 파트 라벨 텍스트 시작
		NoT1 = new TextField();
		f.getContentPane().add(NoT1);

		// Search 파트 라벨 위치설정
		NoT1.setBounds(805, 81, 40, 20);

		// Search 파트 위치설정
		NameT1 = new TextField();
		f.getContentPane().add(NameT1);
		NameT1.setBounds(937, 81, 51, 20);
		Type1T1 = new TextField();
		f.getContentPane().add(Type1T1);
		Type1T1.setBounds(868, 108, 120, 20);
		Type2T1 = new TextField();
		f.getContentPane().add(Type2T1);
		Type2T1.setBounds(868, 137, 120, 20);
		ClassT1 = new TextField();
		f.getContentPane().add(ClassT1);
		ClassT1.setBounds(868, 163, 120, 20);
		

		// 라벨 구현
		JLabel descriptionlabel = new JLabel();
		descriptionlabel.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\design\\descriptionlabel.png"));
		descriptionlabel.setBounds(23, 375, 710, 105);
		f.getContentPane().add(descriptionlabel);
		DescriptionT = new JLabel("설명");
		descriptionlabel.add(DescriptionT);
		DescriptionT.setBounds(25, 20, 620, 60);

		// 데이터 추가 
		JButton addButton = new JButton();
		addButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\design\\addButton.png"));
		addButton.setBounds(756, 204, 39, 39);
		f.getContentPane().add(addButton);
		addButton.addActionListener(new addbox(table));

		// 바탕 라벨
		backgroundlabel = new JLabel();
		backgroundlabel.setBounds(0, 0, 730, 512);
		backgroundlabel.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\design\\resultpart_712x512.png"));
		f.getContentPane().add(backgroundlabel);
		
		//랜덤버턴
		JButton Rdbutton = new JButton();
		backgroundlabel.add(Rdbutton);
		Rdbutton.setBounds(635, 80, 40, 40);
		Rdbutton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\design\\Rdbutton58x58.png"));
		Rdbutton.addActionListener(new Rdbutton());

		JLabel searchpartlabel = new JLabel();
		searchpartlabel.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\design\\searchpart_338x512.png"));
		searchpartlabel.setBounds(710, 0, 360, 512);

		f.getContentPane().add(addButton);
		f.getContentPane().add(searchpartlabel);
		f.setSize(1060, 550);
		f.addWindowListener(ec);
		f.setUndecorated(false);
		f.setResizable(false);// 창 크기 수정 불가
		f.setVisible(true);
		
	}

	
}
