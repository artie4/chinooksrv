package org.artie4.chinooksrv.repository

import org.artie4.chinooksrv.entity.Track
import org.springframework.data.jpa.repository.JpaRepository

interface TrackRepository : JpaRepository<Track, Long>
