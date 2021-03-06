package net.ecoparadise.css.service.contact;

import org.springframework.data.domain.Page;

import net.ecoparadise.entity.contact.CTAContact;
import net.ecoparadise.exceptions.ObjectNotFoundException;
import net.ecoparadise.framework.model.SearchParameter;
import net.ecoparadise.framework.service.GenericService;

public interface ContactService extends GenericService<CTAContact, Long> {

	/**
	 * Get one Contact with the provided code.
	 * 
	 * @param code
	 *            The Contact code
	 * @return The Contact
	 * @throws ObjectNotFoundException
	 *             If no such Contact exists.
	 */
	CTAContact getObjectByCode(String code) throws ObjectNotFoundException;

	/**
	 * Get one Contacts with the provided search parameters.
	 * 
	 * @param searchParameter
	 *            The search parameter
	 * @return The pageable Contacts
	 */
	Page<CTAContact> getObjects(SearchParameter searchParameter);
}
