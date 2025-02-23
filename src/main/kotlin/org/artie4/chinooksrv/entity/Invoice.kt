package org.artie4.chinooksrv.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(name = "invoice")
class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "invoiceid", nullable = false)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerid")
    var customerid: Customer? = null

    @Column(name = "invoicedate")
    var invoicedate: OffsetDateTime? = null

    @Column(name = "billingaddress", length = Integer.MAX_VALUE)
    var billingaddress: String? = null

    @Column(name = "billingcity", length = Integer.MAX_VALUE)
    var billingcity: String? = null

    @Column(name = "billingstate", length = Integer.MAX_VALUE)
    var billingstate: String? = null

    @Column(name = "billingcountry", length = Integer.MAX_VALUE)
    var billingcountry: String? = null

    @Column(name = "billingpostalcode", length = Integer.MAX_VALUE)
    var billingpostalcode: String? = null

    @Column(name = "total", precision = 10, scale = 2)
    var total: BigDecimal? = null

    @OneToMany(mappedBy = "invoiceid", cascade = [CascadeType.ALL])
    var invoicelines: MutableSet<Invoiceline> = mutableSetOf()
}
