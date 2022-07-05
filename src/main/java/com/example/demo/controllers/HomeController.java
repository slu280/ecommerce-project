package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Cart;
import com.example.demo.models.LoginUser;
import com.example.demo.models.Order;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.services.CartService;
import com.example.demo.services.EmailSenderService;
import com.example.demo.services.OrderService;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;



@Controller
public class HomeController {
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private EmailSenderService emailService;
	
	
	@GetMapping("/")
	public String initial(Model m, @ModelAttribute("newCart") Cart newcart, HttpSession session) {
		
		//List<Product> products = productService.allProducts();
		//System.out.println("home: " + session.getId());
		
		//cartService.deleteAll();
		Random random = new Random();
        long randomInt = random.nextInt(999999999);
        String userSession = String.valueOf(randomInt);
		session.setAttribute("guest_user", userSession);
		
		List<Product> products = productService.findProductByBestSeller(true);
		
		
    	m.addAttribute("cartNum", 0);
    	m.addAttribute("products", products);
    	m.addAttribute("sessionId", session.getAttribute(userSession));
    	//System.out.println("Initial Page" + session.getAttribute(userSession));
		return "redirect:/home/"+userSession;
	}
	
	@GetMapping("/home/{sessionId}")
	public String home(Model m, @ModelAttribute("newCart") Cart newcart, HttpSession session, @PathVariable("sessionId") String sessionId) {
		
		
		List<Cart> carts = cartService.findCartsBySessionId(sessionId);
		
		List<Product> products = productService.findProductByBestSeller(true);
		//System.out.println("home: " + session.getAttribute("guest_user"));
    	int totalQTY = 0;
    	for(int i = 0; i < carts.size(); i++) {
    		totalQTY += carts.get(i).getQty();
    	}
    	m.addAttribute("cartNum", totalQTY);
    	m.addAttribute("products", products);
    	m.addAttribute("sessionId", sessionId);
    	//System.out.println(sessionId);
		return "home.jsp";
	}
	@GetMapping("/user")
    public String index(Model model) {
    
        // Bind empty User and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        
        // TO-DO Later -- call a register method in the service 
        // to do some extra validations and create a new user!
        userService.register(newUser, result);
        if(result.hasErrors()) {
            // Be sure to send in the empty LoginUser before 
            // re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        } else {
        	session.setAttribute("user_id", newUser.getId());
        	return "redirect:/dashboard";
        	
        }
        
    
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
        // Add once service is implemented:
        // User user = userServ.login(newLogin, result);
    	User user = userService.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }else {
        	session.setAttribute("user_id", user.getId());
        	return "redirect:/dashboard";
        }
    
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
   
    }
    
    @GetMapping("/logout")
	public String logout(HttpSession s) {
		s.invalidate();
		return "redirect:/";
	}
    
    @GetMapping("/dashboard")
    public String dashboard(HttpSession s, Model m, @ModelAttribute("newProduct") Product newProduct) {
    	Long userId = (Long) s.getAttribute("user_id");
    	
    	if(userId==null) {
    		return "redirect:/";
    	}else {
    		User thisUser = userService.findOne(userId);
    		m.addAttribute("thisUser", thisUser);
    		List<Product> products = productService.allProducts();
    		m.addAttribute("products", products);
    		
    		return "dashboard.jsp";
    	}
    }
    
    @PostMapping("/createProduct")
    public String createProduct(@ModelAttribute("newProduct") Product newProduct, HttpSession s) {
    	
    	
    	Long userId = (Long) s.getAttribute("user_id");
    	User thisLoggedInUser = userService.findOne(userId);
    	
    	newProduct.setUser(thisLoggedInUser);
    	
    	productService.createProduct(newProduct);
    	
    	return "redirect:/dashboard";
    	
    	
    }
    
    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable("id") Long product_id, Model m, @ModelAttribute("editProduct") Product editProduct) {
    	Optional<Product> product = productService.findProduct(product_id);
    	m.addAttribute("old_product", product.get());
    	//System.out.println(product.get().getProductName());
    	return "editProduct.jsp";
    }
    
    @PostMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable("id") Long product_id, @ModelAttribute("editProduct") Product editProduct) {
    	productService.updateProduct(editProduct);
    	return "redirect:/dashboard";
    }
    
    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Long product_id) {
    	productService.deleteProduct(product_id);
    	return "redirect:/dashboard";
    }
    
    @GetMapping("/allProducts/{sessionId}")
    public String allProducts(Model m, @ModelAttribute("newCart") Cart newCart, HttpSession s, @PathVariable("sessionId") String sessionId) {
    	List<Product> allProducts = productService.allProducts();
		List<Cart> carts = cartService.findCartsBySessionId(sessionId);
    	m.addAttribute("allProducts", allProducts);
    	m.addAttribute("sessionId", sessionId);
    	
    	int totalQTY = 0;
    	for(int i = 0; i < carts.size(); i++) {
    		totalQTY += carts.get(i).getQty();
    	}
    	m.addAttribute("cartNum", totalQTY);
    	return "allProducts.jsp";
    }
    
    
    
    @PostMapping("/createCart/{sessionId}")
    public String createCart(@ModelAttribute("newCart") Cart newcart, @PathVariable("sessionId") String sessionId) {
    	cartService.createCart(newcart);
    	return "redirect:/allProducts/" + sessionId;
    }
    
    @PostMapping("/createCartInBest/{sessionId}")
    public String createCartInBest(@ModelAttribute("newCart") Cart newcart, @PathVariable("sessionId") String sessionId) {
    	cartService.createCart(newcart);
    	return "redirect:/home/" + sessionId;
    }
    
    
    @GetMapping("/cart/{sessionId}")
    public String viewCart(Model m, @PathVariable("sessionId") String sessionId) {
    	List<Cart> carts = cartService.findCartsBySessionId(sessionId);
    	int totalQTY = 0;
    	double totalPrice = 0;
    	for(int i = 0; i < carts.size(); i++) {
    		totalQTY += carts.get(i).getQty();
    		totalPrice += carts.get(i).getQty() * carts.get(i).getProduct().getPrice();
    	}
    	
    	m.addAttribute("cartNum", totalQTY);
    	m.addAttribute("carts", carts);
    	m.addAttribute("totalPrice", totalPrice);
    	m.addAttribute("sessionId", sessionId);
    	return "cart.jsp";
    }
    
    @GetMapping("/deleteFromCart/{id}/{sessionId}")
    public String deleteFromCart(@PathVariable("id") Long cart_id, @PathVariable("sessionId") String sessionId) {
    	cartService.deleteCart(cart_id);
    	return "redirect:/cart/" + sessionId;
    }
    
    @GetMapping("/editCart/minus/{id}/{sessionId}")
    public String minusCartQTY(@PathVariable("id") Long cart_id, @PathVariable("sessionId") String sessionId) {
    	Optional<Cart> cart = cartService.findOne(cart_id);
    	if(cart.get().getQty() == 1) {
    		cartService.deleteCart(cart_id);
    	}else {
    		cart.get().setQty(cart.get().getQty()-1);
        	cartService.updateCart(cart.get());
    	}
    	return "redirect:/cart/" + sessionId;
    }
    @GetMapping("/editCart/add/{id}/{sessionId}")
    public String addCartQTY(@PathVariable("id") Long cart_id, @PathVariable("sessionId") String sessionId) {
    	Optional<Cart> cart = cartService.findOne(cart_id);
    	cart.get().setQty(cart.get().getQty()+1);
        cartService.updateCart(cart.get());
    	return "redirect:/cart/" + sessionId;
    }
    
    @GetMapping("/contactus/{sessionId}")
    public String contactUs(Model m, @PathVariable("sessionId") String sessionId) {
    	List<Cart> carts = cartService.findCartsBySessionId(sessionId);
    	int totalQTY = 0;
    	for(int i = 0; i < carts.size(); i++) {
    		totalQTY += carts.get(i).getQty();
    	}
    	m.addAttribute("cartNum", totalQTY);
    	return "contactus.jsp";
    }
    
    @GetMapping("/more/{sessionId}")
    public String more(Model m, @PathVariable("sessionId") String sessionId) {
    	List<Cart> carts = cartService.findCartsBySessionId(sessionId);
    	int totalQTY = 0;
    	for(int i = 0; i < carts.size(); i++) {
    		totalQTY += carts.get(i).getQty();
    	}
    	m.addAttribute("cartNum", totalQTY);
    	return "more.jsp";
    }
    
    @GetMapping("/placeOrder/{sessionId}")
    public String placeOrder(Model m, @ModelAttribute("newOrder") Order newOrder, @PathVariable("sessionId") String sessionId) {
    	List<Cart> carts = cartService.findCartsBySessionId(sessionId);
    	int totalQTY = 0;
        double totalPrice = 0;
        for(int i = 0; i < carts.size(); i++) {
        	totalQTY += carts.get(i).getQty();
        	totalPrice += carts.get(i).getQty() * carts.get(i).getProduct().getPrice();
        }
        m.addAttribute("carts", carts);
        m.addAttribute("cartNum", totalQTY);
        m.addAttribute("totalPrice", totalPrice);
        m.addAttribute("sessionId", sessionId);
    	return "order.jsp";
    }
    
    @PostMapping("/editOrder/{sessionId}")
    public String EditOrder(@Valid@ModelAttribute("newOrder") Order newOrder, BindingResult result,@PathVariable("sessionId") String sessionId, Model model) {
    	List<Cart> carts = cartService.findCartsBySessionId(sessionId);
    	if(result.hasErrors()) {
    		int totalQTY = 0;
            double totalPrice = 0;
            for(int i = 0; i < carts.size(); i++) {
            	totalQTY += carts.get(i).getQty();
            	totalPrice += carts.get(i).getQty() * carts.get(i).getProduct().getPrice();
            }
            
            model.addAttribute("cartNum", totalQTY);
            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("sessionId", sessionId);
            model.addAttribute("newOrder", new Order());
            model.addAttribute("carts", carts);
            return "order.jsp";
    	}else {
    		Random random = new Random();
            long randomInt = random.nextInt(999999999);
            String orderNumber = "OH" + String.valueOf(randomInt);
            List<Order> findOrder = orderService.findOrdersByOrderNumber(orderNumber);
            //System.out.print(findOrder);
            while(findOrder.size() != 0) {
            	randomInt = random.nextInt(999999999);
            	orderNumber = "OH" + String.valueOf(randomInt);
                findOrder = orderService.findOrdersByOrderNumber(orderNumber);
            }
        	 for(int i = 0; i < carts.size(); i++) {
             	Order oneOrder = new Order();
             	oneOrder.setName(newOrder.getName());
             	oneOrder.setPhoneNumber(newOrder.getPhoneNumber());
             	oneOrder.setPayment(newOrder.getPayment());
             	oneOrder.setPickUpDate(newOrder.getPickUpDate());
             	oneOrder.setProduct(carts.get(i).getProduct());
             	oneOrder.setQty(carts.get(i).getQty());
             	oneOrder.setOrderNumber(orderNumber);
             	orderService.createOrder(oneOrder);
             	
             }
        	
        	return "redirect:/confirm/"+ sessionId + "/" +orderNumber;
    	}
    	
    }
    
    @GetMapping("/confirm/{sessionId}/{orderNumber}")
    public String confirm(Model m,@PathVariable("sessionId") String sessionId ,@PathVariable("orderNumber") String orderNumber) {
    	
    	List<Order> orders = orderService.findOrdersByOrderNumber(orderNumber);
    	List<Cart> carts = cartService.findCartsBySessionId(sessionId);
    	int i = 0;
    	
    	while(i < carts.size()) {
    		cartService.deleteCart(carts.get(i).getId());
    		System.out.print(carts.size() + " " + i);
    		i++;
    	}
    	//System.out.println(orders);
    	m.addAttribute("orders", orders);
    	double totalPrice = 0;
    	String message = "Order number " + orders.get(0).getOrderNumber() + "\n" + "Name: " + orders.get(0).getName() + "\n" + "Phone Number: "+ orders.get(0).getPhoneNumber() + "\nPick Up Date: " + orders.get(0).getPickUpDate() +"\n";
    	for(int j = 0; j<orders.size(); j++) {
    		message += "item: " + orders.get(j).getProduct().getProductName() + " QTY: " + orders.get(j).getQty() + "\n";
    	
    		totalPrice += orders.get(j).getQty() * orders.get(j).getProduct().getPrice();
    	}
    	//System.out.println(message);
    	m.addAttribute("totalPrice", totalPrice );
    	m.addAttribute("sessionId", sessionId);
    	m.addAttribute("cartNum", 0);
    	
    	
    	
    	
    	
    	
    	emailService.sendSimpleEmail("kpm.vista@gmail.com", "New Order " + orderNumber, message);
    	
    	return "confirm.jsp";
    }
    @GetMapping("/deleteOrder/{sessionId}")
    public String deleteOrder(@PathVariable("sessionId") String sessionId) {
    	return "redirect:/cart/" + sessionId;
    }
    

}
