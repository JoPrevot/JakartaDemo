package com.mycompany.jakartademo.api;

import com.mycompany.jakartademo.business.Customer;
import com.mycompany.jakartademo.business.Directory;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;



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
            directory.add(customer);
            request.getSession().setAttribute("directory",directory);
        }

}

