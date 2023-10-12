package ru.milovanov.SpringRestProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.milovanov.SpringRestProject.models.Measurement;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer>{
    List<Measurement> getMeasurementByRainingEquals(boolean Rainy);
}
