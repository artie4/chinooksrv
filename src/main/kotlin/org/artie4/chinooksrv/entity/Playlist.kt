package org.artie4.chinooksrv.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "playlist")
class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "playlistid", nullable = false)
    var id: Long? = null

    @Column(name = "name", length = Integer.MAX_VALUE)
    var name: String? = null

    @ManyToMany
    @JoinTable(
        name = "playlisttrack",
        joinColumns = [JoinColumn(name = "playlistid")],
        inverseJoinColumns = [JoinColumn(name = "trackid")]
    )
    var tracks: MutableSet<Track> = mutableSetOf()
}