package example;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SelectAddDeleteMain {

	public static void main(String[] args) {
		// JFrame swing에서 사용하는 frame
		JFrame frame = new JFrame("참가자명단프로그램");
		frame.setPreferredSize(new Dimension(400, 200));
		frame.setLocation(500, 400);
		Container contentPane = frame.getContentPane();

		// 테이블을 생성해서 content pane에 추가합니다(내가 원하는 기술 하나선택시 행이 전체선택되는 부분)
		String colname[] = { "이름", "나이", "성별" };
		DefaultTableModel model = new DefaultTableModel(colname, 0);
		JTable table = new JTable(model);
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
		
		
		// 라벨, 텍스트상자, 버튼을 생성해서 테이블 아래쪽에 추가합니다
		JPanel panel = new JPanel();
		JTextField text1 = new JTextField(6);
		JTextField text2 = new JTextField(3);
		JTextField text3 = new JTextField(2);
		JButton button1 = new JButton("추가");
		JButton button2 = new JButton("삭제");
		
		panel.add(new JLabel("이름1"));
		panel.add(text1);
		panel.add(new JLabel("나이2"));
		panel.add(text2);
		panel.add(new JLabel("성별3"));
		panel.add(text3);
		panel.add(button1);
		panel.add(button2);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		// 추가,삭제 버튼에 대한 리스너를 등록
		button1.addActionListener(new SelectAdd(table,text1,text2,text3));
		button2.addActionListener(new SelectDelete(table));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

}
