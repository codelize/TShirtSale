package br.com.fiap.apitshirtsale.domain.TShirt;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tshirts")
public class TShirtController {

    private final TShirtService TShirtService;

    public TShirtController(TShirtService TShirtService) {
        this.TShirtService = TShirtService;
    }

    @GetMapping
    public List<TShirtResponse> getAllTShirts() {
        return TShirtService.getAllTShirts().stream().map(TShirtResponse::fromModel).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public TShirtResponse getTShirtById(@PathVariable Long id) {
        return TShirtResponse.fromModel( TShirtService.getTShirtById(id) );
    }

}
