package org.artie4.chinooksrv.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.artie4.chinooksrv.dto.PurchaseDto
import org.artie4.chinooksrv.service.impl.PurchaseService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1")
class PurchaseController(
    private val purchaseService: PurchaseService,
) {
    @Operation(
        summary = "Purchase tracks",
        security = [SecurityRequirement(name = "JWT")],
    )
    @PostMapping("purchase")
    fun purchase(
        @RequestBody purchaseDto: PurchaseDto,
    ) {
        purchaseService.purchase(purchaseDto)
    }
}
