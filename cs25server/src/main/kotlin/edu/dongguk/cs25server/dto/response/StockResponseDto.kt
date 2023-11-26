package edu.dongguk.cs25server.dto.response

import java.time.LocalDate

class StockResponseDto(
    val itemId: Long?,
    val itemName: String,
    val supplyPrice: Long,
    val stockQuantity: Long,
    val warehousingDate: LocalDate?
)