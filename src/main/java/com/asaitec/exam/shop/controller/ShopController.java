package com.asaitec.exam.shop.controller;

import com.asaitec.exam.shop.model.ProductBill;
import com.asaitec.exam.shop.service.FileService;
import com.asaitec.exam.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class ShopController {

    private FileService fileService;
    private ShopService shopService;

    @Autowired
    public ShopController(FileService fs, ShopService ss) {
        this.fileService = fs;
        this.shopService = ss;
    }

    @GetMapping(path = {"/"})
    public String mainPage() {
        return "index";
    }

    @PostMapping(path = {"/"})
    public ModelAndView getBill(@RequestParam(name="file1") MultipartFile fileProduct,
                                @RequestParam(name="file2") MultipartFile fileBought) {
        Map<String, Double> products;
        Map<String, Integer> order;
        List<ProductBill> billList;
        List<String> billListDiscounts;
        double total;
        double totalDiscount;
        ModelAndView modelAndView = new ModelAndView("bill");
        String message = "This is embarrassing...";
        try {
            if (fileService.checkFile(fileProduct) && fileService.checkFile(fileBought)) {
                products = shopService.getProduct(fileService.getLines(fileProduct));
                order = shopService.getOrder(fileService.getLines(fileBought));
                billList = shopService.getBillList(products, order);
                total = shopService.getTotal(billList);
                billListDiscounts = shopService.makeDiscounts(shopService.getOffers(), products, order, total);
                message = "Thanks for buying!";
                modelAndView.addObject("billList", billList);
                modelAndView.addObject("billListDiscounts", billListDiscounts);
            }
        } catch (Exception e) {
            message = e.getMessage();
        }
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}
