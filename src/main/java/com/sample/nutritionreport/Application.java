package com.sample.nutritionreport;

import com.sample.nutritionreport.model.Food;
import com.sample.nutritionreport.model.MacroNutrient;
import com.sample.nutritionreport.model.Nutrition;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws JRException {
		SpringApplication.run(Application.class, args);



	}


}
