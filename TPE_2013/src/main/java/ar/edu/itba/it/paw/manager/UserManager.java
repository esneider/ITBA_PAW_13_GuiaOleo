package ar.edu.itba.it.paw.manager;

public interface UserManager {
		
	public boolean existsUser();
	
	public void resetUser(int id);

	public void setUser(int id);

}
