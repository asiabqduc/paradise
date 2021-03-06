package net.ecoparadise.service.trade;

import java.util.Optional;

import net.ecoparadise.entity.Pos;
import net.ecoparadise.exceptions.ObjectNotFoundException;
import net.ecoparadise.framework.service.GenericService;

public interface PosService extends GenericService<Pos, Long> {

	/**
	 * Get one Pos with the provided name.
	 * 
	 * @param name
	 *            The Pos name
	 * @return The Pos
	 * @throws ObjectNotFoundException
	 *             If no such Pos exists.
	 */
	Optional<Pos> getByName(String name) throws ObjectNotFoundException;

	/**
	 * Get one Pos with the provided code.
	 * 
	 * @param code
	 *            The Pos code
	 * @return The Pos
	 * @throws ObjectNotFoundException
	 *             If no such Pos exists.
	 */
	Optional<Pos> getByCode(String code) throws ObjectNotFoundException;
}
