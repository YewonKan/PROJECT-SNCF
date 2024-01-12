package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.CustomersNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Customers;

public interface CustomersDAO {
    Customers findMatchingCustomer(String email) throws CustomersNotFoundException;

    Customers createNewCustomer(String fname, String lname, String email, String phone);

}
