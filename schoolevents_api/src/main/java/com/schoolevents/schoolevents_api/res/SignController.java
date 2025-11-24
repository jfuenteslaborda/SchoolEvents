package com.schoolevents.schoolevents_api.res;

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
    public List<Sign> getSigns(){
        return signService.findAll();
    }

    @GetMapping("/by_id/{id}")
    public Sign getSignById(@PathVariable Long id){
        return signService.findId(id);
    }
    @GetMapping("/by_event/{id}")
    public List<Sign> getSignsByEvent(@PathVariable Long id){
        return signService.findByEventId(id);
    }

    @GetMapping("/by_user/{id}")
    public List<Sign> getSignsByUser(@PathVariable Long id){
        return signService.findByUserId(id);
    }

    @PostMapping("/create")
    public Sign save(@RequestBody Sign sign){
        return signService.save(sign);
    }

    @PutMapping("/update/{id}")
    public Sign update(@PathVariable Long id, @RequestBody Sign sign){
        return signService.update(sign, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        signService.delete(id);
    }

}
