package example2Search;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.*;

public class TableModelTest extends JFrame {
	// ���̺� ������
	String[] tcolumn = { "������", "ȭ����", "������", "�����", "�ݿ���" };
	String[][] tdata = {

			{ "1", "2", "3", "4", "5", "6" },

			{ "1", "1", "2", "3", "4", "5" },

			{ "2", "3", "3", "4", "5", "6" } };

	// �޺� �ڽ� ������
	String[] cbdata = { "��ü", "1", "2", "3", "4", "5", "6" };
	// ������Ʈ
	DefaultTableModel dtm;// ����Ʈ ���̺� ��
	JTable jt;// ���̺�
	JComboBox jcb;// �޺� �ڽ�
	
	public TableModelTest() { // ������
		
		// �г� ����
		JPanel jp = new JPanel();
		
		// ����Ʈ ���̺� �� ����
		dtm = new DefaultTableModel(tdata, tcolumn);
	
		// ���̺� ����
		jt = new JTable(dtm);
		jt.setPreferredScrollableViewportSize(new Dimension(400, 200)); // ���̺� ũ��

		jt.setEnabled(true); // ���̺� �� ���� ���� ����
		
		// ���̺� ��ũ�� ���� ����
		JScrollPane sp = new JScrollPane(jt);
		
		// ���̺� ��ũ�� ��å
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jp.add(sp);
		
		// �� ����
		JLabel jl = new JLabel("������ �˻� �޺� �ڽ�");
		jp.add(jl);
	
		// �˻��� �޺� �ڽ� ����
		jcb = new JComboBox(cbdata);
		jcb.addActionListener(new MyAciontListener());
		jp.add(jcb);
		add(jp);
		setTitle("���̺� �� �׽�Ʈ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);

	}

	private class MyAciontListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == jcb) {
				// "��ü"�� ��
				if (jcb.getSelectedItem() == "��ü") {
					dtm = new DefaultTableModel(tdata, tcolumn);
					jt.setModel(dtm);
					return;
				}

				// "��ü"�� �ƴ� ��
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