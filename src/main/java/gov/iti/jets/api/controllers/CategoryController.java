package gov.iti.jets.api.controllers;

import gov.iti.jets.domain.dtos.category.CategoryRequestDto;
import gov.iti.jets.persistence.JpaUtil;
import gov.iti.jets.persistence.entities.Category;
import gov.iti.jets.persistence.enums.Status;
import gov.iti.jets.persistence.repositories.CategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;

import javax.swing.text.html.parser.Entity;

import org.hibernate.tool.schema.internal.ExceptionHandlerCollectingImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Path("categories")
public class CategoryController {

    @GET
    @Produces("application/xml")
    public Response getAllCategories() {

        EntityManager em = JpaUtil.createEntityManager();
        CategoryRepository cr = new CategoryRepository(em);

        List<Category> categoryList = cr.findAll();
        System.out.println(categoryList);
        GenericEntity<List<Category>> entity = new GenericEntity<>(categoryList) {
        };

        return Response.ok().entity(entity).build();
    }

    @POST
    public Response addNewCategory(CategoryRequestDto newCategory) {

        System.out.println(newCategory);
        EntityManager em = JpaUtil.createEntityManager();
        CategoryRepository cr = new CategoryRepository(em);

        EntityTransaction tx = em.getTransaction();
        try {
            Category category = new Category(newCategory.getName());
            tx.begin();
            em.persist(category);
            tx.commit();
            em.close();
            return Response.ok().entity(category).build();
        } catch (Exception e) {
            return Response.notModified().entity("error").build();
        }

    }

    @GET
    @Path("{cid}")
    @Produces("application/json")
    public Response getCategoryById(@PathParam("cid") int categoryId) {
        EntityManager em = JpaUtil.createEntityManager();
        CategoryRepository cr = new CategoryRepository(em);

        Optional<Category> optionalCategory = cr.findOne(categoryId);

        if (optionalCategory.isPresent()) {
            return Response.ok().entity(optionalCategory.get()).build();
        } else {
            return Response.noContent().build();
        }
    }

}
