package com.schoolevents.schoolevents_api.res;

import com.schoolevents.schoolevents_api.DTO.SignDTO;
import com.schoolevents.schoolevents_api.Services.SignService;
import com.schoolevents.schoolevents_api.models.Sign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signs")
class SignController {

    @Autowired
    private SignService signService;

    @GetMapping("/all")
    public List<SignDTO> getSigns(){
        return signService.findAll();
    }

    @GetMapping("/by_id/{id}")
    public SignDTO getSignById(@PathVariable Long id){
        return signService.findId(id);
    }
    @GetMapping("/by_event/{id}")
    public List<SignDTO> getSignsByEvent(@PathVariable Long id){
        return signService.findByEventId(id);
    }

    @GetMapping("/by_user/{id}")
    public List<SignDTO> getSignsByUser(@PathVariable Long id){
        return signService.findByUserId(id);
    }

    @PostMapping("/create")
    public SignDTO save(@RequestBody Sign sign){
        return signService.save(sign);
    }

    @PutMapping("/update/{id}")
    public SignDTO update(@PathVariable Long id, @RequestBody Sign sign){
        return signService.update(sign, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        signService.delete(id);
    }

}
