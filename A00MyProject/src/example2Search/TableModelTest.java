package example2Search;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.*;

public class TableModelTest extends JFrame {
	// 테이블 데이터
	String[] tcolumn = { "월요일", "화요일", "수요일", "목요일", "금요일" };
	String[][] tdata = {

			{ "1", "2", "3", "4", "5", "6" },

			{ "1", "1", "2", "3", "4", "5" },

			{ "2", "3", "3", "4", "5", "6" } };

	// 콤보 박스 데이터
	String[] cbdata = { "전체", "1", "2", "3", "4", "5", "6" };
	// 컴포넌트
	DefaultTableModel dtm;// 디폴트 테이블 모델
	JTable jt;// 테이블
	JComboBox jcb;// 콤보 박스
	
	public TableModelTest() { // 생성자
		
		// 패널 생성
		JPanel jp = new JPanel();
		
		// 디폴트 테이블 모델 생성
		dtm = new DefaultTableModel(tdata, tcolumn);
	
		// 테이블 생성
		jt = new JTable(dtm);
		jt.setPreferredScrollableViewportSize(new Dimension(400, 200)); // 테이블 크기

		jt.setEnabled(true); // 테이블 셀 편집 가능 여부
		
		// 테이블 스크롤 페인 생성
		JScrollPane sp = new JScrollPane(jt);
		
		// 테이블 스크롤 정책
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jp.add(sp);
		
		// 라벨 생성
		JLabel jl = new JLabel("월요일 검색 콤보 박스");
		jp.add(jl);
	
		// 검색용 콤보 박스 생성
		jcb = new JComboBox(cbdata);
		jcb.addActionListener(new MyAciontListener());
		jp.add(jcb);
		add(jp);
		setTitle("테이블 모델 테스트");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);

	}

	private class MyAciontListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == jcb) {
				// "전체"일 때
				if (jcb.getSelectedItem() == "전체") {
					dtm = new DefaultTableModel(tdata, tcolumn);
					jt.setModel(dtm);
					return;
				}

				// "전체"가 아닐 때
				for (int index = 0; index < jt.getRowCount(); index++) {
					if (!(jt.getValueAt(index, 0).toString() == jcb.getSelectedItem().toString())) {
						dtm.removeRow(index);
						index--;
					}

				}

			}

		}

	}

	public static void main(String[] argv) {
		TableModelTest tmt = new TableModelTest();

	}

}
