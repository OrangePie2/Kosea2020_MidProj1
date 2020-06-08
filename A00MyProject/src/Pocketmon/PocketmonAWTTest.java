package Pocketmon;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PocketmonAWTTest {

	static TextField NoT1;
	static TextField NameT1;
	static TextField Type1T1;
	static TextField Type2T1;
	static TextField ClassT1;

	static TextField NoT;
	static TextField NameT;
	static TextField Type1T;
	static TextField Type2T;
	static TextField ClassT;
	static TextField Specificity1T;
	static TextField Specificity2T;
	static TextField DescriptionT;

	static JTable table;
	static DefaultTableModel model;

	public static class PocketmonExitClass extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}

	}

	public static class SelectAdd2 implements ActionListener {// 목록 구현
		JTable table1;

		public SelectAdd2(JTable table) {
			this.table1 = table;
		}

		public void actionPerformed(ActionEvent e) {
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			model.setNumRows(0);
			try {
				table.removeAll();
				String quary = "SELECT * FROM POCKETMON where No like '%" + NoT1.getText() + "%'";

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

	public static class MyMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			try {

				int row = table.getSelectedRow();//순서 값 불러오기
				String no = (String) table.getValueAt(row, 0);//순서값을 이용하여 포켓몬 넘버 불러오기

				String quary = "SELECT * FROM POCKETMON where no=" + no;
				conn = PocketmonDBconnection.getConnection();
				pstm = conn.prepareStatement(quary);
				rs = pstm.executeQuery();
				while (rs.next()) {
					String No = rs.getString(1);
					String Name = rs.getString(2);
					String Type1 = rs.getString(3);
					String Type2 = rs.getString(4);
					String Class = rs.getString(5);
					String Specificity1 = rs.getString(6);
					String Specificity2 = rs.getString(7);
					String Description = rs.getString(8);

					NoT.setText(No);
					NameT.setText(Name);
					Type1T.setText(Type1);
					Type2T.setText(Type2);
					ClassT.setText(Class);
					Specificity1T.setText(Specificity1);
					Specificity2T.setText(Specificity2);
					DescriptionT.setText(Description);

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

	public static void main(String[] args) {
		JFrame f = new JFrame("Pocketmon Test");

		PocketmonExitClass ec = new PocketmonExitClass();

		Panel p1 = new Panel();
		Panel p2 = new Panel();
		Panel p3 = new Panel();
		JPanel p4 = new JPanel();
		JPanel jp5 = new JPanel();

		// 이미지
		Canvas c = new Canvas();
		c.setBackground(Color.blue);
		c.setBounds(195, 60, 340, 250);

		// 라벨 시작
		Label No = new Label("No.      : ");
		Label Name = new Label("Name : ");
		Label Type1 = new Label("Type1 : ");
		Label Type2 = new Label("Type2 : ");
		Label Class = new Label("Class :");
		Label Specificity1 = new Label("Specificity1:");
		Label Specificity2 = new Label("Specificity2:");
		Label Description = new Label("Description : ");

		// 라벨 위치
		No.setBounds(10, 10, 50, 20);
		Name.setBounds(10, 40, 50, 20);
		Type1.setBounds(10, 70, 40, 20);
		Type2.setBounds(10, 100, 40, 20);
		Class.setBounds(5, 30, 40, 20);
		Specificity1.setBounds(5, 65, 65, 20);
		Specificity2.setBounds(5, 100, 65, 20);
		Description.setBounds(15, 5, 100, 20);

		// Search 파트 시작
		Label No1 = new Label("No. : ", Label.RIGHT);
		Label Name1 = new Label("Name : ", Label.RIGHT);
		Label Type11 = new Label("Type1 : ");
		Label Type21 = new Label("Type2 : ");
		Label Class1 = new Label("Class : ");

		// Search 파트 위치설정
		No1.setBounds(12, 45, 30, 20);
		Name1.setBounds(120, 45, 50, 20);
		Type11.setBounds(10, 75, 50, 20);
		Type21.setBounds(155, 75, 50, 20);
		Class1.setBounds(10, 103, 50, 20);

		// 라벨 텍스트 시작
		NoT = new TextField();
		NameT = new TextField();
		Type1T = new TextField();
		Type2T = new TextField();
		ClassT = new TextField();
		Specificity1T = new TextField();
		Specificity2T = new TextField();
		DescriptionT = new TextField();
		// 라벨 텍스트 위치
		NoT.setBounds(70, 10, 90, 20);
		NameT.setBounds(70, 40, 90, 20);
		Type1T.setBounds(70, 70, 90, 20);
		Type2T.setBounds(70, 100, 90, 20);
		ClassT.setBounds(80, 30, 80, 20);
		Specificity1T.setBounds(80, 65, 80, 20);
		Specificity2T.setBounds(80, 100, 80, 20);
		DescriptionT.setBounds(15, 25, 680, 70);

		// Search 파트 라벨 텍스트 시작
		NoT1 = new TextField();
		NameT1 = new TextField();
		Type1T1 = new TextField();
		Type2T1 = new TextField();
		ClassT1 = new TextField();

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
		// 라벨, 텍스트상자, 버튼을 생성해서 테이블 아래쪽에 추가합니다
		JButton button1 = new JButton("search");
		button1.setBounds(205, 135, 90, 20);
		p4.add(button1);
		// 추가,삭제 버튼에 대한 리스너를 등록
		button1.addActionListener(new SelectAdd2(table));

		// 마우스 클릭
		table.addMouseListener(new MyMouseListener());

		// Search 파트 라벨 위치설정
		NoT1.setBounds(60, 45, 60, 20);
		NameT1.setBounds(170, 45, 125, 20);
		Type1T1.setBounds(60, 75, 85, 20);
		Type2T1.setBounds(205, 75, 90, 20);
		ClassT1.setBounds(60, 105, 235, 20);

		// 이미지 구현
		f.add(c);

		// 라벨, 라벨텍스트 구현
		p1.setBounds(10, 100, 180, 130);
		p2.setBounds(540, 90, 170, 150);
		p3.setBounds(10, 320, 710, 105);
		p4.setBounds(730, 0, 300, 160);
		jp5.setBounds(730, 160, 300, 265);
		// setBounds(x, y, width, height);

		p1.setBackground(Color.magenta);
		p2.setBackground(Color.orange);
		p3.setBackground(Color.pink);
		p4.setBackground(Color.green);
		jp5.setBackground(Color.WHITE);
		p1.add(No);
		p1.add(NoT);
		p1.add(Name);
		p1.add(NameT);
		p1.add(Type1);
		p1.add(Type1T);
		p1.add(Type2);
		p1.add(Type2T);
		p2.add(Class);
		p2.add(ClassT);
		p2.add(Specificity1);
		p2.add(Specificity1T);
		p2.add(Specificity2);
		p2.add(Specificity2T);
		p3.add(Description);
		p3.add(DescriptionT);
		p4.add(No1);
		p4.add(NoT1);
		p4.add(Name1);
		p4.add(NameT1);
		p4.add(Type11);
		p4.add(Type1T1);
		p4.add(Type21);
		p4.add(Type2T1);
		p4.add(Class1);
		p4.add(ClassT1);

		f.add(p1);
		p1.setLayout(null);
		f.add(p2);
		p2.setLayout(null);
		f.add(p3);
		p3.setLayout(null);
		f.add(p4);
		p4.setLayout(null);
		f.add(jp5);
		jp5.setLayout(null);
		f.setLayout(new BorderLayout());
		f.setSize(1050, 470);
		f.addWindowListener(ec);
		f.setVisible(true);

	}

}
