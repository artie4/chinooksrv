package org.artie4.chinooksrv.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Entity
import org.hibernate.Hibernate
import java.io.Serializable
import java.util.Objects

@Embeddable
class PlaylisttrackId : Serializable {
    @Column(name = "playlistid", nullable = false)
    var playlistid: Long? = null

    @Column(name = "trackid", nullable = false)
    var trackid: Long? = null
    override fun hashCode(): Int = Objects.hash(playlistid, trackid)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

        other as PlaylisttrackId

        return playlistid == other.playlistid &&
                trackid == other.trackid
    }

    companion object {
        private const val serialVersionUID = -3341672895014415086L
    }
}