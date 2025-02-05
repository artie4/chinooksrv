package org.artie4.chinooksrv.dto

import java.math.BigDecimal

/**
 * DTO for {@link org.artie4.chinooksrv.entity.Track}
 */
data class TrackDto(
    val id: Long,
    val name: String,
    val composer: String?,
    val milliseconds: Long,
    val bytes: Long,
    val unitprice: BigDecimal,
)
