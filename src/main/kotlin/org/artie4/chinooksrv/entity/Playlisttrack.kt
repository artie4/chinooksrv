package org.artie4.chinooksrv.entity

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table

@Entity
@Table(name = "playlisttrack")
class Playlisttrack {
    @EmbeddedId
    var id: PlaylisttrackId? = null

    @MapsId("playlistid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "playlistid", nullable = false)
    var playlistid: Playlist? = null

    @MapsId("trackid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trackid", nullable = false)
    var trackid: Track? = null
}