package com.summerdev.travel.services.api;

import java.util.Optional;

public interface LoggerHelper {
    default String getMethodInfo(StackWalker walker) {
        Optional<String> methodName = walker.walk(frames -> frames
                .findFirst()
                .map(StackWalker.StackFrame::getMethodName));

        return "Method: " + methodName.orElse("unknown");
    }
}
