package org.artie4.chinooksrv.entity

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

@Entity
@Table(name = "customer")
class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customerid", nullable = false)
    var id: Long? = null

    @Column(name = "firstname", length = Integer.MAX_VALUE)
    var firstname: String? = null

    @Column(name = "lastname", length = Integer.MAX_VALUE)
    var lastname: String? = null

    @Column(name = "company", length = Integer.MAX_VALUE)
    var company: String? = null

    @Column(name = "address", length = Integer.MAX_VALUE)
    var address: String? = null

    @Column(name = "city", length = Integer.MAX_VALUE)
    var city: String? = null

    @Column(name = "state", length = Integer.MAX_VALUE)
    var state: String? = null

    @Column(name = "country", length = Integer.MAX_VALUE)
    var country: String? = null

    @Column(name = "postalcode", length = Integer.MAX_VALUE)
    var postalcode: String? = null

    @Column(name = "phone", length = Integer.MAX_VALUE)
    var phone: String? = null

    @Column(name = "fax", length = Integer.MAX_VALUE)
    var fax: String? = null

    @Column(name = "email", length = Integer.MAX_VALUE)
    var email: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supportrepid")
    var supportrepid: Employee? = null

    @OneToMany(mappedBy = "customerid")
    var invoices: MutableSet<Invoice> = mutableSetOf()
}
