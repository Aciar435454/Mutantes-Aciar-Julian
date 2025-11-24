package org.example.mutantes.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class DnaRequest {

    @NotEmpty(message = "La lista de ADN no puede estar vacía")
    @JsonDeserialize(contentUsing = StrictStringDeserializer.class)
    private List<
            @Pattern(regexp = "^[ATCG]+$", message = "La secuencia contiene caracteres inválidos")
                    String> dna;

    public DnaRequest() {}

    public DnaRequest(List<String> dna) {
        this.dna = dna;
    }

    public List<String> getDna() {
        return dna;
    }

    public void setDna(List<String> dna) {
        this.dna = dna;
    }
}





