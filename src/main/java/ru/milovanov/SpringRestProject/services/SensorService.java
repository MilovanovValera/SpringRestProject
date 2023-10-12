package ru.milovanov.SpringRestProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.milovanov.SpringRestProject.models.Sensor;
import ru.milovanov.SpringRestProject.repositories.SensorRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }

    public List<Sensor> findAll(){
        return sensorRepository.findAll();
    }

    public Sensor findOne(int id){
        Optional<Sensor> foundSensor = sensorRepository.findById(id);
        return foundSensor.orElse(null);
    }

    public Optional<Sensor> getSensorByName(String name){
        return sensorRepository.getSensorByName(name);
    }

}
