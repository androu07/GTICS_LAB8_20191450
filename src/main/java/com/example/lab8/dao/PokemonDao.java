package com.example.lab8.dao;

import com.example.lab8.entity.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PokemonDao {
    public LugarPokemon lugarDelPokemon(String variable){

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://pokeapi.co/api/v2/pokemon/" + variable + "/encounters";

        ResponseEntity<LugarPokemonDto> response = restTemplate.getForEntity(url, LugarPokemonDto.class);

        return response.getBody().getLugar();
    }

    public List<EncounterMethodRates> encuentroDelPokemon(String variable){

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://pokeapi.co/api/v2/location-area/" + variable + "/";

        ResponseEntity<EncounterMethodRates[]> response = restTemplate.getForEntity(url, EncounterMethodRates[].class);

        return Arrays.asList(response.getBody());
    }
}
