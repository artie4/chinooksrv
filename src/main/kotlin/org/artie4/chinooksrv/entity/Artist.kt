package org.artie4.chinooksrv.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "artist")
class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "artistid", nullable = false)
    var id: Long? = null

    @Column(name = "name", length = Integer.MAX_VALUE)
    var name: String? = null

    @OneToMany(mappedBy = "artistid")
    var albums: MutableSet<Album> = mutableSetOf()
}