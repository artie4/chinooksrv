package org.artie4.chinooksrv.service.impl

import jakarta.transaction.Transactional
import org.artie4.chinooksrv.dto.PurchaseDto
import org.artie4.chinooksrv.entity.Invoice
import org.artie4.chinooksrv.entity.Invoiceline
import org.artie4.chinooksrv.repository.InvoiceRepository
import org.artie4.chinooksrv.repository.TrackRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class PurchaseService(
    private val userService: UserService,
    private val trackRepository: TrackRepository,
    private val invoiceRepository: InvoiceRepository,
) {
    @Transactional
    fun purchase(purchaseDto: PurchaseDto) {
        val userDetails = SecurityContextHolder.getContext().authentication.principal as User
        val user =
            userService.findUserByUsername(userDetails.username)
                ?: throw IllegalStateException("User not found")

        val tracks = trackRepository.findAllById(purchaseDto.trackIds)

        // check for missing tracks
        val missingTracks = purchaseDto.trackIds - tracks.map { it.id }.toSet()
        if (missingTracks.isNotEmpty()) {
            throw IllegalArgumentException("Tracks: $missingTracks not found")
        }

        val invoiceLines =
            tracks.map { track ->
                Invoiceline().apply {
                    trackid = track
                    unitprice = track.unitprice
                    quantity = 1
                }
            }.toMutableSet()

        val newInvoice =
            Invoice().apply {
                invoicedate = OffsetDateTime.now()
                customerid = user.customer
                this.invoicelines.addAll(invoiceLines)
                total = invoiceLines.mapNotNull { it.unitprice }.sumOf { it }
            }

        invoiceRepository.save(newInvoice)
    }
}
