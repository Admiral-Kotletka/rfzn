package com.example.demo.service;

import com.example.demo.entity.Socks;
import com.example.demo.repository.SocksRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class SocksService {

    @Autowired
    SocksRepository socksRepository;

    public void addSocks(Socks socks){
        socksRepository.save(socks);
    }

    public Boolean isAlreadyExist(Socks socks) {
        return socksRepository.existsByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
    }

    public void updateQuantityIncome(Socks socks) throws NotFoundException {
        Socks existingSocks = retrieveSocksFromDB(socks);
        existingSocks.setQuantity(existingSocks.getQuantity() + socks.getQuantity());
        addSocks(existingSocks);
    }

    public void updateQuantityOutcome(Socks socks) throws NotFoundException {
        Socks existingSocks = retrieveSocksFromDB(socks);
        existingSocks.setQuantity(existingSocks.getQuantity() - socks.getQuantity());
        addSocks(existingSocks);
    }

    public Boolean isEnoughQuantity(Socks socks) throws NotFoundException {
        Socks existingSocks = retrieveSocksFromDB(socks);
        return existingSocks.getQuantity() >= socks.getQuantity();
    }

    public Socks retrieveSocksFromDB(Socks socks) throws NotFoundException {
        if (isAlreadyExist(socks)) {
            List<Socks> socksList = new ArrayList<>(socksRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart()));
            return socksList.get(0);
        } else {
            throw new NotFoundException("no such Entity in database");
        }
    }

    public void countSocks(){}

}
