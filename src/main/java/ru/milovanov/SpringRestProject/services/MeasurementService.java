package ru.milovanov.SpringRestProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.milovanov.SpringRestProject.models.Measurement;
import ru.milovanov.SpringRestProject.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    @Transactional
    public void save(Measurement measurement){
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    public List<Measurement> findAll(){
        return measurementRepository.findAll();
    }

    public int countRainyDays(){
        List<Measurement> list = new ArrayList<>(measurementRepository
                .getMeasurementByRainingEquals(Boolean.parseBoolean("true")));
        return list.size();
    }

    public void enrichMeasurement(Measurement measurement) {
        measurement.setSensor(sensorService.getSensorByName(measurement.getSensor().getName()).get());

        measurement.setMeasurementDateTime(LocalDateTime.now());
    }
}
