package org.artie4.chinooksrv.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "mediatype")
class Mediatype {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mediatypeid", nullable = false)
    var id: Long? = null

    @Column(name = "name", length = Integer.MAX_VALUE)
    var name: String? = null

    @OneToMany(mappedBy = "mediatypeid")
    var tracks: MutableSet<Track> = mutableSetOf()
}
