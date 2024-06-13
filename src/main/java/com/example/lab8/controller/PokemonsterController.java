package com.example.lab8.controller;

import com.example.lab8.dao.PokemonDao;
import com.example.lab8.entity.EncounterMethodRates;
import com.example.lab8.entity.LugarPokemon;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PokemonsterController {

    final PokemonDao pokemonDao;

    public PokemonsterController(PokemonDao pokemonDao) {
        this.pokemonDao = pokemonDao;
    }

    @GetMapping("/capturarPokemon")
    public String formRegistro() {
        return "form";
    }

    @PostMapping("/capturarPokemon/lugarDelPokemon")
    public String lugarDelPokemon(@ModelAttribute("pokemon") String pokemon, Model model) {
        try {
            LugarPokemon lugar = pokemonDao.lugarDelPokemon(pokemon);
            model.addAttribute("pokegod", pokemon);
            model.addAttribute("lugarDelPokemon", lugar.getName());
            List<EncounterMethodRates> encounterMethodRates = pokemonDao.encuentroDelPokemon(lugar.getName());
            int ratoMayor = 0;
            String metodoEncuentroMayor = "";
            for (EncounterMethodRates e : encounterMethodRates) {
                int rate = e.getVersionDetails().getRate();
                if(rate >= ratoMayor) {
                    ratoMayor = rate;
                    metodoEncuentroMayor = e.getEncounterMethod().getName();
                }
            }
            model.addAttribute("metodo", metodoEncuentroMayor);
            model.addAttribute("rate", ratoMayor);
        }
        catch (Exception e) {
            System.out.println("ERROR" + e.getMessage());
            return "redirect:/capturarPokemon";
        }
        return "form";
    }
}
