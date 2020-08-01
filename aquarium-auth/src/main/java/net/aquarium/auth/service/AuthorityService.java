package net.aquarium.auth.service;

import org.springframework.data.domain.Page;

import net.aquarium.auth.entity.Authority;
import net.aquarium.exceptions.ObjectNotFoundException;
import net.aquarium.framework.model.SearchParameter;
import net.aquarium.framework.service.GenericService;

public interface AuthorityService extends GenericService<Authority, Long> {

	/**
	 * Get one Authority with the provided code.
	 * 
	 * @param name
	 *            The Authority name
	 * @return The Authority
	 * @throws ObjectNotFoundException
	 *             If no such Authority exists.
	 */
	Authority getByName(String name) throws ObjectNotFoundException;

	/**
	 * Get one Authoritys with the provided search parameters.
	 * 
	 * @param searchParameter
	 *            The search parameter
	 * @return The pageable Authorities
	 */
	Page<Authority> getObjects(SearchParameter searchParameter);

	
	/**
	 * Get one Authority with the provided code.
	 * 
	 * @param name
	 *            The Authority name
	 * @return The Authority
	 * @throws ObjectNotFoundException
	 *             If no such Authority exists.
	 */
	Authority getMinimumUserAuthority() throws ObjectNotFoundException;
}