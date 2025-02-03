package mindera.porto.AppMovie.controller;

import mindera.porto.AppMovie.model.Director;
import mindera.porto.AppMovie.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/directors")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    // GET /directors/list → Listar todos os diretores
    @GetMapping("/list")
    public List<Director> getAllDirectors() {
        return directorService.getAllDirectors();
    }

    // GET /directors/{id} → Obter detalhes de um diretor específico
    @GetMapping("/{id}")
    public Director getDirector(@PathVariable Long id) {
        return directorService.getDirector(id);
    }

    // POST /directors/add → Criar um novo diretor
    @PostMapping("/add")
    public void addDirector(@RequestBody Director director) {
        directorService.saveOrUpdateDirector(director);
    }

    // PUT /directors/{id} → Atualizar informações de um diretor
    @PutMapping("/{id}")
    public void updateDirector(@PathVariable Long id, @RequestBody Director director) {
        director.setId(id);
        directorService.saveOrUpdateDirector(director);
    }

    // DELETE /directors/{id} → Remover um diretor
    @DeleteMapping("/{id}")
    public void deleteDirector(@PathVariable Long id) {
        directorService.deleteDirector(id);
    }

    // GET /directors/{id}/tvshows → Listar as séries de um diretor
    @GetMapping("/{id}/tvshows")
    public List<Director> getDirectorsByTvShow(@RequestParam String tvShow) {
        return directorService.getDirectorByTvShow(tvShow);
    }

    // GET /directors/{id}/movies → Listar os filmes de um diretor
    @GetMapping("/{id}/movies")
    public List<Director> getDirectorsByMovie(@RequestParam String movie) {
        return directorService.getDirectorByMovie(movie);
    }
}