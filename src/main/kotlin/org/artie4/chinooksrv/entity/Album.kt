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
@Table(name = "album")
class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "albumid", nullable = false)
    var id: Long? = null

    @Column(name = "title", length = Integer.MAX_VALUE)
    var title: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artistid")
    var artist: Artist? = null

    @OneToMany(mappedBy = "albumid")
    var tracks: MutableSet<Track> = mutableSetOf()
}
