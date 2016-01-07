package by.epam.grodno.uladzimir_stsiatsko.my_dao.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import by.epam.grodno.uladzimir_stsiatsko.my_dao.dao.RouteDao;
import by.epam.grodno.uladzimir_stsiatsko.my_dao.dao.mapper.RouteMapper;
import by.epam.grodno.uladzimir_stsiatsko.my_dao.model.Route;

@Repository
public class RouteDaoImpl implements RouteDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Route> getAll(){
		return jdbcTemplate.query("SELECT * FROM route;", new RouteMapper());
	}

	@Override
	public void detete(Route route) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Route> getAll(long first, long count, String sortBy, String sortType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
