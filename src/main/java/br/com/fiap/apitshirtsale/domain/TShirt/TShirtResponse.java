package br.com.fiap.apitshirtsale.domain.TShirt;

import java.util.List;

public record TShirtResponse(
        Long id,
        String model
) {
    public static TShirtResponse fromModel(TShirt TShirt) {
        return new TShirtResponse(
                TShirt.getId(),
                TShirt.getModel()
        );
    }
}
