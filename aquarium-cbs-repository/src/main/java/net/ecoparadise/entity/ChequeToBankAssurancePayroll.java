/*
 * Copyleft 2007-2011 Ozgur Yazilim A.S.
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * www.tekir.com.tr
 * www.ozguryazilim.com.tr
 *
 */

package net.ecoparadise.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

/**
 * Çek banka teminata gönderme bordrosu
 * 
 * @author uygun
 */
@Entity
@Table(name="TRD_CHEQUE_TO_BANK_ASS_PAYROLL")
public class ChequeToBankAssurancePayroll extends DocumentBase implements Serializable {
	    
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator="genericSeq")
    @Column(name="ID")
	private Long id;
	
    @ManyToOne
    @JoinColumn(name="BANK_ID")
    private Bank bank;
    
    @ManyToOne
    @JoinColumn(name="BANK_BRANCH_ID")
    private BankBranch bankBranch;
    
    @ManyToOne
    @JoinColumn(name="BANK_ACCOUNT_ID")
    private BankAccount bankAccount;

    @OneToMany(mappedBy="owner", cascade=CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<ChequeToBankAssurancePayrollDetail> details = new ArrayList<ChequeToBankAssurancePayrollDetail>();
    
	@Override
	public DocumentType getDocumentType() {
		return DocumentType.ChequeToBankAssurancePayroll;
	}

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public List<ChequeToBankAssurancePayrollDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ChequeToBankAssurancePayrollDetail> details) {
		this.details = details;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public BankBranch getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(BankBranch bankBranch) {
		this.bankBranch = bankBranch;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Transient
    public DocumentTotal getTotals(){
        DocumentTotal tot = new DocumentTotal();

        for( ChequeToBankAssurancePayrollDetail item : getDetails() ){
            tot.add( new MoneySet(item.getCheque().getMoney()));
        }

        return tot;
    }

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChequeToBankAssurancePayroll)) {
            return false;
        }
        ChequeToBankAssurancePayroll other = (ChequeToBankAssurancePayroll)object;
        if (this.getId() != other.getId() && (this.getId() == null || !this.getId().equals(other.getId()))) return false;
        return true;
    }

    @Override
    public String toString() {
        return "ChequeToBankAssurancePayroll[id=" + getId() + "]";
    }
    
}
