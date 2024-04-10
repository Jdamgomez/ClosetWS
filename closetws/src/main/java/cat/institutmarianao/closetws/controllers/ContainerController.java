package cat.institutmarianao.closetws.controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

/* Swagger */
@Tag(name = "Container", description = "ContainerController API")
/**/
@RestController
@RequestMapping("/containers")
@Validated
public class ContainerController {

}
