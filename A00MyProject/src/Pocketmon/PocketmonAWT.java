package Pocketmon;

import java.awt.FileDialog;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
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

	// 이미지 구현
	static byte[] bytes;
	static JLabel lblNewLabel_1;

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

	static String a;
	static String b;
	static int d;

	public static class PocketmonExitClass extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}

	}

	public static class listup implements ActionListener {// 목록 구현
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
						+ Type2T1.getText() + "%' and class like '%" + ClassT1.getText() + "%'";
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
			System.out.println("133lines : "+ picsave);
			// add JLabel에 이미지 넣기
			addjl.setIcon(new ImageIcon(picsave));
		}
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
				conn = PocketmonDBconnection.getConnection();
				pstm = conn.prepareStatement(
						"INSERT INTO POCKETMON(NO, NAME,TYPE1,TYPE2,CLASS,SPECIFICITY1,SPECIFICITY2,description, IMAGE) "
								+ "values" + "('" + b + "'," + "'" + addnameT.getText() + "'," + "'"
								+ addtype1T.getText() + "'," + "'" + addtype2T.getText() + "'," + "'"
								+ addclassT.getText() + "'," + "'" + addspecificity1T.getText() + "'," + "'"
								+ addspecificity2T.getText() + "'," + "'" + adddescriptionT.getText() + "'," + "?)");
				try {
					imageInputStream = new FileInputStream(new File(picsave));
					System.out.println("Line186" + picsave);
				} catch (FileNotFoundException e1) {
					System.out.println(e1);
					e1.printStackTrace();
				}
				pstm.setBinaryStream(1, imageInputStream);
				pstm.execute();
			} catch (SQLException sqle) {
				System.out.println("SELECT2문에서 예외 발생");
				sqle.printStackTrace();
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

			System.out.println("197lines : ");

			// add 파트 라벨 구현 및 위치 크기
			JLabel addno = new JLabel("No  . :");
			JLabel addname = new JLabel("Name :");
			JLabel addtype1 = new JLabel("Type1 :");
			JLabel addtype2 = new JLabel("Type2 :");
			JLabel addclass = new JLabel("Class :");
			JLabel addspecificity1 = new JLabel("Specificity1 :");
			JLabel addspecificity2 = new JLabel("Specificity2 :");
			JLabel adddescription = new JLabel("Description :");
			addno.setBounds(430, 20, 100, 20);
			addname.setBounds(560, 20, 100, 20);
			addtype1.setBounds(430, 50, 100, 20);
			addtype2.setBounds(600, 50, 100, 20);
			addclass.setBounds(430, 135, 100, 20);
			addspecificity1.setBounds(430, 80, 100, 20);
			addspecificity2.setBounds(630, 80, 100, 20);
			adddescription.setBounds(430, 190, 100, 20);
			addJF.getContentPane().add(addno);
			addJF.getContentPane().add(addname);
			addJF.getContentPane().add(addtype1);
			addJF.getContentPane().add(addtype2);
			addJF.getContentPane().add(addclass);
			addJF.getContentPane().add(addspecificity1);
			addJF.getContentPane().add(addspecificity2);
			addJF.getContentPane().add(adddescription);

			// add 파트 텍스트필드 구현 및 위치 크기
			addnoT = new JTextField(b);
			addnoT.setEditable(false);
			addnameT = new JTextField();
			addtype1T = new JTextField();
			addtype2T = new JTextField();
			addclassT = new JTextField();
			addspecificity1T = new JTextField();
			addspecificity2T = new JTextField();
			adddescriptionT = new JTextArea();
			JScrollPane jsp= new JScrollPane(adddescriptionT);
			addnoT.setBounds(470, 20, 70, 20);
			addnameT.setBounds(610, 20, 150, 20);
			addtype1T.setBounds(480, 50, 110, 20);
			addtype2T.setBounds(650, 50, 110, 20);
			addclassT.setBounds(430, 160, 330, 20);
			addspecificity1T.setBounds(430, 105, 130, 20);
			addspecificity2T.setBounds(630, 105, 130, 20);
			jsp.setBounds(430, 215, 330, 100);
			
			addJF.getContentPane().add(addnoT);
			addJF.getContentPane().add(addnameT);
			addJF.getContentPane().add(addtype1T);
			addJF.getContentPane().add(addtype2T);
			addJF.getContentPane().add(addclassT);
			addJF.getContentPane().add(addspecificity1T);
			addJF.getContentPane().add(addspecificity2T);
			addJF.getContentPane().add(jsp);

			// 그림 불러오기 버튼
			JButton addpicbut = new JButton("그림");
			addpicbut.setBounds(430, 320, 70, 20);
			addJF.getContentPane().add(addpicbut);
			addpicbut.addActionListener(SA3);

			// 입력값 저장 버튼
			JButton addsavebut = new JButton("입력");
			addsavebut.setBounds(690, 320, 70, 20);
			addJF.getContentPane().add(addsavebut);
			addsavebut.addActionListener(adddata);

			// add JLabel 구현
			addjl = new JLabel();
			addjl.setBounds(20, 15, 390, 330);
			// String pic2=pic(picsave);
			addjl.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\pocket\\lugia.jpg"));
			addJF.getContentPane().add(addjl);

			// add backLabel 구현
			JLabel addBackLabel = new JLabel();
			addBackLabel.setBounds(0, 0, 800, 400);
			addBackLabel.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\pocket\\addback.jpg"));
			addJF.getContentPane().add(addBackLabel);

			// add프레임 구현
			addJF.getContentPane().setLayout(null);
			addJF.setBounds(250, 250, 800, 400);
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
					String No = rs.getString(1);
					String Name = rs.getString(2);
					String Type1 = rs.getString(3);
					String Type2 = rs.getString(4);
					String Class = rs.getString(5);
					String Specificity1 = rs.getString(6);
					String Specificity2 = rs.getString(7);
					String Description = rs.getString(8);
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
					Image image = lblNewLabel_1.getToolkit().createImage(bytes);
					lblNewLabel_1.setIcon(new ImageIcon(image));
				}
			} catch (SQLException sqle) {
				System.out.println("SELECT문에서 예외 발생");
				sqle.printStackTrace();
			} // 연결 종류
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

	public static class testbutton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("test");
		}
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("Pocketmon Test");
		f.setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\user\\Desktop\\pocket\\\uC804\uCCB4\uCC3D\uC544\uC774\uCF58.jpg"));
		PocketmonExitClass ec = new PocketmonExitClass();
		f.getContentPane().setLayout(null);

		JPanel jp5 = new JPanel();
		jp5.setBounds(730, 160, 300, 265);
		f.getContentPane().add(jp5);
		jp5.setLayout(null);

		// 라벨 시작
		JLabel No = new JLabel("No.      : ");
		JLabel Name = new JLabel("Name : ");
		JLabel Type1 = new JLabel("Type1 : ");
		JLabel Type2 = new JLabel("Type2 : ");
		JLabel Class = new JLabel("Class :");
		JLabel Specificity1 = new JLabel("Specificity1:");
		JLabel Specificity2 = new JLabel("Specificity2:");

		// 라벨 위치
		No.setBounds(10, 10, 50, 20);
		Name.setBounds(10, 40, 50, 20);
		Type1.setBounds(10, 70, 50, 20);
		Type2.setBounds(10, 100, 50, 20);
		Class.setBounds(5, 30, 45, 20);
		Specificity1.setBounds(5, 65, 75, 20);
		Specificity2.setBounds(5, 100, 75, 20);

		// 라벨 텍스트 시작
		NoT = new JLabel();
		NameT = new JLabel();
		Type1T = new JLabel();
		Type2T = new JLabel();
		ClassT = new JLabel();
		Specificity1T = new JLabel();
		Specificity2T = new JLabel();

		// 라벨 텍스트 위치
		NoT.setBounds(70, 10, 90, 20);
		NameT.setBounds(70, 40, 90, 20);
		Type1T.setBounds(70, 70, 90, 20);
		Type2T.setBounds(70, 100, 90, 20);
		ClassT.setBounds(85, 30, 90, 20);
		Specificity1T.setBounds(85, 65, 90, 20);
		Specificity2T.setBounds(85, 100, 90, 20);

		// swing Table 설정 방법
		// 테이블을 생성해서 content pane에 추가합니다(내가 원하는 기술 하나선택시 행이 전체선택되는 부분)
		String colname[] = { "No.", "Name", "Type1", "Type2", "Class" };
		model = new DefaultTableModel(colname, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		table = new JTable(model);
		JScrollPane js = new JScrollPane(table);
		js.setBounds(0, 0, 300, 265);
		jp5.add(js);

		// 마우스 클릭
		table.addMouseListener(new MyMouseListener());
		f.getContentPane().setLayout(null);
		// setBounds(x, y, width, height);

		// 가운데 이미지
		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\pocket\\pika.jpg"));
		lblNewLabel_1.setBounds(195, 60, 340, 250);
		// f.getContentPane().add(lblNewLabel_1);
		f.add(lblNewLabel_1);

		// 오른쪽 위 라벨
		JLabel lblNewLabel_4 = new JLabel();
		lblNewLabel_4.setIcon(
				new ImageIcon("C:\\Users\\user\\Desktop\\pocket\\\uC624\uB978\uCABD\uC704(\uD074\uB798\uC2A4).jpg"));
		lblNewLabel_4.setBounds(540, 90, 180, 150);
		f.getContentPane().add(lblNewLabel_4);
		lblNewLabel_4.add(Class);
		lblNewLabel_4.add(ClassT);
		lblNewLabel_4.add(Specificity1);
		lblNewLabel_4.add(Specificity1T);
		lblNewLabel_4.add(Specificity2);
		lblNewLabel_4.add(Specificity2T);

		// 왼쪽 라벨
		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\pocket\\\uC67C\uCABD \uC704(\uB118\uBC84).jpg"));
		lblNewLabel_2.setBounds(10, 100, 180, 130);
		f.getContentPane().add(lblNewLabel_2);
		lblNewLabel_2.add(No);
		lblNewLabel_2.add(NoT);
		lblNewLabel_2.add(Name);
		lblNewLabel_2.add(NameT);
		lblNewLabel_2.add(Type1);
		lblNewLabel_2.add(Type1T);
		lblNewLabel_2.add(Type2);
		lblNewLabel_2.add(Type2T);

		// 라벨, 텍스트상자, 버튼을 생성해서 테이블 아래쪽에 추가합니다
		JButton button1 = new JButton();
		button1.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\pocket\\\uC544\uC774\uCF58.jpg"));
		f.getContentPane().add(button1);
		button1.setBounds(987, 133, 40, 24);
		// 추가,삭제 버튼에 대한 리스너를 등록
		button1.addActionListener(new listup(table));

		// 테스트 버튼
		JButton button3 = new JButton("테스트");
		f.add(button3);
		button3.setBounds(900, 133, 80, 24);
		button3.addActionListener(new testbutton());

		// Search 파트 라벨 텍스트 시작
		NoT1 = new TextField();
		f.getContentPane().add(NoT1);

		// Search 파트 라벨 위치설정
		NoT1.setBounds(786, 37, 60, 20);

		// Search 파트 시작
		JLabel No1 = new JLabel("No. :");
		f.getContentPane().add(No1);

		// Search 파트 위치설정
		No1.setBounds(730, 37, 30, 20);
		JLabel Name1 = new JLabel("Name :");
		f.getContentPane().add(Name1);
		Name1.setBounds(853, 37, 50, 20);
		NameT1 = new TextField();
		f.getContentPane().add(NameT1);
		NameT1.setBounds(907, 37, 117, 20);
		Type1T1 = new TextField();
		f.getContentPane().add(Type1T1);
		Type1T1.setBounds(786, 75, 85, 20);
		JLabel Type11 = new JLabel("Type1 :");
		f.getContentPane().add(Type11);
		Type11.setBounds(730, 75, 50, 20);
		JLabel Type21 = new JLabel("Type2 :");
		f.getContentPane().add(Type21);
		Type21.setBounds(880, 75, 50, 20);
		Type2T1 = new TextField();
		f.getContentPane().add(Type2T1);
		Type2T1.setBounds(932, 75, 90, 20);
		ClassT1 = new TextField();
		f.getContentPane().add(ClassT1);
		ClassT1.setBounds(787, 112, 235, 20);
		JLabel Class1 = new JLabel("Class :");
		f.getContentPane().add(Class1);
		Class1.setBounds(730, 112, 50, 20);
		DescriptionT = new JLabel();
		f.getContentPane().add(DescriptionT);
		DescriptionT.setBounds(25, 351, 680, 60);
		JLabel Description = new JLabel("Description :");
		Description.setBounds(20, 5, 100, 20);

		// 라벨 구현
		JLabel lblNewLabel_5 = new JLabel();
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\pocket\\\uB514\uC2A4\uD06C\uB9BD\uC158.jpg"));
		lblNewLabel_5.setBounds(8, 320, 710, 105);
		f.getContentPane().add(lblNewLabel_5);
		lblNewLabel_5.add(Description);

		// 데이터 추가
		JButton addButton = new JButton();
		addButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\pocket\\add.jpg"));
		addButton.setBounds(730, 134, 40, 23);
		f.getContentPane().add(addButton);
		addButton.addActionListener(new addbox(table));

		// 바탕 라벨
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(0, 0, 725, 431);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\pocket\\back.jpg"));
		f.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\pocket\\\uAC80\uC0C9\uB780.jpg"));
		lblNewLabel_3.setBounds(726, 0, 309, 431);

		f.getContentPane().add(addButton);
		f.getContentPane().add(lblNewLabel_3);
		f.setSize(1050, 470);
		f.addWindowListener(ec);
		f.setUndecorated(false);
		f.setResizable(false);// 창 크기 수정 불가
		f.setVisible(true);

	}
}
