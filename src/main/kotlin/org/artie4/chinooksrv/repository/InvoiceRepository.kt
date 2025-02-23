package org.artie4.chinooksrv.repository

import org.artie4.chinooksrv.entity.Invoice
import org.springframework.data.jpa.repository.JpaRepository

interface InvoiceRepository : JpaRepository<Invoice, Long>
