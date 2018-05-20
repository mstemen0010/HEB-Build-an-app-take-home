package demo.controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
//
//import java.sql.SQLException;
//import java.util.concurrent.atomic.AtomicLong;
//
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import demo.repository.GroceryEntityRepository;
import demo.model.GroceryEntity;
//
@RestController
@RequestMapping("/api/items")
public class ItemController {
//	
//	@Autowired
    private GroceryEntityRepository groceryEntityRepository;
    private static final String template = "{ \"description\": \"%s\" }"; // json
    
    Map<Long, GroceryEntity> groceryItemsMap = new HashMap<Long, GroceryEntity>();

    @Value("${spring.application.name}")
    String appName;
 
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "index";
    }
    /*
     * ID,Description,lastSold,ShelfLife,Department, Price ,Unit,xFor, Cost
753542,banana,9/5/2017,4d,Produce, $2.99 ,lb,1, $0.44
321654,apples,9/6/2017,7d,Produce, $1.49 ,lb,1, $0.20
95175,Strawberry,9/7/2017,3d,Produce, $2.49 ,lb,1, $0.10
321753,onion,9/8/2017,9d,Produce, $1.00 ,Each,1, $0.05
987654,Tomato,9/9/2017,4d,Produce, $2.35 ,lb,1, $0.20
11122,grapes,9/10/2017,7d,Produce, $4.00 ,lb,1, $1.20
124872,Lettuce,9/11/2017,5d,Produce, $0.79 ,lb,1, $0.10
113,bread,9/12/2017,14d,Grocery, $1.50 ,Each,1, $0.12
1189,hamburger buns,9/13/2017,14d,Grocery, $1.75 ,Each,1, $0.14
12221,pasta sauce,9/14/2017,23d,Grocery, $3.75 ,Each,1, $1.00
1111,cheese slices,9/15/2017,20d,Grocery, $2.68 ,Each,1, $0.40
18939,sliced turkey,9/16/2017,20d,Grocery, $3.29 ,Each,1, $0.67
90879,tortilla chips,9/17/2017,45d,Grocery, $1.99 ,Each,1, $0.14
119290,cereal,9/18/2017,40d,Grocery, $3.19 ,Each,1, $0.19
4629464,canned vegtables,9/19/2017,200d,Grocery, $1.89 ,Each,1, $0.19
9000001,headache medicine,9/20/2017,365d,Pharmacy, $4.89 ,Each,1, $1.90
9000002,Migraine Medicine,9/21/2017,365d,Pharmacy, $5.89 ,Each,1, $1.90
9000003,Cold and Flu,9/22/2017,365d,Pharmacy, $3.29 ,Each,1, $1.90
9000004,Allegry Medicine,9/23/2017,365d,Pharmacy, $3.00 ,Each,1, $1.25
9000005,Pain,9/24/2017,365d,Pharmacy, $2.89 ,Each,1, $1.00
     */
	@PostConstruct
    public void initIt() throws Exception {
		groceryItemsMap.put(Long.valueOf(1), new GroceryEntity(753542, "banana", "9/5/2017", 4, "Produce", 2.99f, 1, 1, .44f));
		groceryItemsMap.put(Long.valueOf(2), new GroceryEntity(321654, "apples", "9/6/2017", 7, "Produce", 1.49f, 1, 1, .20f));
		groceryItemsMap.put(Long.valueOf(3), new GroceryEntity(95175, "Strawberry", "9/7/2017", 3, "Produce", 2.49f, 1, 1, .10f));
		groceryItemsMap.put(Long.valueOf(4), new GroceryEntity(321753, "onion", "9/8/2017", 9, "Produce", 2.49f, 1, 1, .10f));
		groceryItemsMap.put(Long.valueOf(5), new GroceryEntity(987654, "Tomato", "9/9/2017", 4, "Produce", 2.49f, 1, 1, .10f));
		groceryItemsMap.put(Long.valueOf(6), new GroceryEntity(11122, "grapes", "9/10/2017", 7, "Produce", 2.49f, 1, 1, .10f));
		groceryItemsMap.put(Long.valueOf(7), new GroceryEntity(124872, "Lettuce", "9/11/2017", 5, "Produce", 2.49f, 1, 1, .10f));
		groceryItemsMap.put(Long.valueOf(8), new GroceryEntity(113, "bread", "9/12/2017", 14, "Grocery", 2.49f, 1, 1, .10f));
		groceryItemsMap.put(Long.valueOf(9), new GroceryEntity(1189, "hamburger buns", "9/13/2017", 14, "Grocery", 2.49f, 1, 1, .10f));
		groceryItemsMap.put(Long.valueOf(10), new GroceryEntity(12221, "pasta sauce", "9/4/2017", 23, "Grocery", 2.49f, 1, 1, .10f));
		
    }
    
    @Autowired
    public ItemController(GroceryEntityRepository repository) {
        this.groceryEntityRepository = repository;
    }
    
    @SuppressWarnings("unchecked")
	@GetMapping("/{id}")
    public Optional<GroceryEntity> findById(@PathVariable Long id) {
    	return groceryEntityRepository.findById(id);
    }
    
    @GetMapping(value = "/all")
	public List<GroceryEntity> getResource() {
		
		List<GroceryEntity> gItemList = groceryItemsMap.entrySet().stream()
		        .map(entry -> entry.getValue())
		        .collect(Collectors.toList());
		
		return gItemList;
	}
//    
//    @RequestMapping("/welcome")
//    public String loginMessage(){
//        return "welcome";
//    }
////    @GetMapping("/{id}")
////    public Book findOne(@PathVariable Long id) {
////        return bookRepository.findOne(id)
////          .orElseThrow(BookNotFoundException::new);
////    }
}
