package com.schoolevents.schoolevents_api.Services;

import com.schoolevents.schoolevents_api.DTO.SignDTO;
import com.schoolevents.schoolevents_api.mappers.SignMapper;
import com.schoolevents.schoolevents_api.models.Sign;
import com.schoolevents.schoolevents_api.repositories.SignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignService {

    private final SignRepository signRepository;
    private final SignMapper signMapper;

    @Autowired
    public SignService(SignRepository signRepository, SignMapper signMapper) {
        this.signRepository = signRepository;
        this.signMapper = signMapper;
    }

    public List<SignDTO> findAll(){
        List<Sign> signs = signRepository.findAll();
        List<SignDTO> signsDTO = new java.util.ArrayList<>(List.of());
        for (Sign sign : signs) {
            signsDTO.add(signMapper.signToSignDTO(sign));
        }
        return signsDTO;
    }

    public SignDTO findId(Long id){
        Sign sign = signRepository.findById(id);
        return signMapper.signToSignDTO(sign);
    }

    public List<SignDTO> findByEventId(Long event_id){
        List<Sign> signs = signRepository.findByEventId(event_id);
        List<SignDTO> signsDTO = new java.util.ArrayList<>(List.of());
        for (Sign sign : signs) {
            signsDTO.add(signMapper.signToSignDTO(sign));
        }
        return signsDTO;
    }

    public List<SignDTO> findByUserId(Long user_id){
        List<Sign> signs = signRepository.findByUserId(user_id);
        List<SignDTO> signsDTO = new java.util.ArrayList<>(List.of());
        for (Sign sign : signs) {
            signsDTO.add(signMapper.signToSignDTO(sign));
        }
        return signsDTO;
    }

    public SignDTO save(Sign sign){
        Sign s = signRepository.save(sign);
        return signMapper.signToSignDTO(s);
    }

    public SignDTO update(Sign sign, Long id){
        Sign s = signRepository.findById(id);
        s.setEvent(s.getEvent());
        s.setUser(sign.getUser());
        s.setDate(sign.getDate());
        Sign s0 = signRepository.save(s);
        return signMapper.signToSignDTO(s0);
    }

    public void delete(Long id){
        signRepository.deleteById(id);
    }
}
