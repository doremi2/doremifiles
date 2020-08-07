package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
	
	static {// �� �ѹ��� �ʱ�ȭ. �� �ѹ��� ȣ��. �ڵ�����)
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// �޸� �ε� ... �ѹ��� �ϸ� ��. �׷��� static ����̹��� �ѹ��� �ö󰡸� ��
			// �� ����̹��� ���� ���� �ְ�, ��Ÿ���� ���� ����
		} catch (ClassNotFoundException e) {
			System.out.println("오라클 접속 실패");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} // �׷��� ����ó���� ������
	}// static end

	public static Connection getconnection() {//Ŀ�ؼǰ�ü ��ȯ // �������� Ŀ�ؼǰ�ü ��ȯ
		Connection con = null;
		// �����ϴ� �޼ҵ� ���� ����.
		try {// ������ �� �ִ� �ڵ� ����
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					// ������ �ߴٴ� �ǹ��� ��ü. //�����ؾ��� �� �� �̷��� �ؾ���.
					"MING", "1111");
			//con.setAutoCommit(false);// ����Ŀ��
			System.out.println("SUCCESS");
			// ���Ӽ����ϸ� �ǰ�����, �ϳ��� Ʋ���� ����.

		} catch (SQLException e) {
			System.out.println("FAIL");
			e.printStackTrace();
		} // �׷��� ����ó���� �־���.
		return con;
	}// connect method end

	public static void close(ResultSet rs,PreparedStatement pstmt,Connection con) {
		try {
			if (rs != null) {// insert�� �Ҷ� ������ ����. �׷��� ������ ���� ���ؼ� rs�� null�� �ƴҶ����� close�� �� �ֵ��� �Ѵ�.
				rs.close();
			}
			if(pstmt != null) {
			pstmt.close();
			}
			if(con != null) {
			con.close(); // ���� ���� �� �ְ� ���� �ʰ� ����. ���� �������� �ߴ� �۾����� �ݳ��ϴ� ���� ����. ���� ����
			}
		} catch (SQLException e) {
			System.out.println("close fail");
			e.printStackTrace();
		}
	}//Ŭ���� ��
	
	
	public static void commit(Connection con) {
		try {
			con.commit();//����ó�� �ʼ�
			System.out.println("COMMIT");
		} catch (SQLException e) {
			System.out.println("COMMIT FAIL");
			e.printStackTrace();
		}
	}//Ŀ�� ��
	public static void rollback(Connection con) {
		try {
			con.rollback();
			System.out.println("ROLLBACK");
		} catch (SQLException e) {
			System.out.println("ROLLBACK FAIL");
			e.printStackTrace();
	}
	}//�ѹ� ��

}
