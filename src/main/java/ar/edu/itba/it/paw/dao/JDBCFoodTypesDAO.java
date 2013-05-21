package ar.edu.itba.it.paw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.dao.interfaces.FoodTypesDAO;
import ar.edu.itba.it.paw.domain.FoodType;

@Repository
public class JDBCFoodTypesDAO extends AbstractDAO implements FoodTypesDAO {

	private static JDBCFoodTypesDAO self;
	private static Logger logger = Logger.getLogger(JDBCFoodTypesDAO.class);

	public synchronized static FoodTypesDAO getInstance() {
		if (self == null)
			self = new JDBCFoodTypesDAO();
		return self;
	}

	public List<FoodType> getAll() {
		ResultSet rs = executeQuery("SELECT * FROM foodTypes");
		List<FoodType> ls = new ArrayList<FoodType>();
		try {
			while (rs.next())
				ls.add(getFoodType(rs));
			rs.close();
			return ls;
		} catch (SQLException e) {
			logger.error("SQL Error");
		}
		return null;
	}

	public FoodType getSingleFoodType(int id) {
		ResultSet rs = executeQuery("SELECT * FROM foodtypes WHERE id = ?", id);
		try {
			FoodType ft = null;
			if (rs.next())
				ft = getFoodType(rs);
			rs.close();
			return ft;
		} catch (SQLException e) {
			logger.error("SQL Error");
		}
		return null;
	}

	private FoodType getFoodType(ResultSet rs) {

		return null;
	}

}
