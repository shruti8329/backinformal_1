package com.backinformal.BackInFormal_Backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backinformal.BackInFormal_Backend.DTO.InvoiceListPageDetailDTO;
import com.backinformal.BackInFormal_Backend.entity.MainInvoice;

@Repository
public interface MainInvoiceRepository extends JpaRepository<MainInvoice, Long>{

	@Query("SELECT new com.backinformal.BackInFormal_Backend.DTO.InvoiceListPageDetailDTO(i.uniqueInvoiceNumber,i.customer.custName,i.invoiceListId.createdOn, i.netTotal,i.amtReceived,i.amtUnpaid) FROM MainInvoice i")
	List<InvoiceListPageDetailDTO> findAllInvoiceNumAndCustName();

	MainInvoice findByUniqueInvoiceNumber(String invoiceNum);

}
