package ar.edu.itba.it.paw.utils;



public class EnhancedModelAndView extends ModelAndView {

    public EnhancedModelAndView(String title) {

        super();
        this.addObject("documentTitle", title);
    }
}

