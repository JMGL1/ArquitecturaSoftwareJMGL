/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.com350.practica2.zoologico;

/**
 *
 * @author X13
 */

public class PezFactory implements AnimalFactory {

    @Override
    public Animal crearAnimal() {
        return new Pez();
    }
}
