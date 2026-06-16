package com.backend.api.service;

import com.backend.api.model.Ping;
import com.backend.api.repository.PingRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PingService {
    private PingRepository pingRepository;

    public List<Ping> getPingHistoryById(int id){
        return null;
    }
}
