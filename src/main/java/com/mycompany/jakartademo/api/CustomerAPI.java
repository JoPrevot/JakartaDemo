package com.mycompany.jakartademo.api;

import com.mycompany.jakartademo.business.Customer;
import com.mycompany.jakartademo.business.Directory;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("customers")
public class CustomerAPI {
    
   
	
	@GET()
	@Produces({MediaType.APPLICATION_JSON})
	public List<Customer> getCustomers(@Context HttpServletRequest request)
	{
            Directory directory = (Directory) request.getSession().getAttribute("directory");
            if(directory == null)
            {
                directory = new Directory();
                request.getSession().setAttribute("directory",directory);
            }
            return directory.getAll();
	}
        
        @POST()
        @Consumes({MediaType.APPLICATION_JSON})
        public void postCustomer(Customer customer,@Context HttpServletRequest request)
        {
            Directory directory = (Directory) request.getSession().getAttribute("directory");
            if(directory == null)
            {
                directory = new Directory();
            }
            directory.add(customer);
            request.getSession().setAttribute("directory",directory);
        }
        
        @Path("/{id}")
        @GET()
        @Produces({MediaType.APPLICATION_JSON})
        public Response getById(@PathParam("id") Long customerId,@Context HttpServletRequest request)
        {
             Directory directory = (Directory) request.getSession().getAttribute("directory");
            if(directory == null)
            {
                directory = new Directory();
            }
            Optional<Customer> customerOptionnal = directory.findById(customerId);
            
            if(customerOptionnal.isPresent())
            {
                return Response.ok(customerOptionnal.get()).build();
            }
            else
            {
                return Response.status(Response.Status.NOT_FOUND).entity("Customer Not Found").build();
                // return Response.status(404).build();
            }
            
        }
        
        @Path("/{id}")
        @DELETE()
        public void delete(@PathParam("id")Long id,@Context HttpServletRequest request)
        {
             Directory directory = (Directory) request.getSession().getAttribute("directory");
            if(directory == null)
            {
                directory = new Directory();
            }
            directory.delete(id);
        }
        
       @Path("/{id}")
       @PUT()
        public void update(@Context HttpServletRequest request,Customer customerToUpdate)
        {
            Directory directory = (Directory) request.getSession().getAttribute("directory");
            if(directory == null)
            {
                directory = new Directory();
            }
            directory.update(customerToUpdate);
        }

}

