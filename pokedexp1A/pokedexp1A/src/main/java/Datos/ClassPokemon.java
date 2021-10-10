/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author USUARIOTC
 */
public class ClassPokemon {
    private String id;
    private String name;
    private String specie;
    private String habitat;
    private String color;
    private String base_experiencie;
    private String height;
    private String wight;
    private String pokemon_nodisponible;
    
    public ClassPokemon(){
        
    }


    public String getPokemon_nodisponible() {
        return pokemon_nodisponible;
    }

    public void setPokemon_nodisponible(String pokemon_nodisponible) {
        this.pokemon_nodisponible = pokemon_nodisponible;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWight() {
        return wight;
    }

    public void setWight(String wight) {
        this.wight = wight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBase_experiencie() {
        return base_experiencie;
    }

    public void setBase_experiencie(String base_experiencie) {
        this.base_experiencie = base_experiencie;
    }
    
    public ClassPokemon(String id, String nom, String height, String wight, String especie, String habitat,
            String color, String expe ){
        this.id=id;
        this.name=nom;
        this.height=height;
        this.wight=wight;
        this.color=color;
        this.habitat=habitat;
        this.specie=especie;
        this.base_experiencie=expe;
        
    }    
}
