package org.artie4.chinooksrv.service.impl

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.artie4.chinooksrv.dto.PurchaseDto
import org.artie4.chinooksrv.entity.Customer
import org.artie4.chinooksrv.entity.Invoice
import org.artie4.chinooksrv.repository.InvoiceRepository
import org.artie4.chinooksrv.repository.TrackRepository
import org.artie4.chinooksrv.stub.TrackStub
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import java.time.LocalDate

class PurchaseServiceImplTest {
    private lateinit var userService: UserService
    private lateinit var trackRepository: TrackRepository
    private lateinit var invoiceRepository: InvoiceRepository

    private lateinit var underTest: PurchaseServiceImpl

    @BeforeEach
    fun setUp() {
        userService = mockk()
        trackRepository = mockk()
        invoiceRepository = mockk()
        underTest = PurchaseServiceImpl(userService, trackRepository, invoiceRepository)
    }

    @Test
    fun `should purchase successfully`() {
        val userName = "userLogin"
        val user =
            mockk<User> {
                every { username } returns userName
            }
        SecurityContextHolder.getContext().authentication =
            mockk<UsernamePasswordAuthenticationToken> {
                every { principal } returns user
            }

        val requiredTrackIds = setOf(1L, 2, 3)
        val purchaseDto = PurchaseDto(requiredTrackIds)

        val customerEntityId = 10L
        val customerEntity =
            mockk<Customer> {
                every { id } returns customerEntityId
            }
        val userEntity =
            mockk<org.artie4.chinooksrv.entity.User> {
                every { customer } returns customerEntity
            }
        val invoiceSlot = slot<Invoice>()
        every { userService.findUserByUsername(userName) } returns userEntity
        every { trackRepository.findAllById(purchaseDto.trackIds) } returns requiredTrackIds.map { TrackStub.getTrackEntity(id = it) }
        every { invoiceRepository.save(capture(invoiceSlot)) } returnsArgument 0

        underTest.purchase(purchaseDto)

        assertSoftly {
            with(invoiceSlot.captured) {
                customerid?.id shouldBe customerEntityId
                invoicedate?.toLocalDate() shouldBe LocalDate.now()
                invoicelines.map { it.trackid?.id } shouldBe requiredTrackIds
            }
        }
    }
}
