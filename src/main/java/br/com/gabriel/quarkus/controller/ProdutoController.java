package br.com.gabriel.quarkus.controller;

import br.com.gabriel.quarkus.model.Produto;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/produtos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoController {

    @GET
    public List<Produto> buscar() {
        return Produto.listAll();
    }

    @POST
    @Transactional
    public void criar(Produto produto) {
        produto.persist();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void atualizar(@PathParam("id") Long id, Produto dto) {
        Produto produto = (Produto) Produto.findByIdOptional(id).orElseThrow(NotFoundException::new);
        produto.nome = dto.nome;
        produto.valor = dto.valor;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id) {
        Produto.findByIdOptional(id).orElseThrow(NotFoundException::new).delete();
    }
}
