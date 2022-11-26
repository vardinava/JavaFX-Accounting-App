/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
//Nama : Vardina Nava M K
//NRP : 1872025

import java.util.List;

/**
 *
 * @author Vardina/1872025
 */
public interface daoInterface<E> {
    public int addData(E data);
    public int delData(E data);
    public int updateData(E data);
    
    public List<E> showData();
}
