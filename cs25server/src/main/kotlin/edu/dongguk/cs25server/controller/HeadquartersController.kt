package edu.dongguk.cs25server.controller

import edu.dongguk.cs25server.annotation.UserId
import edu.dongguk.cs25server.dto.request.ItemHQRequestDto
import edu.dongguk.cs25server.dto.request.ItemHQUpdateDto
import edu.dongguk.cs25server.service.ItemHQService
import org.springframework.web.bind.annotation.DeleteMapping
import edu.dongguk.cs25server.dto.response.*
import edu.dongguk.cs25server.service.ManagerService
import edu.dongguk.cs25server.service.StoreService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/headquarters")
class HeadquartersController(
    private val itemHQService: ItemHQService,
    private val managerService: ManagerService,
    private val storeService: StoreService
) {

    // 보유 재고 목록 조회
    @GetMapping("/stock-management/stocks")
    fun readStocks(@RequestParam(name = "category", required = false) category: String?,
                   @RequestParam(name = "item_name", required = false, defaultValue = "") itemName: String):
            RestResponse<ListResponseDto<List<StockResponseDto>>> {
        return RestResponse(data = itemHQService.readStocks(category, itemName))
    }

    // 상품 추가
    @PostMapping("/stock-management/stocks")
    fun createItem(@RequestPart data: ItemHQRequestDto,
                   @RequestPart imageFile: MultipartFile): RestResponse<Boolean> {
        return RestResponse(itemHQService.createItem(data, imageFile))
    }

    // 보유 재고 상세 조회
    @GetMapping("/stock-management/stocks/{stockId}")
    fun readStockDetail(@PathVariable("stockId") stockId: Long): RestResponse<ItemDetailResponseDto> {
        return RestResponse(itemHQService.readItemDetail(stockId))
    }

    // 상품 수정
    @PatchMapping("/stock-management/stocks/{stockId}")
    fun updateItem(@PathVariable("stockId") stockId: Long, @RequestPart data: ItemHQUpdateDto,
                   @RequestPart imageFile: MultipartFile): RestResponse<Boolean> {
        return RestResponse(itemHQService.updateItem(stockId, data, imageFile))
    }

    // 상품 삭제
    @DeleteMapping("/stock-management/stocks/{stockId}")
    fun deleteItem(@PathVariable("stockId") stockId: Long): RestResponse<Boolean> {
        return RestResponse(itemHQService.deleteItem(stockId))
    }

    @GetMapping("/managers")
    fun getRequestManagerList(
        @RequestParam("index") index: Long,
        @RequestParam("size", defaultValue = "10") size: Long
    ): RestResponse<ListResponseDto<List<RequestManagerListDto>>> {
        return RestResponse(managerService.getRequestManagerList(index, size))
    }

    @GetMapping("/stores")
    fun getRequestStoreList(
        @RequestParam("index") index: Long,
        @RequestParam("size", defaultValue = "10") size: Long
    ): RestResponse<ListResponseDto<List<RequestStoreListDto>>> {
        return RestResponse(storeService.getRequestStoreList(index, size))
    }
}