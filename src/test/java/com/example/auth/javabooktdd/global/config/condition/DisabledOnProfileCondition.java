package com.example.auth.javabooktdd.global.config.condition;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
public class DisabledOnProfileCondition implements ExecutionCondition {

    private static final ConditionEvaluationResult ENABLED =
            ConditionEvaluationResult.enabled("@DisabledOnProfile is not active");

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {

        Optional<DisabledOnTestProfile> annotation =
                context.getElement().map(el -> el.getAnnotation(DisabledOnTestProfile.class));

        if (annotation.isEmpty()) {
            return ConditionEvaluationResult.enabled("No annotation");
        }

        // Environment 직접 가져오기 (SpringExtension 필요 없음)
        String[] activeProfiles = context.getConfigurationParameter("spring.profiles.active")
                .map(p -> p.split(","))
                .orElse(new String[0]);

        String[] disabledProfiles = annotation.get().value();

        for (String active : activeProfiles) {
            for (String blocked : disabledProfiles) {
                if (active.equals(blocked)) {
                    return ConditionEvaluationResult.disabled(
                            "Disabled on active profile: " + active
                    );
                }
            }
        }

        return ConditionEvaluationResult.enabled("Profile allowed");
    }
}
