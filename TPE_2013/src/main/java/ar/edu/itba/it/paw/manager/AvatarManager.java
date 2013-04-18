package ar.edu.itba.it.paw.manager;

import java.io.FileInputStream;

import ar.edu.itba.it.paw.dao.JDBCAvatarDAO;
import ar.edu.itba.it.paw.dao.interfaces.AvatarDAO;
import ar.edu.itba.it.paw.model.Avatar;

public class AvatarManager {

	private static AvatarManager self;
	private AvatarDAO DAO;

	public synchronized static AvatarManager getInstance() {
		if (self == null)
			self = new AvatarManager(new JDBCAvatarDAO());
		return self;
	}

	private AvatarManager(AvatarDAO avDAO) {
		this.DAO = avDAO;
	}
	
	public boolean insert(String imageName, FileInputStream fs, long imgLength) {
		Avatar av = new Avatar(fs, imageName, (int)imgLength);
		return DAO.insert(av);
	}
	
	public Avatar getAvatarByName(String name){
		return DAO.getAvatarByImgName(name);
	}
	
	public Avatar getAvatarById(int id) {
		return DAO.getAvatarById(id);
	}
	
}
