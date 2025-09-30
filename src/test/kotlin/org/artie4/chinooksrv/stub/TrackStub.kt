package org.artie4.chinooksrv.stub

import org.artie4.chinooksrv.dto.TrackDto
import org.artie4.chinooksrv.entity.Album
import org.artie4.chinooksrv.entity.Genre
import org.artie4.chinooksrv.entity.Mediatype
import org.artie4.chinooksrv.entity.Track
import java.math.BigDecimal

object TrackStub {
    fun getTrackEntity(
        id: Long? = 456,
        name: String? = null,
        albumid: Album? = null,
        mediatypeid: Mediatype? = null,
        genreid: Genre? = null,
        composer: String? = null,
        milliseconds: Long? = 60_000,
        bytes: Long? = 120_000,
        unitprice: BigDecimal? = BigDecimal(1.0),
    ): Track =
        Track().apply {
            this.id = id
            this.name = name
            this.albumid = albumid
            this.mediatypeid = mediatypeid
            this.genreid = genreid
            this.composer = composer
            this.milliseconds = milliseconds
            this.bytes = bytes
            this.unitprice = unitprice
        }

    fun getTrackDto(
        id: Long = 456,
        name: String = "track",
        composer: String? = "Default composer",
        milliseconds: Long = 120_000,
        bytes: Long = 150_000,
        unitprice: BigDecimal = BigDecimal(1.0),
    ): TrackDto =
        TrackDto(
            id = id,
            name = name,
            composer = composer,
            milliseconds = milliseconds,
            bytes = bytes,
            unitprice = unitprice,
        )
}
