package net.ecoparadise.service.impl;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.ecoparadise.css.repository.general.MeasureUnitRepository;
import net.ecoparadise.css.service.general.MeasureUnitService;
import net.ecoparadise.css.service.system.SequenceManager;
import net.ecoparadise.css.service.system.SequenceService;
import net.ecoparadise.css.specification.MeasureUnitSpecification;
import net.ecoparadise.entity.Unit;
import net.ecoparadise.exceptions.AppException;
import net.ecoparadise.exceptions.ObjectNotFoundException;
import net.ecoparadise.framework.model.SearchParameter;
import net.ecoparadise.framework.repository.BaseRepository;
import net.ecoparadise.framework.service.GenericServiceImpl;
import net.ecoparadise.global.GlobalConstants;

@Service
public class MeasureUnitServiceImpl extends GenericServiceImpl<Unit, Long> implements MeasureUnitService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4804189794303411453L;
	@Inject 
	private MeasureUnitRepository repository;

	@Inject
	private SequenceManager sequenceManager;

	protected BaseRepository<Unit, Long> getRepository() {
		return this.repository;
	}

	@Override
	public Optional<Unit> getByName(String name) throws ObjectNotFoundException {
		return repository.findByName(name);
	}

	@Override
	protected Page<Unit> performSearch(String keyword, Pageable pageable) {
		return repository.search(keyword, pageable);
	}

	@Override
	public Page<Unit> getObjects(SearchParameter searchParameter) {
		return this.repository.findAll(MeasureUnitSpecification.buildSpecification(searchParameter), searchParameter.getPageable());
	}

	@Override
	protected Optional<Unit> fetchBusinessObject(Object key) throws ObjectNotFoundException {
		return super.getBizObject("findByName", key);
	}

	@Override
	public Optional<Unit> getByNameLocale(String nameLocal) throws ObjectNotFoundException {
		return super.getBizObject("findByNameLocal", nameLocal);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Long count(String countByProperty, Object value) {
		if ("code".equalsIgnoreCase(countByProperty))
			return this.repository.countByCode((String)value);

		if ("name".equalsIgnoreCase(countByProperty))
			return this.repository.countByName((String)value);

		throw new RuntimeException(String.join("Count by property[", countByProperty, "] with value[", (String)value, "] Not implemented yet!"));
	}

	@Override
	public String nextSerial(String prefix) throws AppException {
		String newSerialNo = this.sequenceManager.getNewNumber(prefix, Integer.valueOf(GlobalConstants.SIZE_CODE));
		newSerialNo = prefix + newSerialNo.substring(prefix.length());
		return newSerialNo;
	}
}
