package mindera.porto.AppMovie.controller;

import mindera.porto.AppMovie.dto.directorDto.DirectorCreateDto;
import mindera.porto.AppMovie.dto.directorDto.DirectorReadDto;
import mindera.porto.AppMovie.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/directors")
public class DirectorController {

    @Autowired //Injeçao da dependencia automática, sem precisar de construtor
    private DirectorService directorService;

    // GET /directors/list → Listar todos os diretores
    @GetMapping("/list")
    public List<DirectorReadDto> getAllDirectors() {
        return directorService.getAllDirectors();
    }

    // GET /directors/{id} → Obter detalhes de um diretor específico
    @GetMapping("/{id}")
    public DirectorReadDto getDirector(@PathVariable Long id) {
        return directorService.getDirector(id);
    }

    // POST /directors/add → Criar um novo diretor
    @PostMapping("/")
    public DirectorReadDto addDirector(@RequestBody DirectorCreateDto directorCreateDto) {
        return directorService.addDirector(directorCreateDto);
    }

    // PUT /directors/{id} → Atualizar informações de um diretor
    @PutMapping("/{id}")
    public DirectorReadDto updateDirector(@PathVariable Long id, @RequestBody DirectorCreateDto directorCreateDto) {
        return directorService.updateDirector(id, directorCreateDto);
    }


    // DELETE /directors/{id} → Remover um diretor
    @DeleteMapping("/{id}")
    public void deleteDirector(@PathVariable Long id) {
        directorService.deleteDirector(id);
    }
}