package net.ecoparadise.service.impl;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ecoparadise.css.repository.general.CatalogueSubtypeRepository;
import net.ecoparadise.css.specification.CatalogueSubtypeSpecification;
import net.ecoparadise.domain.entity.general.CatalogueSubtype;
import net.ecoparadise.exceptions.AppException;
import net.ecoparadise.exceptions.ObjectNotFoundException;
import net.ecoparadise.framework.model.SearchParameter;
import net.ecoparadise.framework.repository.BaseRepository;
import net.ecoparadise.framework.service.GenericServiceImpl;
import net.ecoparadise.service.general.CatalogueSubtypeService;

@Service
public class CatalogueSubtypeServiceImpl extends GenericServiceImpl<CatalogueSubtype, Long> implements CatalogueSubtypeService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1435351574637430464L;
	@Inject 
	private CatalogueSubtypeRepository repository;
	
	protected BaseRepository<CatalogueSubtype, Long> getRepository() {
		return this.repository;
	}

	@Override
	public Optional<CatalogueSubtype> getByName(String name) throws ObjectNotFoundException {
		return repository.findByName(name);
	}

	@Override
	protected Page<CatalogueSubtype> performSearch(String keyword, Pageable pageable) {
		return repository.search(keyword, pageable);
	}

	@Override
	public Page<CatalogueSubtype> getObjects(SearchParameter searchParameter) {
		return this.repository.findAll(CatalogueSubtypeSpecification.buildSpecification(searchParameter), searchParameter.getPageable());
	}

	@Override
	protected Optional<CatalogueSubtype> fetchBusinessObject(Object key) throws AppException {
		return super.getBizObject("findByName", key);
	}
}
