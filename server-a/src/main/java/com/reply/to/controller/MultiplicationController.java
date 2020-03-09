package com.reply.to.controller;


import com.reply.to.model.NumberToMultiply;
import com.reply.to.service.Multiplication;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MultiplicationController {

    @Autowired
    private Multiplication multiplication;

    /**
     * Required flag is true by default, both noOne and noTwo are required:
     * @param noOne
     * @param noTwo
     * @return
     */
    @ApiOperation(value = "Get total as a result of multiplication of two numbers", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully calculated and returned total"),
            @ApiResponse(code = 404, message = "The resource is not found")
    })
    @GetMapping(value="/multiplication")
    public ResponseEntity<?> getTotal(
            @ApiParam(name = "noOne", value = "first number",required = true)
            @RequestParam(value="noOne") int noOne,
            @ApiParam(name = "noTwo", value = "second number",required = true)
            @RequestParam(value="noTwo") int noTwo) {

        NumberToMultiply numberToMultiply = new NumberToMultiply(noOne,noTwo);
        log.info("Multiplication for number {} and {} in progress", noOne, noTwo);
        int total  =  multiplication.calculate(numberToMultiply.hashCode(), numberToMultiply);
        return ResponseEntity.ok().body("Total is: " + total);
    }

}
