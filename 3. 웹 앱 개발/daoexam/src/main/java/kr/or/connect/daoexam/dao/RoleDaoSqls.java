package kr.or.connect.daoexam.dao;
//쿼리 문을 가지고 있는 객체
public class RoleDaoSqls {
	//select all 쿼리
	public static final String SELECT_ALL = "SELECT role_id, description FROM role order by role_id";
	//update문 쿼리, :뒤에 있는 변수가 나중에 값으로 바인딩 될 부분
	public static final String UPDATE = "UPDATE role SET description = :description WHERE ROLE_ID = :roleId";
	//insert는 쿼리가 별도로 필요 없음.
	
	//한 건만 select하는 쿼리, *보다 컬럼명을 정확히 나열하는 것이 좋음
	public static final String SELECT_BY_ROLE_ID = "SELECT role_id, description FROM role where role_id = :roleId";
	//delte 쿼리
	public static final String DELETE_BY_ROLE_ID = "DELETE FROM role WHERE role_id = :roleId";
}	
