package org.artie4.chinooksrv.service

import org.artie4.chinooksrv.dto.PurchaseDto

interface PurchaseService {
    fun purchase(purchaseDto: PurchaseDto)
}
