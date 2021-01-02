package com.freshvotes.web;

import com.freshvotes.domain.Feature;
import com.freshvotes.services.FeatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Optional;


@Controller
@RequestMapping("/products/{productId}/features")
public class FeatureController {
    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private FeatureService featureService;


    @PostMapping("") // this maps to -> "/products/{productId}/features"
    public String createFeature(@PathVariable Long productId){
        Feature feature = featureService.createFeature(productId);
        return  "redirect:/products/" + productId + "/features/" + feature.getId();
    }

    @GetMapping("{featureId}")
    public String getFeature(ModelMap model, @PathVariable Long productId, @PathVariable Long featureId){
        Optional<Feature> featureOpt = featureService.findById(featureId);
        if(featureOpt.isPresent()){
            model.put("feature", featureOpt.get());
        }
//        TODO: handle situaton where cannot find a feature by the featureId
        return "feature";
    }

    @PostMapping("{featureId}")
    public String updateFeature(Feature feature, @PathVariable Long productId, @PathVariable Long featureId){
       feature = featureService.save(feature);
//        return "redirect:/products/" + productId + "/features/" + feature.getId();
        String encodedProductName;
        try {
            encodedProductName = URLEncoder.encode(feature.getProduct().getName(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.warn("Unable to encode URL Product Name");
            return "redirect:/dashboard";
        }
        return "redirect:/p/" + encodedProductName;
    }
}
