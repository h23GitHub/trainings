package by.epam.grodno.uladzimir_stsiatsko.my_service;

import by.epam.grodno.uladzimir_stsiatsko.my_dao.model.Administrator;

public interface AdministratorService {

	void insertOrUpdate(Administrator admin);
	
	void registerAdmin(String login, String password, String firstName, String lastName);
	
}