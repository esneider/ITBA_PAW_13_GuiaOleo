package ar.edu.itba.it.paw.web.base;

import java.util.Iterator;
 
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
 
public abstract class TypeAhead<T> extends TextField<T> {
 
	private static final long serialVersionUID = -4963794937263363628L;

	private AbstractDefaultAjaxBehavior queryAjaxBehavior;
	private AbstractDefaultAjaxBehavior selectAjaxBehavior;
 
	public TypeAhead(String id) {
		this(id, null, null);
	}
 
	public TypeAhead(String id, IModel<T> model) {
		this(id, model, null);
	}
 
	public TypeAhead(String id, Class<T> type) {
		this(id, null, type);
	}
 
	public TypeAhead(String id, IModel<T> model, Class<T> type) {
		super(id, model, type);
 
		queryAjaxBehavior = new AbstractDefaultAjaxBehavior() {
 
			private static final long serialVersionUID = -6727243429271511803L;

			@Override
			protected void respond(AjaxRequestTarget target) {
 
				processInput();
 
				String js = "window." + TypeAhead.this.getMarkupId(true) + "_process(" + getDataSource() + ");";
 
				target.appendJavaScript(js);
			}
		};
 
		add(queryAjaxBehavior);
 
		selectAjaxBehavior = new AbstractDefaultAjaxBehavior() {
 
			private static final long serialVersionUID = 7942309649658172541L;

			@Override
			protected void respond(AjaxRequestTarget target) {
 
				processInput();
				onSelect(target);
			}
		};
 
		add(selectAjaxBehavior);
	}
 
	@Override
	protected void onAfterRender() {
		super.onAfterRender();
 
		getResponse().write(
			"<script type=\"text/javascript\">\n" +
			"	$(\"#" + this.getMarkupId(true) + "\").typeahead({\n" +
			"		source: function(query, process) {\n" +
			"			window." + this.getMarkupId(true) + "_process=process;\n" +
			"			Wicket.Ajax.get({\n"+
			"				\"u\":\"" + queryAjaxBehavior.getCallbackUrl() + "&" + getInputName() + "=\" + encodeURIComponent(query)\n" +
			"			});\n" +
			"		},\n" +
			"		updater: function(item) {\n" +
			"			Wicket.Ajax.get({\n" +
			"				\"u\":\"" + selectAjaxBehavior.getCallbackUrl() + "&" + getInputName() + "=\" + encodeURIComponent(item)\n" + 
			"			});\n" +
			"			return(item);\n" +
			"		}\n" +
			"	});\n" +
			"</script>\n"
		);
	}
 
	private final CharSequence getDataSource() {
 
		Iterator<T> choices = getChoices();
 
		boolean hasPrev = false;
		String data = "[";
		while (choices.hasNext()) {
 
			T choice = choices.next();
 
			if (hasPrev)
				data += ",";
 
			data += "\"" + choice.toString() + "\"";
 
			hasPrev = true;
		}
		data += "]";
 
		return data;
	}
 
	protected abstract Iterator<T> getChoices();
 
	protected void onSelect(AjaxRequestTarget target) {}
}