package com.sample.nutritionreport.processor;

import com.sample.nutritionreport.model.Food;
import com.sample.nutritionreport.model.MacroNutrient;
import com.sample.nutritionreport.model.Nutrition;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NutritionReportApplication {
    public ByteArrayOutputStream generateReport() throws JRException {
        String filePath="E:\\nutritionreport\\src\\main\\resources\\templates\\nutritionreport.jrxml";
        Nutrition protein = new Nutrition("Protein", 62, 83, "g");
//		Nutrition  = new Nutrition("Carbohydrated", 180, 206, "g");
        Nutrition fiber = new Nutrition("Fiber", 20, 38, "g");
        Nutrition sugars = new Nutrition("Sugars", 68, 62, "g");
        Nutrition fat = new Nutrition("Fat", 60, 55, "g");
        Nutrition cholesterol = new Nutrition("Cholesterol", 84, 300, "mg");
        Nutrition sodium = new Nutrition("Sodium", 2200, 2300, "mg");
        Nutrition potassium = new Nutrition("Potassium", 2000, 3500, "mg");
        Nutrition calcium = new Nutrition("Calcium", 62, 100, "%");
        Nutrition iron = new Nutrition("Iron", 38, 100, "%");

        MacroNutrient carbMacroNutrient = new MacroNutrient("Carbohydrates", 48);
        MacroNutrient fatMacroNutrient = new MacroNutrient("Fat", 32);
        MacroNutrient proteinMacroNutrient = new MacroNutrient("Protein", 20);

        List<MacroNutrient> macroNutrientList = new ArrayList<>();
        macroNutrientList.add(carbMacroNutrient);
        macroNutrientList.add(fatMacroNutrient);
        macroNutrientList.add(proteinMacroNutrient);

        List<Nutrition> nutritionList = new ArrayList<>();
        nutritionList.add(protein);
        nutritionList.add(fiber);
        nutritionList.add(sugars);
        nutritionList.add(fat);
        nutritionList.add(cholesterol);
        nutritionList.add(sodium);
        nutritionList.add(potassium);
        nutritionList.add(calcium);
        nutritionList.add(iron);



        JRBeanCollectionDataSource nutritionDataSource=new JRBeanCollectionDataSource(nutritionList);
        JRBeanCollectionDataSource macroNutrientDataSource = new JRBeanCollectionDataSource((macroNutrientList));

        Map<String, Object> parameters=new HashMap<>();
        parameters.put("fistName", "John");
        parameters.put("lastName","Madhusanka");
        parameters.put("dob","20/11/1998");
        parameters.put("age",25);
        parameters.put("nutritionDataSet",nutritionDataSource);
        parameters.put("macroNutrientDataSet",macroNutrientDataSource);
//		parameters.put("foodReport",getFoodReport());
//		parameters.put("foodParameter", getFoodParameter());
        JasperReport report= JasperCompileManager.compileReport(filePath);
        JasperPrint print= JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
        ByteArrayOutputStream byteArrayOutPutStream = new ByteArrayOutputStream();
        JRPdfExporter exporter=new JRPdfExporter();
        SimplePdfExporterConfiguration configuration=new SimplePdfExporterConfiguration();
        configuration.setCompressed(true);
        exporter.setConfiguration(configuration);
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutPutStream));
        exporter.exportReport();
        return byteArrayOutPutStream;

    }
    private JRBeanCollectionDataSource getFoodDataSource(){
        List<Food> foodList = new ArrayList<>();
        Food banana = new Food("banana","brekfast",0,28,1);
        Food avocado = new Food("banana","brekfast",0,28,1);
        Food milk = new Food("banana","brekfast",0,28,1);
        Food chicken = new Food("banana","brekfast",0,28,1);
        Food rice = new Food("banana","brekfast",0,28,1);
        Food agg = new Food("banana","brekfast",0,28,1);
        Food pototo = new Food("banana","brekfast",0,28,1);
        Food oats = new Food("banana","brekfast",0,28,1);

        foodList.add(banana);
        foodList.add(avocado);
        foodList.add(milk);
        foodList.add(chicken);
        foodList.add(rice);
        foodList.add(agg);
        foodList.add(pototo);
        foodList.add(oats);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource((foodList));
        return dataSource;
    }
    private Map getFoodParameter(){
        Map<String, Object> foodParameter=new HashMap<>();
        foodParameter.put("foodDataset", getFoodParameter());
        return foodParameter;
    }
    private JasperReport getFoodReport(){
        String filePath = "E:\\nutritionreport\\src\\main\\resources\\templates\\food_nutrition.jrxml";
        JasperReport report = null;
        try{
            report = JasperCompileManager.compileReport(filePath);
        }catch (JRException e){
            throw new RuntimeException(e);
        }
        return report;
    }
}
