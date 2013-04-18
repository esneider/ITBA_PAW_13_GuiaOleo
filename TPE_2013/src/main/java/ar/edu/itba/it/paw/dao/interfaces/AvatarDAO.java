package ar.edu.itba.it.paw.dao.interfaces;

import ar.edu.itba.it.paw.model.Avatar;

public interface AvatarDAO {

	public boolean insert(Avatar avatar);
	
	public Avatar getAvatarByImgName(String name);
	
	public Avatar getAvatarById(int id);
	
}
