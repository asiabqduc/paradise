/**
 * 
 */
package net.ecoparadise.framework.controller;

import javax.inject.Inject;

import net.ecoparadise.framework.model.FilterBase;
import net.ecoparadise.utility.FacesService;

/**
 * @author bqduc
 *
 */
public abstract class Home <E, F extends FilterBase> extends RootController {
	private static final long serialVersionUID = -3418849331359820691L;

	//protected abstract E buildNoneObject();
	@Inject
	protected FacesService facesService;

	final public String delete() {
		return deleteSelectedObject();
	}

	protected String deleteSelectedObject() {
		return "success";
	}
}