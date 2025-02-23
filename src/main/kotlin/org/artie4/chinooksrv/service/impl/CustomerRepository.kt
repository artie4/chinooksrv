package org.artie4.chinooksrv.service.impl

import org.artie4.chinooksrv.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long>
