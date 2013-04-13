package ar.edu.itba.it.paw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.dao.interfaces.FoodTypesDAO;
import ar.edu.itba.it.paw.model.FoodType;

public class JDBCFoodTypesDAO extends AbstractDAO implements FoodTypesDAO {

	private static JDBCFoodTypesDAO self;

	public synchronized static FoodTypesDAO getInstance() {
		if (self == null)
			self = new JDBCFoodTypesDAO();
		return self;
	}

	public List<FoodType> getAll() {
		ResultSet rs = executeQuery("SELECT * FROM foodTypes");
		List<FoodType> ls = new ArrayList<FoodType>();
		try {
			while (rs.next()) {
				ls.add(getFoodType(rs));
			}
			rs.close();
			return ls;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public FoodType getSingleFoodType(int id) {
		ResultSet rs = executeQuery("SELECT * FROM foodtypes WHERE id = ?", id);
		try {
			rs.next();
			FoodType ft = getFoodType(rs);
			rs.close();
			return ft;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private FoodType getFoodType(ResultSet rs) {
		try {
			return new FoodType(rs.getInt("id"), rs.getString("name"),
					rs.getInt("ammount"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
