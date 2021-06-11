/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.hajofa.dao.interfaces;

import ca.hajofa.entites.Enseignants;
import java.util.List;


/**
 *
 * @author fatim
 */
public interface EnseignantDao {
    //operations crud
    public List<Enseignants> findAll();
    public Enseignants findByEmail(String email);
    public boolean createEnseignant(Enseignants prof);
    public boolean update(String courriel, String motpass);
}
