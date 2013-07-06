package ar.edu.itba.it.paw.web.provider;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.request.resource.PackageResourceReference;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.picture.Picture;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.web.application.RestaurantApplication;
import ar.edu.itba.it.paw.web.picture.PictureImageResource;

public class ImageProvider {

	public static Image getImage(String id, User user) {
		if (user.getAvatar() != null) {
			return new NonCachingImage(id, new PictureImageResource(
					new EntityModel<Picture>(Picture.class, user.getAvatar())));
		} else {
			return new Image(id, new PackageResourceReference(
					RestaurantApplication.class, "default.jpg"));
		}
	}
	
}
