package groupe2.controller;

import groupe2.entity.Type;
import groupe2.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping
    public String getList(Model model) {
        List<Type> list = typeService.findAll();

        model.addAttribute("types", list);  // ← CORRECTION ICI

        // Afficher dans la console pour debug
        System.out.println("✅ Types trouvés : " + list.size());
        for (Type t : list) {
            System.out.println("   - ID: " + t.getId() + ", Libelle: " + t.getLibelle());
        }

        return "type";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("type", new Type());
        return "AjoutType";
    }

    @PostMapping
    public String save(@ModelAttribute Type type) {
        typeService.save(type);
        return "redirect:/type";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        typeService.delete(id);
        return "redirect:/type";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Type type = typeService.findById(id);
        model.addAttribute("type", type);  // ✅ Utiliser "type" pour l'édition
        return "form-type";
    }
}