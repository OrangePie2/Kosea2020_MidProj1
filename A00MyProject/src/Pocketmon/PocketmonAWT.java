package Pocketmon;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PocketmonAWT {
	static TextArea List1;

	public static class PocketmonExitClass extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}

	}

	public static void main(String[] args) {
		Frame f = new Frame("Pocketmon");
		PocketmonExitClass ec = new PocketmonExitClass();

		Panel p1 = new Panel();
		Panel p2 = new Panel();
		Panel p3 = new Panel();
		Panel p4 = new Panel();

		// 서치버튼
		Button b1 = new Button("Search");
		b1.setBounds(243, 135, 50, 20);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List1.setText(" No     Name     Type1     Type2     Class"+"\n"+"--------------------------------------");
				
				//버튼 클릭시 data 베이스 연결
				Connection conn = null;
				PreparedStatement pstm = null;
				ResultSet rs = null;
				
				try {
					String quary = "SELECT * FROM POCKETMON ";
					conn = PocketmonDBconnection.getConnection();
					pstm = conn.prepareStatement(quary);
					rs = pstm.executeQuery();

					while (rs.next()) {
						int No = rs.getInt(1);
						String Name = rs.getString(2);
						String Type1 = rs.getString(3);
						String Type2 = rs.getString(4);
						String Class = rs.getString(5);

						String s = List1.getText();
						String result ="  "+ No + " " + Name + " " + Type1 + "  " + Type2 + "  " + Class;
						
						String line="--------------------------------------";
	
						List1.setText(s + "\n"+ result+ "\n"+line);
						
					}
				} catch (SQLException sqle) {
					System.out.println("SELECT문에서 예외 발생");
					sqle.printStackTrace();
				}//연결 종류 
			}
		});

		// 이미지
		Canvas c = new Canvas();
		c.setBackground(Color.blue);
		c.setBounds(195, 60, 340, 250);

		// 라벨 시작
		Label No = new Label("No.      : ");
		Label Name = new Label("Name : ");
		Label Type1 = new Label("Type1 : ");
		Label Type2 = new Label("Type2 : ");
		Label Class = new Label("Class:");
		Label Specificity1 = new Label("Specificity1: ");
		Label Specificity2 = new Label("Specificity2 : ");
		Label Description = new Label("Description: ");
		Label Search = new Label("Search");
		// 라벨 위치
		Description.setBounds(15, 5, 100, 20);

		// Search 파트 시작
		Search.setBounds(40, 40, 10, 10);
		Label No1 = new Label("No. : ", Label.RIGHT);
		Label Name1 = new Label("Name: ", Label.RIGHT);
		Label Type11 = new Label("Type1: ");
		Label Type21 = new Label("Type2: ");
		Label Class1 = new Label("Class: ");
		Label List = new Label("List");

		// Search 파트 위치설정
		No1.setBounds(12, 45, 30, 20);
		Name1.setBounds(120, 45, 50, 20);
		Type11.setBounds(10, 75, 50, 20);
		Type21.setBounds(155, 75, 50, 20);
		Class1.setBounds(10, 103, 50, 20);
		List.setBounds(10, 135, 30, 20);

		// 라벨 텍스트 시작
		TextField NoT = new TextField(10);
		TextField NameT = new TextField(10);
		TextField Type1T = new TextField(10);
		TextField Type2T = new TextField(10);
		TextField ClassT = new TextField(10);
		TextField Specificity1T = new TextField(10);
		TextField Specificity2T = new TextField(10);
		TextField DescriptionT = new TextField();
		// 라벨 텍스트 위치
		DescriptionT.setBounds(15, 25, 680, 70);

		// Search 파트 라벨 텍스트 시작
		TextField NoT1 = new TextField();
		TextField NameT1 = new TextField();
		TextField Type1T1 = new TextField();
		TextField Type2T1 = new TextField();
		TextField ClassT1 = new TextField();
		List1 = new TextArea("검색 리스트", 100, 100, TextArea.SCROLLBARS_BOTH);
		

		// Search 파트 라벨 위치설정
		NoT1.setBounds(60, 45, 60, 20);
		NameT1.setBounds(170, 45, 125, 20);
		Type1T1.setBounds(60, 75, 85, 20);
		Type2T1.setBounds(205, 75, 90, 20);
		ClassT1.setBounds(60, 105, 235, 20);
		List1.setBounds(10, 160, 285, 260);

		// 이미지 구현
		f.add(c);

		// 라벨, 라벨텍스트 구현

		p1.setBounds(10, 100, 180, 130);
		p2.setBounds(540, 90, 170, 150);
		p3.setBounds(10, 320, 710, 105);
		p4.setBounds(730, 0, 300, 425);
		// p1.setBounds(x, y, width, height);

		p1.setBackground(Color.magenta);
		p2.setBackground(Color.orange);
		p3.setBackground(Color.pink);
		p4.setBackground(Color.green);
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
		p4.add(b1);
		p4.add(List);
		p4.add(List1);

		f.add(p1, FlowLayout.CENTER);
		f.add(p2, BorderLayout.CENTER);
		f.add(p3);
		p3.setLayout(null);
		f.add(p4);
		p4.setLayout(null);
		f.setLayout(new BorderLayout());
		f.setSize(1050, 440);
		f.addWindowListener(ec);
		f.setVisible(true);

	}

}
