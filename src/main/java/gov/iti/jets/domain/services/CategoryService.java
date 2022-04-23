package gov.iti.jets.domain.services;

import gov.iti.jets.domain.models.Category;
import gov.iti.jets.persistence.JpaUtil;
import gov.iti.jets.persistence.repositories.CategoryRepository;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class CategoryService {

    public static Category getCategoryById( int categoryId){

        EntityManager em = JpaUtil.createEntityManager();
        CategoryRepository cr = new CategoryRepository( em );

        Optional<Category> optionalRetrievedCategory = cr.findOne( categoryId );
        if( optionalRetrievedCategory.isPresent() ){
            return optionalRetrievedCategory.get();
        }else{
            throw new IllegalArgumentException();
        }

    }

}
