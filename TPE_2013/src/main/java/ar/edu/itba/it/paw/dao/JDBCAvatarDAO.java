package ar.edu.itba.it.paw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.itba.it.paw.dao.interfaces.AvatarDAO;
import ar.edu.itba.it.paw.dao.interfaces.FoodTypesDAO;
import ar.edu.itba.it.paw.model.Avatar;
import ar.edu.itba.it.paw.model.FoodType;

public class JDBCAvatarDAO extends AbstractDAO implements AvatarDAO {

	private static JDBCAvatarDAO self;

	public synchronized static AvatarDAO getInstance() {
		if (self == null)
			self = new JDBCAvatarDAO();
		return self;
	}

	@Override
	public boolean insert(Avatar avatar) {
		return execute("INSERT INTO pictures (mime, data) VALUES (?, ?)",
				avatar.getImgName(), avatar.getFs(), avatar.getFileLength());
	}

	@Override
	public Avatar getAvatarByImgName(String name) {
		ResultSet rs = executeQuery("SELECT * FROM pictures WHERE mime = ?", name);
		try {
			Avatar av = null;
			if (rs.next()) 
				av = getAvatar(rs);
			rs.close();
			return av;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Avatar getAvatarById(int id) {
		ResultSet rs = executeQuery("SELECT * FROM pictures WHERE id = ?", id);
		try {
			Avatar av = null;
			if (rs.next()) 
				av = getAvatar(rs);
			rs.close();
			return av;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Avatar getAvatar(ResultSet rs){
		try {
			return new Avatar(rs.getInt("id"), rs.getBytes("data"), rs.getString("mime"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
