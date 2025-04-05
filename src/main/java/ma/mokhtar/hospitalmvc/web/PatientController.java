package ma.mokhtar.hospitalmvc.web;

import jakarta.validation.Valid;
import ma.mokhtar.hospitalmvc.entities.Patient;
import ma.mokhtar.hospitalmvc.repositories.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PatientController {
    // Inject the PatientRepository here
    @Autowired
    private PatientRepository patientRepository;

    // Constructor injection is preferred for better testability
    /*public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }*/

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name = "size",defaultValue = "5")int size,
                        @RequestParam(name = "keyword",defaultValue = "") String keyword) {
        Page<Patient> pagePatients = patientRepository.findByNomContainsIgnoreCase(keyword, PageRequest.of(page, size));
        model.addAttribute("listPatients", pagePatients.getContent());
        // For pagination
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients";   // This will resolve to src/main/resources/templates/patients/index.html
    }

    @GetMapping("/deletePatient")
    public String delete(Long id, String keyword, int page) {
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> listPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/formPatients")
    public String formPatients(Model model) {
        model.addAttribute("patient", new Patient());
        return "formPatients";   // This will resolve to src/main/resources/templates/patients/formPatients.html
    }

    @GetMapping("/editPatient")
    public String editPatient(Model model,Long id,String keyword,int page) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient == null) throw new RuntimeException("Patient not found");
        model.addAttribute("patient", patient);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "editPatient";   // This will resolve to src/main/resources/templates/patients/formPatients.html
    }

    @PostMapping(path = "/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam( defaultValue = "0")int page,
                       @RequestParam( defaultValue = "") String keyword) {
        if(bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }



}
