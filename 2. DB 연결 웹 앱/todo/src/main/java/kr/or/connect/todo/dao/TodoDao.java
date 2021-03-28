package kr.or.connect.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.or.connect.todo.dto.TodoDto;

public class TodoDao {
	private static String dburl = "jdbc:mysql://localhost:3306/connectdb";
	private static String dbUser = "connectuser";
	private static String dbpasswd = "connect123!@#";
	
	public int addTodo(TodoDto dto) {
		int insertCount = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "insert into todo(title, name, sequence) values(?,?,?)";
		
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getName());
			ps.setInt(3, dto.getSequence());

			insertCount = ps.executeUpdate(); //select 이외에는 executeUpdate()

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return insertCount;
	}
	
	public List<TodoDto> getTodos(){
		List<TodoDto> list = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "select id, title, name, sequence, type, regdate from todo order by regdate";
		
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					Long id = rs.getLong(1);
					String title = rs.getString(2);
					String name = rs.getString(3);
					int sequence = rs.getInt(4);
					String type = rs.getString(5);
					String regDate = rs.getString(6);
					TodoDto dto = new TodoDto(id, title, name, sequence, regDate, type);
					list.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return list;
	}
	
	public int updateTodo(TodoDto dto) {
		int updateCount = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "update todo set type = ? where id = ?";
		
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, dto.getType());
			ps.setLong(2, dto.getId());

			updateCount = ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return updateCount;
	}
}
