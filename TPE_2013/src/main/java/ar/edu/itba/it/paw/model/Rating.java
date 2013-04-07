package ar.edu.itba.it.paw.model;

public class Rating extends AbstractModel {

	private int score;
	private String comment;
	private User user;

	public Rating(int id, int score, String comment, User user) {
		super(id);
		this.score = score;
		this.comment = comment;
		this.user = user;
	}

}
