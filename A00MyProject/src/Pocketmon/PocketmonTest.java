package Pocketmon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PocketmonTest {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			String quary = "SELECT * FROM POCKETMON";
			conn = PocketmonDBconnection.getConnection();
			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();

			System.out.println("No Name Type1 Type2 Class Specificity1 Specificity2 Description");
			System.out.println("===============================================================");

			while (rs.next()) {
				int No = rs.getInt(1);
				String Name = rs.getString(2);
				String Type1 = rs.getString(3);
				String Type2 = rs.getString(4);
				String Class = rs.getString(5);
				String Specificity1 = rs.getString(6);
				String Specificity2 = rs.getString(7);
				String Description = rs.getString(8);

				String result = No + " " + Name + " " + Type1 + " " + Type2 + " " + Class + " " + Specificity1 + " "
						+ Specificity2 + " " + Description;
				System.out.println(result);
			}

		} catch (SQLException sqle) {
			System.out.println("SELECT문에서 예외 발생");
			sqle.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}

	}

}
