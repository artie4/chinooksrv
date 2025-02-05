package org.artie4.chinooksrv.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "invoiceline")
class Invoiceline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "invoicelineid", nullable = false)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoiceid")
    var invoiceid: Invoice? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trackid")
    var trackid: Track? = null

    @Column(name = "unitprice", precision = 10, scale = 2)
    var unitprice: BigDecimal? = null

    @Column(name = "quantity")
    var quantity: Long? = null
}
