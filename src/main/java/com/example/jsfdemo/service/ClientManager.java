package com.example.jsfdemo.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.example.jsfdemo.domain.Client;

@ApplicationScoped
public class ClientManager {
	private List<Client> db = new ArrayList<Client>();

	public void addClient(Client client) {
		Client newClient = new Client();

		newClient.setFirstName(client.getFirstName());
		newClient.setSecondName(client.getSecondName());
		newClient.setDateOfBirth(client.getDateOfBirth());
		newClient.setAdress(client.getAdress());
		newClient.setZipCode(client.getZipCode());
		newClient.setCity(client.getCity());


		db.add(newClient);
	}

	// Removes the person with given PIN
	public void deleteClient(Client client) {
		Client clientToRemove = null;
		for (Client c : db) {
			if (client.getSecondName().equals(c.getSecondName())) {
				clientToRemove = c;
				break;
			}
		}
		if (clientToRemove != null)
			db.remove(clientToRemove);
	}

	public List<Client> getAllClients() {
		return db;
	}
}
