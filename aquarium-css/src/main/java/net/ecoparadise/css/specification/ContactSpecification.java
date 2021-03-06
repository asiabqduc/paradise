/**
 * 
 */
package net.ecoparadise.css.specification;

import org.springframework.data.jpa.domain.Specification;

import lombok.Builder;
import net.ecoparadise.entity.contact.CTAContact;
import net.ecoparadise.framework.model.SearchParameter;
import net.ecoparadise.framework.model.SearchSpec;
import net.ecoparadise.framework.specification.CoreSpecifications;

/**
 * @author bqduc
 *
 */
@Builder
public class ContactSpecification extends CoreSpecifications<CTAContact, SearchSpec>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4351535957683794972L;

	public static Specification<CTAContact> buildSpecification(final SearchParameter searchParameter) {
		return ContactSpecification
				.builder()
				.build()
				.buildSpecifications(searchParameter);
	}
}
