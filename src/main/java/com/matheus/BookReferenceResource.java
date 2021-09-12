package com.matheus;

import io.smallrye.mutiny.Uni;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/books")
public class BookReferenceResource {

  @Inject
  BookReferenceService service;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<List<String>> keys() {
    return service.keys();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public BookReference create(BookReference bookReference) {
    service.set(bookReference.getKey(), bookReference.getName());
    return bookReference;
  }

  @GET
  @Path("/{key}")
  @Produces(MediaType.APPLICATION_JSON)
  public BookReference get(@PathParam("key") String key) {
    return new BookReference(key, service.get(key));
  }

  @DELETE
  @Path("/{key}")
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<Void> delete(@PathParam("key") String key) {
    return service.del(key);
  }
}