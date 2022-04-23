package gov.iti.jets.restapi.adapters;

import java.util.List;
import java.util.stream.Collectors;

import gov.iti.jets.restapi.dtos.AdaptedLink;
import jakarta.json.bind.adapter.JsonbAdapter;
import jakarta.ws.rs.core.Link;

public class LinkJsonbAdapter implements JsonbAdapter<List<Link>, List<AdaptedLink>>{

    @Override
    public List<AdaptedLink> adaptToJson(List<Link> obj) throws Exception {
        
        
        return obj.stream()
                    .map(link -> new AdaptedLink(link.getUri(), link.getRel()))
                        .collect(Collectors.toList());
    }

    @Override
    public List<Link> adaptFromJson(List<AdaptedLink> obj) throws Exception {
        return obj.stream()
                    .map(adaptedLink -> (Link)Link.fromUri(adaptedLink.getUri()).rel(adaptedLink.getRel()).build("localhost","8080"))
                        .collect(Collectors.toList());
    }
    
}
