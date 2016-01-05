package by.epam.grodno.uladzimir_stsiatsko.my_service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.epam.grodno.uladzimir_stsiatsko.my_dao.dao.TrainDao;
import by.epam.grodno.uladzimir_stsiatsko.my_dao.model.Train;
import by.epam.grodno.uladzimir_stsiatsko.my_service.TrainService;
import by.epam.grodno.uladzimir_stsiatsko.my_service.TripListService;

@Service
public class TrainServiceImpl implements TrainService {

	@Autowired
	TripListService tlService;
	
	@Autowired
	TrainDao tDao;

	public void addTrain(Train train) {
		tDao.addTrain(train);
	}
	
	public void update(Train train) {
		tDao.update(train);
	}

	public boolean containsTrain(String trainNumber) {
		return tDao.containsTrain(trainNumber);
	}
	
	public void deleteTrain(int id) throws IllegalArgumentException {
		if(tlService.containsTrain(id)){
			throw new IllegalArgumentException("This train can't be deleted because of structural integrity needs");
		} else {
			tDao.deleteTrain(id);
		}
	}

	public List<Train> getAll(long first, long count, String sortBy, String sortType) {
		if (sortType.equals("ASCENDING")) {
			return tDao.getAll(first, count, sortBy, "asc");
		} else {
			return tDao.getAll(first, count, sortBy, "desc");
		}
	}

	public int getCount() {
		return tDao.getCount();
	}

	public List<Train> findAll() {
		return tDao.getAll();
	}

}
