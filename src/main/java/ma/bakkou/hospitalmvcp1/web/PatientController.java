package ma.bakkou.hospitalmvcp1.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.bakkou.hospitalmvcp1.security.entities.Patient;
import ma.bakkou.hospitalmvcp1.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class PatientController {



    private PatientRepository patientRepository;

    @GetMapping("/user/index")

    public String index(Model model , @RequestParam(name = "page" , defaultValue = "0")int p ,
                                    @RequestParam(name = "size" ,defaultValue = "4") int s ,
                        @RequestParam(name = "keyword" ,defaultValue = "") String keyword){
        Page<Patient> pagePatients=patientRepository.findByNomContains(keyword,PageRequest.of(p,s));

        model.addAttribute("listPatients",pagePatients.getContent());
        model.addAttribute("pages",new int [pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",p);
        model.addAttribute("keyword",keyword);
        return "patients";
    }

@GetMapping("/admin/delete")
@PreAuthorize("hasRole('ADMIN')")
    public String delete(Long id , String keyword , int page){
patientRepository.deleteById(id);
return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }


    @GetMapping("/user/")
    public String home(){
        return "index";
    }
    @GetMapping("/admin/formPatients")
    @PreAuthorize("hasRole('ADMIN')")

    public String formPatient(Model model) {
        model.addAttribute("patient", new Patient()); // Ajoute un patient vide pour le binding du formulaire
        return "formPatients";
    }
    @PostMapping("/admin/save")
    @PreAuthorize("hasRole('ADMIN')")

    public String save( Model model , @Valid Patient patient , BindingResult bindingResult, @RequestParam(defaultValue = "") String keyword , @RequestParam(defaultValue = "0") int page){
        if (bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
//test
    @GetMapping("/admin/editPatient")
    @PreAuthorize("hasRole('ADMIN')")

    public String editPatient(Model model, Long id , String keyword , int page) {
        Patient patient=patientRepository.findById(id).get();
        if (patient==null) throw  new RuntimeException("patient introuvable");
        model.addAttribute("patient", patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editPatient";
    }




}
