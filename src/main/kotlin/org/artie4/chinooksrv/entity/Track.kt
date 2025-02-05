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
@Table(name = "track")
class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trackid", nullable = false)
    var id: Long? = null

    @Column(name = "name", length = Integer.MAX_VALUE)
    var name: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "albumid")
    var albumid: Album? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mediatypeid")
    var mediatypeid: Mediatype? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genreid")
    var genreid: Genre? = null

    @Column(name = "composer", length = Integer.MAX_VALUE)
    var composer: String? = null

    @Column(name = "milliseconds")
    var milliseconds: Long? = null

    @Column(name = "bytes")
    var bytes: Long? = null

    @Column(name = "unitprice", precision = 10, scale = 2)
    var unitprice: BigDecimal? = null
}