package net.ecoparadise.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ecoparadise.css.repository.system.OptionRepository;
import net.ecoparadise.css.service.system.OptionService;
import net.ecoparadise.css.specification.OptionSpecification;
import net.ecoparadise.entity.Option;
import net.ecoparadise.exceptions.ObjectNotFoundException;
import net.ecoparadise.framework.model.SearchParameter;
import net.ecoparadise.framework.repository.BaseRepository;
import net.ecoparadise.framework.service.GenericServiceImpl;
import net.ecoparadise.options.OptionKey;

@Service
public class OptionServiceImpl extends GenericServiceImpl<Option, Long> implements OptionService{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5209863588217204283L;

	@Inject 
	private OptionRepository repository;

	protected BaseRepository<Option, Long> getRepository() {
		return this.repository;
	}

	@Override
	public Option getByUserKey(String user, String key) throws ObjectNotFoundException {
		return repository.findByUserAndKey(user, key);
	}

	@Override
	protected Page<Option> performSearch(String keyword, Pageable pageable) {
		return repository.search(keyword, pageable);
	}

	@Override
	public Page<Option> getObjects(SearchParameter searchParameter) {
		return this.repository.findAll(OptionSpecification.buildSpecification(searchParameter), searchParameter.getPageable());
	}

	@Override
	public Option getByOptionKey(OptionKey optionKey) throws ObjectNotFoundException {
		return getOption(optionKey.getValue());
	}

	@Override
	public Option getByKey(String key) throws ObjectNotFoundException {
		return getOption(key);
	}

	@Override
	public Option getOption(String key, String defaultValue) throws ObjectNotFoundException {
		return getOption(key, defaultValue, false);
	}

	@Override
	public Option getOption(String key, String defaultValue, boolean forUser) throws ObjectNotFoundException {
    Option o = getOption(key);
    if (o == null) {
        String user = "SYSTEM";
        if (forUser) {
            user = super.getLoggedInUsername();
        }
        o = createOption( key, defaultValue, user );
    }
    return o;
	}

	@Override
	public Option getOption(OptionKey optionKey, boolean create) throws ObjectNotFoundException {
		return create ? getOption(optionKey.getValue(), optionKey.getDefaultValue() ) : getByOptionKey(optionKey);
	}

  private Option getOption(String key) {
  	/*String loggedInUserSsoId = super.getLoggedInUsername();
  	String optionKey = null;

    if (loggedInUserSsoId != null) {
    	optionKey = String.join(loggedInUserSsoId, ".", key);
    } else {
    	optionKey = String.join(loggedInUserSsoId, "SYSTEM.", key);
    }*/ 

    List<Option> options = this.repository.findByKey(key);
    if (!options.isEmpty())
    	return options.get(0);

    return null;
  }	

  private Option createOption(String key, String val, String user) {
    Option o = new Option();
    o.setUser(user);
    o.setKey(key);
    o.setValue(val);
    this.repository.saveAndFlush(o);
    return o;
  }
}
