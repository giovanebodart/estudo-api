package com.estudos.api.services.date;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class getIdade {

    public Integer idade(LocalDate dataDeNascimeto){
        LocalDate now = LocalDate.now();
        Period period = Period.between(dataDeNascimeto, now);
        return period.getYears();
    }
}
