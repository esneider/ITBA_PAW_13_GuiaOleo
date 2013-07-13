package ar.edu.itba.it.paw.web.picture;

import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.DynamicImageResource;

import ar.edu.itba.it.paw.domain.picture.Picture;

public class PictureImageResource extends DynamicImageResource {

	private static final long serialVersionUID = 5725004041044856828L;
	
	private IModel<Picture> pictureModel;
	
	public PictureImageResource(final IModel<Picture> pictureModel){
		super(pictureModel.getObject().getMime());
		this.pictureModel = pictureModel;
	}

	@Override
	protected byte[] getImageData(Attributes attributes) {
		if (pictureModel.getObject() != null)
			return pictureModel.getObject().getBytes();
		return null;
	}
		
	
}
