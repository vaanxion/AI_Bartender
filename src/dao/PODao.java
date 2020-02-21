package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.PO;
import util.DBConn;

public class PODao {
	String sql;
	String tableName = "PO";
	boolean b;
	static Connection conn = DBConn.getConn();

	public boolean update(PO po) {

		if (po.getAction().equals("add")) {
			sql = "insert into " + tableName + "values(?,?,?,?,?,?,?,?)";
		} else {
			sql = "update " + tableName + " set total = ? , status = ? , updateUser = ? where id = ? ";
		}

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			if (po.getAction().equals("add")) {
				ps.setString(1, po.getId());
				ps.setInt(2, po.getTotal());
				ps.setString(3, po.getOwner());
				ps.setString(4, po.getStatus());
				ps.setString(5, po.getCreateUser());
				ps.setString(6, po.getCreateTime());
				ps.setString(7, po.getUpdateUser());
				ps.setString(8, po.getUpdateTime());
			} else if (po.getAction().equals("update")) {
				ps.setInt(1, po.getTotal());
				ps.setString(2, po.getStatus());
				ps.setString(3, po.getUpdateUser());
				ps.setString(4, po.getId());
			}

			b = ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println(e);
		}

		return b;
	}

	public boolean del(PO po) {
		sql = "delete from " + tableName + " where id = ? ";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, po.getId());
			b = ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println(e);
		}

		return b;
	}

	public ArrayList<PO> query(PO po) {
		sql = "select * from " + tableName;
		if (po != null) {
			sql += " where id = '" + po.getId() + "'";
		}
		sql += " order by id desc";
		System.out.println("PO ="+po);
		ArrayList<PO> arr = new ArrayList();
		PO rsPo;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				rsPo = new PO();
				rsPo.setId(rs.getString("id"));
				rsPo.setTotal(rs.getInt("total"));
				rsPo.setOwner(rs.getString("owner"));
				rsPo.setStatus(rs.getString("status"));
				arr.add(rsPo);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return arr;
	}

	public static void main(String[] arg) {

//		conn;
//		ArrayList<PO> arr = new PODao().query(null);
//		System.out.println(arr.size());
	}

}
