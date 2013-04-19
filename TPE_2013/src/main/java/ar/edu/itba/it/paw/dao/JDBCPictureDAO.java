package ar.edu.itba.it.paw.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.itba.it.paw.dao.interfaces.PictureDAO;
import ar.edu.itba.it.paw.model.Picture;

public class JDBCPictureDAO extends AbstractDAO implements PictureDAO {

	private static JDBCPictureDAO self;

	public synchronized static PictureDAO getInstance() {

		if (self == null)
			self = new JDBCPictureDAO();

		return self;
	}

	@Override
	public void insert(Picture picture) {

		PreparedStatement ps = execute("INSERT INTO pictures (mime, data) VALUES (?, ?)",
				picture.getMime(), picture.getInputStream());
		
		try {
			ResultSet rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				System.out.println("NEW ID  " + rs.getInt("id"));
				picture.setId(rs.getInt("id"));
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Picture getPictureById(int id) {

		ResultSet rs = executeQuery("SELECT * FROM pictures WHERE id = ?", id);

		try {

			Picture pic = null;

			if (rs.next()) {
				pic = getPicture(rs);
			}
			rs.close();

			return pic;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private Picture getPicture(ResultSet rs){

		try {

			return new Picture(rs.getInt("id"), rs.getBytes("data"), rs.getString("mime"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
}
