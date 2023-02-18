package org.example.util.thymeleaf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import javax.annotation.PostConstruct;

//For JSP, a view resolver is needed. For Thymeleaf, a template resolver is needed.
// It will be used to resolve the template name to a template in the folder.
@Slf4j
@Component
public class DecoupledLogicSetup {
    private  final SpringResourceTemplateResolver templateResolver;

    public DecoupledLogicSetup(SpringResourceTemplateResolver templateResolver) {
        this.templateResolver = templateResolver;
    }
    // enable decoupled logic
    @PostConstruct
    public void init() {
        this.templateResolver.setUseDecoupledLogic(true);
        log.info("Decoupled logic enabled");
    }
}
