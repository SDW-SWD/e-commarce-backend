package edu.clothify.controller;

import edu.clothify.dto.StockDto;
import edu.clothify.entity.Stock;
import edu.clothify.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/stock")
public class StockController {
    @Autowired
    StockService stockService;

    @PostMapping("/add")
    public boolean addStock(@RequestBody StockDto stock) {
        return stockService.addStock(stock);
    }

    @GetMapping("/get")
    public List<Stock> listStock(@RequestParam String size, @RequestParam Long id) {
        List<Stock> stockDTOList = stockService.getStockAccordingToSizeAndProduct(size,id);
        if (stockDTOList.isEmpty()){
            List list=new ArrayList();
            list.add("not valid");
            return list;
        }
        return stockDTOList;
    }
    @PutMapping ("/update/{id}")
    public StockDto updateStock(@PathVariable Long id, @RequestBody StockDto stockDto) {
        return stockService.updateStock(id, stockDto);
    }
    @DeleteMapping("/remove/{id}")
    public Boolean  deleteStock(@PathVariable Long id){
        return stockService.deleteStock(id);
    }

    @GetMapping("get/stock/{id}")
    public StockDto getStockById(@PathVariable long id){
        return stockService.getStockById(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> error(MethodArgumentNotValidException exception){
        Map <String, String> map=new HashMap<>();
        List<ObjectError> list=exception.getBindingResult().getAllErrors();
        for(ObjectError item:list) {
            FieldError fieldError=(FieldError) item;
            String fieldName= fieldError.getField();
            String message = item.getDefaultMessage();
            map.put(fieldName,message);
        }
        return map;
    }
}