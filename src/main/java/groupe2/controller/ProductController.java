package groupe2.controller;


import groupe2.entity.Product;
import groupe2.entity.Type;
import groupe2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import groupe2.service.TypeService;


import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private TypeService typeService;

    @GetMapping
    public String  getList(Model model){
        List<Product> list =  productService.findAll();
        model.addAttribute("products",list);
        return "product";
    }

    @GetMapping("/new")
    public String form(Model model){
        model.addAttribute("types", typeService.findAll());  // ✅ Charger les types
        return "form-product";
    }

    @PostMapping
    public String save(@ModelAttribute Product product, @RequestParam("typeId") Long typeId){
        // Associer le type au produit
        if (typeId != null && typeId > 0) {
            Type type = typeService.findById(typeId);
            product.setTypeProduct(type);
        }

        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        productService.delete(id);
        return "redirect:/product";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Product product = productService.findById(id);
        model.addAttribute("product",product);
        model.addAttribute("types", typeService.findAll());
        return "form-product";
    }


}
