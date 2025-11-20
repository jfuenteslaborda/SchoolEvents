package com.schoolevents.schoolevents_api.Services;


import com.schoolevents.schoolevents_api.models.Sign;
import com.schoolevents.schoolevents_api.repositories.SignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignService {

    @Autowired
    private SignRepository signRepository;

    public List<Sign> findAll(){
        return signRepository.findAll();
    }

    public Sign findId(Long id){
        return signRepository.findById(id);
    }

    public List<Sign> findByEventId(Long event_id){
        return signRepository.findByEventId(event_id);
    }

    public List<Sign> findByUserId(Long user_id){
        return signRepository.findByUserId(user_id);
    }

    public Sign save(Sign sign){
        return signRepository.save(sign);
    }

    public Sign update(Sign sign, Long id){
        Sign s = signRepository.findById(id);
        s.setEvent(s.getEvent());
        s.setUser(sign.getUser());
        s.setDate(sign.getDate());
        return signRepository.save(s);
    }

    public void delete(Long id){
        signRepository.deleteById(id);
    }
}
