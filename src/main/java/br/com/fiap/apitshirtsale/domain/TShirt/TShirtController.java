package br.com.fiap.apitshirtsale.domain.TShirt;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tshirts")
public class TShirtController {

    private final TShirtService TShirtService;

    public TShirtController(TShirtService TShirtService) {
        this.TShirtService = TShirtService;
    }

    @GetMapping
    public List<TShirtResponse> getAllTShirts() {
        return TShirtService.getAllTShirts().stream().map(TShirtResponse::fromModel).toList();
    }

    @GetMapping("{id}")
    public TShirtResponse getTShirtById(@PathVariable Long id) {
        return TShirtResponse.fromModel( TShirtService.getTShirtById(id) );
    }

}
